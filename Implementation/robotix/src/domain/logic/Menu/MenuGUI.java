package domain.logic.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Robot.Composant;

public class MenuGUI extends JFrame {

    private DbControleur controleur = new DbControleur();
    private JPanel mainPanel;
    private JLabel bienvenue;
    private JLabel messageChoix;
    private JButton voirLesUtilisateursButton;
    private JScrollPane scroll;

    public MenuGUI() throws IOException {
        setContentPane(mainPanel);
        setTitle("Robotix");
        setSize(650, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        scroll.setVisible(false);
        voirLesUtilisateursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scroll.setToolTipText(controleur.recupererListeUtilisateur());
                scroll.add
                scroll.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        MenuGUI menuGUI = new MenuGUI();
    }

}
