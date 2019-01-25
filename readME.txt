requires Java and NuSMV to be on the path


Building NAE:
navigate to the directory containng this readME. Type:
javac NAE\*.java verify\*.java
jar cvfm NAE.jar NAE\manifest.txt NAE\*.class verify\*.class

Running NAE:
navigate to the same directory as before and type:
java -jar NAE.jar  <solution limit> <model file path> <observation file path> 

