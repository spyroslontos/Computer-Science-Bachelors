def countLetters(text):
    global letterList
    letterList = {}
    for letter in text:
        if letter not in letterList:
            letterList.update({letter:1})
        else:
            letterList[letter] += 1
    return letterList

countLetters(str(input("Enter a string: ")))
print(letterList)