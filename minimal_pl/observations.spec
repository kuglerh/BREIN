// Experiment One from twoi plus LIF to twoi
#ExperimentOne[0] |= $TwoiPlusLif	 "twoi plus LIF initial conditions";
#ExperimentOne[0] |= $TwoiCultureConditions "twoi culture conditions";
#ExperimentOne[0] |= $NoKnockOuts "Exp1 knockouts"; 
#ExperimentOne[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentOne[18] |= $Twoi "twoi penultimate state Exp 1";
#ExperimentOne[19] |= $Twoi "twoi final state Exp 1";


// Experiment Two from twoi to twoi plus LIF
#ExperimentTwo[0] |= $Twoi "twoi initial conditions"; 
#ExperimentTwo[0] |= $TwoiPlusLifCultureConditions "twoi plus LIF culture conditions";
#ExperimentTwo[0] |= $NoKnockOuts "Exptwo knockouts"; 
#ExperimentTwo[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentTwo[18] |= $TwoiPlusLif  "twoi plus LIF penultimate state Exp two";
#ExperimentTwo[19] |= $TwoiPlusLif "twoi plus LIF final state Exp two";


// Experiment Three from twoi plus LIF to LIF plus PD
#ExperimentThree[0] |= $TwoiPlusLif "twoi plus LIF initial state";
#ExperimentThree[0] |= $LifPlusPdCultureConditions "LIF plus PD culture conditions";
#ExperimentThree[0] |= $NoKnockOuts "Exp3 knockouts"; 
#ExperimentThree[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentThree[18] |= $LifPlusPd  "LIF plus PD penultimate state Exp 3";
#ExperimentThree[19] |= $LifPlusPd "LIF plus PD final state Exp 3";


// Experiment Four from LIF plus PD to twoi plus LIF
#ExperimentFour[0] |= $LifPlusPd "LIF plus PD initial state";
#ExperimentFour[0] |= $TwoiPlusLifCultureConditions "twoi plus LIF culture conditions";
#ExperimentFour[0] |= $NoKnockOuts "Exp4 knockouts"; 
#ExperimentFour[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentFour[18] |= $TwoiPlusLif  "twoi plus LIF penultimate state Exp 4";
#ExperimentFour[19] |= $TwoiPlusLif "twoi plus LIF final state Exp 4";


// Experiment Five from twoi plus LIF to LIF plus CH
#ExperimentFive[0] |= $TwoiPlusLif "twoi plus LIF initial state";
#ExperimentFive[0] |= $LifPlusChCultureConditions "LIF plus CH culture conditions";
#ExperimentFive[0] |= $NoKnockOuts "Exp5 knockouts";  
#ExperimentFive[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentFive[18] |= $LifPlusCh  "LIF plus CH penultimate state Exp 5";
#ExperimentFive[19] |= $LifPlusCh "LIF plus CH final state Exp 5";


// Experiment Six from LIF plus CH to twoi plus LIF
#ExperimentSix[0] |= $LifPlusCh "LIF plus CH initial state";
#ExperimentSix[0] |= $TwoiPlusLifCultureConditions "twoi plus LIF culture conditions";
#ExperimentSix[0] |= $NoKnockOuts "Exp6 knockouts";  
#ExperimentSix[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentSix[18] |= $TwoiPlusLif  "twoi plus LIF penultimate state Exp 6";
#ExperimentSix[19] |= $TwoiPlusLif "twoi plus LIF final state Exp 6";


// Experiment Seven from twoi to LIF plus PD
#ExperimentSeven[0] |= $Twoi "twoi initial state";
#ExperimentSeven[0] |= $LifPlusPdCultureConditions "LIF plus PD culture conditions";
#ExperimentSeven[0] |= $NoKnockOuts "Exp7 knockouts";  
#ExperimentSeven[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentSeven[18] |= $LifPlusPd  "LIF plus PD penultimate state Exp 7";
#ExperimentSeven[19] |= $LifPlusPd "LIF plus PD final state Exp 7";


// Experiment Eight from LIF plus PD to twoi 
#ExperimentEight[0] |= $LifPlusPd "LIF plus PD initial state";
#ExperimentEight[0] |= $TwoiCultureConditions "twoi culture conditions";
#ExperimentEight[0] |= $NoKnockOuts "Exp8 knockouts";  
#ExperimentEight[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentEight[18] |= $Twoi  "twoi penultimate state Exp 8";
#ExperimentEight[19] |= $Twoi "twoi final state Exp 8";


