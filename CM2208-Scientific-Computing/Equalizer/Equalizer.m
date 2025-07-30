%Spyros Lontos
%C1722325

function varargout = Equalizer(varargin)
% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Equalizer_OpeningFcn, ...
                   'gui_OutputFcn',  @Equalizer_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT

% --- Executes just before Equalizer is made visible.
function Equalizer_OpeningFcn(hObject, eventdata, handles, varargin)

% Choose default command line output for Equalizer
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);
% Clearing command window for error checking
clc;
% Setting values displayed
set(handles.valueVolume, 'String',50);
set(handles.value1, 'String',0);
set(handles.value2, 'String',0);
set(handles.value3, 'String',0);
set(handles.value4, 'String',0);
set(handles.value5, 'String',0);
set(handles.value6, 'String',0);
set(handles.value7, 'String',0);
set(handles.value8, 'String',0);
set(handles.value9, 'String',0);
set(handles.value10, 'String',0);
set(handles.saveaudio, 'Enable', 'off');

% --- Outputs from this function are returned to the command line.
function varargout = Equalizer_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;

% -------------- Browse Button -------------- %

function browse_Callback(hObject, eventdata, handles)

% Retrieves the filename and path
[filename, pathname] = uigetfile({'*.wav;*.flac;*.mp3;*.m4a;*.aac'}, 'Select sound file');

% Checks if action has been canceled or if file is selected 
if isequal(filename,0)
    disp('User selected Cancel')
else
    handles.fullpathname = strcat(pathname, filename);
    set(handles.address, 'String', handles.fullpathname);
    
    % Stores the y and Fs in handles
    [handles.y, handles.Fs] = audioread(handles.fullpathname);
    % Plots the spectrum in the axes
    plotspectrum(handles.y, handles.axesSpectrum, handles.Fs);
    % Enables saving functionality
    set(handles.saveaudio, 'Enable', 'on');
    
    guidata(hObject, handles);
end

% -------------- Cross Synthesis -------------- %
% https://uk.mathworks.com/matlabcentral/fileexchange/66200-cross-synthesis-example-with-matlab-implementation?s_tid=prof_contriblnk

% --- Executes on button press in crosssynth.
function crosssynth_Callback(hObject, eventdata, handles)
% Deletes the instance of the window and opens the CrossSynthesizer window
delete(Equalizer);
CrossSynthesizer;

% ----------------- Multi-Band Sliders ----------------- %
% On every slider callback their corresponding static text field is updated
% And sets the preset dropdown back to manual

% --- Executes on slider movement.
function slider1_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value1, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider1_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider2_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value2, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider2_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider3_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value3, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider3_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider4_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value4, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider4_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider5_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value5, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider5_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider6_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value6, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider6_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider7_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value7, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider7_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider8_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value8, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider8_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider9_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value9, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider9_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on slider movement.
function slider10_Callback(hObject, eventdata, handles)
value = get(hObject,'Value');
set(handles.value10, 'String',round(value,2));
set(handles.presets, 'Value', 1);

% --- Executes during object creation, after setting all properties.
function slider10_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --------- Equalizer Player --------- %
% https://drive.google.com/drive/folders/1ywRB3kXF26d1rjhrpyIzsylXc43RJcNa

function [yEQ, Fs] = play_equalizer(hObject, handles)
global player;
% Loads the handles/values that will be used
[handles.y,handles.Fs] = audioread(handles.fullpathname);
handles.vol = get(handles.volume,'value');
handles.s1=get(handles.slider1,'value');
handles.s2=get(handles.slider2,'value');
handles.s3=get(handles.slider3,'value');
handles.s4=get(handles.slider4,'value');
handles.s5=get(handles.slider5,'value');
handles.s6=get(handles.slider6,'value');
handles.s7=get(handles.slider7,'value');
handles.s8=get(handles.slider8,'value');
handles.s9=get(handles.slider9,'value');
handles.s10=get(handles.slider10,'value');

% Adds the filters to specific frequency points
% http://ant-s4.unibw-hamburg.de/dafx/DAFX_Book_Page_2nd_edition/matlab.html
% MATLAB for Chapter 2

% Lowpass filter
y1 = lowshelving(handles.y, (2*32)/handles.Fs, handles.s1);

