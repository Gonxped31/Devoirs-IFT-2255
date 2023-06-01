import java.io.IOException;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

import javax.naming.ldap.SortControl;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.SingleSelectionModel;

import domain.logic.Fournisseur.Fournisseur;

//Faire import domain.logic.Fournisseur.Fournisseur;

public class Utilisateurs {
    String nom, prenom, pseudo, courriel, telephone;
    //static LinkedList<String> taches = new LinkedList<String>(); 
    public static void main(String[] args) {
       /* LinkedList<String> composantes = new LinkedList<String>();
        afficherEtatRobot(r);
        LinkedList<String> comp = ajouterComposantes(composantes, new Scanner(System.in)); 
        creerAction(comp, r);
        allouerTachesRobot(r);
        voirActivitesMaintenues(r);*/
        //LinkedList<Robot> robots = enregistrerRobot();
        //afficherMetriquesFlotte(robots);
        Robot r = new Robot("Bobby",100, 150, 20, 58, 20, 0.5,new LinkedList<String>(), new LinkedList<String>(), new LinkedList<String>(), new LinkedList<String>());
        //r.actions.add("Deplacer"); 
        LinkedList<String> taches = new LinkedList<String>(); 
        LinkedList<String> composantes = new LinkedList<String>(); //Les composantes que l'utilisateur a acheté
        composantes.add("Deplacer");  
        Scanner scanner = new Scanner(System.in);
        //creerTaches(scanner, taches);
        //allouerTachesRobot(r, composantes,scanner, taches);
        participerActivites(r, scanner);
        scanner.close();
        //trouverFournisseurs();
    }

    public Utilisateurs(String nom, String prenom, String pseudo, String courriel, String telephone){
        
    }

    public static void menu() {
        System.out.println("******************** Menu ********************");
    }

//----Kamen----------------------------------------------------------------------------------------------------------------

    //Scan les composantes de l'action que l'utilisateur
    public static LinkedList<String> ajouterComposantes(LinkedList<String> composantes, Scanner scanner){
        String input = "";
        while (!input.equals("None")){
            System.out.print("Ajouter composante (Bras), (Roue), (Haut-parleur), (Micro), (None):");
            input = scanner.nextLine();
            if (!input.equals("None")){
                composantes.add(input);
            }
        }
        System.out.println(composantes);
        return composantes;
    }

    /* REMARQUE :
        Si un robot prend des actions en paramètre, pourquoi est-ce qu'on a besoin d'un robot en paramètre pour créer des action (pour créer un robot, on a besoin d'action
        et pour creer des actions, on a besoin d'un robot...) 
    */

    //A partir de ce qui a été scanner, on produit une tache
    public static void creerAction(Scanner scanner,LinkedList<String> composantes, Robot robot) {
        String action = "";
        boolean missingComponentAdded = false;
    
        while (!action.equals("None")) {
            System.out.print("Ajouter action: ");
            action = scanner.nextLine();
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
            } else if (action.equals("Attraper")){
                if (composantes.contains("Bras")) {
                    System.out.print(robot.nom + " peut se deplacer! \n");
                    robot.actions.add("Attraper");
                } else {
                    System.out.print("Il vous manque la composante : Bras \n");
                    missingComponentAdded = true;
                }
            }
    
            if (missingComponentAdded) {
                composantes = ajouterComposantes(composantes,scanner);  // Pass the Scanner object
                missingComponentAdded = false;
            }
        }
    
