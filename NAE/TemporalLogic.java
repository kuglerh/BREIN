package NAE;

import java.io.*;
import java.util.*;

public class TemporalLogic extends Converter{
    private HashMap<String,Experiment> experimentNameToObj;

    private boolean TL_MODE;
    private boolean CTL_MODE;
    public TemporalLogic(String s1,String s2)throws Exception{
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
        
   
    String getModule(Node n,int valueNumber){
        StringBuilder module = new StringBuilder();
        module.append(n.getModuleDeclaration());        
        module.append("VAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value"+i+" : boolean;\n");
        }
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        
        if(n.functions.length != 1){
            module.append("transition"+" : {");
            for(int i=0;i<n.functions.length;i++){
                if(i != 0){
                    module.append(",");
                }
                module.append(n.functions[i]);
            }
            module.append("};\n");
        }
    
        module.append(n.getNetworkVars(valueNumber));
        
        module.append("ASSIGN\n");
    
        module.append(n.getConnectionAndFunctionTransitions());

        module.append(n.getStateAndValueTransitions("main.i",valueNumber));
        
        
        //if the node is synchronous, we end the transition here. Otherwise, we want to add a union with its current value
        //which essentially allows some nodes to update, some not to, giving asynchronous behavior
        
            module.append("\n");
        
        
        return module.toString();
    }

    
    void generateNuSMV(String name)throws Exception{
        int valueNumber = experiments.size();
        StringBuilder code = new StringBuilder();

        for(Node n:nodes.values()){
            code.append(getModule(n,valueNumber));
        }

        code.append("MODULE main\nVAR\n");
        code.append("i : boolean;\n");
       
        for(Node n:nodes.values()){
            code.append(getInitialization(n,sync));
        }
        code.append("ASSIGN\n");
        
        //allow i to traverse through its range
        code.append("init(i) := TRUE;\nnext(i) := FALSE;\n");
              
        code.append("DEFINE\n");
        
        //since we have multiple value vars for the parallel model mode, add corresponding definitions
        for(int i = 0; i < valueNumber;i++){
            code.append(definitions.replace(" ","").replace(":=",i+":=").replace("value","value"+i).replace(".FE",".FE"+i).replace(".KO",".KO"+i));
        }
            
        //don't add specs, as they will be inputed in interactive mode
        stringToFile(code.toString(),name);
    }
   
    String getSpecSTEP(){
        Experiment[] experimentList = experimentNameToObj.values().toArray(new Experiment[0]);
        StringBuilder code = new StringBuilder();
        code.append("((");
        for(int i = 0;i < experimentList.length;i++){
            if(i!=0){
                code.append("|");
            }
            code.append(experimentList[i].getLTLSpecParallel());
        }
        code.append(")\n");
        for(String r:restrictions){
            code.append("|"+r+"\n");
        }
        code.append(")");
        return code.toString();
    }

    String getSpecTL(){
        StringBuilder code = new StringBuilder();
        code.append("(FALSE");
        
        for(String r:restrictions){
            code.append("|("+r+")");
        }
        code.append(")");  

        String next = CTL_MODE ? "AX" : "X";
        return next+"("+code.toString().replaceAll("\\s+","")+")";
    }

    String getSpec(){
        return TL_MODE ? getSpecTL() : getSpecSTEP();
    }
    

    int parseExperimentSTEP(String line,int expNum){
        if(experimentNameToObj==null)experimentNameToObj = new HashMap<>();
        if(line.contains("fixpoint")) return parseFixPoint(line,expNum);

        //first get rid of # and $
         line = line.replaceAll("#|\\$|\\s+|;","");
         //sometimes there is a string on this line explaining what it is. We want to remove it for our purposes now
         String tokens[] = line.split("\"");
         line = "";

         for(int i=0;i<tokens.length;i++){
             //skip everything in quotes, that is all odd numbered indices
             if(i % 2 == 0){
                line += tokens[i]; 
             }
         }

         tokens = line.split("\\[|\\]|\\|=");
         String experimentName = tokens[0];
         int timeIndex = Integer.parseInt(tokens[1]);
         String condition = tokens[3];

        //update duration
        if ((timeIndex+1)>duration) duration = timeIndex+1;
              
         //create an experiment or add to one
         Experiment exp = experimentNameToObj.get(experimentName);
         if(exp == null){
             experimentNameToObj.put(experimentName,new Experiment(experimentName,expNum).add(timeIndex,condition));
             experiments.put(experimentName,expNum++);
             return expNum;
         }else{
             exp.add(timeIndex,condition);

             return expNum;
         }
    }