% Peak filter
y2 = peakfilt(y1, (2*64)/handles.Fs, (2*32)/handles.Fs, handles.s2);
y3 = peakfilt(y2, (2*125)/handles.Fs, (2*125)/handles.Fs, handles.s3);
y4 = peakfilt(y3, (2*250)/handles.Fs, (2*125)/handles.Fs, handles.s4);
y5 = peakfilt(y4, (2*500)/handles.Fs, (2*250)/handles.Fs, handles.s5);
y6 = peakfilt(y5, (2*1000)/handles.Fs, (2*500)/handles.Fs, handles.s6);
y7 = peakfilt(y6, (2*2000)/handles.Fs, (2*1000)/handles.Fs, handles.s7);
y8 = peakfilt(y7, (2*4000)/handles.Fs, (2*2000)/handles.Fs, handles.s8);
y9 = peakfilt(y8, (2*8000)/handles.Fs, (2*4000)/handles.Fs, handles.s9);

% Highpass filter
y10 = highshelving(y9, (2*16000)/handles.Fs, handles.s10);

% Adding effects
effectsy = y10;

% Checks the checkboxes to add the corresponding effect

if get(handles.wahbox, 'Value')
    effectsy = wah_wah(effectsy, handles.Fs);
end
if get(handles.flangerbox, 'Value')
    effectsy = flanger(effectsy, handles.Fs);
end
if get(handles.ringbox, 'Value')
    effectsy = ring_mod(effectsy, handles.Fs);
end
if get(handles.pitchbox, 'Value')
    handles.Fs = pitch(effectsy, handles.Fs);
end

yEQ = handles.vol*effectsy;
Fs = handles.Fs;

% Plots the spectrum and plays the sound
plotspectrum(yEQ, handles.axesSpectrum, Fs)
player = audioplayer(yEQ, Fs);

guidata(hObject,handles);

% ----------------- Controls ----------------- %

% --- Executes on button press in play.
function play_Callback(hObject, eventdata, handles)
global player;

% Checks if sound has been loaded 
if ~isfield(handles,'fullpathname')
    disp('No sound file selected')
else
    % Plays the equalized sound
    play_equalizer(hObject, handles); 
    play(player);    
    guidata(hObject,handles)
end

% --- Executes on button press in stop.
function stop_Callback(hObject, eventdata, handles)
global player;

% Checks if sound has been loaded 
if ~isfield(handles,'fullpathname')
    disp('No sound file selected')
else
    % Stops the sound player
    stop(player);
end

% --- Executes on slider movement.
function volume_Callback(hObject, eventdata, handles)
% Updates the static text box with the appropriate new value
value = get(hObject,'Value');
set(handles.valueVolume, 'String',round(value*100,2));

% --- Executes during object creation, after setting all properties.
function volume_CreateFcn(hObject, eventdata, handles)
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end

% --- Executes on button press in reset.
function reset_Callback(hObject, eventdata, handles)
% Resets the slider values and text boxes to 0
set(handles.slider1,'value',0);
set(handles.slider2,'value',0);
set(handles.slider3,'value',0);
set(handles.slider4,'value',0);
set(handles.slider5,'value',0);
set(handles.slider6,'value',0);
set(handles.slider7,'value',0);
set(handles.slider8,'value',0);
set(handles.slider9,'value',0);
set(handles.slider10,'value',0);
set(handles.value1, 'String',0);
set(handles.value2, 'String',0);
set(handles.value3, 'String',0);
set(handles.value4, 'String',0);
set(handles.value5, 'String',0);
set(handles.value6, 'String',0);
set(handles.value7, 'String',0);
set(handles.value8, 'String',0);
set(handles.value9, 'String',0);
set(handles.value10, 'String',0);

% Resets the drop-down preset to manual
set(handles.presets, 'Value', 1);

% --- Executes on button press in saveaudio.
function saveaudio_Callback(hObject, eventdata, handles)
% Retrieves the filename and pathname the file will be saved at 
[filename, pathname] = uiputfile({'*.wav';'*.flac';'*.m4a;'}, 'Save Sound File As');

% Checks if action is canceled
if isequal(filename,0)
    disp('User selected Cancel')
else
    handles.savefullpathname = strcat(pathname, filename);
    
    [yEQ, Fs] = play_equalizer(hObject, handles);
    % Saves the equalized audio using audiowrite
    audiowrite(handles.savefullpathname, yEQ, round(Fs));

    guidata(hObject, handles);
end 

% ----------------- Presets ----------------- %

