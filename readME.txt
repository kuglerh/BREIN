note- ASYNC only works now in CTL mode
note-five modes are supported via command-line arguments, but certain parameters which i would later like to expose to the user are now hard-coded in.
SAT based model checking has been tested using miniSat

Building NAE:
navigate to the directory containng this readME. Type:
jar cvfm NAE.jar NAE\manifest.txt NAE\*.class

Running NAE:
navigate to the same directory as before and type:
java -jar NAE.jar  <mode> <solution limit> <model file path> <observation file path> <optional NuSMV args> 

mode can be: CTL LTLP LTLR LTLPF LTLRF

optional NuSMV args are any arguments to pass to NuSMV

models I've tested on are:
1)pluripotencty model
run using:
java -jar NAE.jar  LTLPF 1 pluripotency_model\model.net pluripotency_model\observation.spec -bmc -bmc_length 0

2)minimal_pl
run using:
java -jar NAE.jar  ltlpf 1 minimal_pl\model.net minimal_pl\observations.spec -bmc -bmc_length 0

3)myloid model
run using: 
java -jar NAE.jar  ctl 20 myloid_model\model.net myloid_model\observations.spec
---modified to make three connections optional

4)toy model
run using:
java -jar NAE.jar  ctl 64 toy_model\model.net toy_model\observations.spec

5)toy model using ltl and bmc
java -jar NAE.jar  ltlp 64 toy_model\model.net toy_model\observations.spec -bmc -bmc_length 21

