package NAE;

import java.io.*;
import java.util.*;

public class Converter{
    
    private static boolean[][]  functions = new boolean[][]
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
    
    private static boolean verifyFunction(int function,Boolean A1,Boolean A2,Boolean R1,Boolean R2){
        boolean optional = false;
        Input a1 = new Input("a1",true,optional);
        Input a2 = new Input("a2",true,optional);
        Input r1 = new Input("r1",false,optional);
        Input r2 = new Input("r2",false,optional);
        Input[] inputs = new Input[4];
        inputs[0] = a1;
        inputs[1] = a2;
        inputs[2] = r1;
        inputs[3] = r2;
        Function f = new Function(function,inputs);
        String b = f.toString().replaceAll("a1.value",A1.toString()).replaceAll("a2.value",A2.toString()).replaceAll("r1.value",R1.toString()).replaceAll("r2.value",R2.toString()).replaceAll(";","");
        while(b.length()>6){
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
            b = b.replaceAll("\\(true\\)","true").replaceAll("\\(false\\)","false");
            b = b.replaceAll("!true","false").replaceAll("!false","true");
            b = b.replaceAll("\\s","");;
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
        }
        return Boolean.parseBoolean(b);
        
        }
    
    private static boolean[] verifyFunction(int function){
        return new boolean[]{
        verifyFunction(function,false,false,false,false),
        verifyFunction(function,true,false,false,false),
        verifyFunction(function,true,true,false,false),
        verifyFunction(function,false,false,false,true),
        verifyFunction(function,true,false,true,false),
        verifyFunction(function,true,true,true,false),
        verifyFunction(function,false,false,true,true),
        verifyFunction(function,true,false,true,true),
        verifyFunction(function,true,true,true,true),
        };
             
    }
   
    public static void main(String args[])throws Exception{
        boolean[][] b = new boolean[18][9];
        for(int i = 0;i < 18;i++){
            b[i] = verifyFunction(i);
        }
        boolean valid  = Arrays.deepEquals(functions,b);
        System.out.println(valid);
        
        //now special cases where there are no activators or repressors at all:
        boolean[] noinput = new boolean[18];
        for(int i = 0;i < 18;i++){
            noinput[i] = verifyFunctionWithoutActivatorsOrRepressors(i);
        }
        boolean[] proper = new boolean[]{false,false,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true,true};
        valid  = Arrays.deepEquals(functions,b);
        System.out.println(valid);
        
        
        //no activators
        for(int i = 0;i < 18;i++){ b[i] = verifyFunctionWithoutActivators(i);}
        valid = true;
        for(int i = 0;i < 18;i++){
            if(b[i][0]==false) valid = false;
            if(b[i][2]==true) valid = false;
            if((i==12)||(i==15)||(i==17)){
                if(b[i][1]==false) valid = false;
            }else{
                if(b[i][1]==true) valid = false;                
            }

        }
        System.out.println(valid);

        //no repressors
        for(int i = 0;i < 18;i++){ b[i] = verifyFunctionWithoutRepressors(i);}
        valid = true;
        for(int i = 0;i < 18;i++){
            if(b[i][0]==true) valid = false;
            if(b[i][2]==false) valid = false;
            if((i==0)||(i==2)||(i==4)){
                if(b[i][1]==true) valid = false;
            }else{
                if(b[i][1]==false) valid = false;                
            }

        }
        System.out.println(valid);
        
        
        
    }

        
    private static boolean verifyFunctionWithoutActivatorsOrRepressors(int function){
        Input[] inputs = new Input[0];

        Function f = new Function(function,inputs);
        String b = f.toString().replaceAll(";","");
        while(b.length()>6){
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
            b = b.replaceAll("\\(true\\)","true").replaceAll("\\(false\\)","false");
            b = b.replaceAll("!true","false").replaceAll("!false","true");
            b = b.replaceAll("\\s","");;
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
        }
        return Boolean.parseBoolean(b);
        
    }
    
    
    private static boolean[] verifyFunctionWithoutActivators(int f){
      return new boolean[]{verifyFunctionWithoutActivators(f,false,false),verifyFunctionWithoutActivators(f,true,false),verifyFunctionWithoutActivators(f,true,true)};
    }    
    
        
    private static boolean[] verifyFunctionWithoutRepressors(int f){
      return new boolean[]{verifyFunctionWithoutRepressors(f,false,false),verifyFunctionWithoutRepressors(f,true,false),verifyFunctionWithoutRepressors(f,true,true)};
    }    
        private static boolean verifyFunctionWithoutRepressors(int function,Boolean R1,Boolean R2){
        boolean optional = false;

        Input r1 = new Input("r1",true,optional);
        Input r2 = new Input("r2",true,optional);
        Input[] inputs = new Input[2];

        inputs[0] = r1;
        inputs[1] = r2;
        Function f = new Function(function,inputs);
        String b = f.toString().replaceAll("r1.value",R1.toString()).replaceAll("r2.value",R2.toString()).replaceAll(";","");
        while(b.length()>6){
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
            b = b.replaceAll("\\(true\\)","true").replaceAll("\\(false\\)","false");
            b = b.replaceAll("!true","false").replaceAll("!false","true");
            b = b.replaceAll("\\s","");;
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
        }
        return Boolean.parseBoolean(b);
        
    }
    
    
    
