best_perfomance=100;

%%f = fopen('test.txt','w');
%%fprintf(f,'nb_neuronnne  |performance |regression  \n');
%%fclose(f);

f = fopen('test.txt','a');
fprintf(f,'\n');
target=output;
hls=[ 5 5;  10 10 ; 13 13 ; 14 14; 15 15; 20 20 ; 25 15; 30 30 ];
for i=1:size(hls,1)
hiddenLayerSize = hls(i,:);

%%initialisation du réseau avec le nombre de couches défini
net = feedforwardnet( hiddenLayerSize,'trainlm');
%%net.trainParam.epochs=600;
net.trainParam.goal=1e-6;

 ret=15;
 %% appeler la fonction
 [best_perf,r, best_tr, best_net,underOver] = ntrain(net,input,target,ret);

 disp (r);
 %%comparer avec le meilleur net enregistré
if(underOver~=1) 
  
if (best_perfomance>best_perf)
best_perfomance = best_perf;
best_trace = best_tr;
meilleur_net = best_net;
regression=r;
architecture=hiddenLayerSize;


end 
 
%%enregistrer dans le fichier
fprintf(f,'\n');
[nom]= toString(hiddenLayerSize);
 
 disp(r);
 fprintf(f,'%s |  %f | %f \n',nom,best_perf,r);

%%enregistrer les graphes
plotperform(best_tr);
fn=sprintf('%sperf.png',nom);
saveas(gcf,fn);
output=best_net(input);
plotregression(target,output);
fn1=sprintf('%sreg.png',nom);
saveas(gcf,fn1);

end
end

save meilleur_net;
[nom]= toString(architecture);

fnet = fopen('net.txt','w');
fprintf(fnet,'%s\n',nom);

fprintf(fnet,'\n');

fprintf(f,' nb retrain est %d\n',ret);

fclose(fnet);
fclose(f);
