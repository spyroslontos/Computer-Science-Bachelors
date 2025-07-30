word = input("Enter a word to check if it is a palindromic word: ")
word.strip(" ") #removes whitspace
x=len(word)

if word[0:x] == word[::-1]:
    print ("It is palindromic")
else:
    print ("It is NOT palindromic")