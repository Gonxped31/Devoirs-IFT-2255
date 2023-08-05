package domain.logic.GUI.UtilisateurGUI;

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
 * Interface graphique pour les requêtes publiques d'un utilisateur.
 */
public class RequetePubliqueUtilisateurGUI {
    /**
     * Le pseudonyme de l'utilisateur.
     */
    private String pseudo;
    /**
     * Le contrôleur de base de données utilisé pour gérer les opérations de base de données.
     */
    private DbControleur dbControleur = domain.logic.Controller.DbControleur.getDbControleur();
    /**
     * La fenêtre principale de l'application.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Le panneau principal contenant les éléments disposés avec une disposition en grille.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Le panneau affichant la liste des utilisateurs, disposé avec une disposition en grille.
     */
    private JPanel voirListeUtilisateursPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau affichant la liste des fournisseurs, disposé avec une disposition en grille.
     */
    private JPanel voirListeFournisseursPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau affichant le profil de l'utilisateur, disposé avec une disposition en grille.
     */
    private JPanel voirProfilPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant d'effectuer des recherches d'utilisateurs, disposé avec une disposition en grille.
     */
    private JPanel rechercheUtilisateurPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un utilisateur par son pseudonyme, disposé avec une disposition en grille.
     */
    private JPanel recherheUtilisateurParPseudoPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un utilisateur par son nom, disposé avec une disposition en grille.
     */
    private JPanel recherheUtilisateurParNomPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un utilisateur par son prénom, disposé avec une disposition en grille.
     */
    private JPanel recherheUtilisateurParPrenomPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un utilisateur par sa liste de suiveurs, disposé avec une disposition en grille.
     */
    private JPanel recherheUtilisateurParListeSuiveursPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de récupérer la liste d'activités, disposé avec une disposition en grille.
     */
    private JPanel recupererListeActivitesPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant d'effectuer une recherche d'intérêts, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher des intérêts avec des filtres, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsAvecFiltrePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher des intérêts sans filtre, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsSansFiltrePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher des intérêts par lettre, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsParLettrePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher des intérêts par pseudonyme, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsParPseudoPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher des intérêts par lettre et pseudonyme, disposé avec une disposition en grille.
     */
    private JPanel rechercheInteretsParLettreEtPseudoPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un fournisseur, disposé avec une disposition en grille.
     */
    private JPanel rechercheFournisseurPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un fournisseur par son nom, disposé avec une disposition en grille.
     */
    private JPanel recherheFournisseurParNomPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un fournisseur par son email, disposé avec une disposition en grille.
     */
    private JPanel recherheFournisseurParEmailPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un fournisseur par son adresse, disposé avec une disposition en grille.
     */
    private JPanel recherheFournisseurParAdressePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher un fournisseur par son type de composante, disposé avec une disposition en grille.
     */
    private JPanel recherheFournisseurParTypeComposantePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant d'effectuer une recherche de composante, disposé avec une disposition en grille.
     */
    private JPanel rechercheComposantePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher une composante par son type, disposé avec une disposition en grille.
     */
    private JPanel rechercheComposanteParTypePanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher une composante par le nom du fournisseur, disposé avec une disposition en grille.
     */
    private JPanel rechercheComposanteParNomFournisseurPanel = new JPanel(new GridBagLayout());
    /**
     * Le panneau permettant de rechercher une composante par son nom, disposé avec une disposition en grille.
     */
    private JPanel rechercheComposanteParNomPanel = new JPanel(new GridBagLayout());
    /**
     * Le label affichant un message pour faire une requête publique pour les utilisateurs.
     */
    private JLabel requetePubliqueUtilisateurLabel = new JLabel("Veuillez faire une requete publique", SwingConstants.CENTER);
    /**
     * Le bouton pour afficher la liste d'utilisateurs.
     */
    private JButton btnVoirListeUtilisateurs = new JButton("Voir la liste d'utilisateurs");
    /**
     * Le bouton pour afficher la liste des fournisseurs.
     */
    private JButton btnVoirListeFournisseurs = new JButton("Voir la liste des fournisseurs");
    /**
     * Le bouton pour afficher le profil de l'utilisateur.
     */
    private JButton btnVoirProfil = new JButton("Voir mon profil");
    /**
     * Le bouton pour effectuer une recherche d'utilisateur.
     */
    private JButton btnRechercheUtilisateur = new JButton("Rechercher un utilisateur");
    /**
     * Le bouton pour récupérer la liste des activités.
     */
    private JButton btnRecupererListeActivites = new JButton("Recuperer la liste des activites");
    /**
     * Le bouton pour récupérer la liste des intérêts.
     */
    private JButton btnRecupererListeInterets = new JButton("Recuperer la liste des interets");
    /**
     * Le bouton pour effectuer une recherche de fournisseur.
     */
    private JButton btnRechercheFournisseur = new JButton("Rechercher un fournisseur");
    /**
     * Le bouton pour effectuer une recherche de composante.
     */
    private JButton btnRechercheComposante = new JButton("Rechercher une composante");
    /**
     * Le bouton pour revenir au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu Utilisateur");
    /**
     * Le bouton pour continuer à la prochaine étape.
     */
    private JButton btnChoix = new JButton("Continuer");
    /**
     * Le groupe de boutons pour regrouper un ensemble de boutons radio en permettant la sélection d'une seule option parmi le groupe.
     */
    private ButtonGroup buttonGroup = new ButtonGroup(); // Classe qui permet de regrouper un ensemble de bouton radio en selectionnant qu'une seule option parmi le groupe
    /**
     * Le bouton radio pour répondre "Oui".
     */
    private JRadioButton ouiLabel = new JRadioButton("Oui");
    /**
     * Le bouton radio pour répondre "Non".
     */
    private JRadioButton nonLabel = new JRadioButton("Non");
    /**
     * La liste des utilisateurs.
     */
    private ArrayList<Utilisateur> listeUtilisateur;
    /**
     * La liste des fournisseurs.
     */
    private ArrayList<Fournisseur> listeFournisseur;
    /**
     * La liste des activités.
     */
    private ArrayList<Activite> listeActivites;

