#Calculane a^n where n is even
def recPower(a,n):
    if n == 1:
        return a
    elif n%2 == 0:
        return recPower(a,n/2)*recPower(a,n/2)
    elif n%2 == 1:
        return (recPower(a,n//2)*recPower(a,n//2)*recPower(a,1))


print("Function", recPower(3,19))
print("Test", 3**19)