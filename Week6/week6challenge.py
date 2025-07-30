off="-"
lights_string = 50*[off]


def string_state(n):

    for bulb in range(n-1, len(lights_string), n):

        if lights_string[bulb]=="-":
            lights_string[bulb]="*"
        elif lights_string[bulb]=="*":
            lights_string[bulb]="-"

    print("Lights state at ", n," is ",*lights_string, sep="")

presses = int(input("Check the lights string after how many presses? : "))

for i in range(1,presses+1):
    string_state(i)