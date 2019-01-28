package validate;

import NAE.*;
import java.io.*;
import java.util.*;

public class Validate{
    
    //an enum describing the state of a  list of booleans, where all, some, or none are true, or none exsist. Used in evaluating transition functions
    private enum State{
        NONE,SOME,ALL,NONEXTANT
    }
    
    //an array that is basically the function chart from RE:IN doc
    private boolean[][]  functions = new boolean[][]
    {
        {false,false,true,false,false,false,false,false,false},
        {false,true,true,false,false,false,false,false,false},
        {false,false,true,false,false,true,false,false,false},
        {false,true,true,false,false,true,false,false,false},
        {false,false,true,false,false,true,false,false,true},
        {false,true,true,false,false,true,false,false,true},
        {false,true,true,false,true,true,false,false,false},
        {false,true,true,false,true,true,false,false,true},
        {false,true,true,false,true,true,false,true,true},
        {true,true,true,false,false,false,false,false,false},
        {true,true,true,false,false,true,false,false,false},
        {true,true,true,false,true,true,false,false,false},    
        {true,true,true,true,true,true,false,false,false},   
        {true,true,true,false,false,true,false,false,true},    
        {true,true,true,false,true,true,false,false,true},   
        {true,true,true,true,true,true,false,false,true},    
        {true,true,true,false,true,true,false,true,true},   
        {true,true,true,true,true,true,false,true,true}
    };
    
    private String abstractModelFileName;
    private String observationFileName;
    private ResultSet resultSet;
    private boolean sync = true;//synchronous by default
    
    
    //@TODO only for testing quick and dirty will remove later 
    public boolean validate(int numExp,int d,HashMap<String,Integer> e)throws IOException{
        AbstractModel m = parseAbstractModel();
        boolean model =  sync ? validateResultSetSync(m,resultSet,numExp,d) : validateResultSetAsync(m,resultSet,numExp,d);
        boolean observation = parseObservations().validate(resultSet,e);
        return model && observation;
    }
    
    //takes 2 files, a model, and observations, as well as a ResultSet, and validates the solution, both against the model and the observations
    public Validate(String abstractModelFileName,String observationFileName,ResultSet r){
        this.abstractModelFileName = abstractModelFileName;
        this.observationFileName = observationFileName;
        this.resultSet = r;
    }    
    
    //takes a file path and gives back a reader
    static BufferedReader readFile(String file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF-8"));
        //get rid of BOM at beginning of file.
        in.mark(1);
        if (in.read() != 0xFEFF)
          in.reset();
        return in;
    }

    //parse the model file, giving back an AbstractModel
    private AbstractModel parseAbstractModel() throws IOException{
        HashMap<String, AbstractNode>  nodes = new HashMap<>();
        BufferedReader reader = readFile(abstractModelFileName); 
        //for now, we assume model is synchronized.
        String line = null;
        while ((line = reader.readLine()) != null) {
            //for now only dear with sync directive
            if(line.startsWith("directive")){
                if(line.contains(" sync")){
                    sync = true;
                }
                if(line.contains(" async")){
                    sync = false;
                }
            }
            
            //if line has a '[' , it must contain the node list 
            else if(line.contains("[")){
                String lines[] = line.trim().split(";");
                for(String n : lines){
                    //get rid of spaces
                    n = n.replace(" ","");
                    //split along [,],(,) which gives us the name, +- for KO/FE (! to be added later), and function list
                    String[] tokens = n.split("\\[|\\]|\\)|\\(");
                    String name = tokens[0]+Converter.identifier;
                    boolean FE = tokens[1].contains("+");
                    boolean KO = tokens[1].contains("-");
                    int[] functions = getFunctionList(tokens[3]);
                    
                    //create an abstract node
                    AbstractNode node = new AbstractNode(name,functions,KO,FE);
                    nodes.put(name,node);
                }
            }
            
            //otherwise parse the connections
            else if(line.trim().length()!=0){
                String[] tokens = line.trim().split("\\s+");
                //first token is source node,
                //second is target node,
                //third is positive/negative
                //and forth optional arg is the  word optional
                AbstractNode source = nodes.get(tokens[0]+Converter.identifier);
                AbstractNode target = nodes.get(tokens[1]+Converter.identifier);
                boolean isPositive = tokens[2].replace(";","").equals("positive");
                boolean optional = line.contains("optional");
                Input input = new Input(source, isPositive,optional);
                target.addInput(input);
            }
        
        }
        return new AbstractModel(nodes.values().toArray(new AbstractNode[nodes.size()]));
    }
 
