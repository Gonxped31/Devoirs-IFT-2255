package domain.logic.Menu;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.Fournisseur;

import java.util.Scanner;

public class MenuFournisseur {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private Menu menu;

    public void inscrireFournisseur(Scanner scanner) {
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

        menu.menuPrincipale(scanner);
    }

    public void connecterFournisseur(Scanner scanner) {
        System.out.println("Veuillez entrez votre nom de fournisseur: ");
        String nomFounisseur = scanner.nextLine();
        if (controlleurFournisseurs.authentificationFournisseur(nomFounisseur, "Fournisseur")) {
            System.out.println("Bienvenue " + nomFounisseur + "!");
            menuFournisseur(scanner, controlleurFournisseurs.trouverFournisseur("1", nomFounisseur).get(0));
        } else {
            System.out.println(nomFounisseur + " n'existe pas.");
            menu.menuPrincipale(scanner);
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
                String types = scanner.nextLine();
                controlleurFournisseurs.ajouterComposante(composante, Double.parseDouble(prix), description, types, fournisseur);
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
                menu.menuPrincipale(scanner);
        }
    }

    public void modifierProfileFournisseur() {

    }

    public void gererComposantes() {

    }

    public void eneregistrerComposante() {

    }

    public void menuNotifications() {

    }

    public void voirProfileFournisseur() {

    }

}
