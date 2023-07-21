package domain.logic.GUI.FournisseurGUI;

import domain.logic.Menu.MenusFournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnexionFournisseurGUI {
    private JPanel connexionFournisseurPanel = new JPanel();
    private JLabel connexionFournisseurLabel = new JLabel("Connexion en tant que fournisseur");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JTextField nomField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JButton btnSeConnecter = new JButton("Se Connecter");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private JFrame jFrame = new JFrame();

    public ConnexionFournisseurGUI()  {
        connexionFournisseurPanel.setLayout(new GridLayout(0, 2, 5, 5));
        connexionFournisseurPanel.add(connexionFournisseurLabel);
        connexionFournisseurPanel.add(Box.createHorizontalStrut(10));
        connexionFournisseurPanel.add(nomLabel);
        connexionFournisseurPanel.add(nomField);
        connexionFournisseurPanel.add(mdpLabel);
        connexionFournisseurPanel.add(mdpField);
        connexionFournisseurPanel.add(btnSeConnecter);
        connexionFournisseurPanel.add(btnRetour);

        btnSeConnecter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                try {
                    menusFournisseur = new MenusFournisseur();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                menusFournisseur.afficherMenuFournisseur(jFrame);
			}
		});
    }

    public void afficherPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.getContentPane().removeAll();
        this.jFrame.setContentPane(connexionFournisseurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
