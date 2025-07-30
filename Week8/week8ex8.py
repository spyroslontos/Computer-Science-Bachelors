import re

def matchEmail(email):
    result = re.match("[a-z]+@[a-z]+.[a-z]{3}",email)
    if result != None:
        print ("Matched")
    else:
        print("Not Matched")


matchEmail(str(input("Enter an email address: ")))
