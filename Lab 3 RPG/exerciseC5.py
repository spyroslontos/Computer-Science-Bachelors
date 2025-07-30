myinv = ["gun","knife","pen"]

player = {"name":"Sheldor","health":100,"mana":250,"experience":0,"alive":True,"inventory":myinv,}

def print_player(player):
    for i in player:

        print(i, "is" ,player[i])

if "dict" == raw_input("Enter (dict) to open dictionary: "):

    print_player(player)

else:
    print ("Wrong")