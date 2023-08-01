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

public class GestionTachesGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private String pseudo;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel creerTachePanel = new JPanel(new GridBagLayout());
    private JPanel allouerTachePanel = new JPanel(new GridBagLayout());
    private JLabel gestionTachesLabel = new JLabel("Gestion de mes taches", SwingConstants.CENTER);
    private JButton btnCreerTache = new JButton("Creer une tache");
    private JButton btnAllouerTache = new JButton("Allouer une tache a un robot");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private JScrollPane scrollPaneCreerTache;
    private JScrollPane scrollPaneAllouerTache;
    private JScrollPane scrollPaneListeTaches;
    private ArrayList<Robot> listeRobot;
    private ArrayList<Tache> listeTaches;
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

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
                        Robot robot = controlleurUtilisateurs.retrouverRobot(nomRobot, pseudo);
                        if (robot != null){
                            controlleurUtilisateurs.allouerTacheRobot(pseudo, robot.getNumeroSerie().toString(), tacheChoisie);
                        }
                    }
                    confirmerTacheAlloue();
                }
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
        String message = "Vous avez creer la tache avec succes !";
        String title = "Creation terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurTacheCreee() {
        String message = "Une erreur s'est produite. Verifier que vous avez bien rempli les champ.";
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
