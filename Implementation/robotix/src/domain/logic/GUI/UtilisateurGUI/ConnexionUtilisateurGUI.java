package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Menu.MenuUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnexionUtilisateurGUI {
    private JFrame jFrame = new JFrame();
    private JPanel connexionUtilisateurPanel = new JPanel();
    private JLabel connexionUtilisateurLabel = new JLabel("Connexion en tant qu'utilisateur");
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField pseudoField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenuUtilisateur menuUtilisateur;

    public ConnexionUtilisateurGUI() {
        connexionUtilisateurPanel.setLayout(new GridLayout(0, 2, 5, 5));
        connexionUtilisateurPanel.add(connexionUtilisateurLabel);
        connexionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        connexionUtilisateurPanel.add(pseudoLabel);
        connexionUtilisateurPanel.add(pseudoField);
        connexionUtilisateurPanel.add(mdpLabel);
        connexionUtilisateurPanel.add(mdpField);
        connexionUtilisateurPanel.add(btnSeConnecter);
        connexionUtilisateurPanel.add(btnRetour);

        btnSeConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    menuUtilisateur = new MenuUtilisateur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menuUtilisateur.afficherMenuUtilisateurPanel(jFrame);
            }
        });
    }

    public void afficherPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.getContentPane().removeAll();
        this.jFrame.setContentPane(connexionUtilisateurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
