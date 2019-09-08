
%% fonction qui retourne les 2 Outputs
function [output] = get_output(Patient)

abp=Patient(2,:);
output=zeros(1,'double');

% ABP Systolique est le pic de l'abp
i=1;
indice_abp_systo=1;
abp_systolique =abp(1);
while i<80
    if abp(i)>abp_systolique
     abp_systolique=abp(i);
     indice_abp_systo=i;
    end
    i=i+1;
end
abp_systolique= round(abp_systolique);

% ABP diastolique min de l'abp
i=1;
abp_diastolique=abp_systolique;

while i<indice_abp_systo
   if abp(i)<abp_diastolique
        abp_diastolique=abp(i);
        indice_abp_diasto=i;
    end
   i= i+1;
end

 abp_diastolique=round( abp_diastolique);
 output(1)=abp_systolique/100;
 output(2)=abp_diastolique/100;
 


end