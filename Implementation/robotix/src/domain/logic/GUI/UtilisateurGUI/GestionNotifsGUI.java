package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;

public class GestionNotifsGUI {
    private JFrame jFrame = new JFrame();
    private JPanel gestionNotifsPanel = new JPanel();
    private JLabel gestionNotifsLabel = new JLabel("Vos notifications");

    public GestionNotifsGUI() {
        gestionNotifsPanel.add(gestionNotifsLabel);
    }

    public void changerContenu(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.setContentPane(gestionNotifsPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
