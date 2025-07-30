import math
from decimal import Decimal, localcontext
n = 228068205667  #14737 * 15475891
def is_prime(n):
    if n == 1:
        return (False)
    if n == 2:
        return(True)
    if n>2 and n%2==0:
        return (False)

    max_divisor = math.floor(math.sqrt(n))
    for d in range(3,int(1+ max_divisor),2):
        if n%d==0:
            return (False)

    return (True)


for i in range (1,int(math.sqrt(n))): #Range is until the sqrt of the number
    if is_prime(i) == True:
        print(i)
        with localcontext() as cont:
            cont.prec = 310
            if (Decimal(n) / Decimal(i))%1 == 0:
                print(i, " IS A FACTOR OF N")
                break

# 135096617 IS A FACTOR
