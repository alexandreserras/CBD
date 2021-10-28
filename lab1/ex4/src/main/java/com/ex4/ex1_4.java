package main.java.com.ex4;


import java.util.Set;

import redis.clients.jedis.Jedis;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
/**
 * Hello world!
 *
 */
public class ex1_4 
{
    public static void main( String[] args )
    {   
        
        SimplePost s = new SimplePost();
        /* Apenas coloca no redis , só preciso de correr 1 vez
        try {
            File myObj = new File("names.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              s.saveUser(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        */
        /*
        Set<String> users =s.getUser();
        for (String x: users){
            System.out.println(x);
        }
        */
        Set<String> users ;
        boolean x =true;
        while(x){
            System.out.print("Search for ('Enter' for quit): ");
            Scanner sc =new Scanner(System.in);
            String a = sc.nextLine();
            
            if (a.equals("")){
                x=false;
                break;
            }
            else{
                users=s.getUser(a);
                System.out.println(users.size());
                for (String c : users){
                    System.out.println(c);
                }
            }
        }
    }
}

