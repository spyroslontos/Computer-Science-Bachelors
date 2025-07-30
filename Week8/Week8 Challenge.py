import re
ranking = {}

def passcodeMatch(code):
    result = re.match("[0-9]{4}", code)
    if result != None:
        passSecurity(code)
    else:
        print("Not Matched")


def passSecurity(code):
    range = []
    for num in code:
        range.append(num)
    ranking[code] = (int(max(range)) - int(min(range)))
    return ranking
    #print(code,"| Range:",int(max(range)) - int(min(range)))


passcodeMatch("2416")
passcodeMatch("3151")
passcodeMatch("8228")
passcodeMatch("9999")

print("Rank for Schemes")
for i, code in enumerate(sorted(ranking.items(),reverse=True, key=lambda item:(item[1], item[0])),start=1):
    print (code[0], ":", i)


