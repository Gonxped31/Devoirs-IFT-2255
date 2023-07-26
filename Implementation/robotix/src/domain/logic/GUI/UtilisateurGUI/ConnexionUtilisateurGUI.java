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
    private Container panelPrecedent = new Container();

    public ConnexionUtilisateurGUI() {
        connexionUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        connexionUtilisateurPanel.setLayout(new BoxLayout(connexionUtilisateurPanel, BoxLayout.Y_AXIS));
        connexionUtilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pseudoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, pseudoField.getPreferredSize().height));
        mdpField.setMaximumSize(new Dimension(Integer.MAX_VALUE, mdpField.getPreferredSize().height));

        // Ajout des composantes
        connexionUtilisateurPanel.add(connexionUtilisateurLabel);
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

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
    }

    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(connexionUtilisateurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
