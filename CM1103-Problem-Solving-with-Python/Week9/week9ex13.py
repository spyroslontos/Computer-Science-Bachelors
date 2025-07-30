import re

def validatorA(data):
    result = re.match("[c]{1}[0-9]{7}", data)

    if result != None:
        print("Passed:", data)
    else:
        print("Not Passed")

validatorA("c1234567")

#-----------------------------------------------------------#

def validatorB(data):

    result = re.match("[s]{1}[c]{1}[m]{1}[0-9]{1}[a-z]{2,3}", data)

    if result != None:
        print("Passed:", data)
    else:
        print("Not Passed")

validatorB("scm7uii")

#-----------------------------------------------------------#

def validatorC(data):

    result = re.match("[0-9]{2}-[0-9]{2}-[0-9]{2}", data)

    if result != None:
        print("Passed:", data)
    else:
        print("Not Passed")

validatorC("09-97-68")

#-----------------------------------------------------------#

def validatorD(data):

    result = re.match("[A-Z]{2}[0-9]{2}[A-Z]{2}", data)

    if result != None:
        print("Passed:", data)
    else:
        print("Not Passed")

validatorD("BD51SMR")