% --- Executes on selection change in presets.
function presets_Callback(hObject, eventdata, handles)
contents = cellstr(get(hObject,'String'));
presetchoice = contents{get(hObject,'Value')};

% Checks for drop-down choice and updates sliders and text boxes
% With corresponding values
if (strcmp(presetchoice,'Manual'))

elseif (strcmp(presetchoice,'Classical'))
    s1 = 4.7;
    s2 = 4;
    s3 = 3;
    s4 = 2.8;
    s5 = 1.2;
    s6 = 1.1;
    s7 = 0;
    s8 = 2.5;
    s9 = 3.2;
    s10 = 4;
    set(handles.slider1,'value',s1);
    set(handles.slider2,'value',s2);
    set(handles.slider3,'value',s3);
    set(handles.slider4,'value',s4);
    set(handles.slider5,'value',s5);
    set(handles.slider6,'value',s6);
    set(handles.slider7,'value',s7);
    set(handles.slider8,'value',s8);
    set(handles.slider9,'value',s9);
    set(handles.slider10,'value',s10);
    set(handles.value1, 'String',s1);
    set(handles.value2, 'String',s2);
    set(handles.value3, 'String',s3);
    set(handles.value4, 'String',s4);
    set(handles.value5, 'String',s5);
    set(handles.value6, 'String',s6);
    set(handles.value7, 'String',s7);
    set(handles.value8, 'String',s8);
    set(handles.value9, 'String',s9);
    set(handles.value10, 'String',s10);
    
elseif (strcmp(presetchoice,'Dance'))
    s1 = 4;
    s2 = 7;
    s3 = 5;
    s4 = 0;
    s5 = 2;
    s6 = 4;
    s7 = 5;
    s8 = 4.5;
    s9 = 4;
    s10 = 0;
    set(handles.slider1,'value',s1);
    set(handles.slider2,'value',s2);
    set(handles.slider3,'value',s3);
    set(handles.slider4,'value',s4);
    set(handles.slider5,'value',s5);
    set(handles.slider6,'value',s6);
    set(handles.slider7,'value',s7);
    set(handles.slider8,'value',s8);
    set(handles.slider9,'value',s9);
    set(handles.slider10,'value',s10);
    set(handles.value1, 'String',s1);
    set(handles.value2, 'String',s2);
    set(handles.value3, 'String',s3);
    set(handles.value4, 'String',s4);
    set(handles.value5, 'String',s5);
    set(handles.value6, 'String',s6);
    set(handles.value7, 'String',s7);
    set(handles.value8, 'String',s8);
    set(handles.value9, 'String',s9);
    set(handles.value10, 'String',s10);
    
elseif (strcmp(presetchoice,'R&B'))
    s1 = 3;
    s2 = 7;
    s3 = 6;
    s4 = 1.5;
    s5 = -2;
    s6 = -1.5;
    s7 = 2;
    s8 = 3;
    s9 = 3;
    s10 = 4;
    set(handles.slider1,'value',s1);
    set(handles.slider2,'value',s2);
    set(handles.slider3,'value',s3);
    set(handles.slider4,'value',s4);
    set(handles.slider5,'value',s5);
    set(handles.slider6,'value',s6);
    set(handles.slider7,'value',s7);
    set(handles.slider8,'value',s8);
    set(handles.slider9,'value',s9);
    set(handles.slider10,'value',s10);
    set(handles.value1, 'String',s1);
    set(handles.value2, 'String',s2);
    set(handles.value3, 'String',s3);
    set(handles.value4, 'String',s4);
    set(handles.value5, 'String',s5);
    set(handles.value6, 'String',s6);
    set(handles.value7, 'String',s7);
    set(handles.value8, 'String',s8);
    set(handles.value9, 'String',s9);
    set(handles.value10, 'String',s10)
    
elseif (strcmp(presetchoice,'Rock'))
    s1 = 5;
    s2 = 4;
    s3 = 3;
    s4 = 1.5;
    s5 = 0;
    s6 = -1;
    s7 = 1;
    s8 = 3;
    s9 = 4;
    s10 = 4.5;
    set(handles.slider1,'value',s1);
    set(handles.slider2,'value',s2);
    set(handles.slider3,'value',s3);
    set(handles.slider4,'value',s4);
    set(handles.slider5,'value',s5);
    set(handles.slider6,'value',s6);
    set(handles.slider7,'value',s7);
    set(handles.slider8,'value',s8);
    set(handles.slider9,'value',s9);
    set(handles.slider10,'value',s10);
    set(handles.value1, 'String',s1);
    set(handles.value2, 'String',s2);
    set(handles.value3, 'String',s3);
    set(handles.value4, 'String',s4);
    set(handles.value5, 'String',s5);
    set(handles.value6, 'String',s6);
    set(handles.value7, 'String',s7);
    set(handles.value8, 'String',s8);
    set(handles.value9, 'String',s9);
    set(handles.value10, 'String',s10)
    
