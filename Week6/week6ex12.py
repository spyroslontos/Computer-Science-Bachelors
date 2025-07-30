import sys

for i in range(-20,20):
    for j in range(-20,20):
        if i**2 + j**2 < 20**2:
            sys.stdout.write("*  ")
        else:
            sys.stdout.write("   ")

    print("")