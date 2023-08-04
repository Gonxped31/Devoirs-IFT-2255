package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Action;
import domain.logic.Robot.Robot;
import domain.logic.Robot.Tache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * La classe `GestionTachesGUI` représente l'interface utilisateur graphique pour la gestion des tâches.
 * Elle permet aux utilisateurs de créer et d'allouer des tâches aux robots.
 * La classe propose des méthodes pour configurer le panneau principal, le panneau de création de tâches et le panneau d'allocation de tâches,
 * ainsi que pour gérer les actions de l'utilisateur sur les composants de l'interface.
 * <p>
 * Cette classe utilise des composants Swing pour créer l'interface utilisateur et interagit avec la classe `ControlleurUtilisateurs`
 * pour gérer les tâches, les actions et les robots liés à un utilisateur spécifique.
 */
public class GestionTachesGUI {
    /**
     * Le contrôleur pour gérer les opérations liées à l'utilisateur.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Le nom d'utilisateur (pseudo) de l'utilisateur pour lequel la gestion des tâches est effectuée.
     */
    private String pseudo;
    /**
     * Le JFrame pour afficher la fenêtre principale de gestion des tâches.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Le panneau principal qui contient les éléments de l'interface utilisateur pour la gestion des tâches.
     * Il utilise une disposition GridLayout pour disposer les composants verticalement dans une seule colonne.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
     /**
     * Le panneau pour créer une nouvelle tâche.
     * Il utilise un GridBagLayout pour un placement flexible des composants.
     */
    private JPanel creerTachePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau pour allouer une tâche à un robot.
     * Il utilise un GridBagLayout pour un placement flexible des composants.
     */
    private JPanel allouerTachePanel = new JPanel(new GridBagLayout());
    /**
     * Le label affichant le titre de la section de gestion des tâches.
     * Le label est centré en utilisant SwingConstants.CENTER.
     */
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches", SwingConstants.CENTER);
    /**
     * Le bouton pour créer une nouvelle tâche.
     */
    private JButton btnCreerTache = new JButton("Creer une tache");
    /**
     * Le bouton pour allouer une tâche à un robot.
     */
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    /**
     * Le bouton pour retourner au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Une référence au conteneur précédent avant de passer à ce panneau de gestion des tâches.
     */
    private Container panelPrecedent = new Container();
    /**
     * Le panneau de défilement utilisé dans le panneau de création de tâche pour permettre le défilement s'il y a de nombreuses actions à choisir.
     */
    private JScrollPane scrollPaneCreerTache;
    /**
     * Le panneau de défilement utilisé dans le panneau d'allocation de tâche pour permettre le défilement s'il y a de nombreux robots à choisir.
     */
    private JScrollPane scrollPaneAllouerTache;
    /**
     * Le panneau de défilement utilisé dans le panneau d'allocation de tâche pour permettre le défilement s'il y a de nombreuses tâches à choisir.
     */
    private JScrollPane scrollPaneListeTaches;
    /**
     * La liste des robots disponibles pour l'allocation des tâches.
     */
    private ArrayList<Robot> listeRobot;
    /**
     * La liste des tâches disponibles pour l'allocation.
     */
    private ArrayList<Tache> listeTaches;
    /**
     * Classe qui definit la maniere dont les composants seront places dans un panel
     */
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Constructeur de la classe GestionTachesGUI.
     * Initialise l'objet avec le nom d'utilisateur (pseudo) fourni et configure le panneau principal,
     * le panneau de création de tâche et le panneau d'allocation de tâche. Il ajoute également des écouteurs d'action
     * aux boutons pour gérer leurs actions respectives.
     *
     * @param pseudo Le nom d'utilisateur (pseudo) de l'utilisateur pour lequel la gestion des tâches est effectuée.
     * @throws IOException Si une erreur d'E/S se produit lors de la configuration des panneaux.
     * @throws ParseException Si une erreur d'analyse se produit lors de la configuration des panneaux.
     */
    public GestionTachesGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
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

    /**
     * Configure le panneau principal de l'interface "Gestion de mes tâches".
     * Configure l'apparence et la mise en page du panneau principal et ajoute des composants tels que des étiquettes et des boutons.
     * Cette méthode est responsable de la configuration de la mise en page, de la police, des bordures du panneau principal et de l'ajout de composants.
     * Le mainPanel est utilisé pour afficher les options principales de gestion des tâches.
     */
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

