package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionFlotteGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel enregistrerRobotPanel = new JPanel();
    private JPanel afficherEtatRobotPanel = new JPanel();
    private JPanel ajouterComposantePanel = new JPanel();
    private JPanel afficherMetriquesPanel = new JPanel();
    private JPanel creerActionPanel = new JPanel();
    private JLabel gestionFlotteLabel = new JLabel("Gestion de ma flotte");
    private JButton btnEnregistrerRobot = new JButton("Enregistrer un robot");
    private JButton btnAfficherEtatRobot = new JButton("Afficher etat d'un robot");
    private JButton btnAjouterComposante = new JButton("Ajouter une composante a un robot");
    private JButton btnAfficherMetriques = new JButton("Afficher les metriques de ma flotte");
    private JButton btnCreerAction = new JButton("Creer action");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public GestionFlotteGUI() {
        setMainPanel();
        setEnregistrerRobotPanel();
        setAfficherEtatRobotPanel();
        setAjouterComposantePanel();
        setAfficherMetriquesPanel();
        setCreerActionPanel();

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(gestionFlotteLabel);
        mainPanel.add(btnEnregistrerRobot);
        mainPanel.add(btnAfficherEtatRobot);
        mainPanel.add(btnAjouterComposante);
        mainPanel.add(btnAfficherMetriques);
        mainPanel.add(btnCreerAction);
        mainPanel.add(btnRetour);
    }
    public void setEnregistrerRobotPanel() {
        enregistrerRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setAfficherEtatRobotPanel() {
        afficherEtatRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }
    public void setAjouterComposantePanel() {
        ajouterComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
    }
    public void setAfficherMetriquesPanel() {
        afficherMetriquesPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setCreerActionPanel() {
        creerActionPanel.setLayout(new GridLayout(0, 2, 5, 5));
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