    //converts a string of form x,y,z to an int array of those values, for use in parsing the model file
    //or of the form x..y to be x-y
    private int[] getFunctionList(String list){
        if(list.contains("..")){
            String[] tokens = list.split("\\.\\.");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            int[] functions = new int[end-start+1];
            int index = 0;
            for(int f = start;f<=end;f++){
                functions[index] = f;
                index++;
            }
            return functions;
        }
        
        String[] tokens = list.split(",");
        int[] functions = new int[tokens.length];
        for(int i = 0; i < functions.length;i++){
            functions[i] = Integer.parseInt(tokens[i]);
        }
        return functions;
    }
    
    //make sure are given result set updates consistently
    private boolean validateResultSetAsync(AbstractModel a,ResultSet r,int numberOfExperiments,int duration){
            
    
        HashMap<String,boolean[][]> map = new HashMap<>();
        //fill it with initial values
        for(AbstractNode n:a.nodes){
            boolean[][] vals = new boolean[duration][numberOfExperiments];
            for(int e = 0;e<numberOfExperiments;e++){
                vals[0][e] = r.nodeVals.get(n.name).values[0][e];
            }
            map.put(n.name,vals);
        }

        
        for(int t = 0;t<(duration-1);t++){
            for(int e = 0;e<numberOfExperiments;e++){
                for(AbstractNode n:a.nodes){
                    boolean current = r.nodeVals.get(n.name).values[t][e];
                    boolean next = r.nodeVals.get(n.name).values[t+1][e];
                    if(current==next){
                        //didn't update (or update didn't matter, which we can treat the same as not updating)
                        map.get(n.name)[t+1][e] = next;
                    }else{
                        //update
                        map.get(n.name)[t+1][e] = getNextValue(r,n,t,e);
                    }
                }
            }
        }
        
        //verify
        for(AbstractNode n:a.nodes){
            boolean[][] NuSMVvals = r.nodeVals.get(n.name).values;
            boolean[][] verifiedVals = map.get(n.name);
            if(!Arrays.deepEquals(NuSMVvals,verifiedVals)){
                System.out.println(n.name);
                
                for(int i =0;i<NuSMVvals.length;i++){
                    String s = "";
                    for(int j = 0;j < NuSMVvals[0].length;j++){
                        s+= (" "+NuSMVvals[i][j]).replace("true","true ");
                    }
                    System.out.println(s);
                }
                
                System.out.println("_____________________");
                for(int i =0;i<verifiedVals.length;i++){
                    String s = "";
                    for(int j = 0;j < verifiedVals[0].length;j++){
                        s+= (" "+verifiedVals[i][j]).replace("true","true ");
                    }
                    System.out.println(s);
                }
                return false;
            }
        }        
        return true;
    }
    
    
    
    //make sure are given result set updates consistently
    private boolean validateResultSetSync(AbstractModel a,ResultSet r,int numberOfExperiments,int duration){
            
    
        HashMap<String,boolean[][]> map = new HashMap<>();
        //fill it with initial values
        for(AbstractNode n:a.nodes){
            boolean[][] vals = new boolean[duration][numberOfExperiments];
            for(int e = 0;e<numberOfExperiments;e++){
                vals[0][e] = r.nodeVals.get(n.name).values[0][e];
            }
            map.put(n.name,vals);
        }

        
        for(int t = 0;t<(duration-1);t++){
            for(int e = 0;e<numberOfExperiments;e++){
                for(AbstractNode n:a.nodes){
                    map.get(n.name)[t+1][e] = getNextValue(r,n,t,e);
                }
            }
        }
        
        //verify
        for(AbstractNode n:a.nodes){
            boolean[][] NuSMVvals = r.nodeVals.get(n.name).values;
            boolean[][] verifiedVals = map.get(n.name);
            if(!Arrays.deepEquals(NuSMVvals,verifiedVals)){
                System.out.println(n.name);
                
                for(int i =0;i<NuSMVvals.length;i++){
                    String s = "";
                    for(int j = 0;j < NuSMVvals[0].length;j++){
                        s+= (" "+NuSMVvals[i][j]).replace("true","true ");
                    }
                    System.out.println(s);
                }
                
                System.out.println("_____________________");
                for(int i =0;i<verifiedVals.length;i++){
                    String s = "";
                    for(int j = 0;j < verifiedVals[0].length;j++){
                        s+= (" "+verifiedVals[i][j]).replace("true","true ");
                    }
                    System.out.println(s);
                }
                return false;
            }
        }        
        return true;
    }
    
