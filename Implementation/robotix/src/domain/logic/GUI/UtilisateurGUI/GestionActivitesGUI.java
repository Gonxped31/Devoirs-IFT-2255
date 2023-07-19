package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;

public class GestionActivitesGUI {
    private JPanel gestionActivitesPanel = new JPanel();
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites");
    private JButton btnCreerActivite = new JButton("Creer une activite");
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");

    public GestionActivitesGUI() {
        gestionActivitesPanel.add(gestionActivitesLabel);
        gestionActivitesPanel.add(btnCreerActivite);
        gestionActivitesPanel.add(btnRejoindreActivite);
    }

    public JPanel getPanel() {
        return gestionActivitesPanel;
    }
}
