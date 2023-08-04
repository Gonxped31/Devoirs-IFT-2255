package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Outils.CheckBoxListRenderer;
import domain.logic.Outils.ComboBoxRenderer;
import domain.logic.Outils.HTMLListCellRenderer;
import domain.logic.Robot.Composant;
import domain.logic.Robot.TypesComposants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GestionFlotteGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private String pseudo;
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

    private JScrollPane scrollPaneAfficherEtatRobot;

    private JList<String> listeComposantesJList;
    private JList<String> listeAfficherEtatRobotJList;

    DefaultListModel listModelAfficherEtatRobot = new DefaultListModel();

    private ArrayList<String> listeComposantes;
    private JScrollPane scrollPaneAction;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionFlotteGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
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
        });//Kinda done
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
        });//Done
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
        });//Done
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
        JLabel nomRobotLabel = new JLabel("Nom du robot");
        JLabel typeRobotLabel = new JLabel("Type du robot");
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
        JLabel robotLabel = new JLabel("Robot");
        JLabel numeroSerieLabel = new JLabel("Entrez le numero de serie du robot");
        JTextField numeroSerieField = new JTextField();
        JButton btnContinuer = new JButton("Afficher");
        JButton btnRetour = new JButton("Retour");

        listeAfficherEtatRobotJList = new JList<>(listModelAfficherEtatRobot);
        listeAfficherEtatRobotJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneAfficherEtatRobot = new JScrollPane(listeAfficherEtatRobotJList);
        scrollPaneAfficherEtatRobot.setPreferredSize(new Dimension(250, 200));
        scrollPaneAfficherEtatRobot.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        afficherEtatRobotPanel.add(robotLabel, constraints);
        constraints.gridy = 1;
        afficherEtatRobotPanel.add(scrollPaneAfficherEtatRobot, constraints);
        constraints.gridy = 2;
        afficherEtatRobotPanel.add(numeroSerieLabel, constraints);
        constraints.gridy = 3;
        afficherEtatRobotPanel.add(numeroSerieField, constraints);
        constraints.gridy = 4;
        afficherEtatRobotPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        afficherEtatRobotPanel.add(btnRetour, constraints);

        onBtnContinuerAfficherEtatRobot(btnContinuer, numeroSerieField);
        onBtnAnnulerClicked(btnRetour);
    }

    private void onBtnContinuerAfficherEtatRobot(JButton btnContinuer, JTextField numeroSerieField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSerie = numeroSerieField.getText();
                if (!numeroSerie.isEmpty()) {
                    String r = controlleurUtilisateurs.afficherEtatRobot(numeroSerie, pseudo);
                    if (!r.equals("Robot non trouver, veuiller verifier le numero de serie")) {
                        r = "<html>" + r.replace("\n", "<br>") + "</html>";
                        listModelAfficherEtatRobot.clear();
                        listModelAfficherEtatRobot.addElement(r);
                    } else {
                        JOptionPane.showMessageDialog(null, "Robot non trouver, veuiller verifier le numero de serie.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Robot non trouve.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    listModelAfficherEtatRobot.clear();
                }
                mettreAJourFrame();
            }
        });
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
        JLabel nomActionLabel = new JLabel("Nom de l'action a créer");
        JLabel nomComposanteLabel = new JLabel("Liste des composantes achetées. Selctionnez celles que vous voulez ajouter à cette action");
        recupererLsitesTypeComposantes();
        JLabel dureeLabel = new JLabel("Veuillez entrer le duree");
        JTextField nomActionField = new JTextField();
        JTextField dureeField = new JTextField();
        JButton btnCreationAction = new JButton("Creation de l'action");
        JButton btnMenuGestionFlotte = new JButton("Retour");

        constraints.gridy = 0;
        creerActionPanel.add(nomActionLabel, constraints);
        constraints.gridy = 1;
        creerActionPanel.add(nomActionField, constraints);
        constraints.gridy = 2;
        creerActionPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 3;
        creerActionPanel.add(scrollPaneAction, constraints);
        constraints.gridy = 4;
        creerActionPanel.add(dureeLabel, constraints);
        constraints.gridy = 5;
        creerActionPanel.add(dureeField, constraints);
        constraints.gridy = 6;
        creerActionPanel.add(btnCreationAction, constraints);
        constraints.gridy = 7;
        creerActionPanel.add(btnMenuGestionFlotte, constraints);


        onBtnCreationActionClicked(btnCreationAction, nomActionField, dureeField);
        onBtnAnnulerClicked(btnMenuGestionFlotte);
    }

    private void recupererLsitesTypeComposantes() {
        listeComposantes = new ArrayList<>();
        listeComposantes.add(TypesComposants.CPU.name());
        listeComposantes.add(TypesComposants.ROUE.name());
        listeComposantes.add(TypesComposants.HELICE.name());
        listeComposantes.add(TypesComposants.HAUTPARLEUR.name());
        listeComposantes.add(TypesComposants.BRAS.name());
        listeComposantes.add(TypesComposants.ECRAN.name());
        listeComposantes.add(TypesComposants.MICRO.name());
        listeComposantes.add(TypesComposants.CAMERA.name());

        JPanel composantesPanel = new JPanel();
        composantesPanel.setLayout(new BoxLayout(composantesPanel, BoxLayout.Y_AXIS));

        for (String composante : listeComposantes) {
            JCheckBox checkBox = new JCheckBox(composante);
            checkBox.setSelected(false);
            composantesPanel.add(checkBox);
        }

        scrollPaneAction = new JScrollPane(composantesPanel);
        scrollPaneAction.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
                if (nomRobotField.getText().isEmpty() || typeRobotField.getText().isEmpty() || numeroSerieField.getText().isEmpty())
                    afficherMessageErreurEnregistrerRobot();
                else{
                    if (!controlleurUtilisateurs.enregistrerRobot(nomRobotField.getText(), typeRobotField.getText(), numeroSerieField.getText(), pseudo)){
                        confirmerEnregistrementRobot();
                    } else {
                        afficherMessageErreurEnregistrerRobot();
                    }
                }
                mettreAJourFrame();
            }
        });
    }

    public void onBtnAjouterComposanteClicked(JButton btnEnregistrer, JTextField nomComposanteField, JTextField numeroSerieField) {
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlleurUtilisateurs.ajouterComposanteRobot(nomComposanteField.getText(), numeroSerieField.getText(), pseudo))
                    confirmerAjoutComposante();
                else {
                    afficherMessageErreurAjoutComposante();
                }
            }
        });
    }

    public void onBtnCreationActionClicked(JButton btnCreationAction, JTextField nomActionField, JTextField dureeField) {
        btnCreationAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel composantesPanel = (JPanel) scrollPaneAction.getViewport().getView();
                Component[] checkBoxes = composantesPanel.getComponents();
                ArrayList<String> selectedComposantesList = new ArrayList<>();

                for (Component checkBox : checkBoxes) {
                    if (checkBox instanceof JCheckBox cb && cb.isSelected()) {
                        selectedComposantesList.add(cb.getText());
                    }
                }
                if (nomActionField.getText().isEmpty() || dureeField.getText().isEmpty() || selectedComposantesList.isEmpty()) {
                    afficherMessageErreurCreationAction();
                } else {
                    controlleurUtilisateurs.creerAction(nomActionField.getText(), selectedComposantesList, dureeField.getText(), pseudo);
                    confirmerCreationAction();
                }
                mettreAJourFrame();
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
