package domain.logic.GUI.Utilisateur;

import javax.swing.*;

public class AchatsGUI {
    private JPanel achatsPanel = new JPanel();
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?");
    private JButton btnAchatRobot = new JButton("Robot");
    private JButton btnAchatComposante = new JButton("Composante");

    public AchatsGUI() {
        achatsPanel.add(achatsLabel);
        achatsPanel.add(btnAchatRobot);
        achatsPanel.add(btnAchatComposante);
    }

    public JPanel getPanel() {
        return achatsPanel;
    }
}
