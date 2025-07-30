# -*- coding: utf-8 -*-
#!/usr/bin/python3
guard_talk = 0
stairs = 0
game_ending = False
guess = 0
from Kleudo_map import rooms
from Kleudo_player import *
from Kleudo_items import *
from Kleudo_parser import *
from Kleudo_characters import *
from Kleudo_objects import *
import winsound
import time
import random
#import os
#os.system('cls') - Clears the screen


def list_of_items(items):

    items_string = ""
    for i in range(len(items)):
        items_string += items[i]["name"]
        if i != (len(items) - 1):
            items_string += ", "

    return items_string
    # This function lists the items

def list_of_objects(objects):
    objects_str = ""
    for i in range(len(objects)):
        objects_str += objects[i]["name"]
        if i != (len(objects) -1):
            objects_str += ", "
    return objects_str
    # This function lists the objects

def list_of_characters(characters):
    char_str = ""
    for i in range(len(characters)):
        char_str += characters[i]["name"]
        if i != (len(characters) -1):
            char_str += ", "
    return char_str
    # This function lists the characters.

def print_room_items(room):

    item = room["items"]
    item_string = ""
    if len(item) != 0 :
        for i in range(len(item)):
            item_string += item[i]["name"]
            if i != (len(item) - 1):
                item_string += ", "

        print ("There is "+item_string+" here.")
        print("")
        #This prints the items inside the room

def print_room_objects(room):
    objects = room["objects"]
    if len(list_of_objects(objects)) != 0:
        print("The objects in this room are " + list_of_objects(objects) + ".")
        print("")
         #This prints the objects inside the room


def print_room_characters(room):
    characters = room["characters"]
    character_list = list_of_characters(characters).split(",")
    if len(character_list)== 1 and len(list_of_characters(characters)) != 0:
        print(list_of_characters(characters) + " is here.")
        print("")
    elif len(character_list)> 1:
        print(list_of_characters(characters) + " are here.")
        print("")
    elif len(list_of_characters(characters)) == 0:
        print("")
         #This prints the characters inside the room


def print_inventory_items(items):

    inv_string = ""
    for i in range(len(items)):
        inv_string += items[i]["name"]
        if i != (len(items) - 1):
            inv_string += ", "
            
    if len(inv_string) !=0:
        print("You have "+inv_string+".")
        print("")
    #Prints the items you currently have in your inventory


def print_room(room):

    # Display room name
    print("")
    print(room["name"].upper())
    print("")
    # Display room description
    print(room["description"])
    print("")
    if print_room_items(room) == True:
        print("")
        print (print_room_items(room))
        print("")
    print_room_objects(room)
    print("")
    print_room_characters(room)



def exit_leads_to(exits, direction):
    return rooms[exits[direction]]["name"]


def print_exit(direction, leads_to):
    print("GO "+direction.upper()+" to go to the "+leads_to+".")
    #prints the possible directions and their locations


def print_menu(exits, room_items, inv_items, room_objects, room_characters):
    print("You can:")

    for direction in exits:
        print_exit(direction, exit_leads_to(exits, direction))
    for item in room_items:
        print("TAKE "+item["id"].upper()+" to take "+item["name"]+".")
    for i in inv_items:
        print("DROP "+i["id"].upper()+" to drop your "+i["id"]+".")
    for items in inv_items:
        print("INSPECT " + items["id"].upper() + " to inspect the " + items["name"] + ".")
    for chars in room_characters:
        print("SPEAK TO " + chars["id"].upper() + " to speak to " + chars["name"] + ".")
    for obj in room_objects:
        print("INSPECT " + obj["id"].upper() + " to inspect the " + obj["name"] + ".")
    print("Additionally:")
    print("You can USE objects")
    print("You can USE items on objects")
    print("You can OPEN objects")
    print("Inspect items and objects for valuable information.")

    print("What do you want to do?")
    #this function prints a list of things that the player can do.


def is_valid_exit(exits, chosen_exit):
    return chosen_exit in exits
    #checks and exit is valid


