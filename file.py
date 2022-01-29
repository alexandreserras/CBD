import csv
from random import randint, random

file = open('NBA_player_of_the_week.csv')
csvreader = csv.reader(file)
header = next(csvreader)
print(header)
rows = []
for row in csvreader:
    print(row)
    if row[2]=="":
        a= randint(0,1)
        if (a==0):
            row[2]= "West"
        else:
            row[2]="East"
    row.append("JOGADOR DA SEMANA" )  
    rows.append(row)
file.close()
with open('NBA_player_of_the_week.csv', 'w', newline="") as file:
    csvwriter = csv.writer(file) # 2. create a csvwriter object
    csvwriter.writerow(header) # 4. write the header
    csvwriter.writerows(rows) # 5. write the rest of the data
file.close()