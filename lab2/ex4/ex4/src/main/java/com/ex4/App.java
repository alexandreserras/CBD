package com.ex4;

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
import com.mongodb.client.result.InsertOneResult;
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
    // Função para inserir restaurante 
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
    // função para update
    public static int editar(MongoCollection<Document> collection,Bson filter, Bson d) {
        UpdateResult result = collection.updateMany(filter, d);
        System.out.println("Modified document count: " + result.getModifiedCount());
        return (int) result.getModifiedCount();
    }
    public static void main( String[] args )
    {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("cbd");
            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");
                MongoCollection<Document> collection = database.getCollection("restaurants");
                // ALINEA A
                //adicionar um ficheiro
                //criarRestaurante(collection);
                //PESQUISA NA DB
                FindIterable<Document> procura_res = find(collection,eq("nome", "Dj RONALDO  Pub And Restaurant"));
                for (Document document : procura_res){
                    System.out.println(document.toJson());
                }
                // UPDATE de um ficheiro
                Bson a =  eq("nome", "El Greco Diner");
                Bson updates =  Updates.combine(Updates.set("nome", "Dj RONALDO  Pub And Restaurant"));
                editar(collection,a,updates);
                System.out.println("Vou fechar");
                
                // ALINEA B
                System.out.println();
                System.out.println();
                System.out.println("Alinea b");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long t1 = timestamp.getTime();
                //fazer a pesquisa 
                find(collection,eq("nome", "Domino'S Pizza"));
                find(collection,eq("localidade", "Brooklyn"));
                long t2 = timestamp.getTime()-t1;
                collection.createIndex(Indexes.ascending("localidade"));
                collection.createIndex(Indexes.ascending("gastronomia"));
                collection.createIndex(Indexes.text("nome"));
                long t3 = timestamp.getTime();
                //fazer a pesquisa 
                find(collection,eq("nome", "Domino'S Pizza"));
                find(collection,eq("localidade", "Brooklyn"));
                long t4 = timestamp.getTime()-t3;
                if (t4 < t2 ){
                    System.out.println("COM OS INDEXES É MAIS RAPIDO");
                }
                else{
                    System.out.println("SEM OS INDEXES É MAIS RAPIDO");
                }
                // Alinea C
                System.out.println();
                System.out.println();
                System.out.println("Alinea c");
                ////9. Indique os restaurantes que não têm gastronomia "American", tiveram uma (ou mais) pontuação superior a 70 e estão numa latitude inferior a -65.
                Bson filter = Filters.and(Filters.lt("address.coord.0", -65),
                     Filters.gt("grades.score",70), Filters.ne("gastronomia","American"));
                     ;
                MongoCursor<Document> cursor = collection.find(filter).iterator();
                while(cursor.hasNext()){
                    System.out.println(cursor.next());
                }
                System.out.println();
                System.out.println();
                //16. Liste o restaurant_id, o nome, o endereço (address) e as coordenadas geográficas (coord) dos restaurantes onde o 2º elemento da matriz de coordenadas tem um valor superior a 42 e inferior ou igual a 52.
                Bson filter1 = Filters.and(Filters.gt("address.coord.1", 42),
                     Filters.lt("grades.score",52));
                     ;
                cursor= collection.find(filter1).projection(Projections.fields(Projections.include("restaurant_id","nome","address.rua","address.coord"),Projections.exclude("_id"))).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                //4. Indique o total de restaurantes localizados no Bronx.
                Bson filter2 = Filters.and(Filters.eq("localidade", "Bronx"));
                long res3= collection.countDocuments(filter2);
                System.out.println(res3);
                System.out.println();
                System.out.println();
                // 23. Apresente o número de gastronomias diferentes na rua "Fifth Avenue"
                
                cursor=collection.aggregate(Arrays.asList(
                    Aggregates.match(Filters.eq("address.rua", "Fifth Avenue")),
                    Aggregates.group("$address.rua", Accumulators.sum("count", 1))
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c.get("count"));
                }
                System.out.println();
                System.out.println();

               //30. Qual a localidade com mais restaurants 
                cursor=collection.aggregate(Arrays.asList(
                    Aggregates.group("$localidade", Accumulators.sum("total", 1)),
                    Aggregates.sort(Sorts.descending("total")),
                    Aggregates.limit(1)
                )).iterator();
                while(cursor.hasNext()){
                    Document c= cursor.next();
                    System.out.println(c);
                }
                System.out.println();
                System.out.println();
                System.out.println("D");
                System.out.println("countLocalidades()");
                System.out.println(countLocalidades( collection));
                System.out.println("countRestByLocalidade()");
                System.out.println(countRestByLocalidade( collection));
                System.out.println("getRestWithNameCloserTo()");
                List<String> alineaDLast  = (getRestWithNameCloserTo( collection , "Park"));
                System.out.println("Nome dos restaurantes contendo Park no nome");
                for (String x : alineaDLast){
                    System.out.println(x);
                }
                mongoClient.close();
            } catch (MongoServerException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }
        }catch (MongoServerException me) {
                System.err.println("Impossivel ligar à base de dados");
    }
    }
    public static int countLocalidades(MongoCollection<Document> collection){
        MongoCursor<Document> cursor = collection.aggregate(Arrays.asList(
                    Aggregates.group("$localidade")
                )).iterator();
        int ret =0;
        while(cursor.hasNext()){
            cursor.next();
            ret++;
        }
        return ret;
    }
    public static  Map<String, Integer> countRestByLocalidade(MongoCollection<Document> collection) {
        Map<String, Integer> ret = new HashMap<>();
        
        MongoCursor<Document> cursor = collection.aggregate(Arrays.asList(
            Aggregates.group("$localidade",Accumulators.sum("NumeroRestaurantes", 1))
        )).iterator();
        while(cursor.hasNext()){
            Document var = cursor.next();
            ret.put(""+var.get("_id"),Integer.parseInt(""+var.get("NumeroRestaurantes")));
        }
        return ret;
    }
    public static  List<String> getRestWithNameCloserTo(MongoCollection<Document> collection,String name){
        List<String> ret = new ArrayList<>();
        Bson filter = Filters.text(name);
        MongoCursor<Document> cursor= collection.find(filter).projection(Projections.fields(Projections.include("nome"),Projections.exclude("_id"))).iterator();
        while( cursor.hasNext()){
            ret.add(""+cursor.next().get("nome"));
        }
        return ret;
    }
}

