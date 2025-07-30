import random
def move(myMoves, opMoves):
    while len(myMoves) < 300:
        return random.choice(["R","P","S"])
    r = opMoves.count("R")
    p = opMoves.count("P")
    s = opMoves.count("S")
    maxPlay = max(r,p,s)
    if maxPlay== r:
        return random.choice(["P","P","R"])
    elif maxPlay ==p:
        return random.choice(["S","S","P"])
    else:
        return random.choice(["R","R","S"])


def name():
    return "c1615033"
