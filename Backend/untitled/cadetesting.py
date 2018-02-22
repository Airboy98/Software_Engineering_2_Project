import csv
import argparse
def whatdaymonth(lst):
    day=int(lst[0][3]+lst[0][4])
    if(day<7):
        return 1
    elif(day<14):
        return 2
    elif(day<22):
        return 3
    elif(day<29):
        return 4
    elif(day):
        return 5
def whatdayyear(lst):
    day=whatdaymonth(lst)
    month=int(lst[0][0]+lst[0][1])
    return day*month
try:
    parser = argparse.ArgumentParser(description='upload excel csv file')
    parser.add_argument('upload',help='do a thing',type=str)
except:
    print("Didnt work")
    exit()

args = parser.parse_args()
print(args)
with open('C:\\Users\cadew\OneDrive\Documents\GitHub\Software2project\DataImport.csv', newline='') as csvfile:
    timeReader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    lst = []
    for row in timeReader:
       lst.append(''.join(row).split(","))

del lst[0]
print(lst[0])
size = len(lst)

for x in range(0,size):
    print(lst[x][0],whatdaymonth(lst[x]),whatdayyear(lst[x]),lst[x][2][0:3],lst[x][1])

