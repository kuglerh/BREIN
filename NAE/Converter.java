package NAE;

import java.io.*;
import java.util.*;

public abstract class Converter{
    
    //an enum that describes how this converter's specs should be run
    public static enum SpecType {LTL_BMC_0,LTL_BMC, LTL_BDD,CTL}
    
    String modelFileName;
    String observationFileName;
    
    //added to all node identifiers to make them distinct from nusmv keywords. we want the string to be unusual, as it will cause problems if found in an identifier
    public static final String identifier = "iIi";
    
    //name of the NuSMV file created
    String fileName;
    
    //data from model:
    HashMap<String,Node> nodes= new HashMap<>();
    //a map of all nodes with only a single possible function to their function number
    //needed to add to resultSet, as these functions won't be gleaned from parsing counterexample
    HashMap<String,Integer> nodesWithOneFunction = new HashMap<>(); 
    boolean sync = true;

    //data from observation:
    //a list of macro definitions
    String definitions;
    
    //map of Experiment names to their corresponding integral representations within NuSMV
    HashMap<String,Integer> experiments = new HashMap<>();
    //duration of longest experiment (and add 1 for fixpoint)
    int duration = 0;
    //number of total experiments
    int numberOfExperiments = 0;
    
    
    //a list of the names of optional connections, used when parsing the nusmv output
    ArrayList<String> optionalConnectionNames = new ArrayList<>();
    //list of restrictions on solutions to put into nusmv file
    ArrayList<String> restrictions = new ArrayList<>();
    //a boolean representing if we want CTL representation. false would indicate LTL
        
    public Converter(String model,String observation)throws Exception{
        modelFileName = model;
        observationFileName = observation;
        fileName = convert();
    }
    
    public String getFileName(){return fileName;}
    
    //this creates a nusmv file from the model and observation RE:IN files, returning the file's name
    String convert()throws Exception{
        String name = modelFileName.split("\\.")[0]+".smv";
        parseModel();
        parseObservation();
        generateNuSMV(name);
        return name;
    }
    
    //parse the model file given in the constructor
    void parseModel() throws Exception{
        BufferedReader model = readFile(modelFileName);
        //read through the file
        String line = null;
        //line number for error messages
        int lineNumber = 0;
        while((line=model.readLine()) != null){
            lineNumber++;
            //for now the only directive we care  about is syncronous.
            //first trim all leading spaces from the line.
            line = line.trim();
            
            if(line.startsWith("directive updates ")){
                //this regulates if updates are sync or async. No other values are allowed
                if(line.substring(18).trim().startsWith("sync")){
                    sync = true;
                }else if(line.substring(18).trim().startsWith("async")){
                    sync = false;
                }else{
                    throw new RuntimeException("ERROR at line "+lineNumber+ ": directive updates must be followed by 'sync' or 'async'. Found: "+line.substring(18).trim());
                }                
            }else if(line.startsWith("directive")){
                //ignore for now
            }else{
                //if it does not start with directive, it can be either a node or a connection.
                //since it may be a node, first thing is to split it by ';' then deal with each part separately.
                String[] clauses = line.split(";");
                for(String c:clauses){
                    //if this line contains a "(" it must be a node declaration
                    if(c.contains("(")){
                        //this is a node declaration
                        createNode(c);
                    }else{
                        //ignore a line of just spaces
                        if(line.trim().length()==0){
                            continue;
                        }
                        //must be a connection
                        createConnection(c);
                    }
                }
            }
            
        }
        
        
    }
    
    //model parsing helper methods               
    void createNode(String line){
        //get the name of the node
        StringBuilder name = new StringBuilder();
        for(int i = 0; i < line.length();i++){
            if((line.charAt(i) == '[' ) ||(line.charAt(i)== '(')){
                break;
            }
            name.append(line.charAt(i));
        }
        name.append(identifier);
        boolean KO = false;        
        boolean FE = false;
        boolean needsActivator = false;
        if(line.contains("[")){
            KO = line.split("\\[|\\]")[1].contains("-");
            FE = line.split("\\[|\\]")[1].contains("+");
            needsActivator = line.split("\\[|\\]")[1].contains("!");
        }
        //get the function data
        int f[]= null;
        //get where functions data starts
        int i=0;
        for(; i < line.length();i++){
            if(line.charAt(i)== '('){
                i++;
                break;
            }
        }
        
        StringBuilder functions = new StringBuilder();
        for(;i<line.length();i++){           
            //if data has ended, break
            if(line.charAt(i)== ')'){
                break;
            }
            functions.append(line.charAt(i));
        }
        f = getFunctions(functions.toString());
        if(f.length == 1){
            nodesWithOneFunction.put(name.toString().trim(),f[0]);
        }
        nodes.put(name.toString().trim(),new Node(name.toString().trim(),f,KO,FE,needsActivator,sync));
    }  
          
