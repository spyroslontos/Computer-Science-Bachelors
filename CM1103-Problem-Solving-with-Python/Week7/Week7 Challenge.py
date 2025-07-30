import itertools
order = 5*[6,9,20]
subsets = []

#Find all the subsets of the numbers
for i in range(0, len(order)):
    for subset in itertools.combinations(order, i):
        subsets.append(sum(subset))

#Clear list from repeats and above 100
cleared_subsets=[]
for i in subsets:
  if i not in cleared_subsets and i<101:
    cleared_subsets.append(i)


#Shows the numbers of guests avoid inviting
print("Don't Invite")
for i in range(1,101):
    if i not in cleared_subsets:
        print(i, end=",")
