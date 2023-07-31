package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionActivitesGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel creerActivitePanel = new JPanel(new GridBagLayout());
    private JPanel rejoindreActivitePanel = new JPanel(new GridBagLayout());
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites", SwingConstants.CENTER);
    private JButton btnCreerActivite = new JButton("Creer une activite");
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionActivitesGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setCreerActivitePanel();
        setRejoindreActivitePanel();

        btnCreerActivite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(creerActivitePanel);
                mettreAJourFrame();
            }
        });
        btnRejoindreActivite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rejoindreActivitePanel);
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
        gestionActivitesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionActivitesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCreerActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRejoindreActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setCreerActivitePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomActiviteLabel = new JLabel("Nom de l'activité");
        JLabel datDebutLabel = new JLabel("Date de debut");
        JLabel datFinLabel = new JLabel("Date de fin");
        JLabel tacheLabel = new JLabel("Veuillez entrer une tache");
        JLabel interetLabel = new JLabel("Veuillez entrer un interet");
        JTextField nomActiviteField = new JTextField();
        JTextField dateDebutField = new JTextField();
        JTextField dateFinField = new JTextField();
        JTextField tacheField = new JTextField();
        JTextField interetField = new JTextField();
        JButton btnCreation = new JButton("Confirmer la creation de l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        nomActiviteField.setPreferredSize(new Dimension(200, 30));
        dateDebutField.setPreferredSize(new Dimension(200, 30));
        dateFinField.setPreferredSize(new Dimension(200, 30));
        tacheField.setPreferredSize(new Dimension(200, 30));
        interetField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        creerActivitePanel.add(nomActiviteLabel, constraints);
        constraints.gridy = 1;
        creerActivitePanel.add(nomActiviteField, constraints);
        constraints.gridy = 2;
        creerActivitePanel.add(datDebutLabel, constraints);
        constraints.gridy = 3;
        creerActivitePanel.add(dateDebutField, constraints);
        constraints.gridy = 4;
        creerActivitePanel.add(datFinLabel, constraints);
        constraints.gridy = 5;
        creerActivitePanel.add(dateFinField, constraints);
        constraints.gridy = 6;
        creerActivitePanel.add(tacheLabel, constraints);
        constraints.gridy = 7;
        creerActivitePanel.add(tacheField, constraints);
        constraints.gridy = 8;
        creerActivitePanel.add(interetLabel, constraints);
        constraints.gridy = 9;
        creerActivitePanel.add(interetField, constraints);
        constraints.gridy = 10;
        creerActivitePanel.add(btnCreation, constraints);
        constraints.gridy = 11;
        creerActivitePanel.add(btnAnnuler, constraints);

        onBtnActiviteCreeeClicked(btnCreation, nomActiviteField, dateDebutField, dateFinField, tacheField, interetField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setRejoindreActivitePanel() {
        JLabel rejoindreActiviteLabel = new JLabel("Veuillez selectionner une activite a rejoindre parmi les suivantes");
        JLabel dateActiviteLabel = new JLabel("Entrez une date de debut de l'activite (format dd/MM/yyyy)");
        JRadioButton balladeForet = new JRadioButton("Ballade en foret (du 02/07/2000 au 02/08/2000)");
        JRadioButton course = new JRadioButton("Course de rally (du 05/07/2000 au 02/08/2001)");
        JRadioButton netflix = new JRadioButton("Soiree netflix and chill (du 09/08/2010 au 02/012/2013)");
        JRadioButton gaming = new JRadioButton("Gaming night (du 02/07/2004 au 02/08/2006)");
        JRadioButton hockey = new JRadioButton("Hockey sur glace (du 14/10/2023 au 02/08/2026)");
        JTextField dateActiviteField = new JTextField();
        JButton btnRejoindre = new JButton("Rejoindre l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        dateActiviteField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        rejoindreActivitePanel.add(rejoindreActiviteLabel, constraints);
        constraints.gridy = 1;
        rejoindreActivitePanel.add(balladeForet, constraints);
        constraints.gridy = 2;
        rejoindreActivitePanel.add(course, constraints);
        constraints.gridy = 3;
        rejoindreActivitePanel.add(netflix, constraints);
        constraints.gridy = 4;
        rejoindreActivitePanel.add(gaming, constraints);
        constraints.gridy = 5;
        rejoindreActivitePanel.add(hockey, constraints);
        constraints.gridy = 6;
        rejoindreActivitePanel.add(dateActiviteLabel, constraints);
        constraints.gridy = 7;
        rejoindreActivitePanel.add(dateActiviteField, constraints);
        constraints.gridy = 8;
        rejoindreActivitePanel.add(btnRejoindre, constraints);
        constraints.gridy = 9;
        rejoindreActivitePanel.add(btnAnnuler, constraints);

        onBtnRejoindreActiviteClicked(btnRejoindre, balladeForet, course, netflix, gaming, hockey, dateActiviteField);
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

    public void onBtnActiviteCreeeClicked(JButton btnCreation, JTextField nomActiviteField, JTextField dateDebutField,
                                          JTextField dateFinField, JTextField tacheField, JTextField interetField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomActiviteField.getText().length() == 0 || dateDebutField.getText().length() == 0 || dateFinField.getText().length() == 0
                        || tacheField.getText().length() == 0 || interetField.getText().length() == 0)
                    afficherMessageErreurActiviteCreee();
                else
                    confirmerActiviteCreee();
            }
        });
    }

    public void onBtnRejoindreActiviteClicked(JButton btnCreation, JRadioButton balladeForet, JRadioButton course, JRadioButton netflix,
                                              JRadioButton gaming, JRadioButton hockey, JTextField dateActiviteField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateActiviteField.getText().length() == 0)
                    afficherMessageErreurRejoindreActivite();
                else
                    confirmerRejoindreActivite();
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

    public void confirmerActiviteCreee() {
        String message = "L'activitée a été bien créée";
        String title = "Activite creee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurActiviteCreee() {
        String message = "Cette activitée existe déjà. Veuillez reessayer un autre.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void confirmerRejoindreActivite() {
        String message = "Vous avez rejoint l'activite ____";
        String title = "Joindre activite reussi";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurRejoindreActivite() {
        String message = "Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
