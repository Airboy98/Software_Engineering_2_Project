import csv
with open('test.csv', newline='') as csvfile:
    timeReader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    for row in timeReader:
        print(', '.join(row))

##https://docs.python.org/3/library/csv.html
