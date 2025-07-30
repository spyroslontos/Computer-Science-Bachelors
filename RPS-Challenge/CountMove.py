import random

def name():
    return ("c1722325")


def move(myMoves,opMoves):
    Rmoves = opMoves.count("R")
    Pmoves = opMoves.count("P")
    Smoves = opMoves.count("S")

    if Rmoves > Smoves and Rmoves > Pmoves:
        return "R"
    elif Smoves > Rmoves and Smoves > Pmoves:
        return "S"
    else:
        return "P"

