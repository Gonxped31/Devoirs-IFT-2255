package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetirerRobotGUI {
    private JFrame jFrame = new JFrame();
    private JPanel retirerRobotPanel = new JPanel();
    private JLabel retirerRobotLabel = new JLabel("Veuillez entrer le numero de serie du robot à retirer");
    private JButton btnRetirerRobot = new JButton("Retirer le robot");
    private JTextField numeroRobot = new JTextField();
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();
    public RetirerRobotGUI() {
        retirerRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
        retirerRobotPanel.add(retirerRobotLabel);
        retirerRobotPanel.add(numeroRobot);
        retirerRobotPanel.add(btnRetirerRobot);
        retirerRobotPanel.add(btnRetour);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });

    }
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(retirerRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
