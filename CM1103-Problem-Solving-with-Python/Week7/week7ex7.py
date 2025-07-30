import csv
with open('MultipleTourWinners.csv') as csvfile:
    rdr = csv.reader(csvfile)
    for i,row in enumerate(rdr, start=1):
        if int(row[2])>= 3 :
            print("In row", i, row[0], "won!")