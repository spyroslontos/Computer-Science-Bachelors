d = {"Tom":500, "Stuart":1000, "Bob":55, "Dave":21274}

print("Number of items in dictionary:", len(d))

keys = []
values = []

for key in d.keys():
    keys.append(key)
print("Keys:", keys)

for value in d.values():
    values.append(value)
print("Values:", values)

d.pop("Bob")
d.update({"Phil":60})
print(d)

print("Dave" in d)
print("Peter" in d)
print(500 in d) #???????

for key,value in d.items():
    print(key,value)

for i in d:
    print(i)