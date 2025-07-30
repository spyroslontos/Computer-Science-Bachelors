bigr=4;
bigx=5;
bigy=5;
th = 0:pi/200:2*pi;
bigxunit = bigr * cos(th) + bigx;
bigyunit = bigr * sin(th) + bigy;
plot(bigxunit, bigyunit);
hold on;

smallr=2;
smallx=2;
smally=4;
smallxunit = smallr * cos(th) + smallx;
smallyunit = smallr * sin(th) + smally;
plot(smallxunit, smallyunit);

[xout,yout] = circcirc(bigx,bigy,bigr,smallx,smally,smallr);

plot(xout(1), yout(1), 'r*');
plot(xout(2), yout(2), 'r*');

disp(xout(1))
disp(yout(1))

A = [xout(1); yout(1)]; % Point A to be on circle circumference
B = [xout(2); yout(2)]; % Same with point B
d = norm(B-A);
R = bigr; % Choose R radius
C = [bigx; bigy]; % Center of circle
a = atan2(A(2)-C(2),A(1)-C(1));
b = atan2(B(2)-C(2),B(1)-C(1));
b = mod(b-a,2*pi)+a; % Ensure that arc moves counterclockwise
t = linspace(a,b,1000);
x = C(1)+R*cos(t);
y = C(2)+R*sin(t);
plot(x,y,'y-',C(1),C(2),'bo')
axis equal