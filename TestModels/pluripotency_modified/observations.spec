



// Experiment Twenty from twoi plus LIF Esrrb Knockdown 
#ExperimentTwenty[0] |=  $twoiPlusLifEsrrbKnockout "Exptwo0 initial expression pattern";
#ExperimentTwenty[0] |=  $TwoiPlusLifCultureConditions "Exptwo0 culture conditions";
#ExperimentTwenty[0] |=  $EsrrbGeneKnockDown "Exptwo0 Esrrb knockdown";  
#ExperimentTwenty[0] |=  $NoOverExpression "Exptwo0 no overexpression";
#ExperimentTwenty[18] |=  $FinalStatetwoiPlusLifEsrrbKnockout "Exptwo0 penultimate state";
#ExperimentTwenty[19] |=  $FinalStatetwoiPlusLifEsrrbKnockout "Exptwo0 final state";

// Experiment Twenty One overexpression of Esrrb from twoi to just PD
#ExperimentTwentyOne[0] |=  $twoiEsrrbOverexpression "Exptwo1 initial expression pattern";
#ExperimentTwentyOne[0] |=  $JustPdCultureConditions "Exptwo1 culture conditions";
#ExperimentTwentyOne[0] |=  $NoKnockDowns "Exptwo1 no knockdowns";  
#ExperimentTwentyOne[0] |=  $EsrrbGeneOverExpression "Exptwo1 no overexpression";
#ExperimentTwentyOne[18] |=  $FinalStatePdOnlyEsrrbOverexpression "Exptwo1 penultimate state";
#ExperimentTwentyOne[19] |=  $FinalStatePdOnlyEsrrbOverexpression "Exptwo1 final state";

// Experiment Twenty Two Nanog knockdown in twoi plus lIF
#ExperimentTwentyTwo[0] |=  $twoiPlusLifNanogKnockdown "Exptwotwo initial expression pattern";
#ExperimentTwentyTwo[0] |=  $TwoiPlusLifCultureConditions "Exptwotwo culture conditions";
#ExperimentTwentyTwo[0] |=  $NanogKnockDown "Exptwotwo Nanog knockdown";
#ExperimentTwentyTwo[0] |=  $NoOverExpression "Exp twotwo no overexpression";
#ExperimentTwentyTwo[18] |=  $FinalStateNanogKnockout "Exptwotwo penultimate state";
#ExperimentTwentyTwo[19] |=  $FinalStateNanogKnockout "Exptwotwo final state";














// Experiment Twenty Three overexpression of Tfcptwol1 in twoi plus LIF
#ExperimentTwentyThree[0] |=  $twoiPlusLifTfcptwol1Overexpression "Exptwo3 initial expression pattern";
#ExperimentTwentyThree[0] |=  $JustPdCultureConditions "Exptwo3 culture conditions";
#ExperimentTwentyThree[0] |=  $NoKnockDowns "Exptwo3 no knockdowns";
#ExperimentTwentyThree[0] |=  $Tfcptwol1GeneOverExpression "Exptwo3 Tfcptwol1 overexpression";
#ExperimentTwentyThree[18] |=  $FinalStateTfcptwol1Overexpression "Exptwo3 penultimate state";
#ExperimentTwentyThree[19] |=  $FinalStateTfcptwol1Overexpression "Exp two3 final state";

//
// TWO DKDS PREDICTIONS WE NEED TO CORRECT
//

