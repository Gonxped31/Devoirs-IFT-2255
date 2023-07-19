package domain.logic.Menu;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.TypeNotification;

public class MenuGestionReseau {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
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
                controlleurUtilisateurs.suivreUtilisateur(nom);
                controlleurUtilisateurs.ajouterNotifs(nom, "Nouvel abonne",pseudo + " vous a suivi",  TypeNotification.NOUVEAU_ABONNE);
                if (!nom.equals(""))
                    System.out.println("Vous suivez maintenant " + nom);
                else {
                    System.out.println("Cet utilisateur n'a pas pu etre ajoute a votre reseau");
                }
                gererReseauSocial(scanner, pseudo);
            } case "2" -> {
                System.out.println("Cette fonctionalitée n'est pas encore disponible );");
                System.out.println("Veuillez rééssayer plus tard.");
                gererReseauSocial(scanner, pseudo);
            }
            //controlleurUtilisateurs.gererSuiveurs(pseudo);
            case "3" -> {
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
}
