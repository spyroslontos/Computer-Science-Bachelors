import re
txtReader = open("postcodes.txt")


def matchPostCodeChecker(postcode):
    postcode = postcode.replace(" ","")
    result = re.match("[A-Z]{2}[0-9]{1,2}[0-9][A-Z]{1,2}",postcode)
    if result != None:
        print (postcode,":","Postcode Matches the expression")
    else:
        print(postcode,":","Warning!")


for line in txtReader:
    matchPostCodeChecker(line)
    #Checks for the postcodes in the txt file