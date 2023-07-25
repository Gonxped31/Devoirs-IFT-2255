package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;

public class GestionActivitesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel creerActivitePanel = new JPanel();
    private JPanel rejoindreActivitePanel = new JPanel();
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites");
    private JButton btnCreerActivite = new JButton("Creer une activite");
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");

    public GestionActivitesGUI() {
        setMainPanel();
        setCreerActivitePanel();
        setRejoindreActivitePanel();
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionActivitesLabel);
        mainPanel.add(btnCreerActivite);
        mainPanel.add(btnRejoindreActivite);
    }

    public void setCreerActivitePanel() {
        creerActivitePanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRejoindreActivitePanel() {
        rejoindreActivitePanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void changerContenu(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
