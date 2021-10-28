package  com.ex4b.app;

import java.util.Set;

import redis.clients.jedis.Jedis;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 

public class App 
{
    public static void main( String[] args )
    {   
        
        SimplePostb s = new SimplePostb();
       
        
        try {
            File myObj = new File("nomes-pt-2021.csv");
            Scanner myReader = new Scanner(myObj);
            String nome;
            int rank;
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(";");
                nome=data[0].toLowerCase();
                rank=Integer.parseInt(data[1]);
                s.saveUser(nome,rank);

            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
       
        
        Scanner sc =new Scanner(System.in);
        Set<String> users ;
        boolean x =true;
        while(x){
            System.out.print("Search for ('Enter' for quit): ");
            
            String a = sc.nextLine().toLowerCase();
            
            if (a.equals("")){
                x=false;
                break;
            }
            else{
                users=s.getUser(a);
                for (String c : users){
                    if (c.matches(a+".*"))
                        System.out.println(c);
                }
            }
        }
        sc.close();
        
    }
}
