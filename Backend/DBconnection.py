#!/usr/bin/python

import pymysql
from passlib.hash import pbkdf2_sha256


conn = pymysql.connect(host='localhost', port=3306, user='root', passwd='0000', db='accounts',autocommit=True)

cur = conn.cursor()

print "(1) Make new account"
print "(2) check password"

select = raw_input()

if select=="1":
    print "input new username"
    uname = raw_input()
    print "input new password"
    pass_nh = raw_input()
    print "input service level of user"
    ID = raw_input()

    phash = pbkdf2_sha256.hash(pass_nh)
    #print("INSERT INTO `users` (`username`,`passhash`,`position`) VALUES ('" + uname + "','" + phash + "','" + ID + "');")
    cur.execute("INSERT INTO `users` (`username`,`passhash`,`position`) VALUES ('" + uname + "','" + phash + "','" + ID + "');")
    #conn.commit()


elif select=="2":
    print "input username"
    uname = raw_input()
    print "input password to check"
    pass_check = raw_input()

    #print("SELECT * FROM users WHERE username LIKE " + uname)

    cur.execute("SELECT * FROM users WHERE username LIKE '" + uname + "'")
    temp = ""
    for row in cur:
        print row
        temp = row

    name = temp[1]
    pass_hash = temp[2]
    role = temp[3]

    pass_value = pbkdf2_sha256.verify(pass_check, pass_hash)

    print(name)
    print(pass_hash)
    print(role)
    print ""
    print(pass_value)

else:
    print "Fail"

cur.close()
conn.close()
