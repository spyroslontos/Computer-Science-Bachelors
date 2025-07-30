import random
import logging
import sys


def match(aString, bString):
    a = __import__(aString)
    b = __import__(bString)

    a_moves = []
    b_moves = []

    a_wins = 0
    b_wins = 0
    draws = 0

    for i in range(1000):
        a_move = a.move(a_moves, b_moves)
        b_move = b.move(b_moves, a_moves)

        a_moves.append(a_move)
        b_moves.append(b_move)

        print (a_wins, draws, b_wins)

        print (a_move + " vs " + b_move)

        if (a_move == "R" and b_move == "S") or (a_move == "S" and b_move == "P") or (a_move == "P" and b_move == "R"):
            a_wins += 1
        elif (b_move == "R" and a_move == "S") or (b_move == "S" and a_move == "P") or (b_move == "P" and a_move == "R"):
            b_wins += 1
        else:
            draws += 1


    if not fairPlayCheck(a_wins, b_wins, draws, a_moves, b_moves):
        print(a +" vs "+ b + 'Cheating going on' )


    print (a_wins, draws, b_wins)
    if a_wins>b_wins:
        winner = [aString]
    elif b_wins>a_wins:
        winner = [bString]
    else:
        # winner = [aString, bString]
        winner = [random.choice([aString, bString])]
    print (a.name() + " vs " + b.name() +"    "  + " ".join(winner) + " wins")
    return winner


def fairPlayCheck(a_wins, b_wins, draws, a_moves, b_moves):
    print("there will be checking here and elsewhere to ensure no rules are broken")
    print("the arguments to this function will be adapted")
    print("The winner's code will be examined")
    return True



if __name__ == "__main__":
    match("SpyRPS", "RafiRPS")

