import random

def name():
    return ("c1722325")


def move(myMoves,opMoves):
    if len(myMoves) == 0:
        return random.choice(["R","P","S"])

    if (myMoves[-1] == "R" and opMoves[-1] == "S") or (myMoves[-1] == "S" and opMoves[-1] == "P") or (myMoves[-1] == "P" and opMoves[-1] == "R"):
        if myMoves[-1] == "R":
            return "R"
        elif myMoves[-1] == "P":
            return "P"
        elif myMoves[-1] == "S":
            return "S"

    elif (opMoves[-1] == "R" and myMoves[-1] == "S") or (opMoves[-1] == "S" and myMoves[-1] == "P") or (opMoves[-1] == "P" and myMoves[-1] == "R"):
        if opMoves[-1] == "R":
            return "R"
        elif opMoves[-1] == "P":
            return "P"
        elif opMoves[-1] == "S":
            return "S"
    else:
        return random.choice(["R","P","S"])
