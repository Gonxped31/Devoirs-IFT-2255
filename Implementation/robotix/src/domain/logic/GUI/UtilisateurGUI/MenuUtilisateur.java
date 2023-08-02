package domain.logic.GUI.UtilisateurGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import java.awt.event.ActionEvent;


import javax.swing.*;


import domain.logic.GUI.Menu;
import domain.logic.GUI.UtilisateurGUI.AchatsGUI;
import domain.logic.GUI.UtilisateurGUI.GestionActivitesGUI;
import domain.logic.GUI.UtilisateurGUI.GestionFlotteGUI;
import domain.logic.GUI.UtilisateurGUI.GestionNotifsGUI;
import domain.logic.GUI.UtilisateurGUI.GestionReseauGUI;
import domain.logic.GUI.UtilisateurGUI.GestionTachesGUI;
import domain.logic.GUI.UtilisateurGUI.ModifierProfilUtilisateurGUI;
import domain.logic.GUI.UtilisateurGUI.RequetePubliqueUtilisateurGUI;
import domain.logic.GUI.UtilisateurGUI.SouscrireInteretGUI;



public class MenuUtilisateur extends JFrame {
        /* Section Utilisateur */
        private String pseudo;
        private JFrame jFrame = new JFrame();
        private JPanel menuUtilisateurPanel = new JPanel();
        private JLabel menuUtilisateurLabel = new JLabel("Menu Utilisateur", SwingConstants.CENTER);
        private JButton btnModifierProfil = new JButton("Modifier mon profil");
        private JButton btnGererFlotte = new JButton("Gerer ma flotte");
        private JButton btnGererTaches = new JButton("Gerer mes taches");
        private JButton btnGererActivites = new JButton("Gerer mes activites");
        private JButton btnGererReseauSocial = new JButton("Gerer mon reseau social");
        private JButton btnAchats = new JButton("Achats");
        private JButton btnVoirNotifications = new JButton("Voir mes notifications");
        private JButton btnRequetePublique = new JButton("Faire une requete publique");
        private JButton btnSouscrireInteret = new JButton("Souscrire a un interet");
        private JButton btnDeconnexion = new JButton("Deconnexion");
        private ModifierProfilUtilisateurGUI modifierProfilUtilisateurGUI;
        private GestionFlotteGUI gestionFlotteGUI;
        private GestionTachesGUI gestionTachesGUI;
        private GestionActivitesGUI gestionActivitesGUI;
        private GestionReseauGUI gestionReseauGUI = new GestionReseauGUI();
        private AchatsGUI achatsGUI;
        private GestionNotifsGUI gestionNotifsGUI = new GestionNotifsGUI();
        private RequetePubliqueUtilisateurGUI requetePubliqueUtilisateurGUI;
        private SouscrireInteretGUI souscrireInteretGUI = new SouscrireInteretGUI();

        public MenuUtilisateur() throws IOException, ParseException {

        }

