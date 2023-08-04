package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Outils.EmailSender;
import domain.logic.Outils.Verifications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Interface graphique pour l'inscription en tant que nouvel utilisateur.
 */
public class InscriptionUtilisateurGUI {
    /**
     * Contrôleur pour les opérations liées aux utilisateurs.
     */
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    /**
     * Fenêtre principale de l'interface d'inscription.
     */
    private JFrame jFrame = new JFrame();
    /**
     * Panneau d'inscription utilisateur.
     */
    private JPanel inscriptionUtilisateurPanel = new JPanel();
    /**
     * Libellé affichant l'intitulé de l'inscription en tant que nouvel utilisateur.
     */
    private JLabel inscriptionUtilisateurLabel = new JLabel("Inscription en tant que nouvel utilisateur", SwingConstants.CENTER);
    /**
     * Libellé pour le champ du prénom.
     */
    private JLabel prenomLabel = new JLabel("Prenom");
    /**
     * Libellé pour le champ du nom.
     */
    private JLabel nomLabel = new JLabel("Nom");
    /**
     * Libellé pour le champ du pseudo.
     */
    private JLabel pseudoLabel = new JLabel("Pseudo");
    /**
     * Libellé pour le champ de l'adresse courriel.
     */
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    /**
     * Libellé pour le champ du mot de passe.
     */
    private JLabel mdpLabel = new JLabel("Mot de passe");
    /**
     * Libellé pour le champ de confirmation du mot de passe.
     */
    private JLabel confirmationMdpLabel = new JLabel("Confirmation mot de passe");
    /**
     * Libellé pour le champ du numéro de téléphone.
     */
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    /**
     * Libellé pour le champ de l'adresse.
     */
    private JLabel adresseLabel = new JLabel("Adresse");
    /**
     * Libellé pour le champ du nom de compagnie.
     */
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    /**
     * Libellé pour le champ des intérêts.
     */
    private JLabel interetLabel = new JLabel("Intérêts : Veuillez séparer les intérêts par des virgules (\",\"). " +
            "Vous devez également entrer au plus 10 interêts. Les interets en trop ne seront pas considérés.");
    /**
     * Champ de texte pour le prénom.
     */
    private JTextField prenomField = new JTextField();
    /**
     * Champ de texte pour le nom.
     */
    private JTextField nomField = new JTextField();
    /**
     * Champ de texte pour le pseudo.
     */
    private JTextField pseudoField = new JTextField();
    /**
     * Champ de texte pour l'adresse courriel.
     */
    private JTextField courrielField = new JTextField();
    /**
     * Champ de texte pour le mot de passe.
     */
    private JPasswordField mdpField = new JPasswordField();
    /**
     * Champ de texte pour la confirmation du mot de passe.
     */
    private JPasswordField confirmationMdpField = new JPasswordField();
    /**
     * Champ de texte pour le numéro de téléphone.
     */
    private JTextField telephoneField = new JTextField();
    /**
     * Champ de texte pour l'adresse.
     */
    private JTextField adresseField = new JTextField();
    /**
     * Champ de texte pour le nom de compagnie.
     */
    private JTextField nomCompagnieField = new JTextField();
    /**
     * Champ de texte pour les intérêts.
     */
    private JTextField interetField = new JTextField();
    /**
     * Bouton pour confirmer l'inscription.
     */
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    /**
     * Bouton pour retourner en arrière.
     */
    private JButton btnRetour = new JButton("Retour");
    /**
     * Référence à l'interface du menu utilisateur.
     */
    private MenuUtilisateur menuUtilisateur;
    /**
     * Référence à l'interface de connexion utilisateur.
     */
    ConnexionUtilisateurGUI connexionUtilisateurGUI;
    /**
     * Conteneur de panel précédent.
     */
    private Container panelPrecedent = new Container();

