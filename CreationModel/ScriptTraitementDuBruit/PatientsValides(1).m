
function [patients_valides_part] = PatientsValides(Part)

%% methode 6sec : heart rate=nombre de piques en 6sec *10
count=1;
%% patient contient heart rate pour chaque patient
patients=zeros(1, 'double');
Rpique= zeros(1, 'double');
for j=1:1:3000
x=Part{j} (3,:);
%% l'interval est de 0.02 sec / point => 6sec=300points 
%% on compte le nombre de piques en 1minute(400points)
k=2;
while k<300 && k<size(x,2)
 if (x(k-1)<x(k) && x(k)>x(k+1) )
     if  0.59<x(k) && x(k)<1.1
         Rpique(count)=x(k);
         count=count+1;
     end 
 end
k=k+1;
end
patients(1,j)=j;
patients(2,j)=count*10;


count=1;
end

sum(patients==10)
%% patients valides, on filtre le bruit
patients_valides_part=zeros(1, 'double');

compteur=1;
for k=1:1:size(patients,2)
    if patients(2,k)>30 &&  patients(2,k)<120
        patients_valides_part(1,compteur)=patients(1,k);
        patients_valides_part(2,compteur)=patients(2,k);
        compteur=compteur+1;
    end
end

%%%plot(patient);
end
