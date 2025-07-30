import csv
def sumRows(filename):
    rdr = csv.reader(open(filename))
    next(rdr)
    for row in rdr:
        for i in row:
            print(i)
            #a = (x for x in i)
            #print (next(a))



def sumColumns(filename):
    rdr = csv.reader(open(filename))
    d = {}
    for col in rdr:
        for i in col:
            d[i] = 0
            print(d)
        break
    for row in rdr:
        for i,j in enumerate(row):
            if i == 0:
                d["bob"] += float(j)
            elif i == 1:
                d["anna"] += float(j)
            elif i == 2:
                d["tim"] += float(j)

    return d



print(sumRows("rows.csv"))
#sumColumns("columns.csv")
