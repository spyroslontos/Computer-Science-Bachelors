%Spyros Lontos
%C1722325

function varargout = coursework(varargin)
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @coursework_OpeningFcn, ...
                   'gui_OutputFcn',  @coursework_OutputFcn, ...
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

% --- Executes just before coursework is made visible.
function coursework_OpeningFcn(hObject, eventdata, handles, varargin)
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);
% Clearing command window for error checking
clc;

% --- Outputs from this function are returned to the command line.
function varargout = coursework_OutputFcn(hObject, eventdata, handles) 
varargout{1} = handles.output;

% ----------------- Buttons ----------------- %

% --- Button to Draw Rectangle --- %
function Rectanglebtn_Callback(hObject, eventdata, handles)

% -------- Reference ----------- % 
%https://github.com/con220/CM2104-Computational-Maths/blob/master/coursework1.m

% Check if rectangle handle exists, if not it creates one
if ~isfield(handles,'rectangleHandles')
    handles.rectangleHandles = [];
end

%Stores the drawn rectangle in the handle
rectangle = drawrectangle('InteractionsAllowed','none','Color','k','FaceAlpha',0,'LineWidth',1);
handles.rectangleHandles = [handles.rectangleHandles ; rectangle];

%Updates Object/Handles Values%
guidata(hObject,handles);

% --- Button to Find Circles --- %
function Findcirclesbtn_Callback(hObject, eventdata, handles)

% For looping all the rectangles stored in the handle
for i = 1:length(handles.rectangleHandles)

    % Gets position and stores half legth and half width
    position = handles.rectangleHandles(i).Position;
    a = position(3)/2; % length / 2
    b = position(4)/2; % height / 2

    % Finds center of rectangle i.e.(0,0)
    centerx = position(1) + a;
    centery = position(2) + b;

    % Uses equations given to find h and k
    h = ((a-b)*(a+b+sqrt(a^2 + 6*a*b + b^2)))/(a-b+sqrt(a^2 + 6*a*b + b^2));
    k = ((a-b)*(a+3*b+sqrt(a^2 + 6*a*b + b^2)))/(4*b);

    %If statement to find orientation(horizontal, vertical) of rectangle
    if a > b
        % --- Reference --- %
        %https://uk.mathworks.com/matlabcentral/answers/400526-how-to-plot-circle-by-one-single-equation

        hold on;
        % --- Right Circle  --- %
        Rightr=a-h;
        Rightx=centerx + h;
        Righty=centery;
        th = 0:pi/200:2*pi;
        Rightxunit = Rightr * cos(th) + Rightx;
        Rightyunit = Rightr * sin(th) + Righty;
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
        
        hold off;
    else
        disp('Square entered, no quadrarcs or oval produced') 
    end
end

% --- Button to Find Quadrarcs --- %
function Findquadrarcsbtn_Callback(hObject, eventdata, handles)

