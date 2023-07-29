package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionNotifsGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel notifsPanel = new JPanel(new GridBagLayout());
    private JLabel gestionNotifsLabel = new JLabel("Vos notifications", SwingConstants.CENTER);
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionNotifsGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gestionNotifsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Ajout des composantes
        constraints.gridy = 0;
        notifsPanel.add(btnRetour, constraints);
        mainPanel.add(gestionNotifsLabel, BorderLayout.NORTH);
        mainPanel.add(notifsPanel, BorderLayout.CENTER);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
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