    /**
     * Constructeur de la classe InscriptionUtilisateurGUI.
     * Crée une interface graphique pour l'inscription d'un nouvel utilisateur.
     *
     * @throws IOException    En cas d'erreur d'entrée/sortie.
     * @throws ParseException En cas d'erreur de parsing.
     */
    public InscriptionUtilisateurGUI() throws IOException, ParseException {
        inscriptionUtilisateurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inscriptionUtilisateurPanel.setLayout(new GridLayout(0, 1));
        inscriptionUtilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        inscriptionUtilisateurPanel.add(inscriptionUtilisateurLabel);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(prenomLabel);
        inscriptionUtilisateurPanel.add(prenomField);
        inscriptionUtilisateurPanel.add(nomLabel);
        inscriptionUtilisateurPanel.add(nomField);
        inscriptionUtilisateurPanel.add(pseudoLabel);
        inscriptionUtilisateurPanel.add(pseudoField);
        inscriptionUtilisateurPanel.add(courrielLabel);
        inscriptionUtilisateurPanel.add(courrielField);
        inscriptionUtilisateurPanel.add(mdpLabel);
        inscriptionUtilisateurPanel.add(mdpField);
        inscriptionUtilisateurPanel.add(confirmationMdpLabel);
        inscriptionUtilisateurPanel.add(confirmationMdpField);
        inscriptionUtilisateurPanel.add(telephoneLabel);
        inscriptionUtilisateurPanel.add(telephoneField);
        inscriptionUtilisateurPanel.add(adresseLabel);
        inscriptionUtilisateurPanel.add(adresseField);
        inscriptionUtilisateurPanel.add(nomCompagnieLabel);
        inscriptionUtilisateurPanel.add(nomCompagnieField);
        inscriptionUtilisateurPanel.add(interetLabel);
        inscriptionUtilisateurPanel.add(interetField);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnConfirmerInscription);
        inscriptionUtilisateurPanel.add(Box.createHorizontalStrut(10));
        inscriptionUtilisateurPanel.add(btnRetour);

        btnConfirmerInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String prenom = prenomField.getText();
                    String nom = nomField.getText();
                    String pseudo = pseudoField.getText();
                    String courriel = courrielField.getText();
                    String mdp = new String(mdpField.getPassword());
                    String confirmationMdp = new String(confirmationMdpField.getPassword());
                    String telephone = telephoneField.getText();
                    String adresse = adresseField.getText();
                    String nomCompagnie = nomCompagnieField.getText();
                    String interets = interetField.getText();
                    String[] tabInterets = interets.split(",");
                    ArrayList<String> listeInteret = new ArrayList<>();
                    for (String tabInteret : tabInterets) {
                        listeInteret.add(tabInteret.trim());
                    }
                    boolean pseudoExiste = controlleurUtilisateurs.verifierPseudo(pseudo);
                    boolean emailValide = Verifications.verifierEmail(courriel);
                    boolean telephoneValide = Verifications.verifierTelephone(telephone);
                    List<String> inputs = new ArrayList<>(Arrays.asList(prenom, nom, pseudo, courriel, mdp, confirmationMdp, telephone, adresse, nomCompagnie));
                    List<String> messageErreur = validerInput(pseudoExiste, emailValide, telephoneValide, inputs, listeInteret);

