package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AchatsGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel achatRobotPanel = new JPanel(new GridBagLayout());
    private JPanel achatComposantePanel = new JPanel(new GridBagLayout());
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?", SwingConstants.CENTER);
    private JButton btnAchatRobot = new JButton("Robot");
    private JButton btnAchatComposante = new JButton("Composante");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public AchatsGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setAchatRobotPanel();
        setAchatComposantePanel();

        btnAchatRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(achatRobotPanel);
                mettreAJourFrame();
            }
        });
        btnAchatComposante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(achatComposantePanel);
                mettreAJourFrame();
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }
    public void setMainPanel() {
        achatsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(achatsLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAchatRobot);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAchatComposante);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setAchatRobotPanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel listeFournisseursLabel = new JLabel("Voici la liste des fournisseurs");
        JLabel nomFournisseurLabel = new JLabel("Choisissez un fournisseur");
        JLabel numeroRobotLabel = new JLabel("Entrez le numero du robot a acheter");
        JTextField nomFournisseurField = new JTextField();
        JTextField numeroRobotField = new JTextField();
        JButton btnConfirmerAchat = new JButton("Confirmer l'achat de robot");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        nomFournisseurField.setPreferredSize(new Dimension(200, 30));
        numeroRobotField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        achatRobotPanel.add(listeFournisseursLabel, constraints);
        constraints.gridy = 1;
        achatRobotPanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 2;
        achatRobotPanel.add(nomFournisseurField, constraints);
        constraints.gridy = 3;
        achatRobotPanel.add(numeroRobotLabel, constraints);
        constraints.gridy = 4;
        achatRobotPanel.add(numeroRobotField, constraints);
        constraints.gridy = 5;
        achatRobotPanel.add(btnConfirmerAchat, constraints);
        constraints.gridy = 6;
        achatRobotPanel.add(btnAnnuler, constraints);

        onBtnConfirmerAchatClicked(btnConfirmerAchat, nomFournisseurField, numeroRobotField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setAchatComposantePanel() {
        JLabel listeFournisseursLabel = new JLabel("Voici la liste des fournisseurs");
        JLabel nomFournisseurLabel = new JLabel("Choisissez un fournisseur");
        JLabel numeroComposanteLabel = new JLabel("Entrez le numero de la composante a acheter");
        JTextField nomFournisseurField = new JTextField();
        JTextField numeroComposanteField = new JTextField();
        JButton btnConfirmerAchat = new JButton("Confirmer l'achat de la composante");
        JButton btnAnnuler = new JButton("Annuler");

        nomFournisseurField.setPreferredSize(new Dimension(200, 30));
        numeroComposanteField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        achatComposantePanel.add(listeFournisseursLabel, constraints);
        constraints.gridy = 1;
        achatComposantePanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 2;
        achatComposantePanel.add(nomFournisseurField, constraints);
        constraints.gridy = 3;
        achatComposantePanel.add(numeroComposanteLabel, constraints);
        constraints.gridy = 4;
        achatComposantePanel.add(numeroComposanteField, constraints);
        constraints.gridy = 5;
        achatComposantePanel.add(btnConfirmerAchat, constraints);
        constraints.gridy = 6;
        achatComposantePanel.add(btnAnnuler, constraints);

        onBtnConfirmerAchatClicked(btnConfirmerAchat, nomFournisseurField, numeroComposanteField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    public void onBtnConfirmerAchatClicked(JButton btnConfirmerAchat, JTextField nomFournisseurField, JTextField numeroField) {
        btnConfirmerAchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomFournisseurField.getText().length() == 0 || numeroField.getText().length() == 0)
                    afficherMessageErreurAchat();
                else
                    confirmerAchat();
            }
        });
    }

    public void onBtnAnnulerClicked(JButton btnAnnuler) {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }

    public void confirmerAchat() {
        String message = "L'achat a ete bien reussi ";
        String title = "Transaction terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurAchat() {
        String message = "L'achat n'a pas ete bien completee. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
