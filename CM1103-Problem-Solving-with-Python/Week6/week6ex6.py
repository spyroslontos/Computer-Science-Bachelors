def shapes(m,n):

    for i in range(m):
        for i in range(n):
            print ("* ",end="")
        print()


print ("Enter your values for columns and rows to draw a shape")

rows = int(input("Rows: "))
columns = int(input("Columns: "))

shapes(rows,columns)