package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Fournisseur;
import domain.logic.Outils.ComboBoxRenderer;
import domain.logic.Outils.EmailSender;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import domain.logic.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

public class AchatsGUI {
    private String pseudo;
    private EmailSender emailSender;
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private DbControleur dbControleur = DbControleur.getDbControleur();
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel achatRobotPanel = new JPanel(new GridBagLayout());
    private JPanel achatComposantePanel = new JPanel(new GridBagLayout());
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?", SwingConstants.CENTER);
    private JButton btnAchatRobot = new JButton("Robot");
    private JButton btnAchatComposante = new JButton("Composante");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private ArrayList<Fournisseur> listeFournisseur;
    private JList<String> listeFournisseurJlistRobot;
    private JList<String> listeFournisseurJlistComposante;
    private JScrollPane scrollPaneRobot;
    private JScrollPane scrollPaneComposante;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public AchatsGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
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
        JLabel listeFournisseursLabel = new JLabel("Voici la liste des fournisseurs. Cliquez sur un fournisseur pour voir ses détails.");
        recupererListeFournisseur("robot");
        JLabel nomFournisseurLabel = new JLabel("Entrez le nom du fournisseur qui vous interresse");
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
        achatRobotPanel.add(scrollPaneRobot, constraints);
        constraints.gridy = 2;
        achatRobotPanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 3;
        achatRobotPanel.add(nomFournisseurField, constraints);
        constraints.gridy = 4;
        achatRobotPanel.add(numeroRobotLabel, constraints);
        constraints.gridy = 5;
        achatRobotPanel.add(numeroRobotField, constraints);
        constraints.gridy = 6;
        achatRobotPanel.add(btnConfirmerAchat, constraints);
        constraints.gridy = 7;
        achatRobotPanel.add(btnAnnuler, constraints);