    /**
     * L'ensemble des intérêts.
     */
    private HashSet<Interet> listeInterets;

    /**
     * JScrollPane pour toute la classe.
     */
    private JScrollPane scrollPaneListeUtilisateur, scrollPaneListeUtilisateurParPseudo, scrollPaneListeUtilisateurParSuiveur,
            scrollPaneListeFournisseur, scrollPaneListeFournisseurParNom, scrollPaneListeFournisseurParEmail,
            scrollPaneListeFournisseurParAddresse, scrollPaneListeFournisseurParTypeComposante, scrollPaneListeActivites,
            scrollPaneListeInteretsSansFiltre, scrollPaneListeComposanteParNom, scrollPaneListeComposanteParType,
            scrollPaneListeComposanteParNomFournisseur;

    /**
     * JList pour toute la classe.
     */
    private JList<String> listeUtilisateurJList, listeUtilisateurParPseudoJListe, listeUtilisateurParSuiveurJList,
            listeFournisseurJList, listeFournisseurParNomJListe, listeFournisseurParEmailJList, listeFournisseurParAddresseJList,
            listeFournisseurParTypeComposanteJList, listeActiviteJList, listeInteretsJListSansFiltre, listeComposanteParNomJList,
            listeComposanteParTypeJList, listeComposanteParNomFournisseurJList;

