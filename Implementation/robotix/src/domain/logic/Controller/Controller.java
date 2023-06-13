package domain.logic.Controller;
import java.io.*; 
import java.util.Scanner;

public class Controller {

    public void read(String fichierCSV, String index, String colonne) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("Implementation\\robotix\\src\\domain\\logic\\BaseDeDonnees\\" + fichierCSV + ".csv"));  
        sc.useDelimiter(",");   //sets the delimiter pattern  
        while (sc.hasNext()){  
            //System.out.println("Working");
            //if (index.equals(sc.next())){
            System.out.print(sc.next()); 
            //} //find and returns the next complete token from this scanner  
        }   
        sc.close();  //closes the scanner  
        } 
    }
