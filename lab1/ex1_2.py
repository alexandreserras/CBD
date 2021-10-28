
dict={} #aqui vai ficar as letras guardadas 
with open("names.txt","r") as file:
    for  palavra in file:
        if palavra[0].upper() not in dict:
            
            dict[palavra[0].upper()]=1
        else:
            
            dict[palavra[0].upper()]+=1

with open("names_couting.txt","w") as file:
    for k in dict:
        file.write("SET {} {} \n".format(k,dict[k]))
    
print(len(dict))