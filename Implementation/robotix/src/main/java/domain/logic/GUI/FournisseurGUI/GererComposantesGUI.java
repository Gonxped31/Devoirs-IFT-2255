package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * Cette classe gère l'interface graphique pour la gestion des composantes par un fournisseur.
 */
public class GererComposantesGUI {
    private ControlleurFournisseurs controlleurFournisseurs = new ControlleurFournisseurs();
    private String nomFournisseur;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel suppressionComposantePanel = new JPanel(new GridBagLayout());
    private JPanel modifierPrixPanel = new JPanel(new GridBagLayout());
    private JPanel modifierDescPanel = new JPanel(new GridBagLayout());
    private JLabel gererComposantesLabel = new JLabel("Choisissez une option", SwingConstants.CENTER);
    private JButton btnSupprimer = new JButton("Supprimer");
    private JButton btnModifierPrix = new JButton("Modifier le prix");
    private JButton btnModifierDesc = new JButton("Modifier la description");
    private JButton btnRetour = new JButton("Retour au menu Fournisseur");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    /**
     * Constructeur de la classe GererComposantesGUI.
     *
     * @param nomFournisseur Le nom du fournisseur qui gère les composantes.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ParseException Si une erreur de parsing se produit.
     */
    public GererComposantesGUI(String nomFournisseur) throws IOException, ParseException {
        this.nomFournisseur = nomFournisseur;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Setup des panels
        setMainPanel();
        setSuppressionComposantePanel();
        setModificationPrixPanel();
        setModificationDescPanel();

        // ActionListener de tous les boutons de ce panel
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(suppressionComposantePanel);
                mettreAJourFrame();
            }
        });
        btnModifierPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierPrixPanel);
                mettreAJourFrame();
            }
        });
        btnModifierDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(modifierDescPanel);
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
     * Configure le panel principal de gestion des composantes.
     * Il ajoute les composantes nécessaires au panel, tels que des étiquettes et des boutons,
     * et définit les action listeners pour les interactions avec les boutons.
     */
    public void setMainPanel() {
        gererComposantesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gererComposantesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnSupprimer);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierPrix);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnModifierDesc);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    /**
     * Configure le panel pour la suppression d'une composante.
     * Il ajoute les composants nécessaires tels que des étiquettes, des champs de texte et des boutons,
     * et définit les action listeners pour les interactions avec les boutons.
     */
    public void setSuppressionComposantePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a supprimer");
        JTextField nomComposanteField = new JTextField();
        JButton btnConfirmerSupprression = new JButton("Supprimer la composante");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension du JTextField
        nomComposanteField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        suppressionComposantePanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        suppressionComposantePanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        suppressionComposantePanel.add(btnConfirmerSupprression, constraints);
        constraints.gridy = 3;
        suppressionComposantePanel.add(btnAnnuler, constraints);

        // ActionListener des boutons
        onBtnSuppressionClicked(btnConfirmerSupprression, nomComposanteField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panel pour la modification du prix d'une composante.
     * Il ajoute les composants nécessaires tels que des étiquettes, des champs de texte et des boutons,
     * et définit les action listeners pour les interactions avec les boutons.
     */
    public void setModificationPrixPanel() {
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a modifier");
        JLabel nouveauPrixLabel = new JLabel("Nouveau prix");
        JTextField nomComposanteField = new JTextField();
        JTextField nouveauPrixField = new JTextField();
        JButton btnModifierPrix = new JButton("Modifier le prix");
        JButton btnAnnuler = new JButton("Annuler");

        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        nouveauPrixField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        modifierPrixPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        modifierPrixPanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        modifierPrixPanel.add(nouveauPrixLabel, constraints);
        constraints.gridy = 3;
        modifierPrixPanel.add(nouveauPrixField, constraints);
        constraints.gridy = 4;
        modifierPrixPanel.add(btnModifierPrix, constraints);
        constraints.gridy = 5;
        modifierPrixPanel.add(btnAnnuler, constraints);

        onBtnModifierPrixClicked(btnModifierPrix, nomComposanteField, nouveauPrixField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Configure le panel pour la modification de la description d'une composante.
     * Il ajoute les composants nécessaires tels que des étiquettes, des champs de texte et des boutons,
     * et définit les action listeners pour les interactions avec les boutons.
     */
    public void setModificationDescPanel() {
        JLabel nomComposanteLabel = new JLabel("Nom de la composante a modifier");
        JLabel nouveauDescLabel = new JLabel("Nouvelle description");
        JTextField nomComposanteField = new JTextField();
        JTextField nouveauDescField = new JTextField();
        JButton btnModifierDesc = new JButton("Modifier la descriprion");
        JButton btnAnnuler = new JButton("Annuler");

        nomComposanteField.setPreferredSize(new Dimension(200, 30));
        nouveauDescField.setPreferredSize(new Dimension(200, 30));

        constraints.gridy = 0;
        modifierDescPanel.add(nomComposanteLabel, constraints);
        constraints.gridy = 1;
        modifierDescPanel.add(nomComposanteField, constraints);
        constraints.gridy = 2;
        modifierDescPanel.add(nouveauDescLabel, constraints);
        constraints.gridy = 3;
        modifierDescPanel.add(nouveauDescField, constraints);
        constraints.gridy = 4;
        modifierDescPanel.add(btnModifierDesc, constraints);
        constraints.gridy = 5;
        modifierDescPanel.add(btnAnnuler, constraints);

        onBtnModifierDescClicked(btnModifierDesc, nomComposanteField, nouveauDescField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    /**
     * Affiche le panel principal sur une fenêtre JFrame.
     * Il sauvegarde également le panel précédent pour permettre le retour au menu Fournisseur.
     *
     * @param jFrame La fenêtre JFrame où afficher le panel principal.
     */
    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Met à jour la fenêtre JFrame après avoir effectué des modifications sur les composants graphiques.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Action listener pour le bouton "Supprimer la composante".
     * Il gère la suppression de la composante en fonction des informations saisies dans le champ de texte.
     *
     * @param btnConfirmerSupprression Le bouton "Supprimer la composante".
     * @param nomComposanteField      Le champ de texte contenant le nom de la composante à supprimer.
     */
    public void onBtnSuppressionClicked(JButton btnConfirmerSupprression, JTextField nomComposanteField) {
        btnConfirmerSupprression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomComposanteField.getText().isEmpty())
                    afficherMessageErreurSuppresion("Veuillez entrer un nom.");
                else {
                    if (controlleurFournisseurs.retirerComposante(nomComposanteField.getText(), nomFournisseur)){
                        confirmerSuppresion();
                    } else {
                        afficherMessageErreurSuppresion("Vous ne possedez pas cette composante.");
                    }
                }
            }
        });
    }

    /**
     * Action listener pour le bouton "Modifier le prix".
     * Il gère la modification du prix de la composante en fonction des informations saisies dans les champs de texte.
     *
     * @param btnModifierPrix      Le bouton "Modifier le prix".
     * @param nomComposanteField  Le champ de texte contenant le nom de la composante à modifier.
     * @param nouveauPrixField    Le champ de texte contenant le nouveau prix de la composante.
     */
    public void onBtnModifierPrixClicked(JButton btnModifierPrix, JTextField nomComposanteField, JTextField nouveauPrixField) {
        btnModifierPrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomComposante = nomComposanteField.getText();
                String nouveauPrix = nouveauPrixField.getText();
                if (nomComposante.isEmpty() || nouveauPrix.isEmpty())
                    afficherMessageErreurModificationPrix("Veuillez remplir tous les champ svp.");
                else{
                    if (controlleurFournisseurs.modifierPrixComposante(nomComposante, nouveauPrix, nomFournisseur)){
                        confirmerModificationPrix();
                    } else {
                        afficherMessageErreurSuppresion("Vous ne possedez pas cette composante.");
                    }
                }
            }
        });
    }

    /**
     * Action listener pour le bouton "Modifier la description".
     * Il gère la modification de la description de la composante en fonction des informations saisies dans les champs de texte.
     *
     * @param btnModifierDesc       Le bouton "Modifier la description".
     * @param nomComposanteField    Le champ de texte contenant le nom de la composante à modifier.
     * @param nouveauDescField      Le champ de texte contenant la nouvelle description de la composante.
     */
    public void onBtnModifierDescClicked(JButton btnModifierDesc, JTextField nomComposanteField, JTextField nouveauDescField) {
        btnModifierDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomComposante = nomComposanteField.getText();
                String nouvelleDescription = nouveauDescField.getText();
                if (nomComposante.isEmpty() || nouvelleDescription.isEmpty())
                    afficherMessageErreurModificationDesc("Veuillez remplir tous les champ.");
                else{
                    if (controlleurFournisseurs.modifierDescriptionComposante(nomComposante, nouvelleDescription, nomFournisseur)){
                        confirmerModificationDesc();
                    } else {
                        afficherMessageErreurModificationDesc("Vous ne possedez pas cette composante.");
                    }

                }
            }
        });
    }

    /**
     * Affiche un message de confirmation après une suppression réussie de la composante.
     */
    public void confirmerSuppresion() {
        String message = "La composante a ete retiree avec succes! ";
        String title = "Suppression reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message de confirmation après une modification réussie du prix de la composante.
     */
    public void confirmerModificationPrix() {
        String message = "Le prix de la composante a ete modifiee avec succes! ";
        String title = "Modification reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message de confirmation après une modification réussie de la description de la composante.
     */
    public void confirmerModificationDesc() {
        String message = "La description de la composante a ete modifieee avec succes! ";
        String title = "Modification reussie";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    /**
     * Affiche un message d'erreur en cas d'erreur lors de la suppression d'une composante.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurSuppresion(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche un message d'erreur en cas d'erreur lors de la modification du prix d'une composante.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurModificationPrix(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Affiche un message d'erreur en cas d'erreur lors de la modification de la description d'une composante.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherMessageErreurModificationDesc(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
     * Action listener pour le bouton "Annuler".
     * Il permet de retourner au panel principal après avoir cliqué sur le bouton "Annuler".
     *
     * @param btnAnnuler Le bouton "Annuler".
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
}