package domain.logic.Menu;

import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import domain.logic.GUI.FournisseurGUI.ConnexionFournisseurGUI;
import domain.logic.GUI.FournisseurGUI.InscriptionFournisseurGUI;
import domain.logic.GUI.UtilisateurGUI.ConnexionUtilisateurGUI;
import domain.logic.GUI.UtilisateurGUI.InscriptionUtilisateurGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private TypesComposants typesComposants;
    private MenuUtilisateur menuUtilisateur = new MenuUtilisateur();
    private MenusFournisseur menusFournisseur = new MenusFournisseur();
    private InscriptionUtilisateurGUI inscriptionUtilisateurGUI = new InscriptionUtilisateurGUI();
    private ConnexionUtilisateurGUI connexionUtilisateurGUI = new ConnexionUtilisateurGUI();
    private InscriptionFournisseurGUI inscriptionFournisseurGUI = new InscriptionFournisseurGUI();
    private ConnexionFournisseurGUI connexionFournisseurGUI = new ConnexionFournisseurGUI();
    private JFrame jFrame = new JFrame("Robotix");
    private JPanel menuPrincipalPanel = new JPanel();
    private JLabel bienvenueLabel =  new JLabel("Bienvenue chez Robotix!", SwingConstants.CENTER);
    private JLabel optionsLabel =  new JLabel("Veuillez choisir l'une des options suivantes", SwingConstants.CENTER);
    private JButton btnInscrireUtilisateur = new JButton("S'inscrire comme utilisateur");
    private JButton btnInscrireFournisseur = new JButton("S'inscrire comme fournisseur");
    private JButton btnConnecterUtilisateur = new JButton("Se connecter comme utilisateur");
    private JButton btnConnecterFournisseur = new JButton("Se connecter comme fournisseur");
    private JButton btnQuitter = new JButton("Quitter");

    public Menu() throws IOException, ParseException {
        setPanel();
        jFrame.setContentPane(menuPrincipalPanel);
        jFrame.setSize(750, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        btnInscrireUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscriptionUtilisateurGUI.afficherPanel(jFrame);
            }
        });//Connexion faite
        btnInscrireFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscriptionFournisseurGUI.afficherPanel(jFrame);
            }
        });//Connexion faite
        btnConnecterUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connexionUtilisateurGUI.afficherPanel(jFrame);
            }
        });//Connexion faite
        btnConnecterFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connexionFournisseurGUI.afficherPanel(jFrame);
            }
        });//Connexion faite
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void setPanel() {
        bienvenueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        optionsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuPrincipalPanel.setLayout(new GridLayout(0, 1));
        menuPrincipalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        menuPrincipalPanel.add(bienvenueLabel);
        menuPrincipalPanel.add(optionsLabel);
        menuPrincipalPanel.add(btnInscrireUtilisateur);
        menuPrincipalPanel.add(Box.createHorizontalStrut(10));
        menuPrincipalPanel.add(btnInscrireFournisseur);
        menuPrincipalPanel.add(Box.createHorizontalStrut(10));
        menuPrincipalPanel.add(btnConnecterUtilisateur);
        menuPrincipalPanel.add(Box.createHorizontalStrut(10));
        menuPrincipalPanel.add(btnConnecterFournisseur);
        menuPrincipalPanel.add(Box.createHorizontalStrut(10));
        menuPrincipalPanel.add(btnQuitter);
    }

    /*public void menuPrincipale(Scanner scanner) throws ParseException, IOException {
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
    }*/
}
