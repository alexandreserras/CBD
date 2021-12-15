package com.ex3;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
       try{

        Session session = cluster.connect("videos");
        System.out.println("Consegui fazer connect");
        selectAll(session,"User");
        insertUser(session,"bms", "Bruno Serras", "brunos@ua.pt");
        
        System.out.println("VERIFICAR O INSERT");
        select(session,"User");
        System.out.println("VERIFICAR O UPDATE");
        updateUser(session,"bms", "Brumo M Serras");
        select(session,"User");
        System.out.println("VERIFICAR O DELETE");
        deleteUser(session,"bms");
        selectAll(session,"User");


        System.out.println("QUERYS DO EXERCÍCIO ANTERIOR");
        System.out.println("Pergunta 5 ");
        ResultSet a = session.execute("select * from Video_User where autor = 'serras' and created_on > '2021-11-03';");
        for (Row row: a){
            System.out.println(row);
        }
        System.out.println("Pergunta 6 ");
        a = session.execute("select * from video limit 10;");
        for (Row row: a){
            System.out.println(row);
        }
        System.out.println("Pergunta 7 ");
        
        a = session.execute("select username from follower where video_id=1;");
        for (Row row: a){
            System.out.println(row);
        }
        System.out.println("Pergunta 10 ");
        a = session.execute("select * from video where id in (1,2,3);");
        for (Row row: a){
            System.out.println(row);
        }

        session.close();
        cluster.close();
       }
       catch (Exception e){
           System.out.println("ERROOOOOOO");
           System.exit(0);
       }
    }

    private static void updateUser(Session session, String username, String string) {
        String query= "UPDATE User  SET nome='"+ string+"' WHERE username='"+username+"';";
        session.execute(query);
    }

    private static void deleteUser(Session session, String username) {
        String query= "Delete from User where   username='"+username+"';";
        session.execute(query);
    }

    private static void insertUser(Session session, String username,String name, String email) {
        String query= "insert into User (username,nome,email,created_on) values"+ "('" + username+"','"+ name+"','"+email+"','"+Timestamp.from(Instant.now())+"');";
        session.execute(query);
    }


    private static void select(Session session, String string) {
        Scanner sc = new Scanner(System.in);
        String username="";
        int id;
        String rating="";
        ResultSet res=null;
        switch (string){
            case "User":
                username="bms";
                res = session.execute("select * from "+ string + " where username='"+username+"'");
                break;
            case "Video":
                System.out.println("Insert do id: ");
                id=1;
                res = session.execute("select * from "+ string + " where id="+id);  
                break;
            case "Follower":
                System.out.println("Insert do video_id:");
                id=Integer.parseInt(sc.nextLine());
                res = session.execute("select * from "+ string + " where video_id="+id);  
                break;
            case "Evento":
                System.out.println("Insert do video_id:");
                id=Integer.parseInt(sc.nextLine());
                System.out.println("Insert do username:");
                username=sc.nextLine();
                res = session.execute("select * from "+ string + " where user_username='"+username+ 
                "' and video_id="+ id);  
                break;
            
            case "ratings":
                System.out.println("Insert do video_id:");
                id=Integer.parseInt(sc.nextLine());
                
                res = session.execute("select * from "+ string + " where video_id="+id);  
                break;
            case "Video_User":
                System.out.println("Insert do username:");
                username=sc.nextLine();
                res = session.execute("select * from "+ string + " where user_username='"+username+"'");  
                break;
            case "Comentario_User":
                System.out.println("Insert do username:");
                username=sc.nextLine();
                res = session.execute("select * from "+ string + " where user_username='"+username+"'");  
                break;
            
            case "Comentario_Video":
                System.out.println("Insert do video_id:");
                id=Integer.parseInt(sc.nextLine());
                res = session.execute("select * from "+ string + " where video_id="+id);  
                break;
            default :
                System.out.println("ERRROOO table não existe");
                System.exit(0);
        }
        
        if (res != null){
            for (Row row: res){
                System.out.println(row);
            }
        }
        
    }

    private static void selectAll(Session session, String string) {
        ResultSet res = session.execute("select * from "+ string);

        for (Row row: res){
            System.out.println(row);
        }

    }
}

