package NAE;
import verify.*;
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
    
    //an analysis can be run from the cmd-line
    public static void main(String args[])throws Exception{
        
        int solutionLimit = Integer.parseInt(args[0]);        
        NAE nae = new NAE(args[1],args[2],solutionLimit);
        nae.runAnalysisInteractive();
        nae.printResults();
        //nae.ver(args[1],args[2]);
    }
    
    //the arguments are the names of the files to analyze
    public NAE(String modelFileName,String observationFileName,int solutionLimit)throws Exception{
        this.solutionLimit = solutionLimit;
        converter = new CTL(modelFileName,observationFileName);
        resultSets = new ArrayList<>();
    }
    
    void ver(String s1,String s2)throws Exception{
        for(ResultSet r:resultSets){
            Verify v =  new Verify(s1,s2,r);
            System.out.println(v.v(converter.getNumberOfExperiments(),converter.getDuration(),converter.getExperimentToNumberMap()));
        }
    }
    
    //new version of run analysis that uses interactive mode
    public void runAnalysisInteractive()throws Exception{
        String nusmvFile = converter.getFileName();

        //create an interface for running nusmv
        NuSMVInterface in = new NuSMVInterface(nusmvFile,false);
        
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