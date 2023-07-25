package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;

public class GestionTachesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel creerTachePanel = new JPanel();
    private JPanel allouerTachePanel = new JPanel();
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches");
    private JLabel creerTacheLabel = new JLabel("Quelles tache voulez-vous creer?");
    private JLabel allouerTacheLabel = new JLabel("A quel robot voulez-vous allouer une tache?");
    private JLabel allouerTacheLabel2 = new JLabel("Quel est le nom de la tache a allouer??");
    private JButton btnCreerTache = new JButton("Creer une tache");
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    private JTextField nomTacheCreeField = new JTextField();
    private JTextField nomTacheAlloueField = new JTextField();
    private JTextField nomRobotField = new JTextField();

    public GestionTachesGUI() {
        setMainPanel();
        setCreerTachePanel();
        setAllouerTachePanel();
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionTachesLabel);
        mainPanel.add(btnCreerTache);
        mainPanel.add(btnAllouerTache);
    }

    public void setCreerTachePanel() {
        creerTachePanel.setLayout(new GridLayout(0, 2, 5, 5));
        creerTachePanel.add(creerTacheLabel);
        creerTachePanel.add(nomTacheCreeField);
    }

    public void setAllouerTachePanel() {
        allouerTachePanel.setLayout(new GridLayout(0, 2, 5, 5));
        allouerTachePanel.add(allouerTacheLabel);
        allouerTachePanel.add(nomRobotField);
        allouerTachePanel.add(allouerTacheLabel2);
        allouerTachePanel.add(nomTacheAlloueField);
    }

    public void afficherMainPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
