import random
import math
import matplotlib.pyplot as plt


def theoreticalMeanQueueLength(alpha, beta):

    if alpha == beta or alpha == 0:
        return (-1)
    else:
        return ((beta/alpha)/(1-(beta/alpha)))


fig = plt.figure("Queue Improvement")
ax = fig.add_subplot(1, 1, 1)

sketchNormx = []
sketchNormy = []
for i in range(11, 111, 1):
    sketchNormx.append(i/10)
    sketchNormy.append(theoreticalMeanQueueLength((i/10), 1))

sketch2ndx = []
sketch2ndy = []
for i in range(11, 111, 1):
    sketch2ndx.append(i/10)
    sketch2ndy.append(theoreticalMeanQueueLength((i/10), 0.5))


ax.plot(sketchNormx, sketchNormy, "r-", label="Normal")
ax.plot(sketch2ndx, sketch2ndy, "b-", label="2nd Teller(50%)")


plt.title("Queue Length Change\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("Mean Queue Length")
ax.legend()
plt.show()
