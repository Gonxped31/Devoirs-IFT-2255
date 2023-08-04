package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Outils.ComboBoxRenderer;
import domain.logic.Outils.GestionDates;
import domain.logic.Outils.HTMLListCellRenderer;
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Cette classe s'occupe de l'affichage des requeêtes publiques chez les fournisseurs.
 */
public class RequetePubliqueFournisseurGUI {
    private DbControleur dbControleur = DbControleur.getDbControleur();
    private String nomFournisseur;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel voirListeUtilisateursPanel = new JPanel(new GridBagLayout());
    private JPanel voirListeFournisseursPanel = new JPanel(new GridBagLayout());
    private JPanel voirProfilPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheUtilisateurPanel = new JPanel(new GridBagLayout());
    private JPanel recherheUtilisateurParPseudoPanel = new JPanel(new GridBagLayout());
    private JPanel recherheUtilisateurParNomPanel = new JPanel(new GridBagLayout());
    private JPanel recherheUtilisateurParPrenomPanel = new JPanel(new GridBagLayout());
    private JPanel recherheUtilisateurParListeSuiveursPanel = new JPanel(new GridBagLayout());
    private JPanel recupererListeActivitesPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsAvecFiltrePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsSansFiltrePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsParLettrePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsParPseudoPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheInteretsParLettreEtPseudoPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheFournisseurPanel = new JPanel(new GridBagLayout());
    private JPanel recherheFournisseurParNomPanel = new JPanel(new GridBagLayout());
    private JPanel recherheFournisseurParEmailPanel = new JPanel(new GridBagLayout());
    private JPanel recherheFournisseurParAdressePanel = new JPanel(new GridBagLayout());
    private JPanel recherheFournisseurParTypeComposantePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheComposantePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheComposanteParTypePanel = new JPanel(new GridBagLayout());
    private JPanel rechercheComposanteParNomFournisseurPanel = new JPanel(new GridBagLayout());
    private JPanel rechercheComposanteParNomPanel = new JPanel(new GridBagLayout());
    private JLabel requetePubliqueFournisseurLabel = new JLabel("Veuillez faire une requete publique", SwingConstants.CENTER);
    private JButton btnVoirListeUtilisateurs = new JButton("Voir la liste d'utilisateurs");
    private JButton btnVoirListeFournisseurs = new JButton("Voir la liste des fournisseurs");
    private JButton btnVoirProfil = new JButton("Voir mon profil");
    private JButton btnRechercheUtilisateur = new JButton("Rechercher un utilisateur");
    private JButton btnRecupererListeActivites = new JButton("Recuperer la liste des activites");
    private JButton btnRechercheInterets = new JButton("Recuperer la liste des interets");
    private JButton btnRechercheFournisseur = new JButton("Rechercher un fournisseur");
    private JButton btnRechercheComposante = new JButton("Rechercher une composante");
    private JButton btnRetour = new JButton("Retour au menu Fournisseur");
    private JButton btnChoix = new JButton("Continuer");
    private ButtonGroup buttonGroup = new ButtonGroup(); // Classe qui permet de regrouper un ensemble de bouton radio en selectionnant qu'une seule option parmi le groupe
    private JRadioButton ouiLabel = new JRadioButton("Oui");
    private JRadioButton nonLabel = new JRadioButton("Non");

    private ArrayList<Utilisateur> listeUtilisateur;
    private ArrayList<Fournisseur> listeFournisseur;
    private ArrayList<Activite> listeActivites;
    private HashSet<Interet> listeInterets;

    private JScrollPane scrollPaneListeUtilisateur, scrollPaneListeUtilisateurParPseudo, scrollPaneListeUtilisateurParSuiveur,
            scrollPaneListeFournisseur, scrollPaneListeFournisseurParNom, scrollPaneListeFournisseurParEmail,
            scrollPaneListeFournisseurParAddresse, scrollPaneListeFournisseurParTypeComposante, scrollPaneListeActivites,
            scrollPaneListeInteretsSansFiltre, scrollPaneListeComposanteParNom, scrollPaneListeComposanteParType,
            scrollPaneListeComposanteParNomFournisseur;


    private JList<String> listeUtilisateurJList, listeUtilisateurParPseudoJListe, listeUtilisateurParSuiveurJList,
            listeFournisseurJList, listeFournisseurParNomJListe, listeFournisseurParEmailJList, listeFournisseurParAddresseJList,
            listeFournisseurParTypeComposanteJList, listeActiviteJList, listeInteretsJListSansFiltre, listeComposanteParNomJList,
            listeComposanteParTypeJList, listeComposanteParNomFournisseurJList;


    private DefaultListModel<String> listeModelUtilisateurParPseudo = new DefaultListModel<>();
    private DefaultListModel<String> listeModelUtilisateurParSuiveur = new DefaultListModel<>();
    private DefaultListModel<String> listeModelFournisseurParNom = new DefaultListModel<>();
    private DefaultListModel<String> listeModelFournisseurParEmail = new DefaultListModel<>();
    private DefaultListModel<String> listeModelFournisseurParAddresse = new DefaultListModel<>();
    private DefaultListModel<String> listeModelFournisseurParTypeComposante = new DefaultListModel<>();
    private DefaultListModel<String> listeModelComposanteParNom = new DefaultListModel<>();
    private DefaultListModel<String> listeModelComposanteParType = new DefaultListModel<>();
    private DefaultListModel<String> listeModelComposanteParNomFournisseur = new DefaultListModel<>();


    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Constructeur de la classe RequetePubliqueFournisseurGUI.
     *
     * @param nomFournisseur Le nom du fournisseur pour lequel afficher les requêtes publiques.
     * @throws IOException   S'il y a une erreur lors de la lecture de fichiers.
     * @throws ParseException S'il y a une erreur lors de l'analyse de la date dans un format spécifique.
     */
    public RequetePubliqueFournisseurGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        setMainPanel();
        setVoirListeUtilisateursPanel();
        setVoirListeFournisseursPanel();
        setVoirProfilPanel();

        // Setup de l'option Recherche Utilisateur
        setRechercheUtilisateurPanel();
        setRechercheUtilisateurParPseudoPanel();
        //setRechercheUtilisateurParNomPanel();
        //setRechercheUtilisateurParPrenomPanel();
        setRechercheUtilisateurParListeSuiveursPanel();

        setRecupererListeActivitesPanel();

        // Setup de l'option Recherche Interets
        //setRechercheInteretsPanel();
        //setRechercheInteretsAvecFiltrePanel();
        setRechercheInteretsSansFiltrePanel();
        //setRechercheInteretsParLettrePanel();
        //setRechercheInteretsParPseudoPanel();
        //setRechercheInteretsParLettreEtPseudoPanel();

        // Setup de l'option Recherche Fournisseur
        setRechercheFournisseurPanel();
        setRechercheFournisseurParNomPanel();
        setRechercheFournisseurParEmailPanel();
        setRechercheFournisseurParAdressePanel();
        setRecherheFournisseurParTypeComposantePanel();

