import random
import math
import time
import sys


# ----------------------- File Reading ----------------------- #


participants = {}
participantNums = []
weightsEdges = []

# inputFile = str(sys.argv[1])
inputFile = str('Formula_One_1984.wmg')

# Reading the file and storing its contents
with open(inputFile, 'r', ) as wmgfile:

    # Reading the 1st line as the number of participants
    line = wmgfile.readline()
    numParticipants = int(line)

    # Storing Participants in dictionary (number: 'Name')
    # and storing Participant Numbers in a ranking list
    for participant in range(1, numParticipants+1):
        line = wmgfile.readline()
        participants[int(line.split(",")[0])] = (line.split(",")[1].strip())
        participantNums.append(int(line.split(",")[0]))

    # Skipping data generation line
    dataGeneration = wmgfile.readline()
    line = wmgfile.readline()

    # Storing <weights, edges> until end of file
    # (weight, participantA, participantB)
    while line != '':
        weightsEdges.append([int(i) for i in line.strip().split(",")])
        line = wmgfile.readline()


# ----------------------- Functions Used ----------------------- #


def KemenyScore(weightsEdgesList, participantNums):

    kemenyScore = 0

    # Iterating through list of <weights,edges>
    for weightEdges in weightsEdgesList:
        # If ranking does not agree with element the weight is added to the score
        if participantNums.index(weightEdges[1]) > participantNums.index(weightEdges[2]):
            kemenyScore += weightEdges[0]

    return kemenyScore


def incrimentalKemenyScore(weightsEdgesList, currentState, neighbouringState, previousScore):

    incrementedScore = previousScore
    swappedParticipants = []
    participantsBetween = []

    # Find the participants that have been swapped
    for i in range(len(currentState)):
        if currentState[i] != neighbouringState[i]:
            swappedParticipants.append(currentState[i])

    # Find the participants between the swapped ones
    for i in range(currentState.index(swappedParticipants[0])+1, currentState.index(swappedParticipants[1])):
        participantsBetween.append(currentState[i])

    # Iterating through list of <weights,edges>
    for weightEdges in weightsEdgesList:

        # Checks if swapped participant has won over a participant in between
        if (weightEdges[1] in swappedParticipants) and (weightEdges[2] in participantsBetween):

            # Checks to see if the new neighbouring State agrees with the picked <weight,edges>
            if neighbouringState.index(weightEdges[1]) > neighbouringState.index(weightEdges[2]):
                incrementedScore += weightEdges[0]
            else:
                incrementedScore -= weightEdges[0]

        # Checks if swapped participant has lost over a participant in between
        if (weightEdges[2] in swappedParticipants) and (weightEdges[1] in participantsBetween):

            # Checks to see if the new neighbouring State agrees with the picked <weight,edges>
            if neighbouringState.index(weightEdges[1]) > neighbouringState.index(weightEdges[2]):
                incrementedScore += weightEdges[0]
            else:
                incrementedScore -= weightEdges[0]

        # Checks if swapped participant has won over the other swapped participant
        if (weightEdges[1] in swappedParticipants) and (weightEdges[2] in swappedParticipants):

            # Checks to see if the new neighbouring State agrees with the picked <weight,edges>
            if neighbouringState.index(weightEdges[1]) > neighbouringState.index(weightEdges[2]):
                incrementedScore += weightEdges[0]
            else:
                incrementedScore -= weightEdges[0]

    # returning the new incremented Kemeny Score
    return incrementedScore


def Neighbour(weightsEdgesList, participantNums):

    # Randomly chosen <weight, edges>
    choice = random.choice(weightsEdgesList)

    # If ranking does not agree with the randomly chosen element the position
    # of the 2 participants in the ranking is swapped.
    if participantNums.index(choice[1]) > participantNums.index(choice[2]):
        participantNums[participantNums.index(choice[1])], participantNums[participantNums.index(choice[2])] = \
            participantNums[participantNums.index(choice[2])], participantNums[participantNums.index(choice[1])]
    # else we use recursion until we find one that does not agree
    else:
        Neighbour(weightsEdgesList, participantNums)

    # returning the list of the neighbour which is the new Ranking after the swap.
    return participantNums


# ----------------------- Simulated Annealing ----------------------- #


# Simulated Annealing Parameters
lengthTemp = 10000
initTemp = 1000
a = 0.999
num_non_improve = 2000
non_improve_iter = 0
numUphillMoves = 0

minimumCost = KemenyScore(weightsEdges, participantNums)
bestRank = participantNums[:]

# Start Time to calculate algorithm's runtime
startTime = time.time()

# Simulated Annealing Algorithm
for i in range(lengthTemp):

    # !! Delete Before Submission !! #
    if i % 1000 == 0:
        print("Min Cost:", minimumCost)

    # Retrieves Ranking & Cost Function of the Current State
    currentState = participantNums
    currentCost = KemenyScore(weightsEdges, currentState)

    # Retrieves Ranking & Incremental Cost Function of the Neighbouring State
    neighbouringState = Neighbour(weightsEdges, participantNums[:])
    neighbouringCost = incrimentalKemenyScore(weightsEdges, currentState, neighbouringState, currentCost)

    # Compares to see if their is an improvement in cost
    if neighbouringCost <= currentCost:
        # If their is improvement change the ranking to the new neighbouring state's ranking
        participantNums = neighbouringState

        # Storing the minimum Kemeny Score and best Rank found
        if neighbouringCost < minimumCost:
            minimumCost = neighbouringCost
            bestRank = participantNums[:]
            non_improve_iter = 0

    else:
        # If their is no improvement make an Uphill move with the Probability of (ΔE)
        if random.random() < math.exp(-(neighbouringCost - currentCost)/initTemp):
            participantNums = neighbouringState

            # Increment when an uphill move is made
            numUphillMoves += 1

        # Increment when a non-improving move is made
        non_improve_iter += 1

    # Cooling Schedule
    initTemp = a * initTemp

    # Stopping Criterion
    if num_non_improve == non_improve_iter:
        print("Stopping criterion reached")
        break


# End Time to calculate algorithm's runtime
endTime = time.time()

# ----------------------- Final Outputs ----------------------- #

# Best Ranking Found in Table Form
print("     -      Final Ranking      -      ")
print("--------------------------------------")
for position, participant in enumerate(bestRank, 1):
    print("| Position:", position, " "*(2-len(str(position))), "|",
          participants[participant], " "*(18-len(participants[participant])), "|")
    print("--------------------------------------")

# Minimum Kemeny Score
print("Minimum Kemeny Score:", minimumCost)

# Algorithm's Runtime
print("Algorithm's Runtime:", round((endTime - startTime)*1000), "ms")

# Number of Uphill moves
print("Number of Uphill moves:", numUphillMoves)
