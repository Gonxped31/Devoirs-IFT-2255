package domain.logic.GUI;

import domain.logic.GUI.UtilisateurGUI.MenuUtilisateur;
import domain.logic.Robot.*;

import java.io.IOException;
import java.text.ParseException;

import domain.logic.GUI.FournisseurGUI.ConnexionFournisseurGUI;
import domain.logic.GUI.FournisseurGUI.InscriptionFournisseurGUI;
import domain.logic.GUI.UtilisateurGUI.ConnexionUtilisateurGUI;
import domain.logic.GUI.UtilisateurGUI.InscriptionUtilisateurGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe Menu gère l'interface graphique pour le système Robotix.
 * Elle permet aux utilisateurs et aux fournisseurs de s'inscrire, de se connecter
 * et offre des options pour quitter l'application.
 */
public class Menu {
    private TypesComposants typesComposants;
    private MenuUtilisateur menuUtilisateur = new MenuUtilisateur();
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

    /**
     * Constructeur de la classe Menu. Initialise et affiche le menu principal de l'application Robotix.
     *
     * @throws IOException    S'il y a une erreur lors de l'accès à un fichier.
     * @throws ParseException S'il y a une erreur lors de l'analyse des dates.
     */
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

    /**
     * Initialise le panneau du menu principal avec ses composants et sa mise en forme.
     */
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
}
