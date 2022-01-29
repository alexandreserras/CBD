package com.ex5;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonValue;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.MongoException;
import com.mongodb.MongoServerException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import java.nio.file.DirectoryStream.Filter;
import java.sql.Timestamp;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App 
{
    public static int criarRestaurante(  MongoCollection<Document>  database) {
        //Ir buscar os valores com um scanner e depois colocar nestes locais
        // CRIO O RESTAURANTE SEM AVALIAÇÕES E TENHO O "PROBLEMA RESOLVIDO "
        String building,  rua,zipcode,  localidade,  gastronomia,  nome;
        double longitude, latitude ;
        String id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o building");
        building=sc.nextLine().trim();
        System.out.println("Indique a rua");
        rua=sc.nextLine().trim();
        System.out.println("Indique o zipcode");
        zipcode=sc.nextLine().trim();
        System.out.println("Indique a localidade");
        localidade = sc.nextLine().trim();
        System.out.println("Indique a gastronomia");
        gastronomia=sc.nextLine().trim();
        System.out.println("Indique o nome");
        nome=sc.nextLine().trim();
        System.out.println("Indique a latitude");
        latitude=Double.parseDouble(sc.nextLine());
        System.out.println("Indique a longitude");
        longitude=Double.parseDouble(sc.nextLine());
        System.out.println("Indique o id do restaurante ");
        id=(sc.nextLine().trim());
        String[] cordenadas = new String [2];

        cordenadas[0]=""+latitude;
        cordenadas[1]=""+longitude;
        String escolha="";

        Document restaurant = new Document("address", new Document("building", building).append("coord", Arrays.asList(latitude, longitude))
                                .append("rua", rua)
                                .append("zipcode", zipcode))
                        .append("localidade", localidade)
                        .append("gastronomia", gastronomia)
                        .append("nome", nome)
                        .append("restaurant_id", id);
        Document grades= new Document();
        boolean controlo = false;
        while (true){
            System.out.println("Já tem alguma grade ? S OU N ");
            escolha = sc.nextLine().toUpperCase();
            if (escolha.equals("S")){
                System.out.println("Indique a grade");
                String grade = sc.nextLine().trim();
                System.out.println("Indique a date");
                String date = sc.nextLine().trim();
                System.out.println("Indique o  score");
                String score = sc.nextLine().trim();
                grades.append("grade", grade);
                grades.append("date", date);
                grades.append("score", score);
                restaurant.append("grades", grades);
                controlo=true;

            }
            else{
                if (! controlo){
                    restaurant.append("grades", Collections.emptyList());

                }
                break;
            }
        }
        System.out.println(restaurant.toJson());
        try{
            InsertOneResult result= database.insertOne(restaurant);
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        }
        catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
        sc.close();
        return 1;
    }
    // função para procura 
    public static FindIterable<Document> find( MongoCollection<Document> collection,Bson bson ){
        FindIterable<Document> doc = collection.find(bson);
        return doc;
        
    }
    
    public static void main( String[] args )
    {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("ex5");
            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");
                MongoCollection<Document> collection = database.getCollection("weather");
                

                //ALINEA C )
                System.out.println();
                System.out.println();
                System.out.println("Alinea c");
                System.out.println("Indicar quais os ids , temperatura , tamanho das ondas e  as coordinates, dos locais onde a temperatura do ar é superior a 5.5 e ondas com  altura igual ou superior a 5 metro");
                ////Indicar quais os ids , temperatura , tamanho das ondas e  as coordinates, dos locais onde a temperatura do ar é superior a 5.5 
                //e ondas com  altura igual ou superior a 5 metro
                Bson filter = Filters.and(
                     Filters.gte("waveMeasurement.waves.height",5), Filters.gt("airTemperature.value",5.5));
                     ;
                MongoCursor<Document>  cursor= collection.find(filter).projection(Projections.fields(Projections.include("position.coordinates","waveMeasurement.waves.height","airTemperature.value"))).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                }
                System.out.println();
                System.out.println();
		
                System.out.println("2-> Indicar onde as callLetters são PLAT, qualityControlProcess é V020 e a sua qualidade de variabilidade tem valor igual ou superior a 9 qual é a sky skyCondition, nao deve ser apresentado o _id");
                filter = Filters.and(
                    Filters.eq("callLetters","PLAT"), Filters.eq("qualityControlProcess","V020"), Filters.gte("visibility.variability.quality","9"));
                    ;
                cursor= collection.find(filter).projection(Projections.fields(Projections.include("skyCondition"),Projections.exclude("_id"))).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                }
                System.out.println();
                System.out.println();
                System.out.println("3-> Indicar onde as sections contem  AG1,AY1,GF1,MW1  SA1, UA1,MD1, UG1 e a sua coordenada da longitude é maior ou igual a 60.9 ,os resultados devem ser ordenados por ordem crescente de  timestamp e apenas escolhido 5");
                filter = Filters.and(
                    Filters.and(Filters.in("sections","AG1"),Filters.in("sections","AY1"),Filters.in("sections","MD1"),Filters.in("sections","GF1"),Filters.in("sections","MW1"),Filters.in("sections","UG1"),Filters.in("sections","SA1"),Filters.in("sections","UA1") ),
                    Filters.gte("position.coordinates.1", 60.9))
                    ;
                Bson f = Sorts.descending("ts");
                int limit;
                cursor= collection.find(filter).projection(Projections.fields(Projections.exclude("_id"))).sort(f).limit(5).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                    
                }
                
                System.out.println();
                System.out.println();
                System.out.println("4-> Descobrir qual é a velocidade do vento mais rapido onde a elevação foi de 9999 ");
                filter = Filters.eq("elevation",9999);
                
                f = Sorts.descending("wind.speed.rate");
                cursor= collection.find(filter).projection(Projections.fields(Projections.include("wind.speed.rate"),Projections.exclude("_id"))).sort(f).limit(1).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                    
                }

                System.out.println();
                System.out.println();
                System.out.println("5-> Onde as ondas tiverem um periodo igual a5 e a pressure maior que 1300 ");
                filter = Filters.and(
                        Filters.gte("waveMeasurement.waves.period",5),
                     Filters.gt("pressure.value", 1300)
                    )
                    ;
                //Filters.ne("estimatedWaterDepth",0) ,
                cursor= collection.find(filter).projection(Projections.fields(Projections.exclude("_id"))).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                    
                }
                
                System.out.println();
                System.out.println();
                System.out.println("6-Nos locais onde a pressão atmosferica tiver uma alteração, com tendency de codigo  2  ,o waveMeasurement for method I e a estimatedWaterDepth for 999 mostrar as suas call callLetters e timestamp");
                filter = Filters.and(
                    Filters.eq("atmosphericPressureChange.tendency.code","2"),
                     Filters.eq("precipitationEstimatedObservation.estimatedWaterDepth", 999),
                     Filters.eq("waveMeasurement.method", "I")

                    )
                    ;
                //Filters.ne("estimatedWaterDepth",0) ,
                cursor= collection.find(filter).projection(Projections.fields(Projections.include("callLetters","ts"),Projections.exclude("_id"))).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                    
                }
                // ALINEA D )
                /*Aggregates.match(Filters.eq("address.rua", "Fifth Avenue")),
                Aggregates.group("$st",),
                Aggregates.sort(Sorts.descending("total")),
                Aggregates.limit(1)*/
                System.out.println();
                System.out.println();
                System.out.println("1-> Para cada st, indicar quais os seus dados sobre ventos ");
                cursor=collection.aggregate(Arrays.asList(
                    Aggregates.group("$st", Accumulators.addToSet("winds", "$wind") )
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                System.out.println("2-> Para as mesmas coordinates,, onde a longitude for maior que 51  , indicar qual foi o somatorio das temperaturas do ar e ordenar por ordem decrescente");
                cursor=collection.aggregate(Arrays.asList(
                    Aggregates.match(Filters.gte("position.coordinates.1", 51)),
                    Aggregates.group("$position.coordinates", Accumulators.sum("temps", "$airTemperature.value") ),
                    Aggregates.sort(Sorts.descending("temps"))

                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                System.out.println("3-> Para o mesmo st, a soma da altura das  ondas esta entre 15 e 30 e a soma da velocidade dos ventos 10");              
                cursor=collection.aggregate(Arrays.asList(
                    
                    Aggregates.group("$st", Accumulators.sum("wind_speed", "$wind.speed.rate"),Accumulators.sum("waves_altura", "$waveMeasurement.waves.height") ),
                    Aggregates.match(Filters.and(Filters.gte("wind_speed", 10), Filters.gt("waves_altura",15), Filters.lt("waves_altura",30)))
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                System.out.println("4->Qual o st com mais ocorrencias ");
                cursor=collection.aggregate(Arrays.asList(
                    
                    Aggregates.group("$st", Accumulators.sum("total", 1) ),
                    Aggregates.sort(Sorts.descending("total")),
                 Aggregates.limit(1)

                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
               
                System.out.println();
                System.out.println();
                System.out.println("5->Quando o array das sections for igual indicar os diversos st  ");

                cursor=collection.aggregate(Arrays.asList(
                    
                    Aggregates.group("$sections",  Accumulators.addToSet("st's", "$st"))
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                System.out.println("6->Para cada valor atmosphericCondition  quantas coordinates existem ");
                cursor=collection.aggregate(Arrays.asList(
                    
                    Aggregates.group("$pastWeatherObservationManual.atmosphericCondition.value",  Accumulators.addToSet("cords's", "$position.coordinates"))
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                mongoClient.close();
            } catch (MongoServerException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }
        }catch (MongoServerException me) {
                System.err.println("Impossivel ligar à base de dados");
    }
    }
    
}