    private static boolean verifyFunctionWithoutActivators(int function,Boolean R1,Boolean R2){
        boolean optional = false;

        Input r1 = new Input("r1",false,optional);
        Input r2 = new Input("r2",false,optional);
        Input[] inputs = new Input[2];

        inputs[0] = r1;
        inputs[1] = r2;
        Function f = new Function(function,inputs);
        String b = f.toString().replaceAll("r1.value",R1.toString()).replaceAll("r2.value",R2.toString()).replaceAll(";","");
        while(b.length()>6){
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
            b = b.replaceAll("\\(true\\)","true").replaceAll("\\(false\\)","false");
            b = b.replaceAll("!true","false").replaceAll("!false","true");
            b = b.replaceAll("\\s","");;
            b= b.replaceAll("true&true","true").replaceAll("true\\|true","true").replaceAll("true&false","false").replaceAll("true\\|false","true").replaceAll("false&true","false").replaceAll("false\\|true","true")
            .replaceAll("false&false","false").replaceAll("false\\|false","false");
        }
        return Boolean.parseBoolean(b);
        
    }
    
    
    private String modelFileName;
    private String observationFileName;
    
    //added to all node identifiers to make them distinct from nusmv keywords. we want the string to be unusual, as it will cause problems if found in an identifier
    public static final String identifier = "iIi";
    
    //name of the NuSMV file created
    private String fileName;
    
    //data from model:
    private HashMap<String,Node> nodes= new HashMap<>();
    //a map of all nodes with only a single possible function to their function number
    //needed to add to resultSet, as these functions won't be gleaned from parsing counterexample
    private HashMap<String,Integer> nodesWithOneFunction = new HashMap<>(); 
    private boolean sync = true;

    //data from observation:
    //a list of macro definitions
    private String definitions;
    
    //map of Experiment names to their corresponding integral representations within NuSMV
    private HashMap<String,Integer> experiments = new HashMap<>();
    //duration of longest experiment (and add 1 for fixpoint)
    private int duration = 0;
    //number of total experiments
    private int numberOfExperiments = 0;
    
    
    //a list of the names of optional connections, used when parsing the nusmv output
    private ArrayList<String> optionalConnectionNames = new ArrayList<>();
    //list of restrictions on solutions to put into nusmv file
    private ArrayList<String> restrictions = new ArrayList<>();
    //a boolean representing if we want CTL representation. false would indicate LTL
        
    public Converter(String model,String observation)throws Exception{
        modelFileName = model;
        observationFileName = observation;
        fileName = convert();
    }
    
    public String getFileName(){return fileName;}
    
    //this creates a nusmv file from the model and observation RE:IN files, returning the file's name
    private String convert()throws Exception{
        String name = modelFileName.split("\\.")[0]+".smv";
        parseModel();
        parseObservation();
        stringToFile(generateNuSMV(),name);
        return name;
    }
    
    //parse the model file given in the constructor
    private void parseModel() throws Exception{
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
    private void createNode(String line){
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
          
    private  int[] getFunctions(String f){
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

    private void createConnection(String line){
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
          
    private void parseObservation() throws Exception{
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
    

    
    //convert macros in observation file to the NuSMV equivalent, define statements
    private String observationMacroToDefineStatement(String s){
        
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
    private int parseExperiment(String line,int expNum){
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

    private String generateNuSMV(){
        int valueNumber = experiments.size();
        StringBuilder code = new StringBuilder();

        
        //append all modules
        for(Node n:nodes.values()){
            code.append(n.getLTLParallelFlatOneHotModule(valueNumber,duration));
        }

        //add main module
        code.append("MODULE main\nVAR\n");
        for(Node n:nodes.values()){
            code.append(n.getInitialization());
        }
        
        
        //add the macros
        code.append("DEFINE\n");
        
        //since we have multiple value vars for the parallel model mode, add corresponding definitions
        for(int i = 0; i < valueNumber;i++){
            for(int step = 0;step<duration;step++){
                code.append(definitions.replace(" ","").replace(":=",step+"-"+i+":=").replace("value","value"+step+"-"+(i)).replace(".FE",".FE"+(i)).replace(".KO",".KO"+(i)));
            }
        }
                
        return code.toString();
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

    
    
    //IO helper methods
    private BufferedReader readFile(String file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF-8"));
        //get rid of BOM at beginning of file.
        in.mark(1);
        if (in.read() != 0xFEFF)
          in.reset();
        return in;
    }
        
    private void stringToFile(String s,String fileName)throws Exception{
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.print(s);
        writer.close();
        
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
}