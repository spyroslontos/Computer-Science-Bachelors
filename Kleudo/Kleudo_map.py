from Kleudo_items import *
from Kleudo_characters import *
from Kleudo_objects import *

room_reception = {
    "name": "Reception",

    "description":
    """
    You are now in RECEPTION, the security guard watches you from behind
    his desk, his torch lies on the desk in front of him (within your reach),
    next to him also lies a pile of empty chocolate wrappers. 
    From here you can head back to the CAFETERIA, go upstairs to the LIBRARY
     or head over to Kirills OFFICE.""",

    "exits": {"office": "Office", "cafeteria": "Cafeteria", "library": "Library"},

    "items": [],

    "objects": [obj_wrappers],

    "characters": [character_guard],

    "music" : "music/reception.wav"
}

room_cafeteria = {
    "name": "Cafeteria",

    "description":
    """
    You arrive in the CAFETERIA, surrounding you are the remanence of 
    Kirills Nth birthday party. There is a half-eaten cake, confetti and 
    numerous presents scattered around the room; 
    attached to the ceiling is a pinata (you note that it hasn't been 
    broken yet). From the CAFETERIA, you can exit to the LIBRARY 
    or RECEPTION.""",

    "exits":  {"reception": "Reception", "library": "Library"},

    "items": [],

    "objects": [obj_pinata, obj_punch, obj_presents],

    "characters": [character_zuckerburg,character_musk,character_gates,character_lee,character_bezos],

    "music" : "music/cafe.wav"
}

room_lab = {
    "name": "Lab C/2.04/2.05",

    "description":
    """
    Weirdly the only thing in this is just an eerie silence, all the 
    computers are turned off and it seems that nobody has been in here
    for quite some time. From here you can go upstairs to N407, leave
    towards Kirills OFFICE, across to the LIBRARY or next door to the 
    LINUXLAB.""",

    "exits": {"n407": "N/407", "library": "Library", "linuxlab": "LINUXLAB"},

    "items": [item_earphones],
    
    "objects": [obj_laptop],

    "characters": [],

    "music" : "music/lab.wav"
}

room_lecture = {
    "name": "Outside N/4.07",

    "description":
    """
    As you aproach N/4.07 you notice the door is locked! looks like you'll need to find the keys. """,

    "exits": {"library": "Library", "linuxlab": "LINUXLAB"},

    "items": [],

    "objects": [obj_door],

    "characters": [],

    "music" : "music/outsidel.wav"
}

room_linux = {
    "name": "Linux Lab",

    "description":
    """
    Here is the LINUXLAB, the room is filled with rows of computers, one of which 
    is still switched on.  You walk over to the computer. The rest of the room 
    is dark and there doesn't appear to be anything unusual going on. 
    Head towards N407 to Kirril's office or across to C204/C205.""",

    "exits": {"n407": "N/407", "labs": "Labs", "office" : "Office"},

    "items": [],

    "objects": [],

    "characters": [],

    "music" : "music/linux.wav"
}

room_koffice = {
    "name": "Outside Kirill's Office",

    "description":
    """
    It apears to be protect by some sort of password lock.You'll need to unlock it to get in.
    """,

    # The OFFICE appears to be locked, you need a pin for the keypad to unlock it.

    "exits": {"reception": "Reception", "linuxlab" : "LINUXLAB"},

    "items": [],

    "objects": [obj_passlock],

    "characters": [],

    "music" : "music/outsideo.wav"
}

room_library = {
    "name": "Library",

    "description":
    """
    You Find yourself in the Trevithick LIBRARY. Many students are studying 
    (Best not disturb them!). In the bin by the entrance there is a hardback 
    copy of a Tesla Owner's Manual. You also notice a student who is fast 
    asleep. If you leave the LIBRARY you can go downstairs to the RECEPTION 
    or the CAFETERIA, otherwise you can head over to N407 or C204/C205.""",

    "exits": {"reception": "Reception","n407": "N/407", "labs": "Labs"},

    "items": [item_manual],

    "objects": [],

    "characters": [],

    "music" : "music/library.wav"
}


rooms = {
    "Reception": room_reception,
    "Cafeteria": room_cafeteria,
    "Labs": room_lab,
    "N/407": room_lecture,
    "LINUXLAB": room_linux,
    "Office": room_koffice,
    "Library": room_library
}
