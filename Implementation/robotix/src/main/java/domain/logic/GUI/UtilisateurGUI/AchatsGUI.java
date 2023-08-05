package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Fournisseur;
import domain.logic.Outils.ComboBoxRenderer;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
/**
 * Cette classe représente une interface graphique pour effectuer des achats, que ce soit de robots ou de composantes.
 * Elle permet d'afficher les fournisseurs disponibles, leurs détails, ainsi que de confirmer les achats et afficher
 * des messages de confirmation ou d'erreur.
 */
public class AchatsGUI {
    /**
     * Représente le pseudonyme d'un utilisateur.
     */
    private String pseudo;
    /**
     * Contrôleur pour les opérations liées aux utilisateurs.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Contrôleur pour les opérations liées aux fournisseurs.
     */
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    /**
     * Contrôleur de base de données pour les opérations sur la base de données.
     */
    private DbControleur dbControleur = domain.logic.Controller.DbControleur.getDbControleur();
    /**
     * Cadre principal (JFrame) de l'application.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Panneau principal de l'application avec GridLayout.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Panneau pour l'achat de robots avec GridBagLayout.
     */
    private JPanel achatRobotPanel = new JPanel(new GridBagLayout());
    /**
     * Panneau pour l'achat de composantes avec GridBagLayout.
     */
    private JPanel achatComposantePanel = new JPanel(new GridBagLayout());
    /**
     * Étiquette pour les options d'achat.
     */
    private JLabel achatsLabel = new JLabel("Que voulez-vous acheter?", SwingConstants.CENTER);
    /**
     * Bouton pour lancer l'achat de robots.
     */
    private JButton btnAchatRobot = new JButton("Robot");
    /**
     * Bouton pour lancer l'achat de composantes.
     */
    private JButton btnAchatComposante = new JButton("Composante");
    /**
     * Bouton pour retourner au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Liste des fournisseurs.
     */
    private ArrayList<Fournisseur> listeFournisseur;
    /**
     * JList pour afficher les fournisseurs pour l'achat de robots.
     */
    private JList<String> listeFournisseurJlistRobot;
    /**
     * JList pour afficher les fournisseurs pour l'achat de composantes.
     */
    private JList<String> listeFournisseurJlistComposante;
    /**
     * JScrollPane pour afficher les fournisseurs pour l'achat de robots.
     */
    private JScrollPane scrollPaneRobot;
    /**
     * JScrollPane pour afficher les fournisseurs pour l'achat de composantes.
     */
    private JScrollPane scrollPaneComposante;
    /**
     * Conserve une référence au panneau précédemment affiché.
     */
    private Container panelPrecedent = new Container();
    /**
     * GridBagConstraints pour définir le placement des composants dans un panneau.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Interface graphique pour les achats.
     *
     * @param pseudo Le pseudonyme de l'utilisateur.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     * @throws ParseException En cas d'erreur lors de l'analyse.
     */
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

    /**
     * Met en place le panneau principal.
     */
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

    /**
     * Met en place le panneau d'achat de robots.
     */
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

    /**
     * Récupère la liste des fournisseurs en fonction du choix spécifié.
     *
     * @param choix Le choix spécifié (peut être "robot" ou "composante").
     */
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

    /**
     * Affiche les détails d'un fournisseur en fonction du choix spécifié.
     *
     * @param fournisseur Le fournisseur dont les détails doivent être affichés.
     * @param choix Le choix spécifié (peut être "robot" ou "composante").
     */
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

    /**
     * Configure le panneau d'achat de composantes.
     */
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

    /**
     * Affiche le panneau principal dans la fenêtre JFrame spécifiée.
     *
     * @param jFrame La fenêtre JFrame dans laquelle afficher le panneau principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu de la fenêtre JFrame.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Gère l'événement lors du clic sur le bouton de confirmation d'achat.
     *
     * @param btnConfirmerAchat Le bouton de confirmation d'achat.
     * @param nomFournisseurField Le champ de texte contenant le nom du fournisseur.
     * @param numeroField Le champ de texte contenant le numéro de l'élément à acheter.
     * @param choix Le choix spécifié (peut être "robot" ou "composante").
     */
    public void onBtnConfirmerAchatClicked(JButton btnConfirmerAchat, JTextField nomFournisseurField, JTextField numeroField, String choix) {
        btnConfirmerAchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomFournisseurField.getText().isEmpty() || numeroField.getText().isEmpty())
                    afficherMessageErreurAchat();
                else{
                    switch (choix.toLowerCase()){
                        case "robot" -> {
                            UUID uuid = dbControleur.acheterRobot(nomFournisseurField.getText(), Integer.parseInt(numeroField.getText()));
                            if (uuid != null){
                                confirmerAchatRobot(uuid);
                            } else {
                                afficherMessageErreurAchat();
                            }
                        }
                        case "composante" -> {

                            if (controlleurUtilisateurs.acheterComposante(nomFournisseurField.getText(), numeroField.getText().toLowerCase(), pseudo)){
                                confirmerAchatComposante();
                            } else {
                                afficherMessageErreurAchat();
                            }
                        }
                    }

                }
            }
        });
    }

    /**
     * Gère l'événement lors du clic sur le bouton d'annulation.
     *
     * @param btnAnnuler Le bouton d'annulation.
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
     * Affiche un message de confirmation d'achat de robot avec le numéro de série.
     *
     * @param uuid Le numéro de série du robot acheté.
     */
    public void confirmerAchatRobot(UUID uuid) {
        String message = "L'achat a ete bien reussi ! Voici le numero de série de votre robot :\n\n" + uuid +
                "\n\nVEUILLEZ LE NOTER CAR IL EST INDISPENSABLE POUR CERTAINES OPTION.";
        String title = "Transaction terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message de confirmation d'achat de composante.
     */
    public void confirmerAchatComposante(){
        String message = "L'achat a ete bien reussi !";
        String title = "Transaction terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur en cas d'échec de l'achat.
     */
    public void afficherMessageErreurAchat() {
        String message = "L'achat n'a pas ete bien completee. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
