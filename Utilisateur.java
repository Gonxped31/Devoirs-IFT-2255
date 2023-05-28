import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Utilisateur {
    public static void main(String[] args) {
        Robot r = new Robot("Bobby",100, 150, 20, 58, 20, 0.5);
        //EcrafficherEtatRobot(r);
        ajouterComposantes();
    }

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(){
        LinkedList<String> composantes = new LinkedList<String>(); 
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("None")){
            System.out.print("Ajouter composante: ");
            input = scanner.nextLine();
            if (!input.equals("None")){
                composantes.add(input);
            }
        }

        scanner.close();
        System.out.println(composantes);
        return composantes;
    }

    //A partir de ce qui a été scanner, on produit une tache
    public void creerAction(){
        
    }

    //Prends un tableau d'action en parametre, et output 
    public void creerTache(){

    }

    public static void afficherEtatRobot(Robot robot){ 
        System.out.println("Nom: " + robot.nom);
        System.out.println("Coordonnées: " + "X: " + robot.X  + "; Y: " + robot.Y);
        System.out.println("Vitesse: " + robot.vitesse);
        System.out.println("Batterie: " + robot.batterie);
        System.out.println("CPU: " + robot.cpu);
        System.out.println("Memoire: " + robot.memoire);
    }

}
