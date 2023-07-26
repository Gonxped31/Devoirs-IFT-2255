package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AchatsGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel achatRobotPanel = new JPanel();
    private JPanel achatComposantePanel = new JPanel();
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?");
    private JButton btnAchatRobot = new JButton("Robot");
    private JButton btnAchatComposante = new JButton("Composante");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();

    public AchatsGUI() {
        setMainPanel();
        setAchatRobotPanel();
        setAchatComposantePanel();

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
        mainPanel.add(achatsLabel);
        mainPanel.add(btnAchatRobot);
        mainPanel.add(btnAchatComposante);
        mainPanel.add(btnRetour);
    }

    public void setAchatRobotPanel() {
        achatRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setAchatComposantePanel() {
        achatComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
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
