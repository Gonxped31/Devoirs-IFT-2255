package domain.logic.GUI.Utilisateur;

import javax.swing.*;

public class SouscrireInteretGUI {
    private JPanel souscrireInteretPanel = new JPanel();
    private JLabel souscrireInteretLabel = new JLabel("A quel interet voulez-vous souscrire?");
    private JButton btnCombat = new JButton("Combat");
    private JButton btnFoot = new JButton("Foot");
    private JButton btnSoccer= new JButton("Soccer");
    private JButton btnDanse = new JButton("Danse");
    private JButton btnBreak = new JButton("Break");

    public SouscrireInteretGUI() {
        souscrireInteretPanel.add(souscrireInteretLabel);
        souscrireInteretPanel.add(btnCombat);
        souscrireInteretPanel.add(btnFoot);
        souscrireInteretPanel.add(btnSoccer);
        souscrireInteretPanel.add(btnDanse);
        souscrireInteretPanel.add(btnBreak);
    }

    public JPanel getPanel() {
        return souscrireInteretPanel;
    }
}
