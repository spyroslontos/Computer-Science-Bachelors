import math

h = float(input("Enter in Height(cm)"))
M = float(input("Enter in Mass(kg)"))

m = M 
h = h / 100.0
V = M / 1000
r = math.sqrt(float(V/(h*math.pi)))

waist_line = 2*math.pi*r

waist_line_inch = 100 * waist_line * 0.39

print (waist_line_inch)