def execute_go(direction):
    global current_room
    global stairs
    if direction in current_room["exits"]:
        if move(current_room["exits"], direction) == rooms["N/407"] and stairs == 0: # -----------------------------------------
            winsound.PlaySound("music/stairs.wav",winsound.SND_ASYNC | winsound.SND_LOOP)
            print("as you approach N/4.07 you remember the dreaded stairs. with a nervous gulp you begin your ascent.")
            time.sleep(5)
            print("you ascend to the first floor, going strong!")
            time.sleep(5)
            print("you ascend to the second floor, Half way there!")  # This part of the code handles the climbing of the stairs
            time.sleep(5)
            print("you ascend to the third floor, almost there!")
            time.sleep(5)
            print("F I N A L L Y.")
            print("You decide to take the lift from now on.")
            stairs = 1 #---------------------------------------------------------------------------------------------------------
        current_room = move(current_room["exits"], direction)
        winsound.PlaySound(current_room["music"],winsound.SND_ASYNC | winsound.SND_LOOP)
        return current_room

    else:
        print("You cannot go there.")
    #this function, after using the go command checks the directon is valid and then moves there and plays the apropriate music for this room.


def inv_mass(item):
    inv_mass=0
    for i in inventory:
        inv_mass += i["mass"]
    return inv_mass
 #this function counts the mass of the players inventory


def execute_take(item_id):
    item_exists = False
    for item in current_room["items"]:
        if item_id == item["id"]:
            item_exists = True
            if max_mass > inv_mass(item) + item["mass"]:
                current_room["items"].remove(item)
                inventory.append(item)
                print(item["name"] + " added to inventory.")
            else:
                print("You carry too much weight.")
    if not item_exists:
        print("You cannot take that.")
    #this function handles taking items


def execute_drop(item_id):
    item_exists = False
    for item in inventory:
        if item_id == item["id"]:
            item_exists = True
            inventory.remove(item)
            current_room["items"].append(item)
            print(item["name"] + " removed from inventory.")
    if not item_exists:
        print("You cannot drop that.")
        #this function handles dropping items


def execute_inspect(item_id):
    global inventory
    global current_room
    items = inventory
    objects = current_room["objects"]
    found = False
    for item in items:
        if item["id"] == item_id:
            found = True
            break
    if found:
        print(item["inspect"])
    if found == False:
        obj_found = False
        for object_ in objects:
            if object_["id"] == item_id:
                obj_found = True
                break
        if obj_found:
            print(object_["inspect"])
        else:
            print("You cannot inspect that.")
        #this function allows you to inspect items and objects by looking for "inspect" in their respective dictonaries

def execute_speak(char_id):
    global current_room
    global guard_talk
    characters = current_room["characters"]
    found = False
    for char in characters:
        if char["id"] == char_id:
            found = True
            break
    if found:
        print("")
        if (char_id == "guard") and (item_chocolate in inventory) and guard_talk == 0: # This part of the speak command checks if the player is speaking to the guard and if they have chocolate
            print("""
            .-----------------------------------------------------------------------.
            | Ayo youngen, I see you got yourself some chocolates there, I do.      |
            | would you like to swap it for my trusty torch youngen?                |
            |									    |
            .------     ------------------------------------------------------------.
                   \   /
                    \ /
                     .
            
            """)
            print("")
            answer = normalise_input(input("Y/N: ")) #After priting the dialogue it asks the player if they would like to trade
            if answer == "y" or "yes":
                print("")
                print("""
                 .-----------------------------------------------------------------------.
                 | Thank you Youngen, Now scram.                                         |
                 |								         |
                 .------     ------------------------------------------------------------.
                        \   /
                         \ /
                          .
                 """)

                inventory.append(item_torch)
                inventory.remove(item_chocolate)
                print("a torch added to you inventory.")
                guard_talk = 1
        elif (char_id == "guard") and guard_talk == 1: # From now on the guard has different dialogue
            print("""
             .-----------------------------------------------------------------------.
             | Good to see you again youngen, Thanks for the chocolates again.       |
             | Don't cause any trouble now!                                          |
             |								             |
             .------     ------------------------------------------------------------.
                    \   /
                     \ /
                      .
             """) #-----------------------------------------------------------------------------------------------------------------
        else:
            print(char["description"]) #this prints the regular dialogue
            print("")
    else:
        print("You cannot speak to them")

