package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequetePubliqueFournisseurGUI {
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
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public RequetePubliqueFournisseurGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        setMainPanel();
        setVoirListeUtilisateursPanel();
        setVoirListeFournisseursPanel();
        setVoirProfilPanel();

        // Setup de l'option Recherche Utilisateur
        setRechercheUtilisateurPanel();
        setRechercheUtilisateurParPseudoPanel();
        setRechercheUtilisateurParNomPanel();
        setRechercheUtilisateurParPrenomPanel();
        setRechercheUtilisateurParListeSuiveursPanel();

        setRecupererListeActivitesPanel();

        // Setup de l'option Recherche Interets
        setRechercheInteretsPanel();
        setRechercheInteretsAvecFiltrePanel();
        setRechercheInteretsSansFiltrePanel();
        setRechercheInteretsParLettrePanel();
        setRechercheInteretsParPseudoPanel();
        setRechercheInteretsParLettreEtPseudoPanel();

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
                jFrame.setContentPane(rechercheInteretsPanel);
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
        JLabel listeUtilisateursLabel = new JLabel("Voici la liste des utilisateurs");
        JButton btnRetour = new JButton("Retour");

        listeUtilisateursLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        voirListeUtilisateursPanel.add(listeUtilisateursLabel, constraints);
        constraints.gridy = 1;
        voirListeUtilisateursPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    public void setVoirListeFournisseursPanel() {
        JLabel listeFournisseursLabel = new JLabel("Voici la liste des fournisseurs");
        JButton btnRetour = new JButton("Retour");

        listeFournisseursLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        voirListeFournisseursPanel.add(listeFournisseursLabel, constraints);
        constraints.gridy = 1;
        voirListeFournisseursPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    public void setVoirProfilPanel() {
        JButton btnRetour = new JButton("Retour");
        JLabel nomLabel = new JLabel("Nom");
        JLabel adresseLabel = new JLabel("Adresse");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel typeRobotLabel = new JLabel("Type de robot a fabriquer");
        JLabel typeComposanteLabel = new JLabel("Type de composante a fabriquer");
        JLabel nbComposantesLabel = new JLabel("Nombre de composantes disponible");
        JLabel nbRobotsLabel = new JLabel("Nombre de robots disponible");

        // Il faut remplacer le champ de texte des JTextField par des variables qui recuperent leur informations respectives
        JTextField nomField = new JTextField("Nom");
        JTextField adresseField = new JTextField("Adresse");
        JTextField telephoneField = new JTextField("Telephone");
        JTextField typeRobotField = new JTextField("Type de robot fabriquer");
        JTextField typeComposanteField = new JTextField("Type de composante a fabriquer");
        JTextField nbComposantesField = new JTextField("Nombre de composantes disponible");
        JTextField nbRobotsField = new JTextField("Nombre de robots disponible");

        // Setup la dimension des JTextField
        nomField.setPreferredSize(new Dimension(200, 30));
        adresseField.setPreferredSize(new Dimension(200, 30));
        telephoneField.setPreferredSize(new Dimension(200, 30));
        typeRobotField.setPreferredSize(new Dimension(200, 30));
        typeComposanteField.setPreferredSize(new Dimension(200, 30));
        nbComposantesField.setPreferredSize(new Dimension(200, 30));
        nbRobotsField.setPreferredSize(new Dimension(200, 30));

        // Rendre les JTextField en mode lecture seule
        nomField.setEditable(false);
        adresseField.setEditable(false);
        telephoneField.setEditable(false);
        typeRobotField.setEditable(false);
        typeComposanteField.setEditable(false);
        nbComposantesField.setEditable(false);
        nbRobotsField.setEditable(false);

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
        voirProfilPanel.add(nbComposantesLabel, constraints);
        voirProfilPanel.add(nbComposantesField, constraints);
        constraints.gridy = 6;
        voirProfilPanel.add(nbRobotsLabel, constraints);
        voirProfilPanel.add(nbRobotsField, constraints);
        constraints.gridy = 7;
        voirProfilPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    public void setRechercheUtilisateurPanel() {
        JLabel filtreLabel = new JLabel("Filtrer par");
        String[] options = {"Pseudo", "Nom", "Prenom", "Obtenir liste des suiveurs de? (pseudo voulu)"};
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
        JLabel pseudoLabel = new JLabel("Entrez le pseudo");
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        pseudoField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        recherheUtilisateurParPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParPseudoPanel.add(pseudoField, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParPseudoPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParPseudoPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }

    public void setRechercheUtilisateurParNomPanel() {
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
    }

    public void setRechercheUtilisateurParListeSuiveursPanel() {
        JLabel pseudoLabel = new JLabel("Entrez le pseudo");
        JTextField pseudoField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheUtilisateurParListeSuiveursPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        recherheUtilisateurParListeSuiveursPanel.add(pseudoField, constraints);
        constraints.gridy = 2;
        recherheUtilisateurParListeSuiveursPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheUtilisateurParListeSuiveursPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheUtilisateurClicked(btnRetourFiltre);
    }

    public void setRecupererListeActivitesPanel() {
        JLabel listeActivitesLabel = new JLabel("Voici la liste des activites");
        JButton btnRetour = new JButton("Retour");

        listeActivitesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridy = 0;
        recupererListeActivitesPanel.add(listeActivitesLabel, constraints);
        constraints.gridy = 1;
        recupererListeActivitesPanel.add(btnRetour, constraints);

        onBtnRetourClicked(btnRetour);
    }

    public void setRechercheInteretsPanel() {
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
    }

    public void setRechercheInteretsSansFiltrePanel() {
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        rechercheInteretsSansFiltrePanel.add(btnRetour, constraints);

        onBtnRetourRechercheInteretsClicked(btnRetour);
    }

    public void setRechercheInteretsParLettrePanel() {
        JLabel caracteresLabel = new JLabel("Entrez vos 3 caracteres");
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
        JLabel nomLabel = new JLabel("Entrez le nom du fournisseur");
        JTextField nomField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheFournisseurParNomPanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParNomPanel.add(nomField, constraints);
        constraints.gridy = 2;
        recherheFournisseurParNomPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheFournisseurParNomPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    public void setRechercheFournisseurParEmailPanel() {
        JLabel emailLabel = new JLabel("Entrez l'email du fournisseur");
        JTextField emailField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheFournisseurParEmailPanel.add(emailLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParEmailPanel.add(emailField, constraints);
        constraints.gridy = 2;
        recherheFournisseurParEmailPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheFournisseurParEmailPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    public void setRechercheFournisseurParAdressePanel() {
        JLabel adresseLabel = new JLabel("Entrez l'adresse du fournisseur");
        JTextField adresseField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheFournisseurParAdressePanel.add(adresseLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParAdressePanel.add(adresseField, constraints);
        constraints.gridy = 2;
        recherheFournisseurParAdressePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheFournisseurParAdressePanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

    public void setRecherheFournisseurParTypeComposantePanel() {
        JLabel typeComposanteLabel = new JLabel("Entrez le type de composante");
        JTextField typeComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        recherheFournisseurParTypeComposantePanel.add(typeComposanteLabel, constraints);
        constraints.gridy = 1;
        recherheFournisseurParTypeComposantePanel.add(typeComposanteField, constraints);
        constraints.gridy = 2;
        recherheFournisseurParTypeComposantePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        recherheFournisseurParTypeComposantePanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheFournisseurClicked(btnRetourFiltre);
    }

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
        JLabel typeComposanteLabel = new JLabel("Entrez le type de composante");
        JTextField typeComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheComposanteParTypePanel.add(typeComposanteLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParTypePanel.add(typeComposanteField, constraints);
        constraints.gridy = 2;
        rechercheComposanteParTypePanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheComposanteParTypePanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
    }

    public void setRechercheComposanteParNomFournisseurPanel() {
        JLabel nomFournisseurLabel = new JLabel("Entrez le nom du fournisseur");
        JTextField nomFournisseurField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheComposanteParNomFournisseurPanel.add(nomFournisseurLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParNomFournisseurPanel.add(nomFournisseurField, constraints);
        constraints.gridy = 2;
        rechercheComposanteParNomFournisseurPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheComposanteParNomFournisseurPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
    }

    public void setRechercheComposanteParNomPanel() {
        JLabel nomComposanteLabel = new JLabel("Entrez le nom de composante");
        JTextField nomComposanteField = new JTextField();
        JButton btnContinuer = new JButton("Continuer");
        JButton btnRetourFiltre = new JButton("Retour au filtre");

        constraints.gridy = 0;
        rechercheComposanteParNomPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        rechercheComposanteParNomPanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        rechercheComposanteParNomPanel.add(btnContinuer, constraints);
        constraints.gridy = 3;
        rechercheComposanteParNomPanel.add(btnRetourFiltre, constraints);

        onBtnRetourRechercheComposanteClicked(btnRetourFiltre);
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
                    case "Nom" -> {
                        jFrame.setContentPane(recherheUtilisateurParNomPanel);
                        mettreAJourFrame();
                    }
                    case "Prenom" -> {
                        jFrame.setContentPane(recherheUtilisateurParPrenomPanel);
                        mettreAJourFrame();
                    }
                    case "Obtenir liste des suiveurs de? (pseudo voulu)" -> {
                        jFrame.setContentPane(recherheUtilisateurParListeSuiveursPanel);
                        mettreAJourFrame();
                    }
                }
            }
        });
    }

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

    public void affirmerMessageErreurCaracteres() {
        String message = "Vous n'avez pas entre 3 caracteres. Veuillez reessayer.";
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

    public void onBtnRetourRechercheInteretsClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheInteretsPanel);
                mettreAJourFrame();
            }
        });
    }
    public void onBtnRetourRechercheInteretsAvecFiltreClicked(JButton btnRetourFiltre) {
        btnRetourFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rechercheInteretsAvecFiltrePanel);
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