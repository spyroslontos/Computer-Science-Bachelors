%Spyros Lontos
%C1722325

function ringy = ring_mod(x, Fs)

index = 1:length(x);
% Ring Modulate with a sine wave frequency Fc
Fc = 440;

carrier = sin(2*pi*index*(Fc/Fs)); 
ringy = x.*carrier;

clearvars -except ringy