     int[] getFunctions(String f){
        if(f.contains(",")){
            //this is not a list
            String[] functions = f.split(",");
            int[] results = new int[functions.length];
            for(int i = 0; i < results.length;i++){
                results[i] = Integer.parseInt(functions[i].trim());
            }
            return results;
        }else if(f.contains("..")){
            //this is a list
            String[] functions = f.split("\\.\\.");
            int start = Integer.parseInt(functions[0]);
            int end = Integer.parseInt(functions[1]);
            int[] results = new int[end-start+1];
            for(int i = start; i <=end;i++){
                results[i-start] = i;
            }
            return results;
        }else{
            //it is a single number
            return new int[]{Integer.parseInt(f)};
        }
    }

    void createConnection(String line){
        String[] tokens = line.split("\\s+");
        /*four possible tokens
          1) mandatory: source
          2) mandatory: target
          3) mandatory: positive/negative
          4) optional: optional (if nothing is there, assumed not to be optional, if optional is there it is optional)
        */
        String name = tokens[0]+identifier;
        String target = tokens[1]+identifier;
        
        boolean isPositive = tokens[2].equals("positive");
        boolean optional = tokens.length>3;
        
        if(optional){
           //add this connection name to optionalConnectionNames, in the format <target>.<name>
           optionalConnectionNames.add(target+"."+name);
        }
              
        Input input = new Input(name,isPositive,optional);
        Node n = nodes.get(target);
        if(n == null){
            throw new RuntimeException("Unknown name of node: "+target);
        }
        n.addInput(input);
    }
    
    

    void parseObservation() throws Exception{
        StringBuilder definitions = new StringBuilder();
        StringBuilder SPECS = new StringBuilder();
        BufferedReader observation = readFile(observationFileName);
        //read through the file
        String line = null;
        //line number for error messages
        int lineNumber = 0;
        //a count for what number experiment this is, useful for LTLP\LTLR modes to tell which vars/states are for which experiment
        int expNum = 0;
        while((line=observation.readLine()) != null){
            //if line starts with $, we are defining a predicate and thus should add it as a define statement.

            if(line.startsWith("$")){
                //sometimes the predicate is defined via multiple lines, in which case we should parse them together
                //thus we add all subsequent lines until we hit a semi-colon
                while(!line.contains(";")){
                    String nextLine = observation.readLine();
                    if(nextLine==null) break;
                    line+=nextLine;
                }     
                definitions.append(observationMacroToDefineStatement(line));
            }  
            else if(line.contains("#")){
                //sometimes the constraint is defined via multiple lines, in which case we should parse them together
                //thus we add all subsequent lines until we hit a semi-colon
                while(!line.contains(";")){
                    String nextLine = observation.readLine();
                    if(nextLine==null) break;
                    line+=nextLine;
                }
                
                expNum = parseExperiment(line,expNum);
            }

        }
        numberOfExperiments = expNum;      
        this.definitions = definitions.toString();
    }
       
    //IO helper methods
    BufferedReader readFile(String file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF-8"));
        //get rid of BOM at beginning of file.
        in.mark(1);
        if (in.read() != 0xFEFF)
          in.reset();
        return in;
    }
        
    void stringToFile(String s,String fileName)throws Exception{
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.print(s);
        writer.close();
        
    }
        
    public int getDuration(){return duration;}
    
    public int getNumberOfExperiments(){return numberOfExperiments;}
    
    public HashMap<Integer,String> getNumberToExperimentMap(){
        HashMap<Integer,String> ret= new HashMap<>();
        for (Map.Entry<String, Integer> entry : experiments.entrySet()){
            ret.put(entry.getValue(),entry.getKey());
        }
        return ret;
    }
    
    public HashMap<String,Integer> getExperimentToNumberMap(){
        return experiments;
    }
    
    //for use in printing results out
    String[] getOptionalConnectionNames(){
        return optionalConnectionNames.toArray(new String[optionalConnectionNames.size()]);
    }   
    
    //this method is used to set the bmc length of Converters running .ltlspec files 
    void setDuration(int d){duration = d;}
    
    //abstract methods 
    abstract String observationMacroToDefineStatement(String s);
    abstract int parseExperiment(String line,int expNum);
    abstract void generateNuSMV(String name)throws Exception;
    abstract public void restrictResult(ResultSet r);
    abstract String getSpec();
    abstract public ResultSet parseAnswer(BufferedReader input)throws IOException;
    abstract public SpecType getSpecType();
}