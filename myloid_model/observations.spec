//gene expression patterns from Krumsiek et al (2011)
$Erythrocyte := {
 GATA2	= 0 and
 GATA1	= 1 and
 FOG1	= 1 and
 EKLF	= 1 and
 Fli1	= 0 and
 SCL	= 1 and
 CEBPa	= 0 and
 PU1	= 0 and
 cjun	= 0 and
 EgrNab = 0 and
 Gfi1	= 0
};


$Megakaryocyte := {
 GATA2	= 0 and
 GATA1	= 1 and
 FOG1 	= 1 and
 EKLF	= 0 and
 Fli1	= 1 and
 SCL	= 1 and
 CEBPa	= 0 and
 PU1	= 0 and
 cjun	= 0 and
 EgrNab = 0 and
 Gfi1	= 0
};


$Monocyte := {
 GATA2	= 0 and
 GATA1	= 0 and
 FOG1 	= 0 and
 EKLF	= 0 and
 Fli1	= 0 and
 SCL	= 0 and
 CEBPa	= 1 and
 PU1	= 1 and
 cjun	= 1 and
 EgrNab = 1 and
 Gfi1	= 0
};


$Granulocyte := {
 GATA2	= 0 and
 GATA1	= 0 and
 FOG1 	= 0 and
 EKLF	= 0 and
 Fli1	= 0 and
 SCL	= 0 and
 CEBPa	= 1 and
 PU1	= 1 and
 cjun	= 0 and
 EgrNab = 0 and
 Gfi1	= 1
};

$Progenitor := {
 GATA2	= 1 and
 GATA1	= 0 and
 FOG1 	= 0 and
 EKLF	= 0 and
 Fli1	= 0 and
 SCL	= 0 and
 CEBPa	= 1 and
 PU1	= 1 and
 cjun	= 0 and
 EgrNab = 0 and
 Gfi1	= 0
};


//experimental observations

#ErythrocyteDiff[0] |= $Progenitor; 
#ErythrocyteDiff[20] |= $Erythrocyte;
fixpoint(#ErythrocyteDiff[20])
"A progenitor cell differentiates into an erythrocyte and stabilizes";

#MegakaryocyteDiff[0] |= $Progenitor; 
#MegakaryocyteDiff[20] |= $Megakaryocyte; 
fixpoint(#MegakaryocyteDiff[20])
"A progenitor cell differentiates into a megakaryocyte and stabilizes";

#MonocyteDiff[0] |= $Progenitor; 
#MonocyteDiff[20] |= $Monocyte;
fixpoint(#MonocyteDiff[20])
"A progenitor cell differentiates into a monocyte and stabilizes";

#GranulocyteDiff[0] |= $Progenitor; 
#GranulocyteDiff[20] |= $Granulocyte;
fixpoint(#GranulocyteDiff[20])
"A progenitor cell differentiates into a granulocyte and stabilizes";