def execute_use1(use_id):
    global current_room
    if (use_id in objects) and (objects[use_id] in current_room["objects"]): #check that the object exists and is in the room
        if use_id == "passwordlock": # this section handles the password lock, upon unlocking the lock it replaces the room.
            password = int(input("please enter your 5 digit code: "))
            if password == 51181:
                current_room["name"] = "kirill's office"
                current_room["description"] =  """
    White boards cover all the walls in the OFFICE, mathematical equations and
    maps of space litter the room. It appears Kirill has been planning a trip 
    to Mars? Other than the precise orbital trajectories, the room is a 
    typical OFFICE, the computer is turned off and beside it is some paperwork 
    and his phone. You can head back to C204/C205 if you wish, or head towards 
    RECEPTION."""
                current_room["objects"].append(obj_phone)
                current_room["objects"].remove(obj_passlock)
                current_room["music"] = "music/kirill.wav"
                winsound.PlaySound("music/kirill.wav",winsound.SND_ASYNC | winsound.SND_LOOP)
            else:
                print("Nope wrong password.")  #---------------
        elif use_id == "phone": # this section triggers the endgame if the phone is used.
            win()
        else:
            print("")
            print(objects[use_id]["use"]["null"]) #this prints the use command for non spesific things.
    else:
        print("")
        print("you can't use that.")


def execute_use2(use_id): # this function handles using an item on an object.
    global inventory
    global current_room
    if (use_id[0] in items and inventory) and (use_id[1] in objects) and (objects[use_id[1]] in current_room["objects"]): # This checks that the item\object is in the inventory\room respectively.
        if use_id[0] == "torch" and use_id[1] == "pinata": # This part of the code is for attacking the pinata with the torch.
            inventory.append(item_keys)
            rooms["Cafeteria"]["objects"].remove(obj_pinata)
            print("")
            print("You slay the pinata in glorious combat, you gain 1 level in THE DARK ARTS. You recover some keys from its vaguely horse-y remains.")
            print("a pair of keys added to you inventory.")#---------------------------------------------------------------------------------------
        elif use_id[0] == "keys" and use_id[1] == "door": # This part of the code is for opening N/4.07 the room outside n/4.07 is replaced in this.
            inventory.remove(item_keys)
            current_room["name"] = "N/4.07"
            current_room["description"] = """
    Kirills body lies lifeless on the floor in the centre of the room, there 
    doesn't appear to be any blood, there is instead signs of a blow to the 
    head. Out of the corner of your eye you spot a piece of paper."""
            current_room["items"].append(item_password)
            current_room["objects"].remove(obj_door)
            current_room["music"] = "music/nfour.wav"
            winsound.PlaySound("music/body.wav",winsound.SND_ASYNC | winsound.SND_LOOP)
            print("")
            print("You turn the key and the door swings open.")#---------------------------------------------------------------------------------------
        elif use_id[0] in objects[use_id[1]]["use"]: # This prints a response for using an item on something if it exists
            print(objects[use_id[1]]["use"][use_id[0]])
        else:
            responses = ["You use the thing on the other thing and a thing doesn't happen.", "Nope.", "nu-uh.", "Nada.", "That is without a doubt the worst idea i've ever heard.","That item? on that object?!?! WHY?"]
            print(random.choice(responses)) # This prints more generic responses
    else:
        print("")
        print("You can't use that.")

