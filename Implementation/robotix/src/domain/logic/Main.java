package domain.logic;
import java.io.FileNotFoundException;
import java.util.*;

import domain.logic.Controller.Controller;
import domain.logic.Fournisseur.Fournisseur;
import java.util.Scanner;
import domain.logic.Utilisateurs.Utilisateurs;


public class Main {
    public static ArrayList<Fournisseur> listeFournisseurs = genererFournisseurs();
    public static ArrayList<Utilisateurs> listeUtilisateurs = genererUtilisateurs();
    public static void main(String[] args) {
        Controller c = new Controller();
        try {
            c.read("test", "hey", "Col2");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Not working");
            e.printStackTrace();
        }
        //choisirOptionMenu(listeFournisseurs, listeUtilisateurs);
    }

    /*
        M�thode qui permet la cr�ation d'utilisateurs d�j� inscrits dans le syst�me
    */
    private static ArrayList<Utilisateurs> genererUtilisateurs() {
        ArrayList<Utilisateurs> listeUtilisateurs = new ArrayList<>();

        return listeUtilisateurs;
    }

    /*
        M�thode qui permet la cr�ation de fournisseurs d�j� inscrits dans le syst�me
    */
    private static ArrayList<Fournisseur> genererFournisseurs() {
        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
        Fournisseur founisseur1 = new Fournisseur("Roy", "123 rue des Innovations, Montr�al, QC, H1A 0A1", "roy1",
                "nom1@robotech.ca", "5142104555", "RobotA", "CPU", "30", "RoboTechnologies");
        Fournisseur founisseur2 = new Fournisseur("Bouchard", "456 avenue des Automates, Montr�al, QC, H5M 1N2", "bouchard2",
                "contact@automatech.ca", "4503335432", "RobotB", "BRAS", "25", "Automatech");
        Fournisseur founisseur3 = new Fournisseur("Adams", "2376 boulevard des G�nies, Qu�bec, QC, G1W 2W5", "adams3",
                "service@innovatech.ca", "4509998888", "RobotC", "ECRAN","27", "Innovatech");
        Fournisseur founisseur4 = new Fournisseur("Wilson", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "wilson4",
                "assistance@iRobot.ca", "4502109876", "RobotD", "CAMERA","35", "iRobot");
        Fournisseur founisseur5 = new Fournisseur("Thompson", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "thompson5",
                "info@roboPro.ca", "4506780000", "RobotE", "HAUTPARLEUR","22", "RoboPro");

        listeFournisseurs.add(founisseur1);
        listeFournisseurs.add(founisseur2);
        listeFournisseurs.add(founisseur3);
        listeFournisseurs.add(founisseur4);
        listeFournisseurs.add(founisseur5);

        return listeFournisseurs;
    }

    /*
        M�thode qui sert du menu principal de Robotix
     */
    public static void choisirOptionMenu(ArrayList<Fournisseur> listeFournisseurs, ArrayList<Utilisateurs> listeUtilisateurs) {
        Scanner scanner = new Scanner(System.in);
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
                    inscrireUtilisateur(listeUtilisateurs);
                    break;
                case 2 : 
                    inscrireFournisseur(listeFournisseurs);
                    break;
                case 3 : 
                    connecterUtilisateur(listeUtilisateurs);
                    break;
                case 4 : 
                    connecterFournisseur(listeFournisseurs);
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

    /*
        M�thode qui permet de s'inscrire en tant que nouvel utilisateur
     */
    private static void inscrireUtilisateur(ArrayList<Utilisateurs> listeUtilisateurs) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("********Nouvel utilisateur********");
        System.out.print("Prenom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom :");
        String nom = scanner.nextLine();
        System.out.print("Pseudo : ");
        String pseudo = scanner.nextLine();
        System.out.print("Adresse courriel : ");
        String courriel = scanner.nextLine();
        System.out.print("Telephone : ");
        String telephone = scanner.nextLine();

        Utilisateurs util = new Utilisateurs(nom, prenom, pseudo, courriel, telephone);
        listeUtilisateurs.add(util);
        System.out.println("Have fun " + pseudo);
        choisirOptionMenu(listeFournisseurs, listeUtilisateurs);
    }

