mark = int(input("Enter mark: "))
if mark <= 50 or mark > 60:
    print("Result is 2:2")


#Print the first 10 square numbers
for n in range(1,11): #(1,11)
    print(n*n)

#Fibonacci sequence
def f(n):
    if n == 1 or n == 2:
        return 1
    else:
        return f(n-1) + f(n-2)