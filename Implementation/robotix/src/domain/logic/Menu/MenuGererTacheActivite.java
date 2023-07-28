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
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = DbControleur.getDbControleur();
    private MenuUtilisateur menuUtil;
    private Activite activite = new Activite();

    public MenuGererTacheActivite() throws IOException {
    }

    //Tache
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
            if (list.size() != 0){
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


    //Activite
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
            if (taches.equals("")){
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
        Date date = new Date();
        System.out.println("Veuillez choisir une a rejoindre parmi les suivantes activites parmi les suivantes");
        System.out.println("1- Ballade en forêt (du 02/07/2000 au 02/08/2000)");
        System.out.println("2- Course de rally (du 05/07/2000 au 02/08/2001)");
        System.out.println("3- Soiree netflix and chill (du 09/08/2010 au 02/012/2013)");
        System.out.println("4- Gaming night (du 02/07/2004 au 02/08/2006)");
        System.out.println("5- Hokey sur glace (du 14/10/2023 au 02/08/2026)");
        String nomActivite = scanner.nextLine();

        System.out.println ("Entrez une date de début de l'activité (format dd/MM/yyyy) : ");
        String dateDebut = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = dateFormat.parse(dateDebut);
        } catch (ParseException e) {
            System.out.println("Format de date invalide !");
        }

        switch (nomActivite){
            case "1" -> nomActivite = "Ballade en forêt (du 02/07/2000 au 02/08/2000)";
            case "2" -> nomActivite = "Course de rally (du 05/07/2000 au 02/08/2001)";
            case "3" -> nomActivite = "Soiree netflix and chill (du 09/08/2010 au 02/012/2013)";
            case "4" -> nomActivite = "Gaming night (du 02/07/2004 au 02/08/2006)";
            case "5" -> nomActivite = "Hokey sur glace (du 14/10/2023 au 02/08/2026)";
        }

        controlleurUtilisateurs.rejoindreActivite(pseudo, activite);
        System.out.println("Vous avez rejoint l'activite: " + nomActivite);
        menuUtil = new MenuUtilisateur();
        menuUtil.menuUtilisateur(scanner, pseudo);
    }
}