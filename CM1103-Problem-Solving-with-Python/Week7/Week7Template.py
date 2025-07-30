"""
Is the function sorting correctly when the names don't matter?

>>> sortStudents([('Tim Jones', 54), ('Anna Smith', 56), ('Barry Thomas', 88)])
[('Barry Thomas', 88), ('Anna Smith', 56), ('Tim Jones', 54)]

Is the function returning something with the correct type?

>>> type(sortStudents([('Tim Jones', 54)]))
<class 'list'>

Is the function sorting correctly when the name does matter?

>>> sortStudents([('Tim Smith', 54), ('Anna Smith', 88), ('Barry Thomas', 88)])
[('Anna Smith', 88), ('Barry Thomas', 88), ('Tim Smith', 54)]

Is it correctly using the surname and forename?
>>> sortStudents([('Tim Smith', 54), ('Anna Smith', 54), ('Barry Thomas', 88)])
[('Barry Thomas', 88), ('Anna Smith', 54), ('Tim Smith', 54)]
>>> sortStudents([('Tim Smith', 54), ('Yulia Smith', 54), ('Barry Thomas', 88)]) 
[('Barry Thomas', 88), ('Tim Smith', 54), ('Yulia Smith', 54)]

Is the function using a lambda expression?
>>> import inspect
>>> len(inspect.getsourcelines(sortStudents)) == 2 and "lambda" in inspect.getsourcelines(sortStudents)[0][1]
True

Does it handle grades as well?
>>> sortStudents([('Tim Jones', 'C'), ('Anna Smith', 'B'), ('Barry Thomas', 'A')])
[('Barry Thomas', 'A'), ('Anna Smith', 'B'), ('Tim Jones', 'C')]
"""

# *** DO NOT CHANGE CODE ABOVE THIS LINE ***
# *** DO NOT ADD ANY COMMENTS OF YOUR OWN IN THE SUBMITTED SOLUTION ***

def sortStudents(a):
    return sorted(sorted(a), key=lambda x:x[1], reverse = True) if type(a[0][1]) is int else sorted(sorted(a), key=lambda x:x[1])


