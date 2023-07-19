package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;

public class GestionTachesGUI {
    private JPanel gestionTachesPanel = new JPanel();
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches");
    private JButton btnCreerTache = new JButton("Creer une tache");
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");

    public GestionTachesGUI() {
        gestionTachesPanel.add(gestionTachesLabel);
        gestionTachesPanel.add(btnCreerTache);
        gestionTachesPanel.add(btnAllouerTache);
    }

    public JPanel getPanel() {
        return gestionTachesPanel;
    }
}
