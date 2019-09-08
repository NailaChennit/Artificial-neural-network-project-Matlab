
function [best_perf,r,best_tr,best_net,underOver] = ntrain(net,input,target,n)
underOver=0;
net = init(net);

[net,tr] = train(net,input,target);

%%% performance des donne de test
tInd = tr.testInd;
testOutput = sim(net,input(:,tInd));
testErrors = target(:,tInd) - testOutput;
testPerform = mse(testErrors);

%%% performance des donne de validation
valInd = tr.valInd;
valdOutput = sim(net,input(:,valInd));
valdErrors = target(:,valInd) - valdOutput;
valdPerform = mse(valdErrors);

if(abs(valdPerform-testPerform)<100)

best_perf = testPerform;
best_tr = tr;
best_net = net;
[r,~,~] = regression(target,net(input));




for i=1:n-1
    disp(i);
    net = init(net);
    [net,tr] = train(net,input,target);
	
    tInd = tr.testInd;
    testOutput = sim(net,input(:,tInd));
    testErrors = target(:,tInd) - testOutput;
    testPerform = mse(testErrors);
	
	%%% performance des donne de validation
	valInd = tr.valInd;
	valdOutput = sim(net,input(:,valInd));
	valdErrors = target(:,valInd) - valdOutput;
	valdPerform = mse(valdErrors);

    if (abs(valdPerform-testPerform)>100) 
        underOver=1; break; 
    end
    
    if(valdPerform< best_perf)
        best_tr = tr;
        best_net = net;
        best_perf = valdPerform;
		[r,~,~] = regression(target,net(input));
    end
   
  
    
end

else
    underOver=1;
 
end


end
