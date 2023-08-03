package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Controller.DbControleur;
import domain.logic.Membre.Interet;
import domain.logic.Robot.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class GestionReseauGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private DbControleur dbControlleur = new DbControleur();

    String pseudo;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel suivreUtilisateurPanel = new JPanel(new GridBagLayout());
    private JPanel gererSuiveursPanel = new JPanel(new GridLayout(0, 1));
    private JPanel voirListeAbonnesPanel = new JPanel(new GridBagLayout());
    private JPanel supprimerAbonnePanel = new JPanel(new GridBagLayout());
    private JPanel ajouterInteretsPanel = new JPanel(new GridBagLayout());
    private JPanel modifierInteretsPanel = new JPanel(new GridLayout(0, 1));
    private JPanel abonnerInteretPanel = new JPanel(new GridLayout(0, 1));
    private JPanel getSupprimerInteretsPanel = new JPanel(new GridLayout(0, 1));
    private JPanel supprimerInteretsPanel = new JPanel(new GridLayout(0, 1));
    private JPanel gererInteretsPanel = new JPanel(new GridLayout(0, 1));
    private JLabel gestionReseauLabel = new JLabel("Gestion reseau", SwingConstants.CENTER);
    private JButton btnSuivreUtilisateur = new JButton("Suivre un utilisateur");
    private JButton btnGererSuiveurs = new JButton("Gerer mes suiveurs");
    private JButton btnGererInterets = new JButton("Gerer mes interets");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private HashSet<Interet> listeInteret;
    private JScrollPane scrollPaneVoirMesAbonnes;
    private JScrollPane scrollPaneAjouterInteret;
    private JScrollPane scrollPaneModifierInteret;
    private JScrollPane scrollPaneSupprimerInteret;
    private  JScrollPane scrollPaneAbonnerInteret;
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

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

    public void setGererSuiveursPanel() {
        JLabel gererSuiveursTitre = new JLabel("Gerer mes suiveurs", SwingConstants.CENTER);
        JLabel gererSuiveursLabel = new JLabel("Que voulez-vous faire?", SwingConstants.CENTER);
        JButton btnVoirAbonnes = new JButton("Voir a qui je suis abonne");
        JButton btnSupprimerAbonne = new JButton("Supprimer un utilisateur de ma liste d'abonne");
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

        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);
    }

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

        onBtnRetourMenuReseauClicked(btnRetourMenuReseau);
        onBtnSupprimerAbonneClicked(btnSupprimer, supprimerAbonneField);
    }

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
        onBtnAnnulerClicked(btnRetour);
    }

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


    }

    private void setAbonneInteret(){
        JLabel abonnerInteret = new JLabel("A quel interet souhaitez-vous vous abonner?");
        recupererListeInteretAbonne();
    }

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

    public void onBtnSuivreUtilisateurClicked(JButton btnSuivreUtilisateur, JTextField pseudoField) {
        btnSuivreUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = pseudoField.getText();
                if (controlleurUtilisateurs.existeDansListeSuivi(pseudo, input)) {
                    afficherMessageErreurUtilDejaSuivi(input);
                }
                else if (input.length() == 0 || !(controlleurUtilisateurs.suivreUtilisateur(pseudo, input))){
                    //Appeller controlleur pour add dans liste de suiveur et suivi

                    afficherMessageErreurSuivreUtilisateur();
                }
                else{
                    confirmerNouvelAbonne(input);
                }
            }
        });
    }

    public void onBtnSupprimerAbonneClicked(JButton btnSupprimer, JTextField supprimerField) {
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aSupprimer = supprimerField.getText();
                if (aSupprimer.length() == 0 || !(controlleurUtilisateurs.suppriemrSuivreUtilisateur(pseudo, aSupprimer)))
                    afficherMessageErreurSupprimerAbonne();
                else
                    confirmerSupprimerAbonne(aSupprimer);
            }
        });
    }

    private void onBtnRetourMenuReseauClicked(JButton btnRetourMenuReseau) {
        btnRetourMenuReseau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(gererSuiveursPanel);
                mettreAJourFrame();
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

    public void confirmerNouvelAbonne(String nom) {
        String message = "Vous suivez maintenant " + nom;
        String title = "Nouvel abonne";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerSupprimerAbonne(String nom) {

        String message = "Vous avez supprime " + nom + " dans vos abonnes";
        String title = "Supprimer abonne";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerModificationInteret(String ancienInteret, String nouvelInteret) {

        String message = "Vous avez modifie " + ancienInteret + " pour " + nouvelInteret;
        String title = "Modification interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void confirmerSupprimerInteret(String ancienInteret) {

        String message = "Vous avez supprime " +  ancienInteret;
        String title = "Suppression interet";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurSuivreUtilisateur() {
        String message = "L'utilisateur que vous voulez suivre n'existe pas. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurUtilDejaSuivi(String nom) {
        String message = "Vous suivez deja " + nom;
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurSupprimerAbonne() {
        String message = "Nous avons pas trouve cet utilisateur dans vos liste d'abonnnes. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurModificationInteret(String ancienInteret) {
        String message = ancienInteret + " n'a pas pu etre modifie";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurSuppressionInteret(String ancienInteret) {
        String message = ancienInteret + " n'a pas pu etre supprime car quelqu'un y est abonne";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageErreurAjouterInteretExiste() {
        String message = "Cet interet existe deja dans le systeme. Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void afficherMessageSuccesAjoutInteret(String interet){
        String message =  interet + " a ete ajoute dans le systeme!";
        String title = "Interet ajoute avecgit com succes";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

}
