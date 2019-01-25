package NAE;

import java.util.ArrayList;

//This class represents a node in the ABN(abstract boolean network)
class Node{
    //the node's name
    private String name;
    //some nodes are allowed to be knocked out
    private boolean KO;
    //some nodes are allowed to be overexpressed, called FE
    private boolean FE;
    //some nodes need at least one activator to be turned once
    private boolean needsActivator;
    //valid functions for this node
    private int[] functions;
    //the names of the nodes whose input is taken into account
    //i.e. the list of nodes that are the sources in connections where this node is the target
    private ArrayList<Input> inputs;
    //is this node part of a synchronous model
    private boolean sync;
    
    
    //the constructor takes the node's name and an array of integers where each number represents a pre-defined boolean function
    //as specified in the REIN documentation
    Node(String name,int[] functions,boolean KO,boolean FE,boolean needsActivator, boolean sync){
        this.name = name;
        this.functions = functions;
        this.KO = KO;
        this.FE = FE;
        this.needsActivator = needsActivator;
        this.sync = sync;
        inputs = new ArrayList<>();
    }
    
    //return node's name
    String getName(){
        return name;
    }
    
    //returns an array of numbers representing the predefined functions as specified in the REIN docs.
    int[] getFunctions(){
        return functions;
    }
    
    //returns a list of all inputs that are relevant to this node's functions
    Input[] getInputs(){
        return inputs.toArray(new Input[0]);
    }
    
    //adds an input to be taken into account in this nodes activation functions
    void addInput(Input input){
        inputs.add(input);
    }
    
    private String getModuleDeclaration(){
        StringBuilder module = new StringBuilder();
        module.append("MODULE node_"+name+"(main");
        //make sure order of inputs is the same as in main
        for(Input input:inputs){
            module.append(","+input.name);
        }
        module.append(")\n");
        return module.toString();
    }
    
    private String getNetworkVars(int valueNumber){
        StringBuilder module = new StringBuilder();       
        //add a VAR for every optional connection
        for(Input input:inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }       
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = valueNumber==1 ? "" : ""+i;
            if(KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        return module.toString();
    }
    
    //valueNumber is the number of value and corresponding transition vars we have in this module.
    private String getConnectionAndFunctionTransitions(){
        StringBuilder module = new StringBuilder();       
        //add a transition for every optional connection, where the connection must not change
        for(Input input:inputs){
            if(input.optional){
                module.append("next("+input.name+"_connected) := "+input.name+"_connected;\n");
            }
        }
        //don't allow function to change either once they've been set
        //if there is one transition make it a constant and don't create transition variable or assign it values
        if(functions.length != 1){
            module.append("next(transition"+") := transition"+";\n");
        } 
    
        return module.toString();
    }
    //freedomExpression is an expression that evaluates to true whenever the state allows the value and ko/fe vars to be free
    //valueNumber is the number of value and corresponding transition vars we have in this module.
    private String getStateAndValueTransitions(String freedomExpression,int valueNumber){
        StringBuilder module = new StringBuilder();               
        //if node is KO or FE, allow for transition
        for(int i=0;i<valueNumber;i++){
            String num = valueNumber==1 ? "" : ""+i;
            if(KO){
                module.append("next(KO"+num+") := case\n"+freedomExpression+" : {TRUE,FALSE};\nTRUE : KO"+num+";\nesac;\n");
            }
            if(FE){
                module.append("next(FE"+num+") := case\n"+freedomExpression+" : {TRUE,FALSE};\nTRUE : FE"+num+";\nesac\n;");
            }    
        }        
        //i plays an important role. For CTL version:
        //this variable is used to see if this state is s0 or not. In state s0, we want to allow initial values
        //of nodes to be arbitrary, so once we have fixed the connections and functions (i.e. the model), we can check for next
        //states that satisfy various experiments with differing initial configurations. Read CTL specs in nusmv file to better understand how this works
        //For LTLR version:
        //We must also allow for values and ko/fe vars to change state in between experiments
        for(int valNum = 0;valNum<valueNumber;valNum++){      
            module.append("next(value"+(valueNumber==1?"":valNum)+") := case\n"+freedomExpression+" : {TRUE,FALSE};\n");
            //if KO or FE exist, when they are true they override all other transition functions
            if(KO){
                module.append("KO"+(valueNumber==1?"":valNum)+" : FALSE;\n");
            }
            if(FE){
                module.append("FE"+(valueNumber==1?"":valNum)+" : TRUE;\n");
            }
            //transitions for all functions
            
            for(int i=0;i<functions.length;i++){
                Integer f = functions[i];
                //use function class to generate a function from the transition value and inputs
                String function = new Function(f,inputs.toArray(new Input[0])).toString();
                if(functions.length!=1){
                    module.append("transition"+" = "+f+" : "+function.replace("value","value"+(valueNumber==1?"":valNum))+"\n");
                
                }else{
                    //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                    //do this instead
                        module.append("TRUE : "+function.replace("value","value"+(valueNumber==1?"":valNum))+"\n");
                }
            }
            module.append("esac;\n");
        }
        return module.toString();
    }
    
    //return a string representing this node as a NuSMV model
    //valueNumber is the number of value and corresponding transition vars we have in this module.
    String getLTLParallelFlatDefineModule(int valueNumber,int duration){
        StringBuilder module = new StringBuilder();
        module.append(getModuleDeclaration());        
        module.append("FROZENVAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value0-"+(valueNumber==1?"":i)+" : boolean;\n");
        }
         
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = valueNumber==1 ? "" : ""+i;
            if(KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        if(functions.length != 1){
            module.append("transition"+" : {");
            for(int i=0;i<functions.length;i++){
                if(i != 0){
                    module.append(",");
                }
                module.append(functions[i]);
            }
            module.append("};\n");
        }
        //add a FROZENVAR for every optional connection
        for(Input input:inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }  
        
        module.append("DEFINE\n");
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("value"+step+"-"+(valueNumber==1?"":valNum)+" := case\n");
                //if KO or FE exist, when they are true they override all other transition functions
                if(KO){
                    module.append("KO"+(valueNumber==1?"":valNum)+" : FALSE;\n");
                }
                if(FE){
                    module.append("FE"+(valueNumber==1?"":valNum)+" : TRUE;\n");
                }
                //transitions for all functions
                
                for(int i=0;i<functions.length;i++){
                    Integer f = functions[i];
                    //use function class to generate a function from the transition value and inputs
                    String function = new Function(f,inputs.toArray(new Input[0])).toString();
                    if(functions.length!=1){
                        module.append("transition"+" = "+f+" : "+function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"\n");
                    
                    }else{
                        //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                        //do this instead
                            module.append("TRUE : "+function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"\n");
                    }
                }
                module.append("esac;\n");
            }
        }
        //if the node is synchronous, we end the transition here. Otherwise, we want to add a union with its current value
        //which essentially allows some nodes to update, some not to, giving asynchronous behavior
        