        // Setup de l'option Recherche Composante
        setRechercheComposantePanel();
        setRechercheComposanteParTypePanel();
        setRechercheComposanteParNomFournisseurPanel();
        setRechercheComposanteParNomPanel();

        btnVoirListeUtilisateurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(voirListeUtilisateursPanel);
                mettreAJourFrame();
            }
        });
        btnVoirListeFournisseurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(voirListeFournisseursPanel);
                mettreAJourFrame();
            }
        });
        btnVoirProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(voirProfilPanel);
                mettreAJourFrame();
            }
        });
        btnRechercheUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheUtilisateurPanel);
                mettreAJourFrame();
            }
        });
        btnRecupererListeActivites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(recupererListeActivitesPanel);
                mettreAJourFrame();
            }
        });
        btnRechercheInterets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheInteretsSansFiltrePanel);
                mettreAJourFrame();
            }
        });
        btnRechercheFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheFournisseurPanel);
                mettreAJourFrame();
            }
        });
        btnRechercheComposante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheComposantePanel);
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
        btnChoix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonGroup.getSelection() != null) {
                    if (buttonGroup.getSelection().getActionCommand() == "Oui") {
                        jFrame.setContentPane(rechercheInteretsAvecFiltrePanel);
                        mettreAJourFrame();
                    }
                    else if (buttonGroup.getSelection().getActionCommand() == "Non") {
                        jFrame.setContentPane(rechercheInteretsSansFiltrePanel);
                        mettreAJourFrame();
                    }
                } else {
                    affirmerMessageErreurRadioButton();
                }
            }
        });
    }

    /**
     * Initialise le panneau principal (mainPanel) de l'interface graphique RequetePubliqueFournisseurGUI.
     * Ajoute les composants nécessaires au panneau principal tels que des étiquettes, des boutons et des espaces horizontaux.
     * Les actions des boutons sont associées pour afficher les panneaux correspondants en cas de clic.
     */
    public void setMainPanel() {
        requetePubliqueFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(requetePubliqueFournisseurLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnVoirListeUtilisateurs);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnVoirListeFournisseurs);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnVoirProfil);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheUtilisateur);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRecupererListeActivites);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheInterets);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheFournisseur);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheComposante);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Initialise le panneau de visualisation de la liste des utilisateurs (voirListeUtilisateursPanel) de l'interface graphique RequetePubliqueFournisseurGUI.
     * Ajoute les composants nécessaires au panneau tels que des étiquettes, une liste déroulante, et un bouton de retour.
     * Les actions des boutons sont associées pour afficher les détails de l'utilisateur sélectionné en cas de clic sur la liste déroulante.
     * La méthode `recupererListeUtilisateur()` est appelée pour récupérer la liste des utilisateurs depuis le contrôleur de base de données et les afficher dans la liste déroulante.
     */
    public void setVoirListeUtilisateursPanel() {
        JLabel listeUtilisateursLabel = new JLabel("Liste des utilisateurs");
        JLabel infoClique = new JLabel("Cliquez sur un utilisateur pour voir son profile.");
        recupererListeUtilisateur();
        JButton btnRetour = new JButton("Retour");

        listeUtilisateursLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        voirListeUtilisateursPanel.add(listeUtilisateursLabel, constraints);
        constraints.gridy = 1;
        voirListeUtilisateursPanel.add(infoClique, constraints);
        constraints.gridy = 2;
        voirListeUtilisateursPanel.add(scrollPaneListeUtilisateur, constraints);
        constraints.gridy = 3;
        voirListeUtilisateursPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }//Done

    /**
     * Récupère la liste des utilisateurs depuis le contrôleur de base de données,
     * les affiche dans une liste déroulante (JList) et permet d'afficher les détails
     * d'un utilisateur sélectionné dans une boîte de dialogue.
     */
    private void recupererListeUtilisateur() {
        listeUtilisateur = dbControleur.recupererListeUtilisateur();
        listeUtilisateurJList = new JList<>(listeUtilisateur.stream()
                .map(Utilisateur::getNom)
                .toArray(String[]::new));
        listeUtilisateurJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeUtilisateurJList.setVisibleRowCount(5);
        listeUtilisateurJList.setCellRenderer(new ComboBoxRenderer());
        listeUtilisateurJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = listeUtilisateurJList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < listeUtilisateur.size()) {
                    Utilisateur selectedSupplier = listeUtilisateur.get(selectedIndex);
                    detailsUtilisateur(selectedSupplier);
                    listeUtilisateurJList.clearSelection();
                }
            }
        });

        scrollPaneListeUtilisateur = new JScrollPane(listeUtilisateurJList);
        scrollPaneListeUtilisateur.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeUtilisateur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Affiche les détails d'un utilisateur dans une boîte de dialogue JOptionPane.
     *
     * @param utilisateur L'utilisateur dont les détails doivent être affichés.
     */
    private void detailsUtilisateur(Utilisateur utilisateur) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nom: ").append(utilisateur.getNom()).append("\n");
        messageBuilder.append("Addresse: ").append(utilisateur.getAdresse()).append("\n");
        messageBuilder.append("Telephone: ").append(utilisateur.getTelephone()).append("\n");
        messageBuilder.append("Courriel: ").append(utilisateur.getEmail()).append("\n");
        messageBuilder.append("Nom de la compagnie: ").append(utilisateur.getNomCompagnie()).append("\n");

        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details de l'utilisateur", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Initialise le panneau de visualisation de la liste des fournisseurs.
     * Récupère la liste des fournisseurs depuis le contrôleur de base de données,
     * les affiche dans une liste déroulante (JList) et permet d'afficher les détails
     * d'un fournisseur sélectionné dans une boîte de dialogue.
     */
    public void setVoirListeFournisseursPanel() {
        JLabel listeFournisseursLabel = new JLabel("Liiste des fournisseurs");
        JLabel infoClique = new JLabel("Cliquez sur un utilisateur pour voir son profile.");
        recupererListeFournisseur();
        JButton btnRetour = new JButton("Retour");

        listeFournisseursLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        voirListeFournisseursPanel.add(listeFournisseursLabel, constraints);
        constraints.gridy = 1;
        voirListeFournisseursPanel.add(infoClique, constraints);
        constraints.gridy = 2;
        voirListeFournisseursPanel.add(scrollPaneListeFournisseur, constraints);
        constraints.gridy = 3;
        voirListeFournisseursPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);

    }

    /**
     * Récupère la liste des fournisseurs depuis le contrôleur de base de données,
     * les affiche dans une liste déroulante (JList) et permet d'afficher les détails
     * d'un fournisseur sélectionné dans une boîte de dialogue.
     */
    private void recupererListeFournisseur() {
        listeFournisseur = dbControleur.recupererListFournisseur();
        listeFournisseurJList = new JList<>(listeFournisseur.stream()
                .map(Fournisseur::getNom)
                .toArray(String[]::new));
        listeFournisseurJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeFournisseurJList.setVisibleRowCount(5);
        listeFournisseurJList.setCellRenderer(new ComboBoxRenderer());
        listeFournisseurJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = listeFournisseurJList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < listeFournisseur.size()) {
                    Fournisseur selectedSupplier = listeFournisseur.get(selectedIndex);
                    detailsFournisseur(selectedSupplier);
                    listeFournisseurJList.clearSelection(); // Deselect the item after showing the details
                }
            }
        });

        scrollPaneListeFournisseur = new JScrollPane(listeFournisseurJList);
        scrollPaneListeFournisseur.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeFournisseur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }


    /**
     * Affiche les détails d'un fournisseur dans une boîte de dialogue JOptionPane.
     *
     * @param fournisseur Le fournisseur dont les détails doivent être affichés.
     */
    private void detailsFournisseur(Fournisseur fournisseur) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nom: ").append(fournisseur.getNom()).append("\n");
        messageBuilder.append("Addresse: ").append(fournisseur.getAdresse()).append("\n");
        messageBuilder.append("Telephone: ").append(fournisseur.getTelephone()).append("\n");
        messageBuilder.append("Type de robot fabriqué: ").append(fournisseur.getTypeRobotFabriquer()).append("\n");
        messageBuilder.append("Type de composantes fabriqués: ").append(fournisseur.getTypeComposantesFabriquer()).append("\n");
        messageBuilder.append("Capacité de production: ").append(fournisseur.getCapaciteProductionComposantes()).append("\n");
        messageBuilder.append("Nom de la compagnie: ").append(fournisseur.getNomCompagnie()).append("\n");
        messageBuilder.append("Nombre de robot disponible : ").append(fournisseur.getInventaireDeRobot().size()).append("\n");
        messageBuilder.append("Nombre de composante disponible :").append(fournisseur.getInventaireComposant().size()).append("\n");
        LinkedList<Robot> robots = fournisseur.getInventaireDeRobot();

        if (!robots.isEmpty()){
            messageBuilder.append("Liste des robots : ").append("\n");
            for (Robot robot : robots) {
                messageBuilder.append("Robot ").append(robot.getNumero()).append(": ").append("CPU - ").append(robot.getCpu()).append(", Mémoire - ").append(robot.getMemoire()).append("\n");
            }
        } else {
            messageBuilder.append("Aucun robot vendu actuellement.").append("\n");
        }

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
        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details du fournisseur", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Initialise le panneau de visualisation du profil du fournisseur.
     * Récupère les informations du fournisseur depuis le contrôleur de base de données,
     * les affiche dans des champs de texte (JTextField) en mode lecture seule.
     * Permet à l'utilisateur de revenir au panneau précédent en cliquant sur le bouton "Retour".
     */
    public void setVoirProfilPanel() {
        Fournisseur fournisseur = dbControleur.retournerFournisseur(nomFournisseur);

        JLabel nomLabel = new JLabel("Nom");
        JLabel adresseLabel = new JLabel("Adresse");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel typeRobotLabel = new JLabel("Type de robot a fabriquer");
        JLabel typeComposanteLabel = new JLabel("Type de composante a fabriquer");
        JLabel nbRobotsDisponibleLabel = new JLabel("Nombre de robot disponible");
        JLabel nbComposantesDisponibleLabel = new JLabel("Nombre de composant disponible");
        JButton btnRetour = new JButton("Retour");

        // Il faut remplacer le champ de texte des JTextField par des variables qui recuperent leur informations respectives
        JTextField nomField = new JTextField(fournisseur.getNom());
        JTextField adresseField = new JTextField(fournisseur.getAdresse());
        JTextField telephoneField = new JTextField(fournisseur.getTelephone());
        JTextField typeRobotField = new JTextField(fournisseur.getTypeRobotFabriquer());
        JTextField typeComposanteField = new JTextField(fournisseur.getTypeComposantesFabriquer());
        JTextField nbRobotsDisponibleField = new JTextField(fournisseur.getInventaireDeRobot().size() + "");
        JTextField nbComposantesDisponibleField = new JTextField(fournisseur.getInventaireComposant().size() + "");

        // Setup la dimension des JTextField
        nomField.setPreferredSize(new Dimension(200, 30));
        adresseField.setPreferredSize(new Dimension(200, 30));
        telephoneField.setPreferredSize(new Dimension(200, 30));
        typeRobotField.setPreferredSize(new Dimension(200, 30));
        typeComposanteField.setPreferredSize(new Dimension(200, 30));
        nbRobotsDisponibleField.setPreferredSize(new Dimension(200, 30));
        nbComposantesDisponibleField.setPreferredSize(new Dimension(200, 30));

        // Rendre les JTextField en mode lecture seule
        nomField.setEditable(false);
        adresseField.setEditable(false);
        telephoneField.setEditable(false);
        typeRobotField.setEditable(false);
        typeComposanteField.setEditable(false);
        nbRobotsDisponibleField.setEditable(false);
        nbComposantesDisponibleField.setEditable(false);

        constraints.gridy = 0;
        voirProfilPanel.add(nomLabel, constraints);
        voirProfilPanel.add(nomField, constraints);
        constraints.gridy = 1;
        voirProfilPanel.add(adresseLabel, constraints);
        voirProfilPanel.add(adresseField, constraints);
        constraints.gridy = 2;
        voirProfilPanel.add(telephoneLabel, constraints);
        voirProfilPanel.add(telephoneField, constraints);
        constraints.gridy = 3;
        voirProfilPanel.add(typeRobotLabel, constraints);
        voirProfilPanel.add(typeRobotField, constraints);
        constraints.gridy = 4;
        voirProfilPanel.add(typeComposanteLabel, constraints);
        voirProfilPanel.add(typeComposanteField, constraints);
        constraints.gridy = 5;
        voirProfilPanel.add(nbRobotsDisponibleLabel, constraints);
        voirProfilPanel.add(nbRobotsDisponibleField, constraints);
        constraints.gridy = 6;
        voirProfilPanel.add(nbComposantesDisponibleLabel, constraints);
        voirProfilPanel.add(nbComposantesDisponibleField, constraints);
        constraints.gridy = 7;
        voirProfilPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }


    //  Recherche utilisateur
    /**
     * Initialise le panneau de recherche d'utilisateurs en affichant un filtre avec des options.
     * Le filtre permet de choisir entre la recherche par pseudo ou la recherche des suiveurs d'un utilisateur (par pseudo).
     * Les résultats de la recherche seront affichés dans une liste déroulante (JList).
     * L'utilisateur peut cliquer sur le bouton "Continuer" pour effectuer la recherche et afficher les résultats,
     * ou cliquer sur le bouton "Retour" pour revenir à l'écran précédent.
     */
    public void setRechercheUtilisateurPanel() {
        JLabel filtreLabel = new JLabel("Filtrer par");
        String[] options = {"Pseudo", "Obtenir liste des suiveurs de ? (pseudo voulu)"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheUtilisateurPanel.add(filtreLabel, constraints);
        constraints.gridy = 1;
        rechercheUtilisateurPanel.add(comboBox, constraints);
        constraints.gridy = 2;
        rechercheUtilisateurPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheUtilisateurPanel.add(btnRetour, constraints);

        onBtnContinuerUtilisateur(btnContinuer, comboBox);
        onBtnRetourClicked(btnRetour);
    }

    /**
     * Initialise le panneau de recherche d'utilisateurs par pseudo.
     * Affiche une liste des utilisateurs trouvés après la recherche par pseudo dans une liste déroulante (JList).
     * L'utilisateur doit entrer un pseudo dans un champ de texte (JTextField) pour effectuer la recherche.
     * L'utilisateur peut cliquer sur le bouton "Continuer" pour effectuer la recherche et afficher les résultats,
     * ou cliquer sur le bouton "Retour au filtre" pour revenir au panneau de filtre.
     */
    public void setRechercheUtilisateurParPseudoPanel() {
        JLabel listeUtilisateurLabel = new JLabel("Utilisateur");
        JLabel pseudoLabel = new JLabel("Entrez le pseudo");
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        pseudoField.setPreferredSize(new Dimension(200, 30));

        listeUtilisateurParPseudoJListe = new JList<>(listeModelUtilisateurParPseudo);
        listeUtilisateurParPseudoJListe.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeUtilisateurParPseudo = new JScrollPane(listeUtilisateurParPseudoJListe);
        scrollPaneListeUtilisateurParPseudo.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeUtilisateurParPseudo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheUtilisateurParPseudoPanel.add(listeUtilisateurLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParPseudoPanel.add(scrollPaneListeUtilisateurParPseudo, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParPseudoPanel.add(pseudoField, constraints);
        constraints.gridy = 4;
        recherheUtilisateurParPseudoPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheUtilisateurParPseudoPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheUtilisateurParPseudo(btnContinuer, pseudoField);
        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }

    /**
     * Traite l'action lors du clic sur le bouton "Continuer" pour effectuer la recherche d'utilisateur par pseudo.
     * Récupère le pseudo entré par l'utilisateur dans le champ de texte, effectue la recherche dans la base de données,
     * et affiche les résultats dans une liste déroulante (JList) à l'aide d'un modèle de liste (ListModel).
     * Si aucun utilisateur n'est trouvé, une boîte de dialogue d'erreur sera affichée.
     *
     * @param btnContinuer Le bouton "Continuer" qui déclenche la recherche.
     * @param pseudoField Le champ de texte pour entrer le pseudo de l'utilisateur recherché.
     */
    private void onBtnContinuerRechercheUtilisateurParPseudo(JButton btnContinuer, JTextField pseudoField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = pseudoField.getText();
                if (!pseudo.isEmpty()) {
                    String u = dbControleur.rechercherUtilisateurParPseudo(pseudo);
                    if (!u.equals("Utilisateur non trouver, veuillez verifier le pseudo")) {
                        u = "<html>" + u.replace("\n", "<br>") + "</html>";
                        listeModelUtilisateurParPseudo.clear();
                        listeModelUtilisateurParPseudo.addElement(u);
                    } else {
                        JOptionPane.showMessageDialog(null, "Utilisateur non trouvé, veuillez vérifier le pseudo",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un pseudo.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche d'utilisateurs par liste de suiveurs.
     * Affiche une liste des utilisateurs trouvés après la recherche par liste de suiveurs dans une liste déroulante (JList).
     * L'utilisateur doit entrer un pseudo dans un champ de texte (JTextField) pour effectuer la recherche.
     * L'utilisateur peut cliquer sur le bouton "Continuer" pour effectuer la recherche et afficher les résultats,
     * ou cliquer sur le bouton "Retour au filtre" pour revenir au panneau de filtre.
     */
    public void setRechercheUtilisateurParListeSuiveursPanel() {
        JLabel listeUtilisateurLabel = new JLabel("Utilisateur");
        JLabel pseudoLabel = new JLabel("Entrez le pseudo");
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeUtilisateurParSuiveurJList = new JList<>(listeModelUtilisateurParSuiveur);
        listeUtilisateurParSuiveurJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeUtilisateurParSuiveur = new JScrollPane(listeUtilisateurParSuiveurJList);
        scrollPaneListeUtilisateurParSuiveur.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeUtilisateurParSuiveur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheUtilisateurParListeSuiveursPanel.add(listeUtilisateurLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParListeSuiveursPanel.add(scrollPaneListeUtilisateurParSuiveur, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParListeSuiveursPanel.add(pseudoLabel, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParListeSuiveursPanel.add(pseudoField, constraints);
        constraints.gridy = 4;
        recherheUtilisateurParListeSuiveursPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheUtilisateurParListeSuiveursPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheUtilisateurParSuiveur(btnContinuer, pseudoField);
        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }

    /**
     * Traite l'action lors du clic sur le bouton "Continuer" pour effectuer la recherche d'utilisateurs par liste de suiveurs.
     * Récupère le pseudo entré par l'utilisateur dans le champ de texte, effectue la recherche dans la base de données,
     * et affiche les résultats dans une liste déroulante (JList) à l'aide d'un modèle de liste (ListModel).
     * Si aucun utilisateur n'est trouvé ou si aucun utilisateur n'est suivi, une boîte de dialogue d'erreur sera affichée.
     *
     * @param btnContinuer Le bouton "Continuer" qui déclenche la recherche.
     * @param pseudoField Le champ de texte pour entrer le pseudo de l'utilisateur recherché.
     */
    private void onBtnContinuerRechercheUtilisateurParSuiveur(JButton btnContinuer, JTextField pseudoField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = pseudoField.getText();
                if (!pseudo.isEmpty()) {
                    String u = dbControleur.rechercherUtilisateurParSuiveur(pseudo);
                    if (!u.isEmpty()) {
                        u = "<html>" + u.replace("\n", "<br>") + "</html>";
                        listeModelUtilisateurParSuiveur.clear();
                        listeModelUtilisateurParSuiveur.addElement(u);
                    } else {
                        JOptionPane.showMessageDialog(null, "Utilisateur non trouvé ou aucun utilisateur suivi.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un pseudo.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau pour afficher la liste des activités.
     * Affiche une liste des activités disponibles dans une liste déroulante (JList).
     * Lorsqu'un utilisateur sélectionne une activité dans la liste, il peut cliquer sur le bouton "Détails"
     * pour afficher les informations détaillées de l'activité sélectionnée.
     * L'utilisateur peut également cliquer sur le bouton "Retour" pour revenir à l'écran précédent.
     */
    public void setRecupererListeActivitesPanel() {
        JLabel listeActivitesLabel = new JLabel("Voici la liste des activites");
        JLabel infoClique = new JLabel("Cliquez sur un utilisateur pour voir son profile.");

        recupererListeActivites();
        JButton btnRetour = new JButton("Retour");

        listeActivitesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        recupererListeActivitesPanel.add(listeActivitesLabel, constraints);
        constraints.gridy = 1;
        recupererListeActivitesPanel.add(infoClique, constraints);
        constraints.gridy = 2;
        recupererListeActivitesPanel.add(scrollPaneListeActivites, constraints);
        constraints.gridy = 3;
        recupererListeActivitesPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    /**
     * Récupère la liste des activités depuis la base de données et les affiche dans une liste déroulante (JList).
     * Chaque élément de la liste affiche le nom de l'activité.
     * Lorsqu'un utilisateur sélectionne une activité dans la liste, l'événement de sélection déclenchera
     * l'affichage des détails de l'activité sélectionnée en appelant la méthode detailsActivites().
     */
    private void recupererListeActivites(){
        listeActivites = dbControleur.recupererListeActivite();

        listeActiviteJList = new JList<>(listeActivites.stream()
                .map(Activite::getNom)
                .toArray(String[]::new));
        listeActiviteJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeActiviteJList.setVisibleRowCount(5);
        listeActiviteJList.setCellRenderer(new ComboBoxRenderer());
        listeActiviteJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = listeActiviteJList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < listeActivites.size()) {
                    Activite activiteSelectionnee = listeActivites.get(selectedIndex);
                    detailsActivites(activiteSelectionnee);
                    listeActiviteJList.clearSelection(); // Deselect the item after showing the details
                }
            }
        });

        scrollPaneListeActivites = new JScrollPane(listeActiviteJList);
        scrollPaneListeActivites.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeActivites.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Affiche les détails de l'activité sélectionnée dans une boîte de dialogue (JOptionPane).
     * Les détails affichés incluent le nom de l'activité, la date de début et la date de fin au format lisible,
     * ainsi que la liste des tâches associées à l'activité.
     * Si l'activité n'a pas de tâches associées, "Taches de l'activite: Aucune tache" sera affiché.
     * Les détails sont récupérés à partir de l'objet Activite fourni en paramètre.
     *
     * @param activite L'objet Activite contenant les détails à afficher.
     */
    private void detailsActivites(Activite activite){
        StringBuilder messageBuilder = new StringBuilder("Nom: ");
        messageBuilder.append(activite.getNom()).append("\n").
                append("Date de debut: ").append(GestionDates.parseDateToString(activite.getDateDebut())).append("\n")
                .append("Date de fin: ").append(GestionDates.parseDateToString(activite.getDateFin())).append("\n")
                .append("Taches de l'activite: ").append("\n")
                .append(activite.getListeDeTache().stream()
                        .map(Tache::getNom)
                        .collect(Collectors.joining("\n")));

        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details de l'activite", JOptionPane.INFORMATION_MESSAGE);
    }


    //Interets
    /**
     * Initialise le panneau pour afficher la liste des intérêts sans filtre.
     * Affiche une liste des intérêts disponibles dans une liste déroulante (JList) sans filtre.
     * Lorsqu'un utilisateur sélectionne un intérêt dans la liste, il peut cliquer sur le bouton "Retour"
     * pour revenir à l'écran précédent.
     */
    public void setRechercheInteretsSansFiltrePanel() {
        JLabel listeinteretLabel = new JLabel("Liste des interets");
        recupererListeInteretsSansFiltre();
        JButton btnRetour = new JButton("Retour");

        listeinteretLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        rechercheInteretsSansFiltrePanel.add(listeinteretLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsSansFiltrePanel.add(scrollPaneListeInteretsSansFiltre, constraints);
        constraints.gridy = 2;
        rechercheInteretsSansFiltrePanel.add(btnRetour, constraints);

        onBtnRetourRechercheInteretsClicked(btnRetour);
    }

    /**
     * Récupère la liste des intérêts depuis la base de données et les affiche dans une liste déroulante (JList)
     * sans appliquer de filtre.
     * Chaque élément de la liste affiche le nom de l'intérêt.
     * Lorsqu'un utilisateur sélectionne un intérêt dans la liste, il peut cliquer sur le bouton "Retour"
     * pour revenir à l'écran précédent.
     */
    private void recupererListeInteretsSansFiltre() {
        listeInterets = dbControleur.recupererListeInteret();

        listeInteretsJListSansFiltre = new JList<>(listeInterets.stream()
                .map(Interet::getNom)
                .toArray(String[]::new));
        listeInteretsJListSansFiltre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeInteretsJListSansFiltre.setVisibleRowCount(5);
        listeInteretsJListSansFiltre.setCellRenderer(new ComboBoxRenderer());

        scrollPaneListeInteretsSansFiltre = new JScrollPane(listeInteretsJListSansFiltre);
        scrollPaneListeInteretsSansFiltre.setPreferredSize(new Dimension(200, 150));
        scrollPaneListeInteretsSansFiltre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Associe un événement de clic sur le bouton "Retour" à une action pour revenir à l'écran principal (mainPanel).
     * Cela met à jour le contenu de la fenêtre JFrame pour afficher le panneau principal.
     *
     * @param btnRetourFiltre Le bouton "Retour" pour lequel l'événement de clic est associé.
     */
    public void onBtnRetourRechercheInteretsClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }


    //Recherche fournisseur
    /**
     * Initialise le panneau de recherche de fournisseurs.
     * Permet de filtrer les fournisseurs par nom, email, adresse ou type de composante.
     * Lorsque l'utilisateur sélectionne un critère de filtrage et clique sur le bouton "Continuer",
     * le panneau de recherche fournisseur par nom sera affiché.
     * L'utilisateur peut également cliquer sur le bouton "Retour" pour revenir à l'écran principal (mainPanel).
     */
    public void setRechercheFournisseurPanel() {
        JLabel filtreLabel = new JLabel("Filtrer par");
        String[] options = {"Nom", "Email", "Adresse", "Type de composante"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheFournisseurPanel.add(filtreLabel, constraints);
        constraints.gridy = 1;
        rechercheFournisseurPanel.add(comboBox, constraints);
        constraints.gridy = 2;
        rechercheFournisseurPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheFournisseurPanel.add(btnRetour, constraints);

        onBtnContinuerFournisseur(btnContinuer, comboBox);
        onBtnRetourClicked(btnRetour);
    }

    /**
     * Initialise le panneau de recherche de fournisseurs par nom.
     * Affiche une liste de fournisseurs dans une liste déroulante (JList) filtrée par nom.
     * L'utilisateur peut entrer un nom de fournisseur dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les fournisseurs correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de fournisseurs (setRechercheFournisseurPanel).
     */
    public void setRechercheFournisseurParNomPanel() {
        JLabel fournisseurLabel = new JLabel("Fournisseur");
        JLabel nomLabel = new JLabel("Entrez le nom du fournisseur");
        JTextField nomField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeFournisseurParNomJListe = new JList<>(listeModelFournisseurParNom);
        listeFournisseurParNomJListe.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeFournisseurParNom = new JScrollPane(listeFournisseurParNomJListe);
        scrollPaneListeFournisseurParNom.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeFournisseurParNom.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheFournisseurParNomPanel.add(fournisseurLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParNomPanel.add(scrollPaneListeFournisseurParNom, constraints);
        constraints.gridy = 2;
        recherheFournisseurParNomPanel.add(nomLabel, constraints);
        constraints.gridy = 3;
        recherheFournisseurParNomPanel.add(nomField, constraints);
        constraints.gridy = 4;
        recherheFournisseurParNomPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheFournisseurParNomPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheFournisseurParNom(btnContinuer, nomField);
        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de fournisseurs par nom
     * à une action pour effectuer la recherche en fonction du nom saisi.
     * Si un fournisseur correspondant au nom est trouvé, il est affiché dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param nomField     Le champ de texte où l'utilisateur entre le nom du fournisseur.
     */
    private void onBtnContinuerRechercheFournisseurParNom(JButton btnContinuer, JTextField nomField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                if (!nom.isEmpty()) {
                    String f = dbControleur.rechercherFournisseurParNom(nom);
                    if (!f.equals("Fournisseur non trouver, veuillez verifier le nom")) {
                        f = "<html>" + f.replace("\n", "<br>") + "</html>";
                        listeModelFournisseurParNom.clear();
                        listeModelFournisseurParNom.addElement(f);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fournisseur non trouver, veuillez verifier le nom",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche de fournisseurs par email.
     * Affiche une liste de fournisseurs dans une liste déroulante (JList) filtrée par email.
     * L'utilisateur peut entrer un email de fournisseur dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les fournisseurs correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de fournisseurs (setRechercheFournisseurPanel).
     */

    public void setRechercheFournisseurParEmailPanel() {
        JLabel fournisseurLabel = new JLabel("Fournisseur");
        JLabel emailLabel = new JLabel("Entrez l'email du fournisseur");
        JTextField emailField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeFournisseurParEmailJList = new JList<>(listeModelFournisseurParEmail);
        listeFournisseurParEmailJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeFournisseurParEmail = new JScrollPane(listeFournisseurParEmailJList);
        scrollPaneListeFournisseurParEmail.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeFournisseurParEmail.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheFournisseurParEmailPanel.add(fournisseurLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParEmailPanel.add(scrollPaneListeFournisseurParEmail, constraints);
        constraints.gridy = 2;
        recherheFournisseurParEmailPanel.add(emailLabel, constraints);
        constraints.gridy = 3;
        recherheFournisseurParEmailPanel.add(emailField, constraints);
        constraints.gridy = 4;
        recherheFournisseurParEmailPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheFournisseurParEmailPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheFournisseurParEmail(btnContinuer, emailField);
        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de fournisseurs par email
     * à une action pour effectuer la recherche en fonction de l'email saisi.
     * Si un fournisseur correspondant à l'email est trouvé, il est affiché dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param emailField   Le champ de texte où l'utilisateur entre l'email du fournisseur.
     */
    private void onBtnContinuerRechercheFournisseurParEmail(JButton btnContinuer, JTextField emailField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if (!email.isEmpty()) {
                    String f = dbControleur.rechercherFournisseurParEmail(email);
                    if (!f.equals("Fournisseur non trouver, veuillez verifier l'email")) {
                        f = "<html>" + f.replace("\n", "<br>") + "</html>";
                        listeModelFournisseurParEmail.clear();
                        listeModelFournisseurParEmail.addElement(f);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fournisseur non trouver, veuillez verifier l'email.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un email.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche de fournisseurs par adresse.
     * Affiche une liste de fournisseurs dans une liste déroulante (JList) filtrée par adresse.
     * L'utilisateur peut entrer une adresse de fournisseur dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les fournisseurs correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de fournisseurs (setRechercheFournisseurPanel).
     */
    public void setRechercheFournisseurParAdressePanel() {
        JLabel fournisseurLabel = new JLabel("Fournisseur");
        JLabel adresseLabel = new JLabel("Entrez l'adresse du fournisseur");
        JTextField adresseField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeFournisseurParAddresseJList = new JList<>(listeModelFournisseurParAddresse);
        listeFournisseurParAddresseJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeFournisseurParAddresse = new JScrollPane(listeFournisseurParAddresseJList);
        scrollPaneListeFournisseurParAddresse.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeFournisseurParAddresse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheFournisseurParAdressePanel.add(fournisseurLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParAdressePanel.add(scrollPaneListeFournisseurParAddresse, constraints);
        constraints.gridy = 2;
        recherheFournisseurParAdressePanel.add(adresseLabel, constraints);
        constraints.gridy = 3;
        recherheFournisseurParAdressePanel.add(adresseField, constraints);
        constraints.gridy = 4;
        recherheFournisseurParAdressePanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheFournisseurParAdressePanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheFournisseurParAddresse(btnContinuer, adresseField);
        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de fournisseurs par adresse
     * à une action pour effectuer la recherche en fonction de l'adresse saisie.
     * Si un fournisseur correspondant à l'adresse est trouvé, il est affiché dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param addresseField Le champ de texte où l'utilisateur entre l'adresse du fournisseur.
     */
    private void onBtnContinuerRechercheFournisseurParAddresse(JButton btnContinuer, JTextField addresseField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addresse = addresseField.getText();
                if (!addresse.isEmpty()) {
                    String f = dbControleur.rechercherFournisseurParAdresse(addresse);
                    if (!f.equals("Fournisseur non trouver, veuillez verifier l'adresse")) {
                        f = "<html>" + f.replace("\n", "<br>") + "</html>";
                        listeModelFournisseurParAddresse.clear();
                        listeModelFournisseurParAddresse.addElement(f);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fournisseur non trouver, veuillez verifier l'adresse.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer une addresse.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche de fournisseurs par type de composante.
     * Affiche une liste de fournisseurs dans une liste déroulante (JList) filtrée par type de composante.
     * L'utilisateur peut entrer un type de composante dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les fournisseurs correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de fournisseurs (setRechercheFournisseurPanel).
     */
    public void setRecherheFournisseurParTypeComposantePanel() {
        JLabel fournisseurLabel = new JLabel("Fournisseur");
        JLabel typeComposanteLabel = new JLabel("Entrez le type de composante");
        JTextField typeComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeFournisseurParTypeComposanteJList = new JList<>(listeModelFournisseurParTypeComposante);
        listeFournisseurParTypeComposanteJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeFournisseurParTypeComposante = new JScrollPane(listeFournisseurParTypeComposanteJList);
        scrollPaneListeFournisseurParTypeComposante.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeFournisseurParTypeComposante.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        recherheFournisseurParTypeComposantePanel.add(fournisseurLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParTypeComposantePanel.add(scrollPaneListeFournisseurParTypeComposante, constraints);
        constraints.gridy = 2;
        recherheFournisseurParTypeComposantePanel.add(typeComposanteLabel, constraints);
        constraints.gridy = 3;
        recherheFournisseurParTypeComposantePanel.add(typeComposanteField, constraints);
        constraints.gridy = 4;
        recherheFournisseurParTypeComposantePanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        recherheFournisseurParTypeComposantePanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheFournisseurParTypeComposante(btnContinuer, typeComposanteField);
        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de fournisseurs par type de composante
     * à une action pour effectuer la recherche en fonction du type de composante saisi.
     * Si un fournisseur correspondant au type de composante est trouvé, il est affiché dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer         Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param typeComposanteField Le champ de texte où l'utilisateur entre le type de composante du fournisseur.
     */
    private void onBtnContinuerRechercheFournisseurParTypeComposante(JButton btnContinuer, JTextField typeComposanteField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeComposanteField.getText();
                if (!type.isEmpty()) {
                    String f = dbControleur.rechercherFournisseurParTypeDeComposant(type);
                    if (!f.equals("Fournisseur non trouver, veuillez verifier le type")) {
                        f = "<html>" + f.replace("\n", "<br>") + "</html>";
                        listeModelFournisseurParTypeComposante.clear();
                        listeModelFournisseurParTypeComposante.addElement(f);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fournisseur non trouver, veuillez verifier le type.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un type.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }



    //Recherche Composantes
    /**
     * Initialise le panneau de recherche de composants.
     * Affiche un menu de filtre (JComboBox) avec des options pour filtrer les composants par type de composant,
     * nom du fournisseur ou nom du composant.
     * Les boutons "Continuer" et "Retour" permettent respectivement de procéder à des recherches spécifiques de composants
     * en fonction des critères choisis ou de revenir au panneau précédent.
     */
    public void setRechercheComposantePanel() {
        JLabel filtreLabel = new JLabel("Filtrer par");
        String[] options = {"Type de la composante", "Nom du fournisseur", "Nom de la composante"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheComposantePanel.add(filtreLabel, constraints);
        constraints.gridy = 1;
        rechercheComposantePanel.add(comboBox, constraints);
        constraints.gridy = 2;
        rechercheComposantePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheComposantePanel.add(btnRetour, constraints);

        onBtnContinuerComposante(btnContinuer, comboBox);
        onBtnRetourClicked(btnRetour);
    }

    /**
     * Initialise le panneau de recherche de composants par type de composant.
     * Affiche une liste de composants dans une liste déroulante (JList) filtrée par type de composant.
     * L'utilisateur peut entrer un type de composant dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les composants correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de composants (setRechercheComposantePanel).
     */
    public void setRechercheComposanteParTypePanel() {
        JLabel composanteLabel = new JLabel("Composante");
        JLabel typeComposanteLabel = new JLabel("Entrez le type de composante");
        JTextField typeComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeComposanteParTypeJList = new JList<>(listeModelComposanteParType);
        listeComposanteParTypeJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeComposanteParType = new JScrollPane(listeComposanteParTypeJList);
        scrollPaneListeComposanteParType.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeComposanteParType.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        rechercheComposanteParTypePanel.add(composanteLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParTypePanel.add(scrollPaneListeComposanteParType, constraints);
        constraints.gridy = 2;
        rechercheComposanteParTypePanel.add(typeComposanteLabel, constraints);
        constraints.gridy = 3;
        rechercheComposanteParTypePanel.add(typeComposanteField, constraints);
        constraints.gridy = 4;
        rechercheComposanteParTypePanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        rechercheComposanteParTypePanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheComposanteParType(btnContinuer, typeComposanteField);
        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de composants par type de composant
     * à une action qui effectue la recherche en fonction du type de composant saisi.
     * Si des composants correspondant au type de composant sont trouvés, ils sont affichés dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer           Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param typeComposanteField    Le champ de texte où l'utilisateur saisit le type de composant.
     */
    private void onBtnContinuerRechercheComposanteParType(JButton btnContinuer, JTextField typeComposanteField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeComposanteField.getText();
                if (!type.isEmpty()) {
                    String c = dbControleur.rechercherComposantParType(type);
                    if (!c.equals("Composant non trouver, veuillez verifier le type")) {
                        c = "<html>" + c.replace("\n", "<br>") + "</html>";
                        listeModelComposanteParType.clear();
                        listeModelComposanteParType.addElement(c);
                    } else {
                        JOptionPane.showMessageDialog(null, "Composant non trouver, veuillez verifier le type.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un type.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche de composants par nom du fournisseur.
     * Affiche une liste de composants dans une liste déroulante (JList) filtrée par nom du fournisseur.
     * L'utilisateur peut entrer un nom du fournisseur dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les composants correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de composants (setRechercheComposantePanel).
     */
    public void setRechercheComposanteParNomFournisseurPanel() {
        JLabel composanteLabel = new JLabel("Composante");
        JLabel nomFournisseurLabel = new JLabel("Entrez le nom du fournisseur");
        JTextField nomFournisseurField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeComposanteParNomFournisseurJList = new JList<>(listeModelComposanteParNomFournisseur);
        listeComposanteParNomFournisseurJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeComposanteParNomFournisseur = new JScrollPane(listeComposanteParNomFournisseurJList);
        scrollPaneListeComposanteParNomFournisseur.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeComposanteParNomFournisseur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        rechercheComposanteParNomFournisseurPanel.add(composanteLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParNomFournisseurPanel.add(scrollPaneListeComposanteParNomFournisseur, constraints);
        constraints.gridy = 2;
        rechercheComposanteParNomFournisseurPanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 3;
        rechercheComposanteParNomFournisseurPanel.add(nomFournisseurField, constraints);
        constraints.gridy = 4;
        rechercheComposanteParNomFournisseurPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        rechercheComposanteParNomFournisseurPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheComposanteParNomFournisseur(btnContinuer, nomFournisseurField);
        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de composants par nom du fournisseur
     * à une action qui effectue la recherche en fonction du nom du fournisseur saisi.
     * Si des composants correspondant au nom du fournisseur sont trouvés, ils sont affichés dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer               Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param nomFournisseurField   Le champ de texte où l'utilisateur saisit le nom du fournisseur.
     */
    private void onBtnContinuerRechercheComposanteParNomFournisseur(JButton btnContinuer, JTextField nomFournisseurField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFournisseur = nomFournisseurField.getText();
                if (!nomFournisseur.isEmpty()) {
                    String c = dbControleur.rechercherComposantParNomFournisseur(nomFournisseur);
                    if (!c.equals("Aucune composante trouvée...")) {
                        c = "<html>" + c.replace("\n", "<br>") + "</html>";
                        listeModelComposanteParNomFournisseur.clear();
                        listeModelComposanteParNomFournisseur.addElement(c);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucune composante trouvee.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nom de fournisseur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Initialise le panneau de recherche de composants par nom de composant.
     * Affiche une liste de composants dans une liste déroulante (JList) filtrée par nom de composant.
     * L'utilisateur peut entrer un nom de composant dans le champ de texte et cliquer sur le bouton "Continuer"
     * pour rechercher les composants correspondants.
     * Le bouton "Retour au filtre" permet de revenir au panneau de recherche de composants (setRechercheComposantePanel).
     */
    public void setRechercheComposanteParNomPanel() {
        JLabel composanteLabel = new JLabel("Composante");
        JLabel nomComposanteLabel = new JLabel("Entrez le nom de composante");
        JTextField nomComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        listeComposanteParNomJList = new JList<>(listeModelComposanteParNom);
        listeComposanteParNomJList.setCellRenderer(new HTMLListCellRenderer());
        scrollPaneListeComposanteParNom = new JScrollPane(listeComposanteParNomJList);
        scrollPaneListeComposanteParNom.setPreferredSize(new Dimension(250, 200));
        scrollPaneListeComposanteParNom.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        constraints.gridy = 0;
        rechercheComposanteParNomPanel.add(composanteLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParNomPanel.add(scrollPaneListeComposanteParNom, constraints);
        constraints.gridy = 2;
        rechercheComposanteParNomPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 3;
        rechercheComposanteParNomPanel.add(nomComposanteField, constraints);
        constraints.gridy = 4;
        rechercheComposanteParNomPanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        rechercheComposanteParNomPanel.add(btnRetourFiltre, constraints);

        onBtnContinuerRechercheComposanteParNom(btnContinuer, nomComposanteField);
        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de composants par nom de composant
     * à une action qui effectue la recherche en fonction du nom de composant saisi.
     * Si des composants correspondant au nom de composant sont trouvés, ils sont affichés dans la liste déroulante (JList).
     * Sinon, une boîte de dialogue d'erreur est affichée.
     *
     * @param btnContinuer             Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param nomComposanteField Le champ de texte où l'utilisateur saisit le nom de composant.
     */
    private void onBtnContinuerRechercheComposanteParNom(JButton btnContinuer, JTextField nomComposanteField) {
        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomComposante = nomComposanteField.getText();
                if (!nomComposante.isEmpty()) {
                    String c = dbControleur.rechercherComposantParNom(nomComposante);
                    if (!c.equals("Composant non trouver, veuillez verifier le nom")) {
                        c = "<html>" + c.replace("\n", "<br>") + "</html>";
                        listeModelComposanteParNom.clear();
                        listeModelComposanteParNom.addElement(c);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucune composante trouvee.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                mettreAJourFrame();
            }
        });
    }

    /**
     * Affiche le panneau principal (mainPanel) dans le JFrame passé en paramètre.
     * Le panneau précédent est enregistré pour pouvoir y revenir plus tard.
     * La méthode met à jour le contenu du JFrame pour afficher le panneau principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu du JFrame en le revalidant et en le repeignant.
     * Cette méthode est utile lorsque le contenu du JFrame est modifié.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche d'utilisateurs
     * avec une action pour basculer vers le panneau de recherche d'utilisateurs spécifique
     * en fonction de l'option sélectionnée dans le JComboBox.
     *
     * @param btnContinuerUtilisateur Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param comboBox               Le JComboBox contenant les options de recherche d'utilisateurs.
     */
    public void onBtnContinuerUtilisateur(JButton btnContinuerUtilisateur, JComboBox<String> comboBox) {
        btnContinuerUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "Pseudo" -> {
                        jFrame.setContentPane(recherheUtilisateurParPseudoPanel);
                        mettreAJourFrame();
                    }
                    case "Obtenir liste des suiveurs de ? (pseudo voulu)" -> {
                        jFrame.setContentPane(recherheUtilisateurParListeSuiveursPanel);
                        mettreAJourFrame();
                    }
                }
            }
        });
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de fournisseurs
     * avec une action pour basculer vers le panneau de recherche de fournisseurs spécifique
     * en fonction de l'option sélectionnée dans le JComboBox.
     *
     * @param btnContinuerFournisseur Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param comboBox               Le JComboBox contenant les options de recherche de fournisseurs.
     */
    public void onBtnContinuerFournisseur(JButton btnContinuerFournisseur, JComboBox<String> comboBox) {
        btnContinuerFournisseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "Nom" -> {
                        jFrame.setContentPane(recherheFournisseurParNomPanel);
                        mettreAJourFrame();
                    }
                    case "Email" -> {
                        jFrame.setContentPane(recherheFournisseurParEmailPanel);
                        mettreAJourFrame();
                    }
                    case "Adresse" -> {
                        jFrame.setContentPane(recherheFournisseurParAdressePanel);
                        mettreAJourFrame();
                    }
                    case "Type de composante" -> {
                        jFrame.setContentPane(recherheFournisseurParTypeComposantePanel);
                        mettreAJourFrame();
                    }
                }
            }
        });
    }

    /**
     * Associe un événement de clic sur le bouton "Continuer" du panneau de recherche de composants
     * avec une action pour basculer vers le panneau de recherche de composants spécifique
     * en fonction de l'option sélectionnée dans le JComboBox.
     *
     * @param btnContinuerComposante Le bouton "Continuer" pour lequel l'événement de clic est associé.
     * @param comboBox              Le JComboBox contenant les options de recherche de composants.
     */
    public void onBtnContinuerComposante(JButton btnContinuerComposante, JComboBox<String> comboBox) {
        btnContinuerComposante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "Type de la composante" -> {
                        jFrame.setContentPane(rechercheComposanteParTypePanel);
                        mettreAJourFrame();
                    }
                    case "Nom du fournisseur" -> {
                        jFrame.setContentPane(rechercheComposanteParNomFournisseurPanel);
                        mettreAJourFrame();
                    }
                    case "Nom de la composante" -> {
                        jFrame.setContentPane(rechercheComposanteParNomPanel);
                        mettreAJourFrame();
                    }
                }
            }
        });
    }


    /**
     * Affiche une boîte de dialogue d'erreur indiquant que l'utilisateur doit sélectionner "Oui" ou "Non".
     */
    public void affirmerMessageErreurRadioButton() {
        String message = "Vous devez selectionne Oui ou Non. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Associe un événement de clic sur le bouton "Retour" à une action pour revenir au panneau principal (mainPanel).
     *
     * @param btnAnnuler Le bouton "Retour" pour lequel l'événement de clic est associé.
     */
    public void onBtnRetourClicked(JButton btnAnnuler) {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Associe un événement de clic sur le bouton "Retour au filtre" du panneau de recherche d'utilisateurs
     * à une action pour revenir au panneau de recherche d'utilisateurs principal (rechercheUtilisateurPanel).
     *
     * @param btnRetourFiltre Le bouton "Retour au filtre" pour lequel l'événement de clic est associé.
     */
    public void onBtnRetourRechercheUtilisateurClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheUtilisateurPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Associe un événement de clic sur le bouton "Retour au filtre" du panneau de recherche de fournisseurs
     * à une action pour revenir au panneau de recherche de fournisseurs principal (rechercheFournisseurPanel).
     *
     * @param btnRetourFiltre Le bouton "Retour au filtre" pour lequel l'événement de clic est associé.
     */
    public void onBtnRetourRechercheFournisseurClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheFournisseurPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Associe un événement de clic sur le bouton "Retour au filtre" du panneau de recherche de composants
     * à une action pour revenir au panneau de recherche de composants principal (rechercheComposantePanel).
     *
     * @param btnRetourFiltre Le bouton "Retour au filtre" pour lequel l'événement de clic est associé.
     */
    public void onBtnRetourRechercheComposanteClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheComposantePanel);
                mettreAJourFrame();
            }
        });
    }
}