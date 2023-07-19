package domain.logic.GUI.Utilisateur;

import javax.swing.*;

public class GestionNotifsGUI {
    private JPanel gestionNotifsPanel = new JPanel();
    private JLabel gestionNotifsLabel = new JLabel("Vos notifications");

    public GestionNotifsGUI() {
        gestionNotifsPanel.add(gestionNotifsLabel);
    }

    public JPanel getPanel() {
        return gestionNotifsPanel;
    }
}
