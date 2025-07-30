import random
myinv = ["gun","knife","pen"]

player = {'name':'Sheldor','health':100,'mana':250,'experience':0,'alive':True,'inventory':myinv,}

def print_player(player):
    for i in player:

        print(i, "is" ,player[i])

damage = int(input("Enter damage dealt on Character: "))

def compute_experience():

    xp = random.randrange(0,damage*2)
    print(xp)



if "dict" == raw_input("Enter (dict) to open dictionary: "):

    print_player(player)

elif "dmg"== raw_input("Enter (dmg) to see damage dealt: "):

    compute_experience()