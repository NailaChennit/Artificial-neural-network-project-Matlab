
%% Fonction qui retourne les indices  determinent pour le calcule des attributt ainsi que la valeur des attribut 2;3;4;5;6

function [attribut,patients_indice_important]= Indices_Importants_patient (patientcorrect)
%xx=zeros(1,'double');

%% les patients qui ont une frequence cardiaque valide
patients_indice_important=zeros(8, 'double');

ecg=patientcorrect(3,:);
ppg=patientcorrect(1,:);

x=ppg(1:300);
y=ecg(1:300);
%plot(x);
%% trouver le premier pic d'ecg
k=1;

%je tire le max dans l'interval ca me donnera le pic
interval=1;
indice_pic_ecg=k;
pic=ecg(k);
while  k<interval+50
    if ecg(k)> pic 
        pic= ecg(k);
        indice_pic_ecg=k;
    end
k=k+1;   
end
%---------------------------------
% A partir de k on trouve le pic de ppg
interval=1;
pic=0;
while k< interval+100
    if ppg(k)> pic 
        pic= ppg(k);
        indice_pic_ppg=k;
    end
k=k+1;
end

%indice_pic_ppg=k;
%----------------------------------------

%trouver le minimun du cycle ppg 
k=indice_pic_ecg;
min=4;

while k<indice_pic_ppg
    if ppg(k)<min 
       min=ppg(k);
       indice_min=k;
    end
    k=k+1;
end

indice_inflexion1=round( (indice_min + indice_pic_ppg)/2 );

% trouver indice fin cycle ppg
k=indice_pic_ppg;
indice_ppg_pic2=k;
pic2=0;
while k<indice_pic_ppg+90
    if ppg(k)>pic2 
       pic2=ppg(k);
       indice_ppg_pic2=k;
    end
    k=k+1;
end
pic_ppg=ppg(indice_pic_ppg);
while  k<size(ppg,2) && ppg(k)<( pic_ppg - pic_ppg*0.2)
    k=k+1;
end

while k<size(ppg,2) && ppg(k)<ppg(k+1)
    k=k+1;
end
k=indice_pic_ppg;
end_cycle=4; indice_end=k;
while   k<indice_ppg_pic2
   if ppg(k)<end_cycle
       end_cycle=ppg(k);
       indice_end=k;   
   end
   k=k+1;
end

indice_inflexion2=indice_pic_ppg+ round((indice_end - indice_pic_ppg)*3/8);


%%%Calcule de la FC
count=1;
k=2;
while k<300 && k<size(ecg,2)
 if (ecg(k-1)<ecg(k) && ecg(k)>ecg(k+1) )
     if  0.59<ecg(k) && ecg(k)<1.1
         Rpique(count)=ecg(k);
         count=count+1;
     end 
 end 
k=k+1;
end

attribut(1)=count*10;




patients_indice_important(1)=indice_pic_ecg;
patients_indice_important(2)=indice_min;
patients_indice_important(3)=indice_inflexion1;
patients_indice_important(4)=indice_pic_ppg;
patients_indice_important(5)=indice_inflexion2;
patients_indice_important(6)=indice_end;

attribut(2)=(indice_min - indice_pic_ecg)*0.02;
attribut(3)=(indice_end - indice_pic_ecg)*0.02;
attribut(4)=(indice_inflexion1 - indice_pic_ecg)*0.02;
attribut(5)=(indice_pic_ppg - indice_pic_ecg)*0.02;
attribut(6)=(indice_inflexion2 - indice_pic_ppg)*0.02;

end
