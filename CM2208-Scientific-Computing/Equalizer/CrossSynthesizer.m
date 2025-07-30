%Spyros Lontos
%C1722325

function varargout = CrossSynthesizer(varargin)
% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @CrossSynthesizer_OpeningFcn, ...
                   'gui_OutputFcn',  @CrossSynthesizer_OutputFcn, ...
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

% --- Executes just before CrossSynthesizer is made visible.
function CrossSynthesizer_OpeningFcn(hObject, eventdata, handles, varargin)

% Choose default command line output for CrossSynthesizer
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);
% Clearing command window for error checking
clc;

% --- Outputs from this function are returned to the command line.
function varargout = CrossSynthesizer_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;

% -------------- Browse Carrier Button -------------- %

% --- Executes on button press in browsecarrier.
function browsecarrier_Callback(hObject, eventdata, handles)

% Retrieves the filename and path
[filename, pathname] = uigetfile({'*.wav;*.flac;*.mp3;*.m4a;*.aac'}, 'Select sound file', 'C:\Users\plays\Desktop\SoundTestings\');

% Checks if action has been canceled or if file is selected 
if isequal(filename,0)
    disp('User selected Cancel')
else
    handles.carrierfullpathname = strcat(pathname, filename);
    set(handles.carrieraddress, 'String', handles.carrierfullpathname);
    
    % Stores the y and Fs in handles
    [handles.carriery, handles.carrierFs] = audioread(handles.carrierfullpathname);
    
    guidata(hObject, handles);
end

% -------------- Browse Modulating Button -------------- %

% --- Executes on button press in browsemodulating.
function browsemodulating_Callback(hObject, eventdata, handles)

% Retrieves the filename and path
[filename, pathname] = uigetfile({'*.wav;*.flac;*.mp3;*.m4a;*.aac'}, 'Select sound file', 'C:\Users\plays\Desktop\SoundTestings\');

% Checks if action has been canceled or if file is selected 
if isequal(filename,0)
    disp('User selected Cancel')
else
    handles.modulatingfullpathname = strcat(pathname, filename);
    set(handles.modulatingaddress, 'String', handles.modulatingfullpathname);
        
    % Stores the y and Fs in handles
    [handles.modulatingy, handles.modulatingFs] = audioread(handles.modulatingfullpathname);
    
    guidata(hObject, handles);
end

% ---------- Synthesizer Player ---------- %

function [ySynth, Fssynth] = play_synthesizer(hObject, handles)
global player;

% Loads of all of the handles being used
carriery = handles.carriery;
carrierFs = handles.carrierFs;

modulatingy = handles.modulatingy;
modulatingFs = handles.modulatingFs;

% Cross synthesizes the sounds using the synth function
[ySynth, Fssynth] = synth(carriery, carrierFs, modulatingy, modulatingFs);

player = audioplayer(ySynth, Fssynth);
guidata(hObject,handles);

% -------------- Controls -------------- %

% --- Executes on button press in crosssynth.
function crosssynth_Callback(hObject, eventdata, handles)
global player;

% Checks if sound has been loaded on both sections 
if (~isfield(handles,'carrierfullpathname')) || (~isfield(handles,'modulatingfullpathname'))
    disp('No sound file selected')
else
    % Plays the synthesized sound
    play_synthesizer(hObject, handles); 
    play(player);    
    guidata(hObject,handles)
end

% --- Executes on button press in stop.
function stop_Callback(hObject, eventdata, handles)
global player;

% Checks if sound has been loaded on both sections 
if (~isfield(handles,'carrierfullpathname')) || (~isfield(handles,'modulatingfullpathname'))
    disp('No sound file selected')
else
    % Stops the sound player
    stop(player);
end

% --- Executes on button press in save.
function save_Callback(hObject, eventdata, handles)

% Checks if sound has been loaded on both sections 
if (~isfield(handles,'carrierfullpathname')) || (~isfield(handles,'modulatingfullpathname'))
    disp('No sound file selected')
else
    % Retrieves the filename and pathname the file will be saved at 
    [filename, pathname] = uiputfile({'*.wav';'*.flac';'*.m4a;'}, 'Save Sound File As');

    % Checks if action is canceled 
    if isequal(filename,0)
        disp('User selected Cancel')
    else
    handles.savefullpathname = strcat(pathname, filename);
    
    [ySynth, Fssynth] = play_synthesizer(hObject, handles);
    ySynth = normalize(ySynth, 'range', [-1 1]);
    
    % Saves the synthesized audio using audiowrite
    audiowrite(handles.savefullpathname, ySynth, Fssynth);

    guidata(hObject, handles);
    end
end 

% -------------- Equalizer -------------- %

% --- Executes on button press in equalizer.
function equalizer_Callback(hObject, eventdata, handles)
% Stops the sound from playing and
% Deletes the instance of the window and opens the equalizer window
global player;

% Checks if sound has been loaded on both sections 
if (~isfield(handles,'carrierfullpathname')) || (~isfield(handles,'modulatingfullpathname'))
else
    % Stops the sound player
    stop(player);
end
delete(CrossSynthesizer);
Equalizer;

% ----------------- Closing ----------------- %

% --- Executes when user attempts to close figure1.
function figure1_CloseRequestFcn(hObject, eventdata, handles)

%Stopping the music before closing the figure and oppening the Equalizer
global player;
if (~isfield(handles,'carrierfullpathname')) || (~isfield(handles,'modulatingfullpathname'))
    delete(hObject);
    Equalizer;
else
    stop(player);
end
delete(hObject);
Equalizer;