    /*
        M�thode qui permet de s'inscrire en tant que nouveau fournisseur
     */
    private static void inscrireFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
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
            NomUnique = verifierNomFournisseur(inputNom, listeFournisseurs, false);
        }

        System.out.print("Adresse de la compaganie: ");
        inputAdresse = scanner.nextLine();

        while (!PseudoUnique) {
            System.out.print("Pseudo: ");
            inputPseudo = scanner.nextLine();
            PseudoUnique = verifierPseudoFournisseur(inputPseudo, listeFournisseurs, false);
        }

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            inputEmail = scanner.nextLine();
            EmailValide = verifierEmailFournisseur(inputEmail);
        }

        while (!TelephoneValide) {
            System.out.print("Num�ro de t�l�phone: ");
            inputTelephone = scanner.nextLine();
            TelephoneValide = verifierTelephoneFournisseur(inputTelephone);
        }

        System.out.print("Type de robots fabriqu�s: ");
        inputTypeRobot = scanner.nextLine();
        System.out.print("Type de composantes fabriqu�es: ");
        inputTypeComposantes = scanner.nextLine();
        System.out.print("Capacit� de fabrication: ");
        inputCapacite = scanner.nextLine();
        System.out.print("Nom de compagnie: ");
        inputCompagnie = scanner.nextLine();

        ajouterFournisseur(inputNom, inputAdresse, inputPseudo, inputEmail, inputTelephone, inputTypeRobot,
                inputTypeComposantes, inputCapacite, inputCompagnie, listeFournisseurs);
    }

    /*
        M�thode qui permet de se connecter en tant qu'utilisateur
     */
    private static void connecterUtilisateur(ArrayList<Utilisateurs> listeUtilisateurs) {
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

    /*
        M�thode qui permet de se connecter en tant que fournisseur
     */
    private static void connecterFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
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

    /*
        M�thode qui permet de v�rifier si le nom entr� est valide, c-�-d s'il est unique
     */
    private static boolean verifierNomFournisseur(String inputNom, ArrayList<Fournisseur> listeFournisseurs, boolean NomUnique) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(inputNom)) {
                NomUnique = false;
                System.out.println("Ce nom de fournisseur existe d�j�. Veuillez saisir un autre nom: ");
                break;
            } else
                NomUnique = true;
        }
        return NomUnique;
    }

    /*
        M�thode qui permet v�rifier si le pseudo entr� est valide, c-�-d s'il est unique
     */
    private static boolean verifierPseudoFournisseur(String inputPseudo, ArrayList<Fournisseur> listeFournisseurs, boolean PseudoUnique) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getPseudo().equals(inputPseudo)) {
                PseudoUnique = false;
                System.out.println("Ce pseduo existe d�j�. Veuillez en saisir un autre: ");
                break;
            }
            else
                PseudoUnique = true;
        }
        return PseudoUnique;
    }

    /*
        M�thode qui permet v�rifier si l'adresse courriel entr� est valide, c-�-d s'il contient le caract�re '@'
     */
    private static boolean verifierEmailFournisseur(String inputEmail) {
        boolean EmailValide;
        if (!inputEmail.contains("@")) {
            EmailValide = false;
            System.out.println("L'adresse courriel est invalide. Veuillez en saisir un autre: ");
        }
        else
            EmailValide = true;

        return EmailValide;
    }

    /*
        M�thode qui permet v�rifier si le num�ro de t�l�phone entr� est valide, c-�-d s'il contient les 10 caract�res
        qui composent un num�ro
     */
    private static boolean verifierTelephoneFournisseur(String inputTelephone) {
        boolean TelephoneValide;
        if (inputTelephone.length() != 10) {
            TelephoneValide = false;
            System.out.println("Le num�ro de t�l�phone doit obtenir exactement 10 caract�res. Veuillez r�essayez: ");
        }
        else
            TelephoneValide = true;

        return TelephoneValide;
    }

    /*
        M�thode qui permet d'ajouter un nouveau fournisseur dans la liste des fournisseurs inscrits du syst�me
     */
    private static void ajouterFournisseur(String inputNom, String inputAdresse, String inputPseudo,
                                           String inputCourriel, String inputTelephone, String inputTypeRobot,
                                           String inputTypeComposantes, String inputCapacite, String inputCompagnie,
                                           ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        String choixUsager;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Y", "y", "N", "n")) ;

        Fournisseur nouveauFournisseur = new Fournisseur(inputNom, inputAdresse, inputPseudo, inputCourriel,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);
        listeFournisseurs.add(nouveauFournisseur);

        System.out.println("\nBienvenue " + nouveauFournisseur.getNom() + ". Vous �tes enregistr� comme �tant fournisseur!");
        System.out.println("Voulez vous continuer? (Y/N): ");

        do {
            choixUsager = scanner.nextLine();

            switch (choixUsager) {
                case "Y" :
                case "y" :  
                    nouveauFournisseur.menuFournisseur(nouveauFournisseur);
                    break;
                case "N" :
                case "n":
                    choisirOptionMenu(listeFournisseurs, listeUtilisateurs);
                    break;
                default :       
                    System.out.println("Choix invalide! Réessayez de nouveau: ");
                    break;
            }
        } while (!options.contains(choixUsager));
    }
}