        System.out.println(robot.actions);
    }
    
    //Prends une liste chainées des actions voulues, et ouput une liste chainées avec la tâches ajoutées
    public static void allouerTachesRobot(Robot r, LinkedList<String> composantes, Scanner scanner, LinkedList<String> taches){
        boolean end = true;
        while (end){
            System.out.println("Veuillez entrez la tache a allouer a " + r.nom + ":");
            String tache = scanner.nextLine();
            if (tache.equals("Faire des zigzags") && taches.contains(tache) && r.actions.contains("Deplacer")){
                r.taches.add(tache);
                System.out.println(r.nom + " peut maintenant + " + tache +"!!!");
                end = false;
            } else if (tache.equals("Faire des zigzags") && !taches.contains(tache)) {
                System.out.println("Tache n'a pas ete ajoutee car elle n'existe pas");
                creerTaches(scanner, taches);
            } else if (tache.equals("Faire des zigzags") && taches.contains(tache) && !r.actions.contains("Deplacer")){
                System.out.println("Il manque l'action 'Deplacer', veuillez l'ajouter");
                creerAction(scanner, composantes, r);
            }        
            
            if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && r.actions.contains("Deplacer") && r.actions.contains("Attraper")){
                r.taches.add(tache);
                System.out.println(r.nom + " peut maintenant " + tache +"!!!");
                end = false;
            } else if (tache.equals("Attraper et relacher un objet") && !taches.contains(tache) && r.actions.contains("Deplacer") && r.actions.contains("Attraper")) {
                System.out.println("Tache n'a pas ete ajoutee car elle n'existe pas");
                creerTaches(scanner, taches);
            }
            else if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && !r.actions.contains("Deplacer") && r.actions.contains("Attraper")){
                System.out.println("Il manque l'action 'Deplacer', veuillez l'ajouter");
                creerAction(scanner, composantes, r);
            }else if (tache.equals("Attraper et relacher un objet") && taches.contains(tache) && r.actions.contains("Deplacer") && !r.actions.contains("Attraper")){
                System.out.println("Il manque l'action 'Attraper', veuillez l'ajouter");
                creerAction(scanner, composantes, r);
            } else {
                System.out.println("Il vous manque des actions");
                creerAction(scanner, composantes, r);
            } 
        }
    }

    public static void voirActivitesMaintenues(Robot r){
        System.out.println("\n\n Voici les activitées maintenues par " + r.nom);
        for (String act : r.taches){
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

    public static void creerTaches(Scanner scanner, LinkedList<String> taches) {
        boolean repeter = true;
        while (repeter){
            //Juste deux options de taches
            System.out.println("Quelle taches voulez vous créer 'Faire des zigzags' ou 'Attraper et relacher un objet'");
            String tache = scanner.nextLine();
            taches.add(tache);
            System.out.println("Voulez-vous ajouter une autre tache:");
            System.out.println("-1 Oui");
            System.out.println("-2 Non");
            String verdict = scanner.nextLine();
            if (verdict.equals("Oui")){
               continue;
            } else {
                break;
            }
        }
        System.out.println(taches);
    }



//-Samir--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static LinkedList<Robot> enregistrerRobot() {
        LinkedList<Robot> robots = new LinkedList<>();
        System.out.println("************* Enrégistrer un robot *************");
        Scanner scanner = new Scanner(System.in);
        do {
            LinkedList<String> infosRobot = demanderInfosRobots(scanner);
            // CREER DES ACTIONS.
            robots.add(new Robot(infosRobot.get(0), 0, 0, 0, 100, 20, Double.parseDouble(infosRobot.get(1)) , null ,null, null, null));
        } while(enregistrerDeNouveau(scanner));
        scanner.close();
        return robots;
    }

    public static LinkedList<String> demanderInfosRobots(Scanner scanner) {
        // TODO
        // Contient dans l'ordre : Nom, composantes
        LinkedList<String> infosRobots;

        int nombreCompoantes = 2;
        infosRobots = new LinkedList<>();

        System.out.println(" ");
        System.out.print("Nom du robot à ajouter : ");
        String nom = scanner.nextLine();
        System.out.print("Capacité mémoire de ce robot (en GB) : ");
        String memoire = scanner.nextLine();

        boolean repeat = true;
        do {
            try {
                System.out.print("Nombre de composantes à rajouter au robot (minimum de 2) : ");
                nombreCompoantes = Integer.parseInt(scanner.nextLine());
                if (nombreCompoantes >= 2) {
                    infosRobots.clear();
                    for (int i = 1; i <= nombreCompoantes; ++i) {
                        System.out.print("Composante " + i + " : ");
                        String choix = scanner.nextLine();
                        infosRobots.add(choix);
                    }
                    verifierComposantes(infosRobots);
                    repeat = false;
                } else {
                    System.out.println(" ");
                    System.out.print("Veuillez entrer une valeur >= 2.");
                    System.out.println(" ");
                }
            } catch (IOException e) {
                System.out.println(" ");
                System.out.print("Votre liste de composantes doit contenir au moins un CPU et une autre composante.");
                System.out.println(" ");
                infosRobots.clear();
                continue;
            } catch (NumberFormatException e) {
                System.out.println(" ");
                System.out.print("Vous devez entrer un chiffre / nombre");
                System.out.println(" ");
                infosRobots.clear();
                continue;
            }
        
        } while (repeat);
        infosRobots.addFirst(memoire);
        infosRobots.addFirst(nom);
        System.out.println(" ");
        System.out.println("Enregistrement réussi ! ");        
        return infosRobots;
    }

    public static void verifierComposantes(LinkedList<String> list) throws IOException {
        int compteur = 0;
        int tailleListe = list.size();
        for (String string : list) {
            if (string.toUpperCase().equals("CPU")){
                compteur++;
            }
        }

        if (compteur == tailleListe || (compteur == tailleListe && tailleListe == 2 || compteur == 0)){
            throw new IOException();
        }

    }

    public static Boolean enregistrerDeNouveau(Scanner scanner) {
        boolean repeter = true;
        boolean result = false;
        while (repeter) {
            System.out.println(" ");
            System.out.println("Choisissez une option (entrez simplement le chiffre) : ");
            System.out.println("1- Enregistrer un autre robot");
            System.out.println("2- Revenir au menu");
            System.out.print(">>> Votre choix : ");
            String answer = scanner.nextLine();
            if (answer.equals("1")) {
                result = true;
                repeter = false;
            } else if (answer.equals("2")) {
                result = false;
                repeter = false;
                menu();
            } else {
                System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        return result;
    }
    

    

    public static void afficherMetriquesFlotte(Scanner scanner, LinkedList<Robot> robotsEnregistres) {
        // TODO 
        System.out.println("************* Métriques de ma flotte *************");
        System.out.println(" ");
        System.out.println(">>> Nombre de robot dans la flotte : " + robotsEnregistres.size());
        System.out.println(">>> Liste des robots : ");
        for (int i = 1; i < robotsEnregistres.size() + 1; i++) {
            Robot robot = robotsEnregistres.get(i-1);
            System.out.println(i + "- " + robot.nom + "\n   Batterie : 100 %\n   Consommation CPU : 73 %");
        }
        System.out.println(">>> Batterie moyenne des robots : 100 %" );
        System.out.println(">>> Consommation moyenne du CPU : 73 %" );
        System.out.println(" ");
        menu();
    }

    public static void gestionDesProblèmes() {
        Random rand = new Random();
        int n = rand.nextInt(3);
        
        switch (n){
            case 0:
                System.out.println("Un robot manque de batteries! Allez le recharger");
                break;
            case 1:
                System.out.println("Un robot overheat! Arretez le immediatement");
                break;
            case 2: 
                System.out.println("Un robot se sent seul... :(, allez lui allouer une activites");
                break;
        }
    }

    public static void participerActivites(Robot r, Scanner scanner) {
        boolean repeter = true;
        while (repeter){
            //Juste deux options de taches
            //Prenons l'exemple de la tag
            System.out.println("Quelle actitvite voulez-vous assigner a votre robot (Jouer a tag) ou (Faire une course)?");
            String activite = scanner.nextLine();
            if (activite.equals("Jouer a tag") && r.taches.contains("Faire de zigzags") && r.taches.contains("Attraper et relacher un objet")){
                System.out.println(r.nom + " peut maintenant " + activite + "!");
                r.activites.add(activite);
                repeter = false;
            } else if (activite.equals("Jouer a tag") && !r.taches.contains("Faire des zigzags") && r.taches.contains("Attraper et relacher un objet")) {
                System.out.println("L'activité 'Jouer a tag' car la tache 'Faire des zigzags' n'existe pas");
                allouerTachesRobot(r,r.composantes, scanner, r.taches);
            } else if (activite.equals("Jouer a tag") && r.taches.contains("Faire des zigzags") && !r.taches.contains("Attraper et relacher un objet")){
                System.out.println("L'activité 'Jouer a tag' car la tache 'Attraper un objet et le relacher' n'existe pas");
                allouerTachesRobot(r,r.composantes, scanner, r.taches);
            } else if (activite.equals("Jouer a tag") && !r.taches.contains("Faire des zigzags") && !r.taches.contains("Attraper et relacher un objet")){
                System.out.println("L'activité 'Jouer a tag' car la tache 'Faire des zigzags' et 'Attraper un objet et le relacher'");
                allouerTachesRobot(r,r.composantes, scanner, r.taches);
            }
            
            if (activite.equals("Faire une course") && r.taches.contains("Faire des zigzags")){
                System.out.println(r.nom + " peut maintenant " + activite + "!");
                r.activites.add(activite);
                repeter = false;
            } else if (activite.equals("Faire une course") && !r.taches.contains("Faire des zigzags")) {
                System.out.println("La tâche 'Faire de zigzags' n'est pas ajoutée car n'existe pas");
                allouerTachesRobot(r,r.composantes, scanner, r.taches);
            }

            System.out.println("Voulez-vous ajouter une autre activite:");
            System.out.println("-1 Oui");
            System.out.println("-2 Non");
            String verdict = scanner.nextLine();
            if (verdict.equals("Oui")){
                repeter = true;
               continue;
            } else {
                break;
            }
        }
        System.out.println(r.activites);
    }

    public static void trouverFournisseurs() {
        ArrayList<ArrayList<String>> fournisseurs = new ArrayList<>();
        ArrayList<String> fournisCPU = new ArrayList<>();
        ArrayList<String> fournisCPU2 = new ArrayList<>();
        ArrayList<String> fournisRoue = new ArrayList<>();
        ArrayList<String> fournisHp = new ArrayList<>();
        ArrayList<String> fournisMic = new ArrayList<>();
        ArrayList<String> fournisMic2 = new ArrayList<>();

        fournisCPU2.add("Fournisseur5");
        fournisCPU2.add("Adresse2");
        fournisCPU2.add("Courriel3");
        fournisCPU2.add("Numero4");
        fournisCPU2.add("type2");
        fournisCPU2.add("compagnie1");
        
        fournisMic2.add("Fournisseur6" );
        fournisMic2.add("Adresse1");
        fournisMic2.add("Courriel3");
        fournisMic2.add("Numero4");
        fournisMic2.add("type3");
        fournisMic2.add("compagnie2");

        fournisseurs.add(fournisCPU);
        fournisseurs.add(fournisRoue);
        fournisseurs.add(fournisHp);
        fournisseurs.add(fournisMic);

        int iterateur = 1;
        for (ArrayList<String> fournisseur : fournisseurs) {
            fournisseur.add("Fournisseur" + iterateur);
            fournisseur.add("Adresse" + iterateur);
            fournisseur.add("Courriel" + iterateur);
            fournisseur.add("Numero" + iterateur);
            fournisseur.add("type" + iterateur);
            fournisseur.add("compagnie" + iterateur);
            ++iterateur;
        }

        fournisseurs.add(fournisCPU2);
        fournisseurs.add(fournisMic2);

        HashMap<String, ArrayList<String>> fournisMap = new HashMap<>();
        for (int i = 1; i < fournisseurs.size() + 1; i++) {
            fournisMap.put("Fournisseur" + i, fournisseurs.get(i - 1));
        }


        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            System.out.println("Choisissez un filtre parmi les suivants : ");
            System.out.println("1- Nom");
            System.out.println("2- Adresse");
            System.out.println("3- Courriel");
            System.out.println("4- Telephone");
            System.out.println("5- Type de robots fabriqués");
            System.out.println("6- Nom de la compagnie");
            System.out.println("7- Aucun filtre");
            System.out.print(">>> Votre choix :");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("Veuillez entrer le nom recherché : ");
                    String nom = scanner.nextLine();
                    getFournisseurs(fournisseurs, nom);
                    continuer = false;
                    break;
                case "2":
                    System.out.print("Veuillez entrer l'adresse recherchée : ");
                    String adresse = scanner.nextLine();
                    getFournisseurs(fournisseurs, adresse);
                    continuer = false;
                    break;
                
                case "3":
                    System.out.print("Veuillez entrer le courriel recherché : ");
                    String courriel = scanner.nextLine();
                    getFournisseurs(fournisseurs, courriel);
                    continuer = false;
                    break;
                
                case "4":
                    System.out.print("Veuillez entrer le numero recherché : ");
                    String numero = scanner.nextLine();
                    getFournisseurs(fournisseurs, numero);
                    continuer = false;
                    break;
    
                case "5":
                    System.out.print("Veuillez entrer le type de robot recherché : ");
                    String type = scanner.nextLine();
                    getFournisseurs(fournisseurs, type);
                    continuer = false;
                    break;
                
                case "6":
                    System.out.print("Veuillez entrer le nom de la compagnie recherché : ");
                    String nomCompagnie = scanner.nextLine();
                    getFournisseurs(fournisseurs, nomCompagnie);
                    continuer = false;
                    break;

                case "7":
                    for (ArrayList<String> element : fournisseurs) {
                        System.out.println(element);
                    }
                    continuer = false;
                    break;
    
                default:
                    System.out.println("Veuillez choisir un élément dans la liste.");
                    break;
            }
    
        }

        selctionnerFournisseur(scanner, fournisMap);
        
        scanner.close();
        
    }

    public static void selctionnerFournisseur(Scanner scanner, HashMap<String, ArrayList<String>> fournisMap) {
        System.out.println(" ");
        System.out.print("Veuillez entrer le nom du fournisseur chez qui vous voulez acheter une composante : ");
        String answer = scanner.nextLine();

        if (fournisMap.containsKey(answer)) {
            acheterComposantes(scanner, fournisMap.get(answer));
        } else {
            System.out.println("Ce fournisseur n'est pas dans la liste.");
            menu();
        }
    }

    public static void getFournisseurs(ArrayList<ArrayList<String>> fournisseurs, String entree) {
        ArrayList<ArrayList<String>> reponse = new ArrayList<>();
        for (int i = 0; i < fournisseurs.size(); i++) {
            ArrayList<String> fournis = fournisseurs.get(i);
            for (int j = 0; j < fournis.size(); j++) {
                if (entree.equals(fournis.get(j))) {
                    reponse.add(fournis);
                }
            }
        }

        if (reponse.size() == 0){
            System.out.println("Il n'y a pas de fournisseur avec cette caractéristique.");
        } else {
            for (ArrayList<String> element : reponse) {
                System.out.println(element);
            }
        }
    }
    public static void acheterComposantes(Scanner scanner, ArrayList<String> fournisseurChoisi) {

        String fourniseurs =  fournisseurChoisi.get(0);
        switch (fourniseurs) {
            case "Fournisseur1":
            case "Fournisseur3":
                System.out.println("Composante vendu : CPU ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Composante indispensable pour tout robot.");
                System.out.println("Prix : 1 000 000 000 $ (non négociable)");
                break;

            case "Fournisseur2":
            case "Fournisseur4":
                System.out.println("Composante vendu : Haut-parleur ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour émettre un song.");
                System.out.println("Prix : 2 000 000 $ (non négociable)");
                break;

            default:
                System.out.println(" ");
                System.out.println("Composante vendu : Roue ");
                System.out.println("Type : " + fournisseurChoisi.get(4));
                System.out.println("Description : Indispensable pour se déplacer.");
                System.out.println("Prix : 5 000 000 $ (non négociable)");
                break;
        }

        System.out.print(">>> Valider l'achat ? : ");
        String answer = scanner.nextLine();

        if (answer.toUpperCase().equals("OUI")){
            System.out.println("Validation de l'achat en cours...");
            System.out.println("Achat Validé !");
        } else {
            System.out.println("Achat annulé...");
        }
    }
}