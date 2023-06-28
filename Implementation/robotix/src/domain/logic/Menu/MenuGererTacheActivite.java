package domain.logic.Menu;

import java.util.ArrayList;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Action;

public class MenuGererTacheActivite {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private MenuUtilisateur menuUtil;

    public void gererMesTaches(Scanner scanner, String pseudo){
        System.out.println("1- Créer une tâche");
        System.out.println("2- Allouer une tache a un robot");
        System.out.println("3- Revenir au menu principal");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix){ 
            case "1": 
                creerTache(scanner, pseudo);
                break;
            case "2": 
                allouerTacheRobot(scanner, pseudo);
                break;
            case "3":
                menuUtil.menuUtilisateur(scanner, pseudo);
                break;
        }
    }

    public void creerTache(Scanner scanner, String pseudo){
        ArrayList<Action> actions = new ArrayList<Action>();
        System.out.println("Quelles actions voulez-vous creer?");
        System.out.println("Nom: ");
        String nomAction = scanner.nextLine();
        System.out.println("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?: ");
        String decision = "Y";
        while (decision.toUpperCase().equals("Y")) {
            System.out.println("Entrez une action:");
            String a = scanner.nextLine();
            Action act = new Action(a, null);
            actions.add(act);
            System.out.println("Voulez-vous rajouter une action a cette tache?(Y/N)");
            decision = scanner.nextLine();
        }
        controlleurUtilisateurs.creerTache(pseudo, nomAction, actions);
    }

    public void allouerTacheRobot(Scanner scanner, String pseudo){
        System.out.println("A quel robot voulez-vous allouer une tache");
        String robot = scanner.nextLine();
        System.out.println("Quel est le nom de la tache a allouer?");
        String tache = scanner.nextLine();
        if (controlleurUtilisateurs.allouerTacheRobot(pseudo, robot, tache))
            System.out.println("La tache a ete allouée avec succès");
        else{
            System.out.println("La tache n'a pas pu être allouée car vous ne possédez pas le robot ou la tache indiqué");
        }
        menuUtil.menuUtilisateur(scanner, pseudo);
    }

}