// Experiment Nine from twoi to LIF plus CH
#ExperimentNine[0] |= $Twoi "twoi initial state";
#ExperimentNine[0] |= $LifPlusChCultureConditions "LIF plus CH culture conditions";
#ExperimentNine[0] |= $NoKnockOuts "Exp9 knockouts";  
#ExperimentNine[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentNine[18] |= $LifPlusCh  "LIF plus CH penultimate state Exp 9";
#ExperimentNine[19] |= $LifPlusCh "LIF plus CH final state Exp 9";

// Experiment Ten from LIF plus CH to twoi 
#ExperimentTen[0] |= $LifPlusCh "LIF plus CH initial state";
#ExperimentTen[0] |= $TwoiCultureConditions "twoi culture conditions";
#ExperimentTen[0] |= $NoKnockOuts "Exp10 knockouts";  
#ExperimentTen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentTen[18] |= $Twoi "twoi penultimate state Exp 10";
#ExperimentTen[19] |= $Twoi "twoi final state Exp 10";


// Experiment Eleven from LIF plus CH to LIF plus PD
#ExperimentEleven[0] |= $LifPlusCh "LIF plus CH initial state";
#ExperimentEleven[0] |= $LifPlusPdCultureConditions "LIF plus PD culture conditions";
#ExperimentEleven[0] |= $NoKnockOuts "Exp11 knockouts";  
#ExperimentEleven[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentEleven[18] |= $LifPlusPd  "LIF plus PD penultimate state Exp 11";
#ExperimentEleven[19] |= $LifPlusPd "LIF plus PD final state Exp 11";


// Experiment Twelve from LIF plus PD to LIF plus CH
#ExperimentTwelve[0] |= $LifPlusPd "LIF plus CH initial state";
#ExperimentTwelve[0] |= $LifPlusChCultureConditions "LIF plus CH culture conditions";
#ExperimentTwelve[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentTwelve[0] |= $NoKnockOuts "Exp1two knockouts";  
#ExperimentTwelve[18] |= $LifPlusCh "LIF plus CH penultimate state Exp 1two";
#ExperimentTwelve[19] |= $LifPlusCh "LIF plus CH final state Exp 1two";

// Experiment Thirteen from LIF plus twoi to no signal
#ExperimentThirteen[0] |= $TwoiPlusLif "twoi plus LIF initial conditions";
#ExperimentThirteen[0] |= $NoSignalCultureConditions "No signals culture conditions";
#ExperimentThirteen[0] |= $NoKnockOuts "Exp13 knockouts"; 
#ExperimentThirteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentThirteen[18] |= $NoSignal "No signal at penultimate state exp 13";
#ExperimentThirteen[19] |= $NoSignal "No signal at final state exp 13";

// Experiment Fourteen from twoi to no signal
#ExperimentFourteen[0] |= $Twoi "twoi initial conditions";
#ExperimentFourteen[0] |= $NoSignalCultureConditions "No signals culture conditions";
#ExperimentFourteen[0] |= $NoKnockOuts "Exp14 knockouts";  
#ExperimentFourteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentFourteen[18] |= $NoSignal "No signal at penultimate state exp 14";
#ExperimentFourteen[19] |= $NoSignal "No signal at final state exp 14";

// Experiment Fifteen from LIF plus PD to no signal
#ExperimentFifteen[0] |= $LifPlusPd "LIF plus PD initial conditions";
#ExperimentFifteen[0] |= $NoSignalCultureConditions "No signals culture conditions";
#ExperimentFifteen[0] |= $NoKnockOuts "Exp15 knockouts";  
#ExperimentFifteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentFifteen[18] |= $NoSignal "No signal at penultimate state exp 15";
#ExperimentFifteen[19] |= $NoSignal "No signal at final state exp 15";

// Experiment Sixteen from twoi Oct4 Knockout to everything being repressed
#ExperimentSixteen[0] |= $TwoiOctFourKnockout "Two i initial conditions with Oct four knockout";
#ExperimentSixteen[0] |= $TwoiCultureConditions "Two i culture conditions";
#ExperimentSixteen[0] |= $Oct4GeneKnockOut "Exp16 knockouts";  
#ExperimentSixteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentSixteen[18] |= $FinalStateAllZeroExpression "penultimate state exp 16";
#ExperimentSixteen[19] |= $FinalStateAllZeroExpression "final state exp 16";


