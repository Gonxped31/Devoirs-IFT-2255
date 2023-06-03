import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Robot.TypeRobot;

public class Main {
    public static void main(String[] args) {
        ArrayList<Fournisseur> listeFournisseurs = genererFournisseurs();
        choisirOptionMenu(listeFournisseurs);
    }

    /*
        Création de fournisseurs déjà inscrits dans le système
    */
    private static ArrayList<Fournisseur> genererFournisseurs() {
        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<Fournisseur>();
        Fournisseur roboTech = new Fournisseur("RoboTech", "123 rue des Innovations, Montréal, QC, H1A 0A1", "info@robotech.ca",
                "4502104555", TypeRobot.A, 30);
        Fournisseur automatech = new Fournisseur("Automatech", "456 avenue des Automates, Montréal, QC, H5M 1N2", "contact@automatech.ca",
                "4503335432", TypeRobot.B, 25);
        Fournisseur innovatech = new Fournisseur("Innovatech", "2376 boulevard des Génies, Québec, QC, G1W 2W5", "service@innovatech.ca",
                "4509998888", TypeRobot.C, 27);
        Fournisseur iRobot = new Fournisseur("iRobot", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "assistance@iRobot.ca",
                "4502109876", TypeRobot.B, 35);
        Fournisseur roboPro = new Fournisseur("RoboPro", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "info@roboPro.ca",
                "4506780000", TypeRobot.A, 22);

        listeFournisseurs.add(roboTech);
        listeFournisseurs.add(automatech);
        listeFournisseurs.add(innovatech);
        listeFournisseurs.add(iRobot);
        listeFournisseurs.add(roboPro);

        return listeFournisseurs;
    }

    private static void choisirOptionMenu(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        System.out.println("Bienvenue chez Robotix! Voici le menu principal:");
        System.out.println("1. S'inscrire comme fournisseur");
        System.out.println("2. Connexion en tant que fournisseur");

        do {
            System.out.println("Entrez votre choix:");
            choix = scanner.nextInt();

            switch (choix) {
                case 1 -> creerFournisseur(listeFournisseurs);
                case 2 -> connecter(listeFournisseurs);
                default -> System.err.println("Choix invalide. Veuillez réessayez.");
            }
        } while (choix != 1 && choix != 2);
    }



    private static void creerFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        String inputNom, inputCourriel, inputTelephone, inputCapacite;

        System.out.println("Complétez les informations suivantes afin de vous inscrire comme fournisseur:");
        System.out.print("Nom:");
        inputNom = scanner.nextLine();
        System.out.print("Courriel:");
        inputCourriel = scanner.nextLine();
        System.out.print("Numéro de téléphone:");
        inputTelephone = scanner.nextLine();
        System.out.print("Capacité de fabrication:");
        inputCapacite = scanner.nextLine();

        verifierFournisseur(inputNom, inputCourriel, inputTelephone, inputCapacite, listeFournisseurs);
    }

    private static void connecter(ArrayList<Fournisseur> listeFournisseurs) {
    }

    /*
        Vérifier si un fournisseur possédant le même nom entré est inscrit
     */
    private static void verifierFournisseur(String inputNom, String inputCourriel, String inputTelephone, String inputCapacite,
                                            ArrayList<Fournisseur> listeFournisseurs) {
        Fournisseur nouveauFournisseur = new Fournisseur();

        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(inputNom)) {
                System.err.println("Ce nom de fournisseur existe déjà");
            }
            else {
                nouveauFournisseur.setNom(inputNom);
                nouveauFournisseur.setEmail(inputCourriel);
                nouveauFournisseur.setTelephone(inputTelephone);
                nouveauFournisseur.setCapacite(Double.parseDouble(inputCapacite));
                listeFournisseurs.add(nouveauFournisseur);
            }
        }
    }


}