package domain.logic.Menu;
import domain.logic.Membre.*;
import domain.logic.Robot.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import domain.logic.Main;

public class Menu {
    public static ArrayList<Fournisseur> listeFournisseurs = genererFournisseurs();
    public static ArrayList<Utilisateurs> listeUtilisateurs = genererUtilisateurs();

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        menuPrincipale(scanner);
    }

    private static ArrayList<Utilisateurs> genererUtilisateurs() {
        ArrayList<Utilisateurs> listeUtilisateurs = new ArrayList<>();

        return listeUtilisateurs;
    }

    /*
        M�thode qui permet la creation de fournisseurs deja inscrits dans le systeme
    */
    private static ArrayList<Fournisseur> genererFournisseurs() {
        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
        Fournisseur founisseur1 = new Fournisseur("Roy", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "roy1",
                "nom1@robotech.ca", "5142104555", "RobotA", "CPU", "RoboTechnologies");
        Fournisseur founisseur2 = new Fournisseur("Bouchard", "456 avenue des Automates, Montr�al, QC, H5M 1N2", "bouchard2",
                "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "Automatech");
        Fournisseur founisseur3 = new Fournisseur("Adams", "2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5", "adams3",
                "service@innovatech.ca", "4509998888", "RobotC", "ECRAN", "Innovatech");
        Fournisseur founisseur4 = new Fournisseur("Wilson", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "wilson4",
                "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA", "iRobot");
        Fournisseur founisseur5 = new Fournisseur("Thompson", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "thompson5",
                "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR", "RoboPro");

        listeFournisseurs.add(founisseur1);
        listeFournisseurs.add(founisseur2);
        listeFournisseurs.add(founisseur3);
        listeFournisseurs.add(founisseur4);
        listeFournisseurs.add(founisseur5);

        return listeFournisseurs;
    }

    public static void menuPrincipale(Scanner scanner) {
        int choixUsager;
        ArrayList<Integer> options = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)) ;

        System.out.println("********Binevenue chez Robotix!********\"");
        System.out.println("Veuillez choisir l'une des options suivantes:");
        System.out.println(options.get(0) + "- S'inscrire en tant qu'utilisateur");
        System.out.println(options.get(1) + "- S'inscrire en tant que fournisseur");
        System.out.println(options.get(2) + "- Se connecter en tant qu'utilisateur");
        System.out.println(options.get(3) + "- Se connecter en tant que fournisseur");
        System.out.println(options.get(4) + "- Quitter Robotix");

        do {
            System.out.print("Entrez votre choix: ");
            choixUsager = scanner.nextInt();

            switch (choixUsager) {
                case 1 : 
                    menuInscriptionUtilisateur(scanner);
                    break;
                case 2 : 
                    menuInscriptionFournisseur();
                    break;
                case 3 : 
                    menuConnexionUtilisateur();
                    break;
                case 4 : 
                    menuConnexionFournisseur();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default :
                    System.out.println("Choix invalide. Veuillez réessayez.");
                    break;
            }
        } while (!options.contains(choixUsager));
    }

    public static void menuInscriptionUtilisateur(Scanner scanner) {
        // TODO : VERIFIER SI L'UTILISATEUR EST DÉJÀ INSCRIT
        System.out.println("********Nouvel utilisateur********");
        System.out.print("Prenom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom :");
        String nom = scanner.nextLine();
        System.out.println("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Pseudo : ");
        String pseudo = scanner.nextLine();
        System.out.print("Adresse courriel : ");
        String courriel = scanner.nextLine();
        System.out.print("Telephone : ");
        String telephone = scanner.nextLine();
        System.out.println("Nom de la compagnie : ");
        String nomCompagnie = scanner.nextLine();

        Utilisateurs util = new Utilisateurs(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie);
        listeUtilisateurs.add(util);
        System.out.println("Have fun " + pseudo + " !");
        menuPrincipale(scanner);
    }

    public static void menuInscriptionFournisseur() {
        Scanner scanner = new Scanner(System.in);
        boolean NomUnique = false;
        boolean PseudoUnique = false;
        boolean EmailValide = false;
        boolean TelephoneValide = false;

        String inputNom = "";
        String inputPseudo = "";
        String inputEmail = "";
        String inputTelephone = "";
        String inputAdresse;
        String inputTypeRobot;
        String inputTypeComposantes;
        String inputCapacite;
        String inputCompagnie;

        System.out.println("********Nouveau fournisseur********");

        while (!NomUnique) {
            System.out.print("Nom: ");
            inputNom = scanner.nextLine();
        }

        System.out.print("Adresse de la compaganie: ");
        inputAdresse = scanner.nextLine();

        while (!PseudoUnique) {
            System.out.print("Pseudo: ");
            inputPseudo = scanner.nextLine();
        }

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            inputEmail = scanner.nextLine();
        }

        while (!TelephoneValide) {
            System.out.print("Num�ro de t�l�phone: ");
            inputTelephone = scanner.nextLine();
        }

        System.out.print("Type de robots fabriqu�s: ");
        inputTypeRobot = scanner.nextLine();
        System.out.print("Type de composantes fabriqu�es: ");
        inputTypeComposantes = scanner.nextLine();
        System.out.print("Capacit� de fabrication: ");
        inputCapacite = scanner.nextLine();
        System.out.print("Nom de compagnie: ");
        inputCompagnie = scanner.nextLine();

    }

    public static void menuConnexionUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre pseudo: ");
        String connexion = scanner.nextLine();
        for (int i = 0; i < listeUtilisateurs.size(); i++) {
            if (listeUtilisateurs.get(i).pseudo.equals(connexion)){
                System.out.println("Bienvenue " + listeUtilisateurs.get(i).pseudo);
                Utilisateurs.menu(scanner);
                break;
            }
        }
        System.out.println(connexion + " n'existe pas...");
    }

    public static void menuConnexionFournisseur() {
        Scanner scanner = new Scanner(System.in);
        boolean EstConnecte = false;

        System.out.println("Veuillez entrez votre nom de fournisseur: ");
        String nomFounisseur = scanner.nextLine();

        for (Fournisseur listeFournisseur : listeFournisseurs) {
            if (listeFournisseur.getNom().equals(nomFounisseur)) {
                EstConnecte = true;
                listeFournisseur.menuFournisseur(listeFournisseur);
                break;
            }
            else
                EstConnecte = false;
        }

        if (!EstConnecte)
            System.out.println("Ce nom n'existe pas. Au revoir!");
    }

    /*public static void menuUtilisateur() {
        ArrayList<String> fournisCPU = new ArrayList<>();
        fournisCPU.add("Fournisseur6" );
        fournisCPU.add("Adresse1");
        fournisCPU.add("Courriel3");
        fournisCPU.add("Numero4");
        fournisCPU.add("type3");
        fournisCPU.add("compagnie2");
        System.out.println("******************** Menu ********************");
        System.out.println("Bienvenue! Veuillez choisir une option:");
        System.out.println("1- Enregistrer un robot");
        System.out.println("2- Afficher état d'un robot");
        System.out.println("3- Ajouter une composante a un robot");
        System.out.println("4- Ajouter une action a un robot");
        System.out.println("5- Ajouter une tâche");
        System.out.println("6- Allouer une tache a un robot");
        System.out.println("7- Afficher métriques d'une flotte");
        System.out.println("8- Participer à une activité");
        System.out.println("9- Afficher activités maintenues par un robot d'une flotte");
        System.out.println("10- Afficher les problèmes du système");
        System.out.println("11- Trouver un fournisseur");
        System.out.println("12- Quitter");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch(choix){
            case("1"):
                Utilisateurs.enregistrerRobot();
                break;
            case("2"):
                Utilisateurs.afficherEtatRobot(scanner);
                break;
            case("3"):
                Utilisateurs.ajouterComposantes(scanner);
                break;
            case("4"):
                Utilisateurs.ajouterAction(scanner);
                break;
            case("5"):
                Utilisateurs.creerTaches(scanner);
                break;
            case("6"):
                Utilisateurs.allouerTachesRobot(scanner);
                break;
            case("7"):
                Utilisateurs.afficherMetriquesFlotte(scanner);
                break;
            case("8"):
                Utilisateurs.participerActivites(scanner);
                break;
            case("9"):
                Utilisateurs.voirActivitesMaintenues(scanner);
                break;
            case("10"):
                Utilisateurs.gestionDesProblemes(scanner);
                break;
            case("11"):
                Utilisateurs.trouverFournisseurs();
                break;
            case("12"):
                Main.choisirOptionMenu(Main.listeFournisseurs, Main.listeUtilisateurs);
                break;
        }
    }*/

    /*public static void menuFournisseur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******************** Menu Fournisseur ********************");
        System.out.println("Bienvenue " + fournisseur.getNom() + "! Veuillez choisir une option:");
        System.out.println("1- Ajouter un nouveau robot");
        System.out.println("2- Retirer un robot");
        System.out.println("3- Ajouter une composante");
        System.out.println("4- Retirer une composante");
        System.out.println("5- Quitter");
        System.out.print(">>> Votre choix : ");
        String choixUsager = scanner.nextLine();

        switch (choixUsager) {
            case "1" : 
                System.out.println("Veuillez entrer les infos du robot : ");
                System.out.print("Nom : ");
                String nomRobot = scanner.nextLine();
                System.out.print("CPU : ");
                String cpu = scanner.nextLine();
                System.out.print("Mémoire : ");
                String memoire = scanner.nextLine();
                System.out.print("Numéro de série : ");
                String numeroSerie = scanner.nextLine();
                Robot robot = new Robot(nomRobot, 0, 0, 0, 0, Integer.parseInt(cpu), Integer.parseInt(memoire), null, null, null, null, numeroSerie);
                ajouterRobot(robot, fournisseur);
                menuFournisseur(fournisseur);
            
            case "2" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String nom = scanner.nextLine();
                retirerRobot(nom, fournisseur);
            
            case "3" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante = scanner.nextLine();
                ajouterComposante(composante, fournisseur);
            
            case "4" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String composante2 = scanner.nextLine();
                retirerComopsante(composante2, fournisseur);
            
            case "5" : 
                System.out.println("Au revoir !");
                Main.choisirOptionMenu(Main.listeFournisseurs, Main.listeUtilisateurs);
                break;

        }
    }*/
}