// Experiment Seventeen from twoi Soxtwo Knockout to everything going down
#ExperimentSeventeen[0] |= $TwoiSoxTwoKnockout "Two i initial conditions with Soxtwo knockout";
#ExperimentSeventeen[0] |= $TwoiCultureConditions "Two i culture conditions";
#ExperimentSeventeen[0] |= $SoxtwoGeneKnockOut "Exp17 knockouts";  
#ExperimentSeventeen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentSeventeen[18] |= $FinalStateAllZeroExpression "penultimate state exp 17";
#ExperimentSeventeen[19] |= $FinalStateAllZeroExpression "final state exp 17";

// Experiment Eighteen from twoi Stat3 Knockout to everything going down
#ExperimentEighteen[0] |= $twoiStatThreeKnockout "twoi with Stat3 knockout";
#ExperimentEighteen[0] |= $LifPlusPdCultureConditions "Lif plus PD culture conditions";
#ExperimentEighteen[0] |= $Stat3GeneKnockOut "Exp18 knockouts";  
#ExperimentEighteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentEighteen[18] |= $FinalStateAllZeroExpression "penultimate state exp 18";
#ExperimentEighteen[19] |= $FinalStateAllZeroExpression "final state exp 18";

// Experiment Nineteen from twoi Esrrb Knockout to everything going down 
#ExperimentNineteen[0] |= $twoiEsrrbKnockout "twoi Esrrb knockout";
#ExperimentNineteen[0] |= $TwoiCultureConditions "twoi culture conditions";
#ExperimentNineteen[0] |= $EsrrbGeneKnockOut "Exp19 knockouts";  
#ExperimentNineteen[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentNineteen[18] |= $FinalStateAllZeroExpression "penultimate state exp 19";
#ExperimentNineteen[19] |= $FinalStateAllZeroExpression "final state exp 19";

// Experiment Twenty from twoi plus LIF Esrrb Knockout 
#ExperimentTwenty[0] |= $twoiPlusLifEsrrbKnockout "twoi plus LIF with Esrrb knockout";
#ExperimentTwenty[0] |= $TwoiPlusLifCultureConditions "twoi plus LIF culture conditions";
#ExperimentTwenty[0] |= $EsrrbGeneKnockOut "Exptwo0 knockouts";  
#ExperimentTwenty[0] |= $NoOverExpression "Exp1 overexpression";
#ExperimentTwenty[18] |= $FinalStatetwoiPlusLifEsrrbKnockout "penultimate state exp two0";
#ExperimentTwenty[19] |= $FinalStatetwoiPlusLifEsrrbKnockout "final state exp two0";

// Experiment Twenty one overexpression of Esrrb from twoi to just PD
#ExperimentTwentyOne[0] |= $twoiEsrrbOverexpression "twoi with Esrrb overexpression";
#ExperimentTwentyOne[0] |= $JustPdCultureConditions "Just PD culture conditions";
#ExperimentTwentyOne[0] |= $NoKnockOuts "Exptwo1 knockouts";  
#ExperimentTwentyOne[0] |= $EsrrbGeneOverExpression "Exptwo1 overexpression";
#ExperimentTwentyOne[18] |= $FinalStatePdOnlyEsrrbOverexpression "penultimate state exp two1";
#ExperimentTwentyOne[19] |= $FinalStatePdOnlyEsrrbOverexpression "final state exp two1";

// Experiment Nanog knockdown in twoi plus lIF
#NanogKnockdown[0] |= $twoiPlusLifNanogKnockdown;
#NanogKnockdown[0] |= $TwoiPlusLifCultureConditions;
#NanogKnockdown[0] |= $NanogGeneKnockOut;
#NanogKnockdown[0] |= $NoOverExpression;
#NanogKnockdown[18] |= $FinalStateNanogKnockout;
#NanogKnockdown[19] |= $FinalStateNanogKnockout;