elseif (strcmp(presetchoice,'Vocal Booster'))
    s1 = -1.5;
    s2 = -3;
    s3 = -3;
    s4 = 1.5;
    s5 = 4;
    s6 = 4;
    s7 = 3;
    s8 = 1.5;
    s9 = 0;
    s10 = -1.5;
    set(handles.slider1,'value',s1);
    set(handles.slider2,'value',s2);
    set(handles.slider3,'value',s3);
    set(handles.slider4,'value',s4);
    set(handles.slider5,'value',s5);
    set(handles.slider6,'value',s6);
    set(handles.slider7,'value',s7);
    set(handles.slider8,'value',s8);
    set(handles.slider9,'value',s9);
    set(handles.slider10,'value',s10);
    set(handles.value1, 'String',s1);
    set(handles.value2, 'String',s2);
    set(handles.value3, 'String',s3);
    set(handles.value4, 'String',s4);
    set(handles.value5, 'String',s5);
    set(handles.value6, 'String',s6);
    set(handles.value7, 'String',s7);
    set(handles.value8, 'String',s8);
    set(handles.value9, 'String',s9);
    set(handles.value10, 'String',s10)
end

% --- Executes during object creation, after setting all properties.
function presets_CreateFcn(hObject, eventdata, handles)
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
      
% ----------------- Effects ----------------- %
% http://users.cs.cf.ac.uk/Dave.Marshall/Multimedia/PDF/07_Audio_Effects.pdf

% --- Executes on button press in wahradio.
function wahbox_Callback(hObject, eventdata, handles)
% Clears other check boxes selections
set(handles.flangerbox, 'Value',0);
set(handles.ringbox, 'Value',0);
set(handles.pitchbox, 'Value',0);

% --- Executes on button press in flangerradio.
function flangerbox_Callback(hObject, eventdata, handles)
% Clears other check boxes selections
set(handles.wahbox, 'Value',0);
set(handles.ringbox, 'Value',0);
set(handles.pitchbox, 'Value',0);

% --- Executes on button press in ringradio.
function ringbox_Callback(hObject, eventdata, handles)
% Clears other check boxes selections
set(handles.wahbox, 'Value',0);
set(handles.flangerbox, 'Value',0);
set(handles.pitchbox, 'Value',0);

% --- Executes on button press in pitchbox.
function pitchbox_Callback(hObject, eventdata, handles)
% Clears other check boxes selections
set(handles.wahbox, 'Value',0);
set(handles.flangerbox, 'Value',0);
set(handles.ringbox, 'Value',0);

% --- Executes on button press in clearcheckboxes.
function clearcheckboxes_Callback(hObject, eventdata, handles)
% Clears any check boxes selections
set(handles.wahbox, 'Value',0);
set(handles.flangerbox, 'Value',0);
set(handles.ringbox, 'Value',0);
set(handles.pitchbox, 'Value',0);

% ----------------- Plotting ----------------- %
% https://github.com/lucasrodes/simple-equalizer-matlab/blob/a5fccb351cd44708b22409c84a21db5507d2e2a9/Functions/EQUALIZER1.m

function plotspectrum(y, plotSpectrum, Fs)
N = 2^14;
k = 0:N-1;
f = (Fs/N).*k;
X = fft(y,N);

% Plots the logarithmic spectrum graph
axes(plotSpectrum);
semilogx(f(1:round(20000/(Fs/N))),20*log(abs(X(1:round(20000/(Fs/N))))), 'b');

xlim([10 20000]);
grid on;

% ----------------- Closing ----------------- %
   
% --- Executes when user attempts to close figure1.
function figure1_CloseRequestFcn(hObject, eventdata, handles)

%Stopping the music before closing the figure
global player;
if ~isfield(handles,'fullpathname')
    delete(hObject);
else
    stop(player);
end
delete(hObject);
