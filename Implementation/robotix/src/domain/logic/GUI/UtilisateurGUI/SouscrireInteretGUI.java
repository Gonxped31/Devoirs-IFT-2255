package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SouscrireInteretGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JLabel souscrireInteretLabel = new JLabel("Selectionnez l'interet que vous souhaitez vous souscrire", SwingConstants.CENTER);
    private ButtonGroup buttonGroup = new ButtonGroup(); // Classe qui permet de regrouper un ensemble de bouton radio en selectionnant qu'une seule option parmi le groupe
    private JRadioButton combatLabel = new JRadioButton("Combat");
    private JRadioButton footLabel = new JRadioButton("Foot");
    private JRadioButton soccerLabel = new JRadioButton("Soccer");
    private JRadioButton danseLabel = new JRadioButton("Danse");
    private JRadioButton breakLabel = new JRadioButton("Break");
    private JButton btnValider = new JButton("Valider");
    private JButton btnAnnuler = new JButton("Annuler");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public SouscrireInteretGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        souscrireInteretLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des interets dans un ButtonGroup
        buttonGroup.add(combatLabel);
        buttonGroup.add(footLabel);
        buttonGroup.add(soccerLabel);
        buttonGroup.add(danseLabel);
        buttonGroup.add(breakLabel);

        // Définition des actionCommand pour chaque JRadioButton
        combatLabel.setActionCommand("Combat");
        footLabel.setActionCommand("Foot");
        soccerLabel.setActionCommand("Soccer");
        danseLabel.setActionCommand("Danse");
        breakLabel.setActionCommand("Break");

        // Ajout des composantes
        mainPanel.add(souscrireInteretLabel);
        mainPanel.add(combatLabel);
        mainPanel.add(footLabel);
        mainPanel.add(soccerLabel);
        mainPanel.add(danseLabel);
        mainPanel.add(breakLabel);
        mainPanel.add(btnValider);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAnnuler);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonGroup.getSelection() == null)
                    affirmerMessageErreur();
                else {
                    String interetSelectionne = buttonGroup.getSelection().getActionCommand();
                    affirmerMessageValidation(interetSelectionne);
                }
            }
        });
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retournerMenuUtilisateur();
            }
        });
    }

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void retournerMenuUtilisateur() { // Methode qui retourne au menu Utilisateur
        jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    public void affirmerMessageValidation(String interetSelectionne) {
        String message = "Vous etes souscrit a l'interet " + interetSelectionne;
        String title = "Souscription reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        retournerMenuUtilisateur();
    }

    public void affirmerMessageErreur() {
        String message = "Vous devez selectionne un interet. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
