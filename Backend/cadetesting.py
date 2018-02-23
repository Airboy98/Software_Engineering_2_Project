import csv
import argparse
import os
import sys
import pymysql
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

##creates a arugment runnable python
# try:
#     parser = argparse.ArgumentParser(description='upload excel csv file')
#     parser.add_argument('upload',help='put the path address as the arugemnt Ex. cadetesting.py C:\\Users\excel.csv')
# except:
#     print("Didnt work")
#     exit()
# args = parser.parse_args()
##end of making python arugment runnable




conn = pymysql.connect(host="localhost",user="root",passwd="0000",db="salesdata",autocommit=True)
y = conn.cursor()

#with open(sys.argv[1], 'r') as csvfile:
with open('C:\\Users\cadew\Documents\GitHub\Software2project\DataImport.csv', 'r') as csvfile:
    timeReader = csv.reader(csvfile, delimiter=' ', quotechar='|')
    lst = []
    for row in timeReader:
       lst.append(''.join(row).split(","))

del lst[0]
print(lst[0])
size = len(lst)
#y.execute("""INSERT INTO dailyinformation VALUES('1mon','2mon','3','4',560)""")

    #for x in range(0,size):
x=0
print(lst[x][0],lst[x][2][0:3],whatdayyear(lst[x]),whatdaymonth(lst[x]),lst[x][1])
thing=lst[x][0]
DayOfWeek= whatdaymonth(lst[x])
DayOfYearByWeek=whatdayyear(lst[x])
DayOfMonth=lst[x][2][0:3]
GrossSales=lst[x][1]
print(thing,DayOfWeek,DayOfYearByWeek,DayOfMonth,GrossSales)
try:
    y.execute("""INSERT INTO dailyinformation(thing,DayOfWeek,DayOfYearByWeek,DayOfMonth,GrossSales) VALUES ('%s','%s','$s','$s','$s') """,{thing,DayOfWeek,DayOfYearByWeek,DayOfMonth,GrossSales})
    print("made it here")
        #y.execute("""INSERT INTO 'dailyinformation' (lst[x][0],whatdaymonth(lst[x]),whatdayyear(lst[x]),lst[x][2][0:3],lst[x][1])""")
except pymysql.InternalError as error:
        code, message = error.args
        print
        ">>>>>>>>>>>>>", code, message

exit()