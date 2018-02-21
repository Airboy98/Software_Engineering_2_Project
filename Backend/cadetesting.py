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
# try:
#     parser = argparse.ArgumentParser(description='upload excel csv file')
#     parser.add_argument('upload',help='put the path address as the arugemnt Ex. cadetesting.py C:\\Users\excel.csv')
# except:
#     print("Didnt work")
#     exit()
#
# args = parser.parse_args()
#
#
# with open(sys.argv[1], 'r') as csvfile:
#     timeReader = csv.reader(csvfile, delimiter=' ', quotechar='|')
#     lst = []
#     for row in timeReader:
#        lst.append(''.join(row).split(","))
#
# del lst[0]
# print(lst[0])
# size = len(lst)
#
# for x in range(0,size):
#     print(lst[x][0],whatdaymonth(lst[x]),whatdayyear(lst[x]),lst[x][2][0:3],lst[x][1])


conn = pymysql.connect(host="localhost",user="root",passwd="0000",db="salesdata",autocommit=True)
print('made it here')
x = conn.cursor()
print("made it here")
x.execute("""INSERT INTO apr VALUES('1mon',256)""")

print("didnt work")

exit()