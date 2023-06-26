package domain.logic.Menu;
import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.Fournisseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    public void menuPrincipale(Scanner scanner) {
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
                    inscrireUtilisateur(scanner);
                case 2 :
                    inscrireFournisseur(scanner);
                case 3 :
                    connecterUtilisateur(scanner);
                case 4 :
                    connecterFournisseur(scanner);
                case 5 :
                    System.out.println("Au revoir !");
                default :
                    System.out.println("Choix invalide. Veuillez réessayez.");
            }
        } while (!options.contains(choixUsager));
    }

    public void inscrireUtilisateur(Scanner scanner) {
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

        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie);
        System.out.println("Have fun " + pseudo + " !");
        menuPrincipale(scanner);
    }

    /*
        M�thode qui permet de s'inscrire en tant que nouveau fournisseur
     */
    private void inscrireFournisseur(Scanner scanner) {
        boolean NomUnique = false;
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
            String resultats = controlleurFournisseurs.verifierNom(inputNom);
            if (resultats.equals("")){
                NomUnique = true;
            } else{
                System.out.println(resultats);
                NomUnique = false;
            }
        }

        System.out.print("Adresse de la compaganie: ");
        inputAdresse = scanner.nextLine();

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

    /*
         M�thode qui permet de se connecter en tant que fournisseur
      */
    private void connecterFournisseur(Scanner scanner) {
        System.out.println("Veuillez entrez votre nom de fournisseur: ");
        String nomFounisseur = scanner.nextLine();
        if (controlleurFournisseurs.authentificationFournisseur(nomFounisseur, "Fournisseur")) {
            System.out.println("Bienvenue " + nomFounisseur + "!");
            menuFournisseur(scanner, controlleurFournisseurs.trouverFournisseur(nomFounisseur).get(0));
        } else {
            System.out.println(nomFounisseur + " n'existe pas.");
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

    public void menuTrouverFournisseur(Scanner scanner){
        boolean continuer = true;
        Boolean bool = false;
        while (continuer) {
            System.out.println("Choisissez un filtre parmi les suivants : ");
            System.out.println("1- Nom");
            System.out.println("2- Adresse");
            System.out.println("3- Courriel");
            System.out.println("4 Type de robots fabriqués");
            System.out.println("5 Nom de la compagnie");
            System.out.println("6 Aucun filtre");
            System.out.print(">>> Votre choix :");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("Veuillez entrer le nom recherché : ");
                    String nom = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(nom));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi).get(0));
                    break;
                case "2":
                    System.out.print("Veuillez entrer l'adresse recherchée : ");
                    String adresse = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(adresse));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi2 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi2).get(0));
                    break;

                case "3":
                    System.out.print("Veuillez entrer le courriel recherché : ");
                    String courriel = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(courriel));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi3 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi3).get(0));
                    break;

                case "4" :
                    System.out.print("Veuillez entrer le type de robot recherché : ");
                    String type = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(type));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi4 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi4).get(0));
                    break;

                case "5":
                    System.out.print("Veuillez entrer le nom de la compagnie recherché : ");
                    String nomCompagnie = scanner.nextLine();
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur(nomCompagnie));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi5 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi5).get(0));
                    break;

                case "6" :
                    printFournisseurs(controlleurFournisseurs.trouverFournisseur("6"));
                    System.out.println("Entrez le nom du fournisseur choisi : ");
                    String fournisChoisi6 = scanner.nextLine();
                    menuAchat(scanner, controlleurFournisseurs.trouverFournisseur(fournisChoisi6).get(0));
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
                System.out.println("Veuillez entrer les infos du robot : ");
                System.out.print("Nom : ");
                String nomRobot = scanner.nextLine();
                System.out.print("CPU : ");
                String cpu = scanner.nextLine();
                System.out.print("Mémoire : ");
                String memoire = scanner.nextLine();
                System.out.print("Numéro de série : ");
                String numeroSerie = scanner.nextLine();
                controlleurFournisseurs.ajouterRobot(fournisseur, nomRobot,0, 0, 0, 0, Integer.parseInt(cpu), Integer.parseInt(memoire), null, null, null, null, numeroSerie);
                menuFournisseur(scanner, fournisseur);

            case "2" :
                System.out.print("Veuillez entrer le nom du robot à retirer : ");
                String nomRobot2 = scanner.nextLine();
                controlleurFournisseurs.retirerRobot(nomRobot2, fournisseur);
                menuFournisseur(scanner, fournisseur);

            
            case "3" :
                System.out.print("Composant : ");
                String composante = scanner.nextLine();
                controlleurFournisseurs.ajouterComposante(composante, fournisseur);
                menuFournisseur(scanner, fournisseur);
            
            case "4" :
                System.out.print("Composant : ");
                String composante2 = scanner.nextLine();
                controlleurFournisseurs.retirerComposante(composante2, fournisseur);
                menuFournisseur(scanner, fournisseur);

            
            case "5" :
                System.out.println("Au revoir !");
                menuPrincipale(scanner);
        }
    }

}