// Tfcptwol1 overexpression
#Tfcptwol1Overexpression[0] |= $twoiPlusLifTfcptwol1Overexpression;
#Tfcptwol1Overexpression[0] |= $JustPdCultureConditions;
#Tfcptwol1Overexpression[0] |= $Tfcptwol1GeneOverExpression;
#Tfcptwol1Overexpression[0] |= $NoKnockOuts;
#Tfcptwol1Overexpression[18] |= $FinalStateTfcptwol1Overexpression;
#Tfcptwol1Overexpression[19] |= $FinalStateTfcptwol1Overexpression;

// Adding all of the twoi plus LIF predictions to search for the minimal model

// Gbxtwo and Klf4 pluripotency maintained
#GbxtwoKlf4Knockdown[0] |= $twoiPlusLifGbxtwoKlf4Knockdown;
#GbxtwoKlf4Knockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoKlf4Knockdown[0] |= $GbxtwoKlf4GeneKnockOut;
#GbxtwoKlf4Knockdown[0] |= $NoOverExpression;
#GbxtwoKlf4Knockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoKlf4Knockdown[19] |= $Oct4AndSoxtwoMaintained;

// Klf4 and Sall4 pluripotency not maintained
#Klf4Sall4Knockdown[0] |= $twoiPlusLifKlf4Sall4Knockdown;
#Klf4Sall4Knockdown[0] |= $TwoiPlusLifCultureConditions;
#Klf4Sall4Knockdown[0] |= $Klf4Sall4GeneKnockOut;
#Klf4Sall4Knockdown[0] |= $NoOverExpression;
#Klf4Sall4Knockdown[18] |= $Oct4AndSoxtwoNotMaintained;
#Klf4Sall4Knockdown[19] |= $Oct4AndSoxtwoNotMaintained;

// Gbxtwo and Esrrb pluripotency maintained
#GbxtwoEsrrbKnockdown[0] |= $twoiPlusLifGbxtwoEsrrbKnockdown;
#GbxtwoEsrrbKnockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoEsrrbKnockdown[0] |= $GbxtwoEsrrbGeneKnockOut;
#GbxtwoEsrrbKnockdown[0] |= $NoOverExpression;
#GbxtwoEsrrbKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoEsrrbKnockdown[19] |= $Oct4AndSoxtwoMaintained;

// Tbx3 and Gbxtwo pluripotency maintained
#GbxtwoTbx3Knockdown[0] |= $twoiPlusLifGbxtwoTbx3Knockdown;
#GbxtwoTbx3Knockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoTbx3Knockdown[0] |= $GbxtwoTbx3GeneKnockOut;
#GbxtwoTbx3Knockdown[0] |= $NoOverExpression;
#GbxtwoTbx3Knockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoTbx3Knockdown[19] |= $Oct4AndSoxtwoMaintained;

// Gbxtwo and Sall4 pluripotency maintained
#GbxtwoSall4Knockdown[0] |= $twoiPlusLifGbxtwoSall4Knockdown;
#GbxtwoSall4Knockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoSall4Knockdown[0] |= $GbxtwoSall4GeneKnockOut;
#GbxtwoSall4Knockdown[0] |= $NoOverExpression;
#GbxtwoSall4Knockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoSall4Knockdown[19] |= $Oct4AndSoxtwoMaintained;

// Gbxtwo and Nanog pluripotency maintained
#GbxtwoNanogKnockdown[0] |= $twoiPlusLifGbxtwoNanogKnockdown;
#GbxtwoNanogKnockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoNanogKnockdown[0] |= $GbxtwoNanogGeneKnockOut;
#GbxtwoNanogKnockdown[0] |= $NoOverExpression;
#GbxtwoNanogKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoNanogKnockdown[19] |= $Oct4AndSoxtwoMaintained;

// Tbx3 and Tfcptwol1 pluripotency maintained
#Tbx3Tfcptwol1Knockdown[0] |= $twoiPlusLifTbx3Tfcptwol1Knockdown;
#Tbx3Tfcptwol1Knockdown[0] |= $TwoiPlusLifCultureConditions;
#Tbx3Tfcptwol1Knockdown[0] |= $Tbx3Tfcptwol1GeneKnockOut;
#Tbx3Tfcptwol1Knockdown[0] |= $NoOverExpression;
#Tbx3Tfcptwol1Knockdown[18] |= $Oct4AndSoxtwoMaintained;
#Tbx3Tfcptwol1Knockdown[19] |= $Oct4AndSoxtwoMaintained;

