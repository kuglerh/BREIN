package NAE;

import java.io.*;
import java.util.*;

public class CTL extends Converter{
    
    public CTL(String s1,String s2)throws Exception{
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
        
       
        line = line.replace("and","&").replace("or","|").replace("not","!");
        
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
        
        String line=null; 
        //get connection names for parsing
        String[] connections = getOptionalConnectionNames();
        Boolean[] values = new Boolean[connections.length];
        //parse to get solution
        while((line=input.readLine()) != null) {
            //get rid of leading white space
            line = line.trim();
            //go through connections
            for(int i = 0;i<connections.length;i++){
                if(line.startsWith(connections[i]+"_connected")){
                    Boolean value = line.contains("TRUE");
                    
                    values[i] = value;
                }
            }
        }    
        HashMap<String,Boolean> optionalConnections = new HashMap<>();
        for(int i = 0;i<connections.length;i++) optionalConnections.put(connections[i],values[i]);
        
        ResultSet r = new ResultSet(null,optionalConnections);
        return r;
    
    }
    
    String generateNuSMV(){
        int valueNumber = experiments.size();
        StringBuilder code = new StringBuilder();
        //if we are using CTL , i is only to distinguish between initial and non-initial states
        for(Node n:nodes.values()){
            code.append(getModule(n));
        }
        code.append("MODULE main\nVAR\n");
        code.append("i : boolean;\n");
        for(Node n:nodes.values()){
            code.append(n.getInitialization());
        }
         code.append("ASSIGN\n");
        //allow i to traverse through its range
        code.append("init(i) := TRUE;\nnext(i) := FALSE;\n");
        code.append("DEFINE\n");
        code.append(definitions);
        //don't add specs, as they will be inputed in interactive mode
        return code.toString();
    }
    

    String getModule(Node n){
        StringBuilder module = new StringBuilder();
        module.append(n.getModuleDeclaration());        
        module.append("VAR\nvalue : boolean;\n");
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        if(n.functions.length != 1){
            module.append("transition : {");
            for(int i=0;i<n.functions.length;i++){
                if(i != 0){
                    module.append(",");
                }
                module.append(n.functions[i]);
            }
            module.append("};\n");
        }
        
        module.append(n.getNetworkVars(1));
        
        module.append("ASSIGN\n");
   
        module.append(n.getConnectionAndFunctionTransitions());

        module.append(n.getStateAndValueTransitions("main.i",1));
        
        
        //if the node is synchronous, we end the transition here. Otherwise, we want to add a union with its current value
        //which essentially allows some nodes to update, some not to, giving asynchronous behavior
        
        if(sync){
            module.append("\n");
        }else{
            module.append("\nFAIRNESS\nrunning;\n");
        }
        return module.toString();
    }
    
    

}


























