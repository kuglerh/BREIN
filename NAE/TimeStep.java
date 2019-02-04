package NAE;

import java.io.*;
import java.util.*;

public class TimeStep extends Converter{
    
    public TimeStep(String s1,String s2)throws Exception{
        super(s1,s2);
    }


    
    //dis-allow this answer
    public void restrictResult(ResultSet r){
        if(r==null) throw new IllegalArgumentException("Error: null ResultSet");
        String[] connections = getOptionalConnectionNames(); //format is target.origin
        StringBuilder restriction = new StringBuilder("(");
        for(int i = 0;i< connections.length;i++){
            if(i!=0){
                restriction.append("&");
            }
            
            boolean connectionValue = r.optionalConnections.get(connections[i]);            
            String prefix = connectionValue ? "" : "!";
            
            restriction.append(prefix+connections[i]+"_connected");
        }
        restriction.append(")");
        restrictions.add(restriction.toString());
    }
          
    //convert macros in observation file to the NuSMV equivalent, define statements
    String observationMacroToDefineStatement(String s){
        
        //separate name from rest of string
        String def = s.substring(1).replaceAll("and","&")+"\n";
        String name = def.split("\\s+")[0];
        def = def.replace(name,"");
        
        for(Node n:nodes.values()){
            //remove identifier from name before replacing
            def = def.replace(n.getName().substring(0,n.getName().length() - identifier.length()),n.getName()+".value");
        }

        //remove spaces
        def = def.replaceAll("\\s+","");
        def = def.replace("=0","=FALSE");
        def = def.replace("=1","=TRUE");

        //change all occurrences of KO(<name>.value) to <name>.KO, and FE(<name>.value) to <name>.FE
        for(Node n:nodes.values()){
            def = def.replaceAll("KO\\("+n.getName()+".value\\)",n.getName()+".KO");
            def = def.replaceAll("FE\\("+n.getName()+".value\\)",n.getName()+".FE");
        }

        return name+def;
    }
        

