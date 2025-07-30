

obj_pinata = {
    "id":"pinata",
    
    "name" : "pinata",
    
    "inspect" : """
    The pinata stares at you with its beady drawn on eyes.
    """,
    
    "use" : {"null" : """The pinata is too strong!
   your frail computer scientist hands cannot hope to damage it un-aided.
   perhaps try something heavier?"""},

    "open" : """
    This is an adventure game! the simple solution won't work.
    """
    }

obj_punch = {
    "id" : "punch",
    
    "name" : "punch bowl",
    
    "inspect" : """
    A large bowl of some fruity punch, you consider drinking some,
    you pass. Remember kids, don't drink and think!
    """,
    
    "use" :{"null" : "Now is not the time for drinking!"},

    "open" : """
    Uh no. Thats not even possible.
    """
    }

obj_presents = {
    "id" : "presents",
    
    "name" : "a bunch of presents",
    
    "inspect" : """
    THERE ARE SO MANY!
    """,
    
    "use" : {"null" : "what do you want to do to the presents?"},

    "open" : "You should never see this, if you do. please email me at: zse4rffb@gmail.com"
    }

obj_wrappers = {
    "id" : "wrappers",
    
    "name" : "chocolate wrappers",

    "inspect" : """
    The guard must like chocolate.
    """,

    "use" : {"null", "There's literally no way to use these."},
    
    "open" : """
    They're already open dunkass.
    """
    }

obj_laptop = {
    "id" : "laptop",
    
    "name" : "laptop",

    "inspect" : """                     _____________________________________________
		   /                                             \
		                  | ______________________________________________|
		  ||                                             ||
		  || To : Kirill Sidorov                         ||
		  || From : xX_ZuccMaster_Xx                     ||
		  ||                                             ||
	          || Subject : Why though?                       ||
		  ||                      Kiril, my man! How are ||
		  || you doing? Why are you still using MySpace? ||
		  || Dont you know how bad this is for my brand? ||
		  || I'd kill for the sort of influence you have!||
		  || Ha Ha just kidding Lol.                     ||
		  ||                                             ||
		  ||              Speak to you soon!             ||
		  ||                   The ZuccMeister           ||
		  ||_____________________________________________||
		  |                                               |
		   \______________________________________________/
		      \_______________________________________/
		    _______________________________________________ """,


    "use" : {"null" : "The laptop has no signal."},

    "open" : """
    it's already open dunkass.
    """
    }

obj_door = {
    "id" : "door",
    
    "name" : "The door to N/4.07",

    "inspect" : """
    The door is locked, you die a little inside knowing you'll have to climb those stairs again.
    """,

    "use" : {"null", "its locked."},
    
    "open" : """
    The doors locked kiddo, what more do you want from me.
    """
    }

obj_passlock = {
    "id" : "passwordlock",
    
    "name" : "a password locked door",

    "inspect" : """
    Kiril's new security system, it has room for 5 digits.
    """,

    "use" : {"password": """You rub the paper up against the lock,
     suprisingly it does absolutely nothing. Maybe try opening it?"""},
    
    "open" : "You should never see this, if you do. please email me at: zse4rffb@gmail.com"
    }

obj_phone = {
    "id": "phone",

    "name": "Nokia brick",

    "inspect": """ 
    You could phone the police with this. 
    """,

     "use" : {"null", "Here for error handling."},
    
    "open" : """
    its a nokia, you'd have beter luck pulling the sword from the stone.
    """

}
objects = { 
    "pinata" : obj_pinata,
    "punch" : obj_punch,
    "presents" : obj_presents,
    "laptop" : obj_laptop,
    "wrappers" : obj_wrappers,
    "door" : obj_door,
    "passwordlock" : obj_passlock,
    "phone" : obj_phone
        }