                    if (!messageErreur.isEmpty()) {
                        String title = "Erreur";
                        String message = "Erreur:\n";
                        for (String s : messageErreur) {
                            message += s + "\n";
                        }
                        int messageType = JOptionPane.ERROR_MESSAGE;

                        JOptionPane.showMessageDialog(null, message, title, messageType);
                    } else {
                        connexionUtilisateurGUI = new ConnexionUtilisateurGUI();
                        inscrireEnvoyerConfirmation(nom, prenom, adresse, pseudo,
                                mdp, courriel, telephone, nomCompagnie, listeInteret);
                        connexionUtilisateurGUI.afficherPanel(jFrame);
                    }

                } catch (IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }
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
     * Affiche le panneau d'inscription dans la fenêtre donnée.
     *
     * @param jFrame La JFrame dans laquelle afficher le panneau d'inscription.
     */
    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(inscriptionUtilisateurPanel);
        this.jFrame.revalidate();
        mettreAJourFrame();
    }

    /**
     * Met à jour la fenêtre en révalidant et redessinant son contenu.
     */
    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Valide les entrées de l'inscription et retourne une liste de messages d'erreur le cas échéant.
     *
     * @param pseudoExiste   Indique si le pseudo existe déjà.
     * @param emailValide    Indique si l'email est valide.
     * @param telephoneValide Indique si le numéro de téléphone est valide.
     * @param inputs         Liste des valeurs des champs de saisie.
     * @param interets       Liste des intérêts de l'utilisateur.
     * @return Une liste de messages d'erreur ou une liste vide si aucune erreur n'est présente.
     */
    private List<String> validerInput(boolean pseudoExiste, boolean emailValide, boolean telephoneValide, List<String> inputs, ArrayList<String> interets){
        List<String> messageErreur = new ArrayList<>();

        if (interets.isEmpty()){
            messageErreur.add("Au moins un des champ n'a pas été rempli.");
        } else {
            for (String s: inputs) {
                if (s.isEmpty()){
                    messageErreur.add("Au moins un des champs n'a pas été rempli.");
                    break;
                }
            }
        }

        if (pseudoExiste) {
            messageErreur.add("Un utilisateur possàde déjà ce pseudo.");
        }

        if (!emailValide){
            messageErreur.add("L'email n'est pas valide.");
        }

        if (!telephoneValide){
            messageErreur.add("Le numéro de téléphone n'est pas valide.");
        }

        if (!inputs.get(4).equals(inputs.get(5))) {
            messageErreur.add("Le mot de passe n'est pas identique.");
        }

        return messageErreur;
    }

    /**
     * Inscrit l'utilisateur et envoie un courriel de confirmation.
     *
     * @param nom           Le nom de l'utilisateur.
     * @param prenom        Le prénom de l'utilisateur.
     * @param adresse       L'adresse de l'utilisateur.
     * @param pseudo        Le pseudo de l'utilisateur.
     * @param mdp           Le mot de passe de l'utilisateur.
     * @param courriel      L'adresse courriel de l'utilisateur.
     * @param telephone     Le numéro de téléphone de l'utilisateur.
     * @param nomCompagnie  Le nom de la compagnie de l'utilisateur.
     * @param listeInteret  La liste des intérêts de l'utilisateur.
     * @throws IOException      En cas d'erreur d'entrée/sortie lors de l'envoi du courriel.
     * @throws ParseException   En cas d'erreur lors de l'analyse des données.
     */
    private void inscrireEnvoyerConfirmation(String nom, String prenom, String adresse, String pseudo,
                                             String mdp, String courriel, String telephone, String nomCompagnie, ArrayList<String> listeInteret) throws IOException, ParseException {
        controlleurUtilisateurs = ControlleurUtilisateurs.getControlleurUtilisateurs(nom, prenom, adresse, pseudo,
                mdp, courriel, telephone, nomCompagnie, listeInteret);
        controlleurUtilisateurs.inscriptionUtilisateur(nom, prenom, adresse, pseudo,mdp, courriel, telephone, nomCompagnie, listeInteret);

        String body = "Cher " + pseudo + ",\n\nNous vous remercions de vous être abonné à Robotix !\nProfitez des fonctionalitées multiples" +
                " que vous propose cette application en tant qu'utilisateur ! Si vous désirez toutefois vendre aussi des robots, vous pouvez" +
                " toujours vous inscrire en tant que fournisseur. \n\nCordialement,\nL'équipe Robotix";

        String message = "Inscription réussie ! Nous vous avons envoyé un couriel de confirmation.\nVeuillez consulter vos couriel svp.";

        JOptionPane.showMessageDialog(null, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        EmailSender emailSender = new EmailSender("robotrobotix4@gmail.com","lkzojmozphkprruj", courriel,
                "Confirmation d'inscription", body);

        emailSender.sendInBackground();
    }
}
