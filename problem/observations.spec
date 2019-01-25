// Experiment One from twoi plus LIF to twoi
#ExperimentOne[0] |=  $TwoiPlusLif "Exp1 initial expression pattern";
#ExperimentOne[0] |=  $TwoiCultureConditions "Exp1 culture conditions";
#ExperimentOne[0] |=  $NoKnockDowns "Exp1 no knockdowns"; 
#ExperimentOne[0] |=  $NoOverExpression "Exp1 no overexpression";
#ExperimentOne[18] |=  $Twoi "Exp1 penultimate state";
#ExperimentOne[19] |=  $Twoi "Exp1 final state";








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
