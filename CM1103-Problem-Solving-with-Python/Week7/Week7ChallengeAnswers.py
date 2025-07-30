def doughnut(n):
    #Base Case
    if n == 6 or n == 9 or n == 20:
        return True
    elif n < 6:
        return False
    else:
        return doughnut(n-6) or doughnut(n-9) or doughnut(n-20)


for i in range(1,101):
    if doughnut(i) == False:
        print(i)