package validate;

import NAE.*;
import java.util.*;

class Observation{
    final ArrayList<String> predicateStrings;
    final ArrayList<String> assertionStrings;
    
    Observation(ArrayList<String> predicates,ArrayList<String> assertions){
        this.predicateStrings = predicates;
        this.assertionStrings = assertions;
    }
    
    
    boolean validate(ResultSet r,HashMap<String,Integer> experiments){
        HashMap<String,Predicate> predicates = new HashMap<>();
        for(String s:predicateStrings){
            Predicate p = new Predicate(r,s);
            predicates.put(p.getName(),p);
        }

        //create a list of assertions
        ArrayList<Assertion> assertions = new ArrayList<>();
        for(String a:assertionStrings){
            assertions.add(new Assertion(a));
        }

        //make sure they are all true
        for(Assertion a:assertions){
            if(!a.value(predicates,experiments,r)) {System.out.println(a);return false;}
        }
        return true;
    }

}


