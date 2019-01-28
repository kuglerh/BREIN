package validate;

import java.util.ArrayList;

class AbstractNode{
    String name;
    int[] functions;
    ArrayList<Input> inputs;
    boolean KOallowed;
    boolean FEallowed;
    
    AbstractNode(String name,int[] functions,boolean ko,boolean fe){
        this.KOallowed = ko;
        this.FEallowed = fe;
        this.name=name;
        this.functions = functions;
        inputs = new ArrayList<>();
    }
    
    void addInput(Input i){
        inputs.add(i);
    }

}