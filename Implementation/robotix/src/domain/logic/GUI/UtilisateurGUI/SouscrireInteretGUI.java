package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;

public class SouscrireInteretGUI {
    private JFrame jFrame = new JFrame();
    private JPanel souscrireInteretPanel = new JPanel();
    private JLabel listeInteretLabel = new JLabel("Voici la liste d'interets");
    private JRadioButton combatLabel = new JRadioButton("Combat");
    private JRadioButton footLabel = new JRadioButton("Foot");
    private JRadioButton soccerLabel = new JRadioButton("Soccer");
    private JRadioButton danseLabel = new JRadioButton("Danse");
    private JRadioButton breakLabel = new JRadioButton("Break");
    private JLabel souscrireInteretLabel = new JLabel("A quel interet voulez-vous souscrire?");
    private JButton btnCombat = new JButton("Combat");
    private JButton btnFoot = new JButton("Foot");
    private JButton btnSoccer= new JButton("Soccer");
    private JButton btnDanse = new JButton("Danse");
    private JButton btnBreak = new JButton("Break");

    public SouscrireInteretGUI() {
        souscrireInteretPanel.add(listeInteretLabel);
        souscrireInteretPanel.add(combatLabel);
        souscrireInteretPanel.add(footLabel);
        souscrireInteretPanel.add(soccerLabel);
        souscrireInteretPanel.add(danseLabel);
        souscrireInteretPanel.add(breakLabel);
        souscrireInteretPanel.add(souscrireInteretLabel);
        souscrireInteretPanel.add(btnCombat);
        souscrireInteretPanel.add(btnFoot);
        souscrireInteretPanel.add(btnSoccer);
        souscrireInteretPanel.add(btnDanse);
        souscrireInteretPanel.add(btnBreak);
    }

    public void afficherPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(souscrireInteretPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
