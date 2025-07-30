% Spyros Lontos
% C1722325

function varargout = testing(varargin)

gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @testing_OpeningFcn, ...
                   'gui_OutputFcn',  @testing_OutputFcn, ...
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
% End initialization code

% --- Executes just before testing is made visible.
function testing_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);
clc;

% --- Outputs from this function are returned to the command line.
function varargout = testing_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;

% ----------------- CODE ----------------- %

% --- Button to Draw Rectangle --- %
function rectbtn_Callback(hObject, eventdata, handles)

% -------- Reference ----------- % 
%https://github.com/con220/CM2104-Computational-Maths/blob/master/coursework1.m

%check if handle for lines/shapes exists
%if not then create the handle
if ~isfield(handles,'rectangleHandles')
    handles.rectangleHandles = [];
end

%Stores the drawn rectangle in the handle
rectangle = drawrectangle('InteractionsAllowed','none','Color','k','FaceAlpha',0,'LineWidth',1);

handles.rectangleHandles = [handles.rectangleHandles ; rectangle];
%Updates Object/Handles Values%

guidata(hObject,handles);

% --- Button to find Values --- %
function circlesbtn_Callback(hObject, eventdata, handles)

for i = 1:length(handles.rectangleHandles)

    % Gets position and stores importand values in variables
    position = handles.rectangleHandles(i).Position;
    a = position(3)/2; % length / 2
    b = position(4)/2; % height / 2

    % (0,0)
    centerx = position(1) + a;
    centery = position(2) + b;

    h = ((a-b)*(a+b+sqrt(a^2 + 6*a*b + b^2)))/(a-b+sqrt(a^2 + 6*a*b + b^2));
    k = ((a-b)*(a+3*b+sqrt(a^2 + 6*a*b + b^2)))/(4*b);

    if a > b
    
        % --- Reference --- %
        %https://uk.mathworks.com/matlabcentral/answers/400526-how-to-plot-circle-by-one-single-equation%

        hold on;
        
        % --- Right Circle  --- %
        Rightr=a-h;
        Rightx=centerx + h;
        Righty=centery;
        th = 0:pi/200:2*pi;
        Rightxunit = Rightr * cos(th) + Rightx;
        Rightyunit = Rightr * sin(th) + Righty;
        %plot(Rightx, Righty, "r+");
        plot(Rightxunit, Rightyunit);

        % --- Left Circle  --- %
        Leftr=a-h;
        Leftx=centerx - h;
        Lefty=centery;
        th = 0:pi/200:2*pi;
        Leftxunit = Leftr * cos(th) + Leftx;
        Leftyunit = Leftr * sin(th) + Lefty;
        plot(Leftxunit, Leftyunit);

         % --- Top Circle  --- %
        Topr=k+b;
        Topx=centerx;
        Topy=centery+k;
        th = 0:pi/200:2*pi;
        Topxunit = Topr * cos(th) + Topx;
        Topyunit = Topr* sin(th) + Topy;
        %plot(Topx, Topy, "r+");
        plot(Topxunit, Topyunit);
        
        % --- Bottom Circle  --- %
        Bottomr=k+b;
        Bottomx=centerx;
        Bottomy=centery-k;
        th = 0:pi/200:2*pi;
        Bottomxunit = Bottomr * cos(th) + Bottomx;
        Bottomyunit = Bottomr * sin(th) + Bottomy;
        plot(Bottomxunit, Bottomyunit);

        %----------- Intercections ---------------------%

        [TopRightxInt,TopRightyInt] = circcirc(Bottomx,Bottomy,Bottomr,Rightx,Righty,Rightr);
        %plot(TopRightxInt(1),TopRightyInt(1), 'y+');
        
        [BottomRightxInt,BottomRightyInt] = circcirc(Topx,Topy,Topr,Rightx,Righty,Rightr);
        %plot(BottomRightxInt(1),BottomRightyInt(1), 'r+');
        
        [BottomLeftxInt,BottomLeftyInt] = circcirc(Topx,Topy,Topr,Leftx,Lefty,Leftr);
        %plot(BottomLeftxInt(1),BottomLeftyInt(1), 'b+');
        
        [TopLeftxInt,TopLeftyInt] = circcirc(Bottomx,Bottomy,Bottomr,Leftx,Lefty,Leftr);
        %plot(TopLeftxInt(1),TopLeftyInt(1), 'g+');

        % -------- Plotting Quadrarcs --------- %

        % --- Right Quadrarc --- %
        
        A = [BottomRightxInt(1); BottomRightyInt(1)]; % 1st point of Intercection
        B = [TopRightxInt(1); TopRightyInt(1)]; % 2nd point of Intercection
        R = Rightr; % Radius of circle with arc
        C = [Rightx; Righty]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'y-','LineWidth',4)
        
        % --- Bottom Quadrarc --- %
        
        A = [BottomLeftxInt(1); BottomLeftyInt(1)]; % 1st point of Intercection
        B = [BottomRightxInt(1); BottomRightyInt(1)]; % 2nd point of Intercection
        R = Topr; % Radius of circle with arc
        C = [Topx; Topy]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'r-','LineWidth',4)
        
        % --- Left Quadrarc --- %
        
        A = [TopLeftxInt(1); TopLeftyInt(1)]; % 1st point of Intercection
        B = [BottomLeftxInt(1); BottomLeftyInt(1)]; % 2nd point of Intercection
        R = Leftr; % Radius of circle with arc
        C = [Leftx; Lefty]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'g-','LineWidth',4)
        
        % --- Top Quadrarc --- %
        
        A = [TopRightxInt(1); TopRightyInt(1)]; % 1st point of Intercection
        B = [TopLeftxInt(1); TopLeftyInt(1)]; % 2nd point of Intercection
        R = Bottomr; % Radius of circle with arc
        C = [Bottomx; Bottomy]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'b-','LineWidth',4)
        
        hold off;
        
    elseif b > a

        hold on;

        % --- Left Circle  --- %
        Leftr=a-h;
        Leftx=centerx + h;
        Lefty=centery;
        th = 0:pi/200:2*pi;
        Leftxunit = Leftr * cos(th) + Leftx;
        Leftyunit = Leftr * sin(th) + Lefty;
        plot(Leftxunit, Leftyunit);

        % --- Right Circle  --- %
        Rightr=a-h;
        Rightx=centerx - h;
        Righty=centery;
        th = 0:pi/200:2*pi;
        Rightxunit = Rightr * cos(th) + Rightx;
        Rightyunit = Rightr * sin(th) + Righty;
        plot(Rightxunit, Rightyunit);

         % --- Bottom Circle  --- %
        Bottomr=k+b;
        Bottomx=centerx;
        Bottomy=centery+k;
        th = 0:pi/200:2*pi;
        Bottomxunit = Bottomr * cos(th) + Bottomx;
        Bottomyunit = Bottomr * sin(th) + Bottomy;
        plot(Bottomxunit, Bottomyunit);
        
        % --- Top Circle  --- %
        Topr=k+b;
        Topx=centerx;
        Topy=centery-k;
        th = 0:pi/200:2*pi;
        Topxunit = Topr * cos(th) + Topx;
        Topyunit = Topr * sin(th) + Topy;
        plot(Topxunit, Topyunit);
    
        %----------- Intercections ---------------------%

        [TopRightxInt,TopRightyInt] = circcirc(Topx,Topy,Topr,Leftx,Lefty,Leftr);
        %plot(TopRightxInt(1),TopRightyInt(1), 'b+');

        [BottomRightxInt,BottomRightyInt] = circcirc(Bottomx,Bottomy,Bottomr,Leftx,Lefty,Leftr);
        %plot(BottomRightxInt(1),BottomRightyInt(1), 'g+');
       
        [BottomLeftxInt,BottomLeftyInt] = circcirc(Bottomx,Bottomy,Bottomr,Rightx,Righty,Rightr);
        %plot(BottomLeftxInt(1),BottomLeftyInt(1), 'r+');
        
        [TopLeftxInt,TopLeftyInt] = circcirc(Topx,Topy,Topr,Rightx,Righty,Rightr);
        %plot(TopLeftxInt(1),TopLeftyInt(1), 'y+');
        
        % -------- Plotting Quadrarcs --------- %

        % --- Right Quadrarc --- %
        
        A = [BottomRightxInt(1); BottomRightyInt(1)]; % 1st point of Intercection
        B = [TopRightxInt(1); TopRightyInt(1)]; % 2nd point of Intercection
        R = Leftr; % Radius of circle with arc
        C = [Leftx; Lefty]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'y-','LineWidth',4)
        
        % --- Bottom Quadrarc --- %
        
        A = [BottomLeftxInt(1); BottomLeftyInt(1)]; % 1st point of Intercection
        B = [BottomRightxInt(1); BottomRightyInt(1)]; % 2nd point of Intercection
        R = Bottomr; % Radius of circle with arc
        C = [Bottomx; Bottomy]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'r-','LineWidth',4)
        
        % --- Left Quadrarc --- %
        
        A = [TopLeftxInt(1); TopLeftyInt(1)]; % 1st point of Intercection
        B = [BottomLeftxInt(1); BottomLeftyInt(1)]; % 2nd point of Intercection
        R = Rightr; % Radius of circle with arc
        C = [Rightx; Righty]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'g-','LineWidth',4)
        
        % --- Top Quadrarc --- %
        
        A = [TopRightxInt(1); TopRightyInt(1)]; % 1st point of Intercection
        B = [TopLeftxInt(1); TopLeftyInt(1)]; % 2nd point of Intercection
        R = Topr; % Radius of circle with arc
        C = [Topx; Topy]; % Center of circle
        a = atan2(A(2)-C(2),A(1)-C(1));
        b = atan2(B(2)-C(2),B(1)-C(1));
        b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
        t = linspace(a,b,1000);
        x = C(1)+R*cos(t);
        y = C(2)+R*sin(t);
        plot(x,y,'b-','LineWidth',4)
        
        hold off;
    else
        disp('Square entered, no quadrarcs or oval produced') 
    end
end

% --- Button to clear Axes --- %
function clearbtn_Callback(hObject, eventdata, handles)

for i = 1:length(handles.rectangleHandles)
    delete(handles.rectangleHandles(1));
    handles.rectangleHandles(1) = [];
end

% Clears axes and updates handle
cla;
guidata(hObject,handles);

% --- Push Tool to Open Figure --- %
function uipushtool1_ClickedCallback(hObject, eventdata, handles)

clear;
[filename] = uigetfile('*.fig', 'Open Figure');
close(gcf);
openfig(filename);

% --- Push Tool to Save Figure --- %
function uipushtool2_ClickedCallback(hObject, eventdata, handles)

[filename] = uiputfile('*.fig', 'Save Figure As');
savefig(filename);
