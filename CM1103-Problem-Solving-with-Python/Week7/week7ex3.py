def printList(a):
    while len(a)>0:
        print(a.pop(0))


a=["a","b","c","d","e"]

printList(a)

print("After function call:", a)