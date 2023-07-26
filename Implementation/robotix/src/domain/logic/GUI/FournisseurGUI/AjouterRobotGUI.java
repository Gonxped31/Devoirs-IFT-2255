package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterRobotGUI {
    private JFrame jFrame = new JFrame();
    private JPanel ajouterRobotPanel = new JPanel();
    private JButton btnRetour = new JButton();
    private Container panelPrecedent = new Container();
    public AjouterRobotGUI() {
        ajouterRobotPanel.setLayout(new GridLayout(0, 2, 5, 5));
        ajouterRobotPanel.add(btnRetour);

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
        this.jFrame.setContentPane(ajouterRobotPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
