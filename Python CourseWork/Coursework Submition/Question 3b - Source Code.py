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


def doubleQueue(alpha, beta, time=480):
    time_arrival = 0
    time_served = 0
    time_served2nd = 0
    current_time = 0
    maxQ = 0
    Q_length = 1
    Q_length2nd = 1
    # Initializes variables used
    while current_time < time:  # Simulates Time
        if time_arrival < time_served and time_arrival < time_served2nd:

            if Q_length <= Q_length2nd: # Chooses shortest Queue
                current_time = current_time + time_arrival
                time_served = time_served - time_arrival
                Q_length = Q_length + 1
                maxQ = max(maxQ, Q_length)
            else:
                current_time = current_time + time_arrival
                time_served2nd = time_served2nd - time_arrival
                Q_length2nd = Q_length2nd + 1
                maxQ = max(maxQ, Q_length2nd)
            time_arrival = nextTime(alpha)  # Simulates Random Customer arrival time

        else:
            if time_served < time_served2nd: # Served different Queues
                time_arrival = time_arrival - time_served
                current_time = current_time + time_served
                Q_length = Q_length - 1
                time_served = nextTime(beta)  # Simulates Random Service time
            else:
                time_arrival = time_arrival - time_served2nd
                current_time = current_time + time_served2nd
                Q_length2nd = Q_length2nd - 1
                time_served2nd = nextTime(beta)  # Simulates Random Service time
        while True:
            if Q_length != 0 and Q_length2nd != 0: # Checks for lengths of queues
                break
            else:
                current_time = current_time + time_arrival
                if Q_length <= Q_length2nd:
                    Q_length = Q_length + 1
                    maxQ = max(maxQ, Q_length)
                else:
                    Q_length2nd = Q_length2nd + 1
                    maxQ = max(maxQ, Q_length2nd)
                time_arrival = nextTime(alpha)  # Simulates Random Customer arrival time

    return maxQ


# Sketching the graphs

singleX = []
singleY = []
for i in range(11,110):
    singleX.append(i/10)
    random.seed(101)
    singleY.append(singleQueue(i/10,4,480))


doubleX = []
doubleY = []
for i in range(11,110):
    doubleX.append(i/10)
    random.seed(101)
    doubleY.append(doubleQueue(i/10,4,480))

# Creating lists with the coordinates used

fig = plt.figure("Two Teller Improvement")
ax = fig.add_subplot(1, 1, 1)

plt.plot(singleX, singleY, "b-", label="One Teller")
plt.plot(doubleX, doubleY, "g-", label="Two Tellers")

plt.title("MaxQ Change With Second Teller\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("MaxQ length")
ax.legend(loc="upper right")
plt.show()
# Plotting a labeled and titled graph of both queues
