package NAE;

public class ConverterFactory{
    
    public static Converter getConverter(String modelFileName,String observationFileName,String type)throws Exception{
        switch (type.toLowerCase()){
            case "time_step": return new TimeStep(modelFileName,observationFileName);
            case "ctl": return new CTL(modelFileName,observationFileName);
            case "temporal_logic": return new TemporalLogic(modelFileName,observationFileName);
            default: throw new IllegalArgumentException("Error: unknown Converter type "+type);
        }
    }
    
}