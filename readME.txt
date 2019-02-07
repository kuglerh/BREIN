How to build and run NAE:

Dependencies:
NAE requires both the java SDK and NuSMV to be on the path.  It was tested using java version 1.8.0_144 and NuSMV-2.6.0.

Building:
to build NAE type the following two commands from within the NetworkAnalysisEngine directory:
javac NAE\*.java validate\*.java
jar cvfm NAE.jar NAE\manifest.txt NAE\*.class validate\*.class

This creates a file named "NAE.jar"

Running:
The following is the syntax for running NAE from the command line assuming we are in the  NetworkAnalysisEngine  directory:
java -jar NAE.jar  <solution limit> <model file path> <observation file path> <mode>

<Solution limit> is the number of solutions to search for
<model file path> is a path to a model file ending in .net
<observation file path> is a path to a spec file ending in .spec or .cltspec or .ltlspec
<mode> is the mode of encoding in NuSMV. Current modes are:
    time_step:                     for use with .spec files, 
    temporal_logic_bmc:      for use with .ctlspec and .ltlspec files to run the SAT solver with bounded model checking
    temporal_logic_bdd:       for use with .ctlspec and .ltlspec files to use the BDD algorithms for verification
    ctl:                                currently under development, but theoritcally more optomized for .ctlspec files

additionally there are optional arguments, which, if specified, must be after the 4 required arguments. They are:
-v to perform validation on solutions 
-bmc <length> to specify the bounds of bmc. Default is 20.

Validation is currently only supported on solutions from time_step mode.

Test models:
The test models in the testModels directory are meant to be run in time_step mode. Many of the included examples outperformed RE:IN on benchmarks. 

The test models in the CTL directory are meant to be run in temporal_logic_bdd mode (NuSMV does not support BMC based verification of CTL). One of the models included demonstrates the
usefulness of  CTL for asyncranous models, and is able to specify constraints on different possible paths that the non-deterministic asyncranous model can take.

The test models in the LTL directory can be run in either temporal_logic_bdd mode or temporal_logic_bmc mode, although the latter generally performs better than the former.
Included is a model that demonstrate the complex specifications LTL is capable of, as well as models that demonstrate how translation of RE:IN time-step style specs into LTL can sometimes result in a
performance boost (contrast the pluripotency_model10 under timestep mode with its LTL translation pluripotency10_ltl).

Evaluating the tool:
Included in the NetworkAnalysisEngine directory is a bash script named test that builds the tool and runs all of the test models under various parameters, printing the results and providing benchmarks. 
Please note that some of the very large models take up alot of memory and may not run properly if the virtual machine has less than 10 GB of memory. 
Make sure the terminal window is opened to fullscreen for proper formatting.
the bash script should be run by invoking: 
> bash test











