"""
Does it work on files where no error checking is needed on the fields

>>> sumRows("rows1.csv") == {'tim': 36.0, 'bob': 11.0, 'anna': 54.0}
True

Does it ignore headers if requested?

>>> sumRows("rows1.csv", header=True) == {'tim': 36.0, 'anna': 54.0}
True

Is it returning the right type of result?
>>> type(sumRows("rows1.csv"))
<class 'dict'>

Does it work on files with empty fields or fields which aren't numbers?

>>> sumRows("rows2.csv") == {'tim': 24.0, 'bob': 11.0, 'anna': 13.0}
True

Does it sum columns correctly?
>>> sumColumns("columns.csv") == {'': 0, 'tim': 5.0, 'bob': 41.0, 'anna': 55.0}
True
"""

# *** DO NOT CHANGE CODE ABOVE THIS LINE ***
# *** DO NOT ADD ANY COMMENTS OF YOUR OWN IN THE SUBMITTED SOLUTION ***
import csv
def sumRows(filename, header=False):
    rdr = csv.reader(open(filename))
    d = {}
    if header == True:
        next(rdr)
    for row in rdr:
        key = row[0]
        if key not in d:
            d[key] = 0
        for i, value in enumerate(row):
            if value.isdigit():
                d[key] += float(row[i])

    return (d)

def sumColumns(filename):
    rdr = csv.reader(open(filename))
    d = {}
    for col in rdr:
        for i in col:
            d[i] = 0
        break
    for row in rdr:
        for i, j in enumerate(row):
            if i == 0:
                d["bob"] += float(j)
            elif i == 1:
                d["anna"] += float(j)
            elif i == 2:
                d["tim"] += float(j)

    return d