    /**
     * Modèle par défaut pour la liste des utilisateurs par pseudonyme.
     */
    private DefaultListModel<String> listeModelUtilisateurParPseudo = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des utilisateurs par suiveur.
     */
    private DefaultListModel<String> listeModelUtilisateurParSuiveur = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des fournisseurs par nom.
     */
    private DefaultListModel<String> listeModelFournisseurParNom = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des fournisseurs par email.
     */
    private DefaultListModel<String> listeModelFournisseurParEmail = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des fournisseurs par adresse.
     */
    private DefaultListModel<String> listeModelFournisseurParAddresse = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des fournisseurs par type de composante.
     */
    private DefaultListModel<String> listeModelFournisseurParTypeComposante = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des composantes par nom.
     */
    private DefaultListModel<String> listeModelComposanteParNom = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des composantes par type.
     */
    private DefaultListModel<String> listeModelComposanteParType = new DefaultListModel<>();
    /**
     * Modèle par défaut pour la liste des composantes par nom de fournisseur.
     */
    private DefaultListModel<String> listeModelComposanteParNomFournisseur = new DefaultListModel<>();
    /**
     * Le conteneur précédent pour stocker un état précédent.
     */
    private Container panelPrecedent = new Container();
    /**
     * Les contraintes pour le positionnement des composants dans un panneau.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel
    /**
     * Interface graphique pour les requêtes publiques de l'utilisateur.
     *
     * @param pseudo Le pseudonyme de l'utilisateur.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ParseException Si une erreur de parsing se produit.
     */
    public RequetePubliqueUtilisateurGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        setMainPanel();
        setVoirListeUtilisateursPanel();
        setVoirListeFournisseursPanel();
        setVoirProfilPanel();

        // Setup de l'option Recherche Utilisateur
        setRechercheUtilisateurPanel();
        setRechercheUtilisateurParPseudoPanel();
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
        btnRecupererListeInterets.addActionListener(new ActionListener() {
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
     * Configure le panneau principal de l'interface graphique.
     * Ajoute les composantes nécessaires au panneau principal.
     */
    public void setMainPanel() {
        requetePubliqueUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(requetePubliqueUtilisateurLabel);
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
        mainPanel.add(btnRecupererListeInterets);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheFournisseur);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRechercheComposante);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Configure le panneau pour afficher la liste des utilisateurs.
     * Ajoute les composantes nécessaires au panneau.
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
    }

