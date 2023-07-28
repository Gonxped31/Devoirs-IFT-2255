package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionActivitesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel creerActivitePanel = new JPanel();
    private JPanel rejoindreActivitePanel = new JPanel();
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites", SwingConstants.CENTER);
    private JButton btnCreerActivite = new JButton("Creer une activite");
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public GestionActivitesGUI() {
        setMainPanel();
        setCreerActivitePanel();
        setRejoindreActivitePanel();

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void setMainPanel() {
        mainPanel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionActivitesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCreerActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRejoindreActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setCreerActivitePanel() {
        creerActivitePanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setRejoindreActivitePanel() {
        rejoindreActivitePanel.setLayout(new GridLayout(0, 2, 5, 5));
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
