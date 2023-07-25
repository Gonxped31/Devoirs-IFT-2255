package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;

public class GestionReseauGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel suivreUtilisateurPanel = new JPanel();
    private JPanel gererSuiveursPanel = new JPanel();
    private JPanel gererInteretsPanel = new JPanel();
    private JLabel gestionReseauLabel = new JLabel("Gestion reseau");
    private JButton btnSuivreUtilisateur = new JButton("Suivre un utilisateur");
    private JButton btnGererSuiveurs = new JButton("Gerer mes suiveurs");
    private JButton btnGererInterets = new JButton("Gerer mes interets");

    public GestionReseauGUI() {
        setMainPanel();
        setSuivreUtilisateurPanel();
        setGererSuiveursPanel();
        setGererInteretsPanel();
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionReseauLabel);
        mainPanel.add(btnSuivreUtilisateur);
        mainPanel.add(btnGererSuiveurs);
        mainPanel.add(btnGererInterets);
    }
    public void setSuivreUtilisateurPanel() {
        suivreUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setGererSuiveursPanel() {
        gererSuiveursPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setGererInteretsPanel() {
        gererInteretsPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void afficherPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