// Klf4 Sall4 DKD does not allow pluripotency to be sustained
#Klf4Sall4DoubleKnockdown[0] |= $TwoiPlusLifWithKlf4Sall4DKD;
#Klf4Sall4DoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Klf4Sall4DoubleKnockdown[0] |= $Klf4Sall4DKD;
#Klf4Sall4DoubleKnockdown[0] |= $NoOverExpression;
not(#Klf4Sall4DoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained);

// Klftwo Tbx3 DKD does allow pluripotency to be sustained
#KlftwoTbx3DoubleKnockdown[0] |= $TwoiPlusLifWithKlftwoTbx3DKD;
#KlftwoTbx3DoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#KlftwoTbx3DoubleKnockdown[0] |= $KlftwoTbx3DKD;
#KlftwoTbx3DoubleKnockdown[0] |= $NoOverExpression;
#KlftwoTbx3DoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#KlftwoTbx3DoubleKnockdown[19] |= $Oct4AndSoxtwoMaintained;


//
// SIX DKDS WE HAVE EXPERIMENTAL EVIDENCE FOR
//

#GbxtwoNanogDoubleKnockdown[0] |= $TwoiPlusLifWithGbxtwoNanogDKD;
#GbxtwoNanogDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoNanogDoubleKnockdown[0] |= $GbxtwoNanogDKD;
#GbxtwoNanogDoubleKnockdown[0] |= $NoOverExpression;
#GbxtwoNanogDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoNanogDoubleKnockdown[19] |= $Oct4AndSoxtwoMaintained;

#TfcpKlftwoDoubleKnockdown[0] |= $TwoiPlusLifWithTfcpKlftwoDKD;
#TfcpKlftwoDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#TfcpKlftwoDoubleKnockdown[0] |= $TfcpKlftwoDKD;
#TfcpKlftwoDoubleKnockdown[0] |= $NoOverExpression;
not(#TfcpKlftwoDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained);

#Sall4KlftwoDoubleKnockdown[0] |= $TwoiPlusLifWithSall4KlftwoDKD;
#Sall4KlftwoDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Sall4KlftwoDoubleKnockdown[0] |= $Sall4KlftwoDKD;
#Sall4KlftwoDoubleKnockdown[0] |= $NoOverExpression;
not(#Sall4KlftwoDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained);

#Klf4EsrrbDoubleKnockdown[0] |= $TwoiPlusLifWithKlf4EsrrbDKD;
#Klf4EsrrbDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Klf4EsrrbDoubleKnockdown[0] |= $Klf4EsrrbDKD;
#Klf4EsrrbDoubleKnockdown[0] |= $NoOverExpression;
#Klf4EsrrbDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#Klf4EsrrbDoubleKnockdown[19] |= $Oct4AndSoxtwoMaintained;

#Klf4NanogDoubleKnockdown[0] |= $TwoiPlusLifWithKlf4NanogDKD;
#Klf4NanogDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#Klf4NanogDoubleKnockdown[0] |= $Klf4NanogDKD;
#Klf4NanogDoubleKnockdown[0] |= $NoOverExpression;
#Klf4NanogDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#Klf4NanogDoubleKnockdown[19] |= $Oct4AndSoxtwoMaintained;

#GbxtwoTfcpDoubleKnockdown[0] |= $TwoiPlusLifWithGbxtwoTfcpDKD;
#GbxtwoTfcpDoubleKnockdown[0] |= $TwoiPlusLifCultureConditions;
#GbxtwoTfcpDoubleKnockdown[0] |= $GbxtwoTfcpDKD;
#GbxtwoTfcpDoubleKnockdown[0] |= $NoOverExpression;
#GbxtwoTfcpDoubleKnockdown[18] |= $Oct4AndSoxtwoMaintained;
#GbxtwoTfcpDoubleKnockdown[19] |= $Oct4AndSoxtwoMaintained;












//
// REPROGRAMMING SPECIFICATIONS
//


// Just twoiplusLIF without any overexpressions
#Control[0] |= $ZeroInitialConditions;
#Control[0] |= $TwoiPlusLifCultureConditions;
#Control[0] |= $NoKnockDowns;
#Control[0] |= $NoOverExpression;
not(#Control[18] |= $TwoiPlusLif);
not(#Control[19] |= $TwoiPlusLif);

// EpiSC can spontaneously convert in twoiplusLIF
// Conversion will occur by step 18 at the latest
#SpontaneousConversion[0] |= $EpiSC;
#SpontaneousConversion[0] |= $TwoiPlusLifCultureConditions;
#SpontaneousConversion[0] |= $NoKnockDowns;
#SpontaneousConversion[0] |= $NoOverExpression;
#SpontaneousConversion[18] |= $TwoiPlusLif;
#SpontaneousConversion[19] |= $TwoiPlusLif;

// EpiSC cannot spontaneously convert in twoi
#NoConversionInTwoiOnly[0] |= $EpiSC;
#NoConversionInTwoiOnly[0] |= $TwoiCultureConditions;
#NoConversionInTwoiOnly[0] |= $NoKnockDowns;
#NoConversionInTwoiOnly[0] |= $NoOverExpression;
not(#NoConversionInTwoiOnly[18] |= $Twoi);
not(#NoConversionInTwoiOnly[19] |= $Twoi);

// EpiSC can convert in twoi with overexpression of Tfcptwol1
#ConversionWithTfcptwol1OE[0] |= $EpiSC;
#ConversionWithTfcptwol1OE[0] |= $TwoiCultureConditions;
#ConversionWithTfcptwol1OE[0] |= $NoKnockDowns;
#ConversionWithTfcptwol1OE[0] |= $Tfcptwol1GeneOverExpression;
#ConversionWithTfcptwol1OE[18] |= $EpiConversionState;
#ConversionWithTfcptwol1OE[19] |= $EpiConversionState;

// Nanog KO EpiSC

#NanogKDEpiSCInTwoiLIF[0] |= $NanogKOEpiSC;
#NanogKDEpiSCInTwoiLIF[0] |= $TwoiPlusLifCultureConditions;
#NanogKDEpiSCInTwoiLIF[0] |= $NanogKnockDown;
#NanogKDEpiSCInTwoiLIF[0] |= $NoOverExpression;
not(#NanogKDEpiSCInTwoiLIF[18] |= $TwoiPlusLif);
not(#NanogKDEpiSCInTwoiLIF[19] |= $TwoiPlusLif);

// Nanog KO can be rescued in Lif plus Ch

#NanogKDEpiSCInLIFAndCH[0] |= $NanogKOEpiSC;
#NanogKDEpiSCInLIFAndCH[0] |= $LifPlusChCultureConditions;
#NanogKDEpiSCInLIFAndCH[0] |= $NanogKnockDown;
#NanogKDEpiSCInLIFAndCH[0] |= $EsrrbGeneOverExpression;
#NanogKDEpiSCInLIFAndCH[18] |= $LifPlusChWithNanogKO;
#NanogKDEpiSCInLIFAndCH[19] |= $LifPlusChWithNanogKO;

// GOF18 in twoi with Klf4 OE
#GOF18Klf4Overexpressiontwoi[0] |= $EpiSCWithKlf4OE;
#GOF18Klf4Overexpressiontwoi[0] |= $TwoiCultureConditions;
#GOF18Klf4Overexpressiontwoi[0] |= $NoKnockDowns;
#GOF18Klf4Overexpressiontwoi[0] |= $Klf4OverExpression;
#GOF18Klf4Overexpressiontwoi[18] |= $AtLeastTwoiWithKlf4OE;
#GOF18Klf4Overexpressiontwoi[19] |= $AtLeastTwoiWithKlf4OE;

// GOF18 in twoiLIF with Klf4 OE
#GOF18Klf4OEtwoiLIF[0] |= $EpiSCWithKlf4OE;
#GOF18Klf4OEtwoiLIF[0] |= $TwoiPlusLifCultureConditions;
#GOF18Klf4OEtwoiLIF[0] |= $NoKnockDowns;
#GOF18Klf4OEtwoiLIF[0] |= $Klf4OverExpression;
#GOF18Klf4OEtwoiLIF[18] |= $TwoiPlusLif;
#GOF18Klf4OEtwoiLIF[19] |= $TwoiPlusLif;

//
// Sall4 Reprogramming
//

// Sall4 should only be as efficient as nothing is in twoiLIF
#ConversionWithSall4OE[0] |= $EpiSC;
#ConversionWithSall4OE[0] |= $TwoiPlusLifCultureConditions;
#ConversionWithSall4OE[0] |= $NoKnockDowns; 
#ConversionWithSall4OE[0] |= $Sall4OverExpression;
#ConversionWithSall4OE[18] |= $TwoiPlusLif;
#ConversionWithSall4OE[19] |= $TwoiPlusLif;

















































































(((not(#SpontaneousConversion[1] |=$TwoiPlusLif)) and #SpontaneousConversion[2] |= $TwoiPlusLif and #SpontaneousConversion[3] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[1] |= $TwoiPlusLif)) and #ConversionWithSall4OE[2] |= $TwoiPlusLif and #ConversionWithSall4OE[3] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[2] |=$TwoiPlusLif)) and #SpontaneousConversion[3] |= $TwoiPlusLif and #SpontaneousConversion[4] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[2] |= $TwoiPlusLif)) and #ConversionWithSall4OE[3] |= $TwoiPlusLif and #ConversionWithSall4OE[4] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[3] |=$TwoiPlusLif)) and #SpontaneousConversion[4] |= $TwoiPlusLif and #SpontaneousConversion[5] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[3] |= $TwoiPlusLif)) and #ConversionWithSall4OE[4] |= $TwoiPlusLif and #ConversionWithSall4OE[5] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[4] |=$TwoiPlusLif)) and #SpontaneousConversion[5] |= $TwoiPlusLif and #SpontaneousConversion[6] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[4] |= $TwoiPlusLif)) and #ConversionWithSall4OE[5] |= $TwoiPlusLif and #ConversionWithSall4OE[6] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[5] |=$TwoiPlusLif)) and #SpontaneousConversion[6] |= $TwoiPlusLif and #SpontaneousConversion[7] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[5] |= $TwoiPlusLif)) and #ConversionWithSall4OE[6] |= $TwoiPlusLif and #ConversionWithSall4OE[7] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[6] |=$TwoiPlusLif)) and #SpontaneousConversion[7] |= $TwoiPlusLif and #SpontaneousConversion[8] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[6] |= $TwoiPlusLif)) and #ConversionWithSall4OE[7] |= $TwoiPlusLif and #ConversionWithSall4OE[8] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[7] |=$TwoiPlusLif)) and #SpontaneousConversion[8] |= $TwoiPlusLif and #SpontaneousConversion[9] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[7] |= $TwoiPlusLif)) and #ConversionWithSall4OE[8] |= $TwoiPlusLif and #ConversionWithSall4OE[9] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[8] |=$TwoiPlusLif)) and #SpontaneousConversion[9] |= $TwoiPlusLif and #SpontaneousConversion[10] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[8] |= $TwoiPlusLif)) and #ConversionWithSall4OE[9] |= $TwoiPlusLif and #ConversionWithSall4OE[10] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[9] |=$TwoiPlusLif)) and #SpontaneousConversion[10] |= $TwoiPlusLif and #SpontaneousConversion[11] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[9] |= $TwoiPlusLif)) and #ConversionWithSall4OE[10] |= $TwoiPlusLif and #ConversionWithSall4OE[11] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[10] |=$TwoiPlusLif)) and #SpontaneousConversion[11] |= $TwoiPlusLif and #SpontaneousConversion[12] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[10] |= $TwoiPlusLif)) and #ConversionWithSall4OE[11] |= $TwoiPlusLif and #ConversionWithSall4OE[12] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[11] |=$TwoiPlusLif)) and #SpontaneousConversion[12] |= $TwoiPlusLif and #SpontaneousConversion[13] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[11] |= $TwoiPlusLif)) and #ConversionWithSall4OE[12] |= $TwoiPlusLif and #ConversionWithSall4OE[13] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[12] |=$TwoiPlusLif)) and #SpontaneousConversion[13] |= $TwoiPlusLif and #SpontaneousConversion[14] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[12] |= $TwoiPlusLif)) and #ConversionWithSall4OE[13] |= $TwoiPlusLif and #ConversionWithSall4OE[14] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[13] |=$TwoiPlusLif)) and #SpontaneousConversion[14] |= $TwoiPlusLif and #SpontaneousConversion[15] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[13] |= $TwoiPlusLif)) and #ConversionWithSall4OE[14] |= $TwoiPlusLif and #ConversionWithSall4OE[15] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[14] |=$TwoiPlusLif)) and #SpontaneousConversion[15] |= $TwoiPlusLif and #SpontaneousConversion[16] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[14] |= $TwoiPlusLif)) and #ConversionWithSall4OE[15] |= $TwoiPlusLif and #ConversionWithSall4OE[16] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[15] |=$TwoiPlusLif)) and #SpontaneousConversion[16] |= $TwoiPlusLif and #SpontaneousConversion[17] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[15] |= $TwoiPlusLif)) and #ConversionWithSall4OE[16] |= $TwoiPlusLif and #ConversionWithSall4OE[17] |= $TwoiPlusLif) or ((not(#SpontaneousConversion[16] |= $TwoiPlusLif)) and #SpontaneousConversion[17] |= $TwoiPlusLif and #SpontaneousConversion[18] |= $TwoiPlusLif and (not(#ConversionWithSall4OE[16] |= $TwoiPlusLif)) and #ConversionWithSall4OE[17] |= $TwoiPlusLif and #ConversionWithSall4OE[18] |= $TwoiPlusLif));

//
// Klf4 SKD
//

#Klf4KD[0] |= $EpiSCWithKlf4KD;
#Klf4KD[0] |= $TwoiPlusLifCultureConditions;
#Klf4KD[0] |= $Klf4GeneKnockdown; 
#Klf4KD[0] |= $NoOverExpression;
#Klf4KD[18] |= $TwoiPlusLifWithKlf4KD;
#Klf4KD[19] |= $TwoiPlusLifWithKlf4KD;

// 
// Klftwo SKD
//

#KlftwoKD[0] |= $EpiSCWithKlftwoKD;
#KlftwoKD[0] |= $TwoiPlusLifCultureConditions;
#KlftwoKD[0] |= $KlftwoGeneKnockDown; 
#KlftwoKD[0] |= $NoOverExpression;
not(#KlftwoKD[18] |= $TwoiPlusLifWithKlftwoKD);

//
// Tfcptwol1 SKD
//
#Tfcptwol1KD[0] |= $EpiSCWithTfcptwol1KD;
#Tfcptwol1KD[0] |= $TwoiPlusLifCultureConditions;
#Tfcptwol1KD[0] |= $Tfcptwol1GeneKnockDown; 
#Tfcptwol1KD[0] |= $NoOverExpression;
#Tfcptwol1KD[18] |= $TwoiPlusLifWithTfcptwol1KD;
#Tfcptwol1KD[19] |= $TwoiPlusLifWithTfcptwol1KD;

 
//
// Specifying the factors that are potent when overexpressed
//

// Klftwo more efficient than twoiLIF alone
#ConversionWithKlftwoOE[0] |= $EpiSCWithKlftwoOE;
#ConversionWithKlftwoOE[0] |= $TwoiPlusLifCultureConditions;
#ConversionWithKlftwoOE[0] |= $NoKnockDowns; 
#ConversionWithKlftwoOE[0] |= $KlftwoOverExpression;
#ConversionWithKlftwoOE[18] |= $TwoiPlusLif;
#ConversionWithKlftwoOE[19] |= $TwoiPlusLif;

((#ConversionWithKlftwoOE[1] |= $TwoiPlusLif and #ConversionWithKlftwoOE[2] |= $TwoiPlusLif and (not(#SpontaneousConversion[1] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[2] |= $TwoiPlusLif and #ConversionWithKlftwoOE[3] |= $TwoiPlusLif and (not(#SpontaneousConversion[2] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[3] |= $TwoiPlusLif and #ConversionWithKlftwoOE[4] |= $TwoiPlusLif and (not(#SpontaneousConversion[3] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[4] |= $TwoiPlusLif and #ConversionWithKlftwoOE[5] |= $TwoiPlusLif and (not(#SpontaneousConversion[4] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[5] |= $TwoiPlusLif and #ConversionWithKlftwoOE[6] |= $TwoiPlusLif and (not(#SpontaneousConversion[5] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[6] |= $TwoiPlusLif and #ConversionWithKlftwoOE[7] |= $TwoiPlusLif and (not(#SpontaneousConversion[6] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[7] |= $TwoiPlusLif and #ConversionWithKlftwoOE[8] |= $TwoiPlusLif and (not(#SpontaneousConversion[7] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[8] |= $TwoiPlusLif and #ConversionWithKlftwoOE[9] |= $TwoiPlusLif and (not(#SpontaneousConversion[8] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[9] |= $TwoiPlusLif and #ConversionWithKlftwoOE[10] |= $TwoiPlusLif and (not(#SpontaneousConversion[9] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[10] |= $TwoiPlusLif and #ConversionWithKlftwoOE[11] |= $TwoiPlusLif and (not(#SpontaneousConversion[10] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[11] |= $TwoiPlusLif and #ConversionWithKlftwoOE[12] |= $TwoiPlusLif and (not(#SpontaneousConversion[11] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[12] |= $TwoiPlusLif and #ConversionWithKlftwoOE[13] |= $TwoiPlusLif and (not(#SpontaneousConversion[12] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[13] |= $TwoiPlusLif and #ConversionWithKlftwoOE[14] |= $TwoiPlusLif and (not(#SpontaneousConversion[13] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[14] |= $TwoiPlusLif and #ConversionWithKlftwoOE[15] |= $TwoiPlusLif and (not(#SpontaneousConversion[14] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[15] |= $TwoiPlusLif and #ConversionWithKlftwoOE[16] |= $TwoiPlusLif and (not(#SpontaneousConversion[15] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[16] |= $TwoiPlusLif and #ConversionWithKlftwoOE[17] |= $TwoiPlusLif and (not(#SpontaneousConversion[16] |= $TwoiPlusLif))) or (#ConversionWithKlftwoOE[17] |= $TwoiPlusLif and #ConversionWithKlftwoOE[18] |= $TwoiPlusLif and (not(#SpontaneousConversion[17] |= $TwoiPlusLif))));

// Klf4 more efficient than twoiLIF alone
#ConversionWithKlf4OE[0] |= $EpiSCWithKlf4OE;
#ConversionWithKlf4OE[0] |= $TwoiPlusLifCultureConditions;
#ConversionWithKlf4OE[0] |= $NoKnockDowns; 
#ConversionWithKlf4OE[0] |= $Klf4OverExpression;
#ConversionWithKlf4OE[18] |= $TwoiPlusLif;
#ConversionWithKlf4OE[19] |= $TwoiPlusLif;

((#ConversionWithKlf4OE[1] |= $TwoiPlusLif and #ConversionWithKlf4OE[2] |= $TwoiPlusLif and (not(#SpontaneousConversion[1] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[2] |= $TwoiPlusLif and #ConversionWithKlf4OE[3] |= $TwoiPlusLif and (not(#SpontaneousConversion[2] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[3] |= $TwoiPlusLif and #ConversionWithKlf4OE[4] |= $TwoiPlusLif and (not(#SpontaneousConversion[3] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[4] |= $TwoiPlusLif and #ConversionWithKlf4OE[5] |= $TwoiPlusLif and (not(#SpontaneousConversion[4] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[5] |= $TwoiPlusLif and #ConversionWithKlf4OE[6] |= $TwoiPlusLif and (not(#SpontaneousConversion[5] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[6] |= $TwoiPlusLif and #ConversionWithKlf4OE[7] |= $TwoiPlusLif and (not(#SpontaneousConversion[6] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[7] |= $TwoiPlusLif and #ConversionWithKlf4OE[8] |= $TwoiPlusLif and (not(#SpontaneousConversion[7] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[8] |= $TwoiPlusLif and #ConversionWithKlf4OE[9] |= $TwoiPlusLif and (not(#SpontaneousConversion[8] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[9] |= $TwoiPlusLif and #ConversionWithKlf4OE[10] |= $TwoiPlusLif and (not(#SpontaneousConversion[9] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[10] |= $TwoiPlusLif and #ConversionWithKlf4OE[11] |= $TwoiPlusLif and (not(#SpontaneousConversion[10] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[11] |= $TwoiPlusLif and #ConversionWithKlf4OE[12] |= $TwoiPlusLif and (not(#SpontaneousConversion[11] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[12] |= $TwoiPlusLif and #ConversionWithKlf4OE[13] |= $TwoiPlusLif and (not(#SpontaneousConversion[12] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[13] |= $TwoiPlusLif and #ConversionWithKlf4OE[14] |= $TwoiPlusLif and (not(#SpontaneousConversion[13] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[14] |= $TwoiPlusLif and #ConversionWithKlf4OE[15] |= $TwoiPlusLif and (not(#SpontaneousConversion[14] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[15] |= $TwoiPlusLif and #ConversionWithKlf4OE[16] |= $TwoiPlusLif and (not(#SpontaneousConversion[15] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[16] |= $TwoiPlusLif and #ConversionWithKlf4OE[17] |= $TwoiPlusLif and (not(#SpontaneousConversion[16] |= $TwoiPlusLif))) or (#ConversionWithKlf4OE[17] |= $TwoiPlusLif and #ConversionWithKlf4OE[18] |= $TwoiPlusLif and (not(#SpontaneousConversion[17] |= $TwoiPlusLif))));

// Esrrb more efficient than twoiLIF alone
#ConversionWithEsrrbOE[0] |= $EpiSCWithEsrrbOE;
#ConversionWithEsrrbOE[0] |= $TwoiPlusLifCultureConditions;
#ConversionWithEsrrbOE[0] |= $NoKnockDowns; 
#ConversionWithEsrrbOE[0] |= $EsrrbGeneOverExpression;
#ConversionWithEsrrbOE[18] |= $TwoiPlusLif;
#ConversionWithEsrrbOE[19] |= $TwoiPlusLif;

((#ConversionWithEsrrbOE[1] |= $TwoiPlusLif and #ConversionWithEsrrbOE[2] |= $TwoiPlusLif and (not(#SpontaneousConversion[1] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[2] |= $TwoiPlusLif and #ConversionWithEsrrbOE[3] |= $TwoiPlusLif and (not(#SpontaneousConversion[2] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[3] |= $TwoiPlusLif and #ConversionWithEsrrbOE[4] |= $TwoiPlusLif and (not(#SpontaneousConversion[3] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[4] |= $TwoiPlusLif and #ConversionWithEsrrbOE[5] |= $TwoiPlusLif and (not(#SpontaneousConversion[4] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[5] |= $TwoiPlusLif and #ConversionWithEsrrbOE[6] |= $TwoiPlusLif and (not(#SpontaneousConversion[5] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[6] |= $TwoiPlusLif and #ConversionWithEsrrbOE[7] |= $TwoiPlusLif and (not(#SpontaneousConversion[6] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[7] |= $TwoiPlusLif and #ConversionWithEsrrbOE[8] |= $TwoiPlusLif and (not(#SpontaneousConversion[7] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[8] |= $TwoiPlusLif and #ConversionWithEsrrbOE[9] |= $TwoiPlusLif and (not(#SpontaneousConversion[8] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[9] |= $TwoiPlusLif and #ConversionWithEsrrbOE[10] |= $TwoiPlusLif and (not(#SpontaneousConversion[9] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[10] |= $TwoiPlusLif and #ConversionWithEsrrbOE[11] |= $TwoiPlusLif and (not(#SpontaneousConversion[10] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[11] |= $TwoiPlusLif and #ConversionWithEsrrbOE[12] |= $TwoiPlusLif and (not(#SpontaneousConversion[11] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[12] |= $TwoiPlusLif and #ConversionWithEsrrbOE[13] |= $TwoiPlusLif and (not(#SpontaneousConversion[12] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[13] |= $TwoiPlusLif and #ConversionWithEsrrbOE[14] |= $TwoiPlusLif and (not(#SpontaneousConversion[13] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[14] |= $TwoiPlusLif and #ConversionWithEsrrbOE[15] |= $TwoiPlusLif and (not(#SpontaneousConversion[14] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[15] |= $TwoiPlusLif and #ConversionWithEsrrbOE[16] |= $TwoiPlusLif and (not(#SpontaneousConversion[15] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[16] |= $TwoiPlusLif and #ConversionWithEsrrbOE[17] |= $TwoiPlusLif and (not(#SpontaneousConversion[16] |= $TwoiPlusLif))) or (#ConversionWithEsrrbOE[17] |= $TwoiPlusLif and #ConversionWithEsrrbOE[18] |= $TwoiPlusLif and (not(#SpontaneousConversion[17] |= $TwoiPlusLif))));

// Tbx3 more efficient than twoiLIF alone
#ConversionWithTbx3OE[0] |= $EpiSCWithTbx3OE;
#ConversionWithTbx3OE[0] |= $TwoiPlusLifCultureConditions;
#ConversionWithTbx3OE[0] |= $NoKnockDowns; 
#ConversionWithTbx3OE[0] |= $Tbx3OverExpression;
#ConversionWithTbx3OE[18] |= $TwoiPlusLif;
#ConversionWithTbx3OE[19] |= $TwoiPlusLif;

((#ConversionWithTbx3OE[1] |= $TwoiPlusLif and #ConversionWithTbx3OE[2] |= $TwoiPlusLif and (not(#SpontaneousConversion[1] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[2] |= $TwoiPlusLif and #ConversionWithTbx3OE[3] |= $TwoiPlusLif and (not(#SpontaneousConversion[2] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[3] |= $TwoiPlusLif and #ConversionWithTbx3OE[4] |= $TwoiPlusLif and (not(#SpontaneousConversion[3] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[4] |= $TwoiPlusLif and #ConversionWithTbx3OE[5] |= $TwoiPlusLif and (not(#SpontaneousConversion[4] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[5] |= $TwoiPlusLif and #ConversionWithTbx3OE[6] |= $TwoiPlusLif and (not(#SpontaneousConversion[5] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[6] |= $TwoiPlusLif and #ConversionWithTbx3OE[7] |= $TwoiPlusLif and (not(#SpontaneousConversion[6] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[7] |= $TwoiPlusLif and #ConversionWithTbx3OE[8] |= $TwoiPlusLif and (not(#SpontaneousConversion[7] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[8] |= $TwoiPlusLif and #ConversionWithTbx3OE[9] |= $TwoiPlusLif and (not(#SpontaneousConversion[8] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[9] |= $TwoiPlusLif and #ConversionWithTbx3OE[10] |= $TwoiPlusLif and (not(#SpontaneousConversion[9] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[10] |= $TwoiPlusLif and #ConversionWithTbx3OE[11] |= $TwoiPlusLif and (not(#SpontaneousConversion[10] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[11] |= $TwoiPlusLif and #ConversionWithTbx3OE[12] |= $TwoiPlusLif and (not(#SpontaneousConversion[11] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[12] |= $TwoiPlusLif and #ConversionWithTbx3OE[13] |= $TwoiPlusLif and (not(#SpontaneousConversion[12] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[13] |= $TwoiPlusLif and #ConversionWithTbx3OE[14] |= $TwoiPlusLif and (not(#SpontaneousConversion[13] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[14] |= $TwoiPlusLif and #ConversionWithTbx3OE[15] |= $TwoiPlusLif and (not(#SpontaneousConversion[14] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[15] |= $TwoiPlusLif and #ConversionWithTbx3OE[16] |= $TwoiPlusLif and (not(#SpontaneousConversion[15] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[16] |= $TwoiPlusLif and #ConversionWithTbx3OE[17] |= $TwoiPlusLif and (not(#SpontaneousConversion[16] |= $TwoiPlusLif))) or (#ConversionWithTbx3OE[17] |= $TwoiPlusLif and #ConversionWithTbx3OE[18] |= $TwoiPlusLif and (not(#SpontaneousConversion[17] |= $TwoiPlusLif))));























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

$JustPdCultureConditions :=
{
 LIF = 0 and
 CH = 0 and 
 PD = 1
};

// Knock downs and overexpressions

$NoKnockDowns :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Oct4GeneKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 1 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$SoxtwoGeneKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 1 and
 KO(Tcf3) = 0
};

$Stat3GeneKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 1 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$EsrrbGeneKnockDown :=
{
 KO(Esrrb) = 1 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$NanogKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 1 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$GbxtwoNanogDKD :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 1 and
 KO(Nanog) = 1 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$TfcpKlftwoDKD :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 1 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 1 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Sall4KlftwoDKD :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 1 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 1 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Klf4EsrrbDKD :=
{
 KO(Esrrb) = 1 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 1 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Klf4NanogDKD :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 1 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 1 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$GbxtwoTfcpDKD :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 1 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 1 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Klf4GeneKnockdown := 
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 1 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0 and
 KO(Oct4) = 0 
};

$KlftwoGeneKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 1 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$Tfcptwol1GeneKnockDown :=
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 1 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};


$NoOverExpression :=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=0 and 
 FE(Klf4)=0 and
 FE(Klftwo)=0 and
 FE(Tbx3)=0 and 
 FE(Sall4)=0 and
 FE(Gbxtwo)=0 and
 FE(Nanog)=0 and
 FE(Stat3)=0 and
 FE(Oct4)=0 and
 FE(Soxtwo)=0 and
 FE(Tcf3)=0
};

$EsrrbGeneOverExpression :=
{
 FE(Esrrb)=1 and
 FE(Tfcptwol1)=0 and 
 FE(Klf4)=0 and
 FE(Klftwo)=0 and
 FE(Tbx3)=0 and 
 FE(Sall4)=0 and
 FE(Gbxtwo)=0 and
 FE(Nanog)=0 and
 FE(Stat3)=0 and
 FE(Oct4)=0 and
 FE(Soxtwo)=0 and
 FE(Tcf3)=0
};

$Tfcptwol1GeneOverExpression:=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=1 and 
 FE(Klf4)=0 and
 FE(Klftwo)=0 and
 FE(Tbx3)=0 and 
 FE(Sall4)=0 and
 FE(Gbxtwo)=0 and
 FE(Nanog)=0 and
 FE(Stat3)=0 and
 FE(Oct4)=0 and
 FE(Soxtwo)=0 and
 FE(Tcf3)=0
};


$Klf4OverExpression:=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=0 and 
 FE(Klf4)=1 and
 FE(Klftwo)=0 and
 FE(Tbx3)=0 and 
 FE(Sall4)=0 and
 FE(Gbxtwo)=0 and
 FE(Nanog)=0 and
 FE(Stat3)=0 and
 FE(Oct4)=0 and
 FE(Soxtwo)=0 and
 FE(Tcf3)=0
};

$YamanakaOverExpression:=
{
 FE(Esrrb)=0 and
 FE(Tfcptwol1)=0 and 
 FE(Klf4)=1 and
 FE(Klftwo)=0 and
 FE(Tbx3)=0 and 
 FE(Sall4)=0 and
 FE(Gbxtwo)=0 and
 FE(Nanog)=0 and
 FE(Stat3)=0 and
 FE(Oct4)=1 and
 FE(Soxtwo)=1 and
 FE(Tcf3)=0
};

$Sall4OverExpression := 
{
 FE(Esrrb) = 0 and
 FE(Tfcptwol1) = 0 and
 FE(Klf4) = 0 and
 FE(Klftwo) = 0 and
 FE(Tbx3) = 0 and
 FE(Sall4) = 1 and
 FE(Gbxtwo) = 0 and
 FE(Nanog) = 0 and
 FE(Stat3) = 0 and
 FE(Soxtwo) = 0 and
 FE(Oct4) = 0 and
 FE(Tcf3) = 0
};

$Tbx3OverExpression := 
{
 FE(Esrrb) = 0 and
 FE(Tfcptwol1) = 0 and
 FE(Klf4) = 0 and
 FE(Klftwo) = 0 and
 FE(Tbx3) = 1 and
 FE(Sall4) = 0 and
 FE(Gbxtwo) = 0 and
 FE(Nanog) = 0 and
 FE(Stat3) = 0 and
 FE(Soxtwo) = 0 and
 FE(Oct4) = 0 and
 FE(Tcf3) = 0
 };

$KlftwoOverExpression := 
{
 FE(Esrrb) = 0 and
 FE(Tfcptwol1) = 0 and
 FE(Klf4) = 0 and
 FE(Klftwo) = 1 and
 FE(Tbx3) = 0 and
 FE(Sall4) = 0 and
 FE(Gbxtwo) = 0 and
 FE(Nanog) = 0 and
 FE(Stat3) = 0 and
 FE(Soxtwo) = 0 and
 FE(Oct4) = 0 and
 FE(Tcf3) = 0
 };


// Gene expression levels patterns under each culture condition 

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

$Oct4AndSoxtwoMaintained :=
{
 Oct4 = 1 and 
 Soxtwo = 1
};

$Klf4Sall4DKD := 
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 1 and
 KO(Klftwo) = 0 and
 KO(Tbx3) = 0 and
 KO(Sall4) = 1 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$TwoiPlusLifWithKlf4Sall4DKD := 
{
Esrrb = 1 and
Tfcptwol1 = 1 and
Klf4 = 0 and
Klftwo = 1 and
Tbx3 = 1 and
Sall4 = 0 and
Gbxtwo = 1 and
Nanog = 1 and
Stat3 = 1 and
Oct4 = 1 and
Soxtwo = 1 and
Tcf3 = 0 and
MEKERK = 0
};

$KlftwoTbx3DKD := 
{
 KO(Esrrb) = 0 and
 KO(Tfcptwol1) = 0 and
 KO(Klf4) = 0 and
 KO(Klftwo) = 1 and
 KO(Tbx3) = 1 and
 KO(Sall4) = 0 and
 KO(Gbxtwo) = 0 and
 KO(Nanog) = 0 and
 KO(Stat3) = 0 and
 KO(Oct4) = 0 and
 KO(Soxtwo) = 0 and
 KO(Tcf3) = 0
};

$TwoiPlusLifWithKlftwoTbx3DKD := 
{
Esrrb = 1 and
Tfcptwol1 = 1 and
Klf4 = 1 and
Klftwo = 0 and
Tbx3 = 0 and
Sall4 = 1 and
Gbxtwo = 1 and
Nanog = 1 and
Stat3 = 1 and
Oct4 = 1 and
Soxtwo = 1 and
Tcf3 = 0 and
MEKERK = 0
};

$EpiSC :=
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=1 and
 Sall4=1 and
 Stat3=0 
};

$ZeroInitialConditions:=
{
 MEKERK = 1 and
 Oct4=0 and
 Soxtwo=0 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=0 and
 Sall4=0 and
 Stat3=0
};

$EpiConversionState:=
{
 Nanog=1 and
 Oct4=1 and 
 Soxtwo=1 and
 Tbx3=1 and 
 Esrrb=1 and
 Tfcptwol1=1
};

$NanogKOEpiSC:=
{
 MEKERK = 1 and
 Oct4=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=1 and
 Stat3=0 
};

$LifPlusChWithNanogKO:=
{
 MEKERK = 1 and
 Oct4=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Stat3=1 
};

$preIPSState:=
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=0 and
 Sall4=0 and
 Stat3=1 
};

// Everything expressed but not constraining Tcf3 or MEKERK
$EverythingExpressed:=
{
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Gbxtwo=1 and
 Tbx3=1 and
 Sall4=1 and
 Stat3=1 
};


$TwoiPlusLifWithGbxtwoNanogDKD :=
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

$TwoiPlusLifWithTfcpKlftwoDKD :=
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

$TwoiPlusLifWithSall4KlftwoDKD :=
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

$TwoiPlusLifWithKlf4EsrrbDKD :=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$TwoiPlusLifWithKlf4NanogDKD :=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=0 and
 Gbxtwo=1 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$TwoiPlusLifWithGbxtwoTfcpDKD :=
{
 MEKERK = 0 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=0 and
 Sall4=1 and
 Stat3=1
};

$AtLeastTwoiWithKlf4OE:=
{
 Oct4=1 and
 Soxtwo=1 and
 Nanog=1 and
 Esrrb=1 and
 Klftwo=1 and
 Tfcptwol1=1 and
 Klf4=1 and
 Tbx3=1 and
 Sall4=1
};



$EpiSCWithKlftwoOE := 
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=1 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=1 and
 Sall4=1 and
 Stat3=0 
};

$EpiSCWithKlf4OE := 
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=1 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=1 and
 Sall4=1 and
 Stat3=0 
};

$EpiSCWithEsrrbOE := 
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=1 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=0 and
 Tcf3=1 and
 Sall4=1 and
 Stat3=0 
};

$EpiSCWithTbx3OE := 
{
 MEKERK = 1 and
 Oct4=1 and
 Soxtwo=1 and
 Nanog=0 and
 Esrrb=0 and
 Klftwo=0 and
 Tfcptwol1=0 and
 Klf4=0 and
 Gbxtwo=0 and
 Tbx3=1 and
 Tcf3=1 and
 Sall4=1 and
 Stat3=0 
};

$EpiSCWithKlf4KD := 
{
Esrrb = 0 and
Tfcptwol1 = 0 and
Klf4 = 0 and
Klftwo = 0 and
Tbx3 = 0 and
Sall4 = 1 and
Gbxtwo = 0 and
Nanog = 0 and
Stat3 = 0 and
Soxtwo = 1 and
Tcf3 = 1 and
Oct4 = 1 and
MEKERK = 1
};

$TwoiPlusLifWithKlf4KD := 
{
Esrrb = 1 and
Tfcptwol1 = 1 and
Klf4 = 0 and
Klftwo = 1 and
Tbx3 = 1 and
Sall4 = 1 and
Gbxtwo = 1 and
Nanog = 1 and
Stat3 = 1 and
Soxtwo = 1 and
Tcf3 = 0 and
Oct4 = 1 and
MEKERK = 0
};

$EpiSCWithKlftwoKD := 
{
Esrrb = 0 and
Tfcptwol1 = 0 and
Klf4 = 0 and
Klftwo = 0 and
Tbx3 = 0 and
Sall4 = 1 and
Gbxtwo = 0 and
Nanog = 0 and
Stat3 = 0 and
Soxtwo = 1 and
Tcf3 = 1 and
Oct4 = 1 and
MEKERK = 1
};

$TwoiPlusLifWithKlftwoKD := 
{
Esrrb = 1 and
Tfcptwol1 = 1 and
Klf4 = 1 and
Klftwo = 0 and
Tbx3 = 1 and
Sall4 = 1 and
Gbxtwo = 1 and
Nanog = 1 and
Stat3 = 1 and
Soxtwo = 1 and
Tcf3 = 0 and
Oct4 = 1 and
MEKERK = 0
};

$EpiSCWithTfcptwol1KD :=
{
Esrrb = 0 and
Tfcptwol1 = 0 and
Klf4 = 0 and
Klftwo = 0 and
Tbx3 = 0 and
Sall4 = 1 and
Gbxtwo = 0 and
Nanog = 0 and
Stat3 = 0 and
Soxtwo = 1 and
Tcf3 = 1 and
Oct4 = 1 and
MEKERK = 1
};


$TwoiPlusLifWithTfcptwol1KD :=
{
Esrrb = 1 and
Tfcptwol1 = 0 and
Klf4 = 1 and
Klftwo = 1 and
Tbx3 = 1 and
Sall4 = 1 and
Gbxtwo = 1 and
Nanog = 1 and
Stat3 = 1 and
Soxtwo = 1 and
Tcf3 = 0 and
Oct4 = 1 and
MEKERK = 0
};
