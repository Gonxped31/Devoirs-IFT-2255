package domain.logic.Menu;

import java.util.Scanner;

import domain.logic.Controller.ControlleurUtilisateurs;

public class MenuGestionReseau {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private MenusUtilisateur menuUtil;
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
                controlleurUtilisateurs.gererInteret(pseudo);
                break;
            case "4":
                menuUtil.menuUtilisateur(scanner, pseudo);
        }
    }
}
