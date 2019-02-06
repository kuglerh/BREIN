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
java -jar NAE.jar 10 TestModels\pluripotency_model10\model.net TestModels\pluripotency_model10\observation.spec time_step
NOTE: add -v to perform validation, but leave it out when running benchmarks.

models in the CTL directory should be run as follows:
java -jar NAE.jar 100 CTL\toy_model_ctl\model.net CTL\toy_model_ctl\observations.ctlspec temporal_logic_bdd
java -jar NAE.jar 25 CTL\asyncMultiplePathExample\model.net CTL\asyncMultiplePathExample\observations.ctlspec temporal_logic_bdd

NOTE:validation is not supported in this mode

models in LTL directory can be run in 2 ways, either in temporal_logic_bdd mode or temporal_logic_bmc mode:

java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bdd
java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bmc -bmc 20
java -jar NAE.jar 10 LTL\pluripotency10_ltl\model.net LTL\pluripotency10_ltl\observation.ltlspec temporal_logic_bmc
java -jar NAE.jar 25 LTL\ComplexLTLExample\model.net LTL\ComplexLTLExample\observation.ltlspec temporal_logic_bmc

NOTE:validation is not supported in this mode

Advantages of NAE:
1) Speed. Compare Benchmarks of NAE to RE:IN on the time_step models 

2) Expressivness. This comes with 2 benefits.

1) In some cases, translating a time-step spec into its equivalent temporal logic form  can result in a speed up. See transaltion of toy model into LTL/CTL for an idea of how such a translation looks. Compare:
java -jar NAE.jar 10 TestModels\pluripotency_model10\model.net TestModels\pluripotency_model10\observation.spec time_step
java -jar NAE.jar 10 LTL\pluripotency10_ltl\model.net LTL\pluripotency10_ltl\observation.ltlspec temporal_logic_bmc
Also compare to RE:IN

2) Can express specs not possible in RE:IN. LTL and CTL have overlap, but are different in expressive power, and bot hare useful.

Example of LTL expressivness:
java -jar NAE.jar 25 LTL\ComplexLTLExample\model.net LTL\ComplexLTLExample\observation.ltlspec temporal_logic_bmc

This model contains complex LTL statements, such as seeing if a predicate will ever hold in the future, ensuring that a predicate is true until a second one holds, and that ine predicate frees a second one, seeing a property always holds, or only holds once.

Example of CTL expressivness. CTLs benefits really shine when verifying async models, where things are not deterministic. Since CTL can look at different branches, we can use it to ensure that a given async concrete model can potentially satisfy different contradictory conditions. For example, we can asertain that experiment N can theoretically stabilize at state A and can theoretically stabilize at state B. 
java -jar NAE.jar 25 CTL\asyncMultiplePathExample\model.net CTL\asyncMultiplePathExample\observations.ctlspec temporal_logic_bdd

















