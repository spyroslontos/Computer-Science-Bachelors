%Spyros Lontos
%C1722325

function flangery = flanger(x, Fs)

% current sample is 11kHz so 0-3 ms is 0 - 33 samples
% parameters to vary the effect %

max_time_delay=0.003;   % 3ms max delayin seconds
rate=1;                 %rate of flange in Hz

index=1:length(x);

% sin reference to create oscillating delay
sin_ref = (sin(2*pi*index*(rate/Fs)))';     % sin(2pi*fa/fs);

max_samp_delay=round(max_time_delay*Fs);    %convert delay in ms to max delay in samples

y = zeros(length(x),1);                     % create empty out vector

y(1:max_samp_delay)=x(1:max_samp_delay);    % to avoid referencing of negative samples

amp=0.7; % suggested coefficient from page 71 DAFX

% for each sample
for i = (max_samp_delay+1):length(x)
    cur_sin=abs(sin_ref(i));                    %abs of current sin val 0-1
    cur_delay=ceil(cur_sin*max_samp_delay);     % generate delay from 1-max_samp_delay and ensure whole number  
    y(i) = (amp*x(i)) + amp*(x(i-cur_delay));   % add delayed sample
end

flangery = y;
clearvars -except flangery