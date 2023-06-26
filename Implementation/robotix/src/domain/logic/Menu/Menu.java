package domain.logic.Menu;
import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.Fournisseur;
import domain.logic.Robot.Composant;
import domain.logic.Robot.TypesComposants;

import javax.xml.transform.sax.SAXSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private TypesComposants typesComposants;
    public void menuPrincipale(Scanner scanner) {
        String choixUsager;
        ArrayList<Integer> options = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)) ;

        System.out.println("********Binevenue chez Robotix!********");
        System.out.println("Veuillez choisir l'une des options suivantes:");
        System.out.println("1- S'inscrire en tant qu'utilisateur");
        System.out.println("2- S'inscrire en tant que fournisseur");
        System.out.println("3- Se connecter en tant qu'utilisateur");
        System.out.println("4- Se connecter en tant que fournisseur");
        System.out.println("5- Quitter Robotix");

        do {
            System.out.print("Entrez votre choix: ");
            choixUsager = scanner.nextLine();

            switch (choixUsager) {
                case "1" :
                    inscrireUtilisateur(scanner);
                    break;
                case "2" :
                    inscrireFournisseur(scanner);
                    break;
                case "3" :
                    connecterUtilisateur(scanner);
                    break;
                case "4" :
                    connecterFournisseur(scanner);
                    break;
                case "5" :
                    System.out.println("Au revoir !");
                    break;
                default :
                    System.out.println("Choix invalide. Veuillez réessayez.");
                    break;
            }
        } while (!options.contains(Integer.parseInt(choixUsager)));
    }

    /*Section Utilisateur */
    public void inscrireUtilisateur(Scanner scanner) {
        // TODO : VERIFIER SI L'UTILISATEUR EST DÉJÀ INSCRIT
        boolean PseudoUnique = false;
        boolean EmailValide = false;
        boolean TelephoneValide = false;
        String pseudo = "";
        String courriel = "";
        String telephone = "";

        System.out.println("********Nouvel utilisateur********");
        System.out.print("Prenom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom :");
        String nom = scanner.nextLine();

        while (!PseudoUnique) {
            System.out.print("Pseudo: ");
            pseudo = scanner.nextLine();
            PseudoUnique = controlleurUtilisateurs.verifierPseudo(pseudo);
            if (!PseudoUnique) {
                System.out.print("Ce pseudo existe déjà, veuillez entrer un autre : ");
            }
        }

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            courriel = scanner.nextLine();
            EmailValide = controlleurUtilisateurs.verifierEmail(courriel);
            if (!EmailValide) {
                System.out.println("Email invalide, veuillez reessayer.");
            }
        }

        while (!TelephoneValide) {
            System.out.print("Numéro de téléphone: ");
            telephone = scanner.nextLine();
            TelephoneValide = controlleurUtilisateurs.verifierTelephone(telephone);
            if (!TelephoneValide) {
                System.out.println("Le numéro de téléphone doit obtenir exactement 10 caractères. Veuillez réessayez: ");
            }
        }

        System.out.println("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.println("Nom de la compagnie : ");
        String nomCompagnie = scanner.nextLine();

        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie);
        System.out.println("Have fun " + pseudo + " !");
        menuPrincipale(scanner);
    }

    public void connecterUtilisateur(Scanner scanner) {
        System.out.println("Veuillez entrer votre pseudo: ");
        String connexion = scanner.nextLine();
        if (controlleurUtilisateurs.authentification(connexion, "Utilisateur")) {
            System.out.println("Bienvenue " + connexion + "!");
            menuUtilisateur(scanner, connexion);
        } else {
            System.out.println(connexion + " n'existe pas.");
            menuPrincipale(scanner);
        }
    }

    public void menuUtilisateur(Scanner scanner, String pseudo) {
        ArrayList<String> fournisCPU = new ArrayList<>();
        fournisCPU.add("Fournisseur6" );
        fournisCPU.add("Adresse1");
        fournisCPU.add("Courriel3");
        fournisCPU.add("Numero4");
        fournisCPU.add("type3");
        fournisCPU.add("compagnie2");
        System.out.println("******************** Menu: " + pseudo + " ********************");
        System.out.println("Bienvenue! Veuillez choisir une option:");
        System.out.println("1- Modifier mon profile");
        System.out.println("");
        System.out.println("Gérer mes follower");
        System.out.println("7- Gérer ma flotte");
        System.out.println("8- Participer à une activité");
        System.out.println("9- Créer une activité");

        System.out.println("10- Afficher les problèmes du système");
        System.out.println("11- Trouver un fournisseur");
        System.out.println("12- Quitter");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch(choix){
            case("1") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 1, pseudo);
            case("2") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 2, pseudo);
            case("3") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 3, pseudo);
            case("4") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 4, pseudo);
            case("5") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 5, pseudo);
            case("6") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 6, pseudo);
            case("7") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 7, pseudo);
            case("8") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 8, pseudo);
            case("9") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 9, pseudo);
            case("10") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 10, pseudo);
            case("11") :
                controlleurUtilisateurs.actionsUtilisateurs(scanner, 11, pseudo);
            case("12") :
                menuPrincipale(scanner);
        }
    }

    public void modifierProfile() {

    }

    public void gereMaFlotte() {
        System.out.println("2- Enregistrer un robot");
        System.out.println("3- Afficher état d'un robot");
        System.out.println("4- Ajouter une composante a un robot");
        System.out.println("5- Ajouter une action a un robot");
        System.out.println("5- Créer une tâche");
        System.out.println("6- Allouer une tache a un robot");
    }



    public void menuTrouverFournisseur(Scanner scanner){
        boolean continuer = true;
        Boolean bool = false;
        while (continuer) {
            System.out.println("Choisissez un filtre parmi les suivants : ");
            System.out.println("1- Nom");
            System.out.println("2- Adresse");
            System.out.println("3- Type de composantes");
            System.out.println("4- Aucun filtre");
            System.out.print(">>> Votre choix :");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("Veuillez entrer le nom recherché : ");
                    String nom = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(choix,nom));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur("1" ,fournisChoisi).get(0));
                    break;
                case "2":
                    System.out.print("Veuillez entrer l'adresse recherchée : ");
                    String adresse = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(choix, adresse));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi2 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur("1", fournisChoisi2).get(0));
                    break;

                case "3":
                    System.out.print("Veuillez entrer le Type de composante recherché : ");
                    String courriel = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(choix, courriel));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi3 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur("1", fournisChoisi3).get(0));
                    break;

                case "4" :
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur("4", null));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi6 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur("1", fournisChoisi6).get(0));
                    break;

                default:
                    System.out.println("Veuillez choisir un élément dans la liste.");
                    break;
            }
        }
    }

    public void menuAchat(Scanner scanner, Fournisseur fournisseur) {

    }

    public void menuAchatComposante(Scanner scanner, Fournisseur fournisseur){

    }

    public void menuAchatRobot(Scanner scanner, Fournisseur fournisseur){

    }

    public void menuAjouterComposante(Scanner scanner, String pseudo) {
        System.out.print("Nom de la composante : ");
        String nom = scanner.nextLine();
        controlleurUtilisateurs.actionsUtilisateurs(scanner, 3, pseudo);
    }

    public void printComposantes(Fournisseur fournisseur){
        for (Composant composant: fournisseur.getInventaireComposant()) {

        }
    }

    public void printFournisseurs(ArrayList<Fournisseur> fournisseurs) {
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.print(fournisseur.getNom());
            System.out.print(" ,");
            System.out.print(fournisseur.getAdresse());
            System.out.print(" ,");
            System.out.print(fournisseur.getEmail());
            System.out.print(" ,");
            System.out.print(fournisseur.getTelephone());
            System.out.print(" ,");
            System.out.print(fournisseur.getNomCompagnie());
            System.out.print(" ,");
            System.out.print(fournisseur.getTypeRobotFabriquer());
            System.out.print(" ,");
            System.out.print(fournisseur.getTypeComposantesFabriquer());
            System.out.print(" ,");
            System.out.print(fournisseur.getCapaciteProductionComposantes());
            System.out.println(" ");
        }
    }



    /* Section Fournisseur */
    private void inscrireFournisseur(Scanner scanner) {
        boolean NomUnique = false;
        boolean EmailValide = false;
        boolean TelephoneValide = false;

        String inputNom = "";
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
            NomUnique = controlleurFournisseurs.verifierNom(inputNom);
            if (!NomUnique){
                System.out.println("Ce nom de fournisseur existe déjà. Veuillez saisir un autre nom: ");
            }
        }

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            inputEmail = scanner.nextLine();
            EmailValide = controlleurFournisseurs.verifierEmail(inputEmail);
            if (!EmailValide) {
                System.out.println("Email invalide, veuillez reessayer.");
            }
        }

        while (!TelephoneValide) {
            System.out.print("Num�ro de t�l�phone: ");
            inputTelephone = scanner.nextLine();
            TelephoneValide = controlleurFournisseurs.verifierTelephone(inputTelephone);
            if (!TelephoneValide) {
                System.out.println("Le num�ro de t�l�phone doit obtenir exactement 10 caract�res. Veuillez r�essayez: ");
            }
        }

        System.out.print("Adresse : ");
        inputAdresse = scanner.nextLine();
        System.out.print("Type de robots fabriqu�s: ");
        inputTypeRobot = scanner.nextLine();
        System.out.print("Type de composantes fabriqu�es: ");
        inputTypeComposantes = scanner.nextLine();
        System.out.print("Capacit� de fabrication: ");
        inputCapacite = scanner.nextLine();
        System.out.print("Nom de compagnie: ");
        inputCompagnie = scanner.nextLine();

        controlleurFournisseurs.inscriptionFournisseur(inputNom, inputAdresse, inputEmail,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);

        menuPrincipale(scanner);
    }

    private void connecterFournisseur(Scanner scanner) {
        System.out.println("Veuillez entrez votre nom de fournisseur: ");
        String nomFounisseur = scanner.nextLine();
        if (controlleurFournisseurs.authentificationFournisseur(nomFounisseur, "Fournisseur")) {
            System.out.println("Bienvenue " + nomFounisseur + "!");
            menuFournisseur(scanner, controlleurFournisseurs.trouverFournisseur("1", nomFounisseur).get(0));
        } else {
            System.out.println(nomFounisseur + " n'existe pas.");
            menuPrincipale(scanner);
        }
    }

    public void menuFournisseur(Scanner scanner, Fournisseur fournisseur) {
        System.out.println("******************** Menu Fournisseur ********************");
        System.out.println("Bienvenue ! Veuillez choisir une option:");
        System.out.println("1- Ajouter un nouveau robot");
        System.out.println("2- Retirer un robot");
        System.out.println("3- Ajouter une composante");
        System.out.println("4- Retirer une composante");
        System.out.println("5- Quitter");
        System.out.print(">>> Votre choix : ");
        String choixUsager = scanner.nextLine();

        switch (choixUsager) {
            case "1" :
                System.out.print("Type de robot fabriqué : ");
                String type = scanner.nextLine();
                controlleurFournisseurs.ajouterRobot(fournisseur);
                System.out.println(" ");
                System.out.println("Le robot a été rajouté avec succès !");
                System.out.println(" ");
                menuFournisseur(scanner, fournisseur);

            case "2" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String nomRobot2 = scanner.nextLine();
                if(controlleurFournisseurs.retirerRobot(nomRobot2, fournisseur)){
                    System.out.println("Le robot a été retiré avec succès !");

                } else {
                    System.out.println("Vous ne possédez ce robot.");
                }
                menuFournisseur(scanner, fournisseur);

            
            case "3" :
                System.out.print("Composant : ");
                String composante = scanner.nextLine();
                System.out.print("Prix : ");
                String prix = scanner.nextLine();
                System.out.print("Description : ");
                String description = scanner.nextLine();
                System.out.print("Type de la composante : ");
                String type = scanner.nextLine();
                controlleurFournisseurs.ajouterComposante(composante, Double.parseDouble(prix), description, type, fournisseur);
                System.out.println(" ");
                System.out.println("La composante a été rajoutée avec succès");
                System.out.println(" ");
                menuFournisseur(scanner, fournisseur);
            
            case "4" :
                System.out.print("Composant : ");
                String composante2 = scanner.nextLine();
                if(controlleurFournisseurs.retirerComposante(composante2, fournisseur)){
                    System.out.println("La composante a été retirée avec succès !");
                } else {
                    System.out.println("Vous ne possédez cette composante.");
                }
                menuFournisseur(scanner, fournisseur);

            case "5" :
                System.out.println("Au revoir !");
                menuPrincipale(scanner);
        }
    }
}
