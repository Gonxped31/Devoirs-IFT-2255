package domain.logic.Menu;
import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

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

    //MOVED TO MenuUtilisateur
    public void inscrireUtilisateur(Scanner scanner) {
        // TODO : VERIFIER SI L'UTILISATEUR EST DÉJÀ INSCRIT
        boolean PseudoUnique = false;
        boolean EmailValide = false;
        boolean TelephoneValide = false;
        String pseudo = "";
        String courriel = "";
        String telephone = "";
        ArrayList<String> listeInteret = new ArrayList<>();

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
        System.out.println("Ajouter 10 interets: ");
        for (int i = 9; i >= 0; i--) {
            System.out.println("Veuillez entrer un interet: ");
            String interet = scanner.nextLine();
            listeInteret.add(interet);
            System.out.println("Il vous reste " + i + "interets a choisir");
        }
        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo, courriel, telephone, nomCompagnie, listeInteret);
        System.out.println("Have fun " + pseudo + " !");
        menuPrincipale(scanner);
    }

    //MOVED TO MenuUtilisateur
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

    //MOVED TO MenuUtilisateur
    public void menuUtilisateur(Scanner scanner, String pseudo) {
        ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
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
        System.out.println("2- Gérer ma flotte");  
        System.out.println("3- Gérer mes taches");
        System.out.println("4- Gérer mes activites");
        System.out.println("5- Gerer mon reseau social");
        System.out.println("6- Achats");
        System.out.println("7- Voir mes notifications");
        System.out.println("8- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch(choix){
            case("1") :
                modifierProfile(scanner, pseudo);
            case("2") :
                gererMaFlotte(scanner, pseudo);
            case("3") :
                gererMesTaches();
            case("4") :

            case("5") :
                gererReseauSocial(scanner, pseudo);
            case("6") :

            case("7") :
                voirNotification();
            case("8") :
                menuPrincipale(scanner);
        }
    }

    //MOVED TO MenuUtilisateur
    public void modifierProfile(Scanner scanner, String pseudo) {
        System.out.println("Que voulez-vous modifier");
        System.out.println("1- Nom");
        System.out.println("2- Prenom");
        System.out.println("3- Adresse");
        System.out.println("4- Pseudo");
        System.out.println("5- Email");
        System.out.println("6- Numero de telephone");
        System.out.println("7- Nom de la compagnie");
        System.out.println("8- Mot de passe");
        String choix = scanner.nextLine();
        switch(choix){
            case "1":
                System.out.println("Entrez votre nouveau nom: ");
                String nom = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "nom", nom);
                break;
            case "2":
                System.out.println("Entrez votre nouveau prenom: ");
                String prenom = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "prenom", prenom);
                break;
            case "3":
                System.out.println("Entrez votre nouvelle adresse: ");
                String adresse = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "adresse", adresse);
                break;
            case "4":
                System.out.println("Entrez votre nouveau pseudo: ");
                String newPseudo = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "pseudo", newPseudo);
                break;
            case "5": 
                System.out.println("Entrez votre nouveau email : ");
                String email = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "email", email);
                break;
            case "6":
                System.out.println("Entrez votre nouveau numero de telephone : ");
                String numTele = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "numerotelephone", numTele);
                break;
            case "7": 
                System.out.println("Entrez votre nouvelle compagnie : ");
                String compagnie = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "nomcompagnie", compagnie);
                break;
            case "8": 
                System.out.println("Entrez votre nouveau mot de passe : ");
                String mdp= scanner.nextLine();
                controlleurUtilisateurs.modifierProfile(pseudo, "mdp", mdp);
                break;
        }        
    }

    //MOVED TO MenuGestionFlotte
    public void gererMaFlotte(Scanner scanner, String pseudo) {
        System.out.println("1- Enregistrer un robot");
        System.out.println("2- Afficher état d'un robot");
        System.out.println("3- Ajouter une composante a un robot");
        System.out.println("4- Afficher les metriques de ma flotte");
        System.out.println("5- Creer action");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> menuEnregistrerRobot(scanner, pseudo);
            case "2" -> printRobots(controlleurUtilisateurs.afficherEtatRobot(pseudo));
            case "3" -> menuAjouterComposante(scanner, pseudo);
            case "4" -> menuAfficherMetriquesFlotte(pseudo);
            case "5" -> menuCreerActions(scanner, pseudo);
        }
    }

    //MOVED TO MenuGestionFlotte
    public void menuAfficherMetriquesFlotte(String pseudo) {
        System.out.println("********** Métriques de ma flotte **********");
        System.out.println("Nombre de robot : " + controlleurUtilisateurs.afficherMetriquesFlotte(pseudo));
        System.out.println("Consommation globale du CPU : 83 %");
        System.out.println("Consommation globale de la mémoire : 85 %");
        System.out.println(" ");

    }

    //MOVED TO MenuGestionFlotte
    public void menuCreerActions(Scanner scanner, String pseudo) {
        ArrayList<Composant> composantes = new ArrayList<>();
        System.out.println("Quelles actions voulez-vous creer?");
        System.out.println("Nom: ");
        String nomAction = scanner.nextLine();
        System.out.println("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?: ");
        String decision = "Y";
        while (decision.toUpperCase().equals("Y")) {
            System.out.println("Entrez une composante:");
            String comp = scanner.nextLine();
            Composant compo = new Composant(comp, null, null, null);
            composantes.add(compo);
            System.out.println("Voulez-vous rajouter une composante a cette action (Y/N)?");
            decision = scanner.nextLine();
        }
        controlleurUtilisateurs.creerAction(pseudo, nomAction, composantes);
    }

    //MOVED TO MenuGestionFlotte
    public void menuEnregistrerRobot(Scanner scanner,String pseudo){
        boolean reessayer = true;
        while (reessayer) {
            System.out.println("Nom du robot : ");
            String nomRobot = scanner.nextLine();
            System.out.println("Numero de serie: ");
            String numeroDeSerie = scanner.nextLine();
            if(controlleurUtilisateurs.enregistrerRobot(pseudo, nomRobot, numeroDeSerie)) {
                System.out.println("Le robot a été bien enrégistré !");
                reessayer = false;
            } else {
                System.out.println("Aucun robot vendu par nos founisseurs ne possède ce numéro de série. Voulez vous rééssayer ?");
                System.out.println("1- Oui");
                System.out.println("2- Non");
                System.out.print(">> Votre choix : ");
                if (scanner.nextLine().equalsIgnoreCase("non")){
                    break;
                }
            }
        }
        System.out.println(" ");
        menuUtilisateur(scanner, pseudo);

    }

    //MOVED TO MenuGererTacheActivite
    public void gererMesTaches(Scanner scanner, String pseudo){
        System.out.println("1- Créer une tâche");
        System.out.println("2- Allouer une tache a un robot");
        System.out.println("3- Revenir au menu principal");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix){ 
            case "1": 
                creerTache(scanner, pseudo);
                break;
            case "2": 
                allouerTacheRobot(scanner, pseudo);
                break;
            case "3":
                menuPrincipale(scanner);
                break;
        }
    }

    //MOVED TO MenuGererTacheActivite
    public void creerTache(Scanner scanner, String pseudo){
        ArrayList<Action> actions = new ArrayList<Action>();
        System.out.println("Quelles actions voulez-vous creer?");
        System.out.println("Nom: ");
        String nomAction = scanner.nextLine();
        System.out.println("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?: ");
        String decision = "Y";
        while (decision.toUpperCase().equals("Y")) {
            System.out.println("Entrez une action:");
            String a = scanner.nextLine();
            Action act = new Action(a, null);
            actions.add(act);
            System.out.println("Voulez-vous rajouter une action a cette tache?(Y/N)");
            decision = scanner.nextLine();
        }
        controlleurUtilisateurs.creerTache(pseudo, nomAction, actions);
    }

    //MOVED TO MenuGererTacheActivite
    public void allouerTacheRobot(Scanner scanner, String pseudo){
        System.out.println("A quel robot voulez-vous allouer une tache");
        String robot = scanner.nextLine();
        System.out.println("Quel est le nom de la tache a allouer?");
        String tache = scanner.nextLine();
        if (controlleurUtilisateurs.allouerTacheRobot(pseudo, robot, tache))
            System.out.println("La tache a ete allouée avec succès");
        else{
            System.out.println("La tache n'a pas pu être allouée car vous ne possédez pas le robot ou la tache indiqué");
        }
        menuUtilisateur(scanner, pseudo);
    }

    //MOVED TO MenuGererTacheActivite
    public void gererMesActivites(Scanner scanner, String pseudo){
        System.out.println("1- Créer une activites");
        System.out.println("2- Rejoindre une activite");
        System.out.println("3- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix){ 
            case "1": 
                menuCreerActivite(scanner);
                break;
            case "2":
                menuRejoindreActivite();
                break;
            case "3":
                menuUtilisateur(scanner, pseudo);
        }
    }

    //MOVED TO MenuGererTacheActivite
    public void menuCreerActivite(Scanner scanner){
        boolean continuer = false;
        ArrayList<String> listeTache = new ArrayList<>();
        System.out.println(" ");
        System.out.print("Nom de l'activité : ");
        String nomActivite = scanner.nextLine();
        System.out.println("Date de debut : ");
        String dateDebut = scanner.nextLine();
        System.out.println("Date de fin : ");
        String dateFin = scanner.nextLine();
        do {
            System.out.print("Veuillez entrer une tache : ");
            listeTache.add(scanner.nextLine());
            System.out.print("Voulez-vous ajouter une autre tache ? (répondez par oui ou non): ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")){
                continuer = true;
            }
        } while (continuer);

        do {
            System.out.print("Veuillez entrer un interêt : ");
            listeTache.add(scanner.nextLine());
            System.out.print("Voulez-vous ajouter une autre tache ? (répondez par oui ou non): ");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")){
                continuer = true;
            }
        } while (continuer);

        if (controlleurUtilisateurs.creerActivites(pseudo, nomActivite, dateDebut, dateFin, listeTache)) {
            System.out.println("L'activitée a été bien créée (:");
        } else {
            System.out.println("Cette activitée existe déjà...");
        }
        menuUtilisateur(scanner, pseudo);
    }

    //MOVED TO MenuGererTacheActivite
    public void menuRejoindreActivite(){

    }

    //MOVED TO MenuGererReseau
    public void gererReseauSocial(Scanner scanner, String pseudo){
        System.out.println("1- Suivre un utilisateur");
        System.out.println("2- Gerer mes suiveurs");
        System.out.println("3- Gerer mes interets");
        System.out.println("4- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix){ 
            case "1":
                System.out.println("Quel est le nom de l'utilisateur que vous voulez suivre");
                String nom = scanner.nextLine();
                if (controlleurUtilisateurs.suivreUtilisateur(pseudo, nom))
                    System.out.println("Vous suivez maintenant " + nom);
                else{
                    System.out.println(nom + " n'a pas pu etre ajoute a votre reseau");
                }
                break;
            case "2": 
                controlleurUtilisateurs.gererSuiveurs(pseudo);
                break;
            case "3": 
                controlleurUtilisateurs.gererInterets(pseudo);
                break;
            case "4":
                menuUtilisateur(scanner, pseudo);
        }
    }

    
    //MOVED TO MenuUtilisateur
    public void voirNotification(){
        for (String notif : controlleurUtilisateurs.voirNotifications()) {
            System.out.println(notif);
        }
    }

    public void menuTrouverFournisseur(Scanner scanner){
        boolean continuer = true;
        boolean bool = false;
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

    //MOVED TO MenuGestionFlotte
    public void menuAjouterComposante(Scanner scanner, String pseudo) {
        System.out.print("Nom de la composante à ajouter : ");
        String nomComposante = scanner.nextLine();
        System.out.print("Nom du robot : ");
        String nomRobot = scanner.nextLine();
        if (controlleurUtilisateurs.ajouterComposanteRobot(nomComposante, nomRobot, pseudo)){
            System.out.println(" ");
            System.out.println("La composante a bien été ajoutée.");
            System.out.println(" ");
        } else {
            System.out.println("La composante ou le robot entrée n'existe pas ):");
        }
        menuUtilisateur(scanner, pseudo);
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

    //MOVED TO MenuGestionFlotte
    public void printRobots(ArrayList<Robot> robots) {
        for (Robot robot : robots) {
            System.out.println(">>" + robot.getNom());
            System.out.println("Position : (" + robot.getX() + ", " + robot.getY() + ")");
            System.out.println("Vitesse : " + robot.getVitesse());
            System.out.println("Niveau de batterie : " + robot.getBatterie());
            System.out.println("CPU : " + robot.getCpu());
            System.out.println("Memoire" + robot.getMemoire());
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
