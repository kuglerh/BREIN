rem building NAE...

javac NAE\*.java validate\*.java
jar cvfm NAE.jar NAE\manifest.txt NAE\*.class validate\*.class

rem Running time_step models:

powershell -Command "Measure-Command { java -jar NAE.jar 100 TestModels\toy_model\model.net TestModels\toy_model\observations.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 1 TestModels\minimal_pl\model.net TestModels\minimal_pl\observations.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 1 TestModels\pluripotency_model\model.net TestModels\pluripotency_model\observation.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 5 TestModels\pluripotency_model\model.net TestModels\pluripotency_model\observation.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 1 TestModels\pluripotency_modified\model.net TestModels\pluripotency_modified\observations.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 10 TestModels\myloid_model\model.net TestModels\myloid_model\observations.spec time_step | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 10 TestModels\pluripotency_model10\model.net TestModels\pluripotency_model10\observation.spec time_step | Out-Default}"


rem Running LTL models:

powershell -Command "Measure-Command { java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bdd | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 100 LTL\toy_model_ltl\model.net LTL\toy_model_ltl\observations.ltlspec temporal_logic_bmc -bmc 20 | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 10 LTL\pluripotency10_ltl\model.net LTL\pluripotency10_ltl\observation.ltlspec temporal_logic_bmc | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 25 LTL\ComplexLTLExample\model.net LTL\ComplexLTLExample\observation.ltlspec temporal_logic_bmc | Out-Default}"

rem Running CTL models:

powershell -Command "Measure-Command { java -jar NAE.jar 100 CTL\toy_model_ctl\model.net CTL\toy_model_ctl\observations.ctlspec temporal_logic_bdd | Out-Default}"
powershell -Command "Measure-Command { java -jar NAE.jar 25 CTL\asyncMultiplePathExample\model.net CTL\asyncMultiplePathExample\observations.ctlspec temporal_logic_bdd | Out-Default}"


PAUSE