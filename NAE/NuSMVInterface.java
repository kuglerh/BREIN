package NAE;
import java.io.*;
import java.util.*;
public class NuSMVInterface{
    private Process pr;
    private Scanner in;
    private PrintWriter out;
    private Converter c;
    
    //takes a path to an smv file and a converter which dictates  how to perform verification
    public NuSMVInterface(String path,Converter c)throws Exception{
        //get NuSMV up and running                
        ProcessBuilder b = new ProcessBuilder(new String[]{"NuSMV","-int",path});
        b.redirectErrorStream(true);
        pr = b.start();
        in = new Scanner(pr.getInputStream());
        out = new PrintWriter(pr.getOutputStream());
        this.c = c;
        
        readPrompt();
        if(c.getSpecType().toString().toLowerCase().contains("bmc"))
            out.println("go_bmc");
        else
            out.println("go");
        out.flush();
        readPrompt();
    }
    
    public void close(){
        out.println("quit");
    }
        
    public String check(String spec)throws Exception{
        switch(c.getSpecType()){
            case LTL_BMC_0: return checkLTL_BMC_0(spec);
            case LTL_BMC: return checkLTL_BMC(spec);
            case LTL_BDD: return checkLTL_BDD(spec);
            case CTL: return checkCTL(spec);
        }
        return null;
    }
    
    private String checkLTL_BMC_0(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\n", "").replace("@"," ");
        //execute command
        out.println("check_ltlspec_bmc -k 0 -l X  -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
    

    private String checkLTL_BMC(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\n", "").replace("@"," ");
        //get bound
        int duration = c.getDuration()+1;
        //execute command
        out.println("check_ltlspec_bmc_inc -k "+duration+" -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
    

    private String checkLTL_BDD(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\n", "").replace("@"," ");
        //execute command
        out.println("check_ltlspec -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
        
    private String checkCTL(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\s+","").replace("@"," ");
        //execute command
        out.println("check_ctlspec -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
    
    private String readPrompt(){
        StringBuilder ret = new StringBuilder();
        String l;
        while((l=in.next())!=null){
            if (l.startsWith("NuSMV"))  {in.next();break;}//the call to next() reads past the ">" character
            ret.append(l+in.nextLine()+"\n");
        }
        return ret.toString();
    }
    
}