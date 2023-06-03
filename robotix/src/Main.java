import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Scanner;

import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Fournisseur.Type;
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
        Fournisseur founisseur1 = new Fournisseur("Roy", "123 rue des Innovations, Montréal, QC, H1A 0A1", "roy1",
                "nom1@robotech.ca", "5142104555", TypeRobot.A, Type.CPU, 30, "RoboTechnologies");
        Fournisseur founisseur2 = new Fournisseur("Bouchard", "456 avenue des Automates, Montréal, QC, H5M 1N2", "bouchard2",
                "contact@automatech.ca", "4503335432", TypeRobot.B, Type.BRAS, 25, "Automatech");
        Fournisseur founisseur3 = new Fournisseur("Adams", "2376 boulevard des Génies, Québec, QC, G1W 2W5", "adams3",
                "service@innovatech.ca", "4509998888", TypeRobot.C, Type.ECRAN,27, "Innovatech");
        Fournisseur founisseur4 = new Fournisseur("Wilson", "89 boulevard de la Technologie, Laval, QC, H7M 7B7", "wilson4",
                "assistance@iRobot.ca", "4502109876", TypeRobot.B, Type.CAMERA,35, "iRobot");
        Fournisseur founisseur5 = new Fournisseur("Thompson", "10 Place de la Robotique, Longueuil, QC, J4H 1A1", "thompson5",
                "info@roboPro.ca", "4506780000", TypeRobot.A, Type.HAUTPARLEUR,22, "RoboPro");

        listeFournisseurs.add(founisseur1);
        listeFournisseurs.add(founisseur2);
        listeFournisseurs.add(founisseur3);
        listeFournisseurs.add(founisseur4);
        listeFournisseurs.add(founisseur5);

        return listeFournisseurs;
    }

    private static void choisirOptionMenu(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        int choixUsager;
        ArrayList<Integer> options = new ArrayList<>(Arrays.asList(1, 2)) ;

        System.out.println("Choisissez l'une des options suivantes:");
        System.out.println(options.get(0) + "- S'inscrire en tant que fournisseur");
        System.out.println(options.get(1) + "- Se connecter en tant que fournisseur");

        do {
            System.out.println("Entrez votre choix: ");
            choixUsager = scanner.nextInt();

            switch (choixUsager) {
                case 1 -> inscrireFournisseur(listeFournisseurs);
                case 2 -> connecterFournisseur(listeFournisseurs);
                default -> System.out.println("Choix invalide. Veuillez réessayez.");
            }
        } while (!options.contains(choixUsager));
    }

    private static void inscrireFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        boolean NomUnique = false;
        boolean PseudoUnique = false;
        boolean EmailValide = false;
        boolean TelephoneValide = false;
        boolean TypeRobotValide = false;
        boolean TypeComposanteValide = false;

        String inputNom = "";
        String inputPseudo = "";
        String inputEmail = "";
        String inputTelephone = "";
        String inputAdresse;
        String inputTypeRobot = "";
        String inputTypeComposantes = "";
        String inputCapacite;
        String inputCompagnie;

        System.out.println("Complétez les informations suivantes afin de vous inscrire comme étant un fournisseur:");

        while (!NomUnique) {
            System.out.print("Nom: ");
            inputNom = scanner.nextLine();
            NomUnique = verifierNomFournisseur(inputNom, listeFournisseurs, NomUnique);
        }

        System.out.print("Adresse: ");
        inputAdresse = scanner.nextLine();

        while (!PseudoUnique)
        {
            System.out.print("Pseudo: ");
            inputPseudo = scanner.nextLine();
            PseudoUnique = verifierPseudoFournisseur(inputPseudo, listeFournisseurs, PseudoUnique);
        }

        while (!EmailValide)
        {
            System.out.print("Email: ");
            inputEmail = scanner.nextLine();
            EmailValide = verifierEmailFournisseur(inputEmail);
        }

        while (!TelephoneValide)
        {
            System.out.print("Numéro de téléphone: ");
            inputTelephone = scanner.nextLine();
            TelephoneValide = verifierTelephoneFournisseur(inputTelephone);
        }

        while (!TypeRobotValide)
        {
            System.out.print("Type de robots fabriqués: ");
            inputTypeRobot = scanner.nextLine();
            TypeRobotValide = verifierTypeRobot(inputTypeRobot);
        }

        while (!TypeComposanteValide)
        {
            System.out.print("Type de composantes fabriquées: ");
            inputTypeComposantes = scanner.nextLine();
            TypeComposanteValide = verifierTypeComposante(inputTypeComposantes);
        }

        System.out.print("Capacité de fabrication: ");
        inputCapacite = scanner.nextLine();

        System.out.print("Nom de compagnie: ");
        inputCompagnie = scanner.nextLine();

        ajouterFournisseur(inputNom, inputAdresse, inputPseudo, inputEmail, inputTelephone, inputTypeRobot,
                inputTypeComposantes, inputCapacite, inputCompagnie, listeFournisseurs);

        System.out.println(listeFournisseurs);
    }



    private static boolean verifierNomFournisseur(String inputNom, ArrayList<Fournisseur> listeFournisseurs, boolean NomUnique) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(inputNom)) {
                NomUnique = false;
                System.out.println("Ce nom de fournisseur existe déjà. Veuillez saisir un autre nom");
                break;
            } else
                NomUnique = true;
        }
        return NomUnique;
    }

    private static boolean verifierPseudoFournisseur(String inputPseudo, ArrayList<Fournisseur> listeFournisseurs, boolean PseudoUnique) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getPseudo().equals(inputPseudo)) {
                PseudoUnique = false;
                System.out.println("Ce pseduo existe déjà. Veuillez en saisir un autre.");
                break;
            }
            else
                PseudoUnique = true;
        }
        return PseudoUnique;
    }

    private static boolean verifierEmailFournisseur(String inputEmail) {
        boolean EmailValide;
        if (!inputEmail.contains("@"))
        {
            EmailValide = false;
            System.out.println("L'adresse courriel est invalide. Veuillez en saisir un autre.");
        }
        else
            EmailValide = true;

        return EmailValide;
    }

    private static boolean verifierTelephoneFournisseur(String inputTelephone) {
        boolean TelephoneValide;
        if (inputTelephone.length() != 10)
        {
            TelephoneValide = false;
            System.out.println("Le numéro de téléphone doit obtenir exactement 10 caractères. Veuillez réessayez.");
        }
        else
            TelephoneValide = true;

        return TelephoneValide;
    }

    private static boolean verifierTypeRobot(String inputTypeRobot) {
        boolean TypeRobotValide;
        ArrayList<String> typeRobotTrouve = new ArrayList<>();

        for (TypeRobot typeRobot : TypeRobot.values()) {
            if (typeRobot.equals(inputTypeRobot))
                typeRobotTrouve.add(typeRobot.toString());
        }
            TypeRobotValide = true;

        return TypeRobotValide;
    }

    private static boolean verifierTypeComposante(String inputTypeComposantes) {
        boolean TypeComposanteValide;


        return false;
    }

    /*
        Ajouter un nouveau fournisseur
     */
    private static void ajouterFournisseur(String inputNom, String inputAdresse, String inputPseudo,
                                           String inputCourriel, String inputTelephone, String inputTypeRobot,
                                           String inputTypeComposantes, String inputCapacite, String inputCompagnie,
                                           ArrayList<Fournisseur> listeFournisseurs) {
        Fournisseur nouveauFournisseur = new Fournisseur(inputNom, inputAdresse, inputPseudo, inputCourriel,
                inputTelephone, TypeRobot.valueOf(inputTypeRobot), Type.valueOf(inputTypeComposantes),
                Double.parseDouble(inputCapacite), inputCompagnie);
        listeFournisseurs.add(nouveauFournisseur);
        }

        /*

         */
    private static void connecterFournisseur(ArrayList<Fournisseur> listeFournisseurs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrez votre nom de fournisseur:");
        scanner.nextLine();
// ---------------------------- AJOUTER UN BOOL POUR VALIDER QUE JE SUIS CONNECTER COMME FOURNISSEUR POUR UTILISER LES OPTIONS DU MENU ------------------------
        for (int i = 0; i < listeFournisseurs.size() ; i++) {
            if (listeFournisseurs.get(0).getNom().equals(scanner.nextLine())) {
                System.out.println("Vous êtes connecté en tant que fournissseur");
                break;
            }
            else
                System.out.println("Ce nom n'existe pas");
        }
    }
}