        if(sync){
            module.append("\n");
        }else{
            module.append("\nFAIRNESS\nrunning;\n");
        }
        
        return module.toString();
    }

    
    String getLTLParallelFlatModule(int valueNumber,int duration){
        StringBuilder module = new StringBuilder();
        module.append(getModuleDeclaration());        
        module.append("FROZENVAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value0-"+(valueNumber==1?"":i)+" : boolean;\n");
        }
        
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("value"+step+"-"+(valueNumber==1?"":valNum)+" : boolean;\n");
            }
        }
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = valueNumber==1 ? "" : ""+i;
            if(KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        if(functions.length != 1){
            module.append("transition"+" : {");
            for(int i=0;i<functions.length;i++){
                if(i != 0){
                    module.append(",");
                }
                module.append(functions[i]);
            }
            module.append("};\n");
        }
        //add a FROZENVAR for every optional connection
        for(Input input:inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }  
        
        module.append("ASSIGN\n");
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("init(value"+step+"-"+(valueNumber==1?"":valNum)+") := case\n");
                //if KO or FE exist, when they are true they override all other transition functions
                if(KO){
                    module.append("KO"+(valueNumber==1?"":valNum)+" : FALSE;\n");
                }
                if(FE){
                    module.append("FE"+(valueNumber==1?"":valNum)+" : TRUE;\n");
                }
                //transitions for all functions
                
                for(int i=0;i<functions.length;i++){
                    Integer f = functions[i];
                    //use function class to generate a function from the transition value and inputs
                    String function = new Function(f,inputs.toArray(new Input[0])).toString();
                    if(functions.length!=1){
                        module.append("transition"+" = "+f+" : "+function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"\n");
                    
                    }else{
                        //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                        //do this instead
                            module.append("TRUE : "+function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"\n");
                    }
                }
                module.append("esac;\n");
            }
        }

        if(sync){
            module.append("\n");
        }else{
            module.append("\nFAIRNESS\nrunning;\n");
        }
        
        return module.toString();
    }

    
    String getLTLParallelFlatDefineOneHotModule(int valueNumber,int duration){
        StringBuilder module = new StringBuilder();
        module.append(getModuleDeclaration());        
        module.append("FROZENVAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value0-"+(valueNumber==1?"":i)+" : boolean;\n");
        }
         
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = valueNumber==1 ? "" : ""+i;
            if(KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        
        //use one-hot encoding to eliminate transitional branching
        if(functions.length != 1){
            for(int i=0;i<functions.length;i++){
                module.append("transition"+functions[i]+": boolean;\n");
            }
        }
        //add a FROZENVAR for every optional connection
        for(Input input:inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }  
        
        //add init clause  to ensure one-hot encoding

        if(functions.length != 1){
            module.append("INIT\n(");
            for(int i=0;i<functions.length;i++){
                if(i!=0){
                    module.append("+");
                }
                module.append("transitionN"+functions[i]);
            }
            module.append(")=1");
        }
        
        module.append("\nDEFINE\n");
        //define terms to ensure one-hot encoding in init clause
        if(functions.length != 1){
            for(int i=0;i<functions.length;i++){
                module.append("transitionN"+functions[i]+":= "+"transition"+functions[i]+"?1:0;\n");
            }
        }
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("value"+step+"-"+(valueNumber==1?"":valNum)+" :=((\n");

                //transitions for all functions

                for(int i=0;i<functions.length;i++){
                    Integer f = functions[i];
                    //use function class to generate a function from the transition value and inputs
                    String function = new Function(f,inputs.toArray(new Input[0])).toString().replace(";","");
                    if(i!=0){
                        module.append("|");
                    }
                    if(functions.length!=1){
                        module.append("(transition"+f+" & ("+function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"))\n");
                    
                    }else{
                        //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                        //do this instead
                            module.append(function.replace("value","value"+(step-1)+"-"+(valueNumber==1?"":valNum))+"\n");
                    }
                }
                //if KO or FE exist, when they are true they override all other transition functions
                //be  cautious since and goes before or in order of operations
                if(FE){
                    module.append("|FE"+(valueNumber==1?"":valNum)+"\n");
                }
                
                module.append(")");
                if(KO){
                    module.append("&!KO"+(valueNumber==1?"":valNum)+"\n");
                }

                module.append(");\n");
            }
        }
        
        return module.toString();
    }
    
        
    String getLTLParallelFlatOneHotModule(int valueNumber,int duration){
        StringBuilder module = new StringBuilder();
        module.append(getModuleDeclaration());        
        module.append("FROZENVAR\n");
        for(int i =0;i < valueNumber;i++){
            module.append("value0-"+(i)+" : boolean;\n");
        }
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){      
                module.append("value"+step+"-"+(valNum)+" : boolean;\n");
            }
        }
        //if node can be KO or FE, append proper VARS
        for(int i=0;i<valueNumber;i++){
            String num = ""+i;
            if(KO){
                module.append("KO"+num+" : boolean;\n");
            }
            if(FE){
                module.append("FE"+num+" : boolean;\n");
            }  
        }
        
        //if there is one transition make it a constant and don't create a transition variable
        //otherwise Nusmv will convert it into a constant and this will create problems elsewhere in the program
        
        //use one-hot encoding to eliminate transitional branching
        if(functions.length != 1){
            for(int i=0;i<functions.length;i++){
                module.append("transition"+functions[i]+": boolean;\n");
            }
        }
        //add a FROZENVAR for every optional connection
        for(Input input:inputs){
            if(input.optional){
                module.append(input.name+"_connected : boolean;\n");
            }
        }  
        
        //add init clause  to ensure one-hot encoding

        if(functions.length != 1){
            module.append("INIT\n(");
            for(int i=0;i<functions.length;i++){
                if(i!=0){
                    module.append("+");
                }
                module.append("transitionN"+functions[i]);
            }
            module.append(")=1");
        }
        
        module.append("\nDEFINE\n");
        //define terms to ensure one-hot encoding in init clause
        if(functions.length != 1){
            for(int i=0;i<functions.length;i++){
                module.append("transitionN"+functions[i]+":= "+"transition"+functions[i]+"?1:0;\n");
            }
        }
        module.append("ASSIGN\n");
        for(int step = 1;step < duration;step++){            
            for(int valNum = 0;valNum<valueNumber;valNum++){ 
                String async = sync ? "" : "value"+(step-1)+"-"+(valNum)+" union ";
                module.append("init(value"+step+"-"+(valNum)+") :="+async+"((\n");

                //transitions for all functions

                for(int i=0;i<functions.length;i++){
                    Integer f = functions[i];
                    //use function class to generate a function from the transition value and inputs
                    String function = new Function(f,inputs.toArray(new Input[0])).toString().replace(";","");
                    if(i!=0){
                        module.append("|");
                    }
                    if(functions.length!=1){
                        module.append("(transition"+f+" & ("+function.replace("value","value"+(step-1)+"-"+(valNum))+"))\n");
                    
                    }else{
                        //if there is only one function, transition would be a constant, and nusmv would give us problems, so we 
                        //do this instead
                            module.append(function.replace("value","value"+(step-1)+"-"+(valNum))+"\n");
                    }
                }
                //if KO or FE exist, when they are true they override all other transition functions
                //be  cautious since and goes before or in order of operations
                if(FE){
                    module.append("|FE"+(valNum)+"\n");
                }
                
                module.append(")");
                if(KO){
                    module.append("&!KO"+(valNum)+"\n");
                }

                module.append(");\n");
            }
        }
        
        return module.toString();
    }
    
    
    
    //return a string representing how to declare this node as a module in the main module of the nusmv  program
    String getInitialization(){
        StringBuilder init =  new StringBuilder(name+" : node_"+name+"(self");
        for(Input input:inputs){
            init.append(","+input.name);
        }    
        init.append(");\n");
        return init.toString();
    }
    
}