    /**
     * Configure le panneau pour la création d'une nouvelle tâche.
     * Configure l'apparence et la mise en page du panneau de création de tâche et ajoute des composants tels que des étiquettes, des champs de texte et des boutons.
     * Cette méthode peuple le panneau avec des composants pour entrer le nom de la tâche et sélectionner les actions à ajouter à la tâche.
     * Le panneau contient des étiquettes pour le nom de la tâche et la sélection d'actions, un champ de texte pour entrer le nom de la tâche et un volet de défilement pour afficher la liste des actions disponibles.
     * L'utilisateur peut entrer un nom de tâche, sélectionner des actions dans la liste disponible et confirmer la création de la tâche.
     */
    public void setCreerTachePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel tacheCreeeLabel = new JLabel("Inscrivez le nom de la tache a creee");
        JLabel listActionLabel = new JLabel("Choisissez les actions a ajouter");
        recupererListeActions();
        JTextField tacheCreeeField = new JTextField();
        JButton btnCreation = new JButton("Confirmer la creation de la tache");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        tacheCreeeField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        creerTachePanel.add(tacheCreeeLabel, constraints);
        constraints.gridy = 1;
        creerTachePanel.add(tacheCreeeField, constraints);
        constraints.gridy = 2;
        creerTachePanel.add(listActionLabel, constraints);
        constraints.gridy = 3;
        creerTachePanel.add(scrollPaneCreerTache, constraints);
        constraints.gridy = 4;
        creerTachePanel.add(btnCreation, constraints);
        constraints.gridy = 5;
        creerTachePanel.add(btnAnnuler, constraints);

