package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SouscrireInteretGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JLabel listeInteretLabel = new JLabel("Voici la liste d'interets", SwingConstants.CENTER);
    private JRadioButton combatLabel = new JRadioButton("Combat");
    private JRadioButton footLabel = new JRadioButton("Foot");
    private JRadioButton soccerLabel = new JRadioButton("Soccer");
    private JRadioButton danseLabel = new JRadioButton("Danse");
    private JRadioButton breakLabel = new JRadioButton("Break");
    private JLabel souscrireInteretLabel = new JLabel("A quel interet voulez-vous souscrire?", SwingConstants.CENTER);
    private JButton btnCombat = new JButton("Combat");
    private JButton btnFoot = new JButton("Foot");
    private JButton btnSoccer= new JButton("Soccer");
    private JButton btnDanse = new JButton("Danse");
    private JButton btnBreak = new JButton("Break");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public SouscrireInteretGUI() {
        mainPanel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(listeInteretLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(combatLabel);
        mainPanel.add(footLabel);
        mainPanel.add(soccerLabel);
        mainPanel.add(danseLabel);
        mainPanel.add(breakLabel);
        mainPanel.add(souscrireInteretLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCombat);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnFoot);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnSoccer);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnDanse);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnBreak);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);

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
