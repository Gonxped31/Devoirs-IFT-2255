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
import java.util.LinkedList;
import java.util.stream.Collectors;

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
    private ArrayList<Interet> listeInterets;

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

    }//Done

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
    public void setVoirProfilPanel() {
        Fournisseur fournisseur = dbControleur.retournerFournisseur(nomFournisseur);

        JLabel nomLabel = new JLabel("Nom");
        JLabel adresseLabel = new JLabel("Adresse");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel typeRobotLabel = new JLabel("Type de robot a fabriquer");
        JLabel typeComposanteLabel = new JLabel("Type de composante a fabriquer");
        JButton btnRetour = new JButton("Retour");

        // Il faut remplacer le champ de texte des JTextField par des variables qui recuperent leur informations respectives
        JTextField nomField = new JTextField(fournisseur.getNom());
        JTextField adresseField = new JTextField(fournisseur.getAdresse());
        JTextField telephoneField = new JTextField(fournisseur.getTelephone());
        JTextField typeRobotField = new JTextField(fournisseur.getTypeRobotFabriquer());
        JTextField typeComposanteField = new JTextField(fournisseur.getTypeComposantesFabriquer());

        // Setup la dimension des JTextField
        nomField.setPreferredSize(new Dimension(200, 30));
        adresseField.setPreferredSize(new Dimension(200, 30));
        telephoneField.setPreferredSize(new Dimension(200, 30));
        typeRobotField.setPreferredSize(new Dimension(200, 30));
        typeComposanteField.setPreferredSize(new Dimension(200, 30));

        // Rendre les JTextField en mode lecture seule
        nomField.setEditable(false);
        adresseField.setEditable(false);
        telephoneField.setEditable(false);
        typeRobotField.setEditable(false);
        typeComposanteField.setEditable(false);

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
        voirProfilPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }//Done


    //  Recherche utilisateur
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

    /*public void setRechercheUtilisateurParNomPanel() {
        JLabel nomLabel = new JLabel("Entrez le nom");
        JTextField nomField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        nomField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        recherheUtilisateurParNomPanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParNomPanel.add(nomField, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParNomPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParNomPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }

    public void setRechercheUtilisateurParPrenomPanel() {
        JLabel prenomLabel = new JLabel("Entrez le prenom");
        JTextField prenomField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheUtilisateurParPrenomPanel.add(prenomLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParPrenomPanel.add(prenomField, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParPrenomPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParPrenomPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }*/

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
   /* public void setRechercheInteretsPanel() {
        // Ajout des interets dans un ButtonGroup
        buttonGroup.add(ouiLabel);
        buttonGroup.add(nonLabel);

        // Définition des actionCommand pour chaque JRadioButton
        //ouiLabel.setActionCommand("Oui");
        //nonLabel.setActionCommand("Non");

        //JLabel filtreLabel = new JLabel("Voulez vous appliquer un filtre?");
        //JButton btnRetour = new JButton("Retour");

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
    }*/

    /*public void setRechercheInteretsAvecFiltrePanel() {
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
        JLabel listeInteretLabel = new JLabel("Liste interets");
        JLabel caracteresLabel = new JLabel("Entrez vos 3 caracteres");
        JTextField caracteresField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheInteretsParLettreEtPseudoPanel.add(listeInteretLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsParLettreEtPseudoPanel.add(scrollPaneListeInteretsParLettre, constraints);
        constraints.gridy = 2;
        rechercheInteretsParLettrePanel.add(caracteresLabel, constraints);
        constraints.gridy = 3;
        rechercheInteretsParLettrePanel.add(caracteresField, constraints);
        constraints.gridy = 4;
        rechercheInteretsParLettrePanel.add(btnContinuer, constraints);
        constraints.gridy = 4;
        rechercheInteretsParLettrePanel.add(btnRetourFiltre, constraints);

        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caracteresField.getText().length() != 3) {
                    affirmerMessageErreurCaracteres();
                }
            };
        });

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }*/
    /*
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
        constraints.gridy = 2;
        rechercheInteretsParPseudoPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }

    public void setRechercheInteretsParLettreEtPseudoPanel() {
        JLabel listeInteretLabel = new JLabel("Liste interets");
        JLabel caracteresLabel = new JLabel("Entrez vos 3 characteres");
        JLabel pseudoLabel = new JLabel("Entrez le pseudo de l'utilisateur");
        JTextField caracteresField = new JTextField();
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheInteretsParLettreEtPseudoPanel.add(listeInteretLabel, constraints);
        constraints.gridy = 1;
        rechercheInteretsParLettreEtPseudoPanel.add(listeInteretsJList, constraints);
        constraints.gridy = 2;
        rechercheInteretsParLettreEtPseudoPanel.add(caracteresLabel, constraints);
        constraints.gridy = 3;
        rechercheInteretsParLettreEtPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 4;
        rechercheInteretsParLettreEtPseudoPanel.add(pseudoField, constraints);
        constraints.gridy = 5;
        rechercheInteretsParLettreEtPseudoPanel.add(btnContinuer, constraints);
        constraints.gridy = 5;
        rechercheInteretsParLettreEtPseudoPanel.add(btnRetourFiltre, constraints);

        btnContinuer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caracteresField.getText().length() != 3) {
                    affirmerMessageErreurCaracteres();
                }
            };
        });

        onBtnRetourRechercheInteretsAvecFiltreClicked(btnRetourFiltre);
    }
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

    public void onBtnRetourRechercheInteretsClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }
    /*public void onBtnRetourRechercheInteretsAvecFiltreClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheInteretsAvecFiltrePanel);
                mettreAJourFrame();
            }
        });
    }*/


    //Recherche fournisseur
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



    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

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

    public void affirmerMessageErreurRadioButton() {
        String message = "Vous devez selectionne Oui ou Non. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void onBtnRetourClicked(JButton btnAnnuler) {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainPanel);
                mettreAJourFrame();
            }
        });
    }

    public void onBtnRetourRechercheUtilisateurClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheUtilisateurPanel);
                mettreAJourFrame();
            }
        });
    }


    public void onBtnRetourRechercheFournisseurClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheFournisseurPanel);
                mettreAJourFrame();
            }
        });
    }

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