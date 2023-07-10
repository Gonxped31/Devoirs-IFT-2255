package domain.logic.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Notification;

import domain.logic.Controller.ControlleurUtilisateurs;

import javax.swing.*;

public class MenuUtilisateur extends JFrame {
    /*Section Utilisateur */
    private ControlleurUtilisateurs controlleurUtilisateurs;// = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = new DbControleur();
    private Menu menu;
    private MenuGestionFlotte menuGestionFlotte = new MenuGestionFlotte();
    private MenuGererTacheActivite menuGererTacheActivite = new MenuGererTacheActivite();
    private MenuGestionReseau menuReseau = new MenuGestionReseau();
    private JPanel menuUtilisateurPanel;
    private JPanel inscriptionUtilisateurPanel = new JPanel();
    private JPanel loginUtilisateurPanel = new JPanel();
    private JLabel inscriptionUtilisateurLabel = new JLabel("Inscription en tant qu'utilisateur");
    private JLabel loginUtilisateurLabel = new JLabel("CONNEXION");
    private JLabel prenomLabel = new JLabel("Prenom");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JLabel interetsLabel = new JLabel("Intérêts");
    private JTextField prenomField = new JTextField();
    private JTextField nomField = new JTextField();
    private JTextField pseudoField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JButton btnRetour = new JButton("Retour");

