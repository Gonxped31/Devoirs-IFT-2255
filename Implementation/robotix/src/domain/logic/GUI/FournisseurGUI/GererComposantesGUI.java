package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class GererComposantesGUI {
    private JPanel gererComposantesPanel = new JPanel();
    private JLabel gererComposantesLabel = new JLabel("Choisissez une option");
    private JButton btnSupprimerComposante = new JButton();
    private JButton btnModifierPrixComposante = new JButton();
    private JButton btnModifierDescComposante = new JButton();

    public GererComposantesGUI() {
        gererComposantesPanel.add(gererComposantesLabel);
        gererComposantesPanel.add(btnSupprimerComposante);
        gererComposantesPanel.add(btnModifierPrixComposante);
        gererComposantesPanel.add(btnModifierDescComposante);
    }

    public JPanel getPanel() {
        return gererComposantesPanel;
    }
}
