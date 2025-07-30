sports =["football","rugby","hockey","tennis"]
print(sports[0])
print(sports[-1])
sports.append("cycling")
print(sports)
print(len(sports))
for i in sports:
    print(i[0])

sports.remove("football")
print(sports)

new_list = []

if len(sports)%2==0:

    x = (len(sports))/2
    new_list.append(sports[int(x-1)])
    new_list.append(sports[int(x)])

print(new_list)

