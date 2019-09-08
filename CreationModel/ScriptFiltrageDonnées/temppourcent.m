%%fonction qui retorne la valeur des attribut 11...19 pour un ptient donné

%%calcul du cumul du ppg
%% calcul du taux entre debut et fin
function [att12,att13,att14,att15,att16,att17,att18,att19,att20] = temppourcent(debut,fin,PPG)
somme=0;
for i=debut:1:fin
somme=somme+PPG(1,i);
end
dixperc=somme*10/100;
temp=0;
sommeperc=0;
for i=debut:1:fin-1
    
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==dixperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<dixperc && somme2>=dixperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att12=temp;


vinperc=somme*20/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==vinperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<vinperc && somme2>=vinperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att13=temp;

trenperc=somme*30/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==trenperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<trenperc && somme2>=trenperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att14=temp;

quarperc=somme*40/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==quarperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<quarperc && somme2>=quarperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att15=temp;

cinqperc=somme*50/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==cinqperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<cinqperc && somme2>=cinqperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att16=temp;

soixperc=somme*60/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==soixperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<soixperc && somme2>=soixperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att17=temp;

septperc=somme*70/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==septperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<septperc && somme2>=septperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att18=temp;

huitperc=somme*80/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==huitperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<huitperc && somme2>=huitperc )
        break;
    end
    
    debut=debut+1;
end
debut=i;
att19=temp;

neufperc=somme*90/100;
for i=debut:1:fin-1
    temp=temp+0.02;
    sommeperc=sommeperc+PPG(debut);
    if(sommeperc==neufperc)
        break;
    end
    somme2=sommeperc+PPG(debut+1);
     if(sommeperc<neufperc && somme2>=neufperc )
        break;
    end
    
    debut=debut+1;
end
att20=temp;
end