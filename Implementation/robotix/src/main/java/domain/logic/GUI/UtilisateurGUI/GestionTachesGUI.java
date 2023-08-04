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
 * The `GestionTachesGUI` class represents the graphical user interface for task management.
 * It allows users to create and allocate tasks to robots.
 * The class provides methods for setting up the main panel, create task panel, and allocate task panel,
 * as well as handling user actions on the interface components.
 * <p>
 * This class uses Swing components to create the user interface and interacts with the `ControlleurUtilisateurs` class
 * to manage tasks, actions, and robots related to a specific user.
 */
public class GestionTachesGUI {
    /**
     * The controller for managing user-related operations.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * The username (pseudo) of the user for whom the task management is being done.
     */
    private String pseudo;
    /**
     * The JFrame to display the main window for task management.
     */
    private JFrame jFrame = new JFrame();
    /**
     * The main panel that contains the user interface elements for task management.
     * It uses a GridLayout to arrange components vertically in a single column.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * The panel for creating a new task.
     * It uses a GridBagLayout for flexible component placement.
     */
    private JPanel creerTachePanel = new JPanel(new GridBagLayout());
    /**
     * The panel for allocating a task to a robot.
     * It uses a GridBagLayout for flexible component placement.
     */
    private JPanel allouerTachePanel = new JPanel(new GridBagLayout());
    /**
     * The label displaying the title of the task management section.
     * The label is centered using SwingConstants.CENTER.
     */
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches", SwingConstants.CENTER);
    /**
     * The button for creating a new task.
     */
    private JButton btnCreerTache = new JButton("Creer une tache");
    /**
     * The button for allocating a task to a robot.
     */
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    /**
     * The button for returning to the user menu.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * A reference to the previous container before switching to this task management panel.
     */
    private Container panelPrecedent = new Container();
    /**
     * The scroll pane used in the create task panel to allow scrolling if there are many actions to choose from.
     */
    private JScrollPane scrollPaneCreerTache;
    /**
     * The scroll pane used in the allocate task panel to allow scrolling if there are many robots to choose from.
     */
    private JScrollPane scrollPaneAllouerTache;
    /**
     * The scroll pane used in the allocate task panel to allow scrolling if there are many tasks to choose from.
     */
    private JScrollPane scrollPaneListeTaches;
    /**
     * The list of robots available for task allocation.
     */
    private ArrayList<Robot> listeRobot;
    /**
     * The list of tasks available for allocation.
     */
    private ArrayList<Tache> listeTaches;
    /**
     * Classe qui definit la maniere dont les composants seront places dans un panel
     */
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Constructor for GestionTachesGUI class.
     * Initializes the object with the provided username (pseudo) and sets up the main panel,
     * create task panel, and allocate task panel. It also adds action listeners to the buttons
     * to handle their respective actions.
     *
     * @param pseudo The username (pseudo) of the user for whom the task management is being done.
     * @throws IOException If an I/O error occurs while setting up the panels.
     * @throws ParseException If a parsing error occurs while setting up the panels.
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
     * Sets up the main panel for the "Gestion de mes taches" (Task Management) interface.
     * Configures the appearance and layout of the main panel and adds components such as labels and buttons.
     * This method is responsible for setting the main panel's layout, font, borders, and adding components to it.
     * The mainPanel is used to display the main options for task management.
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
     * Sets up the panel for creating a new task.
     * Configures the appearance and layout of the task creation panel and adds components such as labels, text fields, and buttons.
     * This method populates the panel with components for entering the task name and selecting actions to be added to the task.
     * The panel contains labels for task name and action selection, a text field for entering the task name, and a scroll pane to display the list of available actions.
     * The user can enter a task name, select actions from the available list, and confirm the creation of the task.
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
     * Sets up the panel for allocating tasks to robots.
     * Configures the appearance and layout of the allocation panel and adds components such as labels, scroll panes, and buttons.
     * This method populates the panel with a list of available robots and tasks for allocation.
     * The panel contains labels for robot and task selection, scroll panes to display lists, and buttons for confirmation and cancellation.
     * The user can choose a robot and a task from the available lists and confirm the allocation.
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
     * Displays the main panel for managing tasks in the provided JFrame.
     * Sets the previous panel (if any) as the panelPrecedent, then sets the mainPanel as the contentPane of the provided JFrame.
     * Calls mettreAJourFrame() to refresh the JFrame.
     * @param jFrame The JFrame in which to display the main panel for task management.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Refreshes the current JFrame by revalidating its components and repainting them.
     * This method is called after changing the contentPane of the JFrame to update its display.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Sets up the action listener for the "Confirmer la creation de la tache" button.
     * When the button is clicked, this method checks for selected actions and the entered task name.
     * If the task name or the selected actions are empty, it displays an error message.
     * Otherwise, it calls the "creerTache" method of the "controlleurUtilisateurs" to create a new task with the selected actions and the provided task name.
     * It then displays a confirmation message to indicate the successful creation of the task.
     * If any errors occur during task creation, it displays an error message.
     * @param btnCreation The "Confirmer la creation de la tache" button to set up the action listener for.
     * @param tacheCreeeField The text field for entering the name of the task to be created.
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
     * Sets up the action listener for the "Confirmer l'affectation de la tache" button.
     * When the button is clicked, this method retrieves the selected robots and the chosen task.
     * If no robots or task are selected, it displays an error message.
     * Otherwise, it iterates through the selected robots, finds the corresponding Robot objects using the "retrouverRobotNom" method of "controlleurUtilisateurs",
     * and allocates the chosen task to each of the selected robots using the "allouerTacheRobot" method of "controlleurUtilisateurs".
     * After the task allocation, it displays a confirmation message to indicate the successful allocation of the task.
     * If any errors occur during the task allocation, it displays an error message.
     * @param btnAffectation The "Confirmer l'affectation de la tache" button to set up the action listener for.
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
     * Sets up the action listener for the "Annuler" button.
     * When the button is clicked, this method sets the mainPanel as the contentPane of the JFrame and calls mettreAJourFrame() to refresh the JFrame.
     * This method is used to go back to the main panel when the "Annuler" button is clicked on other panels.
     * @param btnAnnuler The "Annuler" button to set up the action listener for.
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
     * Displays a confirmation message to indicate the successful creation of a task.
     * The message contains a success message and a title for the confirmation dialog.
     * It sets the mainPanel as the contentPane of the JFrame and calls mettreAJourFrame() to refresh the JFrame.
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
     * Displays an error message indicating that there was an issue while creating a task.
     * The error message contains information about the issue, such as empty fields.
     * It sets the mainPanel as the contentPane of the JFrame and calls mettreAJourFrame() to refresh the JFrame.
     */
    public void afficherMessageErreurTacheCreee() {
        String message = "Une erreur s'est produite. Verifier que vous avez bien rempli les champ.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Displays a confirmation message to indicate the successful allocation of a task to robots.
     * The message contains a success message and a title for the confirmation dialog.
     * It sets the mainPanel as the contentPane of the JFrame and calls mettreAJourFrame() to refresh the JFrame.
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
     * Displays an error message indicating that there was an issue while allocating a task to robots.
     * The error message contains information about the issue, such as not having the required robot or task.
     * It sets the mainPanel as the contentPane of the JFrame and calls mettreAJourFrame() to refresh the JFrame.
     */
    public void afficherMessageErreurTacheAlloue() {
        String message = "La tache n'a pas pu être allouee car vous ne possedez pas le robot ou la tache indique. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
