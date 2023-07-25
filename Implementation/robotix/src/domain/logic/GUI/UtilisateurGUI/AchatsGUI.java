package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;

public class AchatsGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel achatRobotPanel = new JPanel();
    private JPanel achatComposantePanel = new JPanel();
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?");
    private JButton btnAchatRobot = new JButton("Robot");
    private JButton btnAchatComposante = new JButton("Composante");

    public AchatsGUI() {
        setMainPanel();
        setAchatRobotPanel();
        setAchatComposantePanel();
    }
    public void setMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
        mainPanel.add(achatsLabel);
        mainPanel.add(btnAchatRobot);
        mainPanel.add(btnAchatComposante);
    }

    public void setAchatRobotPanel() {
        achatRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
    }

    public void setAchatComposantePanel() {
        achatComposantePanel.setLayout(new GridLayout(0, 2, 5, 5));
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
