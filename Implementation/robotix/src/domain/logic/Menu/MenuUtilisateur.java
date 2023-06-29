package domain.logic.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import domain.logic.Menu.Menu;

import domain.logic.Controller.ControlleurUtilisateurs;

public class MenuUtilisateur {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private Menu menu;
    private MenuGestionFlotte menuGestionFlotte;
    private MenuGererTacheActivite menuGererTacheActivite;
    private MenuGestionReseau menuReseau;
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
        menu.menuPrincipale(scanner);
    }
    public void connecterUtilisateur(Scanner scanner) {
        System.out.println("Veuillez entrer votre pseudo: ");
        String connexion = scanner.nextLine();
        if (controlleurUtilisateurs.authentification(connexion, "Utilisateur")) {
            System.out.println("Bienvenue " + connexion + "!");
            menuUtilisateur(scanner, connexion);
        } else {
            System.out.println(connexion + " n'existe pas.");
            menu.menuPrincipale(scanner);
        }
    }
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
                menuGestionFlotte.gererMaFlotte(scanner, pseudo);
            case("3") :
                menuGererTacheActivite.gererMesTaches(scanner, pseudo);
            case("4") :
                menuGererTacheActivite.gererMesActivites(scanner, pseudo);
            case("5") :
                menuReseau.gererReseauSocial(scanner, pseudo);
            case("6") :

            case("7") :
                menuNotification(scanner, pseudo);
            case("8") :
                menu.menuPrincipale(scanner);
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
    public void menuNotification(Scanner scanner, String pseudo){
        for (String notif : controlleurUtilisateurs.voirNotifications(pseudo)) {
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