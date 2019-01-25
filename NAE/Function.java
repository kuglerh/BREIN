package NAE;

import java.util.ArrayList;

class Function{
    int type;
    Input[] positiveFunctions;
    Input[] negativeFunctions;
    String functionString = ""; 
    //support for now functions 0-17
    
    
    //create a function of specified type and specified inputs
    Function(int type,Input[] inputs){
        this.type = type;
        //divide inputs into negative and positive
        ArrayList<Input> positive= new ArrayList<>();
        ArrayList<Input> negative= new ArrayList<>();
        for(Input input:inputs){
            if(input.isPositive){
                positive.add(input);
            }else{
                negative.add(input);
            }
        }
        this.positiveFunctions = positive.toArray(new Input[0]);
        this.negativeFunctions = negative.toArray(new Input[0]);
        
        this.functionString = getFunctionWithoutBranching(type,this.positiveFunctions,this.negativeFunctions);
    }
    
    //get this function representation for NuSMV
    @Override
    public String toString() {
        return functionString;
    }
    
    
    //the following methods convert the specified function number into a string, given its Inputs
    private String zero(Input[] positive,Input[] negative){
        //active only when all activators and no repressors are active
        //conjunction of all activators and conjunction of the negation of all repressors
        //with DeMorgan's law that is the same as conjunction of all activators and negation of disjunction of all repressors.
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        
        if(hasActivators && hasRepressors){
            return conjuntionOfActivators(positive,negative) +"& !"+ disjuntionOfRepressors(positive,negative)+";";
        }else if(hasActivators){
            return  conjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }
    
    private String one(Input[] positive,Input[] negative){
        //active when any activator is active and no repressor is active
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        
        if(hasActivators && hasRepressors){
            return  disjuntionOfActivators(positive,negative) + "& !" + disjuntionOfRepressors(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }
    
    private String two(Input[] positive,Input[] negative){
        //all activators, not all repressors 
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
          
        if(hasActivators && hasRepressors){
            return  conjuntionOfActivators(positive,negative) + "& !" + conjuntionOfRepressors(positive,negative) +";";
        }else if(hasActivators){
            return  conjuntionOfActivators(positive,negative)+";";            
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";  
        }else{
            return  "FALSE;";
        }
    }
    
    private String three(Input[] positive,Input[] negative){
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
                
        if(hasActivators && hasRepressors){
            return  "("+disjuntionOfActivators(positive,negative) +"& !"+disjuntionOfRepressors(positive,negative)+")|("+conjuntionOfActivators(positive,negative)+"&!"+conjuntionOfRepressors(positive,negative) +");";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }        
    }
    
    private String four(Input[] positive,Input[] negative){
        //all activators, ignore repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
         
        if(hasActivators && hasRepressors){
            return  conjuntionOfActivators(positive,negative) +";";
        }else if(hasActivators){
            return  conjuntionOfActivators(positive,negative)+";";            
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }

    private String five(Input[] positive,Input[] negative){
        //all activators or some activators and no repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
         
        if(hasActivators && hasRepressors){
            return  "(" + disjuntionOfActivators(positive,negative) + "& !" + disjuntionOfRepressors(positive,negative) + ") | "+conjuntionOfActivators(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }
 
    private String six(Input[] positive,Input[] negative){
        //any activators and not all repressors.
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
         
        if(hasActivators && hasRepressors){
            return  disjuntionOfActivators(positive,negative) + "& !" + conjuntionOfRepressors(positive,negative) + ";"; 
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }
   
    private String seven(Input[] positive,Input[] negative){
        //any activator not all repressors, or all activators and all repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
         
        if(hasActivators && hasRepressors){
            return  "("+disjuntionOfActivators(positive,negative) + "& !" + conjuntionOfRepressors(positive,negative) +")|("+conjuntionOfActivators(positive,negative)+"&"+conjuntionOfRepressors(positive,negative) +");";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }
   
    private String eight(Input[] positive,Input[] negative){
        //any activator, ignore repressors.
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
         
        if(hasActivators && hasRepressors){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return  "FALSE;";
        }
    }

    private String nine(Input[] positive, Input[] negative){
        //this function is off as long as there are any repressors, otherwise one
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!" + disjuntionOfRepressors(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }
    }
        
    private String ten(Input[] positive, Input[] negative){
        //no repressors, or all activators and not all repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "(!" + disjuntionOfRepressors(positive,negative)+") | ("+conjuntionOfActivators(positive,negative)+"& !"+conjuntionOfRepressors(positive,negative)+");" ;
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    }    
      
    private String eleven(Input[] positive, Input[] negative){
        //no repressors, or some activators and not all repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "(!" + disjuntionOfRepressors(positive,negative)+") | ("+disjuntionOfActivators(positive,negative)+"& !"+conjuntionOfRepressors(positive,negative)+");" ;
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    }   
    
    private String twelve(Input[] positive, Input[] negative){
        //not all repressors
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!" + conjuntionOfRepressors(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            //not all repressors
            return "!"+ conjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    }   
    
    private String thirteen(Input[] positive, Input[] negative){
        //no repressors or all activators
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!"+disjuntionOfRepressors(positive,negative) +"|"+ conjuntionOfActivators(positive,negative)+";";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    }  
    
    private String fourteen(Input[] positive, Input[] negative){
        //can't have more repressors than activators
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return thirteen(positive,negative)+"|("+disjuntionOfActivators(positive,negative)+"&!"+conjuntionOfRepressors(positive,negative)+");"; 
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    } 
    
    private String fiveteen(Input[] positive, Input[] negative){
        //cant be the case that all repressors and not every activators
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!("+conjuntionOfRepressors(positive,negative)+"&!"+conjuntionOfActivators(positive,negative)+");";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return "!"+ conjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    } 
    
    private String sixteen(Input[] positive, Input[] negative){
        //can't be the case that no activators and any repressors 
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!(!"+disjuntionOfActivators(positive,negative)+"&"+disjuntionOfRepressors(positive,negative)+");";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return  "!" + disjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    } 
   
    private String seventeen(Input[] positive, Input[] negative){
        //can't be the case all repressors and no activators
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        if(hasActivators && hasRepressors){
            return "!("+conjuntionOfRepressors(positive,negative)+"&!"+disjuntionOfActivators(positive,negative)+");";
        }else if(hasActivators){
            return  disjuntionOfActivators(positive,negative)+";";
        }else if (hasRepressors){
            return "!"+ conjuntionOfRepressors(positive,negative)+";";
        }else{
            return "TRUE;";
        }         
    } 
   
   
    
    //helper methods
    private String conjuntionOfActivators(Input[] positive,Input[] negative){
        StringBuilder activators = new StringBuilder("(");
        for(int i = 0;i<positive.length;i++){
            if(i!=0){
                activators.append("&");
            }
            if(positive[i].optional){
                activators.append("("+positive[i].name+".value | ! "+positive[i].name+"_connected)");
            }else{
                activators.append(positive[i].name+".value");
            }
        }
        activators.append(")");
        return activators.toString();
    }

    private String disjuntionOfActivators(Input[] positive,Input[] negative){
        StringBuilder activators = new StringBuilder("(");
        for(int i = 0;i<positive.length;i++){
            if(i!=0){
                activators.append("|");
            }
            if(positive[i].optional){
                activators.append("("+positive[i].name+".value & "+positive[i].name+"_connected)");  
            }else{
                activators.append(positive[i].name+".value");
            }
        }
        activators.append(")");
        return activators.toString();
    }

    private String conjuntionOfRepressors(Input[] positive,Input[] negative){
        StringBuilder repressors = new StringBuilder("(");
        for(int i = 0;i<negative.length;i++){
           if(i!=0){
               repressors.append("&");
           }
           if(negative[i].optional){
                repressors.append("("+negative[i].name+".value | ! "+negative[i].name+"_connected)");
           }else{
                repressors.append(negative[i].name+".value");
           }
        }
        repressors.append(")");
        return repressors.toString();
    }

    private String disjuntionOfRepressors(Input[] positive,Input[] negative){
        StringBuilder repressors = new StringBuilder("(");
        for(int i = 0;i<negative.length;i++){
            if(i!=0){
                repressors.append("|");
            }
            if(negative[i].optional){
                repressors.append("("+negative[i].name+".value & "+negative[i].name+"_connected)");  
            }else{
                repressors.append(negative[i].name+".value");
            }
        }
        repressors.append(")");
        return repressors.toString();
    }
    
    private String getFunction(int function,Input[] positive,Input[] negative){       
        switch(function){
            case 0:
                return zero(positive,negative);
            case 1:
                return one(positive,negative);
            case 2:
                return two(positive,negative);
            case 3:
                return three(positive,negative);
            case 4:
                return four(positive,negative);
            case 5:
                return five(positive,negative);
            case 6:
                return six(positive,negative);
            case 7:
                return seven(positive,negative);
            case 8:
                return eight(positive,negative);
            case 9:
                return nine(positive,negative);
            case 10:
                return ten(positive,negative);
            case 11:
                return eleven(positive,negative);
            case 12:
                return twelve(positive,negative);
            case 13:
                return thirteen(positive,negative);
            case 14:
                return fourteen(positive,negative);
            case 15:
                return fiveteen(positive,negative);
            case 16:
                return sixteen(positive,negative);
            case 17:
                return seventeen(positive,negative);
        }
        return null;
    }
    
    
    //if all activators are optional, AND all are not connected, we need to branch to a different version of 
    //the function, as the boolean trick we use to avoid branching breaks down when there is not a single mandatory activator.
    //The same logic applies for the repressors.
    public String getFunctionWithBranching(int type,Input[] positive,Input[] negative){
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        String functionWithActivatorsAndRepressors = getFunction(type,positive,negative).replaceAll(";","");
        boolean mandatoryActivator = false;
        boolean mandatoryRepressor = false;
        //see if we have any mandatory activators
        for(Input input:positive){ if(!input.optional) mandatoryActivator = true;}
        for(Input input:negative){ if(!input.optional) mandatoryRepressor = true;}
        
        //if both are true, return. Also return if there are no repressors at all or no activators at all
        if((mandatoryActivator || !hasActivators) && (mandatoryRepressor || !hasRepressors)){
            return functionWithActivatorsAndRepressors+";";
        }

        //otherwise we define three more functions
        String functionWithActivatorsOnly = getFunction(type,positive,new Input[0]).replaceAll(";","");
        String functionWithRepressorsOnly = getFunction(type,new Input[0],negative).replaceAll(";","");
        String functionWithoutActivatorsAndRepressors = getFunction(type,new Input[0],new Input[0]).replaceAll(";","");

        //we also need the branching conditions
        //This is when all activators are not connected. ie !(A|B|C)
        String noActivators = "!(";
        for(int i = 0;i< positive.length;i++){
            if(i!=0) noActivators+="|";
            if(positive[i].optional) noActivators += positive[i].name + "_connected";
        }
        noActivators+=")";
        //This is when all repressors are not connected. ie !(A|B|C)
        String noRepressors = "!(";
        for(int i = 0;i< negative.length;i++){
            if(i!=0) noRepressors+="|";
            if(negative[i].optional) noRepressors += negative[i].name + "_connected";
        }    
        noRepressors+=")";
        //now we explore all three possibilities
        if((!mandatoryActivator && hasActivators) && (!mandatoryRepressor && hasRepressors)){
            //this is the one case of a double branch
            return noRepressors+"? ("+noActivators+"?"+functionWithoutActivatorsAndRepressors+":"+functionWithActivatorsOnly+") : ("+noActivators+"?"+functionWithRepressorsOnly+":"+functionWithActivatorsAndRepressors+");";
        }
        
        if(!mandatoryActivator && hasActivators){
            return noActivators+"?"+functionWithRepressorsOnly+":"+functionWithActivatorsAndRepressors+";";
        }
        if(!mandatoryRepressor && hasRepressors){
            return noRepressors+"?"+functionWithActivatorsOnly+":"+functionWithActivatorsAndRepressors+";";
        }
        return null;
    }
    
    
    //using boolean logic to avoid any branching
    public String getFunctionWithoutBranching(int type,Input[] positive,Input[] negative){
        boolean hasActivators = positive.length > 0;
        boolean hasRepressors = negative.length > 0;
        String functionWithActivatorsAndRepressors = "("+getFunction(type,positive,negative).replaceAll(";","")+")";
        boolean mandatoryActivator = false;
        boolean mandatoryRepressor = false;
        //see if we have any mandatory activators
        for(Input input:positive){ if(!input.optional) mandatoryActivator = true;}
        for(Input input:negative){ if(!input.optional) mandatoryRepressor = true;}
        
        //if both are true, return. Also return if there are no repressors at all or no activators at all
        if((mandatoryActivator || !hasActivators) && (mandatoryRepressor || !hasRepressors)){
            return functionWithActivatorsAndRepressors+";";
        }

        //otherwise we define three more functions
        String functionWithActivatorsOnly = "("+getFunction(type,positive,new Input[0]).replaceAll(";","")+")";
        String functionWithRepressorsOnly = "("+getFunction(type,new Input[0],negative).replaceAll(";","")+")";
        String functionWithoutActivatorsAndRepressors = "("+getFunction(type,new Input[0],new Input[0]).replaceAll(";","")+")";

        //we also need the branching conditions
        //This is when all activators are not connected. ie !(A|B|C)
        String noActivators = "!(";
        for(int i = 0;i< positive.length;i++){
            if(i!=0) noActivators+="|";
            if(positive[i].optional) noActivators += positive[i].name + "_connected";
        }
        noActivators+=")";
        
        //This is when all repressors are not connected. ie !(A|B|C)
        String noRepressors = "!(";
        for(int i = 0;i< negative.length;i++){
            if(i!=0) noRepressors+="|";
            if(negative[i].optional) noRepressors += negative[i].name + "_connected";
        }    
        noRepressors+=")";

        //now we explore all three possibilities
        if((!mandatoryActivator && hasActivators) && (!mandatoryRepressor && hasRepressors)){
            //this is the one case of a double branch
            return "("+noRepressors+"& ( ("+noActivators+"&"+functionWithoutActivatorsAndRepressors+") | (!"+noActivators+"&"+functionWithActivatorsOnly+") ) ) | (!"+noRepressors+"& ( ("+noActivators+"&"+functionWithRepressorsOnly+") | (!"+noActivators+"&"+functionWithActivatorsAndRepressors+") ) );";
        }
        
        if(!mandatoryActivator && hasActivators){
            return "("+noActivators+"&"+functionWithRepressorsOnly+") | (!"+noActivators+"&"+functionWithActivatorsAndRepressors+");";
        }
        if(!mandatoryRepressor && hasRepressors){
            return "("+noRepressors+"&"+functionWithActivatorsOnly+") | (!"+noRepressors+"&"+functionWithActivatorsAndRepressors+");";
        }
        return null;
    }
    
}