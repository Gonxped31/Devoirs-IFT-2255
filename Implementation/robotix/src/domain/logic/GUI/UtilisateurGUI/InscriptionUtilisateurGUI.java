package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Menu.MenuUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InscriptionUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel inscriptionUtilisateurPanel = new JPanel();
    private JLabel inscriptionUtilisateurLabel = new JLabel("Inscription en tant qu'utilisateur");
    private JLabel prenomLabel = new JLabel("Prenom");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JLabel interetsLabel = new JLabel("Intérêts");
    private JTextField prenomField = new JTextField();
    private JTextField nomField = new JTextField();
    private JTextField pseudoField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    private JButton btnRetour = new JButton("Retour");
    private MenuUtilisateur menuUtilisateur;

    public InscriptionUtilisateurGUI() {
        inscriptionUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
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
        inscriptionUtilisateurPanel.add(interetsLabel);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnConfirmerInscription);
        inscriptionUtilisateurPanel.add(btnRetour);

        btnConfirmerInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuUtilisateur = new MenuUtilisateur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menuUtilisateur.afficherMenuUtilisateur(jFrame);
            }
        });
    }

    public void afficherPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.getContentPane().removeAll();
        this.jFrame.setContentPane(inscriptionUtilisateurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}