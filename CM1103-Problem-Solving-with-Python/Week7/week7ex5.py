import csv
with open('MultipleTourWinners.csv') as csvfile:
    rdr = csv.reader(csvfile)
    for row in rdr:
        if row[1] == "FRA":
            print("French", row[0], "won", row[2], "times.")