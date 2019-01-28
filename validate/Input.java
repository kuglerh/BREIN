package validate;

class Input{
    AbstractNode n;
    boolean isPositive;
    boolean optional;
    
    Input(AbstractNode n,boolean isPositive,boolean optional){
        this.n = n;
        this.isPositive = isPositive;
        this.optional = optional;
    }
    
}