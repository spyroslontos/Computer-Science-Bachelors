import math
import random
import matplotlib.pyplot as plt


def nextTime(mean):
    return -mean * math.log(1 - random.random())
    # Returns the mean with standard deviation added


def singleQueue(alpha, beta, time=480):
    time_arrival = 0
    time_served = 0
    current_time = 0
    maxQ = 0
    Q_length = 1
    # Initializes variables used
    while current_time < time:  # Simulates Time
        if time_arrival < time_served:
            time_served = time_served - time_arrival
            current_time = current_time + time_arrival
            Q_length = Q_length + 1
            maxQ = max(maxQ, Q_length)
            time_arrival = nextTime(alpha)  # Simulates Random Customer arrival time
        else:
            time_arrival = time_arrival - time_served
            current_time = current_time + time_served
            Q_length = Q_length - 1
            time_served = nextTime(beta)  # Simulates Random Service time
        while True:
            if Q_length != 0:  # Checks for the length of the queue
                break
            else:
                current_time = current_time + time_arrival
                Q_length = Q_length + 1
                maxQ = max(maxQ, Q_length)
                time_arrival = nextTime(alpha)  # Simulates Random Customer arrival time

    return (maxQ)


# Sketching the graphs

sketchNormx = []
sketchNormy = []
for i in range(11, 111, 1):
    sketchNormx.append(i/10)
    random.seed(101)
    sketchNormy.append(singleQueue(i / 10, 4, 480))


sketchEqt1x = []
sketchEqt1y = []
for i in range(11, 111, 1):
    sketchEqt1x.append(i/10)
    random.seed(101)
    sketchEqt1y.append(singleQueue(i / 10, 3, 480))


sketchEqt2x = []
sketchEqt2y = []
for i in range(11, 111, 1):
    sketchEqt2x.append(i/10)
    random.seed(101)
    sketchEqt2y.append(singleQueue(i / 10, 2, 480))

# Creating lists with the coordinates used

fig = plt.figure("Equipment Improvement")
ax = fig.add_subplot(1, 1, 1)

ax.plot(sketchNormx, sketchNormy, "b-", label="Normal")
ax.plot(sketchEqt1x, sketchEqt1y, "r-", label="25%")
ax.plot(sketchEqt2x, sketchEqt2y, "g-", label="50%")

plt.title("MaxQ Change With New Equipment\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("MaxQ length")
ax.legend(loc="upper right")
plt.show()
# Plotting a labelled and titled graph of the queues
