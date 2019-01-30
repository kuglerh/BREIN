requires Java and NuSMV to be on the path


Building NAE:
navigate to the directory containng this readME. Type:
javac NAE\*.java verify\*.java
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

