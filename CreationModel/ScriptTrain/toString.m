function [nom]= toString(hiddenLayerSize)
for i=1:size(hiddenLayerSize,1)
 nom=int2str(hiddenLayerSize(i,:));
end