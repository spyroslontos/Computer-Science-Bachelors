import csv
import math
import random
import re

def readExperimentParameters(filename):
    rdr = csv.reader(open(filename))
    next(rdr)
    file_list = []
    filtered_list = []
    for row in rdr:
        file_list.append(row)
    temp_list = [list(filter(None, lst)) for lst in file_list]

    for i in temp_list:
        if i[3] != " h":
            i.remove(i[3])
        else:
            i[2] = int(i[2]) * 60
            i.remove(i[3])

    print(temp_list)
    for elements in temp_list:
        elements = list(map(int,elements))
        filtered_list.append(tuple(elements))

    return (filtered_list)

#print(readExperimentParameters('experiments.csv')[3])
#(20, 2, 480)

#-------------------------------------------#

def nextTime(mean):
    return -mean * math.log(1 - random.random())

def checkMean(mean, iterations=10000):
    mean_var = 0
    for i in range(1, iterations+1):
        mean_var += nextTime(mean)

    return (mean_var / iterations)


#random.seed(57)
#print(checkMean(5, 1000))
#4.973347344130324

#------------------------------------------#

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


random.seed(57)
print(singleQueue(10, 3, 480))
#3