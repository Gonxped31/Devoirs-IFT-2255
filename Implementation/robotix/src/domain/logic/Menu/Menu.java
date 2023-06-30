package domain.logic.Menu;
import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;

public class Menu {
    //private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    //private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private TypesComposants typesComposants;
    private MenuUtilisateur menuUtilisateur = new MenuUtilisateur();
    private MenusFournisseur menusFournisseur = new MenusFournisseur();

    public Menu() throws IOException {
    }

    public void menuPrincipale(Scanner scanner) throws ParseException, IOException {
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
                    menuUtilisateur.inscrireUtilisateur(scanner);
                    break;
                case "2" :
                    menusFournisseur.menuInscriptionFournisseur(scanner);
                    break;
                case "3" :
                    menuUtilisateur.connecterUtilisateur(scanner);
                    break;
                case "4" :
                    menusFournisseur.menuConnexionFournisseur(scanner);
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
}
