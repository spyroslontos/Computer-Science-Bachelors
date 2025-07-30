"""
a = list(range(9,-1,-1))
print (a)
print(len(a))
print(sum(a))
print(sum(a[0:5]))
print(min(a))
print(max(a[::3]))
print(a[::-1])
"""
import statistics

"""
b = [9, 3, 5, 4, 10, 4, 3, 2, 4]
print(statistics.mean(b))
print(statistics.median(b))
print(statistics.mode(b))
"""

for char in "1bc4":
    if char.isalpha()==True:
        print("True")
    elif char.isdigit()==True:
        print("False")

for char in "1bc4":
    if char.isalpha()==True:
        print(char.upper())
    elif char.isdigit()==True:
        print("not a letter")