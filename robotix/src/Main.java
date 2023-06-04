import java.util.Scanner;
import domain.logic.Fournisseur.Fournisseur;
import domain.logic.Utilisateurs.Utilisateurs;

import java.util.LinkedList;

public class Main {
    //Liste d'utilisateurs
    static LinkedList<Utilisateurs> utilisateurs = new LinkedList<>();
    static LinkedList<Fournisseur> fournisseurs= new LinkedList<>();
    //Liste de fournisseurs 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue chez Robotix! Veuillez choisir une des options ci-dessous:");
        System.out.println("1- Vous inscrire comme utilisateur");
        System.out.println("2- Vous inscrire comme fournisseur");
        System.out.println("3- Vous connecter comme utilisateur");
        System.out.println("4- Vous connecter comme fournisseur");
        String decision = scanner.nextLine();
        switch (decision){
            case("1"):
                System.out.println("********Nouvel utilisateur********");
                System.out.print("Prenom : ");
                String prenom = scanner.nextLine();
                System.out.print("Nom :");
                String nom = scanner.nextLine();
                System.out.print("Pseudo : ");
                String pseudo = scanner.nextLine();
                System.out.print("Adresse courriel : ");
                String courriel = scanner.nextLine();
                System.out.print("Telephone : ");
                String telephone = scanner.nextLine();
                Utilisateurs util = new Utilisateurs(nom, prenom, pseudo, courriel, telephone);
                utilisateurs.add(util);
                System.out.println(utilisateurs.get(0));
                System.out.println("Have fun " + pseudo);
                break;
            case("2"):
                System.out.println("********Nouveau fournisseur********");
                System.out.print("Nom :");
                String nomFour = scanner.nextLine();
                System.out.print("Adresse de la compagnie :");
                String adresseFour = scanner.nextLine();
                System.out.print("Pseudo :");
                String pseudoFour = scanner.nextLine();
                System.out.print("Adresse courriel :");
                String courrielFour = scanner.nextLine();
                System.out.print("Telephone :");
                String telephoneFour = scanner.nextLine();
                System.out.print("Type de robots fabriqués :");
                String typeRobot = scanner.nextLine();
                System.out.print("Capacité : ");
                String capacite = scanner.nextLine();
                System.out.print("Nom de la compagnie :");
                String compagnie = scanner.nextLine();
                Fournisseur fournisseur = new Fournisseur(nomFour,adresseFour, courrielFour, pseudoFour, typeRobot, telephoneFour, capacite, compagnie);
                fournisseurs.add(fournisseur);
                System.out.println("Go sell some products " + fournisseur.nom);
                break;
            case("3"):
                System.out.println("Veuillez entrer votre pseudo: ");
                String connexion = scanner.nextLine();
                for (int i = 0; i < utilisateurs.size(); i++) {
                    if (utilisateurs.get(i).pseudo.equals(connexion)){
                        System.out.println("Bienvenue " + utilisateurs.get(i).pseudo);
                        Utilisateurs.main(scanner);
                        break;
                    }
                }
                System.out.println(connexion + " n'existe pas...");
                break;
            case("4"):
                System.out.print("Veuillez entrer votre pseudo: ");
                String connexionFour = scanner.nextLine();
                for (int i = 0; i < fournisseurs.size(); i++) {
                    if (fournisseurs.get(i).pseudo.equals(connexionFour)){
                        System.out.println("Bienvenue " + fournisseurs.get(i).pseudo);
                        Utilisateurs.main(scanner);
                        break;
                    }
                }
                System.out.println(connexionFour + " n'existe pas...");
                break;
        }   
        String[] arr0=new String[]{""};
        scanner.close();
        main(arr0);
    }
}