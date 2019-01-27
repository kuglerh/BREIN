package NAE;
import java.util.Map;

public class ResultSet{
    public Map<String,NodeData> nodeVals;
    public Map<String,Boolean> optionalConnections;
    
    
    ResultSet(Map<String,NodeData> nodeVals,Map<String,Boolean> optionalConnections){
        this.nodeVals = nodeVals;
        this.optionalConnections = optionalConnections;
    }
    
    public static class NodeData{
        public boolean[][] values;
        public Integer function;
        public Boolean[] KO;
        public Boolean[] FE;
        NodeData(int duration,int numExp){
            this.values = new boolean[duration][numExp];
            KO = new Boolean[numExp];
            FE = new Boolean[numExp];
        }        
    }
    
    
}




