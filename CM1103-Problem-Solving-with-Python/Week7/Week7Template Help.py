a = [('Tim Smith', 54), ('Anna Smith', 54), ('Barry Thomas', 88)]
b = [('Tim Jones', 'C'), ('Anna Smith', 'B'), ('Barry Thomas', 'A')]

print(sorted(sorted(a), key=lambda x: x[1], reverse = True))
print(sorted(sorted(b), key=lambda x: x[1]))

if type(a[0][1])is int:
    print("Integer")
else:
    print("Letter")

#Can use both type() or isinstance()

if isinstance(b[0][1], int):
    print("Integer")
else:
    print("Letter")

#isinstance(object,classinfo)
#uses the object and is true of the info matches