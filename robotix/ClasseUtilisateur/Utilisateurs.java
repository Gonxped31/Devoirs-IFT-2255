import java.util.LinkedList;
import java.util.Scanner;

public class Utilisateurs {
    public static void main(String[] args) {
        LinkedList<String> composantes = new LinkedList<String>();
        LinkedList<String> activites = new LinkedList<String>();
        activites.add("Se deplacer et dire 'Allo'");
        activites.add("Se deplacer et ecouter");
        Robot r = new Robot("Bobby",100, 150, 20, 58, 20, 0.5, new LinkedList<String>(), activites);
        afficherEtatRobot(r);
        LinkedList<String> comp = ajouterComposantes(composantes,new Scanner(System.in));
        creerAction(comp, r);
        voirActivitesMaintenues(r);
    }

    //Kamen 

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(LinkedList<String> composantes, Scanner scanner){
        String input = "";
        while (!input.equals("None")){
            System.out.print("Ajouter composante: ");
            input = scanner.nextLine();
            if (!input.equals("None")){
                composantes.add(input);
            }
        }

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
                    robot.actions.add("Ecouter");
                } else {
                    System.out.print("Il vous manque la composante : Micro \n");
                    missingComponentAdded = true;
                }
            } else if (action.equals("Deplacer")) {
                if (composantes.contains("Roue")) {
                    System.out.print(robot.nom + " peut se deplacer! \n");
                    robot.actions.add("Deplacer");
                } else {
                    System.out.print("Il vous manque la composante : Roue \n");
                    missingComponentAdded = true;
                }
            }
    
            if (missingComponentAdded) {
                composantes = ajouterComposantes(composantes,scan);  // Pass the Scanner object
                missingComponentAdded = false;
            }
        }
    
        scan.close();
        System.out.println(robot.actions);
    }
    

    //Prends un tableau d'actions en parametre, et output 
    //Prends une liste chainées des actions voulues, et ouput une liste chainées avec la tâches ajoutées
    public void creerTache(Robot r, LinkedList<String> actionsVoulues){
        if (actionsVoulues.contains("Parler") && actionsVoulues.contains("Deplacer")){
            //r.activites;
            //TODO
        }
    }

    public static void voirActivitesMaintenues(Robot r){
        System.out.println("\n\n Voici les activitées maintenues par " + r.nom);
        for (String act : r.activites){
            System.out.println("    - " + act);
        }
    }

    public static void afficherEtatRobot(Robot robot){ 
        System.out.println("Nom: " + robot.nom);
        System.out.println("Coordonnées: " + "X: " + robot.X  + "; Y: " + robot.Y);
        System.out.println("Vitesse: " + robot.vitesse + "km/h");
        System.out.println("Batterie: " + robot.batterie + "%");
        System.out.println("CPU: " + robot.cpu + "%");
        System.out.println("Memoire: " + robot.memoire + "%");
    }



    // Samir


    public static void enregistrerRobot() {
        // TODO
    }

    public static void afficherMetriquesFlotte() {
        // TODO
    }

    public static void gestionDesProblèmes() {
        // TODO
    }

    public static void participerActivités() {
        // TODO
    }

    public static void trouverFournisseurs() {
        // TODO

    }

}