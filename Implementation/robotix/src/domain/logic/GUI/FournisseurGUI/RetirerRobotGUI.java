package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;

public class RetirerRobotGUI {
    private JFrame jFrame = new JFrame();
    private JPanel retirerRobotPanel = new JPanel();
    private JLabel retirerRobotLabel = new JLabel("Veuillez entrer le numero de serie du robot à retirer");
    private JButton btnRetirerRobot = new JButton("Retirer le robot");
    private JTextField numeroRobot = new JTextField();
    private JButton btnRetour = new JButton("Retour");
    public RetirerRobotGUI() {
        retirerRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
        retirerRobotPanel.add(retirerRobotLabel);
        retirerRobotPanel.add(btnRetirerRobot);
        retirerRobotPanel.add(numeroRobot);
        retirerRobotPanel.add(btnRetour);

    }
    public void afficherMainPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(retirerRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
