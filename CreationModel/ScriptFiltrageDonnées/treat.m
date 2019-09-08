%%script traitement de donnéé
%%MAIN	


output=zeros(2,'double');
input=zeros(19,3300);
for i=1:1:(size(patientcorrect,2)-20)

mat=patientcorrect{i};
PPG=mat(1,:);
[attribut,patients_indice_important]= Indices_Importants_patient (mat);


indice_ordonne(1)=patients_indice_important(2);
indice_ordonne(2)=patients_indice_important(3);
indice_ordonne(3)=patients_indice_important(4);
indice_ordonne(4)=patients_indice_important(5);
indice_ordonne(5)=patients_indice_important(6);



[att8,att9,att10,att11] = area(indice_ordonne,PPG);
[att12,att13,att14,att15,att16,att17,att18,att19,att20] = temppourcent(indice_ordonne(1),indice_ordonne(2),PPG);


 input(:,i)= [attribut(1);attribut(2);attribut(3);attribut(4);attribut(5);attribut(6);att8;att9;att10;att11;att12;att13;att14;att15;att16;att17;att18;att19;att20];
    
 [target1] = get_output(mat);
 
 output(1,i)=target1(1);
 output(2,i)=target1(2);


end
