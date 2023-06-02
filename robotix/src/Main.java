import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Robot.TypeRobot;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Fournisseur> listeFournisseurs = creerFournisseurs();

        //trouverFournisseur(scanner, listeFournisseurs);
        inscrireFournisseur(listeFournisseurs);
    }



    /*
        Création de fournisseurs déjà inscrits dans le système
    */
    private static ArrayList<Fournisseur> creerFournisseurs() {
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

    private static void inscrireFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        String inputNom, inputCourriel, inputTelephone, inputTypeRobot, inputComposante, inputCapacite;

        System.out.println("Complétez les informations suivantes afin de vous inscrire comme fournisseur:");
        System.out.print("Nom:");
        inputNom = scanner.nextLine();
        System.out.print("Courriel:");
        inputCourriel = scanner.nextLine();
        System.out.print("Numéro de téléphone:");
        inputTelephone = scanner.nextLine();
        System.out.print("Type de robot fabriqué:");
        inputTypeRobot = scanner.nextLine();
        System.out.print("Type de composantes fabriquées:");
        inputComposante = scanner.nextLine();
        System.out.print("Capacité de fabrication:");
        inputCapacite = scanner.nextLine();

        verifierFournisseur(inputNom, inputCourriel, inputTelephone, inputTypeRobot, inputComposante, inputCapacite, listeFournisseurs);
    }

    private static void verifierFournisseur(String inputNom, String inputCourriel, String inputTelephone, String inputTypeRobot,
                                            String inputComposante, String inputCapacite, ArrayList<Fournisseur> listeFournisseurs) {
        Fournisseur fournisseur = new Fournisseur();

        for (Fournisseur listeFournisseur : listeFournisseurs) {
            if (listeFournisseur.getNom().equals(inputNom)) {
                System.err.println("Ce nom de fournisseur existe déjà");
            }
            else {
                fournisseur.setNom(inputNom);
                fournisseur.setEmail(inputCourriel);
                fournisseur.setTelephone(inputTelephone);
                /*fournisseur.setTypeRobotFabriquer();
                fournisseur.setComposants();
                fournisseur.setCapacite();*/
                listeFournisseurs.add(fournisseur);
            }
        }
    }

    /*
        Trouver un fournisseur dans la liste des fournisseurs inscrits
     */
    private static void trouverFournisseur(Scanner scanner, ArrayList<Fournisseur> listeFournisseurs) {
        String choixUsager;
        System.out.println("Voulez-vous rechercher un fournisseur avec filtre? (Y/N):");

        do {
            choixUsager = scanner.nextLine();

            switch (choixUsager) {
                case "Y", "y" -> rechercherAvecFiltre(scanner, listeFournisseurs);
                case "N", "n" -> rechercherSansFiltre(scanner, listeFournisseurs);
                default -> System.err.println("Choix invalide! Réessayez de nouveau.");
            }
        } while (!choixUsager.equals("Y") && !choixUsager.equals("y") && !choixUsager.equals("N") && !choixUsager.equals("n"));
    }

    /*
        Recherche d'un fournisseur inscrit avec un filtre de recherche
     */
    private static void rechercherAvecFiltre(Scanner scannerChoix, ArrayList<Fournisseur> listeFournisseurs) {
        List<Integer> numeroOption = Arrays.asList(1, 2, 3, 4);
        String caractere;
        Scanner scannerCaractere = new Scanner(System.in);

        System.out.println("Choisissez votre option de filtre:");
        System.out.println(numeroOption.get(0) + ". Par nom");
        System.out.println(numeroOption.get(1) + ". Par adresse");
        System.out.println(numeroOption.get(2) + ". Par email");
        System.out.println(numeroOption.get(3) + ". Par téléphone");

        int choixUsager = scannerChoix.nextInt();

        //do {
            switch (choixUsager) {
                case 1 -> {
                    System.out.println("Entrez au moins un caractère du nom du fournisseur recherché:");

                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParNom(scannerCaractere, caractere, listeFournisseurs);
                }
                case 2 -> {
                    System.out.println("Entrez au moins un caractère de l'adresse du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParAdresse(scannerCaractere, caractere, listeFournisseurs);
                }
                case 3 -> {
                    System.out.println("Entrez au moins un caractère du courriel du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParEmail(scannerCaractere, caractere, listeFournisseurs);
                }
                case 4 -> {
                    System.out.println("Entrez au moins un caractère du numéro de téléphone du fournisseur recherché:");
                    caractere = scannerCaractere.nextLine();
                    verifierRechercheParTelephone(scannerCaractere, caractere, listeFournisseurs);
                }
                default -> System.err.println("Choix invalide! Réessayez de nouveau.");
            }
        //} while (numeroOption.contains(choixUsager));
    }

    /*
        Rechercher un fournisseur inscrit sans filtre. (Attention, sensible à la case)
     */
    private static void rechercherSansFiltre(Scanner scanner, ArrayList<Fournisseur> listeFournisseurs) {
        String nomFournisseur;

        System.out.println("Voici la liste des fournisseurs inscrits:\n");
        for (Fournisseur listeFournisseur : listeFournisseurs) {
            System.out.println(listeFournisseur);
        }

        System.out.println("Veuillez saisir le nom du fournisseur recherché:");
        nomFournisseur = scanner.nextLine();

        verifierRechercheSansFiltre(scanner, nomFournisseur, listeFournisseurs);
    }

    private static ArrayList<String> verifierRechercheParNom(Scanner scanner, String nom, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

        for (int i = 0; i < listeFournisseurs.size(); i++) {
                if (listeFournisseurs.get(i).getNom().contains(nom)) {
                    System.out.println("Fournisseur trouvé!");
                    fournisseursSelectionnes.add(listeFournisseurs.get(i).getNom());
                }
                /*else if (listeFournisseurs.get(i).getNom().contains(nom)) {
                    System.err.println("Aucun fournisseur possédant ce nom a été trouvé");
                    trouverFournisseur(scanner, listeFournisseurs);
                }*/

                ///fournisseursSelectionnes.add(nom);
                System.out.println(fournisseursSelectionnes);
        }
        return fournisseursSelectionnes;
    }

    private static ArrayList<String> verifierRechercheParAdresse(Scanner scanner, String adresse, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

        /*for (int i = 0; i < listeFournisseurs.size(); i++) {
            if (!listeFournisseurs.get(i).getAdresse(). {
                System.err.println("Aucun résultat trouvé");
                trouverFournisseur(scanner, listeFournisseurs);
            }
            else {
                System.out.println("Voici le(s) fournisseur(s) trouvés:");

                fournisseursSelectionnes.add(adresse);
            }
        }*/
        return fournisseursSelectionnes;
    }

    private static ArrayList<String> verifierRechercheParEmail(Scanner scanner, String email, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

//        for (int i = 0; i < listeFournisseurs.size(); i++) {
//            if (!listeFournisseurs.get(i).getEmail().contains(email)) {
//                System.err.println("Aucun résultat trouvé");
//                trouverFournisseur(scanner, listeFournisseurs);
//            }
//            else {
//                System.out.println("Voici le(s) fournisseur(s) trouvés:");
//                fournisseursSelectionnes.add(email);
//                break;
//            }
//        }
        return fournisseursSelectionnes;
    }

    private static ArrayList<String> verifierRechercheParTelephone(Scanner scanner, String telephone, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

//        for (int i = 0; i < listeFournisseurs.size(); i++) {
//            if (!listeFournisseurs.get(i).getTelephone().contains(telephone)) {
//                System.err.println("Aucun résultat trouvé");
//                trouverFournisseur(scanner, listeFournisseurs);
//            }
//            else {
//                System.out.println("Voici le(s) fournisseur(s) trouvés:");
//                fournisseursSelectionnes.add(telephone);
//            }
//        }
        return fournisseursSelectionnes;
    }

    private static ArrayList<String> verifierRechercheSansFiltre(Scanner scanner, String nom, ArrayList<Fournisseur> listeFournisseurs) {
        ArrayList<String> fournisseursSelectionnes = new ArrayList<>();

        for (int i = 0; i < listeFournisseurs.size(); i++) {
            if (listeFournisseurs.get(i).getNom().equals(nom)) {
                System.out.println("Vous avez sélectionné le fournisseur " + listeFournisseurs.get(i).getNom());
                fournisseursSelectionnes.add(nom);
                break;
            }
            /*else {
                System.err.println("Aucun fournisseur possédant ce nom a été trouvé");
                //trouverFournisseur(scanner, listeFournisseurs);
            }*/
        }
        return fournisseursSelectionnes;
    }

    private static void choisirOptionMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue chez Robotix! Voici le menu principal\n");
        System.out.println("1) Création d'un compte utilisateur\n");
        System.out.println("2) Se connecter\n");
    }
}