item_earphones = {
    "id": "earphones",

    "name": "Apple Earphones",

    "description":
    "These are the wired Apple earphone, they look pretty innocent",

    "inspect":
    """
    Lethal in the right hands and dangerous nonethless.
    You take extra care to not get tangled up, you don't want to be here all 
    day!""",

"mass": 1

}



item_manual = {
    "id": "manual",
    
    "name": "hardback Tesla Owners Manual",

    "description": "Why is this Tesla Owners manual here? Clearly somebody is rich!",

    "inspect":"""
     The corner of the manual appears to be dented, clearly been dropped or hit
     against something hard. There is a lot of heft to this book, clearly 
     there is a lot to know if you own a Tesla """,


    "mass": 5

}

item_torch = {
    "id": "torch",
    
    "name": "a torch",

    "description": """
    It's just a torch, very normal in everyway. Its a high acheiver
    when it comesto lighting dark places. """,

    "inspect": """
     You could probably swing this torch quite hard, it could come in handy at some point. """,

    "mass": 3

}

item_chocolate = {
    "id": "chocolate",
    
    "name": "a box of chocolates",

    "description": "Yummy, a box of unbranded chocolates, these have always been your favourite",

    "inspect": """
     Chocolates are always good to have on you, you never know who you might bump 
     into who wants a chocolate, people would do a lot for chocolate. """,

    "mass": 3

}

item_keys = {
    "id": "keys",
    
    "name": "a pair of keys",

    "description": " Looks like a normal pair of keys, at a guess I would say they probably unlock something? ",

    "inspect": " On the back is a small ingraving, it reads N/4.07. ",

    "mass": 1

}

item_password = {
    "id": "password",
    
    "name": "a secret password",

    "description": """
    It appears to have some sort of logic puzzle written on it, you need to take
    a closer look """,

    "inspect":"""
     It's the passcode into Kirills Office! You just need to work out the 
     answer to get the pin though.
     
     If 18 matchsticks make up the number 508, 
     by only moving two matchsticks, what is the largest number you can create? 
       _  _  _
      |_ | ||_|
       _||_||_| """,

       #(answer - 51181)

    "mass": 1

}

items = {
    "earphones" : item_earphones,
    "manual" : item_manual , 
    "torch" : item_torch,
    "chocolate" : item_chocolate,
    "keys" : item_keys, 
    "password" : item_password
}
