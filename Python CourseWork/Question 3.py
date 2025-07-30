import random
import math
import matplotlib.pyplot as plt


def nextTime(mean):
    return -mean * math.log(1 - random.random())


def singleQueue(alpha, beta, time=480):

    time_arrival = 0
    time_served = 0
    current_time = 0
    maxQ = 0
    Q_length = 1
    while current_time < time:
        if time_arrival < time_served:
            time_served = time_served - time_arrival
            current_time = current_time + time_arrival
            Q_length = Q_length + 1
            maxQ = max(maxQ, Q_length)
            time_arrival = nextTime(alpha)
        else:
            time_arrival = time_arrival - time_served
            current_time = current_time + time_served
            Q_length = Q_length - 1
            time_served = nextTime(beta)
        while True:
            if Q_length != 0:
                break
            else:
                current_time = current_time + time_arrival
                Q_length = Q_length + 1
                maxQ = max(maxQ, Q_length)
                time_arrival = nextTime(alpha)

    return (maxQ)

#-------------------- (MaxQ Sketch) ------------------------#

maxQx = []
maxQy = []

for i in range(1,90):
    random.seed(935)
    maxQy.append(singleQueue(10, 9-(i/10), 280))
    maxQx.append(i/10)
    #See the change in maxQ as beta(service time) lowers


#------------------------- (a) -----------------------------#

#Since beta is the mean service time.
#New equipment will lower beta.
#By lowering beta the maxQ gets lower.

random.seed(101)
print(singleQueue(5, 1)) #singleQueue(5, 3) > 6

#------------------------- (b) -----------------------------#

#Employing a second teller means the service time will go to half.
#Instead of service time being 4 it goes to 2.
#Halfing the beta lowers the maxQ.

random.seed(935)
print(singleQueue(10, 4.5, 280)) # singleQueue(10, 9, 280) > 10

#----------------------- PLOTTING ---------------------------#

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

sketchEqtx = []
sketchEqty = []
for i in range(11, 111, 1):
    sketchEqtx.append(i/10)
    sketchEqty.append(theoreticalMeanQueueLength((i/10), 0.75))

sketch2ndx = []
sketch2ndy = []
for i in range(11, 111, 1):
    sketch2ndx.append(i/10)
    sketch2ndy.append(theoreticalMeanQueueLength((i/10), 0.5))


ax.plot(sketchNormx, sketchNormy, "r-", label="Normal")
ax.plot(sketchEqtx, sketchEqty, "b-", label="New Equipment")
ax.plot(sketch2ndx, sketch2ndy, "g-", label="2nd Teller")

#ax.plot(maxQx, maxQy, "y-", label="MaxQ") # <------

plt.title("Queue Length Change\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("Mean Queue Length")
ax.legend()
plt.show()
