package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class RetirerRobotGUI {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private String nomFournisseur;
    private JFrame jFrame = new JFrame();
    private JPanel retirerRobotPanel = new JPanel(new GridBagLayout());
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public RetirerRobotGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Déclaration des composantes implementees dans le panel
        JLabel numeroRobotLabel = new JLabel("Entrez le numero de serie du robot à retirer");
        JTextField numeroRobotField = new JTextField();
        JButton btnRetirerRobot = new JButton("Retirer le robot");
        JButton btnRetour = new JButton("Retour au menu Fournisseur");

        // Setup la dimension du JTextField
        numeroRobotField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        retirerRobotPanel.add(numeroRobotLabel, constraints);
        constraints.gridy = 1;
        retirerRobotPanel.add(numeroRobotField, constraints);
        constraints.gridy = 2;
        retirerRobotPanel.add(btnRetirerRobot, constraints);
        constraints.gridy = 3;
        retirerRobotPanel.add(btnRetour, constraints);

        btnRetirerRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSerie = numeroRobotField.getText();
                if (!numeroSerie.isEmpty()){
                    if (controlleurFournisseurs.retirerRobot(numeroSerie, nomFournisseur)){
                        JOptionPane.showMessageDialog(null, "Robot retire avec succes",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne possedez pas ce robot",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un numeroDeSerie",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(retirerRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    public void afficherMessageValidation() {
        String message = "Le robot a ete retire avec succes";
        String title = "Operation reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreur() {
        String message = "Vous ne possedez ce robot. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}