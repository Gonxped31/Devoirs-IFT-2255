package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * Cette classe représente l'interface graphique permettant au fournisseur de modifier son profil.
 * Elle affiche différents panels pour permettre au fournisseur de modifier son nom, adresse, email,
 * numéro de téléphone, nom de compagnie, capacité de production, ou mot de passe.
 */
public class ModifierProfilFournisseurGUI {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private String nomFournisseur;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel modifierNomPanel = new JPanel(new GridBagLayout());
    private JPanel modifierAdressePanel = new JPanel(new GridBagLayout());
    private JPanel modifierEmailPanel = new JPanel(new GridBagLayout());
    private JPanel modifierTelephonePanel = new JPanel(new GridBagLayout());
    private JPanel modifierCompagniePanel = new JPanel(new GridBagLayout());
    private JPanel modifierCapaciteProductionPanel = new JPanel(new GridBagLayout());
    private JPanel modifierMdpPanel = new JPanel(new GridBagLayout());
    private JLabel profilFournisseurLabel = new JLabel("Que voulez-vous modifier?", SwingConstants.CENTER);
    private JButton btnNom = new JButton("Nom");
    private JButton btnAdresse = new JButton("Adresse");
    private JButton btnEmail = new JButton("Email");
    private JButton btnNumeroTelephone = new JButton("Numero de telephone");
    private JButton btnNomCompagnie = new JButton("Nom de la compagnie");
    private JButton btnCapaciteProduction = new JButton("Capacite de production");
    private JButton btnMdp = new JButton("Mot de passe");
    private JButton btnRetour = new JButton("Retour au menu Fournisseur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Constructeur de la classe ModifierProfilFournisseurGUI.
     * Il configure les composants graphiques pour chaque type de modification et définit les action listeners
     * pour les boutons de sauvegarde et d'annulation.
     *
     * @param nomFournisseur Le nom du fournisseur dont le profil doit être modifié.
     * @throws IOException    En cas d'erreur lors de la lecture ou de l'écriture de fichiers.
     * @throws ParseException En cas d'erreur lors de l'analyse de dates ou d'heures.
     */
    public ModifierProfilFournisseurGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setModifierNomPanel();
        setModifierAdressePanel();
        setModifierEmailPanel();
        setModifierTelephonePanel();
        setModifierCompagniePanel();
        setModifierCapaciteProductionPanel();
        setModifierMdpPanel();

        btnNom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierNomPanel);
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
        btnCapaciteProduction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierCapaciteProductionPanel);
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
     * Configure le panel principal de l'interface graphique.
     * Ajoute les composants tels que le label du fournisseur, les boutons pour chaque type de modification (nom, adresse, email, etc.),
     * et associe les actions aux boutons pour gérer les interactions utilisateur.
     */
    public void setMainPanel() {
        profilFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(profilFournisseurLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNom);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnAdresse);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnEmail);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNumeroTelephone);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnNomCompagnie);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCapaciteProduction);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnMdp);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Configure le panel de modification du nom du fournisseur.
     * Déclare les composants tels que le label pour entrer le nouveau nom, le champ de texte pour saisir le nom,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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
     * Configure le panel de modification de l'adresse du fournisseur.
     * Déclare les composants tels que le label pour entrer la nouvelle adresse, le champ de texte pour saisir l'adresse,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleAdresseField, "addresse");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panel de modification de l'email du fournisseur.
     * Déclare les composants tels que le label pour entrer le nouvel email, le champ de texte pour saisir l'email,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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
     * Configure le panel de modification du numéro de téléphone du fournisseur.
     * Déclare les composants tels que le label pour entrer le nouveau numéro de téléphone, le champ de texte pour saisir le numéro,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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

        onBtnEnregistrerClicked(btnEnregistrer, nouveauTelephoneField, "telephone");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panel de modification du nom de la compagnie du fournisseur.
     * Déclare les composants tels que le label pour entrer le nouveau nom de la compagnie, le champ de texte pour saisir le nom,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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
     * Configure le panel de modification de la capacité de production du fournisseur.
     * Déclare les composants tels que le label pour entrer la nouvelle capacité de production, le champ de texte pour saisir la capacité,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
     */
    public void setModifierCapaciteProductionPanel() {
        JLabel capaciteProductionLabel = new JLabel("Entrez votre nouvelle capacite de production");
        JTextField nouvelleCapaciteProductionField = new JTextField();
        JButton btnEnregistrer = new JButton("Enregistrer les modifications");
        JButton btnAnnuler = new JButton("Annuler les modifications");

        nouvelleCapaciteProductionField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        modifierCapaciteProductionPanel.add(capaciteProductionLabel, constraints);
        constraints.gridy = 1;
        modifierCapaciteProductionPanel.add(nouvelleCapaciteProductionField, constraints);
        constraints.gridy = 2;
        modifierCapaciteProductionPanel.add(btnEnregistrer, constraints);
        constraints.gridy = 3;
        modifierCapaciteProductionPanel.add(btnAnnuler, constraints);

        onBtnEnregistrerClicked(btnEnregistrer, nouvelleCapaciteProductionField, "capaciteproduction");
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panel de modification du mot de passe du fournisseur.
     * Déclare les composants tels que le label pour entrer le nouveau mot de passe, le champ de texte pour saisir le mot de passe,
     * et les boutons pour enregistrer ou annuler les modifications.
     * Associe également les actions aux boutons pour gérer les interactions utilisateur.
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
     * Affiche le panel principal dans la fenêtre JFrame fournie en paramètre.
     * Enregistre également le panel précédent pour permettre le retour en arrière.
     * Met à jour la JFrame pour afficher le panel principal.
     *
     * @param jFrame La fenêtre JFrame dans laquelle afficher le panel principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour la fenêtre JFrame pour refléter les changements effectués dans les panels de modification.
     * Cette méthode permet de rafraîchir l'interface graphique après chaque changement de panel.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Méthode appelée lorsque le bouton d'enregistrement est cliqué.
     * Elle récupère la valeur saisie par l'utilisateur dans le champ de saisie correspondant
     * et appelle la méthode pour modifier le profil du fournisseur avec la nouvelle valeur.
     *
     * @param btnEnregistrer Le bouton d'enregistrement associé à ce panel de modification.
     * @param response      Le champ de saisie où l'utilisateur entre la nouvelle valeur.
     * @param modif         Le type de modification à effectuer (nom, adresse, email, etc.).
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
                        case "nom" -> controlleurFournisseurs.modifierProfile("nom", reponse, nomFournisseur);
                        case "capaciteproduction" -> controlleurFournisseurs.modifierProfile("capaciteproduction", reponse, nomFournisseur);
                        case "adresse" -> controlleurFournisseurs.modifierProfile("adresse", reponse, nomFournisseur);
                        case "pseudo" -> controlleurFournisseurs.modifierProfile("pseudo", reponse, nomFournisseur);
                        case "email" ->  controlleurFournisseurs.modifierProfile("email", reponse, nomFournisseur);
                        case "telephone" -> controlleurFournisseurs.modifierProfile("numerotelephone", reponse, nomFournisseur);
                        case "nomcompagnie" -> controlleurFournisseurs.modifierProfile("nomcompagnie", reponse, nomFournisseur);
                        case "mdp" -> controlleurFournisseurs.modifierProfile("mdp", reponse, nomFournisseur);
                    }
                    afficherMessageConfirmation();
                }
            }
        });
    }

    /**
     * Méthode appelée lorsque le bouton d'annulation est cliqué.
     * Elle revient au panel principal affichant tous les choix de modification disponibles.
     *
     * @param btnAnnuler Le bouton d'annulation associé à ce panel de modification.
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
     * Affiche un message de confirmation après la sauvegarde des modifications.
     * Ce message indique que la modification a été effectuée avec succès.
     */
    public void afficherMessageConfirmation() {
        String message = "Modification effectuee avec succes!";
        String title = "Modification terminee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur en cas de problème lors de la sauvegarde des modifications.
     * Ce message informe l'utilisateur qu'une erreur s'est produite et l'invite à réessayer.
     */
    public void afficherMessageErreur() {
        String message = "Une erreur s'est produite ! Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}