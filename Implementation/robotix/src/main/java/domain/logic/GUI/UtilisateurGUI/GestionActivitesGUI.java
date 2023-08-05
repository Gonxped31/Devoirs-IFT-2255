package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Outils.GestionDates;
import domain.logic.Outils.Verifications;
import domain.logic.Robot.Action;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import domain.logic.Robot.Tache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GestionActivitesGUI {
    /**
     * Contrôleur des utilisateurs pour gérer les opérations liées aux utilisateurs.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Pseudo de l'utilisateur associé à l'interface.
     */
    private String pseudo;
    /**
     * Fenêtre JFrame pour l'interface utilisateur.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Panneau principal de l'interface utilisateur avec une disposition en GridLayout.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Panneau pour la création d'une activité avec une disposition en GridBagLayout.
     */
    private JPanel creerActivitePanel = new JPanel(new GridBagLayout());
    /**
     * Panneau pour rejoindre une activité avec une disposition en GridBagLayout.
     */
    private JPanel rejoindreActivitePanel = new JPanel(new GridBagLayout());
    /**
     * Étiquette pour indiquer la gestion des activités.
     */
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites", SwingConstants.CENTER);
    /**
     * Bouton pour créer une activité.
     */
    private JButton btnCreerActivite = new JButton("Creer une activite");
    /**
     * Bouton pour rejoindre une activité.
     */
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");
    /**
     * Bouton pour retourner au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Liste des tâches associées à l'utilisateur.
     */
    private ArrayList<Tache> listeTaches;
    /**
     * Liste des activités associées à l'utilisateur.
     */
    private ArrayList<Activite> listeActivites;
    /**
     * Liste des robots associés à l'utilisateur.
     */
    private ArrayList<Robot> listeRobot;

    /**
     * Composant JScrollPane pour afficher la liste des robots.
     */
    private JScrollPane scrollPaneRobot;
    /**
     * Composant JScrollPane pour afficher la liste des tâches.
     */
    private JScrollPane scrollPaneListeTaches;
    /**
     * Composant JScrollPane pour afficher la liste des activités.
     */
    private JScrollPane scrollPaneListeActivites;
    /**
     * Conteneur pour stocker le panneau précédent.
     */
    private Container panelPrecedent = new Container();
    /**
     * Contraintes pour la disposition des composants dans les panneaux.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Interface graphique pour la gestion des activités, liée à un utilisateur identifié par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @throws IOException En cas d'erreur d'entrée/sortie lors de la création de l'interface.
     * @throws ParseException En cas d'erreur lors de l'analyse de la date.
     */
    public GestionActivitesGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
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


    /**
     * Définit et configure le panneau principal de l'interface graphique de gestion des activités.
     * Ce panneau contient des composants tels que des étiquettes et des boutons.
     */
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

    /**
     * Configure le panneau de création d'activité avec les composants nécessaires tels que des étiquettes, des champs de texte,
     * des boutons, et des éléments interactifs pour la sélection de tâches et d'intérêts.
     */
    public void setCreerActivitePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomActiviteLabel = new JLabel("Nom de l'activité");
        JLabel datDebutLabel = new JLabel("Date de debut (en format yyyy-MM-dd)");
        JLabel datFinLabel = new JLabel("Date de fin (en format yyyy-MM-dd)");
        JLabel tacheLabel = new JLabel("Veuillez selectionner les taches a rajouter a l'activite");
        recupererListeTache();
        JLabel interetLabel = new JLabel("Veuillez entrer un les interets a ajouter a cette activte (veuillez les separer par des virgules)");
        JTextField nomActiviteField = new JTextField();
        JTextField dateDebutField = new JTextField();
        JTextField dateFinField = new JTextField();
        JTextField interetField = new JTextField();
        JButton btnCreation = new JButton("Confirmer la creation de l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        nomActiviteField.setPreferredSize(new Dimension(200, 30));
        dateDebutField.setPreferredSize(new Dimension(200, 30));
        dateFinField.setPreferredSize(new Dimension(200, 30));
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
        creerActivitePanel.add(scrollPaneListeTaches, constraints);
        constraints.gridy = 8;
        creerActivitePanel.add(interetLabel, constraints);
        constraints.gridy = 9;
        creerActivitePanel.add(interetField, constraints);
        constraints.gridy = 10;
        creerActivitePanel.add(btnCreation, constraints);
        constraints.gridy = 10;
        creerActivitePanel.add(btnAnnuler, constraints);

        onBtnActiviteCreeeClicked(btnCreation, nomActiviteField, dateDebutField, dateFinField, interetField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Récupère la liste des tâches associées à l'utilisateur actuel (identifié par son pseudo)
     * à partir du contrôleur d'utilisateurs et configure l'interface correspondante.
     */
    private void recupererListeTache() {
        listeTaches = controlleurUtilisateurs.recuprerListeTache(pseudo);
        JPanel tachePanel = new JPanel();
        tachePanel.setLayout(new BoxLayout(tachePanel, BoxLayout.Y_AXIS));

        for (Tache tache: listeTaches) {
            JCheckBox checkBox = new JCheckBox(tache.getNom());
            checkBox.setSelected(false);
            tachePanel.add(checkBox);
        }
        scrollPaneListeTaches = new JScrollPane(tachePanel);
        scrollPaneListeTaches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Configure le panneau de rejoindre une activité avec les composants nécessaires tels que des étiquettes, des boutons,
     * et des éléments interactifs pour la sélection d'une activité à rejoindre et de robots à ajouter.
     */
    public void setRejoindreActivitePanel() {
        JLabel rejoindreActiviteLabel = new JLabel("Veuillez selectionner une activite a rejoindre parmi les suivantes");
        recupererListeActivites();
        JLabel choisirRobotLabel = new JLabel("Veuillez choisir les robots que vous voulez ajouter a cette activite");
        recupererListeRobot();
        JButton btnRejoindre = new JButton("Rejoindre l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        constraints.gridy = 0;
        rejoindreActivitePanel.add(rejoindreActiviteLabel, constraints);
        constraints.gridy = 1;
        rejoindreActivitePanel.add(scrollPaneListeActivites, constraints);
        constraints.gridy = 2;
        rejoindreActivitePanel.add(choisirRobotLabel, constraints);
        constraints.gridy = 3;
        rejoindreActivitePanel.add(scrollPaneRobot, constraints);
        constraints.gridy = 4;
        rejoindreActivitePanel.add(btnRejoindre, constraints);
        constraints.gridy = 4;
        rejoindreActivitePanel.add(btnAnnuler, constraints);

        onBtnRejoindreActiviteClicked(btnRejoindre);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Récupère la liste des activités à partir du contrôleur d'utilisateurs et configure l'interface
     * pour afficher les activités sous forme de boutons radio permettant la sélection.
     */
    private void recupererListeActivites(){
        listeActivites = controlleurUtilisateurs.recupererListeActivites();

        JPanel activitePanel = new JPanel();
        activitePanel.setLayout(new BoxLayout(activitePanel, BoxLayout.Y_AXIS));

        ButtonGroup buttonGroup = new ButtonGroup();

        for (Activite activite: listeActivites) {
            StringBuilder activiteBuilder = new StringBuilder();
            activiteBuilder.append(activite.getNom())
                    .append(" (Du ")
                    .append(GestionDates.parseDateToString(activite.getDateDebut()))
                    .append(" au ").append(GestionDates.parseDateToString(activite.getDateFin()))
                    .append(")");
            JRadioButton radioButton = new JRadioButton(activiteBuilder.toString());
            radioButton.setActionCommand(activite.getNom());
            activitePanel.add(radioButton);
            buttonGroup.add(radioButton);
        }

        scrollPaneListeActivites = new JScrollPane(activitePanel);
        scrollPaneListeActivites.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Récupère la liste des robots associés à l'utilisateur actuel (identifié par son pseudo)
     * à partir du contrôleur d'utilisateurs et configure l'interface pour afficher les robots
     * sous forme de cases à cocher permettant la sélection.
     */
    private void recupererListeRobot(){
        listeRobot = controlleurUtilisateurs.recupererListeRobot(pseudo);

        JPanel robotPanel = new JPanel();
        robotPanel.setLayout(new BoxLayout(robotPanel, BoxLayout.Y_AXIS));

        for (Robot robot: listeRobot) {
            JCheckBox checkBox = new JCheckBox(robot.getNom());
            checkBox.setSelected(false);
            robotPanel.add(checkBox);
        }

        scrollPaneRobot = new JScrollPane(robotPanel);
        scrollPaneRobot.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Affiche le panneau principal de l'interface graphique dans la fenêtre spécifiée.
     *
     * @param jFrame La fenêtre dans laquelle afficher le panneau principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu de la fenêtre en révalidant et en redessinant ses composants.
     * Cela permet d'assurer que les modifications apportées à la fenêtre sont correctement affichées.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Réagit au clic sur le bouton de création d'activité en vérifiant les données saisies,
     * en effectuant des vérifications et en exécutant la création de l'activité si les conditions sont remplies.
     *
     * @param btnCreation Le bouton de création d'activité.
     * @param nomActiviteField Le champ de texte contenant le nom de l'activité.
     * @param dateDebutField Le champ de texte contenant la date de début de l'activité.
     * @param dateFinField Le champ de texte contenant la date de fin de l'activité.
     * @param interetField Le champ de texte contenant les intérêts liés à l'activité (séparés par des virgules).
     */
    public void onBtnActiviteCreeeClicked(JButton btnCreation, JTextField nomActiviteField, JTextField dateDebutField,
                                          JTextField dateFinField, JTextField interetField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder messageErreurBuilder = new StringBuilder();
                messageErreurBuilder.append("Erreur:\n");
                String dateDebut = dateDebutField.getText();
                String dateFin = dateFinField.getText();
                ArrayList<String> listeInterets = new ArrayList<>(Arrays.asList(interetField.getText().split(",")));
                if (!GestionDates.validerDate(dateDebut) || !GestionDates.validerDate(dateFin)){
                    messageErreurBuilder.append("Reverifier le format des dates entrees.\n");
                    afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                } else {
                    if (!GestionDates.veriferSiDateRealiste(dateDebut)){
                        messageErreurBuilder.append("La date de debut de l'activitee doit commencer au moins au mois aujoud'hui." +
                                "\nNous ne pouvons malheureusement pas encore voyager dans le temps donc veuillez patienter\n" +
                                "jusqu'a ce que cette fonctionnalitee soit disponible un jour :)\n");
                        afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                    } else {
                        if (!GestionDates.verifierCoherenceDate(dateDebut, dateFin)){
                            messageErreurBuilder.append("La date de debut doit commencer avant la date de fin.\n");
                            afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                        } else {
                            if (listeTaches.isEmpty() || listeInterets.get(0).isEmpty()){
                                messageErreurBuilder.append("Vous devez rajouter au moins un interet.\n");
                                afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                            } else {
                                JPanel tachesPanel = (JPanel) scrollPaneListeTaches.getViewport().getView();
                                Component[] taches = tachesPanel.getComponents();
                                ArrayList<String> tachesChoisies = new ArrayList<>();

                                for (Component tache: taches) {
                                    if (tache instanceof JCheckBox cb && cb.isSelected()){
                                        tachesChoisies.add(cb.getText());
                                    }
                                }

                                try {
                                    if (controlleurUtilisateurs.creerActivites(pseudo, nomActiviteField.getText(), dateDebut, dateFin, tachesChoisies, listeInterets)){
                                        confirmerActiviteCreee();
                                    } else {
                                        messageErreurBuilder.append("L'activite que vous tentez de creer existe deja.");
                                        afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                                    }
                                } catch (ParseException ex) {
                                    messageErreurBuilder.setLength(0);
                                    messageErreurBuilder.append("Une erreur s'est produite. Deconnectez-vous puis reconnectez-vous " +
                                            "et assurez vous que les dates sont bien ecrites.\n");
                                    afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                                }

                            }
                        }
                    }
                }

            }
        });
    }

    /**
     * Réagit au clic sur le bouton de rejoindre une activité en vérifiant les robots sélectionnés
     * et l'activité choisie, puis effectue les actions correspondantes.
     *
     * @param btnCreation Le bouton de rejoindre une activité.
     */
    public void onBtnRejoindreActiviteClicked(JButton btnCreation) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel robotPanel = (JPanel) scrollPaneRobot.getViewport().getView();
                Component[] robots = robotPanel.getComponents();
                ArrayList<String> robotChoisie = new ArrayList<>();

                for (Component robot: robots) {
                    if (robot instanceof JCheckBox cb && cb.isSelected()){
                        robotChoisie.add(cb.getText());
                    }
                }

                JPanel activitePanel = (JPanel) scrollPaneListeActivites.getViewport().getView();
                Component[] activites = activitePanel.getComponents();
                String activiteChoisie = "";

                for (Component activiteButton: activites) {
                    if (activiteButton instanceof JRadioButton rb && rb.isSelected()){
                        activiteChoisie = rb.getActionCommand();
                        break;
                    }
                }

                if (robotChoisie.isEmpty() || activiteChoisie.isEmpty()){
                    afficherMessageErreurRejoindreActivite();
                } else {
                    String result = controlleurUtilisateurs.rejoindreActivite(pseudo, activiteChoisie);
                    if (result.equals("true")){
                        for (String nomRobot: robotChoisie) {
                            ArrayList<String> numerosSeries = new ArrayList<>(Collections.singletonList(controlleurUtilisateurs.retrouverRobotNom(nomRobot, pseudo).getNumeroSerie().toString()));
                            controlleurUtilisateurs.ajouterRobotActivite(numerosSeries ,activiteChoisie, pseudo);
                        }
                        confirmerRejoindreActivite("Vous avez rejoint l'activite avec succes.");
                    } else if (result.equals("true2")){
                        confirmerRejoindreActivite("Vous avez deja rejoint l'activitee.");
                    } else {
                        afficherMessageErreurRejoindreActivite();
                    }
                }
            }
        });
    }

    /**
     * Réagit au clic sur le bouton "Annuler" en ramenant le contenu de la fenêtre
     * au panneau principal, puis en mettant à jour la fenêtre.
     *
     * @param btnAnnuler Le bouton "Annuler" sur lequel l'action est déclenchée.
     */
    public void onBtnAnnulerClicked(JButton btnAnnuler) {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Affiche une fenêtre de confirmation indiquant que l'activité a été créée avec succès.
     * Cette méthode change également le contenu de la fenêtre vers le panneau principal et
     * met à jour la fenêtre.
     */
    public void confirmerActiviteCreee() {
        String message = "L'activitée a été bien créée";
        String title = "Activite creee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une fenêtre d'erreur avec le message spécifié lorsqu'il y a une erreur lors de la création d'une activité.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurActiviteCreee(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une fenêtre de confirmation indiquant que l'utilisateur a rejoint une activité avec succès.
     * Cette méthode change également le contenu de la fenêtre vers le panneau principal et met à jour la fenêtre.
     *
     * @param message Le message de confirmation à afficher.
     */
    public void confirmerRejoindreActivite(String message) {
        String title = "Joindre activite reussi";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une fenêtre d'erreur indiquant que l'opération de rejoindre une activité a échoué
     * et suggérant de réessayer.
     */
    public void afficherMessageErreurRejoindreActivite() {
        String message = "Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
