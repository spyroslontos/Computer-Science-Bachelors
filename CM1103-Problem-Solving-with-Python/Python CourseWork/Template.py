import random
import math
import csv


def nextTime(mean):
    return -mean * math.log(1 - random.random())


def theoreticalMeanQueueLength(alpha, beta):
    """
    >>> theoreticalMeanQueueLength(10, 2)
    0.25
    >>> theoreticalMeanQueueLength(5, 1)
    0.25
    >>> theoreticalMeanQueueLength(4, 2)
    1.0
    >>> theoreticalMeanQueueLength(5.5, 1.3)
    0.3095238095238095
    >>> theoreticalMeanQueueLength(5.5, 0)
    0.0
    >>> theoreticalMeanQueueLength(1, 1)
    -1
    >>> type(theoreticalMeanQueueLength(10, 2))
    <class 'float'>
    """
    if alpha == beta or alpha == 0:
        return (-1)
    else:
        return ((beta/alpha)/(1-(beta/alpha)))  #Uses a given formula to return a value


def checkMean(mean, iterations=10000):
    """
    >>> random.seed(57)
    >>> checkMean(5, 10)
    6.309113224728108
    >>> random.seed(57)
    >>> checkMean(5, 1000)
    4.973347344130324
    >>> random.seed(57)
    >>> checkMean(5, 100000)
    4.988076126529703
    >>> random.seed(57)
    >>> checkMean(195, 100000)
    194.53496893466047
    >>> random.seed(57)
    >>> checkMean(195)
    196.71853828860912
    >>> random.seed(57)
    >>> checkMean(31)
    31.273203522804728
    >>> type(checkMean(31, 5))
    <class 'float'>
    """
    mean_var = 0
    for i in range(1, iterations + 1): #Iterates a number of times
        mean_var += nextTime(mean)

    return (mean_var / iterations)  #Finds the average of the standard deviations


def readExperimentParameters(filename):
    """
    >>> readExperimentParameters('experiments.csv')[0]
    (10, 2, 480)
    >>> len(readExperimentParameters('experiments.csv'))
    5
    >>> readExperimentParameters('experiments.csv')[3]
    (20, 2, 480)
    >>> readExperimentParameters('experiments.csv')[2]
    (20, 15, 240)
    >>> type(readExperimentParameters('experiments.csv')[1])
    <class 'tuple'>
    """
    rdr = csv.reader(open(filename)) #Opens the File
    next(rdr) #Skips the header of the File
    file_list = []
    filtered_list = []
    #Initializing lists used

    for row in rdr:
        file_list.append(row)
    temp_list = [list(filter(None, lst)) for lst in file_list] #Filters the empty elements in a List

    for i in temp_list:
        if i[3] != " h": #Checks in the 4th Column for the letter h
            i.remove(i[3]) #If h is not found the element is removed
        else:
            i[2] = int(i[2]) * 60 #If h is found it multiplies the element in column 3 with 60(minutes)
            i.remove(i[3])

    for elements in temp_list:
        elements = list(map(int, elements)) #Makes all the elements from strings to integers
        filtered_list.append(tuple(elements)) #Makes the lists to tuples

    return (filtered_list)


def singleQueue(alpha, beta, time=480):
    """
    >>> random.seed(57)
    >>> singleQueue(10, 3, 480)
    3
    >>> random.seed(101)
    >>> singleQueue(5, 3, 480)
    6
    >>> random.seed(101)
    >>> singleQueue(5, 3)
    6
    >>> random.seed(935)
    >>> singleQueue(10, 9, 280)
    10
    >>> type(singleQueue(10, 9, 280))
    <class 'int'>
    """
    time_arrival = 0
    time_served = 0
    current_time = 0
    maxQ = 0
    Q_length = 1
    #Initializes variables used
    while current_time < time: #Simulates Time
        if time_arrival < time_served:
            time_served = time_served - time_arrival
            current_time = current_time + time_arrival
            Q_length = Q_length + 1
            maxQ = max(maxQ, Q_length)
            time_arrival = nextTime(alpha) #Simulates Random Customer arrival time
        else:
            time_arrival = time_arrival - time_served
            current_time = current_time + time_served
            Q_length = Q_length - 1
            time_served = nextTime(beta) #Simulates Random Service time
        while True:
            if Q_length != 0: #Checks for the length of the queue
                break
            else:
                current_time = current_time + time_arrival
                Q_length = Q_length + 1
                maxQ = max(maxQ, Q_length)
                time_arrival = nextTime(alpha)  #Simulates Random Customer arrival time

    return (maxQ)
