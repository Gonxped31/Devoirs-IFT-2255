package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterRobotGUI {
    private JFrame jFrame = new JFrame();
    private JPanel ajouterRobotPanel = new JPanel(new GridBagLayout());
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public AjouterRobotGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Déclaration des composantes implementees dans le panel
        JLabel infosLabel = new JLabel("Entrez les informations de la composante");
        JLabel rajouterLabel = new JLabel("Voulez-vous rajouter une autre composante ?");
        JTextField infosField = new JTextField();
        ButtonGroup buttonGroup = new ButtonGroup(); // Classe qui regroupe un ensemble de boutons radio en selectionnant qu'une seule option parmi le groupe
        JRadioButton ouiLabel = new JRadioButton("Oui");
        JRadioButton nonLabel = new JRadioButton("Non");
        JButton btnAjouter = new JButton("Ajouter ce nouveau robot");
        JButton btnRetour = new JButton("Retour");

        // Setup la dimension du JTextField
        infosField.setPreferredSize(new Dimension(200, 30));

        // Ajout des interets dans un ButtonGroup
        buttonGroup.add(ouiLabel);
        buttonGroup.add(nonLabel);

        // Définition des actionCommand pour chaque JRadioButton
        ouiLabel.setActionCommand("Oui");
        nonLabel.setActionCommand("Non");

        // Ajout des composantes
        constraints.gridy = 0;
        ajouterRobotPanel.add(infosLabel, constraints);
        constraints.gridy = 1;
        ajouterRobotPanel.add(infosField, constraints);
        constraints.gridy = 2;
        ajouterRobotPanel.add(rajouterLabel, constraints);
        constraints.gridy = 3;
        ajouterRobotPanel.add(ouiLabel, constraints);
        constraints.gridy = 4;
        ajouterRobotPanel.add(nonLabel, constraints);
        constraints.gridy = 5;
        ajouterRobotPanel.add(btnAjouter, constraints);
        constraints.gridy = 6;
        ajouterRobotPanel.add(btnRetour, constraints);

        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // a completer
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
