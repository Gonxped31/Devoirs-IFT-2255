package domain.logic.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Robot.Action;

public class MenuGererTacheActivite {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = new DbControleur();
    private MenuUtilisateur menuUtil;

    public MenuGererTacheActivite() throws IOException {
    }

    //Tache
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
            Action act = new Action(a, null, "0");
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


    //Activite
    public void gererMesActivites(Scanner scanner, String pseudo){
        System.out.println("1- Créer une activites");
        System.out.println("2- Rejoindre une activite");
        System.out.println("3- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix){ 
            case "1": 
                menuCreerActivite(scanner, pseudo);
                break;
            case "2":
                menuRejoindreActivite(pseudo, scanner);
                break;
            case "3":
                menuUtil.menuUtilisateur(scanner, pseudo);
        }
    }

    public void menuCreerActivite(Scanner scanner, String pseudo){
        boolean continuer = false;
        ArrayList<String> listeTache = new ArrayList<>();
        System.out.println(" ");
        System.out.print("Nom de l'activité : ");
        String nomActivite = scanner.nextLine();
        System.out.println("Date de debut : ");
        String dateDebut = scanner.nextLine();
        System.out.println("Date de fin : ");
        String dateFin = scanner.nextLine();
        do {
            System.out.print("Veuillez entrer une tache : ");
            listeTache.add(scanner.nextLine());
            System.out.print("Voulez-vous ajouter une autre tache ? (répondez par oui ou non): ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")){
                continuer = true;
            }
        } while (continuer);

        do {
            System.out.print("Veuillez entrer un interêt : ");
            listeTache.add(scanner.nextLine());
            System.out.print("Voulez-vous ajouter une autre tache ? (répondez par oui ou non): ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")){
                continuer = true;
            }
        } while (continuer);

        if (controlleurUtilisateurs.creerActivites(pseudo, nomActivite, dateDebut, dateFin, listeTache)) {
            System.out.println("L'activitée a été bien créée (:");
        } else {
            System.out.println("Cette activitée existe déjà...");
        }
        menuUtil.menuUtilisateur(scanner, pseudo);
    }

    public void menuRejoindreActivite(String pseudo, Scanner scanner){
        System.out.println("Veuillez choisir une a rejoindre parmi les suivantes activites parmi les suivantes");
        String nomActivite = scanner.nextLine();
        controlleurUtilisateurs.rejoindreActivite(pseudo, nomActivite);
    }
}