% For looping all the rectangles stored in the handle
for i = 1:length(handles.rectangleHandles)

    % Gets position and stores half legth and half width
    position = handles.rectangleHandles(i).Position;
    a = position(3)/2; % length / 2
    b = position(4)/2; % height / 2

    % Finds center of rectangle i.e.(0,0)
    centerx = position(1) + a;
    centery = position(2) + b;

    % Uses equations given to find h and k
    h = ((a-b)*(a+b+sqrt(a^2 + 6*a*b + b^2)))/(a-b+sqrt(a^2 + 6*a*b + b^2));
    k = ((a-b)*(a+3*b+sqrt(a^2 + 6*a*b + b^2)))/(4*b);

    %If statement to find orientation(horizontal, vertical) of rectangle
    if a > b

        hold on;
        % --- Right Circle  --- %
        Rightr=a-h;
        Rightx=centerx + h;
        Righty=centery;

        % --- Left Circle  --- %
        Leftr=a-h;
        Leftx=centerx - h;
        Lefty=centery;

         % --- Top Circle  --- %
        Topr=k+b;
        Topx=centerx;
        Topy=centery+k;
 
        % --- Bottom Circle  --- %
        Bottomr=k+b;
        Bottomx=centerx;
        Bottomy=centery-k;

        %----------- Intercections ---------------------%
        
        % -------- Reference ----------- %
        %https://uk.mathworks.com/help/map/ref/circcirc.html
        
        [TopRightxInt,TopRightyInt] = circcirc(Bottomx,Bottomy,Bottomr,Rightx,Righty,Rightr);
        
        [BottomRightxInt,BottomRightyInt] = circcirc(Topx,Topy,Topr,Rightx,Righty,Rightr);
        
        [BottomLeftxInt,BottomLeftyInt] = circcirc(Topx,Topy,Topr,Leftx,Lefty,Leftr);
        
        [TopLeftxInt,TopLeftyInt] = circcirc(Bottomx,Bottomy,Bottomr,Leftx,Lefty,Leftr);

        % -------- Plotting Quadrarcs --------- %
        
        % -------- Reference ----------- %
        %https://uk.mathworks.com/matlabcentral/answers/367126-plot-an-arc-on-a-2d-grid-by-given-radius-and-end-points
        
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
        plot(x,y,'y-','LineWidth',3)
        
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
        plot(x,y,'r-','LineWidth',3)
        
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
        plot(x,y,'g-','LineWidth',3)
        
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
        plot(x,y,'b-','LineWidth',3)
        
        hold off;
        
    elseif b > a

        hold on;
        % --- Left Circle  --- %
        Leftr=a-h;
        Leftx=centerx + h;
        Lefty=centery;

        % --- Right Circle  --- %
        Rightr=a-h;
        Rightx=centerx - h;
        Righty=centery;

         % --- Bottom Circle  --- %
        Bottomr=k+b;
        Bottomx=centerx;
        Bottomy=centery+k;

        
        % --- Top Circle  --- %
        Topr=k+b;
        Topx=centerx;
        Topy=centery-k;

        %----------- Intercections ---------------------%
        
        % -------- Reference ----------- %
        %https://uk.mathworks.com/help/map/ref/circcirc.html
        
        [TopRightxInt,TopRightyInt] = circcirc(Topx,Topy,Topr,Leftx,Lefty,Leftr);

        [BottomRightxInt,BottomRightyInt] = circcirc(Bottomx,Bottomy,Bottomr,Leftx,Lefty,Leftr);
       
        [BottomLeftxInt,BottomLeftyInt] = circcirc(Bottomx,Bottomy,Bottomr,Rightx,Righty,Rightr);
        
        [TopLeftxInt,TopLeftyInt] = circcirc(Topx,Topy,Topr,Rightx,Righty,Rightr);
        
        % -------- Plotting Quadrarcs --------- %

        % -------- Reference ----------- %
        %https://uk.mathworks.com/matlabcentral/answers/367126-plot-an-arc-on-a-2d-grid-by-given-radius-and-end-points
        
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
        plot(x,y,'y-','LineWidth',3)
        
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
        plot(x,y,'r-','LineWidth',3)
        
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
        plot(x,y,'g-','LineWidth',3)
        
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
        plot(x,y,'b-','LineWidth',3)
        
        hold off;
    else
        disp('Square entered, no quadrarcs or oval produced') 
    end
end

% --- Button to Clear Axes/Handle --- %
function Clearnbtn_Callback(hObject, eventdata, handles)

%Clears all the rectangles from handle
for r = 1:length(handles.rectangleHandles)
    delete(handles.rectangleHandles(1));
    handles.rectangleHandles(1) = [];
end
% Clears axes and updates handle
cla;
guidata(hObject,handles);

% ----------------- Push Tools ----------------- %

% --- UI Push Tool to Open Figure --- %
function OpenPushTool_ClickedCallback(hObject, eventdata, handles)

clear;
[filename] = uigetfile('*.fig', 'Open Figure');
close(gcf);
openfig(filename);

% --- UI Push Tool to Save Figure --- %
function SavePushTool_ClickedCallback(hObject, eventdata, handles)

[filename] = uiputfile('*.fig', 'Save Figure As');
savefig(filename);
