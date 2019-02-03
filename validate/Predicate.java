package validate;
import java.util.Map;
import NAE.*;

class Predicate{

    private ResultSet resultSet;
    private String predicate;
    private String name;
    Predicate(ResultSet r,String predicate){
        predicate = predicate.replace("and","&").replaceAll(";|\\}|\\{|\\s+","").replace("(or)","(|)").replace("(or","(|").replace("or)","|)");
        String[] tokens = predicate.split(":=");
        if(tokens.length!=2) throw new IllegalArgumentException("Error: Unexpected syntax in observation file predicates");
        this.name = tokens[0];
        this.predicate = tokens[1].replace("=",Converter.identifier+"=");
        this.resultSet = r;
    }   

    
    boolean value(int timeStep,int expNum){
        String p = predicate;
        for (Map.Entry<String, ResultSet.NodeData> entry : resultSet.nodeVals.entrySet()){
            //replace every node with its value at this time step for this experiment
            p = p.replace(entry.getKey(),entry.getValue().values[timeStep][expNum] ? "1":"0");
            
            //replace eKO/FE
            if(entry.getValue().FE[expNum] != null){
                p = p.replace("FE("+entry.getKey().substring(0,entry.getKey().length() - Converter.identifier.length())+")"+Converter.identifier,entry.getValue().FE[expNum]? "1":"0");
            }
            if(entry.getValue().KO[expNum] != null){
                p = p.replace("KO("+entry.getKey().substring(0,entry.getKey().length() - Converter.identifier.length())+")"+Converter.identifier,entry.getValue().KO[expNum]? "1":"0");
            }
        }
        p = p.replaceAll("1=1|0=0", "true");
        p = p.replaceAll("1=0|0=1","false");
        return evaluateBooleanExpression(p);
    }
    
    String getName(){return name;}       
    //assumes expression has no spaces and only contains the following literals: 'true','false','and','or','not','(',')'
    //also assumes parens are balanced
    static boolean evaluateBooleanExpression(String exp){
        //order of precedence: 1)not 2)and 3)or
        
        //loop until exp is atomic
        while(!(exp.equals("true") || exp.equals("false"))){
            //first remove parens from atomic expressions
            exp = exp.replace("(true)","true").replace("(false)","false");
            
            String reduced = exp.replace("!true","false").replace("!false","true");
            if(!exp.equals(reduced)){exp = reduced;continue;}
        
            reduced = exp.replace("true&true","true").replace("false&true","false").replace("false&false","false").replace("true&false","false");
            if(!exp.equals(reduced)){exp = reduced;continue;}
        
            reduced = exp.replace("true|true","true").replace("false|true","true").replace("false|false","false").replace("true|false","true");
            if(!exp.equals(reduced)){exp = reduced;continue;}
            
        }
        return Boolean.parseBoolean(exp);
    }
}