    public MenuUtilisateur() throws IOException {
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void afficherFormulaireInscription(JFrame jFrame) {
        creerFormulaireInscription();
        jFrame.getContentPane().removeAll();
        jFrame.setContentPane(inscriptionUtilisateurPanel);
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void creerFormulaireInscription() {
        inscriptionUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
        inscriptionUtilisateurPanel.add(inscriptionUtilisateurLabel);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(prenomLabel);
        inscriptionUtilisateurPanel.add(prenomField);
        inscriptionUtilisateurPanel.add(nomLabel);
        inscriptionUtilisateurPanel.add(nomField);
        inscriptionUtilisateurPanel.add(pseudoLabel);
        inscriptionUtilisateurPanel.add(pseudoField);
        inscriptionUtilisateurPanel.add(courrielLabel);
        inscriptionUtilisateurPanel.add(courrielField);
        inscriptionUtilisateurPanel.add(mdpLabel);
        inscriptionUtilisateurPanel.add(mdpField);
        inscriptionUtilisateurPanel.add(telephoneLabel);
        inscriptionUtilisateurPanel.add(telephoneField);
        inscriptionUtilisateurPanel.add(adresseLabel);
        inscriptionUtilisateurPanel.add(adresseField);
        inscriptionUtilisateurPanel.add(nomCompagnieLabel);
        inscriptionUtilisateurPanel.add(nomCompagnieField);
        inscriptionUtilisateurPanel.add(interetsLabel);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnRetour);
    }

    public void afficherFormulaireConnexion(JFrame jFrame) {
        creerFormulaireConnexion();
        jFrame.getContentPane().removeAll();
        jFrame.setContentPane(loginUtilisateurPanel);
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void creerFormulaireConnexion() {
        loginUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
        loginUtilisateurPanel.add(loginUtilisateurLabel);
        loginUtilisateurPanel.add(Box.createHorizontalStrut(10));
        loginUtilisateurPanel.add(pseudoLabel);
        loginUtilisateurPanel.add(pseudoField);
        loginUtilisateurPanel.add(mdpLabel);
        loginUtilisateurPanel.add(mdpField);
        loginUtilisateurPanel.add(btnRetour);
    }

    public void inscrireUtilisateur(Scanner scanner) throws ParseException, IOException {
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
            try {
                PseudoUnique = controlleurUtilisateurs.verifierPseudo(pseudo);
            } catch (NullPointerException e){
                PseudoUnique = false;
            }
            if (PseudoUnique) {
                System.out.print("Ce pseudo existe déjà, veuillez entrer un autre : ");
            } else {
                break;
            }
        }

        System.out.print("Mot de passe: ");
        String mdp = scanner.nextLine();

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            courriel = scanner.nextLine();
            try {
                EmailValide = controlleurUtilisateurs.verifierEmail(courriel);
            } catch (NullPointerException e){
                EmailValide = true;
            }
            if (!EmailValide) {
                System.out.println("Email invalide, veuillez reessayer.");
            }
        }

        while (!TelephoneValide) {
            System.out.print("Numéro de téléphone: ");
            telephone = scanner.nextLine();
            try {
                TelephoneValide = controlleurUtilisateurs.verifierTelephone(telephone);
            } catch (NullPointerException e){
                TelephoneValide = true;
            }
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
            System.out.println("Il vous reste " + i + " interets a choisir");
            System.out.println("Voulez-vous en ajouter encore? (Y/N)");
            String decis = scanner.nextLine();
            if (decis.toUpperCase().equals("N")){
                break;
            }
        }
        this.controlleurUtilisateurs = new ControlleurUtilisateurs(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, listeInteret);
        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, listeInteret);
        System.out.println("Have fun " + pseudo + " !");
        menu = new Menu();
        menu.menuPrincipale(scanner);
    }

    public void connecterUtilisateur(Scanner scanner) throws ParseException, IOException {

        controlleurUtilisateurs = new ControlleurUtilisateurs();
        for (int i = 0; i < 3; i++) {
            System.out.println("Veuillez entrer votre pseudo: ");
            String connexion = scanner.nextLine();
            System.out.println("Veuillez entrer votre mot de passe: ");
            String mdp = scanner.nextLine();
            connexion = connexion.trim();
            if (controlleurUtilisateurs.authentification(connexion, mdp)) {
                System.out.println("Bienvenue " + connexion + "!");
                menuUtilisateur(scanner, connexion);
                break;
            } else {
                System.out.println(connexion + " n'existe pas.");

            }
        }
        menu = new Menu();
        menu.menuPrincipale(scanner);
    }


    public void menuUtilisateur(Scanner scanner, String pseudo) throws ParseException, IOException {
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
        System.out.println("8- Faire une requete publique");
        System.out.println("9- Souscrire a un interet");
        System.out.println("10- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case ("1") -> {
                modifierProfile(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
            }
            case ("2") -> menuGestionFlotte.gererMaFlotte(scanner, pseudo);
            case ("3") -> menuGererTacheActivite.gererMesTaches(scanner, pseudo);
            case ("4") -> menuGererTacheActivite.gererMesActivites(scanner, pseudo);
            case ("5") -> menuReseau.gererReseauSocial(scanner, pseudo);
            case ("6") -> {
                menuAchat(scanner, pseudo);
                //System.out.println("Ce menu est indisponible pour le moment ): \nVeuillez reessayer plus tard.");
                //System.out.println(" ");
                menuUtilisateur(scanner, pseudo);
            }
            case ("7") -> {
                //menuNotification(scanner, pseudo);
                System.out.println("Ce menu est indisponible pour le moment ): \nVeuillez reessayer plus tard.");
                System.out.println(" ");
                menuUtilisateur(scanner, pseudo);
            }
            case ("8") -> menuRequetesPubliques(scanner, pseudo);
            case ("9") -> {
                System.out.println("Voici la liste d'interets : ");
                ArrayList<String> interets = new ArrayList<>();
                interets.add("Combat");
                interets.add("Foot");
                interets.add("Soccer");
                interets.add("Danse");
                interets.add("Break");
                /*dbControlleur.recupererListeInteret()*/
                for (String interet : interets) {
                    System.out.println(interet);
                }
                System.out.println("A quel interet voulez-vous vous souscrire");
                String decision = scanner.nextLine();
                /*controlleurUtilisateurs.souscrireAunInteret(decision)*/
                if (interets.contains(decision.trim())) {
                    System.out.println("Vous etes souscrit a : " + decision);
                } else {
                    System.out.println("Veuillez verifier le nom de l'interet");
                }
                menuUtilisateur(scanner, pseudo);
            }
            case ("10") -> {
                menu = new Menu();
                menu.menuPrincipale(scanner);
            }
        }
    }

    public void menuAchat(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("Que voulez-vous acheter?");
        System.out.println("1- Robot");
        System.out.println("2- Composante");
        String decision = scanner.nextLine();
        switch(decision){
            case "1" -> {
                System.out.println("Voici la liste des fournisseurs: ");
                System.out.println(dbControlleur.recupererListFournisseur());
                System.out.println("Choisissez un fournisseur");
                String nomFournisseur = scanner.nextLine();
                System.out.println(dbControlleur.obtenirListRobotFournisseur(nomFournisseur));
                System.out.println("Entrez le numero du robot a acheter");
                int numero = Integer.parseInt(scanner.nextLine());
                System.out.println("Voici le numero de serie");
                System.out.println(dbControlleur.acheterRobot(nomFournisseur, numero));
                menuUtilisateur(scanner, pseudo);
            }
            case "2" -> {
                System.out.println("Voici la liste des fournisseurs: ");
                System.out.println(dbControlleur.recupererListFournisseur());
                System.out.println("Choisissez un fournisseur");
                String nomFournisseur = scanner.nextLine();
                String res = dbControlleur.rechercherComposantParNomFournisseur(nomFournisseur);
                if (res.equals("Aucune composante trouvée...")){
                    System.out.println(res);
                } else {
                    System.out.println(res);
                    System.out.println("Entrez le numero de la composante à acheter");
                    String numero = scanner.nextLine();
                    System.out.println("Achat confirmé");
                }

                menuUtilisateur(scanner, pseudo);
            }
        }
    }

    public void menuRequetesPubliques(Scanner scanner,String pseudo) throws ParseException, IOException {
        System.out.println("Veuillez faire une requete publique : ");
        System.out.println("1- Voir la liste d'utilisateurs");
        System.out.println("2- Voir la liste des fournisseurs");
        System.out.println("3- Voir mon profil");
        System.out.println("4- Chercher utilisateur par: "); //TODO
        System.out.println("5- Recuperer la liste des activites");
        System.out.println("6- Recuperer la liste des interets");
        System.out.println("7- Rechercher fournisseur par nom"); //TODO
        System.out.println("8- Rechercher une composante par nom"); //TODO
        String choix = scanner.nextLine();
        switch (choix){
            case "1" :
                System.out.println(dbControlleur.recupererListeUtilisateur());
                menuUtilisateur(scanner, pseudo);
                break;

            case "2" :
                System.out.println(dbControlleur.recupererListFournisseur());
                menuUtilisateur(scanner, pseudo);
                break;

            case "3" :
                controlleurUtilisateurs.voirProfilUtilisateurCourant();
                menuUtilisateur(scanner, pseudo);
                break;

            case "4" :
                menuChercherUtilisateur(scanner, pseudo);
                /*System.out.println("Ce menu est indisponible pour le moment ): \nVeuillez reessayer plus tard.");
                System.out.println(" ");*/
                menuUtilisateur(scanner, pseudo);

            case "5" :
                System.out.println(dbControlleur.recupererListeActivite());
                menuUtilisateur(scanner, pseudo);
                break;

            case "6" :
                menuRechercheInterets(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
                break;

            case "7" :
                menuChercherFournisseur(scanner, pseudo);
                /*System.out.println("Ce menu est indisponible pour le moment ): \nVeuillez reessayer plus tard.");
                System.out.println(" ");*/
                menuUtilisateur(scanner, pseudo);

            case "8" :
                menuRechercheComposante(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
                break;

        }
    }

    public void menuRechercheComposante(Scanner scanner, String nomFournisseur){
        System.out.println("Filtrer par: ");
        System.out.println("1- Type de la composante");
        System.out.println("2- Nom de fournisseur");
        System.out.println("3- Nom de la composante");
        String decision = scanner.nextLine();
        switch (decision){
            case "1" :
                System.out.println("Entrez le type : ");
                String nom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParType(nom));
                break;

            case "2" :
                System.out.println("Entrez le nom de fournisseur : ");
                String nomFour = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParNomFournisseur(nomFour));
                break;

            case "3" :
                System.out.println("Entrez le nom de la composante : ");
                String nomComposante = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParNom(nomComposante));
                break;
        }
    }

    public void menuRechercheInterets(Scanner scanner, String nomFournisseur) {
        System.out.println("Voulez vous appliquer un filtre? (oui/non)");
        String decision = scanner.nextLine();
        switch (decision.toLowerCase().trim()) {
            case "oui":
                System.out.println("Par quel filtre voulez vous filtrer?");
                System.out.println("1- Filtrer par trois premieres lettres");
                System.out.println("2- Filtrer par pseudo utilisateur");
                System.out.println("3- Filtrer par pseudo utilisateur et trois premieres lettres d'interets");
                String decisionFiltre = scanner.nextLine();
                switch (decisionFiltre.toUpperCase()) {
                    case "1":
                        System.out.println("Entrez vos 3 characteres");
                        String troislettre = scanner.nextLine();
                        System.out.println(dbControlleur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre));
                        break;

                    case "2":
                        System.out.println("Entrez le pseudo de l'utilisateur");
                        String pseudo = scanner.nextLine();
                        System.out.println(dbControlleur.recupererListeInteretUtilisateur(pseudo));
                        break;


                    case "3":
                        System.out.println("Entrez le pseudo de l'utilisateur: ");
                        String pseudo1 = scanner.nextLine();
                        System.out.println("Entrez les 3 characteres de l'interet");
                        String troislettre1 = scanner.nextLine();
                        System.out.println(dbControlleur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(pseudo1, troislettre1));
                        break;
                    }
                    break;

                case "non":
                    System.out.println(dbControlleur.recupererListeInteret());
                    break;

        }
    }

    public void menuChercherFournisseur(Scanner scanner, String nomFournisseur){
        System.out.println("Filtrer par:");
        System.out.println("1- Nom");
        System.out.println("2- Email");
        System.out.println("3- Adresse");
        System.out.println("4- Type de composantes");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" :
                System.out.println("Entrez le nom du fournisseur : ");
                String nom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParNom(nom));
                break;

            case "2" :
                System.out.println("Entrez l'email du fournisseur : ");
                String email = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParEmail(email));
                break;

            case "3" :
                System.out.println("Entrez l'adresse du fournisseur : ");
                String adresse = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParAdresse(adresse));
                break;

            case "4" :
                System.out.println("Entrez le type de composantes : ");
                String type = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParTypeDeComposant(type));
                break;

        }
    }

    public void menuChercherUtilisateur(Scanner scanner, String nomFournisseur){
        System.out.println("Filtrer par:");
        System.out.println("1- Pseudo");
        System.out.println("2- Nom");
        System.out.println("3- Prenom");
        System.out.println("4- Obtenir liste des suiveurs de? (pseudo voulu)");
        String decision = scanner.nextLine();
        switch (decision){
            case "1" :
                System.out.println("Entrez le pseudo");
                String decisionPseudo = scanner.nextLine();
                System.out.println((dbControlleur.rechercherUtilisateurParPseudo(decisionPseudo)));
                break;


            case "2" :
                System.out.println("Entrez le nom");
                String decisionNom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParNom(decisionNom));
                break;


            case "3" :
                System.out.println("Entrez le prenom");
                String decisionPrenom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParPrenom(decisionPrenom));
                break;


            case "4" :
                System.out.println("Entrez le pseudo");
                String pseudoUtilisateur = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParSuiveur(pseudoUtilisateur));
                break;
        }
    }

    public void modifierProfile(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("Que voulez-vous modifier");
        System.out.println("1- Nom");
        System.out.println("2- Prenom");
        System.out.println("3- Adresse");
        System.out.println("4- Pseudo");
        System.out.println("5- Email");
        System.out.println("6- Numero de telephone");
        System.out.println("7- Nom de la compagnie");
        System.out.println("8- Mot de passe");
        System.out.println("9- Revenir au menu utilisateur");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> {
                System.out.println("Entrez votre nouveau nom: ");
                String nom = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("nom", nom);
            }
            case "2" -> {
                System.out.println("Entrez votre nouveau prenom: ");
                String prenom = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("prenom", prenom);
            }
            case "3" -> {
                System.out.println("Entrez votre nouvelle adresse: ");
                String adresse = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("adresse", adresse);
            }
            case "4" -> {
                System.out.println("Entrez votre nouveau pseudo: ");
                String newPseudo = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("pseudo", newPseudo);
            }
            case "5" -> {
                System.out.println("Entrez votre nouveau email : ");
                String email = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("email", email);
            }
            case "6" -> {
                System.out.println("Entrez votre nouveau numero de telephone : ");
                String numTele = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("numerotelephone", numTele);
            }
            case "7" -> {
                System.out.println("Entrez votre nouvelle compagnie : ");
                String compagnie = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("nomcompagnie", compagnie);
            }
            case "8" -> {
                System.out.println("Entrez votre nouveau mot de passe : ");
                String mdp = scanner.nextLine();
                controlleurUtilisateurs.modifierProfile("mdp", mdp);
            }
            case "9" -> menuUtilisateur(scanner, pseudo);
        }
    }

    public void menuNotification(Scanner scanner, String pseudo) throws ParseException, IOException {
        for (Notification notif : controlleurUtilisateurs.voirNotifications(pseudo)) {
            System.out.println("- " + notif);
        }
        System.out.println("Voulez-vous supprimer les notifs (Y/N)?");
        String decision = scanner.nextLine();
        switch (decision.toUpperCase()){
            case "Y":
                controlleurUtilisateurs.supprimerNotifs(pseudo);
            case "N": 
                menuUtilisateur(scanner, pseudo);
                break;
        }
    }
}