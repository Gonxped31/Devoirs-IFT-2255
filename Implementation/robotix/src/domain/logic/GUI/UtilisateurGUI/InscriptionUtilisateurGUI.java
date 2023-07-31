package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Menu.MenuUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class InscriptionUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel inscriptionUtilisateurPanel = new JPanel();
    private JLabel inscriptionUtilisateurLabel = new JLabel("Inscription en tant que nouvel utilisateur", SwingConstants.CENTER);
    private JLabel prenomLabel = new JLabel("Prenom");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JLabel interetLabel = new JLabel("Intérêts");
    private JTextField prenomField = new JTextField();
    private JTextField nomField = new JTextField();
    private JTextField pseudoField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JTextField interetField = new JTextField();
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    private JButton btnRetour = new JButton("Retour");
    private MenuUtilisateur menuUtilisateur;
    private Container panelPrecedent = new Container();

    public InscriptionUtilisateurGUI() {
        inscriptionUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inscriptionUtilisateurPanel.setLayout(new GridLayout(0, 1));
        inscriptionUtilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        inscriptionUtilisateurPanel.add(inscriptionUtilisateurLabel);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(prenomLabel);
        inscriptionUtilisateurPanel.add(prenomField);
        inscriptionUtilisateurPanel.add(nomLabel);
        inscriptionUtilisateurPanel.add(nomField);
        inscriptionUtilisateurPanel.add(pseudoLabel);
        inscriptionUtilisateurPanel.add(pseudoField);
        inscriptionUtilisateurPanel.add(courrielLabel);
        inscriptionUtilisateurPanel.add(courrielField);
        inscriptionUtilisateurPanel.add(mdpLabel);
        inscriptionUtilisateurPanel.add(mdpField);
        inscriptionUtilisateurPanel.add(telephoneLabel);
        inscriptionUtilisateurPanel.add(telephoneField);
        inscriptionUtilisateurPanel.add(adresseLabel);
        inscriptionUtilisateurPanel.add(adresseField);
        inscriptionUtilisateurPanel.add(nomCompagnieLabel);
        inscriptionUtilisateurPanel.add(nomCompagnieField);
        inscriptionUtilisateurPanel.add(interetLabel);
        inscriptionUtilisateurPanel.add(interetField);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnConfirmerInscription);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnRetour);

        btnConfirmerInscription.addActionListener(new ActionListener() { // Faut ajouter une condition pour que tous les champs soient remplies avant de passer au menu
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuUtilisateur = new MenuUtilisateur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menuUtilisateur.afficherMenuUtilisateurPanel(jFrame);
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(inscriptionUtilisateurPanel);
        this.jFrame.revalidate();
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
