pallets = [750, 387, 291, 712, 100, 622, 109, 750, 282]
max_weight = 3000
weight = []
pallets.sort(key=int,reverse=True)


def lorry_weight(x):

    while (sum(weight)+x) < max_weight:
        weight.append(x)
        print(x)
        return(weight)
    else:
        print ("Lorry will overload. Weight it carries is: ", sum(weight))

for i in pallets:
    lorry_weight(i)