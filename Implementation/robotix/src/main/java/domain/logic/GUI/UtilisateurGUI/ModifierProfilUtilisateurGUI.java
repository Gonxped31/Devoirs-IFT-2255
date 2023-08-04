package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * Cette classe représente l'interface utilisateur pour la modification du profil utilisateur.
 * Elle permet de modifier différents aspects du profil utilisateur.
 */
public class ModifierProfilUtilisateurGUI {
    /**
     * Le pseudo de l'utilisateur actuel.
     */
    String pseudo;
    /**
     * Le contrôleur pour les opérations liées aux utilisateurs.
     */
    ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Fenêtre principale de l'interface utilisateur.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Panneau principal contenant les options de modification.
     */
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    /**
     * Panneaux spécifiques pour chaque modification - Modifier le nom.
     */
    private JPanel modifierNomPanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier le prénom.
     */
    private JPanel modifierPrenomPanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier l'adresse.
     */
    private JPanel modifierAdressePanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier le pseudo.
     */
    private JPanel modifierPseudoPanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier l'email.
     */
    private JPanel modifierEmailPanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier le téléphone.
     */
    private JPanel modifierTelephonePanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier la compagnie.
     */
    private JPanel modifierCompagniePanel = new JPanel(new GridBagLayout());
    /**
     * Panneaux spécifiques pour chaque modification - Modifier le mot de passe.
     */
    private JPanel modifierMdpPanel = new JPanel(new GridBagLayout());
    /**
     * Étiquette indiquant le but de l'interface.
     */
    private JLabel profilUtilisateurLabel = new JLabel("Que voulez-vous modifier?", SwingConstants.CENTER);
    /**
     * Bouton pour déclencher la modification du nom.
     */
    private JButton btnNom = new JButton("Nom");
    /**
     * Bouton pour déclencher la modification du prénom.
     */
    private JButton btnPrenom = new JButton("Prenom");
    /**
     * Bouton pour déclencher la modification de l'adresse.
     */
    private JButton btnAdresse = new JButton("Adresse");
    /**
     * Bouton pour déclencher la modification du pseudo.
     */
    private JButton btnPseudo = new JButton("Pseudo");
    /**
     * Bouton pour déclencher la modification de l'email.
     */
    private JButton btnEmail = new JButton("Email");
    /**
     * Bouton pour déclencher la modification du numéro de téléphone.
     */
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    /**
     * Bouton pour déclencher la modification du nom de la compagnie.
     */
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    /**
     * Bouton pour déclencher la modification du mot de passe.
     */
    private JButton btnMdp = new JButton("Mot de passe");
    /**
     * Bouton pour retourner au menu utilisateur.
     */
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    /**
     * Conteneur précédent pour sauvegarder le contenu précédent du panneau.
     */
    private Container panelPrecedent = new Container();
    /**
     * Contraintes pour la disposition des composants dans un panneau.
     */
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Cette classe représente l'interface utilisateur pour la modification du profil utilisateur.
     * Elle permet à l'utilisateur de modifier différentes informations de son profil.
     *
     * @param pseudo Le pseudo de l'utilisateur actuel.
     * @throws IOException En cas d'erreur d'entrée/sortie lors de l'accès aux données.
     * @throws ParseException En cas d'erreur lors de l'analyse des données.
     */
    public ModifierProfilUtilisateurGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setModifierNomPanel();
        setModifierPrenomPanel();
        setModifierAdressePanel();
        setModifierPseudoPanel();
        setModifierEmailPanel();
        setModifierTelephonePanel();
        setModifierCompagniePanel();
        setModifierMdpPanel();

