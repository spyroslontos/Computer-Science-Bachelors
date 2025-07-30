a = ["tim", "bob", "trevor", "anna", "susan"]
print(a)
#lambda argument:expression

print("a)", sorted(a))

print("b)", sorted(a,key=lambda x:x[1]))

print("c)", sorted(a,key=lambda x:x[-1]))

print("d)", sorted(a,key=lambda x:len(x)))

print("e)", sorted(a,key=lambda x:(len(x),x)))

print ("f", sorted(a,key=lambda x:(True if x[0] in ["a","e","i","o","u"] else False,x)))
