package domain.logic.Menu;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.TypeNotification;

public class MenuGestionReseau {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = new DbControleur();
    private MenuUtilisateur menuUtil;

    public MenuGestionReseau() throws IOException {
    }

    public void gererReseauSocial(Scanner scanner, String pseudo) throws ParseException, IOException {
        System.out.println("********** Gestion réseau **********");
        System.out.println("1- Suivre un utilisateur");
        System.out.println("2- Gerer mes suiveurs");
        System.out.println("3- Gerer mes interets");
        System.out.println("4- Revenir au menu utilisateur");
        System.out.print(">>> Votre choix : ");
        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> {
                System.out.println("Quel est le pseudo de l'utilisateur que vous voulez suivre");
                String nom = scanner.nextLine();
                if (controlleurUtilisateurs.suivreUtilisateur(pseudo, nom) == false) {
                    System.out.println("L'utilisateur que vous voulez suivre n'existe pas");
                    gererReseauSocial(scanner, pseudo);
                }
                if (controlleurUtilisateurs.etreSuivi(pseudo, nom) == false){
                    System.out.println("L'utilisateur que vous voulez suivre n'existe pas");
                    gererReseauSocial(scanner, pseudo);
                }

                controlleurUtilisateurs.ajouterNotifs(nom, "Nouvel abonne",pseudo + " vous a suivi",  TypeNotification.NOUVEAU_ABONNE);
                if (!nom.equals(""))
                    System.out.println("Vous suivez maintenant " + nom);
                else {
                    System.out.println("Cet utilisateur n'a pas pu etre ajoute a votre reseau");
                }
                gererReseauSocial(scanner, pseudo);
            } case "2" -> {
                System.out.println("Que voulez-vous faire?");
                System.out.println("1- Voir a qui je suis abonne");
                System.out.println("2- Supprimer un utilisateur de ma liste d'abonne");
                String decision = scanner.nextLine();
                switch (decision) {
                    case "1" -> {
                        System.out.println(controlleurUtilisateurs.voirListeUtilisateur(pseudo));
                        gererReseauSocial(scanner, pseudo);
                    }
                    case "2" -> {
                        System.out.println("Quel utilisateur voulez vous supprimer de votre liste");
                        String nom = scanner.nextLine();
                        controlleurUtilisateurs.suppriemrSuivreUtilisateur(pseudo, nom);
                        gererReseauSocial(scanner, pseudo);
                    }
                }
                System.out.println("Cette fonctionalitée n'est pas encore disponible );");
                System.out.println("Veuillez rééssayer plus tard.");
                gererReseauSocial(scanner, pseudo);
            }
            //controlleurUtilisateurs.gererSuiveurs(pseudo);
            case "3" -> {
                menuGestionInterets(pseudo, scanner);
                System.out.println("Cette fonctionalitée n'est pas encore disponible );");
                System.out.println("Veuillez rééssayer plus tard.");
                gererReseauSocial(scanner, pseudo);
            }
            //controlleurUtilisateurs.gererInteret(pseudo);
            case "4" -> {
                menuUtil = new MenuUtilisateur();
                menuUtil.menuUtilisateur(scanner, pseudo);
            }
        }
    }

    public void menuGestionInterets(String pseudo, Scanner scanner){
        System.out.println("Veuillez choisir une option: ");
        System.out.println("1- Ajouter un interets");
        System.out.println("2- Modifier un interet");
        System.out.println("3- Supprimer un interet");
        System.out.println("4- S'abonner a un interet");
        System.out.println("5- Se desabonner d'un interet");
        String decision = scanner.nextLine();
        switch (decision){
            case ("1") -> {
                System.out.println("Ajouter interet dans le systeme Robotix");
                String interet = scanner.nextLine();
                if (dbControlleur.souscrireAunInteret(interet) == null) {
                    dbControlleur.ajouterInteret(interet);
                } else {
                    System.out.println("Cette interet interet existe deja dans le systeme");
                }
            }
            case ("2") -> {
                System.out.println("Veuillez choisir l'interet a modifier");
                System.out.println(dbControlleur.recupererListeInteret());

            }
            case ("3") -> {
                System.out.println("Supprimer un interet");
                String interet = scanner.nextLine();
                if (controlleurUtilisateurs.extraireInteretsUtilisateurs(interet)){
                    dbControlleur.supprimerInteret(interet);
                    System.out.println("Supprimer avec succes");
                }else{
                    System.out.println("Un utilisateur possede cet interet vous ne pouvez pas le supprimer");
                }
            }
            case ("4") -> {
                System.out.println("Veuillez choisir l'interet auquel vous voulez vous abonner");
                System.out.println(dbControlleur.recupererListeInteret());
                System.out.println(">>> Votre choix : ");
                String interet = scanner.nextLine();
                controlleurUtilisateurs.abonnerInteret(interet, pseudo);
            }
            case ("5") -> {
                System.out.println("A quel interet voulez-vous vous desabonner?");
                System.out.println(controlleurUtilisateurs.retournerInteretUtilisateur(pseudo));
                String choix = scanner.nextLine();
                controlleurUtilisateurs.desabonnerInteret(choix, pseudo);
            }

            }
        }

    }