        btnNom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierNomPanel);
                mettreAJourFrame();
            }
        });
        btnPrenom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPrenomPanel);
                mettreAJourFrame();
            }
        });
        btnAdresse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierAdressePanel);
                mettreAJourFrame();
            }
        });
        btnPseudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPseudoPanel);
                mettreAJourFrame();
            }
        });
        btnEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierEmailPanel);
                mettreAJourFrame();
            }
        });
        btnNumeroTelephone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierTelephonePanel);
                mettreAJourFrame();
            }
        });
        btnNomCompagnie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierCompagniePanel);
                mettreAJourFrame();
            }
        });
        btnMdp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierMdpPanel);
                mettreAJourFrame();
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent);
                mettreAJourFrame();
            }
        });
    }

    /**
     * Définit le panneau principal de modification du profil utilisateur.
     * Ce panneau affiche les boutons permettant à l'utilisateur de choisir
     * quelle information du profil il souhaite modifier.
     */
    public void setMainPanel() {
        profilUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(profilUtilisateurLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnPrenom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAdresse);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnPseudo);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnEmail);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnMdp);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Définit le panneau de modification du nom de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau nom d'utilisateur.
     */
    public void setModifierNomPanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomLabel = new JLabel("Entrez votre nouveau nom");
        JTextField nouveauNomField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauNomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierNomPanel.add(nomLabel, constraints);
        constraints.gridy = 1;
        modifierNomPanel.add(nouveauNomField, constraints);
        constraints.gridy = 2;
        modifierNomPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierNomPanel.add(btnAnnuler, constraints);
        onBtnEnregistrerClicked(btnEnregistrer, nouveauNomField, "nom");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification du prénom de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau prénom d'utilisateur.
     */
    public void setModifierPrenomPanel() {
        JLabel prenomLabel = new JLabel("Entrez votre nouveau prenom");
        JTextField nouveauPrenomField = new JTextField();
        JButton btnEnregistrerPrenom = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauPrenomField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPrenomPanel.add(prenomLabel, constraints);
        constraints.gridy = 1;
        modifierPrenomPanel.add(nouveauPrenomField, constraints);
        constraints.gridy = 2;
        modifierPrenomPanel.add(btnEnregistrerPrenom, constraints);
        constraints.gridy = 3;
        modifierPrenomPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrerPrenom, nouveauPrenomField, "prenom");
        onBtnAnnulerClicked(btnAnnuler);
    }
    /**
     * Définit le panneau de modification de l'adresse de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * une nouvelle adresse d'utilisateur.
     */
    public void setModifierAdressePanel() {
        JLabel adresseLabel = new JLabel("Entrez votre nouvelle adresse");
        JTextField nouvelleAdresseField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleAdresseField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierAdressePanel.add(adresseLabel, constraints);
        constraints.gridy = 1;
        modifierAdressePanel.add(nouvelleAdresseField, constraints);
        constraints.gridy = 2;
        modifierAdressePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierAdressePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleAdresseField, "adresse");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification du pseudo de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau pseudo d'utilisateur.
     */
    public void setModifierPseudoPanel() {
        JLabel pseudoLabel = new JLabel("Entrez votre nouveau pseudo");
        JTextField nouveauPseudoField = new JTextField();
        JButton btnEnregistrerPseudo = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauPseudoField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierPseudoPanel.add(pseudoLabel, constraints);
        constraints.gridy = 1;
        modifierPseudoPanel.add(nouveauPseudoField, constraints);
        constraints.gridy = 2;
        modifierPseudoPanel.add(btnEnregistrerPseudo, constraints);
        constraints.gridy = 3;
        modifierPseudoPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrerPseudo, nouveauPseudoField, "pseudo");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification de l'email de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau email d'utilisateur.
     */
    public void setModifierEmailPanel() {
        JLabel emailLabel = new JLabel("Entrez votre nouveau email");
        JTextField nouveauEmailField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauEmailField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierEmailPanel.add(emailLabel, constraints);
        constraints.gridy = 1;
        modifierEmailPanel.add(nouveauEmailField, constraints);
        constraints.gridy = 2;
        modifierEmailPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierEmailPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauEmailField, "email");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification du numéro de téléphone de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau numéro de téléphone d'utilisateur.
     */
    public void setModifierTelephonePanel() {
        JLabel telephoneLabel = new JLabel("Entrez votre nouveau numero de telephone");
        JTextField nouveauTelephoneField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauTelephoneField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierTelephonePanel.add(telephoneLabel, constraints);
        constraints.gridy = 1;
        modifierTelephonePanel.add(nouveauTelephoneField, constraints);
        constraints.gridy = 2;
        modifierTelephonePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierTelephonePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauTelephoneField, "numerotelephone");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification de la compagnie de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * une nouvelle compagnie d'utilisateur.
     */
    public void setModifierCompagniePanel() {
        JLabel compagnieLabel = new JLabel("Entrez votre nouvelle compagnie");
        JTextField nouvelleCompagnieField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleCompagnieField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierCompagniePanel.add(compagnieLabel, constraints);
        constraints.gridy = 1;
        modifierCompagniePanel.add(nouvelleCompagnieField, constraints);
        constraints.gridy = 2;
        modifierCompagniePanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierCompagniePanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleCompagnieField, "nomcompagnie");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Définit le panneau de modification du mot de passe de l'utilisateur.
     * Ce panneau contient les éléments nécessaires pour entrer et enregistrer
     * un nouveau mot de passe d'utilisateur.
     */
    public void setModifierMdpPanel() {
        JLabel mdpLabel = new JLabel("Entrez votre nouveau mot de passe");
        JPasswordField nouveauMdpField = new JPasswordField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouveauMdpField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierMdpPanel.add(mdpLabel, constraints);
        constraints.gridy = 1;
        modifierMdpPanel.add(nouveauMdpField, constraints);
        constraints.gridy = 2;
        modifierMdpPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierMdpPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouveauMdpField, "mdp");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Affiche le panneau principal dans la fenêtre JFrame donnée.
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
     * Cette méthode permet de rafraîchir l'affichage après des modifications.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Associe l'action de cliquer sur le bouton "Enregistrer les modifications"
     * à la modification du profil de l'utilisateur en fonction du champ de modification.
     * Affiche un message de confirmation en cas de succès ou un message d'erreur en cas d'échec.
     * @param btnEnregistrer Le bouton "Enregistrer les modifications" sur lequel l'action est associée.
     * @param response Le champ de réponse contenant la nouvelle valeur de la modification.
     * @param modif Le type de modification à effectuer (nom, prénom, adresse, etc.).
     */
    public void onBtnEnregistrerClicked(JButton btnEnregistrer, JTextField response, String modif) {
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reponse = response.getText();
                if (reponse.isEmpty()) {
                    afficherMessageErreur();
                } else {
                    switch (modif.toLowerCase().trim()) {
                        case "nom" -> controlleurUtilisateurs.modifierProfile("nom", reponse, pseudo);
                        case "prenom" -> controlleurUtilisateurs.modifierProfile("prenom", reponse, pseudo);
                        case "adresse" -> controlleurUtilisateurs.modifierProfile("adresse", reponse, pseudo);
                        case "pseudo" -> controlleurUtilisateurs.modifierProfile("pseudo", reponse, pseudo);
                        case "email" ->  controlleurUtilisateurs.modifierProfile("email", reponse, pseudo);
                        case "numerotelephone" -> controlleurUtilisateurs.modifierProfile("numerotelephone", reponse, pseudo);
                        case "nomcompagnie" -> controlleurUtilisateurs.modifierProfile("nomcompagnie", reponse, pseudo);
                        case "mdp" -> controlleurUtilisateurs.modifierProfile("mdp", reponse, pseudo);
                    }
                    afficherMessageConfirmation();
                }
            }
        });
    }


    /**
     * Associe l'action de cliquer sur le bouton "Annuler les modifications"
     * au retour à l'écran principal de modification du profil.
     * @param btnAnnuler Le bouton "Annuler les modifications" sur lequel l'action est associée.
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
     * Affiche un message de confirmation en cas de succès de la modification du profil.
     */
    public void afficherMessageConfirmation() {
        String message = "Changement effectué avec succès !";
        String title = "Modification terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur en cas d'échec de la modification du profil.
     */
    public void afficherMessageErreur() {
        String message = "Une erreur s'est produite ! Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
