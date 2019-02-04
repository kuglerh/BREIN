package NAE;

import java.io.*;
import java.util.*;

public class CTL extends Converter{
    private HashMap<String,Experiment> experimentNameToObj;
    
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
        restrictions.add("!"+restriction.toString());
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
            experimentNameToObj.put(experimentName,new Experiment(experimentName,expNum++).add(timeIndex,condition));
            return expNum;
        }else{
            exp.add(timeIndex,condition);
            return expNum;
        }
    }
        
    
      
          
    int parseExperiment(String line,int expNum){
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

         //create an experiment or add to one
         Experiment exp = experimentNameToObj.get(experimentName);
         if(exp == null){
             experimentNameToObj.put(experimentName,new Experiment(experimentName,expNum++).add(timeIndex,condition));
             return expNum;
         }else{
             exp.add(timeIndex,condition);
             return expNum;
         }
    }
    
    String getSpec(){
        Experiment[] experimentList = experimentNameToObj.values().toArray(new Experiment[0]);
        StringBuilder code = new StringBuilder();
        code.append(" !((");
        for(int i = 0;i < experimentList.length;i++){
            if(i!=0){
                code.append("&");
            }
            code.append(experimentList[i].getCTLSpec());
        }
        code.append(")\n");
        for(String r:restrictions){
            code.append("&"+r+"\n");
        }
        code.append(")");
        return code.toString();
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
            //if no answer return null
            if(line.replaceAll("\\s+","").contains("--specification")&& line.replaceAll("\\s+","").contains("istrue")) return null;
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
    
    void generateNuSMV(String name)throws Exception{
        int valueNumber = experimentNameToObj.size();
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
        stringToFile(code.toString(),name);
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
    
    
    public SpecType getSpecType(){return SpecType.CTL;}

    
    
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
        
    //returns this experiment as a nusmv CTLSPEC
    String getCTLSpec(){
        StringBuilder spec = new StringBuilder("(");
        Collections.sort(observations);
        int currentState = -1;
        for(int i=0;i<observations.size();i++){
            while(currentState<observations.get(i).timeStep){
                spec.append(" EX( ");
                currentState++;
            }
            spec.append(observations.get(i).condition);
            if(i != observations.size()-1){
                //not last observation
                spec.append("&");
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