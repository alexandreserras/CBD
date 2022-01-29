from neo4j import GraphDatabase
class Neo4j:
    def __init__(self, uri, user, pwd):
        self.__uri = uri
        self.__user = user
        self.__pwd = pwd
        self.__driver = None
        try:
            self.__driver = GraphDatabase.driver(self.__uri, auth=(self.__user, self.__pwd))
            print(f"LIGADO À BASE DE DADOS à db {uri} com o user: {user} ")
        except Exception as e:
            print("Failed to create the driver:", e)
        
    def close(self):
        if self.__driver is not None:
            self.__driver.close()
        
    def query(self, query, db=None):
        assert self.__driver is not None, "Driver not initialized!"
        session = None
        response = None
        try: 
            session = self.__driver.session(database=db) if db is not None else self.__driver.session() 
            response = list(session.run(query))
        except Exception as e:
            print("Query failed:", e)
        finally: 
            if session is not None:
                session.close()
        return response


if __name__ == "__main__":
    conn = Neo4j(uri="bolt://localhost:7687", user="neo4j", pwd="nba")
    #Linha que faz o insert dos dados
    #conn.query("load csv with headers from 'file:///NBA_player_of_the_week.csv' as linha merge (j: Jogador{nome:linha.Player, draft_ano:linha.Draft_Year}) merge (equipa: Equipa{nome:linha.Team}) merge (equipaDraft: Equipa{nome:linha.draftTeam}) merge (conf : Conferencia{nome:linha.Conference}) merge (premio: Premio{nome:linha.Premio}) merge (j)-[:Jogou]->(equipa) merge (j)-[:Universidade]-> (equipaDraft) merge (equipa)- [:Pertence]->(conf) merge (j)-[:recebe {data:linha.Date, posição:linha.Position, peso:linha.Weight, altura:linha.Height,idade:linha.Age,epocas:linha.Seasons_in_league,shortcut:linha.Season_short,epoca:linha.Season}]->(premio)")
    

    file =open("CBD_L44c_output.txt","w")  
    file.write("1-> Quais os jogadores da equipa Miami Heat já ganharam um prémio?\n")

    query="match (p:Jogador)-[:Jogou]->(e:Equipa {nome:'Miami Heat'})  match (p) - [:recebe] -> (premio:Premio)   return Distinct p.nome"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 2-> Quais os jogadores que já jogaram na numa equipa onde o Lebron James jogou já ganharam um prémio, estes jogadores devem ter jogado na confencia WEST      \n")
    query=("match (p:Jogador {nome:'LeBron James'})-[:Jogou]->(e:Equipa)  <-[:Jogou]- (p2:Jogador)   where p2 <> p match (e)- [:Pertence]->(conf:Conferencia {nome:'West'}) return Distinct p2")
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 3jogador-> Que jogadores a jogar na posição C receberam premios na época 2015-2016")
    query=("match (p:Jogador)- [:recebe {posição:'C', epoca:'2015-2016'}] ->(premio:Premio) return Distinct p")
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write(" \n 4- Quais jogadores que foram draft em 2009 e pertenciam ao Arizona State já ganharam prémios e quantos prémios ganharam \n")
    query="match (p:Jogador {draft_ano :'2009'}) - [:Universidade ]- (e:Equipa {nome:'Arizona State'})  match (p)- [:recebe]->(reward:Premio) return p.nome,count(reward)"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 5- Quais equipas possuem mais do que 1 jogador que na sua quinta época  ganharam prémios")
    query="match (p:Jogador ) - [:Jogou ]- (e:Equipa) <-[:Jogou] -(p2:Jogador) where p<>p2 match (p)-[:recebe {epocas:'5'}] ->(r1:Premio) match (p2)-[:recebe {epocas:'5'}] ->(r2:Premio)  return Distinct e.nome as Nome_Equipa"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 6- Quais os jogadores, que já ganharam prémios a jogar pelo Los Angeles Lakers  e Miami heat entre os anos 2009 e 2018 \n")
    query="match (e:Equipa {nome:'Miami Heat'}) <-[:Jogou]- (p:Jogador) -[:Jogou]->(e2:Equipa {nome:'Los Angeles Lakers'}) match (p)-[x:recebe ] ->(r:Premio) where x.shortcut >'2008' and x.shortcut<'2019' return distinct p.nome"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 7-> Qual o jogador que se encontra a uma distancia maior da equipa Miami Heat e qual o tamanho dessa distancia \n")
    query="match (p1:Equipa {nome:'Miami Heat'}), (p2:Equipa) where p1<>p2 match p=shortestPath((p1)-[*]-(p2)) return p2.nome, length(p) as distancia order by distancia Desc limit 1"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 8-> Qual o jogador da epoca 2016-2017 que coleciou mais prémios a jogar em 1 posição \n")
    query="match (n:Jogador) -[a:recebe {epoca:'2016-2017' }]->(p:Premio) return n.nome, count(p) as total , a.posição order by total desc limit 1"
    res=conn.query(query)
    file.write(query+'\n')
    file.write(str(res))
    file.write("\n 9-> ")

    file.close()
    #linha que faz o delete dos dados
    #conn.query("match (n) detach delete n")
    conn.close()
