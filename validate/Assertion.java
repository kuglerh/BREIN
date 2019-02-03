package validate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import NAE.*;
class Assertion{
    private String assertion;
    Assertion(String assertion){
        //remove comments in quotes
        String tokens[] = assertion.split("\"");
        StringBuilder line = new StringBuilder();
        for(int i=0;i<tokens.length;i++){
            //skip everything in quotes, that is all odd numbered indices
            if(i % 2 == 0){
                line.append(tokens[i]); 
            }
        }
        assertion = line.toString();
        
        //replace and or and not with proper NuSMV operators, and ten remove all spaces
        this.assertion = assertion.replace("and","&").replace("not","!").replaceAll(";|\\s+","").replace("(or)","(|)").replace("(or","(|").replace("or)","|)");
        
    }

    //evaluate this assertion
    boolean value(HashMap<String,Predicate> predicates,HashMap<String,Integer> experiments,ResultSet r){
        //find every substring of the form "#<expName>[timeStep]|=$<predicateName>"
        ArrayList<String> atoms = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<assertion.length();i++){
            if((assertion.charAt(i) == '#')&&((i<9)||(!assertion.substring(i-9,i).equals("fixpoint(")))){
                //start creating token
                //keep reading unconditionally until we encounter $
                while((i < assertion.length()) && (assertion.charAt(i) != '$')){sb.append(assertion.charAt(i));i++;}

                /*now keep reading until we encounter either:
                  1)end of assertion
                  2) &,|,!,(,),{,}
                */
                while((i < assertion.length()) &&  (!"&|!(){}".contains(Character.toString(assertion.charAt(i))))){sb.append(assertion.charAt(i));i++;}

                //now add this atomic assertion to atoms
                atoms.add(sb.toString());
                //clear the StringBuilder
                sb = new StringBuilder();
            }
        }

         //find fixpoints
        ArrayList<String> fixPoints = new ArrayList<>();
        String[] fp = assertion.split("ixpoint\\(");
        for(int i = 1;i<fp.length;i+=2){
            String token = fp[i].split("\\)")[0];
            fixPoints.add(token);
        }
        
        
        
        //now match each atom with its value, based on the predicates
        HashMap<String,Boolean> atomValues = new HashMap<>();
        for(String atom:atoms) atomValues.put(atom,evaluateAtom(atom,predicates,experiments));
        
        for(String f:fixPoints) atomValues.put("fixpoint("+f+")",evalFixpoint(f,r,experiments));

        
        //finally  replace all atoms with their proper values
        String a = assertion;
        for (Map.Entry<String, Boolean> entry : atomValues.entrySet()){
            a = a.replace(entry.getKey(),entry.getValue().toString());
        }        

        return Predicate.evaluateBooleanExpression(a);
    }
    
    private boolean evalFixpoint(String f,ResultSet r,HashMap<String,Integer> experiments){
        String[] tokens = f.split("\\[|\\]");
        int timeStep = Integer.parseInt(tokens[1]);
        int expNum = experiments.get(tokens[0].replace("#",""));
        for (Map.Entry<String, ResultSet.NodeData> entry : r.nodeVals.entrySet()){
            if(entry.getValue().values[timeStep][expNum] != entry.getValue().values[timeStep+1][expNum]) return false;
        }
        return true;
    }
    
    private boolean evaluateAtom(String atom,HashMap<String,Predicate> predicates,HashMap<String,Integer> experiments){
        String[] tokens =  atom.split("\\[|\\]");
        String expName = tokens[0].replace("#","");
        int timeStep = Integer.parseInt(tokens[1]);
        String predicate = atom.split("=")[1];
        int expNum = experiments.get(expName);
        return predicates.get(predicate).value(timeStep,expNum);
    }


    public String toString(){
        return assertion;
    }


}