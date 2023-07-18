package domain.logic.GUI;

import javax.swing.*;

public class GestionFlotteGUI {
    private JPanel gestionFlottePanel = new JPanel();
    private JLabel gestionFlotteLabel = new JLabel("Gestion de ma flotte");
    private JButton btnEnregistrerRobot = new JButton("Enregistrer un robot");
    private JButton btnAfficherEtatRobot = new JButton("Afficher etat d'un robot");
    private JButton btnAjouterComposante = new JButton("Ajouter une composante a un robot");
    private JButton btnAfficherMetriques = new JButton("Afficher les metriques de ma flotte");
    private JButton btnCreerAction = new JButton("Creer action");

    public GestionFlotteGUI() {
        gestionFlottePanel.add(gestionFlotteLabel);
        gestionFlottePanel.add(btnEnregistrerRobot);
        gestionFlottePanel.add(btnAfficherEtatRobot);
        gestionFlottePanel.add(btnAjouterComposante);
        gestionFlottePanel.add(btnAfficherMetriques);
        gestionFlottePanel.add(btnCreerAction);
    }

    public JPanel getPanel() {
        return gestionFlottePanel;
    }
}
