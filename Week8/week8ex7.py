import re
def expressionTest(text):
    result=re.match("[a-z]+",text)
    if result != None:
        print ("Matched")
    else:
        print("Not Matched")


expressionTest(str(input("Enter a string: ")))
expressionTest("Hello")
expressionTest("&")
expressionTest("Python␣is␣cool")
expressionTest("goodbye")
expressionTest("test1")