import csv
with open('facup.csv') as csvfile:
    rdr = csv.reader(csvfile)
    for row in rdr:
        print(row[0]+" last won in "+ row[1])
        if int(row[1])%2 == 0 :
            print("♦ Year", row[1]+" Is an even number")