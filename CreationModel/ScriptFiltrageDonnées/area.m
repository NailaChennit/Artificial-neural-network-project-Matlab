
%%Fonction pour les attribut 7,8,9,10

function [att8,att9,att10,att11] = area(tableauindex,PPG)
    
%%calcule de l air sous la courbe


point1=tableauindex(1);
point2=tableauindex(2);
j=1;
for i=point1:1:point2
x(1,j)=i;
y(1,j)=PPG(i);
j=j+1;
end
air=trapz(x,y);
att8=air;

point1=tableauindex(2);
point2=tableauindex(3);
j=1;
for i=point1:1:point2
x(1,j)=i;
y(1,j)=PPG(i);
j=j+1;
end
air=trapz(x,y);
att9=air;

point1=tableauindex(3);
point2=tableauindex(4);
j=1;
for i=point1:1:point2
x(1,j)=i;
y(1,j)=PPG(i);
j=j+1;
end
air=trapz(x,y);
att10=air;

point1=tableauindex(4);
point2=tableauindex(5);
j=1;
for i=point1:1:point2
x(1,j)=i;
y(1,j)=PPG(i);
j=j+1;
end
air=trapz(x,y);
att11=air;

end