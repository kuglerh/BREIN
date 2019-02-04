package NAE;
import validate.*;
import java.io.*;
import java.util.*;
//NAE stands for network analysis engine
//This is the main class of NAE
public class NAE{

    //converter object used to parse model and observation files and convert them into NuSMV
    private Converter converter;
    private int solutionLimit;
    //a list of all ResultSets found, each one corresponding to one solution.
    private ArrayList<ResultSet> resultSets;
    
    //a string to help the user
    private static final String helpString = "";
    
    private static final int defaultBMCLength = 20;
    
    //an analysis can be run from the cmd-line
    public static void main(String args[])throws Exception{
        /*here is the format:
          solutionLimit model spec mode 
          modes are:
          1) time_step - can only run on .spec files
          2) temporal_logic_bmc
          3) temporal_logic_bdd
          4) ctl - currently under development
          
          if mode is temporal_logic_bmc, asumed bmc length is 20
          
          optional args: -bmc <length> - set bmc length
          -v - perform validation
        */
        if(args.length<4){
            System.out.println(helpString);
            System.exit(0);
        }
        
        //parse args
        int solutionLimit = Integer.parseInt(args[0]);   
        String model = args[1];
        String spec = args[2];
        String mode = args[3].toLowerCase();
        boolean bmc = true;
        boolean temporalLogicMode = false;
        int bmc_length = defaultBMCLength;
        boolean validate = false;

        for(int i =0;i<args.length-1;i++){
            if(args[i].equals("-bmc")) {
                                                 
                bmc_length = Integer.parseInt(args[i+1]);

                if(!mode.equals("temporal_logic_bmc")){
                    System.out.println("BMC length can only be set in temporal_logic_bmc mode");
                    System.exit(0);
                }
                if(!spec.contains(".ltlspec")){
                    System.out.println("BMC length can only be set for .ltlspec files");
                    System.exit(0);                
                }
            }
        }
            
        
        
        
        if(mode.equals("temporal_logic_bdd")){
            mode = "temporal_logic";
            bmc = false;
            temporalLogicMode = true;
        }
        if(mode.equals("temporal_logic_bmc")){
            mode = "temporal_logic";
            temporalLogicMode = true;
        }
        if(mode.equals("ctl")){
            bmc = false;
        }
        for(String a:args) {if (a.equals("-v")) validate = true;}
        

        
        //see if combos  are legal and advisable
        if(validate && !mode.equals("time_step")){
            System.out.println("Currently, validation is only supported in time_step mode");
            System.exit(0);
        }
        
        if(spec.contains(".ctlspec") && bmc){
            System.out.println("NusMV does not support Bounded Model Checking with CTL");
            System.exit(0);
        }
        
        if(!temporalLogicMode && (spec.contains(".ltlspec")||spec.contains(".ctlspec"))){
            System.out.println(".ltlspec and .ctlspec files only supported in temporal_logic modes");
        }

        if(!mode.equals("time_step") && spec.contains(".spec")){
            System.out.println("Please note that time_step mode is optimized for .spec files");
            System.out.println("The other modes are not optimized for this format, and don't support boolean expressions as specifications");
            System.out.println("Spec files that contain such boolean expressions will not be parsed properly in this mode");
        }
        
        if(mode.equals("ctl")){
            System.out.println("Please note that ctl mode is under development");
            System.out.println("for .ctlspec files use temporal_logic_bdd mode");
        }
        Converter c = null;
        try{
            c = ConverterFactory.getConverter(model,spec,mode);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        }                        

        if( args[3].toLowerCase().equals("temporal_logic_bmc")){
            if(spec.contains(".ltlspec")){
                c.setDuration(bmc_length);
            }
        }
        //finally run NAE!
        NAE nae = new NAE(c,solutionLimit);
        nae.runAnalysisInteractive();
        nae.printResults();
        if(validate) nae.validate(model,spec);
    }
    
    //the arguments are the names of the files to analyze
    public NAE(Converter c,int solutionLimit){
        this.solutionLimit = solutionLimit;
        converter = c;
        resultSets = new ArrayList<>();
    }
    
    //validate the given files against the resultSets
    void validate(String model,String spec)throws Exception{
        for(ResultSet r:resultSets){
            Validate v =  new Validate(model,spec,r);
            System.out.println(v.validate(converter.getNumberOfExperiments(),converter.getDuration(),converter.getExperimentToNumberMap()));
        }
    }
    
    //new version of run analysis that uses interactive mode
    public void runAnalysisInteractive()throws Exception{
        String nusmvFile = converter.getFileName();

        //create an interface for running nusmv
        NuSMVInterface in = new NuSMVInterface(nusmvFile,converter);
        
        //result loop
        while(true){
            if(resultSets.size() >= solutionLimit){
                break;
            }
            //get updated spec, including restricting other solutions
            String spec = converter.getSpec();         

            ResultSet resultSet = converter.parseAnswer(new BufferedReader(new StringReader(in.check(spec))));
            if(resultSet == null){
                //no answer was found
                break;
            }
            resultSets.add(resultSet);
            
            
       
            converter.restrictResult(resultSet);
            
            
        }
    }
      
  
    //prints out the results in a way where only optional connections are visible
    public void printResults(){
        //print no solutions if there are none
        if(resultSets.isEmpty()){
            System.out.println("No Solutions Found");
            return;
        }
        
        StringBuilder out = new StringBuilder("                   ");
        String[] connections = converter.getOptionalConnectionNames();
        for(int i = 0;i<resultSets.size();i++){
            out.append(i+" "+(i>9 ? "":" "));
        }
        out.append("\n");
        
        
        for(int i =0;i<connections.length;i++){
            out.append(String.format("%1$17s :", connectionNameToPrintVersion(connections[i])));
            for(int j = 0;j<resultSets.size();j++){              
                out.append(resultSets.get(j).optionalConnections.get(connections[i])?"\u25A0  ":"   ");
            }
            out.append("\n");
        }
        System.out.println(out.toString());
    }    

    //converts a string of form X.B to B->X for use in printResults
    private static String connectionNameToPrintVersion(String n){
        String[] nodes = n.split("\\.");
        //replace occurences of identifier, a string defined in Converter that is appended to nodes to make them unique from NuSMV keywords 
        return (nodes[1]+"->"+nodes[0]).replace(Converter.identifier,"");
    }


    
}