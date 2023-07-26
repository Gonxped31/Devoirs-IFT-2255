package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;

public class AjouterRobotGUI {
    private JFrame jFrame = new JFrame();
    private JPanel ajouterRobotPanel = new JPanel();
    public AjouterRobotGUI() {

    }
    public void afficherMainPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(ajouterRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
