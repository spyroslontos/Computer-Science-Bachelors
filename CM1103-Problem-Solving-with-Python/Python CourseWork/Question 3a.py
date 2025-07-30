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

sketchEqt1x = []
sketchEqt1y = []
for i in range(11, 111, 1):
    sketchEqt1x.append(i/10)
    sketchEqt1y.append(theoreticalMeanQueueLength((i/10), 0.9))

sketchEqt2x = []
sketchEqt2y = []
for i in range(11, 111, 1):
    sketchEqt2x.append(i/10)
    sketchEqt2y.append(theoreticalMeanQueueLength((i/10), 0.75))

sketchEqt3x = []
sketchEqt3y = []
for i in range(11, 111, 1):
    sketchEqt3x.append(i/10)
    sketchEqt3y.append(theoreticalMeanQueueLength((i/10), 0.6))


ax.plot(sketchNormx, sketchNormy, "r-", label="Normal")
ax.plot(sketchEqt1x, sketchEqt1y, "b-", label="10%")
ax.plot(sketchEqt2x, sketchEqt2y, "g-", label="25%")
ax.plot(sketchEqt3x, sketchEqt3y, "y-", label="40%")


plt.title("Queue Length Change\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("Mean Queue Length")
ax.legend(loc="upper right")
plt.show()
