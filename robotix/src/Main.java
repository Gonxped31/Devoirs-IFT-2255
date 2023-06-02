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
        System.out.println("2. Trouver un fournisseur");

        do {
            System.out.println("Entrez votre choix:");
            choix = scanner.nextInt();

            switch (choix) {
                case 1 -> creerFournisseur(listeFournisseurs);
                case 2 -> trouverFournisseur(listeFournisseurs);
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

    /*
        Trouver un fournisseur dans la liste des fournisseurs inscrits
     */
    private static void trouverFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        String choixUsager;
        System.out.println("Voulez-vous rechercher un fournisseur avec filtre? (Y/N):");

        do {
            choixUsager = scanner.nextLine();

            switch (choixUsager) {
                case "Y", "y" -> rechercherAvecFiltre(listeFournisseurs);
                case "N", "n" -> rechercherSansFiltre(scanner, listeFournisseurs);
                default -> System.err.println("Choix invalide! Réessayez de nouveau.");
            }
        } while (!choixUsager.equals("Y") && !choixUsager.equals("y") && !choixUsager.equals("N") && !choixUsager.equals("n"));
    }

    /*
        Recherche d'un fournisseur inscrit avec un filtre de recherche
     */
    private static void rechercherAvecFiltre(ArrayList<Fournisseur> listeFournisseurs) {
        String caractere;
        Scanner scannerCaractere = new Scanner(System.in);
        Scanner scannerChoix = new Scanner(System.in);
        int choixUsager = 0;

        System.out.println("Choisissez votre option de filtre:");
        System.out.println(1 + ". Par nom");
        System.out.println(2 + ". Par adresse");
        System.out.println(3 + ". Par email");
        System.out.println(4 + ". Par téléphone");

        do {
            choixUsager = scannerChoix.nextInt();

            switch (choixUsager) {
                case 1 -> {
                    System.out.println("Entrez au moins un caractère du nom du fournisseur recherché:");

                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParNom(caractere, listeFournisseurs);
                }
                case 2 -> {
                    System.out.println("Entrez au moins un caractère de l'adresse du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParAdresse(caractere, listeFournisseurs);
                }
                case 3 -> {
                    System.out.println("Entrez au moins un caractère du courriel du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParEmail(caractere, listeFournisseurs);
                }
                case 4 -> {
                    System.out.println("Entrez au moins un caractère du numéro de téléphone du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParTelephone(caractere, listeFournisseurs);
                }
                default -> System.err.println("Choix invalide! Réessayez de nouveau.");
            }
        } while (choixUsager < 1 || choixUsager > 4);
    }

    /*
        Rechercher un fournisseur inscrit sans l'option de filtre.
     */
    private static void rechercherSansFiltre(Scanner scanner, ArrayList<Fournisseur> listeFournisseurs) {
        String nomFournisseur;

        System.out.println("Voici la liste des fournisseurs inscrits:\n");
        for (Fournisseur listeFournisseur : listeFournisseurs) {
            System.out.println(listeFournisseur);
        }

        System.out.println("Veuillez saisir le nom du fournisseur recherché:");
        nomFournisseur = scanner.nextLine();

        verifierRechercheSansFiltre(nomFournisseur, listeFournisseurs);
    }

    /*
        Vérifier la recherche de filtre d'un fournisseur par son nom
     */
    private static void verifierRechercheParNom(String nom, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursTrouves = new ArrayList<>();

        for (Fournisseur fournisseurRecherche : listeFournisseurs) {
            if (fournisseurRecherche.getNom().contains(nom.toUpperCase()) || fournisseurRecherche.getNom().contains(nom.toLowerCase())) {
                fournisseursTrouves.add(fournisseurRecherche.getNom());
            }
        }

        if (fournisseursTrouves.size() == 0)
            System.err.println("Aucun fournisseur(s) trouvé(s)");
        else {
            System.out.println(fournisseursTrouves.size() + " fournisseur(s) trouvé(s):");
            System.out.println(fournisseursTrouves);
        }
    }

    /*
        Vérifier la recherche de filtre d'un fournisseur par son adresse
     */
    private static void verifierRechercheParAdresse(String adresse, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursTrouves = new ArrayList<>();

        for (Fournisseur fournisseurRecherche : listeFournisseurs) {
            if (fournisseurRecherche.getAdresse().contains(adresse.toUpperCase()) || fournisseurRecherche.getAdresse().contains(adresse.toLowerCase())) {
                fournisseursTrouves.add(fournisseurRecherche.getNom());
            }
        }

        if (fournisseursTrouves.size() == 0)
            System.err.println("Aucun fournisseur(s) trouvé(s)");
        else {
            System.out.println(fournisseursTrouves.size() + " fournisseur(s) trouvé(s):");
            System.out.println(fournisseursTrouves);
        }
    }

    /*
        Vérifier la recherche de filtre d'un fournisseur par son courriel
     */
    private static void verifierRechercheParEmail(String email, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursTrouves = new ArrayList<>();

        for (Fournisseur fournisseurRecherche : listeFournisseurs) {
            if (fournisseurRecherche.getEmail().contains(email.toUpperCase()) || fournisseurRecherche.getEmail().contains(email.toLowerCase())) {
                fournisseursTrouves.add(fournisseurRecherche.getNom());
            }
        }

        if (fournisseursTrouves.size() == 0)
            System.err.println("Aucun fournisseur(s) trouvé(s)");
        else {
            System.out.println(fournisseursTrouves.size() + " fournisseur(s) trouvé(s):");
            System.out.println(fournisseursTrouves);
        }
    }

    /*
        Vérifier la recherche de filtre d'un fournisseur par son téléphone
     */
    private static void verifierRechercheParTelephone(String telephone, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursTrouves = new ArrayList<>();

        for (Fournisseur fournisseurRecherche : listeFournisseurs) {
            if (fournisseurRecherche.getTelephone().contains(telephone)) {
                fournisseursTrouves.add(fournisseurRecherche.getNom());
            }
        }

        if (fournisseursTrouves.size() == 0)
            System.err.println("Aucun fournisseur(s) trouvé(s)");
        else {
            System.out.println(fournisseursTrouves.size() + " fournisseur(s) trouvé(s):");
            System.out.println(fournisseursTrouves);
        }
    }

    /*
        Vérifier la recherche sans filtre d'un fournisseur
     */
    private static ArrayList<String> verifierRechercheSansFiltre(String nom, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

        for (Fournisseur fournisseurRecherche : listeFournisseurs) {
            if (fournisseurRecherche.getNom().equals(nom.toUpperCase()) || fournisseurRecherche.getNom().equals(nom.toLowerCase()))
                fournisseursSelectionnes.add(fournisseurRecherche.getNom());
        }

        if (fournisseursSelectionnes.size() == 0)
            System.err.println("Aucun fournisseur(s) trouvé(s)");
        else {
            System.out.println("Vous avez sélectionné le fournisseur " + fournisseursSelectionnes);
        }
        return fournisseursSelectionnes;
    }
}