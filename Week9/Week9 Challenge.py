import matplotlib.pyplot as plt
import random


def proportion(n):
    merged_list = []
    assignments = 10000
    ratio = int(assignments / n)
    for i in range(1, n+1):
        merged_list += [str(i)]*ratio

    random.shuffle(merged_list)

    matches = 0
    for student in range(n):
        for i in range(ratio*student-ratio,ratio*student):
            matches += merged_list[i].count(str(student))

    return (matches/assignments)


sketchy =[]
sketchx = []
for i in range(1,11):
    sketchx.append(i)
    sketchy.append(proportion(i))

print(sketchx)
print(sketchy)
plt.plot(sketchx, sketchy,"r-")
plt.title("Proportion Change\n")
plt.xlabel("Number of Students")
plt.ylabel("Proportion")
#plt.show()

#-----------------------------------------------------#

xaxis = []
probabilitylist = []
exercises = 10000
for i in range(1,11):
    probability = ((exercises/i)/exercises)
    print("n", probability)
    probabilitylist.append(probability)
    xaxis.append(i)

plt.plot(xaxis, probabilitylist, "g-")
plt.show()