        public MenuUtilisateur(String pseudo) throws IOException, ParseException {
            this.pseudo = pseudo;
            instancierMenus(pseudo);

            modifierProfilUtilisateurGUI = new ModifierProfilUtilisateurGUI(pseudo);
            menuUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
            menuUtilisateurPanel.setLayout(new GridLayout(0, 1));
            menuUtilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Ajout des composantes
            menuUtilisateurPanel.add(menuUtilisateurLabel);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnModifierProfil);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererFlotte);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererTaches);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererActivites);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnGererReseauSocial);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnAchats);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnVoirNotifications);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnRequetePublique);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnSouscrireInteret);
            menuUtilisateurPanel.add(Box.createHorizontalStrut(10));
            menuUtilisateurPanel.add(btnDeconnexion);

            btnModifierProfil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modifierProfilUtilisateurGUI.afficherMainPanel(jFrame);
                }
            }); //Connexion faite
            btnGererFlotte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionFlotteGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererTaches.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionTachesGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererActivites.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionActivitesGUI.afficherMainPanel(jFrame);
                }
            });
            btnGererReseauSocial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionReseauGUI.afficherMainPanel(jFrame);
                }
            });
            btnAchats.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    achatsGUI.afficherMainPanel(jFrame);
                }
            }); //Connexion etablie
            btnVoirNotifications.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionNotifsGUI.afficherMainPanel(jFrame);
                }
            });
            btnRequetePublique.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    requetePubliqueUtilisateurGUI.afficherMainPanel(jFrame);
                }
            });
            btnSouscrireInteret.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    souscrireInteretGUI.afficherMainPanel(jFrame);
                }
            });
            btnDeconnexion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.dispose();
                    try {
                        domain.logic.GUI.Menu menu = new Menu();
                    } catch (IOException | ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

        }

        private void instancierMenus(String pseudo) throws IOException, ParseException {
            gestionFlotteGUI = new GestionFlotteGUI(pseudo);
            achatsGUI = new AchatsGUI(pseudo);
            gestionTachesGUI = new GestionTachesGUI(pseudo);
            gestionActivitesGUI = new GestionActivitesGUI(pseudo);
            requetePubliqueUtilisateurGUI = new RequetePubliqueUtilisateurGUI(pseudo);
        }

        public void afficherMenuUtilisateurPanel(JFrame jFrame) {
            this.jFrame = jFrame;
            this.jFrame.setContentPane(menuUtilisateurPanel);
            this.jFrame.revalidate();
            this.jFrame.repaint();
        }

    /*public void inscrireUtilisateur(Scanner scanner) throws ParseException, IOException {
        controlleurUtilisateurs = new ControlleurUtilisateurs();
        boolean PseudoExiste = false;
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

        System.out.print("Pseudo: ");
        do {
            pseudo = scanner.nextLine();
            PseudoExiste = controlleurUtilisateurs.verifierPseudo(pseudo);
            if (PseudoExiste) {
                System.out.print("Ce pseudo existe déjà, veuillez entrer un autre : ");
            }
        } while (PseudoExiste);

        System.out.print("Mot de passe: ");
        String mdp = scanner.nextLine();

        while (!EmailValide) {
            System.out.print("Adresse courriel: ");
            courriel = scanner.nextLine();
            try {
                EmailValide = Verifications.verifierEmail(courriel);
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
                TelephoneValide = Verifications.verifierTelephone(telephone);
            } catch (NullPointerException e){
                TelephoneValide = true;
            }
            if (!TelephoneValide) {
                System.out.println("Le numéro de téléphone est invalide. Veuillez réessayez.");
            }
        }

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Nom de la compagnie : ");
        String nomCompagnie = scanner.nextLine();
        System.out.print("Vous devez ajouter des interets (au plus 10 interets)");
        for (int i = 9; i >= 0; i--) {
            System.out.print("Veuillez entrer un interet: ");
            String interet = scanner.nextLine();
            listeInteret.add(interet);
            System.out.println("Vous pouvez encore choisir " + i + " interets. Voulez-vous en ajouter encore? (Y/N)");
            String decis = scanner.nextLine();
            if (decis.toUpperCase().equals("N")){
                break;
            }
        }
        this.controlleurUtilisateurs = ControlleurUtilisateurs.getControlleurUtilisateurs(nom, prenom, adresse, pseudo,
                mdp, courriel, telephone, nomCompagnie, listeInteret);

        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, listeInteret);
        System.out.println("Envoie de l'email de confirmation en cours. Veuillez patienter...");
        String body = "Cher " + pseudo + ",\n\nNous vous remercions de vous être abonné à Robotix !\nProfitez des fonctionalitées multiples" +
                " que vous propose cette application en tant qu'utilisateur ! Si vous désirez toutefois vendre aussi des robots, vous pouvez" +
                " toujours vous inscrire en tant que fournisseur. \n\nCordialement,\nL'équipe Robotix";

        EmailSender emailSender = new EmailSender("robotrobotix4@gmail.com","lkzojmozphkprruj", courriel,
                "Confirmation d'inscription", body);

        emailSender.sendInBackground();


        System.out.println("Have fun " + pseudo + " !");
        menu = new Menu();
        menu.menuPrincipale(scanner);
    }

    public void connecterUtilisateur(Scanner scanner) throws ParseException, IOException {

        controlleurUtilisateurs = new ControlleurUtilisateurs();
        for (int i = 0; i < 3; i++) {
            System.out.print("Veuillez entrer votre pseudo: ");
            String connexion = scanner.nextLine();
            System.out.print("Veuillez entrer votre mot de passe: ");
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
                menuUtilisateur(scanner, pseudo);
            }
            case ("7") -> {
                menuNotification(scanner, pseudo);
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
                dbControlleur.recupererListeInteret();
                for (String interet : interets) {
                    System.out.println(interet);
                }
                System.out.println("A quel interet voulez-vous vous souscrire");
                String decision = scanner.nextLine();
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
                String numeroSerie = dbControlleur.acheterRobot(nomFournisseur, numero).toString();
                controlleurUtilisateurs.ajouterRobot(pseudo, numeroSerie);
                System.out.println(numeroSerie);

                //Ajouter nom fournisseur
                controlleurFournisseurs.ajouterNotifs(nomFournisseur, "Achat de robot",pseudo +" a achete "
                            + dbControlleur.retournerRobot(numeroSerie).getNom(), TypeNotification.ACHAT_ROBOT);
                System.out.println(dbControlleur.acheterRobot(nomFournisseur, numero));
                System.out.println(">>> VEUILLEZ LE CONSERVER TRÈS PRÉCIEUSEMENT PUISQU'IL EST INDISPENSABLE POUR EFFECTUER " +
                        "TOUT CHANGEMENT  OU TOUTE CONSULTATION PAR RAPPORT A VOS ROBOT ! <<<");
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
                    controlleurFournisseurs.ajouterNotifs(nomFournisseur, "Achat de composant",pseudo +" a achete une composante", TypeNotification.ACHAT_ROBOT);
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
    }*/
}