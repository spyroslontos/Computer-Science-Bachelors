import csv
d = {}
with open('towns.csv') as csvfile:
    rdr = csv.reader(csvfile)
    for row in rdr:
        #print(str(row[0]),row[1])
        d[row[0]] = row[1]

for key,value in d.items():
    print(key,":",value)

#Puts all the len of the towns in a list and finds the max
width = max([len(x) for x in d])
print(width)

with open('towns.csv') as csvfile:
    rdr = csv.reader(csvfile)
    for row in rdr:
        if  500000 < int(row[1]) and int(row[1]) < 1000000:
            print(row[0],"has a population between 0.5-1 million")

 #Sorted in decending order
for key,value in sorted(d.items(),key=lambda t: t[1], reverse=True):
    print(key,":",value)