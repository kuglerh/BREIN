requires Java and NuSMV to be on the path


Building NAE:
navigate to the directory containng this readME. Type:
javac NAE\*.java validate\*.java
jar cvfm NAE.jar NAE\manifest.txt NAE\*.class validate\*.class

This creates a file named "NAE.jar"

Running NAE:
navigate to the same directory as before and type:
java -jar NAE.jar  <solution limit> <model file path> <observation file path> <mode>

<Solution limit> is the number of solutions to search for
<model file path> is a path to a model file ending in .net
<observation file path> is a path to a spec file ending in .spec or .cltspec or .ltlspec
<mode> is the mode of encoding in NuSMV. Current modes are:
    time_step for use with .spec files, 
    temporal_logic_bmc for use with .ctlspec and .ltlspec files to run the SAT solver with bounded model checking
    temporal_logic_bdd for use with .ctlspec and .ltlspec files to use the BDD algorithms for verification
    ctl currently under development, but theoritcally more optomized for .ctlspec files

additionally there are optional arguments, which, if specified, must be after the 4 required arguments. They are:
-v to perform validation on solutions 
-bmc <length> to specify the bounds of bmc. Default is 20.

Specific details on running the test models:

models under the directory testModels are valid RE:IN models that NAE can run under time_step mode. To run them:

java -jar NAE.jar 100 TestModels\toy_model\model.net TestModels\toy_model\observations.spec time_step
java -jar NAE.jar 1 TestModels\minimal_pl\model.net TestModels\minimal_pl\observations.spec time_step
java -jar NAE.jar 1 TestModels\pluripotency_model\model.net TestModels\pluripotency_model\observation.spec time_step
java -jar NAE.jar 1 TestModels\pluripotency_modified\model.net TestModels\pluripotency_modified\observations.spec time_step
java -jar NAE.jar 10 TestModels\myloid_model\model.net TestModels\myloid_model\observations.spec time_step

NOTE: add -v to perform validation, but leave it out when running benchmarks.

models in the CTL directory should be run as follows:
java -jar NAE.jar 100 CTL\toy_model_ctl\model.net CTL\toy_model_ctl\observations.ctlspec temporal_logic_bdd

NOTE:validation is not supported in this mode

models in LTL directory can be run in 2 ways as follows:


NOTE:validation is not supported in this mode
java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bdd
java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bmc -bmc 20















