package domain.logic.Menu;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Outils.GestionDates;
import domain.logic.Robot.Action;
import domain.logic.Robot.Activite;

import javax.swing.*;

public class MenuGererTacheActivite {
    /*private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = DbControleur.getDbControleur();
    private MenuUtilisateur menuUtil;
    public MenuGererTacheActivite() throws IOException, ParseException {
    }

    //Taches
    public void gererMesTaches(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("********** Gestion des taches **********");
        System.out.println("1- Créer une tâche");
        System.out.println("2- Allouer une tache a un robot");
        System.out.println("3- Revenir au menu");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> creerTache(scanner, pseudo);
            case "2" -> allouerTacheRobot(scanner, pseudo);
            case "3" -> {
                menuUtil = new MenuUtilisateur();
                menuUtil.menuUtilisateur(scanner, pseudo);
            }
        }
    }

    public void creerTache(Scanner scanner, String pseudo) throws IOException, ParseException {
        ArrayList<String> actions = new ArrayList<>();
        System.out.print("Entrez le nom de la tâche à créer : ");
        String nomAction = scanner.nextLine();
        String acts = controlleurUtilisateurs.recuprerListeAction(pseudo);
        if (acts.equals("")){
            System.out.println("Vous ne possédez aucune action.");
        } else {
            System.out.println("Parmi vos actions, laquelle/lesquelles voulez-vous associer a cette tache?: ");
            System.out.println(acts);
            System.out.print(">>> Votre choix : ");
            String decision = "Y";
            while (decision.toUpperCase().equals("Y")) {
                String act = scanner.nextLine();
                actions.add(act);
                System.out.println("Voulez-vous rajouter une action a cette tache?(Y/N)");
                decision = scanner.nextLine();
            }
            ArrayList<String> list = controlleurUtilisateurs.creerTache(nomAction, actions, pseudo);
            if (!list.isEmpty()){
                System.out.println("Vous ne possédez pas les actions suivantes : ");
                list.forEach(System.out::println);
                System.out.println("Vous pouvez les creer dans le menu de gestion de votre flotte.");
                System.out.println("");
            } else {
                System.out.println("Vous avez creer la tache : " + nomAction);
            }
        }
        menuUtil = new MenuUtilisateur();
        menuUtil.menuUtilisateur(scanner, pseudo);
    }

    public void allouerTacheRobot(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("Quel est le numero de serie du robot auquel vous voulez allouer une tache ?");
        String numeroSerieRobot = scanner.nextLine();
        System.out.println("Quel est le nom de la tache a allouer?");
        String tache = scanner.nextLine();
        if (controlleurUtilisateurs.allouerTacheRobot(pseudo, numeroSerieRobot, tache))
            System.out.println("La tache a ete allouée avec succès");
        else{
            System.out.println("La tache n'a pas pu être allouée car vous ne possédez pas le robot ou la tache indiquée");
        }
        gererMesTaches(scanner, pseudo);
    }


    //Activites
    public void gererMesActivites(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("********** Gestion des activites **********");
        System.out.println("1- Créer une activites");
        System.out.println("2- Rejoindre une activite");
        System.out.println("3- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> menuCreerActivite(scanner, pseudo);
            case "2" -> menuRejoindreActivite(pseudo, scanner);
            case "3" -> new MenuUtilisateur().menuUtilisateur(scanner, pseudo);
        }
    }

    public void menuCreerActivite(Scanner scanner, String pseudo) throws ParseException, IOException {
        boolean continuer;
        ArrayList<String> listeTache = new ArrayList<>();
        ArrayList<String> listeInteret = new ArrayList<>();
        System.out.println(" ");
        System.out.print("Nom de l'activité : ");
        String nomActivite = scanner.nextLine();
        String dateDebut;
        String dateFin;
        do {
            continuer = false;
            System.out.print("Veuillez entrer une date de debut (en format yyyy-MM-dd) : ");
            dateDebut = scanner.nextLine();
            System.out.print("Veuillez entrer une date de fin (en format yyyy-MM-dd) : ");
            dateFin = scanner.nextLine();

            if (GestionDates.validerDate(dateDebut) && GestionDates.validerDate(dateFin)){
                if (GestionDates.veriferSiDateRealiste(dateDebut)){
                    if (!GestionDates.verifierCoherenceDate(dateDebut, dateFin)){
                        System.out.println("La date debut doit etre avant la date de fin.");
                        continuer = true;
                    }
                } else {
                    System.out.println("La date de debut doit etre au moins aujourd'hui !");
                    continuer = true;
                }
            } else {
                System.out.println("Date invalide, veuillez reessayer.");
                continuer = true;
            }

        } while (continuer);

        do {
            String taches = controlleurUtilisateurs.recuprerListeTache(pseudo);
            if (taches.isEmpty()){
                System.out.println("Vous ne possedez aucune tache.");
                continuer = false;
            } else {
                continuer = false;
                System.out.print("Veuillez entrer une tache a rajouter a l'activite par les taches suivantes que vous possedez: ");
                System.out.println(taches);
                listeTache.add(scanner.nextLine());
                System.out.print("Voulez-vous ajouter une autre tache ? (répondez par 'oui' ou 'non'): ");
                String reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")){
                    continuer = true;
                }
            }
        } while (continuer);

        if (!listeTache.isEmpty()){
            do {
                continuer = false;
                System.out.print("Veuillez entrer un interêt : ");
                listeInteret.add(scanner.nextLine());
                System.out.print("Voulez-vous ajouter une autre interet ? (répondez par oui ou non): ");
                String reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")){
                    continuer = true;
                }
            } while (continuer);

            if (controlleurUtilisateurs.creerActivites(pseudo, nomActivite, dateDebut, dateFin, listeTache, listeInteret)) {
                System.out.println("L'activitée a été bien créée (:");
            } else {
                System.out.println("Cette activitée existe déjà...");
            }
        }

        gererMesActivites(scanner, pseudo);
    }

    public void menuRejoindreActivite(String pseudo, Scanner scanner) throws IOException, ParseException {
        String liste = controlleurUtilisateurs.recupererListeActivites();
        if (!liste.isEmpty()){
            System.out.println("Veuillez le nom de l'activite a rejoindre parmi les activites suivantes :");
            System.out.println(liste);
            System.out.print(">>> Votre choix : ");
            String nomActivite = scanner.nextLine();
            String result = controlleurUtilisateurs.rejoindreActivite(pseudo, nomActivite);
            if (result.equals("true")){
                menuAjouterRobotActivite(scanner, pseudo, nomActivite);
            } else if (result.equals("true2")) {
                System.out.println("Vous avez deja rejoint cette activitee.");
            } else {
                System.out.println("L'activite que vous tentez de rejoindre n'existe pas.");
            }
        } else {
            System.out.println("Oups.... On dirait bien que vous ne possedez aucune activitee...");
        }
        gererMesActivites(scanner, pseudo);
    }

    private void menuAjouterRobotActivite(Scanner scanner, String pseudo, String nomActivite){
        String robots = controlleurUtilisateurs.recupererListeRobot(pseudo);
        if (!robots.isEmpty()){
            ArrayList<String> numeroRobots = new ArrayList<>();
            String choix;
            do {
                System.out.println("Quel robot voulez vous rajouter a cette activitee ? (veuillez entrer le numero de serie du robot) :");
                System.out.println(robots);
                System.out.print(">>> Votre choix : ");
                numeroRobots.add(scanner.nextLine().trim());
                System.out.print("Voulez-vous ajouter un autre robot ? (Y/N) : ");
                choix = scanner.nextLine();
            } while (choix.toUpperCase().equals("Y"));
            if (!controlleurUtilisateurs.ajouterRobotActivite(numeroRobots, nomActivite, pseudo)){
                System.out.println("Tous les robots n'ont pas pu etre rajoutes. Certains n'ont pas pu etre ajoutes " +
                        "car le numero de serie est inexistant ou ils sont occupees dans une autre activitee.");
            } else {
                System.out.printf("Tous les robots ont ete rajoutes avec succes !");
            }
        } else {
            System.out.println("Vous ne possedez aucun robot disponible pour rejoindre une activite.");
        }
        System.out.println("Vous avez rejoint l'activite: " + nomActivite + " avec succes.");
    }*/
}