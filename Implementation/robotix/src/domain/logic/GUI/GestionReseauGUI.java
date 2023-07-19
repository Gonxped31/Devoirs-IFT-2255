package domain.logic.GUI;

import javax.swing.*;

public class GestionReseauGUI {
    private JPanel gestionReseauPanel = new JPanel();
    private JLabel gestionReseauLabel = new JLabel("Gestion reseau");
    private JButton btnSuivreUtilisateur = new JButton("Suivre un utilisateur");
    private JButton btnGererSuiveurs = new JButton("Gerer mes suiveurs");
    private JButton btnGererInterets = new JButton("Gerer mes interets");

    public GestionReseauGUI() {
        gestionReseauPanel.add(gestionReseauLabel);
        gestionReseauPanel.add(btnSuivreUtilisateur);
        gestionReseauPanel.add(btnGererSuiveurs);
        gestionReseauPanel.add(btnGererInterets);
    }

    public JPanel getPanel() {
        return gestionReseauPanel;
    }
}
