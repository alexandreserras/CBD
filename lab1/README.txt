correr o ficheiro o ex1_2.py  e depois carregar os dados no reddis

python3 ex1_2.py
cat names_couting.txt | redis-cli

e o reddis da logo a informação de OK 26 vezes, que é o número de vezes que a instrução é corrida (1 por letra)
