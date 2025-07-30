import matplotlib.pyplot as plt


def theoreticalMeanQueueLength(alpha, beta):
    if alpha == beta or alpha == 0:
        return (-1)
    else:
        return ((beta/alpha)/(1-(beta/alpha)))
        # Uses the formula given to return a value


sketchx = []  # Initializes x axis
sketchy = []  # Initializes y axis

for i in range(11, 101, 1):
    sketchx.append(i/10)  # List of the x values [1.1, 1.2, ...]
    sketchy.append(theoreticalMeanQueueLength((i/10), 1))
    # List of the y values calculated by the theoreticalMeanQueueLength Function

plt.plot(sketchx, sketchy, "b-")  #Plots the graph
plt.title("Queue Length Change\n")
plt.xlabel("Mean Gap of Customers arriving")
plt.ylabel("Mean Queue Length")
plt.show()