        onBtnTacheCreeeClicked(btnCreation, tacheCreeeField);
        onBtnAnnulerClicked(btnAnnuler);
    }


    /**
     * Récupère la liste des actions disponibles pour l'utilisateur à partir du contrôleur d'utilisateurs.
     * Crée un panneau contenant des cases à cocher pour chaque action et l'ajoute à la fenêtre de création de tâche.
     */
    private void recupererListeActions() {
        ArrayList<Action> listeAcction = controlleurUtilisateurs.recuprerListeAction(pseudo);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        for (Action action: listeAcction) {
            JCheckBox checkBox = new JCheckBox(action.getNomAction());
            checkBox.setSelected(false);
            actionsPanel.add(checkBox);
        }

        scrollPaneCreerTache = new JScrollPane(actionsPanel);
        scrollPaneCreerTache.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Configure le panneau pour l'allocation des tâches aux robots.
     * Configure l'apparence et la mise en page du panneau d'allocation et ajoute des composants tels que des étiquettes, des volets de défilement et des boutons.
     * Cette méthode peuple le panneau avec une liste de robots et de tâches disponibles pour l'allocation.
     * Le panneau contient des étiquettes pour la sélection de robots et de tâches, des volets de défilement pour afficher les listes et des boutons pour la confirmation et l'annulation.
     * L'utilisateur peut choisir un robot et une tâche dans les listes disponibles et confirmer l'allocation.
     */
    public void setAllouerTachePanel() {
        JLabel nomRobotLabel = new JLabel("Choisissez a quel robot la tache sera allouee");
        recupererListeRobot();
        JLabel nomTacheLabel = new JLabel("Choisissez la tache a allouer aux robots");
        recupererListeTache();
        JButton btnAffectation = new JButton("Confirmer l'affectation de la tache");
        JButton btnAnnuler = new JButton("Annuler");

        constraints.gridy = 0;
        allouerTachePanel.add(nomRobotLabel, constraints);
        constraints.gridy = 1;
        allouerTachePanel.add(scrollPaneAllouerTache, constraints);
        constraints.gridy = 2;
        allouerTachePanel.add(nomTacheLabel, constraints);
        constraints.gridy = 3;
        allouerTachePanel.add(scrollPaneListeTaches, constraints);
        constraints.gridy = 4;
        allouerTachePanel.add(btnAffectation, constraints);
        constraints.gridy = 5;
        allouerTachePanel.add(btnAnnuler, constraints);

        onBtnTacheAlloueClicked(btnAffectation);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Récupère la liste des tâches de l'utilisateur à partir du contrôleur d'utilisateurs.
     * Crée un panneau contenant des boutons radio pour chaque tâche et l'ajoute à la fenêtre d'allocation de tâche.
     */
    private void recupererListeTache() {
        listeTaches = controlleurUtilisateurs.recuprerListeTache(pseudo);
        JPanel tachePanel = new JPanel();
        tachePanel.setLayout(new BoxLayout(tachePanel, BoxLayout.Y_AXIS));

        ButtonGroup buttonGroup = new ButtonGroup();

        for (Tache tache: listeTaches) {
            String nomTache = tache.getNom();
            JRadioButton radioButton = new JRadioButton(nomTache);
            radioButton.setActionCommand(nomTache);
            tachePanel.add(radioButton);
            buttonGroup.add(radioButton);
        }
        scrollPaneListeTaches = new JScrollPane(tachePanel);
        scrollPaneListeTaches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Récupère la liste des robots de l'utilisateur à partir du contrôleur d'utilisateurs.
     * Crée un panneau contenant des cases à cocher pour chaque robot et l'ajoute à la fenêtre d'allocation de tâche.
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

        scrollPaneAllouerTache = new JScrollPane(robotPanel);
        scrollPaneAllouerTache.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Affiche le panneau principal pour la gestion des tâches dans le JFrame fourni.
     * Définit le panneau précédent (si présent) comme panelPrecedent, puis définit le mainPanel comme le contentPane du JFrame fourni.
     * Appelle mettreAJourFrame() pour actualiser le JFrame.
     * @param jFrame Le JFrame dans lequel afficher le panneau principal de gestion des tâches.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Rafraîchit le JFrame actuel en révalidant ses composants et en les redessinant.
     * Cette méthode est appelée après avoir changé le contentPane du JFrame pour mettre à jour son affichage.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Met en place l'écouteur d'événements pour le bouton "Confirmer la création de la tâche".
     * Lorsque le bouton est cliqué, cette méthode vérifie les actions sélectionnées et le nom de la tâche saisi.
     * Si le nom de la tâche ou les actions sélectionnées sont vides, elle affiche un message d'erreur.
     * Sinon, elle appelle la méthode "creerTache" du "controlleurUtilisateurs" pour créer une nouvelle tâche avec les actions sélectionnées et le nom de la tâche fourni.
     * Ensuite, elle affiche un message de confirmation pour indiquer la création réussie de la tâche.
     * Si des erreurs surviennent pendant la création de la tâche, elle affiche un message d'erreur.
     * @param btnCreation Le bouton "Confirmer la création de la tâche" pour lequel l'écouteur d'événements doit être configuré.
     * @param tacheCreeeField Le champ de texte pour saisir le nom de la tâche à créer.
     */
    public void onBtnTacheCreeeClicked(JButton btnCreation, JTextField tacheCreeeField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel actionsPanel = (JPanel) scrollPaneCreerTache.getViewport().getView();
                Component[] checkBoxes = actionsPanel.getComponents();
                ArrayList<String> selectedActions = new ArrayList<>();

                for (Component checkBox: checkBoxes) {
                    if (checkBox instanceof JCheckBox cb && cb.isSelected()){
                        selectedActions.add(cb.getText());
                    }
                }

                if (tacheCreeeField.getText().isEmpty() || selectedActions.isEmpty())
                    afficherMessageErreurTacheCreee();
                else{
                    controlleurUtilisateurs.creerTache(tacheCreeeField.getText(), selectedActions, pseudo);
                    confirmerTacheCreee();
                }
            }
        });
    }

    /**
     * Met en place l'écouteur d'événements pour le bouton "Confirmer l'affectation de la tâche".
     * Lorsque le bouton est cliqué, cette méthode récupère les robots sélectionnés et la tâche choisie.
     * Si aucun robot ou aucune tâche n'est sélectionné, elle affiche un message d'erreur.
     * Sinon, elle parcourt les robots sélectionnés, trouve les objets Robot correspondants en utilisant la méthode "retrouverRobotNom" de "controlleurUtilisateurs",
     * et attribue la tâche choisie à chacun des robots sélectionnés à l'aide de la méthode "allouerTacheRobot" de "controlleurUtilisateurs".
     * Après l'attribution de la tâche, elle affiche un message de confirmation pour indiquer l'affectation réussie de la tâche.
     * Si des erreurs surviennent pendant l'affectation de la tâche, elle affiche un message d'erreur.
     * @param btnAffectation Le bouton "Confirmer l'affectation de la tâche" pour lequel l'écouteur d'événements doit être configuré.
     */
    public void onBtnTacheAlloueClicked(JButton btnAffectation) {
        btnAffectation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel robotPanel = (JPanel) scrollPaneAllouerTache.getViewport().getView();
                Component[] robots = robotPanel.getComponents();
                ArrayList<String> robotChoisie = new ArrayList<>();

                for (Component robot: robots) {
                    if (robot instanceof JCheckBox cb && cb.isSelected()){
                        robotChoisie.add(cb.getText());
                    }
                }

                JPanel tachePanel = (JPanel) scrollPaneListeTaches.getViewport().getView();
                Component[] taches = tachePanel.getComponents();
                String tacheChoisie = "";

                for (Component tacheButton: taches) {
                    if (tacheButton instanceof JRadioButton rb && rb.isSelected()){
                        tacheChoisie = rb.getActionCommand();
                        break;
                    }
                }

                if (robotChoisie.isEmpty() || tacheChoisie.isEmpty()){
                    afficherMessageErreurTacheAlloue();
                } else {
                    for (String nomRobot: robotChoisie) {
                        Robot robot = controlleurUtilisateurs.retrouverRobotNom(nomRobot, pseudo);
                        if (robot != null){
                            controlleurUtilisateurs.allouerTacheRobot(pseudo, robot.getNumeroSerie().toString(), tacheChoisie);
                        }
                    }
                    confirmerTacheAlloue();
                }
            }
        });
    }

    /**
     * Met en place l'écouteur d'événements pour le bouton "Annuler".
     * Lorsque le bouton est cliqué, cette méthode définit le mainPanel comme le contentPane du JFrame et appelle mettreAJourFrame() pour rafraîchir le JFrame.
     * Cette méthode est utilisée pour revenir au panneau principal lorsque le bouton "Annuler" est cliqué sur d'autres panneaux.
     * @param btnAnnuler Le bouton "Annuler" pour lequel l'écouteur d'événements doit être configuré.
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
     * Affiche un message de confirmation pour indiquer la création réussie d'une tâche.
     * Le message contient un message de réussite et un titre pour la boîte de dialogue de confirmation.
     * Il définit le mainPanel comme le contentPane du JFrame et appelle mettreAJourFrame() pour rafraîchir le JFrame.
     */
    public void confirmerTacheCreee() {
        String message = "Vous avez creer la tache avec succes !";
        String title = "Creation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur indiquant qu'il y a eu un problème lors de la création d'une tâche.
     * Le message d'erreur contient des informations sur le problème, telles que des champs vides.
     * Il définit le mainPanel comme le contentPane du JFrame et appelle mettreAJourFrame() pour rafraîchir le JFrame.
     */
    public void afficherMessageErreurTacheCreee() {
        String message = "Une erreur s'est produite. Verifier que vous avez bien rempli les champ.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche un message de confirmation indiquant que l'allocation d'une tâche aux robots a réussi.
     * Le message de confirmation contient un message de succès et un titre pour la boîte de dialogue de confirmation.
     * Il définit le mainPanel comme le contentPane du JFrame et appelle mettreAJourFrame() pour rafraîchir le JFrame.
     */
    public void confirmerTacheAlloue() {
        String message = "La tache a ete allouee avec succes";
        String title = "Affectation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur indiquant qu'il y a eu un problème lors de l'allocation d'une tâche aux robots.
     * Le message d'erreur contient des informations sur le problème, comme ne pas avoir le robot ou la tâche requis.
     * Il définit le mainPanel comme le contentPane du JFrame et appelle mettreAJourFrame() pour rafraîchir le JFrame.
     */
    public void afficherMessageErreurTacheAlloue() {
        String message = "La tache n'a pas pu être allouee car vous ne possedez pas le robot ou la tache indique. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
