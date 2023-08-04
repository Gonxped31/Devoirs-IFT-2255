package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.TypeNotification;
import domain.logic.Robot.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Cette classe gère l'interface pour la fonctionalité "Reseau social de l'application."
 */
public class GestionReseauGUI {
    /**
     * Le contrôleur pour gérer les utilisateurs.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Le contrôleur pour accéder à une base de données.
     */
    private DbControleur dbControlleur = DbControleur.getDbControleur();
    /**
     * Le pseudo de l'utilisateur.
     */
    private String pseudo;
    /**
     * La fenêtre principale pour l'interface graphique.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Un panneau principal avec un agencement en grille de 1 colonne et un nombre de lignes dynamique.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour gérer le processus de suivi d'un utilisateur.
     */
    private JPanel suivreUtilisateurPanel = new JPanel(new GridBagLayout());
    /**
     * Un panneau pour gérer la liste des suiveurs de l'utilisateur.
     */
    private JPanel gererSuiveursPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour afficher la liste des abonnés de l'utilisateur.
     */
    private JPanel voirListeAbonnesPanel = new JPanel(new GridBagLayout());
    /**
     * Un panneau pour supprimer un abonné de la liste des abonnés de l'utilisateur.
     */
    private JPanel supprimerAbonnePanel = new JPanel(new GridBagLayout());
    /**
     * Un panneau pour ajouter des intérêts à l'utilisateur.
     */
    private JPanel ajouterInteretsPanel = new JPanel(new GridBagLayout());
    /**
     * Un panneau pour modifier les intérêts de l'utilisateur.
     */
    private JPanel modifierInteretsPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour afficher les intérêts auxquels l'utilisateur est abonné.
     */
    private JPanel abonnerInteretPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour se désabonner d'intérêts.
     */
    private JPanel desabonnerInteretPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour afficher les intérêts à supprimer.
     */
    private JPanel getSupprimerInteretsPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour supprimer des intérêts.
     */
    private JPanel supprimerInteretsPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Un panneau pour gérer les intérêts de l'utilisateur.
     */
    private JPanel gererInteretsPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Une étiquette pour le titre de la gestion du réseau.
     */
    private JLabel gestionReseauLabel = new JLabel("Gestion reseau", SwingConstants.CENTER);
    /**
     * Un bouton pour suivre un utilisateur.
     */
    private JButton btnSuivreUtilisateur = new JButton("Suivre un utilisateur");
    /**
     * Un bouton pour gérer les suiveurs de l'utilisateur.
     */
    private JButton btnGererSuiveurs = new JButton("Gerer mes suiveurs");
    /**
     * Un bouton pour gérer les intérêts de l'utilisateur.
     */
    private JButton btnGererInterets = new JButton("Gerer mes interets");
    /**
     * Un bouton pour revenir au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Un conteneur pour stocker le panneau précédent lors de la navigation dans l'interface.
     */
    private Container panelPrecedent = new Container();
    /**
     * Une liste d'objets "Interet" représentant les intérêts de l'utilisateur.
     */
    private HashSet<Interet> listeInteret;
    /**
     * Plusieurs panneaux de défilement pour les zones de texte avec barre de défilement.
     */
    private JScrollPane scrollPaneVoirMesAbonnes;
    private JScrollPane scrollPaneAjouterInteret;
    private JScrollPane scrollPaneModifierInteret;
    private JScrollPane scrollPaneSupprimerInteret;
    private  JScrollPane scrollPaneAbonnerInteret;
    private JScrollPane scrollPaneDesabonnerInteret;
    /**
     * Classe qui definit la maniere dont les composants seront places dans un panel
     */
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Constructeur de la classe GestionReseauGUI qui initialise l'interface graphique pour gérer le réseau de l'utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @throws IOException    Si une erreur d'entrée/sortie se produit lors de l'accès aux fichiers.
     * @throws ParseException Si une erreur de parsing se produit lors de l'accès aux données.
     */
    public GestionReseauGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setSuivreUtilisateurPanel();
        setGererSuiveursPanel();
        setVoirListeAbonnesPanel();
        setSupprimerAbonnePanel();
        setGererInteretsPanel();
        setAjouterInteretPanel();
        setModifierInteretsPanel();
        setSupprimerInteretPanel();
        setAbonneInteret();
        setDesabonneInteret();

        btnSuivreUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(suivreUtilisateurPanel);
                mettreAJourFrame();
            }
        });
        btnGererSuiveurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(gererSuiveursPanel);
                mettreAJourFrame();
            }
        });
        btnGererInterets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(gererInteretsPanel);
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
     * Configure le panneau principal (mainPanel) de l'interface graphique GestionReseauGUI.
     * Il ajoute les composantes pour gérer le réseau de l'utilisateur, notamment des boutons pour suivre un utilisateur,
     * gérer ses suiveurs et ses intérêts, ainsi qu'un bouton de retour au menu utilisateur.
     */
    public void setMainPanel() {
        gestionReseauLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionReseauLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnSuivreUtilisateur);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnGererSuiveurs);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnGererInterets);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Configure le panneau "suivreUtilisateurPanel" de l'interface graphique GestionReseauGUI.
     * Il initialise les composantes telles que les labels, le champ de texte et les boutons pour permettre
     * à l'utilisateur de saisir le pseudo d'un autre utilisateur qu'il souhaite suivre.
     * Ce panneau comprend également les ActionListener pour les boutons "Suivre cet utilisateur" et "Annuler".
     *
     * @see #onBtnSuivreUtilisateurClicked(JButton, JTextField)
     * @see #onBtnAnnulerClicked(JButton)
     */
    public void setSuivreUtilisateurPanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel pseudoLabel = new JLabel("Quel est le pseudo de l'utilisateur que vous voulez suivre");
        JTextField pseudoField = new JTextField();
        JButton btnSuivreUtilisateur = new JButton("Suivre cet utilisateur");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension du JTextField
        pseudoField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        suivreUtilisateurPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        suivreUtilisateurPanel.add(pseudoField, constraints);
        constraints.gridy = 2;
        suivreUtilisateurPanel.add(btnSuivreUtilisateur, constraints);
        constraints.gridy = 3;
        suivreUtilisateurPanel.add(btnAnnuler, constraints);

        onBtnSuivreUtilisateurClicked(btnSuivreUtilisateur, pseudoField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panneau "gererSuiveursPanel" de l'interface graphique GestionReseauGUI.
     * Il initialise les composantes telles que les labels, les boutons et leur police pour permettre
     * à l'utilisateur de gérer ses suiveurs. Ce panneau comprend également les ActionListener pour les boutons
     * "Voir a qui je suis abonne", "Arêter de suivre un utilisateur" et "Retour au menu gestion reseau".
     *
     * @see #onBtnAnnulerClicked(JButton)
     */
    public void setGererSuiveursPanel() {
        JLabel gererSuiveursTitre = new JLabel("Gerer mes suiveurs", SwingConstants.CENTER);
        JLabel gererSuiveursLabel = new JLabel("Que voulez-vous faire?", SwingConstants.CENTER);
        JButton btnVoirAbonnes = new JButton("Voir a qui je suis abonne");
        JButton btnSupprimerAbonne = new JButton("Arêter de suivre un utilisateur.");
        JButton btnRetourMenuReseau = new JButton("Retour au menu gestion reseau");

        gererSuiveursTitre.setFont(new Font("Arial", Font.BOLD, 24));
        gererSuiveursLabel.setFont(new Font("Arial", Font.BOLD, 18));

        gererSuiveursPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gererSuiveursPanel.add(gererSuiveursTitre);
        gererSuiveursPanel.add(Box.createHorizontalStrut(10));
        gererSuiveursPanel.add(gererSuiveursLabel);
        gererSuiveursPanel.add(Box.createHorizontalStrut(10));
        gererSuiveursPanel.add(btnVoirAbonnes);
        gererSuiveursPanel.add(Box.createHorizontalStrut(10));
        gererSuiveursPanel.add(btnSupprimerAbonne);
        gererSuiveursPanel.add(Box.createHorizontalStrut(10));
        gererSuiveursPanel.add(btnRetourMenuReseau);

        btnVoirAbonnes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(voirListeAbonnesPanel);
                mettreAJourFrame();
            }
        });
        btnSupprimerAbonne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(supprimerAbonnePanel);
                mettreAJourFrame();
            }
        });
        onBtnAnnulerClicked(btnRetourMenuReseau); // ActionListener Event lorsqu'on clique le bouton btnRetourMenuReseau
    }

    /**
     * Configure le panneau "voirListeAbonnesPanel" de l'interface graphique GestionReseauGUI.
     * Il initialise les composantes pour afficher la liste des abonnements de l'utilisateur.
     * Ce panneau comprend également le bouton "Retour au menu précédent".
     *
     * @see #recupererListeDeMesAbonnes()
     * @see #onBtnRetourAbonnees(JButton)
     */
    public void setVoirListeAbonnesPanel() {
        JLabel listeAbonnementsLabel = new JLabel("Voici la liste de vos abonnements");
        recupererListeDeMesAbonnes();
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");
        listeAbonnementsLabel.setFont(new Font("Arial", Font.BOLD, 24));

        constraints.gridy = 0;
        voirListeAbonnesPanel.add(listeAbonnementsLabel, constraints);
        constraints.gridy = 1;
        voirListeAbonnesPanel.add(scrollPaneVoirMesAbonnes, constraints);
        constraints.gridy = 2;
        voirListeAbonnesPanel.add(btnRetourMenuReseau, constraints);

        onBtnRetourAbonnees(btnRetourMenuReseau);
    }

    /**
     * Récupère la liste des abonnements de l'utilisateur et les affiche dans le panneau "voirListeAbonnesPanel".
     * Les abonnements sont récupérés via le controlleurUtilisateurs en utilisant le pseudo de l'utilisateur actuel.
     * Chaque abonnement est affiché sous forme de label dans le panneau "suiveursPanel".
     * Le panneau "suiveursPanel" est ensuite placé dans un JScrollPane pour permettre le défilement vertical
     * en cas de dépassement de la taille du panneau.
     */
    public void recupererListeDeMesAbonnes(){
        ArrayList<String> listePseudoUtilisateur = controlleurUtilisateurs.voirListeUtilisateur(pseudo);

        JPanel suiveursPanel = new JPanel();
        suiveursPanel.setLayout(new BoxLayout(suiveursPanel, BoxLayout.Y_AXIS));

        for (String pseudoUtil: listePseudoUtilisateur) {
            JLabel label = new JLabel(pseudoUtil);
            suiveursPanel.add(label);
        }

        scrollPaneVoirMesAbonnes = new JScrollPane(suiveursPanel);
        scrollPaneVoirMesAbonnes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Configure le panneau "supprimerAbonnePanel" de l'interface graphique GestionReseauGUI.
     * Il initialise les composantes pour afficher la liste des abonnements de l'utilisateur,
     * ainsi qu'un champ de texte pour saisir le pseudo de l'utilisateur à supprimer de la liste.
     * Ce panneau comprend également les boutons "Supprimer cet abonne" et "Retour au menu précédent".
     *
     * @see #recupererListeDeMesAbonnes()
     * @see #onBtnRetourAbonnees(JButton)
     * @see #onBtnSupprimerAbonneClicked(JButton, JTextField)
     */
    private void setSupprimerAbonnePanel() {
        JLabel supprimerAbonneLabel = new JLabel("Quel utilisateur voulez vous supprimer de votre liste");
        recupererListeDeMesAbonnes();
        JTextField supprimerAbonneField = new JTextField();
        JButton btnSupprimer = new JButton("Supprimer cet abonne");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        supprimerAbonneField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        supprimerAbonnePanel.add(supprimerAbonneLabel, constraints);
        constraints.gridy = 1;
        supprimerAbonnePanel.add(scrollPaneVoirMesAbonnes, constraints);
        constraints.gridy = 2;
        supprimerAbonnePanel.add(supprimerAbonneField, constraints);
        constraints.gridy = 3;
        supprimerAbonnePanel.add(btnSupprimer, constraints);
        constraints.gridy = 4;
        supprimerAbonnePanel.add(btnRetourMenuReseau, constraints);

        onBtnRetourAbonnees(btnRetourMenuReseau);
        onBtnSupprimerAbonneClicked(btnSupprimer, supprimerAbonneField);
    }

    /**
     * Associe un ActionListener au bouton "Retour" (btnRetourMenuReseau) du panneau "gererSuiveursPanel".
     * Lorsque le bouton "Retour" est cliqué, le panneau "gererSuiveursPanel" est affiché dans la fenêtre principale (jFrame).
     *
     * @param btnRetourMenuReseau Le bouton "Retour" (btnRetourMenuReseau) du panneau "gererSuiveursPanel".
     */
    private void onBtnRetourAbonnees(JButton btnRetourMenuReseau) {
        btnRetourMenuReseau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(gererSuiveursPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Configure le panneau "gererInteretsPanel" de l'interface graphique GestionReseauGUI.
     * Il initialise les composantes pour afficher les différentes options de gestion des intérêts :
     * - Ajouter un intérêt
     * - Modifier un intérêt
     * - Supprimer un intérêt
     * - S'abonner à un intérêt
     * - Se désabonner d'un intérêt
     * Ce panneau comprend également le bouton "Retour" pour revenir au menu précédent.
     *
     * @see #setAjouterInteretPanel()
     * @see #setModifierInteretsPanel()
     * @see #setSupprimerInteretPanel()
     * @see #setAbonneInteret()
     * @see #setDesabonneInteret()
     * @see #onBtnRetourMenuReseauClicked(JButton)
     */
    private void setGererInteretsPanel() {
        JLabel gererInteretsTitre = new JLabel("Gerer mes interets", SwingConstants.CENTER);
        JLabel gererInteretsLabel = new JLabel("Que voulez-vous faire?", SwingConstants.CENTER);
        JButton btnAjouterInteret = new JButton("Ajouter un interet");
        JButton btnModifierInteret = new JButton("Modifier un interet");
        JButton btnSupprimerInteret = new JButton("Supprimer un interet");
        JButton btnAbonnerInteret = new JButton("S'abonner a un interet");
        JButton btnDesabonnerInteret = new JButton("Se desabonner d'un interet");
        JButton btnRetour = new JButton("Retour");

        gererInteretsTitre.setFont(new Font("Arial", Font.BOLD, 24));
        gererInteretsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        gererInteretsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gererInteretsPanel.add(gererInteretsTitre);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(gererInteretsLabel);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnAjouterInteret);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnModifierInteret);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnSupprimerInteret);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnAbonnerInteret);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnDesabonnerInteret);
        gererInteretsPanel.add(Box.createHorizontalStrut(10));
        gererInteretsPanel.add(btnRetour);

        //ADD LISTENERS
        btnAjouterInteret.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jFrame.setContentPane(ajouterInteretsPanel);
                        mettreAJourFrame();
                    }
                });
        btnModifierInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierInteretsPanel);
                mettreAJourFrame();
            }
        });

        btnSupprimerInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(supprimerInteretsPanel);
                mettreAJourFrame();
            }
        });

        btnAbonnerInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(abonnerInteretPanel);
                mettreAJourFrame();
            }
        });

        btnDesabonnerInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(desabonnerInteretPanel);
                mettreAJourFrame();
            }
        });
        onBtnAnnulerClicked(btnRetour);
    }

    /**
     * Configure le panneau "ajouterInteretsPanel" de l'interface graphique GestionReseauGUI.
     * Ce panneau permet à l'utilisateur d'ajouter un nouvel intérêt au système Robotix.
     * Il comprend un champ de texte pour saisir le nom du nouvel intérêt et un bouton "Ajouter interet" pour effectuer l'ajout.
     * Le panneau contient également un bouton "Retour au menu précédent" pour revenir au menu de gestion des intérêts.
     *
     * @see #onBtnRetourMenuReseauClicked(JButton)
     * @see #onBtnAjouterInteretClicked(JButton, JTextField)
     */
    private void setAjouterInteretPanel(){
        JLabel ajouterInteretLabel = new JLabel("Quel interet voulez ajouter au systeme Robotix?");
        JTextField ajouterInteretField = new JTextField();
        JButton btnAjouter = new JButton("Ajouter interet");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        ajouterInteretField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        ajouterInteretsPanel.add(ajouterInteretLabel, constraints);
        constraints.gridy = 1;
        ajouterInteretsPanel.add(ajouterInteretField, constraints);
        //constraints.gridy = 2;
        //supprimerAbonnePanel.add(supprimerAbonneField, constraints);
        constraints.gridy = 2;
        ajouterInteretsPanel.add(btnAjouter, constraints);
        constraints.gridy = 3;
        ajouterInteretsPanel.add(btnRetourMenuReseau, constraints);

        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);
        //onBtnSupprimerAbonneClicked(btnAjouter, ajouterInteretField);
        onBtnAjouterInteretClicked(btnAjouter, ajouterInteretField);
    }

    /**
     * Configure le panneau "modifierInteretsPanel" de l'interface graphique GestionReseauGUI.
     * Ce panneau permet à l'utilisateur de modifier un intérêt existant du système Robotix.
     * Il affiche la liste des intérêts disponibles dans un champ de défilement.
     * L'utilisateur peut sélectionner l'intérêt qu'il souhaite modifier dans ce champ.
     * Le panneau comprend également un champ de texte pour saisir le nouveau nom de l'intérêt modifié,
     * ainsi qu'un bouton "Modifier" pour valider la modification.
     * Un bouton "Retour au menu précédent" est également inclus pour revenir au menu de gestion des intérêts.
     *
     * @see #recupererListeInteretsModifier()
     * @see #onBtnModifierInteretClicked(JButton, JTextField)
     * @see #onBtnRetourMenuReseauClicked(JButton)
     */
    private void setModifierInteretsPanel(){
        JLabel modifierInteretLabel = new JLabel("Quel interet voulez modifier?");
        recupererListeInteretsModifier();
        JLabel ajouterNouvelInteretLabel = new JLabel("Par quel interet voulez-vous le remplacer?");
        JTextField ajouterNouvelInteretField = new JTextField();
        //ajouterNouvelInteretField.setPreferredSize(new Dimension(200, 30));
        JButton btnAjouterNouvelInteret = new JButton("Modifier");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        modifierInteretLabel.setFont(new Font("Arial", Font.BOLD, 24));
        ajouterNouvelInteretLabel.setFont(new Font("Arial", Font.BOLD, 18));

        constraints.gridy = 0;
        modifierInteretsPanel.add(ajouterNouvelInteretLabel, constraints);
        constraints.gridy = 1;
        modifierInteretsPanel.add(scrollPaneModifierInteret, constraints);
        constraints.gridy = 2;
        modifierInteretsPanel.add(ajouterNouvelInteretField, constraints);
        constraints.gridy = 3;
        modifierInteretsPanel.add(btnAjouterNouvelInteret, constraints);
        constraints.gridy = 4;
        modifierInteretsPanel.add(btnRetourMenuReseau , constraints);

        //FIX VISUAL
        onBtnModifierInteretClicked(btnAjouterNouvelInteret, ajouterNouvelInteretField);
        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);

    }

    /**
     * Configure le panneau "supprimerInteretsPanel" de l'interface graphique GestionReseauGUI.
     * Ce panneau permet à l'utilisateur de supprimer un intérêt existant du système Robotix.
     * Il affiche la liste des intérêts disponibles dans un champ de défilement.
     * L'utilisateur peut sélectionner l'intérêt qu'il souhaite supprimer dans ce champ.
     * Le panneau comprend également un bouton "Supprimer" pour valider la suppression.
     * Un bouton "Retour au menu précédent" est également inclus pour revenir au menu de gestion des intérêts.
     *
     * @see #recupererListeInteretsSupprimer()
     * @see #onBtnSupprimerInteretClicked(JButton)
     * @see #onBtnRetourMenuReseauClicked(JButton)
     */
    private void setSupprimerInteretPanel(){
        JLabel supprimerInteretLabel = new JLabel("Quel interet voulez-vous supprimer?");
        recupererListeInteretsSupprimer();
        JButton btnSupprimerInteret = new JButton("Supprimer");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        supprimerInteretLabel.setFont(new Font("Arial", Font.BOLD, 24));

        constraints.gridy = 0;
        supprimerInteretsPanel.add(supprimerInteretLabel, constraints);
        constraints.gridy =1;
        supprimerInteretsPanel.add(scrollPaneSupprimerInteret, constraints);
        constraints.gridy = 2;
        supprimerInteretsPanel.add(btnSupprimerInteret, constraints);
        constraints.gridy = 3;
        supprimerInteretsPanel.add(btnRetourMenuReseau, constraints);

        onBtnSupprimerInteretClicked(btnSupprimerInteret);
        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);

    }

    /**
     * Configure le panneau "abonnerInteretPanel" de l'interface graphique GestionReseauGUI.
     * Ce panneau permet à l'utilisateur de s'abonner à un intérêt existant du système Robotix.
     * Il affiche la liste des intérêts disponibles dans un champ de défilement.
     * L'utilisateur peut sélectionner l'intérêt auquel il souhaite s'abonner dans ce champ.
     * Le panneau comprend également un bouton "S'abonner" pour valider l'abonnement.
     * Un bouton "Retour au menu précédent" est également inclus pour revenir au menu de gestion des intérêts.
     *
     * @see #recupererListeInteretAbonne()
     * @see #onBtnAbonnerInteretClicked(JButton)
     * @see #onBtnRetourMenuReseauClicked(JButton)
     */
    private void setAbonneInteret(){
        JLabel abonnerInteretLabel = new JLabel("A quel interet souhaitez-vous vous abonner?");
        recupererListeInteretAbonne();
        JButton btnAbonnerInteret = new JButton("S'abonner");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        constraints.gridy = 0;
        abonnerInteretPanel.add(abonnerInteretLabel, constraints);
        constraints.gridy =1;
        abonnerInteretPanel.add(scrollPaneAbonnerInteret, constraints);
        constraints.gridy = 2;
        abonnerInteretPanel.add(btnAbonnerInteret, constraints);
        constraints.gridy = 3;
        abonnerInteretPanel.add(btnRetourMenuReseau, constraints);


        //Add methode abonnement
        onBtnAbonnerInteretClicked(btnAbonnerInteret);
        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);
    }

    /**
     * Configure le panneau "desabonnerInteretPanel" de l'interface graphique GestionReseauGUI.
     * Ce panneau permet à l'utilisateur de se désabonner d'un intérêt auquel il est déjà abonné.
     * Il affiche la liste des intérêts auxquels l'utilisateur est abonné dans un champ de défilement.
     * L'utilisateur peut sélectionner l'intérêt duquel il souhaite se désabonner dans ce champ.
     * Le panneau comprend également un bouton "Se désabonner" pour valider la désabonnement.
     * Un bouton "Retour au menu précédent" est également inclus pour revenir au menu de gestion des intérêts.
     *
     * @see #recupererListeInteretDesabonne()
     * @see #onBtnDesabonnerInteretClicked(JButton)
     * @see #onBtnRetourMenuReseauClicked(JButton)
     */
    private void setDesabonneInteret(){
        JLabel desabonnerInteretLabel = new JLabel("A quel interet souhaitez-vous vous desabonner?");
        recupererListeInteretDesabonne();
        JButton btnDesabonnerInteret = new JButton("Se desabonner");
        JButton btnRetourMenuReseau = new JButton("Retour au menu precedent");

        constraints.gridy = 0;
        desabonnerInteretPanel.add(desabonnerInteretLabel, constraints);
        constraints.gridy =1;
        desabonnerInteretPanel.add(scrollPaneDesabonnerInteret, constraints);
        constraints.gridy = 2;
        desabonnerInteretPanel.add(btnDesabonnerInteret, constraints);
        constraints.gridy = 3;
        desabonnerInteretPanel.add(btnRetourMenuReseau, constraints);

        //Add methode abonnement
        onBtnDesabonnerInteretClicked(btnDesabonnerInteret);
        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);
    }

    /**
     * Récupère la liste des intérêts existants à partir du contrôleur de base de données (dbControlleur).
     * Crée un panneau (listeInteretsPanel) contenant des boutons radio pour chaque intérêt récupéré.
     * Les boutons radio permettent à l'utilisateur de sélectionner l'intérêt qu'il souhaite modifier.
     * Ces boutons radio sont regroupés sous un ButtonGroup (radioButtonsGroupInterets) pour n'autoriser qu'une seule sélection.
     * Le panneau (listeInteretsPanel) est ensuite placé dans un JScrollPane (scrollPaneModifierInteret) pour permettre le défilement
     * si la liste des intérêts est trop longue.
     * Le JScrollPane est configuré avec une barre de défilement verticale en permanence.
     *
     * @see DbControleur#recupererListeInteret()
     */
    private void recupererListeInteretsModifier(){
        listeInteret = dbControlleur.recupererListeInteret();
        JPanel listeInteretsPanel = new JPanel();
        listeInteretsPanel.setLayout(new BoxLayout(listeInteretsPanel, BoxLayout.Y_AXIS));

        ButtonGroup radioButtonsGroupInterets = new ButtonGroup();

        for (Interet interet : listeInteret) {
            String nom = interet.getNom();
            JRadioButton radioButton = new JRadioButton(nom);
            radioButton.setActionCommand(nom);
            listeInteretsPanel.add(radioButton);
            radioButtonsGroupInterets.add(radioButton);
        }

        scrollPaneModifierInteret = new JScrollPane(listeInteretsPanel);
        scrollPaneModifierInteret.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Récupère la liste des intérêts existants à partir du contrôleur de base de données (dbControlleur).
     * Crée un panneau (listeInteretsPanel) contenant des boutons radio pour chaque intérêt récupéré.
     * Les boutons radio permettent à l'utilisateur de sélectionner l'intérêt qu'il souhaite supprimer.
     * Ces boutons radio sont regroupés sous un ButtonGroup (radioButtonsGroupInterets) pour n'autoriser qu'une seule sélection.
     * Le panneau (listeInteretsPanel) est ensuite placé dans un JScrollPane (scrollPaneSupprimerInteret) pour permettre le défilement
     * si la liste des intérêts est trop longue.
     * Le JScrollPane est configuré avec une barre de défilement verticale en permanence.
     *
     * @see DbControleur#recupererListeInteret()
     */
    private void recupererListeInteretsSupprimer(){
        listeInteret = dbControlleur.recupererListeInteret();
        JPanel listeInteretsPanel = new JPanel();
        listeInteretsPanel.setLayout(new BoxLayout(listeInteretsPanel, BoxLayout.Y_AXIS));

        ButtonGroup radioButtonsGroupInterets = new ButtonGroup();

        for (Interet interet : listeInteret) {
            String nom = interet.getNom();
            JRadioButton radioButton = new JRadioButton(nom);
            radioButton.setActionCommand(nom);
            listeInteretsPanel.add(radioButton);
            radioButtonsGroupInterets.add(radioButton);
        }

        scrollPaneSupprimerInteret = new JScrollPane(listeInteretsPanel);
        scrollPaneSupprimerInteret.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Récupère la liste de tous les intérêts existants à partir du contrôleur de base de données (dbControlleur).
     * Crée un panneau (listeInteretsPanel) contenant des boutons radio pour chaque intérêt récupéré.
     * Les boutons radio permettent à l'utilisateur de sélectionner l'intérêt auquel il souhaite s'abonner.
     * Ces boutons radio sont regroupés sous un ButtonGroup (radioButtonsGroupInterets) pour n'autoriser qu'une seule sélection.
     * Le panneau (listeInteretsPanel) est ensuite placé dans un JScrollPane (scrollPaneAbonnerInteret) pour permettre le défilement
     * si la liste des intérêts est trop longue.
     * Le JScrollPane est configuré avec une barre de défilement verticale en permanence.
     *
     * @see DbControleur#recupererListeInteret()
     */
    private void recupererListeInteretAbonne(){
        listeInteret = dbControlleur.recupererListeInteret();
        JPanel listeInteretsPanel = new JPanel();
        listeInteretsPanel.setLayout(new BoxLayout(listeInteretsPanel, BoxLayout.Y_AXIS));

        ButtonGroup radioButtonsGroupInterets = new ButtonGroup();

        for (Interet interet : listeInteret) {
            String nom = interet.getNom();
            JRadioButton radioButton = new JRadioButton(nom);
            radioButton.setActionCommand(nom);
            listeInteretsPanel.add(radioButton);
            radioButtonsGroupInterets.add(radioButton);
        }

        scrollPaneAbonnerInteret = new JScrollPane(listeInteretsPanel);
        scrollPaneAbonnerInteret.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Récupère la liste des intérêts auxquels l'utilisateur (pseudo) est abonné à partir du contrôleur de base de données (dbControlleur).
     * Crée un panneau (listeInteretsPanel) contenant des boutons radio pour chaque intérêt auquel l'utilisateur est abonné.
     * Les boutons radio permettent à l'utilisateur de sélectionner l'intérêt auquel il souhaite se désabonner.
     * Ces boutons radio sont regroupés sous un ButtonGroup (radioButtonsGroupInterets) pour n'autoriser qu'une seule sélection.
     * Le panneau (listeInteretsPanel) est ensuite placé dans un JScrollPane (scrollPaneDesabonnerInteret) pour permettre le défilement
     * si la liste des intérêts est trop longue.
     * Le JScrollPane est configuré avec une barre de défilement verticale en permanence.
     *
     * @see DbControleur#recupererListeInteretUtilisateur(String)
     */
    private void recupererListeInteretDesabonne(){
        listeInteret = dbControlleur.recupererListeInteretUtilisateur(pseudo);
        JPanel listeInteretsPanel = new JPanel();
        listeInteretsPanel.setLayout(new BoxLayout(listeInteretsPanel, BoxLayout.Y_AXIS));

        ButtonGroup radioButtonsGroupInterets = new ButtonGroup();

        for (Interet interet : listeInteret) {
            String nom = interet.getNom();
            JRadioButton radioButton = new JRadioButton(nom);
            radioButton.setActionCommand(nom);
            listeInteretsPanel.add(radioButton);
            radioButtonsGroupInterets.add(radioButton);
        }

        scrollPaneDesabonnerInteret = new JScrollPane(listeInteretsPanel);
        scrollPaneDesabonnerInteret.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Modifier" (btnAjouterNouvelInteret) dans le panneau de modification d'intérêt (modifierInteretsPanel).
     * Cette méthode récupère l'intérêt sélectionné par l'utilisateur dans le panneau (listeInteretPanel) contenant les boutons radio.
     * Ensuite, elle récupère la valeur saisie dans le champ de texte (ajouterNouvelInteretField), qui représente le nouvel intérêt saisi par l'utilisateur.
     * La méthode vérifie ensuite si l'intérêt sélectionné existe dans la base de données (extraireInterets(interetChoisi)) et si le nouvel intérêt ne fait pas déjà partie de la base de données (!dbControlleur.existeDansDbInteret(nouvelInteret))
     * et si la longueur du nouvel intérêt n'est pas égale à 0 (nouvelInteret.length() != 0).
     * Si toutes les conditions sont satisfaites, elle demande une confirmation à l'utilisateur pour la modification de l'intérêt (confirmerModificationInteret).
     * Puis, elle appelle la méthode du contrôleur de base de données (dbControlleur.modifierInteret) pour effectuer la modification de l'intérêt.
     * Sinon, elle affiche un message d'erreur indiquant que la modification de l'intérêt n'est pas possible (afficherMessageErreurModificationInteret).
     *
     * @param btnAjouterNouvelInteret Le bouton "Modifier" sur lequel l'événement est déclenché.
     * @param ajouterNouvelInteretField Le champ de texte dans lequel l'utilisateur saisit le nouvel intérêt.
     * @see DbControleur#extraireInterets(String)
     * @see DbControleur#existeDansDbInteret(String)
     * @see DbControleur#modifierInteret(String, String)
     */
    private void onBtnModifierInteretClicked(JButton btnAjouterNouvelInteret, JTextField ajouterNouvelInteretField) {
        btnAjouterNouvelInteret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JPanel listeInteretPanel = (JPanel) scrollPaneModifierInteret.getViewport().getView();
                Component[] listeInterets = listeInteretPanel.getComponents();
                String interetChoisi = "";

                for (Component interetsButton: listeInterets) {
                    if (interetsButton instanceof JRadioButton rb) {
                        if (rb.isSelected()) {
                            interetChoisi = rb.getActionCommand();
                            break;
                        }
                    }
                }

                String nouvelInteret = ajouterNouvelInteretField.getText();

                //Verifier si interet existe deja existe deja
                if (dbControlleur.extraireInterets(interetChoisi) && !dbControlleur.existeDansDbInteret(nouvelInteret) && nouvelInteret.length() != 0){
                    confirmerModificationInteret(interetChoisi,nouvelInteret);
                    dbControlleur.modifierInteret(interetChoisi, nouvelInteret);
                }else{
                    afficherMessageErreurModificationInteret(interetChoisi);
                }
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Supprimer" (btnSupprimerInteret) dans le panneau de suppression d'intérêt (supprimerInteretsPanel).
     * Cette méthode récupère l'intérêt sélectionné par l'utilisateur dans le panneau (listeInteretPanel) contenant les boutons radio.
     * Ensuite, elle vérifie si l'intérêt sélectionné existe dans la base de données (dbControlleur.extraireInterets(interetChoisi)).
     * Si l'intérêt existe, elle demande une confirmation à l'utilisateur pour la suppression de l'intérêt (confirmerSupprimerInteret).
     * Puis, elle appelle la méthode du contrôleur de base de données (dbControlleur.supprimerInteret) pour effectuer la suppression de l'intérêt.
     * Sinon, elle affiche un message d'erreur indiquant que la suppression de l'intérêt n'est pas possible (afficherMessageErreurSuppressionInteret).
     *
     * @param btnSupprimerInteret Le bouton "Supprimer" sur lequel l'événement est déclenché.
     * @see DbControleur#extraireInterets(String)
     * @see DbControleur#supprimerInteret(String)
     */
    private void onBtnSupprimerInteretClicked(JButton btnSupprimerInteret) {
        btnSupprimerInteret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JPanel listeInteretPanel = (JPanel) scrollPaneSupprimerInteret.getViewport().getView();
                Component[] listeInterets = listeInteretPanel.getComponents();
                String interetChoisi = "";

                for (Component interetsButton: listeInterets) {
                    if (interetsButton instanceof JRadioButton rb) {
                        if (rb.isSelected()) {
                            interetChoisi = rb.getActionCommand();
                            break;
                        }
                    }
                }

                //Verifier si interet existe deja existe deja
                if (dbControlleur.extraireInterets(interetChoisi)){
                    confirmerSupprimerInteret(interetChoisi);
                    dbControlleur.supprimerInteret(interetChoisi);
                }else{
                    afficherMessageErreurSuppressionInteret(interetChoisi);
                }
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "S'abonner" (btnAbonnerInteret) dans le panneau d'abonnement à un intérêt (abonnerInteretPanel).
     * Cette méthode récupère l'intérêt sélectionné par l'utilisateur dans le panneau (listeInteretPanel) contenant les boutons radio.
     * Ensuite, elle vérifie si l'utilisateur ne possède pas déjà cet intérêt (controlleurUtilisateurs.possedeInteret(interetChoisi, pseudo)).
     * Si l'utilisateur ne possède pas cet intérêt, elle appelle la méthode du contrôleur d'utilisateurs (controlleurUtilisateurs.abonnerInteret) pour l'abonner à l'intérêt.
     * Puis, elle demande une confirmation à l'utilisateur pour l'abonnement à l'intérêt (confirmerAbonnementInteret).
     * Sinon, elle affiche un message d'erreur indiquant que l'utilisateur possède déjà cet intérêt (afficherMessageErreurAbonnerInteret).
     *
     * @param btnAbonnerInteret Le bouton "S'abonner" sur lequel l'événement est déclenché.
     * @see ControlleurUtilisateurs#possedeInteret(String, String)
     * @see ControlleurUtilisateurs#abonnerInteret(String, String)
     */
    private void onBtnAbonnerInteretClicked(JButton btnAbonnerInteret){
        btnAbonnerInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel listeInteretPanel = (JPanel) scrollPaneAbonnerInteret.getViewport().getView();
                Component[] listeInterets = listeInteretPanel.getComponents();
                String interetChoisi = "";

                for (Component interetsButton: listeInterets) {
                    if (interetsButton instanceof JRadioButton rb) {
                        if (rb.isSelected()) {
                            interetChoisi = rb.getActionCommand();
                            break;
                        }
                    }
                }

                if (!controlleurUtilisateurs.possedeInteret(interetChoisi, pseudo)) {
                    controlleurUtilisateurs.abonnerInteret(interetChoisi, pseudo);
                    confirmerAbonnementInteret(interetChoisi);

                } else {
                    afficherMessageErreurAbonnerInteret(interetChoisi);
                }
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Se désabonner" (btnAbonnerInteret) dans le panneau de désabonnement d'un intérêt (desabonnerInteretPanel).
     * Cette méthode récupère l'intérêt sélectionné par l'utilisateur dans le panneau (listeInteretPanel) contenant les boutons radio.
     * Ensuite, elle vérifie si l'utilisateur possède cet intérêt (controlleurUtilisateurs.possedeInteret(interetChoisi, pseudo)).
     * Si l'utilisateur possède cet intérêt, elle appelle la méthode du contrôleur d'utilisateurs (controlleurUtilisateurs.desabonnerInteret) pour le désabonner de l'intérêt.
     * Puis, elle demande une confirmation à l'utilisateur pour le désabonnement de l'intérêt (confirmerDesabonnementInteret).
     * Sinon, elle affiche un message d'erreur indiquant que l'utilisateur ne possède pas cet intérêt (afficherMessageErreurDesabonnerInteret).
     *
     * @param btnAbonnerInteret Le bouton "Se désabonner" sur lequel l'événement est déclenché.
     * @see ControlleurUtilisateurs#possedeInteret(String, String)
     * @see ControlleurUtilisateurs#desabonnerInteret(String, String)
     */
    private void onBtnDesabonnerInteretClicked(JButton btnAbonnerInteret){
        btnAbonnerInteret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel listeInteretPanel = (JPanel) scrollPaneDesabonnerInteret.getViewport().getView();
                Component[] listeInterets = listeInteretPanel.getComponents();
                String interetChoisi = "";

                for (Component interetsButton: listeInterets) {
                    if (interetsButton instanceof JRadioButton rb) {
                        if (rb.isSelected()) {
                            System.out.println("HERE");
                            interetChoisi = rb.getActionCommand();
                            break;
                        }
                    }
                }

                if (controlleurUtilisateurs.possedeInteret(interetChoisi, pseudo)) {
                    controlleurUtilisateurs.desabonnerInteret(interetChoisi, pseudo);
                    confirmerDesabonnementInteret(interetChoisi);
                } else {
                    afficherMessageErreurDesabonnerInteret(interetChoisi);
                }
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Ajouter interet" (btnAjouter) dans le panneau d'ajout d'un intérêt (ajouterInteretsPanel).
     * Cette méthode récupère le nom de l'intérêt saisi par l'utilisateur dans le champ de texte (ajouterInteretField).
     * Ensuite, elle vérifie si l'intérêt n'existe pas déjà dans la base de données (dbControlleur.souscrireAunInteret(interet) == null).
     * Si l'intérêt n'existe pas déjà, elle affiche un message de succès d'ajout d'intérêt (afficherMessageSuccesAjoutInteret) et ajoute l'intérêt dans la base de données (dbControlleur.ajouterInteret).
     * Sinon, elle affiche un message d'erreur indiquant que l'intérêt existe déjà (afficherMessageErreurAjouterInteretExiste).
     *
     * @param btnAjouter Le bouton "Ajouter interet" sur lequel l'événement est déclenché.
     * @param ajouterInteretField Le champ de texte où l'utilisateur saisit le nom de l'intérêt à ajouter.
     * @see ControlleurUtilisateurs#souscrireAunInteret(String)
     */
    private void onBtnAjouterInteretClicked(JButton btnAjouter, JTextField ajouterInteretField) {
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String interet = ajouterInteretField.getText();
                if (dbControlleur.souscrireAunInteret(interet) == null) {
                    afficherMessageSuccesAjoutInteret(interet);
                    dbControlleur.ajouterInteret(interet);
                } else {
                    afficherMessageErreurAjouterInteretExiste();
                }
            }
        });
    }

    /**
     * Affiche le panneau principal (mainPanel) dans la fenêtre principale (jFrame) en remplaçant le contenu actuel de la fenêtre.
     * Cette méthode sauvegarde le contenu précédent du panneau (panelPrecedent) avant de changer le contenu pour permettre le retour au panneau précédent.
     *
     * @param jFrame La fenêtre principale où afficher le panneau principal (mainPanel).
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Utilisateur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour la fenêtre principale (jFrame) pour refléter les changements apportés aux panneaux.
     * Cette méthode invalide le panneau actuel et redessine la fenêtre.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Suivre utilisateur" (btnSuivreUtilisateur) dans le panneau de gestion des utilisateurs.
     * Cette méthode récupère le pseudo de l'utilisateur saisi par l'utilisateur dans le champ de texte (pseudoField).
     * Ensuite, elle vérifie si le champ de texte est vide (input.isEmpty()).
     * Si le champ de texte est vide, elle affiche un message d'erreur demandant à l'utilisateur de saisir un pseudo (afficherMessageErreurSuivreUtilisateur).
     * Sinon, elle vérifie si l'utilisateur suit déjà l'utilisateur dont le pseudo est saisi (controlleurUtilisateurs.existeDansListeSuivi(pseudo, input)).
     * Si l'utilisateur ne suit pas déjà cet utilisateur, elle ajoute une notification pour le nouvel abonné (controlleurUtilisateurs.ajouterNotifs) et ajoute cet utilisateur à la liste des abonnements de l'utilisateur courant (controlleurUtilisateurs.suivreUtilisateur).
     * Enfin, elle demande une confirmation à l'utilisateur pour le nouvel abonnement (confirmerNouvelAbonne).
     *
     * @param btnSuivreUtilisateur Le bouton "Suivre utilisateur" sur lequel l'événement est déclenché.
     * @param pseudoField Le champ de texte où l'utilisateur saisit le pseudo de l'utilisateur à suivre.
     * @see ControlleurUtilisateurs#existeDansListeSuivi(String, String)
     * @see ControlleurUtilisateurs#ajouterNotifs(String, String, String, TypeNotification)
     * @see ControlleurUtilisateurs#suivreUtilisateur(String, String)
     */
    public void onBtnSuivreUtilisateurClicked(JButton btnSuivreUtilisateur, JTextField pseudoField) {
        btnSuivreUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = pseudoField.getText();

                if (input.isEmpty()){
                    afficherMessageErreurSuivreUtilisateur("Veuillez entrer un pseudo");
                } else {
                    if (controlleurUtilisateurs.existeDansListeSuivi(pseudo, input)){
                        afficherMessageErreurSuivreUtilisateur("Vous suivez déjà cet utilisateur");
                    } else {
                        controlleurUtilisateurs.ajouterNotifs(input, "Nouvel abonne",pseudo + " vous a suivi", TypeNotification.NOUVEAU_ABONNE);
                        controlleurUtilisateurs.suivreUtilisateur(pseudo, input);
                        confirmerNouvelAbonne(input);
                    }
                }
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Supprimer abonné" (btnSupprimer) dans le panneau de gestion des abonnements.
     * Cette méthode récupère le pseudo de l'abonné saisi par l'utilisateur dans le champ de texte (supprimerField).
     * Ensuite, elle vérifie si le champ de texte est vide (aSupprimer.isEmpty()).
     * Si le champ de texte est vide, elle affiche un message d'erreur demandant à l'utilisateur de saisir un pseudo (afficherMessageErreurSupprimerAbonne).
     * Sinon, elle vérifie si l'utilisateur suit effectivement cet abonné (controlleurUtilisateurs.existeDansListeSuivi(pseudo, aSupprimer)).
     * Si l'utilisateur suit l'abonné, elle arrête de suivre l'abonné (controlleurUtilisateurs.arreterSuivreUtilisateur) et affiche une confirmation de suppression d'abonné (confirmerSupprimerAbonne).
     * Sinon, elle affiche un message d'erreur indiquant que l'utilisateur ne suit pas cet abonné (afficherMessageErreurSupprimerAbonne).
     *
     * @param btnSupprimer Le bouton "Supprimer abonné" sur lequel l'événement est déclenché.
     * @param supprimerField Le champ de texte où l'utilisateur saisit le pseudo de l'abonné à supprimer.
     * @see ControlleurUtilisateurs#existeDansListeSuivi(String, String)
     * @see ControlleurUtilisateurs#arreterSuivreUtilisateur(String, String)
     */
    public void onBtnSupprimerAbonneClicked(JButton btnSupprimer, JTextField supprimerField) {
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aSupprimer = supprimerField.getText();
                if (aSupprimer.isEmpty())
                    afficherMessageErreurSupprimerAbonne("Veuillez entrer un pseudo.");
                else{
                    if (!controlleurUtilisateurs.existeDansListeSuivi(pseudo, aSupprimer)){
                        afficherMessageErreurSupprimerAbonne("Vous ne suivez pas cet utilisateur.");
                    } else {
                        controlleurUtilisateurs.arreterSuivreUtilisateur(pseudo, aSupprimer);
                        confirmerSupprimerAbonne(aSupprimer);
                    }
                }

            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Retour au menu précédent" (btnRetourMenuReseau) dans le panneau de gestion des abonnements, de gestion des intérêts, etc.
     * Cette méthode change le contenu de la fenêtre principale (jFrame) pour afficher le panneau de gestion des intérêts (gererInteretsPanel).
     *
     * @param btnRetourMenuReseau Le bouton "Retour au menu précédent" sur lequel l'événement est déclenché.
     */
    private void onBtnRetourMenuReseauClicked(JButton btnRetourMenuReseau) {
        btnRetourMenuReseau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(gererInteretsPanel);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Méthode déclenchée lorsqu'on clique sur le bouton "Annuler" (btnAnnuler).
     * Cette méthode change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param btnAnnuler Le bouton "Annuler" sur lequel l'événement est déclenché.
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
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur suit maintenant un nouvel abonné.
     * Cette méthode affiche un message de succès avec le nom de l'abonné suivi.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param nom Le nom de l'abonné suivi.
     */
    public void confirmerNouvelAbonne(String nom) {
        String message = "Vous suivez maintenant " + nom;
        String title = "Nouvel abonne";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur a supprimé un abonné de sa liste d'abonnements.
     * Cette méthode affiche un message de succès avec le nom de l'abonné supprimé.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param nom Le nom de l'abonné supprimé.
     */
    public void confirmerSupprimerAbonne(String nom) {

        String message = "Vous avez supprime " + nom + " dans vos abonnes";
        String title = "Supprimer abonne";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur a modifié un intérêt existant.
     * Cette méthode affiche un message de succès avec l'ancien intérêt et le nouvel intérêt.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param ancienInteret L'ancien intérêt avant la modification.
     * @param nouvelInteret Le nouvel intérêt après la modification.
     */
    public void confirmerModificationInteret(String ancienInteret, String nouvelInteret) {

        String message = "Vous avez modifie " + ancienInteret + " pour " + nouvelInteret;
        String title = "Modification interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur a supprimé un intérêt.
     * Cette méthode affiche un message de succès avec l'ancien intérêt supprimé.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param ancienInteret L'ancien intérêt supprimé.
     */
    public void confirmerSupprimerInteret(String ancienInteret) {

        String message = "Vous avez supprime " +  ancienInteret;
        String title = "Suppression interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur s'est abonné à un nouvel intérêt.
     * Cette méthode affiche un message de succès avec le nouvel intérêt auquel l'utilisateur s'est abonné.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param nouvelInteret Le nouvel intérêt auquel l'utilisateur s'est abonné.
     */
    public void confirmerAbonnementInteret(String nouvelInteret) {

        String message = "Vous etes abonne a " +  nouvelInteret;
        String title = "Abonnement d'interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue de confirmation indiquant que l'utilisateur s'est désabonné d'un intérêt.
     * Cette méthode affiche un message de succès avec l'intérêt dont l'utilisateur s'est désabonné.
     * Après avoir cliqué sur le bouton "OK" dans la boîte de dialogue, elle change le contenu de la fenêtre principale (jFrame) pour afficher le panneau principal (mainPanel).
     *
     * @param nouvelInteret L'intérêt dont l'utilisateur s'est désabonné.
     */
    public void confirmerDesabonnementInteret(String nouvelInteret) {

        String message = "Vous etes desabonne a " +  nouvelInteret;
        String title = "Desabonnement d'interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche une boîte de dialogue d'erreur avec le message d'erreur spécifié.
     * Cette méthode est utilisée pour afficher des messages d'erreur lorsqu'il y a un problème lors du suivi d'un utilisateur.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurSuivreUtilisateur(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur avec le message d'erreur spécifié.
     * Cette méthode est utilisée pour afficher des messages d'erreur lorsqu'il y a un problème lors de la suppression d'un abonné.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurSupprimerAbonne(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur indiquant que la modification de l'intérêt n'a pas pu être effectuée.
     * Cette méthode affiche un message d'erreur avec l'ancien intérêt qui n'a pas été modifié.
     *
     * @param ancienInteret L'ancien intérêt qui n'a pas été modifié.
     */
    public void afficherMessageErreurModificationInteret(String ancienInteret) {
        String message = ancienInteret + " n'a pas pu etre modifie";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur indiquant que la suppression de l'intérêt n'a pas pu être effectuée car quelqu'un y est abonné.
     * Cette méthode affiche un message d'erreur avec l'ancien intérêt qui n'a pas été supprimé.
     *
     * @param ancienInteret L'ancien intérêt qui n'a pas été supprimé car quelqu'un y est abonné.
     */
    public void afficherMessageErreurSuppressionInteret(String ancienInteret) {
        String message = ancienInteret + " n'a pas pu etre supprime car quelqu'un y est abonne";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur indiquant qu'un intérêt avec le même nom existe déjà dans le système.
     * Cette méthode est utilisée pour afficher des messages d'erreur lorsqu'un utilisateur tente d'ajouter un intérêt qui existe déjà dans le système.
     */
    public void afficherMessageErreurAjouterInteretExiste() {
        String message = "Cet interet existe deja dans le systeme. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur indiquant que l'utilisateur est déjà abonné à un certain intérêt.
     *
     * @param interet L'intérêt auquel l'utilisateur est déjà abonné.
     */
    public void afficherMessageErreurAbonnerInteret(String interet) {
        String message = "Vous etes deja abonnes a " + interet;
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue d'erreur indiquant que l'utilisateur n'est pas abonné à un certain intérêt.
     *
     * @param interet L'intérêt auquel l'utilisateur n'est pas abonné.
     */
    public void afficherMessageErreurDesabonnerInteret(String interet) {
        String message = "Vous n'etes pas abonne a " + interet;
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche une boîte de dialogue de succès indiquant que l'intérêt a été ajouté avec succès dans le système.
     *
     * @param interet L'intérêt qui a été ajouté dans le système.
     */
    public void afficherMessageSuccesAjoutInteret(String interet){
        String message =  interet + " a ete ajoute dans le systeme!";
        String title = "Interet ajoute avecgit com succes";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

}