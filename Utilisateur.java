import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Utilisateur {

    public static void main(String[] args) {
        Robot r = new Robot("Bobby",100, 150, 20, 58, 20, 0.5, new LinkedList<String>());
        Scanner scanner = new Scanner(System.in);
        afficherEtatRobot(r);
        
        LinkedList<String> comp = ajouterComposantes(scanner);
        creerAction(comp, r);
    }

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(Scanner scanner){
        LinkedList<String> composantes = new LinkedList<String>(); 
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
    public static void creerAction(LinkedList<String> composantes, Robot robot) {
        Scanner scan = new Scanner(System.in);
        String action = "";
        boolean missingComponentAdded = false;
    
        while (!action.equals("None")) {
            System.out.print("Ajouter action: ");
            action = scan.nextLine();
            if (action.equals("Parler")) {
                if (composantes.contains("Haut-parleur")) {
                    System.out.print(robot.nom + " peut parler! \n");
                    robot.actions.add("Parler");
                } else {
                    System.out.print("Il vous manque la composante : Haut-parleur \n");
                    missingComponentAdded = true;
                }
            } else if (action.equals("Ecouter")) {
                if (composantes.contains("Micro")) {
                    System.out.print(robot.nom + " peut ecouter! \n");
                    robot.actions.add("Micro");
                } else {
                    System.out.print("Il vous manque la composante : Micro \n");
                    missingComponentAdded = true;
                }
            } else if (action.equals("Deplacer")) {
                if (composantes.contains("Roue")) {
                    System.out.print(robot.nom + " peut se deplacer! \n");
                    robot.actions.add("Roue");
                } else {
                    System.out.print("Il vous manque la composante : Roue \n");
                    missingComponentAdded = true;
                }
            }
    
            if (missingComponentAdded) {
                composantes = ajouterComposantes(scan);  // Pass the Scanner object
                missingComponentAdded = false;
            }
        }
    
        scan.close();
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
