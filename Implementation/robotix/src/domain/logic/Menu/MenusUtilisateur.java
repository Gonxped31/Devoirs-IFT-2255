package domain.logic.Menu;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Notification;

import domain.logic.Controller.ControlleurUtilisateurs;

public class MenusUtilisateur {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = new DbControleur();
    // private Menu menu = new Menu();
    private MenuGestionFlotte menuGestionFlotte = new MenuGestionFlotte();
    private MenuGererTacheActivite menuGererTacheActivite = new MenuGererTacheActivite();
    private MenuGestionReseau menuReseau = new MenuGestionReseau();

    public MenusUtilisateur() throws IOException {

    }

    public void inscrireUtilisateur(Scanner scanner) throws ParseException {
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
            if (PseudoUnique) {
                System.out.print("Ce pseudo existe déjà, veuillez entrer un autre : ");
            } else {
                break;
            }
        }

        System.out.println("Mot de passe: ");
        String mdp = scanner.nextLine();

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
                System.out
                        .println("Le numéro de téléphone doit obtenir exactement 10 caractères. Veuillez réessayez: ");
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
        }
        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo, mdp, courriel, telephone,
                nomCompagnie, listeInteret);
        System.out.println("Have fun " + pseudo + " !");
        // menu.menuPrincipale(scanner);
    }

    public void connecterUtilisateur(Scanner scanner) throws IOException, ParseException {
        System.out.println("Veuillez entrer votre pseudo: ");
        String connexion = scanner.nextLine();
        System.out.println("Veuillez entrer votre mot de passe: ");
        String mdp = scanner.nextLine();
        if (controlleurUtilisateurs.authentification(connexion, mdp, "Utilisateur")) {
            System.out.println("Bienvenue " + connexion + "!");
            menuUtilisateur(scanner, connexion);
        } else {
            System.out.println(connexion + " n'existe pas.");
            // menu.menuPrincipale(scanner);
        }
    }

    public void menuUtilisateur(Scanner scanner, String pseudo) throws IOException, ParseException {
        ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
        ArrayList<String> fournisCPU = new ArrayList<>();
        fournisCPU.add("Fournisseur6");
        fournisCPU.add("Adresse1");
        fournisCPU.add("Courriel3");
        fournisCPU.add("Numero4");
        fournisCPU.add("type3");
        fournisCPU.add("compagnie2");
        System.out.println("******************** Menu: " + pseudo + " ********************");
        ArrayList<Notification> notifications = controlleurUtilisateurs.notifier();
        if (notifications.size() > 0) {
            System.out.println(">>> Vous avez " + notifications.size() + "notifications <<<");
        }
        System.out.println("Bienvenue! Veuillez choisir une option:");
        System.out.println("1- Modifier mon profile");
        System.out.println("2- Gérer ma flotte");
        System.out.println("3- Gérer mes taches");
        System.out.println("4- Gérer mes activites");
        System.out.println("5- Gerer mon reseau social");
        System.out.println("6- Achats (Robots ou composantes)");
        System.out.println("7- Voir mes notifications");
        System.out.println("8- Faire une requete publique");
        System.out.println("9- Revenir au menu principale");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case ("1"):
                modifierProfile(scanner, pseudo);
            case ("2"):
                menuGestionFlotte.gererMaFlotte(scanner, pseudo);
            case ("3"):
                menuGererTacheActivite.gererMesTaches(scanner, pseudo);
            case ("4"):
                menuGererTacheActivite.gererMesActivites(scanner, pseudo);
            case ("5"):
                menuReseau.gererReseauSocial(scanner, pseudo);
            case ("6"):
                menuAchat(scanner, pseudo);
            case ("7"):
                menuNotifications(notifications);
            case ("8"):
                menuRequetesPubliques(scanner, pseudo);
            case ("9"):
                // menu.menuPrincipale(scanner);
        }
    }

    public void menuAchat(Scanner scanner, String pseudo) {
        System.out.println("Voulez-vous acheter un robot ou une composante?");
        System.out.println("1- Robot");
        System.out.println("2- Composante");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" -> {

            }
            case "2" -> {
                System.out.print("Choisissez un fournisseur : ");
                System.out.println(dbControlleur.recupererListFournisseur());
                System.out.print("Votre choix: ");
                String nomFour = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParNomFournisseur(nomFour));
                System.out.println("Quelles composantes voulez-vous acheter?");
                String choix = scanner.nextLine();
                controlleurUtilisateurs.ajouterComposantesAInventaire(nomFour, choix);
            }
        }
    }

    public void menuRequetesPubliques(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("Veuillez faire une requete publique : ");
        System.out.println("1- Voir la liste d'utilisateurs");
        System.out.println("2- Voir la liste des fournisseurs");
        System.out.println("3- Voir mon profil");
        System.out.println("4- Chercher utilisateur par: ");// TODO
        System.out.println("5- Recuperer la liste des activites");
        System.out.println("6- Recuperer la liste des interets");
        System.out.println("7- Rechercher fournisseur par nom");// TODO
        System.out.println("8- Rechercher une composante par nom");// TODO
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> {
                System.out.println(dbControlleur.recupererListeUtilisateur());
                menuUtilisateur(scanner, pseudo);
            }
            case "2" -> {
                System.out.println(dbControlleur.recupererListFournisseur());
                menuUtilisateur(scanner, pseudo);
            }
            case "3" -> {
                controlleurUtilisateurs.voirProfilUtilisateurCourant();
                menuUtilisateur(scanner, pseudo);
            }
            case "4" -> {
                menuChercherUtilisateur(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
            }
            case "5" -> {
                System.out.println(dbControlleur.recupererListeActivite());
                menuUtilisateur(scanner, pseudo);
            }
            case "6" -> {
                menuRechercheInterets(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
            }
            case "7" -> {
                menuChercherFournisseur(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
            }
            case "8" -> {
                menuRechercheComposante(scanner, pseudo);
                menuUtilisateur(scanner, pseudo);
            }
        }
    }

    public void menuRechercheComposante(Scanner scanner, String nomFournisseur) {
        System.out.println("Filtrer par: ");
        System.out.println("1- Type de la composante");
        System.out.println("2- Nom de fournisseur");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" -> {
                System.out.println("Entrez le type : ");
                String nom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParType(nom));
            }
            case "2" -> {
                System.out.println("Entrez le nom de fournisseur : ");
                String nomFour = scanner.nextLine();
                System.out.println(dbControlleur.rechercherComposantParNomFournisseur(nomFour));
            }
        }
    }

    public void menuRechercheInterets(Scanner scanner, String nomFournisseur) {
        System.out.println("Voulez vous appliquer un filtre?");
        System.out.println("1- Oui");
        System.out.println("2- Non");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" -> {
                System.out.println("Par quel filtre voulez vous filtrer?");
                System.out.println("1- Filtrer par trois premieres lettres");
                System.out.println("2- Filtrer par pseudo utilisateur");
                System.out.println("3- Filtrer par pseudo utilisateur et trois premieres lettres d'interets");
                String decisionFiltre = scanner.nextLine();
                switch (decisionFiltre.toUpperCase()) {
                    case "1" -> {
                        System.out.println("Entrez vos 3 characteres");
                        String troislettre = scanner.nextLine();
                        System.out.println(
                                dbControlleur.recupererListeInteretParFiltrageSurTroisPremierSousChaine(troislettre));
                    }
                    case "2" -> {
                        System.out.println("Entrez le pseudo de l'utilisateur");
                        String pseudo = scanner.nextLine();
                        System.out.println(dbControlleur.recupererListeInteretUtilisateur(pseudo));

                    }
                    case "3" -> {
                        System.out.println("Entrez le pseudo de l'utilisateur: ");
                        String pseudo = scanner.nextLine();
                        System.out.println("Entrez les 3 characteres de l'interet");
                        String troislettre = scanner.nextLine();
                        System.out.println(
                                dbControlleur.recupererListeInteretUtilisateurParFiltrageSurTroisPremierSousChaine(
                                        pseudo, troislettre));
                    }
                }
            }
            case "2" -> {
                System.out.println(dbControlleur.recupererListeInteret());
            }
        }
    }

    public void menuChercherFournisseur(Scanner scanner, String nomFournisseur) {
        System.out.println("Filtrer par:");
        System.out.println("1- Nom");
        System.out.println("2- Email");
        System.out.println("3- Adresse");
        System.out.println("4- Type de composantes");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" -> {
                System.out.println("Entrez le nom du fournisseur : ");
                String nom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParNom(nom));
            }
            case "2" -> {
                System.out.println("Entrez l'email du fournisseur : ");
                String email = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParEmail(email));
            }
            case "3" -> {
                System.out.println("Entrez l'adresse du fournisseur : ");
                String adresse = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParAdresse(adresse));
            }
            case "4" -> {
                System.out.println("Entrez le type de composantes : ");
                String type = scanner.nextLine();
                System.out.println(dbControlleur.rechercherFournisseurParTypeDeComposant(type));
            }
        }
    }

    public void menuChercherUtilisateur(Scanner scanner, String nomFournisseur) {
        System.out.println("Filtrer par:");
        System.out.println("1- Pseudo");
        System.out.println("2- Nom");
        System.out.println("3- Prenom");
        System.out.println("4- Obtenir liste des suiveurs de? (pseudo voulu)");
        String decision = scanner.nextLine();
        switch (decision) {
            case "1" -> {
                System.out.println("Entrez le pseudo");
                String decisionPseudo = scanner.nextLine();
                System.out.println((dbControlleur.rechercherUtilisateurParPseudo(decisionPseudo)));

            }
            case "2" -> {
                System.out.println("Entrez le nom");
                String decisionNom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParNom(decisionNom));

            }
            case "3" -> {
                System.out.println("Entrez le prenom");
                String decisionPrenom = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParPrenom(decisionPrenom));

            }
            case "4" -> {
                System.out.println("Entrez le pseudo");
                String pseudoUtilisateur = scanner.nextLine();
                System.out.println(dbControlleur.rechercherUtilisateurParSuiveur(pseudoUtilisateur));

            }
        }
    }

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
        switch (choix) {
            case "1" -> {
                System.out.println("Entrez votre nouveau nom: ");
                String nom = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "nom", nom);
            }
            case "2" -> {
                System.out.println("Entrez votre nouveau prenom: ");
                String prenom = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "prenom", prenom);
                System.out.println("Modification terminée avec succès !");
            }
            case "3" -> {
                System.out.println("Entrez votre nouvelle adresse: ");
                String adresse = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "adresse", adresse);
                System.out.println("Modification terminée avec succès !");
            }
            case "4" -> {
                System.out.println("Entrez votre nouveau pseudo: ");
                String newPseudo = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "pseudo", newPseudo);
                System.out.println("Modification terminée avec succès !");
            }
            case "5" -> {
                System.out.println("Entrez votre nouveau email : ");
                String email = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "email", email);
                System.out.println("Modification terminée avec succès !");
            }
            case "6" -> {
                System.out.println("Entrez votre nouveau numero de telephone : ");
                String numTele = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "numerotelephone", numTele);
                System.out.println("Modification terminée avec succès !");
            }
            case "7" -> {
                System.out.println("Entrez votre nouvelle compagnie : ");
                String compagnie = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "nomcompagnie", compagnie);
                System.out.println("Modification terminée avec succès !");
            }
            case "8" -> {
                System.out.println("Entrez votre nouveau mot de passe : ");
                String mdp = scanner.nextLine();
                System.out.println("Modification en cours...");
                controlleurUtilisateurs.modifierProfile(pseudo, "mdp", mdp);
                System.out.println("Modification terminée avec succès !");
            }
        }
    }

    public void menuNotifications(ArrayList<Notification> notifications) {
        System.out.println(notifications + "\n");
    }

    public void menuNotification(Scanner scanner, String pseudo) throws ParseException, IOException {
        for (Notification notif : controlleurUtilisateurs.voirNotifications()) {
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