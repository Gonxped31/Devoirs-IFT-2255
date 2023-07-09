package domain.logic.Menu;
import domain.logic.Robot.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu extends JFrame {
    //private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    //private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private TypesComposants typesComposants;
    private MenuUtilisateur menuUtilisateur = new MenuUtilisateur();
    private MenusFournisseur menusFournisseur = new MenusFournisseur();
    private JLabel txtBienvenue;
    private JButton btnInscrireUtilisateur;
    private JButton btnInscrireFournisseur;
    private JButton btnConnecterUtilisateur;
    private JButton btnConnecterFournisseur;
    private JButton btnQuitter;
    private JPanel menuPanel;
    private JLabel txtSousTitre;

    public Menu() throws IOException {
        setContentPane(menuPanel);
        setTitle("ROBOTIX");
        setSize(650, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnInscrireUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //menuUtilisateur.inscrireGUI();

            }
        });
        btnInscrireFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //menusFournisseur.inscrireGUI();
            }
        });
        btnConnecterUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //menuUtilisateur.seConnecterGUI();

            }
        });
        btnConnecterFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // menusFournisseur.seConnecterGUI();

            }
        });
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
                case "1" -> menuUtilisateur.inscrireUtilisateur(scanner);
                case "2" -> menusFournisseur.menuInscriptionFournisseur(scanner);
                case "3" -> menuUtilisateur.connecterUtilisateur(scanner);
                case "4" -> menusFournisseur.menuConnexionFournisseur(scanner);
                case "5" -> {
                    System.out.println("Au revoir !");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Choix invalide. Veuillez réessayez.");
                    menuPrincipale(scanner);
                }
            }
        } while (!options.contains(Integer.parseInt(choixUsager)));
    }
}
