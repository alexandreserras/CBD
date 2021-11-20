Notas importantes do exercicio 4:
    <dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.1.1</version>
    </dependency>
Adicionar esta dependencia 

Como ligar a App à base de dados mongo : https://docs.mongodb.com/drivers/java/sync/current/fundamentals/connection/

Para correr :
     mvn package
     mvn exec:java -Dexec.mainClass="com.ex4.App"  -Dexec.cleanupDaemonThreads=false 



Criar indexs : https://docs.mongodb.com/drivers/java/sync/current/fundamentals/indexes/



   "grades": [{"date": {"$date": 1393804800000}, "grade": "A", "score": 2}, 
   {"date": {"$date": 1378857600000}, "grade": "A", "score": 6}, {"date": {"$date": 1358985600000}, 
   "grade": "A", "score": 10}, {"date": {"$date": 1322006400000}, "grade": "A", "score": 9},
    {"date": {"$date": 1299715200000}, "grade": "B", "score": 14}], 
