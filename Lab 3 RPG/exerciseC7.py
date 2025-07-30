import random
myinv = ["gun","knife","pen"]
player = {'name':'Sheldor','health':100,'mana':250,'experience':0,'alive':True,'inventory':myinv,}

def print_player(player):
    for key,value in player.items():
        print(key,"is",value)

def take_damage():
    player['health'] = player['health'] - damage
    print ("Your health is",player['health'])

    if player['health'] <= 0:
        player['alive']= False
        print ("YOU DIED")

def compute_experience():
    xp = random.randrange(0,damage*2)
    player['experience'] = xp + player['experience']
    print ("Experience is:",player['experience'])

while player['health'] > 0:

    damage = int(input("Enter damage dealt on Character: "))

    take_damage()
    compute_experience()

    while True:
        action = input("Enter an action (stats,dmg taken,xp,hurt,exit): ")

        if "stats" == action:
            print_player(player)
        elif "dmg taken" == action:
            take_damage()
        elif "xp" == action:
            compute_experience()
        elif action == "hurt":
             break;
        elif action == "exit":
             break;