def execute_open(open_id): # This function allows the player to open objects
    if (open_id in objects) and (objects[open_id] in current_room["objects"]): #this checks the object exists and is in the room.
        if open_id == "presents": # This handles opening the present
            inventory.append(item_chocolate)
            rooms["Cafeteria"]["objects"].remove(obj_presents)
            print("Oh boy! unbranded chocolate! my favourite.")
            print("box of chocolates added to your inventory.") # -----------------------------------------
        elif open_id == "door" and item_keys in inventory: # this is an alternate solution to opening the door to N/4.07 see use2 for details
            inventory.remove(item_keys)
            current_room["name"] = "N/4.07"
            current_room["description"] = """
    Kirills body lies lifeless on the floor in the centre of the room, there 
    doesn't appear to be any blood, there is instead signs of a blow to the 
    head. Out of the corner of your eye you spot a piece of paper."""
            current_room["items"].append(item_password)
            current_room["objects"].remove(obj_door)
            current_room["music"] = "music/nfour.wav"
            winsound.PlaySound("music/body.wav",winsound.SND_ASYNC | winsound.SND_LOOP)
            print("")
            print("You turn the key and the door swings open.") #------------------------------------------------------------------------------
        elif open_id == "passwordlock":  # this is an alternate solution to opening the password lock see use1 for details
            password = int(input("please enter your 5 digit code: "))
            if password == 51181:
                current_room["name"] = "kirill's office"
                current_room["description"] =  """
    White boards cover all the walls in the OFFICE, mathematical equations and
    maps of space litter the room. It appears Kirill has been planning a trip 
    to Mars? Other than the precise orbital trajectories, the room is a 
    typical OFFICE, the computer is turned off and beside it is some paperwork 
    and his phone. You can head back to C204/C205 if you wish, or head towards 
    RECEPTION."""
                current_room["objects"].append(obj_phone)
                current_room["objects"].remove(obj_passlock)
                current_room["music"] = "music/kirill.wav"
                winsound.PlaySound("music/kirill.wav",winsound.SND_ASYNC | winsound.SND_LOOP)
            else:
                print("Nope wrong password.") #----------------------------------------------------------------------------------------------------
        else:
            print("")
            print(objects[open_id]["open"])# This prints a response for opening something 
    else:
        print("You can't open that.")



def execute_command(command): # this function checks which command to execute

    if 0 == len(command):
        return

    if command[0] == "go":
        if len(command) > 1:
            execute_go(command[1])

    elif command[0] == "take":
        if len(command) > 1:
            execute_take(command[1])

    elif command[0] == "drop":
        if len(command) > 1:
            execute_drop(command[1])

    elif command[0] == "inspect":
        if len(command) > 1:
            execute_inspect(command[1])

    elif command[0] == "speak":
        if len(command) > 1:
            execute_speak(command[1])
    elif command[0] == "use":
        if len(command) == 2:
            execute_use1(command[1])
        elif len(command) == 3:
            execute_use2(command[1:3])
    elif command[0] == "open":
         if len(command) > 1:
            execute_open(command[1])
    else:
        print("This makes no sense.")

def win(): # this is the win condition
    global game_ending
    global guess
    if guess == 0: # this section gives different responses depending on how many times they've guessed.
        print("""
        Hello you've reached the police, what!? you'd like to report a murder?""")
    elif guess == 1:
        print("""
        Ok just so you know we dont take false acusiations lightly.""")   
    elif guess == 2:
        print("""
        ok last chance kiddo.""") # -----------------------------------------------------------
    murderer = str(input("""
    Who do you think is the murderer?: """)) # this section normalises the input for name of the suspect.
    murderer_list = normalise_input(murderer)
    x = 0
    normalised_murderer = ""
    for word in murderer_list:
        if x > 0:
            normalised_murderer += " "
        normalised_murderer += word
        x = 1 #---------------------------------------------------------------------------
    if normalised_murderer == "elon musk" or normalised_murderer == "elon" or normalised_murderer == "musk": #this section check that the name is correct
        murderer_correct = True
        weapon = str(input("""
        And what weapon do you think they used?: """)) #here we ask for the weapon
        normalised_weapon = normalise_input(weapon)
        if "manual" in normalised_weapon: #looks for the word manual.
            weapon_correct = True
        else:
            print("""
            That can't be correct, come back with more evidence.""")
            murderer_correct = False
            weapon_correct = False
            guess += 1 # this counts the number of wrong guesses
    else:
        print("""
        That can't be correct, find the true suspect.""")
        murderer_correct = False
        weapon_correct = False
        guess += 1  #This counts the number of wrong guesses
    if murderer_correct == True and weapon_correct == True: # this triggers the ending if the player guesses correctly.
        game_ending = True
        end()
    elif guess == 3: #this triggers the bad ending if the player guesses incorectly 3 times
        print("""
        Sorry we cant take any more acusations for you. you'll have to wait for the experts.""")
        badend()
        game_ending = True
    
