package NAE;

//This class represents an input that a node takes, consisting of the name of the node at the giving end of this connection, as well as if this connection is 
//positive or negative, that is an activator or repressor, as well as if the connection is optional or not in the ABN
class Input{
    final boolean optional;
    final boolean isPositive;
    //the name of the node
    final String name;
    
    Input(String name,boolean isPositive,boolean optional){
        this.name = name;
        this.isPositive = isPositive;
        this.optional = optional;
    }

}