    //returns the current number of experiments encountered, for use in assigning numbers to newly found experiments
    int parseExperiment(String line,int expNum){
        //remove semicolon
        line = line.replace(";","");
    
        //sometimes there is a string on this line explaining what it is, ie a comment. We want to remove it for our purposes 
        String tokens[] = line.split("\"");
        line = "";
        for(int i=0;i<tokens.length;i++){
            //skip everything in quotes, that is all odd numbered indices
            if(i % 2 == 0){
                line += tokens[i]; 
            }
        }
         
        /*once comments in quotes are removed, begin to parse. The string can contain any number of the following literals:
            *and -> & 
            *or ->  |
            *not -> !
            *( -> as is
            *) -> as is
            *fixpoint(#<EXP-name>[<time-step>])
            * #<EXP-name>[<time-step>]  |= $<macro>    -> the macro at specified experiment and time-step
            
            
        */
        
       
        line = line.replace("and","&").replace(" or ","|").replace("not","!").replace(" or(","|(").replace(")or ",")|").replace(")or(",")|(");
        
        //next, find all SAT requirements.
        ArrayList<String> reqs = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < line.length();i++){
            if((line.charAt(i) == '#')&&((i<9)||(!line.substring(i-9,i).equals("fixpoint(")))){
                while((i < line.length()) && (line.charAt(i) != '$')){sb.append(line.charAt(i));i++;}
            while((i < line.length()) && (line.charAt(i) != ' ')&& (line.charAt(i) != ')')&& (line.charAt(i) != '}')){sb.append(line.charAt(i));i++;}
                reqs.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
        //find fixpoints
        ArrayList<String> fixPoints = new ArrayList<>();
        String[] fp = line.split("ixpoint\\(");
        for(int i = 1;i<fp.length;i+=2){
            String token = fp[i].split("\\)")[0];
            fixPoints.add(token);
        }
        
        
        HashMap<String,String> replacments = new HashMap<>();
        //now parse each one:
        for(String r:reqs){
            tokens = r.split("\\[|\\]|\\$");
            int time = Integer.parseInt(tokens[1]);
            
            //update duration
            if ((time+1)>duration) duration = time+1;
            
            
            String expName = tokens[0].replace("#","");
            
            if(experiments.get(expName)==null){
                experiments.put(expName,expNum);
                expNum++;
            }
            
            //macro format is name<timeStep>-<ExpNum>
            String macroName = tokens[3];
            macroName = macroName + time + "-" + experiments.get(expName);
            replacments.put(r,macroName);
        }
        
        //parse fixpoints
        for(String f:fixPoints){
            tokens = f.split("\\[|\\]");
            int time = Integer.parseInt(tokens[1]);
            //update duration
            if ((time+2)>duration) duration = time+2;
            
            int num = experiments.get(tokens[0].replace("#",""));
         
            //create stabilization condition for fixpoint
            String condition = "(";
            Node[] nodeArray = nodes.values().toArray(new Node[0]);
            for(int i = 0; i < nodeArray.length;i++){
                if(i!=0){
                    condition+="&";
                }                 
                //'@' is changed to space later                
                condition +="(" +nodeArray[i].getName()+".value"+time+"-"+num+"@xnor@"+nodeArray[i].getName()+".value"+(time+1)+"-"+num   +")";
            }
            condition+=")";
            
            replacments.put("fixpoint("+f+")",condition);
        }
        
        //perform replacements
        for (Map.Entry<String, String> entry : replacments.entrySet()){
            line = line.replace(entry.getKey(),entry.getValue());
        }
        
        restrictions.add("!("+line+")");
        
        return expNum;
    }

    void generateNuSMV(String name)throws Exception{
        int valueNumber = experiments.size();
        PrintWriter code = new PrintWriter(name, "UTF-8");


        
        //print all modules
        for(Node n:nodes.values()){
            code.print(getModule(n,valueNumber,duration));
        }

        //add main module
        code.print("MODULE main\nVAR\n");
        for(Node n:nodes.values()){
            code.print(n.getInitialization());
        }
        
        
        //add the macros
        code.print("DEFINE\n");
        
        //since we have multiple value vars for the parallel model mode, add corresponding definitions
        for(int i = 0; i < valueNumber;i++){
            for(int step = 0;step<duration;step++){
                code.print(definitions.replace(" ","").replace(":=",step+"-"+i+":=").replace("value","value"+step+"-"+(i)).replace(".FE",".FE"+(i)).replace(".KO",".KO"+(i)));
            }
        }
        code.close();      
    }
        
    String getSpec(){
        StringBuilder code = new StringBuilder();
        code.append("(FALSE");
        
        for(String r:restrictions){
            code.append("|("+r+")");
        }
        code.append(")");        
        return code.toString().replaceAll("\\s+","");
    }

    
    //methods for use in parsing counterExample:   
    //parse the counter Example
    //return null if there is no answer
    public ResultSet parseAnswer(BufferedReader input)throws IOException{
        /*in ltlpf mode, we want to glean the following knowledge:
            1)value of optional connections
            2)function values (one hot encoding)
            3)KO/FE state of nodes
            4)values of nodes at each time point for each experiment
        
            we have the following data structures to do so:
            1)map of ints to experiment name 
            2)list of optional connections
            3)list of nodes that have a single function associated with them
            
            and can store it in the following data structures:
            
            1)map of connection names to boolean values
            2)map of nodes to integer function values, KO/FE, and a 2d array indexed by [time][expNum]
                time can be gotten from duration var in converter objects as can expnum
            
            All this is encapsulated in a ResultSet
            
            Here is the format of the input:
            
            functions with one-hot encoding:
            <nodeName>.transitionN<functionNumber> = 0/1   (we could use transition variable as well, but use transitionN as it is easier to disambiguate)
            
            optional Connection:
            C.B_connected = FALSE   :  B->C is false   
            
           KO/FE:
           <nodeName>.KO/FE<num> = TRUE
            
            
            Node values:
            <NodeName>.value<timeStep>-<EXP_NUM> = FALSE
            
        */
        int duration = getDuration();
        int numExp = getNumberOfExperiments();
        HashMap<String,ResultSet.NodeData> nodeVals = new HashMap<>();
        HashMap<String,Boolean> optionalConnections = new HashMap<>();
        
       
        String line = null;
        while((line=input.readLine()) != null) {             
            //remove all whitespace 
            line = line.replaceAll("\\s+","");
            if(line.contains("--specification")) continue;
            //parse values
            if(line.contains(".value")){
                String[] tokens = line.split("\\.|\\-|=");
                String node = tokens[0];
                int timeStep = Integer.parseInt(tokens[1].substring(5));
                int expNum = Integer.parseInt(tokens[2]);
                boolean value = Boolean.parseBoolean(tokens[3]);
                
                //add the data
                if(nodeVals.get(node)==null) nodeVals.put(node,new ResultSet.NodeData(duration,numExp));
                
                nodeVals.get(node).values[timeStep][expNum] = value;
            }
        
            //parse connections
            else if(line.contains("_connected=")){
                String[] tokens = line.split("_|=");
                String connectionName = tokens[0];
                boolean value = Boolean.parseBoolean(tokens[2]);
                
                //add data
                optionalConnections.put(connectionName,value);
            }
            
            //parse functions one hot  encoding
            else if(line.contains(".transitionN")){
                //    B.transitionN8 = 0

                String[] tokens = line.split("\\.|=");
                if(tokens[2].equals("0")) continue;
                //otherwise we know this transition var is true, ie the value of this node's function
                
                String node = tokens[0];
                int number = Integer.parseInt(tokens[1].substring(11));
                
                //add data
                if(nodeVals.get(node)==null) nodeVals.put(node,new ResultSet.NodeData(duration,numExp));
                
                nodeVals.get(node).function = number;
                
            }
            
            //parse KO
            else if(line.contains(".KO")){
                //verify this is not just a connection to a node whose name includes 'KO',
                String[] tokens = line.split("\\.");
                if ((tokens.length==2)&&tokens[1].contains("KO")){
                    //node.KO1=TRUE 
                    tokens = line.split("\\.KO|=");
                    String node = tokens[0];
                    int expNum = Integer.parseInt(tokens[1]);
                    boolean value = Boolean.parseBoolean(tokens[2]);
                    
                    //add data
                    if(nodeVals.get(node)==null) nodeVals.put(node,new ResultSet.NodeData(duration,numExp));
                    
                    nodeVals.get(node).KO[expNum] = value;
                }
            }
            
            //parse FE
            else if(line.contains(".FE")){
                //verify this is not just a connection to a node whose name includes 'FE',
                String[] tokens = line.split("\\.");
                if ((tokens.length==2)&&tokens[1].contains("FE")){
                    //node.KO1=TRUE 
                    tokens = line.split("\\.FE|=");
                    String node = tokens[0];
                    int expNum = Integer.parseInt(tokens[1]);
                    boolean value = Boolean.parseBoolean(tokens[2]);
                    
                    //add data
                    if(nodeVals.get(node)==null) nodeVals.put(node,new ResultSet.NodeData(duration,numExp));
                    
                    nodeVals.get(node).FE[expNum] = value;
                }
            }
            
            //if no answer return null
            else if(line.contains("nocounterexample")) return null;
        }
        
        //add functions of nodes with only one possible function
        for (Map.Entry<String, Integer> entry : nodesWithOneFunction.entrySet()){
            if(nodeVals.get(entry.getKey())==null) nodeVals.put(entry.getKey(),new ResultSet.NodeData(duration,numExp));
            nodeVals.get(entry.getKey()).function = entry.getValue();  
        }
        
        
        return new ResultSet(nodeVals,optionalConnections);
    }

    String getModule(Node n,int valueNumber,int duration){
        StringBuilder module = new StringBuilder();
        module.append(n.getModuleDeclaration());        
        module.append("FROZENVAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value0-"+(i)+" : boolean;\n");
        }
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("value"+step+"-"+(valNum)+" : boolean;\n");
            }
        }
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = ""+i;
            if(n.KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(n.FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        
        //use one-hot encoding to eliminate transitional branching
        if(n.functions.length != 1){
            for(int i=0;i<n.functions.length;i++){
                module.append("transition"+n.functions[i]+": boolean;\n");
            }
        }
        //add a FROZENVAR for every optional connection
        for(Input input:n.inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }  
        
        //add init clause  to ensure one-hot encoding

        if(n.functions.length != 1){
            module.append("INIT\n(");
            for(int i=0;i<n.functions.length;i++){
                if(i!=0){
                    module.append("+");
                }
                module.append("transitionN"+n.functions[i]);
            }
            module.append(")=1");
        }
        
        module.append("\nDEFINE\n");
        //define terms to ensure one-hot encoding in init clause
        if(n.functions.length != 1){
            for(int i=0;i<n.functions.length;i++){
                module.append("transitionN"+n.functions[i]+":= "+"transition"+n.functions[i]+"?1:0;\n");
            }
        }
        module.append("ASSIGN\n");
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){ 
                String async = n.sync ? "" : "value"+(step-1)+"-"+(valNum)+" union ";
                module.append("init(value"+step+"-"+(valNum)+") :="+async+"((\n");

                //transitions for all functions

                for(int i=0;i<n.functions.length;i++){
                    Integer f = n.functions[i];
                    //use function class to generate a function from the transition value and inputs
                    String function = new Function(f,n.inputs.toArray(new Input[0])).toString().replace(";","");
                    if(i!=0){
                        module.append("|");
                    }
                    if(n.functions.length!=1){
                        module.append("(transition"+f+" & ("+function.replace("value","value"+(step-1)+"-"+(valNum))+"))\n");
                    
                    }else{
                        //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                        //do this instead
                            module.append(function.replace("value","value"+(step-1)+"-"+(valNum))+"\n");
                    }
                }
                //if KO or FE exist, when they are true they override all other transition functions
                //be  cautious since and goes before or in order of operations
                if(n.FE){
                    module.append("|FE"+(valNum)+"\n");
                }
                
                module.append(")");
                if(n.KO){
                    module.append("&!KO"+(valNum)+"\n");
                }

                module.append(");\n");
            }
        }
        
        return module.toString();
    }
    
    public SpecType getSpecType(){return SpecType.LTL_BMC_0;}
}