    //parse experiment from a file not of RE:IN type specs but of ltl
    int parseExperimentTL(String line,int expNum){
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
        
        line = line.replace("and","&").replace(" or ","|").replace("not","!").replace(" or(","|(").replace(")or ",")|").replace(")or(",")|(");
        
        //next, find all SAT requirements.
        ArrayList<String> reqs = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < line.length();i++){
            if((line.charAt(i) == '#')){
                while((i < line.length()) && (line.charAt(i) != '$')){sb.append(line.charAt(i));i++;}
            while((i < line.length()) && (line.charAt(i) != ' ')&& (line.charAt(i) != ')')&& (line.charAt(i) != '}')){sb.append(line.charAt(i));i++;}
                reqs.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
        HashMap<String,String> replacments = new HashMap<>();
        //now parse each one:
        for(String r:reqs){
            tokens = r.replaceAll("\\s+","").split("\\|=");
            
            
            String expName = tokens[0].replace("#","");
            
            if(experiments.get(expName)==null){
                experiments.put(expName,expNum);
                expNum++;
            }
            
            String macroName = tokens[1].replace("$","");
            macroName = macroName  + experiments.get(expName);
            replacments.put(r,macroName);
        }
        

        //perform replacements
        for (Map.Entry<String, String> entry : replacments.entrySet()){
            line = line.replace(entry.getKey(),entry.getValue());
        }
        restrictions.add("!("+line+")");
        
        return expNum;
    }
    
    int parseExperiment(String line,int expNum){
        return TL_MODE ? parseExperimentTL(line,expNum) : parseExperimentSTEP(line,expNum);
    }
              
    private int parseFixPoint(String line,int expNum){
        //first get rid of # and $ and the word fixpoint and ()
        line = line.replaceAll("#|\\$|\\s+|;|fixpoint|\\(|\\)","");
        //sometimes there is a string on this line explaining what it is. We want to remove it for our purposes now
        String tokens[] = line.split("\"");
        line = "";
        
        for(int i=0;i<tokens.length;i++){
            //skip everything in quotes, that is all odd numbered indices
            if(i % 2 == 0){
               line += tokens[i]; 
            }
        }
        tokens = line.split("\\[|\\]|\\|=");
        String experimentName = tokens[0];
        int timeIndex = Integer.parseInt(tokens[1]);
        //create stabilization condition for fixpoint
        String condition = "";
        Node[] nodeArray = nodes.values().toArray(new Node[0]);
        for(int i = 0; i < nodeArray.length;i++){
            if(i!=0){
                condition+="&";
            }
            condition +="(" +nodeArray[i].getName()+".value xnor (EX "+nodeArray[i].getName()+".value))";
        }
        
        //create an experiment or add to one
        Experiment exp = experimentNameToObj.get(experimentName);
        if(exp == null){
            experimentNameToObj.put(experimentName,new Experiment(experimentName,expNum).add(timeIndex,condition));
            experiments.put(experimentName,expNum++);

            return expNum;
        }else{
            exp.add(timeIndex,condition);
            return expNum;
        }
    }
        
    void parseObservation() throws Exception{
        TL_MODE = observationFileName.contains(".ltlspec") || observationFileName.contains(".ctlspec");
        CTL_MODE = observationFileName.contains(".ctlspec");
        super.parseObservation();
    }  
    public ResultSet parseAnswer(BufferedReader input)throws IOException{
        return TL_MODE ? parseAnswerTL(input) : parseAnswerSTEP(input);
    }
    
    public ResultSet parseAnswerTL(BufferedReader input)throws IOException{
        int timeStep = -2;
        int duration = getDuration();
        int numExp = getNumberOfExperiments();
        HashMap<String,ResultSet.NodeData> nodeVals = new HashMap<>();
        HashMap<String,Boolean> optionalConnections = new HashMap<>();
        
       
        String line = null;
        while((line=input.readLine()) != null) { 
            //remove all whitespace 
            line = line.replaceAll("\\s+","");
                     
            if(line.contains("--specification")&&line.contains("istrue")) return null;

        
            //parse connections --
            else if(line.contains("connected=")){
                String[] tokens = line.split("_|=");
                String connectionName = tokens[0];
                boolean value = Boolean.parseBoolean(tokens[2]);
                //add data
                optionalConnections.put(connectionName,value);
            }
            
            //parse functions one hot  encoding --
            else if(line.contains(".transition")){
                //    B.transition = 5

                String[] tokens = line.split("\\.|=");
                
                String node = tokens[0];
                int number = Integer.parseInt(tokens[2]);
                
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
            else if(line.contains("--nocounterexamplefoundwithbound"+(duration+1)))return null;
        }
        
        //add functions of nodes with only one possible function
        for (Map.Entry<String, Integer> entry : nodesWithOneFunction.entrySet()){
            if(nodeVals.get(entry.getKey())==null) nodeVals.put(entry.getKey(),new ResultSet.NodeData(duration,numExp));
            nodeVals.get(entry.getKey()).function = entry.getValue();  
        }
        
        
        return new ResultSet(nodeVals,optionalConnections);
    }

     
    public ResultSet parseAnswerSTEP(BufferedReader input)throws IOException{
        int timeStep = -2;
        int duration = getDuration();
        int numExp = getNumberOfExperiments();
        HashMap<String,ResultSet.NodeData> nodeVals = new HashMap<>();
        HashMap<String,Boolean> optionalConnections = new HashMap<>();
        
       
        String line = null;
        while((line=input.readLine()) != null) {
            //remove all whitespace 
            line = line.replaceAll("\\s+","");
            
            //update timestep 
            if(line.startsWith("->State:")){
                timeStep++;
                if(timeStep<=0) continue;
                //update all node values to have same state as last time, as NuSMV only prints them out if they changed
                for(ResultSet.NodeData d:nodeVals.values()){
                    for(int i = 0;i<numExp;i++){
                        d.values[timeStep][i] = d.values[timeStep-1][i];
                    }
                }
            }            
            if(line.contains("--specification")) continue;
            //parse values --
            if(line.contains(".value")){
                String[] tokens = line.split("\\.|=");
                String node = tokens[0];
                int expNum = Integer.parseInt(tokens[1].substring(5));
                boolean value = Boolean.parseBoolean(tokens[2]);
                //add the data
                if(nodeVals.get(node)==null) nodeVals.put(node,new ResultSet.NodeData(duration,numExp));
                
                nodeVals.get(node).values[timeStep<0?0:timeStep][expNum] = value;
            }
        
            //parse connections --
            else if(line.contains("_connected=")){
                String[] tokens = line.split("_|=");
                String connectionName = tokens[0];
                boolean value = Boolean.parseBoolean(tokens[2]);
                
                //add data
                optionalConnections.put(connectionName,value);
            }
            
            //parse functions one hot  encoding --
            else if(line.contains(".transition")){
                //    B.transition = 5

                String[] tokens = line.split("\\.|=");
                
                String node = tokens[0];
                int number = Integer.parseInt(tokens[2]);
                
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
            else if(line.contains("--nocounterexamplefoundwithbound19andnoloop")) return null;
        }
        
        //add functions of nodes with only one possible function
        for (Map.Entry<String, Integer> entry : nodesWithOneFunction.entrySet()){
            if(nodeVals.get(entry.getKey())==null) nodeVals.put(entry.getKey(),new ResultSet.NodeData(duration,numExp));
            nodeVals.get(entry.getKey()).function = entry.getValue();  
        }
        
        
        return new ResultSet(nodeVals,optionalConnections);
    }


    public SpecType getSpecType(){
        if(!TL_MODE) return SpecType.LTL_BMC;
        if(CTL_MODE) return SpecType.CTL;
        //otherwise, we are in LTL mode, and can run in either BDD mode or BMC mode
        //if duration is non zero, return BMC mode
        if(duration != 0) return SpecType.LTL_BMC;
        return SpecType.LTL_BDD;
    }

     
    //return a string representing how to declare this node as a module in the main module of the nusmv  program
    private String getInitialization(Node n,boolean sync){
        String p = sync ? "":"process";
        StringBuilder init =  new StringBuilder(n.name+" : " +p+ " node_"+n.name+"(self");
        for(Input input:n.inputs){
            init.append(","+input.name);
        }    
        init.append(");\n");
        return init.toString();
    }
    
    
    class Experiment{
    //all observations in this experiment
    private ArrayList<Observation> observations;
    private String name;
    //the number is the position in which it was parsed, useful for LTLP and LTLR modes to tell which variables/states are for which experiment
    private int number;
    Experiment(String name,int number){
        this.name = name;
        this.number = number;
        observations = new ArrayList<>();
    }  
    
    //add a time step and condition, which are then added to our list as an observation object
    Experiment add(int timeStep,String condition){
        observations.add(new Observation(timeStep,condition));
        return this;
    }
    
    //get the duration of this experiment, equivalent to the time-step of the observation with the latest time-step + 1, as the time steps start at 0
    //this is the number of time steps this experiment lasts for
    int getDuration(){
        return Collections.max(observations).timeStep+1;
    }
        
    String getLTLSpecParallel(){
        StringBuilder spec = new StringBuilder("(");
        Collections.sort(observations);
        int currentState = -1;
        for(int i=0;i<observations.size();i++){
            while(currentState<observations.get(i).timeStep){
                if(currentState==-1){
                    spec.append(" X( ");
                }else{
                    spec.append(" X( ");
                }
                currentState++;
            }
            spec.append("!"+observations.get(i).condition+number);
            if(i != observations.size()-1){
                //not last observation
                spec.append("|");
            }
        }
        //balance the parentheses
        for(int i =0;i<=currentState+1;i++){
            spec.append(")");
        }
        spec.append("\n");
        return spec.toString();
    }
        
        
    final class Observation implements Comparable<Observation> {
        final int timeStep;
        final String condition;
        Observation(int timeStep,String condition){
            this.timeStep = timeStep;
            this.condition = condition;
        }   
        //we want to be able to compare observations to see which one came first
        @Override
        public int compareTo(Observation o){
           if(this.timeStep==o.timeStep) return 0;
           return this.timeStep > o.timeStep ? 1 : -1;
        }  
    }   
}
}













        