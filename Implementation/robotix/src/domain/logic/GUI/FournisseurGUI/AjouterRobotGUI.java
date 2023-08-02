package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class AjouterRobotGUI {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private String nomFournisseur;
    private JFrame jFrame = new JFrame();
    private JPanel ajouterRobotPanel = new JPanel(new GridBagLayout());
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public AjouterRobotGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        // Déclaration des composantes implementees dans le panel
        JLabel infosLabel = new JLabel("Entrez le type du robot a rajouter");
        JTextField infosField = new JTextField();
        JButton btnAjouter = new JButton("Ajouter ce nouveau robot");
        JButton btnRetour = new JButton("Retour au menu Fournisseur");

        // Setup la dimension du JTextField
        infosField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        ajouterRobotPanel.add(infosLabel, constraints);
        constraints.gridy = 1;
        ajouterRobotPanel.add(infosField, constraints);
        constraints.gridy = 2;
        ajouterRobotPanel.add(btnAjouter, constraints);
        constraints.gridy = 3;
        ajouterRobotPanel.add(btnRetour, constraints);

        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = infosField.getText();
                if (!type.isEmpty()){
                    UUID uuid = controlleurFournisseurs.ajouterRobot(type, nomFournisseur);
                    String message = "Le robot a ete bien ajoute.\nVoici le numero de serie du robot :\n" + uuid;
                    JOptionPane.showMessageDialog(null, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez enrter un type.", "Errur", JOptionPane.ERROR_MESSAGE);
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
        this.jFrame.setContentPane(ajouterRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}