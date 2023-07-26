package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public GestionReseauGUI() {
        setMainPanel();
        setSuivreUtilisateurPanel();
        setGererSuiveursPanel();
        setGererInteretsPanel();

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionReseauLabel);
        mainPanel.add(btnSuivreUtilisateur);
        mainPanel.add(btnGererSuiveurs);
        mainPanel.add(btnGererInterets);
        mainPanel.add(btnRetour);
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

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