    /**
     * Récupère la liste des utilisateurs à partir de la base de données et configure la JList pour les afficher.
     * Associe un gestionnaire d'événements pour afficher les détails d'un utilisateur lorsque sélectionné.
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
     * Affiche les détails d'un utilisateur dans une boîte de dialogue.
     *
     * @param utilisateur L'utilisateur dont les détails doivent être affichés.
     */
    private void detailsUtilisateur(Utilisateur utilisateur) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nom: ").append(utilisateur.getNom()).append("\n");
        messageBuilder.append("Pseudo: ").append(utilisateur.getPseudo()).append("\n");
        messageBuilder.append("Addresse: ").append(utilisateur.getAdresse()).append("\n");
        messageBuilder.append("Telephone: ").append(utilisateur.getTelephone()).append("\n");
        messageBuilder.append("Courriel: ").append(utilisateur.getEmail()).append("\n");
        messageBuilder.append("Nom de la compagnie: ").append(utilisateur.getNomCompagnie()).append("\n");

        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details de l'utilisateur", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Configure le panneau pour afficher la liste des fournisseurs.
     * Ajoute les composantes nécessaires au panneau.
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
     * Récupère la liste des fournisseurs depuis la base de données et configure la JList pour les afficher.
     * Associe un gestionnaire d'événements pour afficher les détails d'un fournisseur lorsque sélectionné.
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
     * Affiche les détails d'un fournisseur dans une boîte de dialogue.
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
        LinkedList<domain.logic.Robot.Robot> robots = fournisseur.getInventaireDeRobot();

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
     * Configure le panneau d'affichage du profil de l'utilisateur.
     * Affiche les informations du profil de l'utilisateur courant.
     */
    public void setVoirProfilPanel() {
        Utilisateur utilisateur = dbControleur.retournerUtilisateur(pseudo);

        JButton btnRetour = new JButton("Retour");
        JLabel nomLabel = new JLabel("Nom");
        JLabel prenomLabel = new JLabel("Prenom");
        JLabel pseudoLabel = new JLabel("Pseudo");
        JLabel adresseLabel = new JLabel("Adresse");
        JLabel courrielLabel = new JLabel("Courriel");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel nbPointsLabel = new JLabel("Nombre de points");
        JLabel nbSuiveursLabel = new JLabel("Nombre de suiveurs");
        JLabel nbInteretLabel = new JLabel("Nombre d'interet");
        JLabel nbActionsLabel = new JLabel("Nombre d'action");
        JLabel nbTachesLabel = new JLabel("Nombre de taches");
        JLabel nbActivitesCreeLabel = new JLabel("Nombre d'activites cree");
        JLabel nbActivitesRejointLabel = new JLabel("Nombre activites rejoint");

        // Il faut remplacer le champ de texte des JTextField par des variables qui recuperent leur informations respectives
        JTextField nomField = new JTextField(utilisateur.getNom());
        JTextField prenomField = new JTextField(utilisateur.getPrenom());
        JTextField pseudoField = new JTextField(utilisateur.getPseudo());
        JTextField adresseField = new JTextField(utilisateur.getAdresse());
        JTextField courrielField = new JTextField(utilisateur.getEmail());
        JTextField telephoneField = new JTextField(utilisateur.getTelephone());
        JTextField nbPointsField = new JTextField(utilisateur.getPoint() + "");
        JTextField nbSuiveursField = new JTextField(utilisateur.getListSuiveur().size() + "");
        JTextField nbInteretField = new JTextField(utilisateur.getListeInteret().size() + "");
        JTextField nbActionsField = new JTextField(utilisateur.getListeActions().size() + "");
        JTextField nbTachesField = new JTextField(utilisateur.getListeTaches().size() + "");
        JTextField nbActivitesCreeField = new JTextField(utilisateur.getListeActivitesCreer().size() + "");
        JTextField nbActivitesRejointField = new JTextField(utilisateur.getListeActivitesRejoint().size() + "");

        // Setup la dimension des JTextField
        nomField.setPreferredSize(new Dimension(200, 30));
        prenomField.setPreferredSize(new Dimension(200, 30));
        pseudoField.setPreferredSize(new Dimension(200, 30));
        adresseField.setPreferredSize(new Dimension(200, 30));
        courrielField.setPreferredSize(new Dimension(200, 30));
        telephoneField.setPreferredSize(new Dimension(200, 30));
        nbPointsField.setPreferredSize(new Dimension(200, 30));
        nbSuiveursField.setPreferredSize(new Dimension(200, 30));
        nbInteretField.setPreferredSize(new Dimension(200, 30));
        nbActionsField.setPreferredSize(new Dimension(200, 30));
        nbTachesField.setPreferredSize(new Dimension(200, 30));
        nbActivitesCreeField.setPreferredSize(new Dimension(200, 30));
        nbActivitesRejointField.setPreferredSize(new Dimension(200, 30));

        // Rendre les JTextField en mode lecture seule
        nomField.setEditable(false);
        prenomField.setEditable(false);
        pseudoField.setEditable(false);
        adresseField.setEditable(false);
        courrielField.setEditable(false);
        telephoneField.setEditable(false);
        nbPointsField.setEditable(false);
        nbSuiveursField.setEditable(false);
        nbInteretField.setEditable(false);
        nbActionsField.setEditable(false);
        nbTachesField.setEditable(false);
        nbActivitesCreeField.setEditable(false);
        nbActivitesRejointField.setEditable(false);


        constraints.gridy = 0;
        voirProfilPanel.add(nomLabel, constraints);
        voirProfilPanel.add(nomField, constraints);
        constraints.gridy = 1;
        voirProfilPanel.add(prenomLabel, constraints);
        voirProfilPanel.add(prenomField, constraints);
        constraints.gridy = 2;
        voirProfilPanel.add(pseudoLabel, constraints);
        voirProfilPanel.add(pseudoField, constraints);
        constraints.gridy = 3;
        voirProfilPanel.add(adresseLabel, constraints);
        voirProfilPanel.add(adresseField, constraints);
        constraints.gridy = 4;
        voirProfilPanel.add(courrielLabel, constraints);
        voirProfilPanel.add(courrielField, constraints);
        constraints.gridy = 5;
        voirProfilPanel.add(telephoneLabel, constraints);
        voirProfilPanel.add(telephoneField, constraints);
        constraints.gridy = 6;
        voirProfilPanel.add(nbPointsLabel, constraints);
        voirProfilPanel.add(nbPointsField, constraints);
        constraints.gridy = 7;
        voirProfilPanel.add(nbSuiveursLabel, constraints);
        voirProfilPanel.add(nbSuiveursField, constraints);
        constraints.gridy = 8;
        voirProfilPanel.add(nbInteretLabel, constraints);
        voirProfilPanel.add(nbInteretField, constraints);
        constraints.gridy = 9;
        voirProfilPanel.add(nbActionsLabel, constraints);
        voirProfilPanel.add(nbActionsField, constraints);
        constraints.gridy = 10;
        voirProfilPanel.add(nbTachesLabel, constraints);
        voirProfilPanel.add(nbTachesField, constraints);
        constraints.gridy = 11;
        voirProfilPanel.add(nbActivitesCreeLabel, constraints);
        voirProfilPanel.add(nbActivitesCreeField, constraints);
        constraints.gridy = 12;
        voirProfilPanel.add(nbActivitesRejointLabel, constraints);
        voirProfilPanel.add(nbActivitesRejointField, constraints);
        constraints.gridy = 13;
        voirProfilPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    /**
     * Configure le panneau de recherche d'utilisateurs.
     * Permet à l'utilisateur de spécifier un critère de filtrage pour la recherche d'utilisateurs.
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
     * Configure le panneau de recherche d'utilisateurs par pseudo.
     * Affiche une liste d'utilisateurs correspondant au pseudo spécifié.
     * Permet à l'utilisateur d'entrer un pseudo et de continuer la recherche.
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
     * Associe un gestionnaire d'événements au bouton "Continuer" dans le panneau de recherche d'utilisateurs par pseudo.
     * Lorsque le bouton est cliqué, récupère le pseudo entré par l'utilisateur, recherche l'utilisateur correspondant,
     * met à jour la liste des utilisateurs trouvés dans le modèle de liste et affiche les résultats dans le panneau.
     * En cas d'erreur ou d'absence de pseudo, affiche des messages d'erreur appropriés.
     *
     * @param btnContinuer Le bouton "Continuer" dans le panneau de recherche.
     * @param pseudoField Le champ texte contenant le pseudo entré par l'utilisateur.
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
     * Configure le panneau de recherche d'utilisateurs par liste de suiveurs.
     * Ce panneau permet à l'utilisateur de rechercher d'autres utilisateurs en fonction du pseudo d'un suiveur.
     * Il affiche une liste des utilisateurs trouvés et fournit des boutons pour continuer ou retourner au filtre.
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
     * Réagit à l'action du bouton "Continuer" dans le panneau de recherche d'utilisateurs par liste de suiveurs.
     * Récupère le pseudo entré par l'utilisateur, effectue une recherche et affiche les résultats dans le panneau.
     * Si aucun utilisateur n'est trouvé ou si aucun utilisateur n'est suivi, un message d'erreur est affiché.
     * Si aucun pseudo n'est entré, une notification d'erreur est également affichée.
     *
     * @param btnContinuer Le bouton "Continuer" sur lequel l'action est déclenchée.
     * @param pseudoField Le champ de texte contenant le pseudo entré par l'utilisateur.
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
     * Configure le panneau d'affichage de la liste des activités.
     * Affiche la liste des activités disponibles et permet à l'utilisateur de sélectionner une activité pour voir les détails.
     * Un bouton "Retour" est également fourni pour revenir au panneau précédent.
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
     * Récupère la liste des activités à partir du contrôleur de base de données.
     * Configure une liste déroulante d'activités disponibles et associe un gestionnaire de sélection à la liste.
     * Lorsqu'une activité est sélectionnée, les détails de l'activité sont affichés et la sélection est effacée.
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
     * Affiche les détails d'une activité dans une boîte de dialogue.
     *
     * @param activite L'objet Activite dont les détails doivent être affichés.
     */
    private void detailsActivites(Activite activite){
        StringBuilder messageBuilder = new StringBuilder("Nom: ");
        messageBuilder.append(activite.getNom()).append("\n")
                .append("Auteur: ").append(activite.getAuteur()).append("\n")
                .append("Date de debut: ").append(GestionDates.parseDateToString(activite.getDateDebut())).append("\n")
                .append("Date de fin: ").append(GestionDates.parseDateToString(activite.getDateFin())).append("\n")
                .append("Taches de l'activite: ").append("\n")
                .append(activite.getListeDeTache().stream()
                        .map(Tache::getNom)
                        .collect(Collectors.joining("\n")));

        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Details de l'activite", JOptionPane.INFORMATION_MESSAGE);
    }


    /*public void setRechercheInteretsPanel() {
        // Ajout des interets dans un ButtonGroup
        buttonGroup.add(ouiLabel);
        buttonGroup.add(nonLabel);

        // Définition des actionCommand pour chaque JRadioButton
        ouiLabel.setActionCommand("Oui");
        nonLabel.setActionCommand("Non");

        JLabel filtreLabel = new JLabel("Voulez vous appliquer un filtre?");
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheInteretsPanel.add(filtreLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsPanel.add(ouiLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsPanel.add(nonLabel, constraints);
        constraints.gridy = 2;
        rechercheInteretsPanel.add(btnChoix, constraints);
        constraints.gridy = 3;
        rechercheInteretsPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }
    public void setRechercheInteretsAvecFiltrePanel() {
        JLabel filtreLabel = new JLabel("Filtrer par");
        String[] options = {"3 premieres lettres", "Pseudo utilisateur", "Les deux"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheInteretsAvecFiltrePanel.add(filtreLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsAvecFiltrePanel.add(comboBox, constraints);
        constraints.gridy = 2;
        rechercheInteretsAvecFiltrePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheInteretsAvecFiltrePanel.add(btnRetour, constraints);

        onBtnContinuerInterets(btnContinuer, comboBox);
        onBtnRetourRechercheInteretsClicked(btnRetour);
    }*/

    /**
     * Configure le panneau d'affichage des intérêts sans filtre.
     * Affiche la liste des intérêts disponibles.
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
     * Récupère la liste des intérêts sans filtre depuis le contrôleur de base de données
     * et les affiche dans une liste déroulante.
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


    /*public void setRechercheInteretsParLettrePanel() {
        JLabel caracteresLabel = new JLabel("Entrez vos 3 characteres");
        JTextField caracteresField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheInteretsParLettrePanel.add(caracteresLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsParLettrePanel.add(caracteresField, constraints);
        constraints.gridy = 2;
        rechercheInteretsParLettrePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheInteretsParLettrePanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }

    public void setRechercheInteretsParPseudoPanel() {
        JLabel pseudoLabel = new JLabel("Entrez le pseudo de l'utilisateur");
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheInteretsParPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsParPseudoPanel.add(pseudoField, constraints);
        constraints.gridy = 2;
        rechercheInteretsParPseudoPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheInteretsParPseudoPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }

    public void setRechercheInteretsParLettreEtPseudoPanel() {
        JLabel caracteresLabel = new JLabel("Entrez vos 3 characteres");
        JLabel pseudoLabel = new JLabel("Entrez le pseudo de l'utilisateur");
        JTextField caracteresField = new JTextField();
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheInteretsParLettreEtPseudoPanel.add(caracteresLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsParLettreEtPseudoPanel.add(caracteresField, constraints);
        constraints.gridy = 2;
        rechercheInteretsParLettreEtPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 3;
        rechercheInteretsParLettreEtPseudoPanel.add(pseudoField, constraints);
        constraints.gridy = 4;
        rechercheInteretsParLettreEtPseudoPanel.add(btnContinuer, constraints);
        constraints.gridy = 5;
        rechercheInteretsParLettreEtPseudoPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }*/


    /**
     * Initialise le panneau de recherche de fournisseurs avec des options de filtrage.
     * Le panneau contient une liste déroulante pour choisir un critère de filtrage,
     * des boutons pour continuer et retourner en arrière.
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
     * Le panneau contient une liste de fournisseurs correspondant au nom recherché,
     * un champ de texte pour entrer le nom du fournisseur, des boutons pour continuer
     * et retourner au filtre de recherche.
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
     * Gère l'action lorsque le bouton "Continuer" est cliqué dans le panneau de recherche de fournisseurs par nom.
     * Récupère le nom saisi dans le champ de texte, effectue une recherche de fournisseur par nom dans la base de données,
     * met à jour la liste des fournisseurs correspondants dans le modèle de liste, et affiche les résultats dans le panneau.
     * Si aucun fournisseur correspondant n'est trouvé, affiche un message d'erreur approprié.
     * Si le champ de saisie est vide, affiche un message d'erreur indiquant de saisir un nom.
     * Met également à jour l'affichage de la fenêtre principale.
     *
     * @param btnContinuer Le bouton "Continuer" sur lequel l'action est déclenchée.
     * @param nomField Le champ de texte contenant le nom du fournisseur à rechercher.
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
     * Configure le panneau de recherche de fournisseurs par email.
     * Ajoute des éléments tels que des étiquettes, des champs de texte et des boutons au panneau
     * pour permettre à l'utilisateur d'effectuer une recherche de fournisseur par email.
     * Le panneau affichera également la liste des fournisseurs correspondants et permettra à l'utilisateur
     * de continuer pour afficher les détails d'un fournisseur sélectionné ou de revenir au filtre de recherche.
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
     * Écouteur pour le bouton "Continuer" lors de la recherche de fournisseurs par email.
     * Cette méthode est appelée lorsque le bouton est cliqué.
     * Elle récupère l'email saisi par l'utilisateur, effectue une recherche de fournisseur correspondant dans la base de données,
     * met à jour le modèle de la liste des fournisseurs par email avec le résultat, ou affiche un message d'erreur si aucun fournisseur n'est trouvé.
     * Enfin, elle met à jour l'interface utilisateur pour refléter les changements.
     *
     * @param btnContinuer Le bouton "Continuer" cliqué.
     * @param emailField Le champ de texte contenant l'email saisi par l'utilisateur.
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
     * Configure le panneau de recherche de fournisseurs par adresse.
     * Ce panneau permet à l'utilisateur de rechercher des fournisseurs en fonction de leur adresse.
     * Il contient des champs de saisie pour entrer l'adresse, un bouton "Continuer" pour lancer la recherche,
     * et un bouton "Retour au filtre" pour revenir au panneau de filtre de recherche.
     * Une liste des fournisseurs correspondant à l'adresse saisie est affichée dans un composant de liste.
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
     * Gère l'action lorsqu'on clique sur le bouton "Continuer" pour rechercher des fournisseurs par adresse.
     *
     * @param btnContinuer Le bouton "Continuer" qui déclenche l'action.
     * @param addresseField Le champ de texte contenant l'adresse saisie par l'utilisateur.
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
     * Configure le panneau de recherche des fournisseurs par type de composante.
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
     * Gère l'action lors du clic sur le bouton "Continuer" dans le panneau de recherche des fournisseurs par type de composante.
     *
     * @param btnContinuer Le bouton "Continuer" sur lequel l'action a lieu.
     * @param typeComposanteField Le champ de texte pour entrer le type de composante.
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

    /**
     * Configure le panneau de recherche de composantes.
     * Ce panneau permet de filtrer les composantes selon différents critères.
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
     * Configure le panneau de recherche de composantes.
     * Ce panneau permet de filtrer les composantes selon différents critères.
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
     * Gère l'action du bouton "Continuer" dans le panneau de recherche de composantes par type.
     * Cette méthode est appelée lorsque le bouton est cliqué.
     *
     * @param btnContinuer Le bouton "Continuer" sur lequel l'action est associée.
     * @param typeComposanteField Le champ de texte contenant le type de composante entré par l'utilisateur.
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
     * Définit le panneau de recherche de composantes par nom de fournisseur.
     * Ce panneau permet à l'utilisateur de rechercher des composantes en fonction du nom du fournisseur.
     * Il affiche une liste des composantes correspondantes et permet de sélectionner une composante pour afficher ses détails.
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
     * Gère l'action du bouton "Continuer" lors de la recherche de composantes par nom de fournisseur.
     * Cette méthode est appelée lorsque le bouton "Continuer" est cliqué.
     *
     * @param btnContinuer       Le bouton "Continuer" associé à l'action.
     * @param nomFournisseurField Le champ de texte contenant le nom du fournisseur saisi par l'utilisateur.
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
     * Configure le panneau de recherche de composantes par nom.
     * Ce panneau affiche une liste de composantes correspondant au nom saisi par l'utilisateur.
     * L'utilisateur peut sélectionner une composante pour afficher ses détails.
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
     * Gère l'action du bouton "Continuer" dans le panneau de recherche de composantes par nom.
     * Lorsque le bouton est cliqué, cette méthode est déclenchée pour effectuer la recherche de composantes par nom.
     *
     * @param btnContinuer Le bouton "Continuer" sur lequel l'action est associée.
     * @param nomComposanteField Le champ de texte contenant le nom de la composante saisi par l'utilisateur.
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
     * Affiche le panneau principal de l'application dans le JFrame donné.
     *
     * @param jFrame Le JFrame dans lequel le panneau principal doit être affiché.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour le contenu du JFrame en révalidant et en redessinant celui-ci.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Associe une action au bouton "Continuer" dans le panneau de recherche d'utilisateurs.
     * L'action dépend de la sélection dans la liste déroulante.
     *
     * @param btnContinuerUtilisateur Le bouton "Continuer" sur lequel l'action est associée.
     * @param comboBox La liste déroulante contenant les options de recherche.
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
     * Associe une action au bouton "Continuer" dans le panneau de recherche d'intérêts.
     * L'action dépend de la sélection dans la liste déroulante.
     *
     * @param btnContinuerInterets Le bouton "Continuer" sur lequel l'action est associée.
     * @param comboBox La liste déroulante contenant les options de recherche.
     */
    public void onBtnContinuerInterets(JButton btnContinuerInterets, JComboBox<String> comboBox) {
        btnContinuerInterets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "3 premieres lettres" -> {
                        jFrame.setContentPane(rechercheInteretsParLettrePanel);
                        mettreAJourFrame();
                    }
                    case "Pseudo utilisateur" -> {
                        jFrame.setContentPane(rechercheInteretsParPseudoPanel);
                        mettreAJourFrame();
                    }
                    case "Les deux" -> {
                        jFrame.setContentPane(rechercheInteretsParLettreEtPseudoPanel);
                        mettreAJourFrame();
                    }
                }
            }
        });
    }

    /**
     * Associe une action au bouton "Continuer" dans le panneau de recherche de fournisseurs.
     * L'action dépend de la sélection dans la liste déroulante.
     *
     * @param btnContinuerFournisseur Le bouton "Continuer" sur lequel l'action est associée.
     * @param comboBox La liste déroulante contenant les options de recherche.
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
     * Associe une action au bouton "Continuer" dans le panneau de recherche de composantes.
     * L'action dépend de la sélection dans la liste déroulante.
     *
     * @param btnContinuerComposante Le bouton "Continuer" sur lequel l'action est associée.
     * @param comboBox La liste déroulante contenant les options de recherche.
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
     * Affiche un message d'erreur pour la sélection d'un bouton radio manquant.
     */
    public void affirmerMessageErreurRadioButton() {
        String message = "Vous devez selectionne Oui ou Non. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Associe une action au bouton "Retour" pour revenir au panneau principal.
     *
     * @param btnAnnuler Le bouton "Retour" sur lequel l'action est associée.
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
     * Associe une action au bouton "Retour" pour revenir au panneau de recherche d'utilisateurs.
     *
     * @param btnRetourFiltre Le bouton "Retour" sur lequel l'action est associée.
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
     * Associe une action au bouton "Retour" pour revenir au panneau principal depuis le panneau de recherche d'intérêts.
     *
     * @param btnRetourFiltre Le bouton "Retour" sur lequel l'action est associée.
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

    /**
     * Associe une action au bouton "Retour" pour revenir au panneau de recherche d'intérêts avec filtre.
     *
     * @param btnRetourFiltre Le bouton "Retour" sur lequel l'action est associée.
     */
    public void onBtnRetourRechercheInteretsAvecFiltreClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheInteretsAvecFiltrePanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Associe une action au bouton "Retour" pour revenir au panneau de recherche de fournisseurs.
     *
     * @param btnRetourFiltre Le bouton "Retour" sur lequel l'action est associée.
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
     * Associe une action au bouton "Retour" pour revenir au panneau de recherche de composantes.
     *
     * @param btnRetourFiltre Le bouton "Retour" sur lequel l'action est associée.
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
