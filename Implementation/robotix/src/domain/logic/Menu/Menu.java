package domain.logic.Menu;
import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private ControlleurUtilisateurs controlleurUtilisateurs;
    private ControlleurFournisseurs controlleurFournisseurs;
    private TypesComposants typesComposants;
    private MenusUtilisateur menuUtilisateur;
    private MenusFournisseur menusFournisseur;
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
                case "1" -> menuUtilisateur.inscrireUtilisateur(scanner);
                case "2" -> menusFournisseur.menuInscriptionFournisseur(scanner);
                case "3" -> menuUtilisateur.connecterUtilisateur(scanner);
                case "4" -> menusFournisseur.menuConnexionFournisseur(scanner);
                case "5" -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide. Veuillez réessayez.");
            }
        } while (!options.contains(Integer.parseInt(choixUsager)));
    }
}