def end(): # Good ending

    print("Congratualtions on completing Kluedo!")
    print("""
    You solved the mystery, with questionable elagance.
    people on the streets cheer for you honour. Cardiff university
    errects a statue in your honour in the parade. speaking of parades,
    they throw one in your honour. YEAH YOUR THAT GOOD. Kirill's ghost
    still teaches through a ouija board. life is good.""")
    time.sleep(7)
    print("")
    print("""
    10 YEARS LATER""")
    time.sleep(3)
    print("")
    print("")
    print("                                 _/**\_")
    print("                                /      \ ")
    print("                              /          \ ")
    print("                             |     /\     |")
    print("                             |    /  \    |")
    print("                             |   / __ \   |")
    print("                             |  | (  ) |  |")
    print("                             |  | (__) |  |")
    print("                        /\   |  |      |  |   /\ ")
    print("                       /  \  |  |______|  |  /  \ ")
    print("                      |----| |  |      |  | |----|")
    print("                      |    | | /|   .  |\ | |    |")
    print("                      |    | /  |   .  |  \ |    |")
    print("                      |    /    |   .  |    \    |")
    print("                      |  /      |   .  |      \  |")
    print("                      |/        |   .  |        \|")
    print("                      /  KirillX|   .  | KirillX \ ")
    print("                     (__________|______|___________)")
    print("                      |____| |--|______|--| |____|")
    print("                       /  \     /  \/  \     /  \ ")
    print("                        \/       \/  \/       \/ ")
    print("                        \/       \/  \/       \/ ")
    print("")

    for x in range(0,35,1):
        time.sleep(0.5)
        print("")
    print("     ████████╗██╗  ██╗███████╗    ███████╗███╗   ██╗██████╗")
    time.sleep(0.5)
    print("     ╚══██╔══╝██║  ██║██╔════╝    ██╔════╝████╗  ██║██╔══██╗")
    time.sleep(0.5)
    print("        ██║   ███████║█████╗      █████╗  ██╔██╗ ██║██║  ██║")
    time.sleep(0.5)
    print("        ██║   ██╔══██║██╔══╝      ██╔══╝  ██║╚██╗██║██║  ██║")
    time.sleep(0.5)
    print("        ██║   ██║  ██║███████╗    ███████╗██║ ╚████║██████╔╝")
    time.sleep(0.5)
    print("        ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚══════╝╚═╝  ╚═══╝╚═════╝")
    time.sleep(20)

def badend(): # Bad ending
    print("""
    The police come. the murderer isnt found.
    your are arrested for obstructing justice.
    The End?""")





def menu(exits, room_items, inv_items, room_objects, room_characters):

    # Display menu
    print_menu(exits, room_items, inv_items, room_objects, room_characters)

    user_input = input("> ")

    normalised_user_input = normalise_input(user_input)

    return normalised_user_input


def move(exits, direction):
    # Next room to go to
    return rooms[exits[direction]]


# This is the entry point of our program
def main():
    print("")
    print("")
    print("")
    print("                     __ __ __    __  ____________  ____  ")
    print("                    / //_// /   / / / / ____/ __ \/ __ \ ")
    print("                   / ,<  / /   / / / / __/ / / / / / / / ")
    print("                  / /| |/ /___/ /_/ / /___/ /_/ / /_/ /  ") 
    print("                 /_/ |_/_____/\____/_____/_____/\____/   ")  
    print("                                                         ")
    print("")
    print("")
    print("""   You are at what's left of Kirills birthday party on Queens building grounds.
    On an off chance, Kirill had invited A list celebrities from the world of 
    computer science; these included Facebook creator Mark Zuckerburg, Amazon 
    owner Jeff Bezos, Microsoft owner Bill Gates, father of the World Wide Web 
    Tim Bernes Lee and Playboy philanthropist Elon Musk (space man). 

    Kirills body has been found by a cleaner! He is dead.....
    With everyone at the party being the potential murderer, it's up to you 
    to work out who did it.  
        """)
    winsound.PlaySound("music/cafe.wav",winsound.SND_ASYNC | winsound.SND_LOOP)

    # Main game loop
    while True:
        # Display game status (room description, inventory etc.)
        print_room(current_room)
        print_inventory_items(inventory)

        # Show the menu with possible actions and ask the player
        command = menu(current_room["exits"], current_room["items"], inventory, current_room["objects"], current_room["characters"])

        # Execute the player's command
        execute_command(command)
        
        if game_ending == True:
            break



if __name__ == "__main__":
    main()