// Tfcptwol1 and Klftwo pluripotency not maintained
#Tfcptwol1KlftwoKnockdown[0] |= $twoiPlusLifTfcptwol1KlftwoKnockdown;
#Tfcptwol1KlftwoKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Tfcptwol1KlftwoKnockdown[0] |= $Tfcptwol1KlftwoGeneKnockOut;
#Tfcptwol1KlftwoKnockdown[0] |= $NoOverExpression;
#Tfcptwol1KlftwoKnockdown[18] |= $Oct4AndSoxtwoNotMaintained;
#Tfcptwol1KlftwoKnockdown[19] |= $Oct4AndSoxtwoNotMaintained;

// Esrrb and Nanog pluripotency maintained
#EsrrbNanogKnockdown[0] |= $twoiPlusLifEsrrbNanogKnockdown;
#EsrrbNanogKnockdown[0] |= $TwoiPlusLifCultureConditions;
#EsrrbNanogKnockdown[0] |= $EsrrbNanogGeneKnockOut;
#EsrrbNanogKnockdown[0] |= $NoOverExpression;
#EsrrbNanogKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#EsrrbNanogKnockdown[19] |= $Oct4AndSoxtwoMaintained;

// Klftwo and Tbx3 pluripotency maintained
#KlftwoTbx3DoubleKnockout[0] |= $twoiPlusLifKlftwoTbx3Knockdown;
#KlftwoTbx3DoubleKnockout[0] |= $TwoiPlusLifCultureConditions;
#KlftwoTbx3DoubleKnockout[0] |= $KlftwoTbx3GeneKnockOut;
#KlftwoTbx3DoubleKnockout[0] |= $NoOverExpression;
#KlftwoTbx3DoubleKnockout[18] |= $Oct4AndSoxtwoMaintained;
#KlftwoTbx3DoubleKnockout[19] |= $Oct4AndSoxtwoMaintained;

// Sall4 and Klftwo pluripotency not maintained
#Sall4KlftwoKnockdown[0] |= $twoiPlusLifSall4KlftwoKnockdown;
#Sall4KlftwoKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Sall4KlftwoKnockdown[0] |= $Sall4KlftwoGeneKnockOut;
#Sall4KlftwoKnockdown[0] |= $NoOverExpression;
#Sall4KlftwoKnockdown[18] |= $Oct4AndSoxtwoNotMaintained;
#Sall4KlftwoKnockdown[19] |= $Oct4AndSoxtwoNotMaintained;

// Culture conditions 
$TwoiPlusLifCultureConditions :=
{
 LIF = 1 and
 CH = 1 and 
 PD = 1
};

$TwoiCultureConditions :=
{
 LIF = 0 and
 CH = 1 and
 PD = 1
};

$LifPlusPdCultureConditions :=
{
 LIF = 1 and 
 CH = 0 and 
 PD = 1
};

$LifPlusChCultureConditions :=
{
 LIF = 1 and
 CH = 1 and 
 PD = 0
};

$NoSignalCultureConditions :=
{
 LIF = 0 and
 CH = 0 and 
 PD = 0
};

// When you do not want any knock outs or overexpressions
$NoKnockOuts :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

// Setting which genes are knocked out or overexpressed

$Oct4GeneKnockOut :=
{
 KO(Oct4)=1 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$SoxtwoGeneKnockOut :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=1 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and
 KO(Tfcptwol1)=0 and  
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0
};

$Stat3GeneKnockOut :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=1 and
 KO(Nanog)=0 and
 KO(Tfcptwol1)=0 and  
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$EsrrbGeneKnockOut :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=1 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$NanogGeneKnockOut :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=1 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$KlftwoTbx3GeneKnockOut :=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=1 and
 KO(Tbx3)=1 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$GbxtwoKlf4GeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=1 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=1
};

$Klf4Sall4GeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=1 and 
 KO(Sall4)=1 and
 KO(Gbxtwo)=0
};

$Sall4KlftwoGeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=1 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=1 and
 KO(Gbxtwo)=0
};

$GbxtwoTbx3GeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=1 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=1
};

$Tbx3Tfcptwol1GeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=1 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=1 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$GbxtwoEsrrbGeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=1 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=1
};

$GbxtwoSall4GeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=1 and
 KO(Gbxtwo)=1
};

$GbxtwoNanogGeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=1 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=1
};

$EsrrbNanogGeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=1 and
 KO(Stat3)=0 and
 KO(Nanog)=1 and 
 KO(Tfcptwol1)=0 and 
 KO(Klftwo)=0 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$Tfcptwol1KlftwoGeneKnockOut:=
{
 KO(Oct4)=0 and
 KO(Soxtwo)=0 and
 KO(Esrrb)=0 and
 KO(Stat3)=0 and
 KO(Nanog)=0 and 
 KO(Tfcptwol1)=1 and 
 KO(Klftwo)=1 and
 KO(Tbx3)=0 and
 KO(Klf4)=0 and 
 KO(Sall4)=0 and
 KO(Gbxtwo)=0
};

$JustPdCultureConditions :=
{
 LIF = 0 and
 CH = 0 and 
 PD = 1
};

$EsrrbGeneOverExpression :=
{
 FE(Esrrb)=1 and 
 FE(Tfcptwol1)=0
};

$Tfcptwol1GeneOverExpression:=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=1 
};

$NoOverExpression :=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=0
};

// Gene expression levels for each culture condition 

$TwoiPlusLif:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$Twoi:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$LifPlusPd:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=0 and
 Tcf3=1 and
 Sall4=0 and
 Stat3=1
};

$LifPlusCh:=
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$NoSignal:=
{
 MEKERK = 1 and
 Oct4=0 and
 Soxtwo=0 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Gbxtwo = 0 and 
 Tfcptwol1=0 and
 Klf4=0 and
 Tbx3=0 and
 Sall4=0 and
 Stat3=0
};

// Twoi conditions with Oct4 knockout
$TwoiOctFourKnockout:=
{
 MEKERK = 0 and
 Oct4=0 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

// Twoi conditions with Soxtwo knockout
$TwoiSoxTwoKnockout:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=0 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

// LIF plus PD conditions with Stat3 knockout
$twoiStatThreeKnockout :=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};
 
$FinalStateAllZeroExpression:=
{
 MEKERK = 0 and
 Oct4=0 and
 Soxtwo=0 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Gbxtwo=0 and 
 Tfcptwol1=0 and
 Klf4=0 and
 Tbx3=0 and
 Sall4=0 and
 Stat3=0
};

$twoiEsrrbKnockout:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiPlusLifEsrrbKnockout:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$FinalStatetwoiPlusLifEsrrbKnockout:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

// Twoi conditions with esrrb overexpression
$twoiEsrrbOverexpression :=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

// Only PD
$FinalStatePdOnlyEsrrbOverexpression:=
{
 Oct4 = 1 and 
 Soxtwo = 1 and 
 Nanog = 1 and
 Esrrb = 1 and
 Klf4 = 0
};

// Nanog knock down in twoi plus LIF
$twoiPlusLifNanogKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

// Final state
$FinalStateNanogKnockout:=
{
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Tbx3=1 and
 Esrrb=0
};

// Initial state Tfcptwol1 overexpression in twoi plus LIF
$twoiPlusLifTfcptwol1Overexpression:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

// Final state Tfcptwol1 overexpression
$FinalStateTfcptwol1Overexpression:=
{
 Oct4=1 and
 Soxtwo=1 and 
 Nanog=1 and 
 Esrrb=1 and 
 Klf4=0
};

$twoiPlusLifKlftwoTbx3Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=0 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=0 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiKlf4NanogKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiKlftwoKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=0 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiKlf4Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiNanogKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiTfcptwol1Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=0
};

$twoiPlusLifSall4KlftwoKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=0 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=0 and
 Stat3=1
};

$twoiPlusLifTfcptwol1KlftwoKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifKlf4Sall4Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=0 and
 Stat3=1
};

$twoiPlusLifGbxtwoKlf4Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifGbxtwoTbx3Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifTbx3Tfcptwol1Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=0 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifGbxtwoEsrrbKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifGbxtwoSall4Knockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=0 and
 Stat3=1
};

$twoiPlusLifGbxtwoNanogKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$twoiPlusLifEsrrbNanogKnockdown:=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$Oct4AndSoxtwoMaintained:=
{
 Oct4=1 and 
 Soxtwo=1
};

$Oct4AndSoxtwoNotMaintained:=
{
 Oct4=0 and 
 Soxtwo=0
};