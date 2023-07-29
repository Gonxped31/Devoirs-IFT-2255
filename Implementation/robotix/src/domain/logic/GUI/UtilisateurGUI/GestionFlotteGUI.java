package domain.logic.GUI.UtilisateurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionFlotteGUI {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel enregistrerRobotPanel = new JPanel(new GridBagLayout());
    private JPanel afficherEtatRobotPanel = new JPanel(new GridBagLayout());
    private JPanel ajouterComposantePanel = new JPanel(new GridBagLayout());
    private JPanel afficherMetriquesPanel = new JPanel(new GridBagLayout());
    private JPanel creerActionPanel = new JPanel(new GridBagLayout());
    private JLabel gestionFlotteLabel = new JLabel("Gestion de ma flotte", SwingConstants.CENTER);
    private JButton btnEnregistrerRobot = new JButton("Enregistrer un robot");
    private JButton btnAfficherEtatRobot = new JButton("Afficher etat d'un robot");
    private JButton btnAjouterComposante = new JButton("Ajouter une composante a un robot");
    private JButton btnAfficherMetriques = new JButton("Afficher les metriques de ma flotte");
    private JButton btnCreerAction = new JButton("Creer action");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionFlotteGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setEnregistrerRobotPanel();
        setAfficherEtatRobotPanel();
        setAjouterComposantePanel();
        setAfficherMetriquesPanel();
        setCreerActionPanel();

        btnEnregistrerRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(enregistrerRobotPanel);
                mettreAJourFrame();
            }
        });
        btnAfficherEtatRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(afficherEtatRobotPanel);
                mettreAJourFrame();
            }
        });
        btnAjouterComposante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(ajouterComposantePanel);
                mettreAJourFrame();
            }
        });
        btnAfficherMetriques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(afficherMetriquesPanel);
                mettreAJourFrame();
            }
        });
        btnCreerAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(creerActionPanel);
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
        gestionFlotteLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionFlotteLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnEnregistrerRobot);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAfficherEtatRobot);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAjouterComposante);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAfficherMetriques);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCreerAction);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }
    public void setEnregistrerRobotPanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomRobotLabel = new JLabel("Nom de robot");
        JLabel typeRobotLabel = new JLabel("Type de robot");
        JLabel numeroSerieLabel = new JLabel("Numero de serie");
        JTextField nomRobotField = new JTextField();
        JTextField typeRobotField = new JTextField();
        JTextField numeroSerieField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer ce robot");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        nomRobotField.setPreferredSize(new Dimension(200, 30));
        typeRobotField.setPreferredSize(new Dimension(200, 30));
        numeroSerieField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        enregistrerRobotPanel.add(nomRobotLabel, constraints);
        constraints.gridy = 1;
        enregistrerRobotPanel.add(nomRobotField, constraints);
        constraints.gridy = 2;
        enregistrerRobotPanel.add(typeRobotLabel, constraints);
        constraints.gridy = 3;
        enregistrerRobotPanel.add(typeRobotField, constraints);
        constraints.gridy = 4;
        enregistrerRobotPanel.add(numeroSerieLabel, constraints);
        constraints.gridy = 5;
        enregistrerRobotPanel.add(numeroSerieField, constraints);
        constraints.gridy = 6;
        enregistrerRobotPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 7;
        enregistrerRobotPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerRobotClicked(btnEnregistrer, nomRobotField, typeRobotField, numeroSerieField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    public void setAfficherEtatRobotPanel() {
        JButton btnMenuGestionFlotte = new JButton("Retour");

        constraints.gridy = 0;
        afficherEtatRobotPanel.add(btnMenuGestionFlotte);

        onBtnAnnulerClicked(btnMenuGestionFlotte);
    }
    public void setAjouterComposantePanel() {
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a ajouter");
        JLabel numeroSerieLabel = new JLabel("Numero de serie");
        JTextField nomComposanteField = new JTextField();
        JTextField numeroSerieField = new JTextField();
        JButton btnAjouter = new JButton("Ajouter la composante");
        JButton btnAnnuler = new JButton("Annuler");

        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        numeroSerieField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        ajouterComposantePanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        ajouterComposantePanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        ajouterComposantePanel.add(numeroSerieLabel, constraints);
        constraints.gridy = 3;
        ajouterComposantePanel.add(numeroSerieField, constraints);
        constraints.gridy = 4;
        ajouterComposantePanel.add(btnAjouter, constraints);
        constraints.gridy = 5;
        ajouterComposantePanel.add(btnAnnuler, constraints);

        onBtnAjouterComposanteClicked(btnAjouter, nomComposanteField, numeroSerieField);
        onBtnAnnulerClicked(btnAnnuler);
    }
    public void setAfficherMetriquesPanel() {
        JLabel metriqueLabel = new JLabel("METRIQUES DE MA FLOTTE");
        JLabel consommationCPULabel = new JLabel("Consommation globale du CPU: 83%");
        JLabel consommationMemoireLabel = new JLabel("Consommation globale de la mémoire : 85 %");
        JLabel vitesseLabel = new JLabel("Vitesse moyenne des robots : 15 km/h");
        JLabel batterieLabel = new JLabel("Batterie moyenne de la flotte : 62 %");
        JButton btnMenuGestionFlotte = new JButton("Retour");

        metriqueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        afficherMetriquesPanel.add(metriqueLabel, constraints);
        constraints.gridy = 1;
        afficherMetriquesPanel.add(consommationCPULabel, constraints);
        constraints.gridy = 2;
        afficherMetriquesPanel.add(consommationMemoireLabel, constraints);
        constraints.gridy = 3;
        afficherMetriquesPanel.add(vitesseLabel, constraints);
        constraints.gridy = 4;
        afficherMetriquesPanel.add(batterieLabel, constraints);
        constraints.gridy = 5;
        afficherMetriquesPanel.add(btnMenuGestionFlotte, constraints);

        onBtnAnnulerClicked(btnMenuGestionFlotte);
    }

    public void setCreerActionPanel() {
        JLabel nomActionLabel = new JLabel("Nom de l'action a creer");
        JLabel nomComposanteLabel = new JLabel("Parmi vos composantes, laquelle/lesquelles voulez-vous associer a cette action?");
        JLabel ajouterComposanteLabel = new JLabel("Voulez-vous rajouter une composante a cette action?");
        JLabel dureeLabel = new JLabel("Veuillez entrer le duree");
        JTextField nomActionField = new JTextField();
        JTextField nomComposanteField = new JTextField();
        JTextField dureeField = new JTextField();
        JRadioButton radioButtonOui = new JRadioButton("Oui");
        JRadioButton radioButtonNon = new JRadioButton("Non");
        JButton btnCreationAction = new JButton("Creation de l'action");
        JButton btnMenuGestionFlotte = new JButton("Retour");

        constraints.gridy = 0;
        creerActionPanel.add(nomActionLabel, constraints);
        constraints.gridy = 1;
        creerActionPanel.add(nomActionField, constraints);
        constraints.gridy = 2;
        creerActionPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 3;
        creerActionPanel.add(nomComposanteField, constraints);
        constraints.gridy = 4;
        creerActionPanel.add(ajouterComposanteLabel, constraints);
        constraints.gridy = 5;
        creerActionPanel.add(radioButtonOui, constraints);
        constraints.gridy = 6;
        creerActionPanel.add(radioButtonNon, constraints);
        constraints.gridy = 7;
        creerActionPanel.add(dureeLabel, constraints);
        constraints.gridy = 8;
        creerActionPanel.add(dureeField, constraints);
        constraints.gridy = 9;
        creerActionPanel.add(btnCreationAction, constraints);
        constraints.gridy = 10;
        creerActionPanel.add(btnMenuGestionFlotte, constraints);

        onBtnCreationActionClicked(btnCreationAction, nomActionField, nomComposanteField, radioButtonOui, radioButtonNon, dureeField);
        onBtnAnnulerClicked(btnMenuGestionFlotte);
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

    public void onBtnEnregistrerRobotClicked(JButton btnEnregistrer, JTextField nomRobotField, JTextField typeRobotField, JTextField numeroSerieField) {
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomRobotField.getText().length() == 0 || typeRobotField.getText().length() == 0 || numeroSerieField.getText().length() == 0)
                    afficherMessageErreurEnregistrerRobot();
                else
                    confirmerEnregistrementRobot();
            }
        });
    }

    public void onBtnAjouterComposanteClicked(JButton btnEnregistrer, JTextField nomComposanteField, JTextField numeroSerieField) {
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().length() == 0 ||  numeroSerieField.getText().length() == 0)
                    afficherMessageErreurAjoutComposante();
                else
                    confirmerAjoutComposante();
            }
        });
    }

    public void onBtnCreationActionClicked(JButton btnCreationAction, JTextField nomActionField, JTextField nomComposanteField,
                                           JRadioButton radioButtonOui, JRadioButton radioButtonNon, JTextField dureeField) {
        btnCreationAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomActionField.getText().length() == 0 ||  nomComposanteField.getText().length() == 0 ||  dureeField.getText().length() == 0)
                    afficherMessageErreurCreationAction();
                else
                    confirmerCreationAction();
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

    public void confirmerEnregistrementRobot() {
        String message = "Le robot a ete bien enregistre!";
        String title = "Enregistrement terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerAjoutComposante() {
        String message = "La composante a ete bien ajoute!";
        String title = "Ajout terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerCreationAction() {
        String message = "L'action a ete bien creee";
        String title = "Creation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurEnregistrerRobot() {
        String message = "Aucun robot vendu par nos fournisseurs ne possede ce numero de serie. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurAjoutComposante() {
        String message = "La composante ou le robot entree n'existe pas. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurCreationAction() {
        String message = "Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
