package NAE;
import java.io.*;
import java.util.*;
public class NuSMVInterface{
    private Process pr;
    private Scanner in;
    private PrintWriter out;
    boolean BMC;
    
    public NuSMVInterface(String path,boolean BMC)throws Exception{
        //get NuSMV up and running                
        ProcessBuilder b = new ProcessBuilder(new String[]{"nusmv","-int",path});
        b.redirectErrorStream(true);
        pr = b.start();
        in = new Scanner(pr.getInputStream());
        out = new PrintWriter(pr.getOutputStream());
        this.BMC = BMC;
        
        readPrompt();
        if(BMC)
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
        return BMC ? checkLTL(spec) : checkCTL(spec);
    }
    
    private String checkLTL(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\n", "").replace("@"," ");
        //execute command
        out.println("check_ltlspec_bmc_inc -k 20  -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
    
    private String checkINVARBMC(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\n", "").replace("@"," ");

        //execute command
        out.println("check_invar_bmc -a een-sorensson -p \""+spec+"\"");
        out.flush();
        String s = readPrompt();
        return s;
    }
    
    
   
    private String checkCTL(String spec)throws Exception{
        //remove newlines
        spec = spec.replaceAll("\\s+","").replace("@"," ");
        //execute command
        out.println("check_ltlspec -p \""+spec+"\"");
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
        System.out.println(ret.toString());
        return ret.toString();
    }
    
}