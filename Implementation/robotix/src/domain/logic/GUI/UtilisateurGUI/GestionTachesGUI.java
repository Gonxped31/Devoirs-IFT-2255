package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionTachesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel creerTachePanel = new JPanel(new GridBagLayout());
    private JPanel allouerTachePanel = new JPanel(new GridBagLayout());
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches", SwingConstants.CENTER);
    private JButton btnCreerTache = new JButton("Creer une tache");
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionTachesGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setCreerTachePanel();
        setAllouerTachePanel();

        btnCreerTache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(creerTachePanel);
                mettreAJourFrame();
            }
        });
        btnAllouerTache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(allouerTachePanel);
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
        gestionTachesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionTachesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCreerTache);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAllouerTache);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setCreerTachePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel tacheCreeeLabel = new JLabel("Inscrivez le nom de la tache a creee");
        JLabel actionLabel = new JLabel("Parmi vos actions, laquelle/lesquelles voulez-vous associer a cette tache?");
        JLabel ajouterActionLabel = new JLabel("Voulez-vous rajouter une action a cette tache?");
        JTextField tacheCreeeField = new JTextField();
        JTextField actionField = new JTextField();
        JRadioButton radioButtonOui = new JRadioButton("Oui");
        JRadioButton radioButtonNon = new JRadioButton("Non");
        JButton btnCreation = new JButton("Confirmer la creation de la tache");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        tacheCreeeField.setPreferredSize(new Dimension(200, 30));
        actionField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        creerTachePanel.add(tacheCreeeLabel, constraints);
        constraints.gridy = 1;
        creerTachePanel.add(tacheCreeeField, constraints);
        constraints.gridy = 2;
        creerTachePanel.add(actionLabel, constraints);
        constraints.gridy = 3;
        creerTachePanel.add(actionField, constraints);
        constraints.gridy = 4;
        creerTachePanel.add(ajouterActionLabel, constraints);
        constraints.gridy = 5;
        creerTachePanel.add(radioButtonOui, constraints);
        constraints.gridy = 6;
        creerTachePanel.add(radioButtonNon, constraints);
        constraints.gridy = 7;
        creerTachePanel.add(btnCreation, constraints);
        constraints.gridy = 8;
        creerTachePanel.add(btnAnnuler, constraints);

        onBtnTacheCreeeClicked(btnCreation, tacheCreeeField, actionField, radioButtonOui, radioButtonNon);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setAllouerTachePanel() {
        JLabel nomRobotLabel = new JLabel("A quel robot voulez-vous allouer une tache?");
        JLabel nomTacheLabel = new JLabel("Quel est le nom de la tache a allouer?");
        JTextField nomRobotField = new JTextField();
        JTextField nomTacheField = new JTextField();
        JButton btnAffectation = new JButton("Confirmer l'affectation de la tache");
        JButton btnAnnuler = new JButton("Annuler");

        nomRobotField.setPreferredSize(new Dimension(200, 30));
        nomTacheField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        allouerTachePanel.add(nomRobotLabel, constraints);
        constraints.gridy = 1;
        allouerTachePanel.add(nomRobotField, constraints);
        constraints.gridy = 2;
        allouerTachePanel.add(nomTacheLabel, constraints);
        constraints.gridy = 3;
        allouerTachePanel.add(nomTacheField, constraints);
        constraints.gridy = 4;
        allouerTachePanel.add(btnAffectation, constraints);
        constraints.gridy = 5;
        allouerTachePanel.add(btnAnnuler, constraints);

        onBtnTacheAlloueClicked(btnAffectation, nomRobotField, nomTacheField);
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

    public void onBtnTacheCreeeClicked(JButton btnCreation, JTextField tacheCreeeField, JTextField actionField, JRadioButton radioButtonOui, JRadioButton radioButtonNon) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tacheCreeeField.getText().length() == 0 || actionField.getText().length() == 0)
                    afficherMessageErreurTacheCreee();
                else
                    confirmerTacheCreee();
            }
        });
    }

    public void onBtnTacheAlloueClicked(JButton btnAffectation, JTextField nomRobotField, JTextField nomTacheField) {
        btnAffectation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomRobotField.getText().length() == 0 || nomTacheField.getText().length() == 0)
                    afficherMessageErreurTacheAlloue();
                else
                    confirmerTacheAlloue();
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

    public void confirmerTacheCreee() {
        String message = "Vous avez creer la tache ____ ";
        String title = "Creation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurTacheCreee() {
        String message = "Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void confirmerTacheAlloue() {
        String message = "La tache a ete allouee avec succes";
        String title = "Affectation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurTacheAlloue() {
        String message = "La tache n'a pas pu être allouee car vous ne possedez pas le robot ou la tache indique. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