        onBtnConfirmerAchatClicked(btnConfirmerAchat, nomFournisseurField, numeroRobotField, "robot");
        onBtnAnnulerClicked(btnAnnuler);
    }

    private void recupererListeFournisseur(String choix) {
        listeFournisseur = dbControleur.recupererListFournisseur();
        switch (choix.toLowerCase()) {
            case "robot" -> {
                listeFournisseurJlistRobot = new JList<>(listeFournisseur.stream().map(Fournisseur::getNom).toArray(String[]::new));
                listeFournisseurJlistRobot.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listeFournisseurJlistRobot.setVisibleRowCount(5);
                listeFournisseurJlistRobot.setCellRenderer(new ComboBoxRenderer());
                listeFournisseurJlistRobot.addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        int selectedIndex = listeFournisseurJlistRobot.getSelectedIndex();
                        if (selectedIndex >= 0 && selectedIndex < listeFournisseur.size()) {
                            Fournisseur selectedSupplier = listeFournisseur.get(selectedIndex);
                            detailsFournisseur(selectedSupplier, "robot");
                            listeFournisseurJlistRobot.clearSelection(); // Deselect the item after showing the details
                        }
                    }
                });

                scrollPaneRobot = new JScrollPane(listeFournisseurJlistRobot);
                scrollPaneRobot.setPreferredSize(new Dimension(200, 150));
                scrollPaneRobot.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            }
            case "composante" -> {
                listeFournisseurJlistComposante = new JList<>(listeFournisseur.stream().map(Fournisseur::getNom).toArray(String[]::new));
                listeFournisseurJlistComposante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listeFournisseurJlistComposante.setVisibleRowCount(5);
                listeFournisseurJlistComposante.setCellRenderer(new ComboBoxRenderer());
                listeFournisseurJlistComposante.addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        int selectedIndex = listeFournisseurJlistComposante.getSelectedIndex();
                        if (selectedIndex >= 0 && selectedIndex < listeFournisseur.size()) {
                            Fournisseur selectedSupplier = listeFournisseur.get(selectedIndex);
                            detailsFournisseur(selectedSupplier, "composante");
                            listeFournisseurJlistComposante.clearSelection(); // Deselect the item after showing the details
                        }
                    }
                });
                scrollPaneComposante = new JScrollPane(listeFournisseurJlistComposante);
                scrollPaneComposante.setPreferredSize(new Dimension(200, 150));
                scrollPaneComposante.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            }
        }
    }
    private void detailsFournisseur(Fournisseur fournisseur, String choix) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nom: ").append(fournisseur.getNom()).append("\n");
        messageBuilder.append("Addresse: ").append(fournisseur.getAdresse()).append("\n");
        messageBuilder.append("Telephone: ").append(fournisseur.getTelephone()).append("\n");
        messageBuilder.append("Type de robot fabriqué: ").append(fournisseur.getTypeRobotFabriquer()).append("\n");
        messageBuilder.append("Type de composantes fabriqués: ").append(fournisseur.getTypeComposantesFabriquer()).append("\n");
        messageBuilder.append("Capacité de production: ").append(fournisseur.getCapaciteProductionComposantes()).append("\n");
        messageBuilder.append("Nom de la compagnie: ").append(fournisseur.getNomCompagnie()).append("\n");
        switch (choix.toLowerCase()){
            case "robot" -> {
                messageBuilder.append("Nombre de robot disponible : ").append(fournisseur.getInventaireDeRobot().size()).append("\n");
                LinkedList<Robot> robots = fournisseur.getInventaireDeRobot();
                if (!robots.isEmpty()){
                    messageBuilder.append("Liste des robots : ").append("\n");
                    for (Robot robot : robots) {
                        messageBuilder.append("Robot ").append(robot.getNumero()).append(": ").append("CPU - ").append(robot.getCpu()).append(", Mémoire - ").append(robot.getMemoire()).append("\n");
                    }
                } else {
                    messageBuilder.append("Aucun robot vendu actuellement.").append("\n");
                }
            }

            case "composante" -> {
                messageBuilder.append("Nombre de composante disponible :").append(fournisseur.getInventaireComposant().size()).append("\n");
                LinkedList<Composant> composants = fournisseur.getInventaireComposant();
                if (!composants.isEmpty()){
                    messageBuilder.append("Liste des composantes : ").append("\n");
                    for (Composant composant :composants) {
                        messageBuilder.append(composant.getNom()).append(": ").append("Type - ").append(composant.getTypeComposant())
                                .append(" Prix - ").append(composant.getPrix()).append(" Description - ").append(composant.getDescription()).append("\n");
                    }
                } else {
                    messageBuilder.append("Aucune comopsante vendu actuellement.").append("\n");
                }
            }
        }

        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details du fournisseur", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setAchatComposantePanel() {
        JLabel listeFournisseursLabel = new JLabel("Voici la liste des fournisseurs. Cliquez sur un fournisseur pour voir ses détails.");
        recupererListeFournisseur("composante");
        JLabel nomFournisseurLabel = new JLabel("Entrez le nom du fournisseur qui vous interresse");
        JLabel numeroComposanteLabel = new JLabel("Entrez le nom de la composante a acheter");
        JTextField nomFournisseurField = new JTextField();
        JTextField nomComposanteField = new JTextField();
        JButton btnConfirmerAchat = new JButton("Confirmer l'achat de la composante");
        JButton btnAnnuler = new JButton("Annuler");

        nomFournisseurField.setPreferredSize(new Dimension(200, 30));
        nomComposanteField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        achatComposantePanel.add(listeFournisseursLabel, constraints);
        constraints.gridy = 1;
        achatComposantePanel.add(scrollPaneComposante, constraints);
        constraints.gridy = 2;
        achatComposantePanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 3;
        achatComposantePanel.add(nomFournisseurField, constraints);
        constraints.gridy = 4;
        achatComposantePanel.add(numeroComposanteLabel, constraints);
        constraints.gridy = 5;
        achatComposantePanel.add(nomComposanteField, constraints);
        constraints.gridy = 6;
        achatComposantePanel.add(btnConfirmerAchat, constraints);
        constraints.gridy = 7;
        achatComposantePanel.add(btnAnnuler, constraints);

        onBtnConfirmerAchatClicked(btnConfirmerAchat, nomFournisseurField, nomComposanteField, "composante");
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
    public void onBtnConfirmerAchatClicked(JButton btnConfirmerAchat, JTextField nomFournisseurField, JTextField numeroField, String choix) {
        btnConfirmerAchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                Fournisseur  fournisseur = dbControleur.retournerFournisseur(nomFournisseurField.getText());
                if (nomFournisseurField.getText().isEmpty() || numeroField.getText().isEmpty())
                    afficherMessageErreurAchat();
                else{
                    switch (choix.toLowerCase()){
                        case "robot" -> {
                            //TODO: Mettre les notifs
                            UUID uuid = dbControleur.acheterRobot(nomFournisseurField.getText(), Integer.parseInt(numeroField.getText()));
                            if (uuid != null){
                                confirmerAchatRobot(uuid);
                                String body = "Cher " + nomFournisseurField.getText() + ",\n\n"+ pseudo +" a acheté un nouveau robot!\n\nL'équipe robotix";
                                emailSender = new EmailSender("robotrobotix4@gmail.com","lkzojmozphkprruj", fournisseur.getEmail(),
                                        "Achat de robot", body);
                                emailSender.sendInBackground();
                            } else {
                                afficherMessageErreurAchat();
                            }
                        }
                        case "composante" -> {
                            if (controlleurUtilisateurs.acheterComposante(nomFournisseurField.getText(), numeroField.getText().toLowerCase(), pseudo)){
                                confirmerAchatComposante();
                                String body = "Cher " + nomFournisseurField.getText() + ",\n\n"+ pseudo +" a acheté une nouvelle composante!\n\nL'équipe robotix";
                                emailSender = new EmailSender("robotrobotix4@gmail.com","lkzojmozphkprruj", fournisseur.getEmail(),
                                        "Achat de composant", body);
                                emailSender.sendInBackground();
                            } else {
                                afficherMessageErreurAchat();
                            }
                        }
                    }

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

    public void confirmerAchatRobot(UUID uuid) {
        String message = "L'achat a ete bien reussi ! Voici le numero de série de votre robot :\n\n" + uuid +
                "\n\nVEUILLEZ LE NOTER CAR IL EST INDISPENSABLE POUR CERTAINES OPTION.";
        String title = "Transaction terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerAchatComposante(){
        String message = "L'achat a ete bien reussi !";
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