    //given a node, model, resultSet, current time step and experiment number, return nodes value at timeStep+1
    private boolean getNextValue(ResultSet r, AbstractNode n,int timeStep,int numExp){
            //first check for KO/FE
            if((n.KOallowed)&&(Boolean.TRUE.equals(r.nodeVals.get(n.name).KO[numExp]))) return false;
            if((n.FEallowed)&&(Boolean.TRUE.equals(r.nodeVals.get(n.name).FE[numExp]))) return true;
        
             //get this node's function from the resultSet
            int function = r.nodeVals.get(n.name).function;

            //make sure the function is a valid one
            boolean valid = false;
            for(int i = 0; i<n.functions.length;i++){
                if(n.functions[i]==function) {valid = true;break;}
            }
            if(!valid) throw new IllegalArgumentException("Error: invalid function in NuSMV model");
            
            //get the nodes that are inputs via the abstractModel, and then get their values from the resultSet
            ArrayList<Boolean> activators = new ArrayList<>();
            ArrayList<Boolean> repressors = new ArrayList<>();
            
            for(Input input:n.inputs){
                //get its value
                boolean val = r.nodeVals.get(input.n.name).values[timeStep][numExp];
                //get the name of this connection as a string, formatted as dest.src
                String connectionName = n.name +"."+input.n.name;

                
                if(input.isPositive){
                    //either it is mandatory, and add it, or it is optional but part of this concrete model, so add it.
                    //otherwise don't add                    
                    if(!input.optional) activators.add(val); 
                    else if(r.optionalConnections.get(connectionName)) activators.add(val);                                   
                }else{
                    //same deal as above, only with repressors
                    if(!input.optional) repressors.add(val);
                    else if(r.optionalConnections.get(connectionName)) repressors.add(val);
                    
                }
            }
            
            //compute the next value of this node and return it
            return function(function,activators,repressors);
    }
    
    //given a list of booleans, return its state, either ALL true, SOME are true, or NONE are true, or the inputs don't exsist, a special case
    private State getState(ArrayList<Boolean> list){
        if(list.size()==0) return State.NONEXTANT;
        //ands
        boolean conjunction = true;
        //ors
        boolean disjunction = false;
        
        for(boolean b:list){
            conjunction = conjunction && b;
            disjunction = disjunction || b;
        }
        
        if (conjunction) return State.ALL;
        if (!disjunction) return State.NONE;
        return State.SOME;
    }
    
    //returns the value of a node given the function number, and input values
    private boolean function(int function,ArrayList<Boolean> activators,ArrayList<Boolean> repressors){
        
        
        
        //get state of activators:
        State a = getState(activators);
        State r = getState(repressors);
        
        //first check if either state is NONEXTANT
        if((a==State.NONEXTANT) && (r==State.NONEXTANT)){
            return function > 8;
        }
        else if(a==State.NONEXTANT){
            if(r==State.NONE) return true;
            if(r==State.SOME) return ((function==12) || (function==15) || (function==17));
            if(r==State.ALL) return false;
        }
        else if(r==State.NONEXTANT){
            if(a==State.NONE) return false;
            if(a==State.SOME) return !((function==0) || (function==2) || (function==4));
            if(a==State.ALL) return true;
        }
        
        //now convert state to an index that matches 0-8 in accordance with its place on the RE:IN documentation chart 
        int index = getInputIndex(a,r);
        //finally get a function array and return its value at that index
        return functions[function][index];
    }
    
    //REIN defines 9 different inputs, based on repressors or activators being
    //in one of 3 states as specified in the State enum.
    //This function, given 2 statesm returns the input index, 0-8, that this input has in the chart in the RE:IN documentation, for use with the function arrays that
    private int getInputIndex(State a,State r){
        if((a == State.NONE) && (r ==State.NONE)) return 0;
        if((a == State.SOME) && (r ==State.NONE)) return 1;
        if((a == State.ALL) && (r ==State.NONE)) return 2;
        
        if((a == State.NONE) && (r ==State.SOME)) return 3;
        if((a == State.SOME) && (r ==State.SOME)) return 4;
        if((a == State.ALL) && (r ==State.SOME)) return 5;
        
        if((a == State.NONE) && (r ==State.ALL)) return 6;
        if((a == State.SOME) && (r ==State.ALL)) return 7;
        if((a == State.ALL) && (r ==State.ALL)) return 8;
        
        return -1;
    }



    //extracts the boolean predicates and assertions from the observation file. predicates begin with a $, assertions with a #.
    //returns an observation
    private Observation parseObservations() throws IOException{
        //first read file, and get an array of predicate strings before extracting the info
        ArrayList<String> predicates = new ArrayList<>();
        ArrayList<String> assertions = new ArrayList<>();
        
        BufferedReader rd = readFile(observationFileName);
        
        String line = null;
        while ((line = rd.readLine()) != null) {
            //remove comments
            if(line.startsWith("//")) continue;
            
            //parse predicates
            if(line.startsWith("$")){
                StringBuilder sb = new StringBuilder();
                
                while(line != null){
                    sb.append(line);
                    if(line.contains(";")) break;
                    line = rd.readLine();
                }
                predicates.add(sb.toString());
            }
            //parse assertions
            if(line.contains("#")){
                StringBuilder sb = new StringBuilder();
                
                while(line != null){
                    sb.append(line);
                    if(line.contains(";")) break;
                    line = rd.readLine();
                }
                assertions.add(sb.toString());
            }
            
        }
        return new Observation(predicates,assertions);
    }
        
}
