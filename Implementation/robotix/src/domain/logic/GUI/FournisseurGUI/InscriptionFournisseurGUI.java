package domain.logic.GUI.FournisseurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
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
 * Cette classe représente l'interface graphique pour l'inscription en tant que nouveau fournisseur.
 * Elle permet à un nouveau fournisseur de saisir ses informations, de les valider et de s'inscrire.
 */
public class InscriptionFournisseurGUI {
    private ControlleurFournisseurs controlleurFournisseurs;
    private JFrame jFrame = new JFrame();
    private JPanel inscriptionFournisseurPanel = new JPanel();
    private JLabel inscriptionFournisseurLabel = new JLabel("Inscription en tant que nouveau fournisseur", SwingConstants.CENTER);
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel confirmerMdpLabel = new JLabel("Confirmer le mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel typeRobotFabriquesLabel = new JLabel("Type de robots fabriques");
    private JLabel typeComposantesFabriquesLabel = new JLabel("Type de composantes fabriques");
    private JLabel capaciteFabricationLabel = new JLabel("Capacite de fabrication");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JTextField nomField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JPasswordField confirmerMdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField typeRobotFabriquesField = new JTextField();
    private JTextField typeComposantesFabriquesField = new JTextField();
    private JTextField capaciteFabricationField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    private JButton btnRetour = new JButton("Retour");
    private MenusFournisseur menusFournisseur;
    private Container panelPrecedent = new Container();

    /**
     * Constructeur de la classe InscriptionFournisseurGUI.
     * Il configure les composants graphiques et définit les action listeners pour les boutons.
     *
     * @throws IOException    En cas d'erreur lors de la lecture ou de l'écriture de fichiers.
     * @throws ParseException En cas d'erreur lors de l'analyse de dates ou d'heures.
     */
    public InscriptionFournisseurGUI() throws IOException, ParseException {
        inscriptionFournisseurLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inscriptionFournisseurPanel.setLayout(new GridLayout(0, 1));
        inscriptionFournisseurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        inscriptionFournisseurPanel.add(inscriptionFournisseurLabel);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(nomLabel);
        inscriptionFournisseurPanel.add(nomField);
        inscriptionFournisseurPanel.add(courrielLabel);
        inscriptionFournisseurPanel.add(courrielField);
        inscriptionFournisseurPanel.add(mdpLabel);
        inscriptionFournisseurPanel.add(mdpField);
        inscriptionFournisseurPanel.add(confirmerMdpLabel);
        inscriptionFournisseurPanel.add(confirmerMdpField);
        inscriptionFournisseurPanel.add(telephoneLabel);
        inscriptionFournisseurPanel.add(telephoneField);
        inscriptionFournisseurPanel.add(adresseLabel);
        inscriptionFournisseurPanel.add(adresseField);
        inscriptionFournisseurPanel.add(typeRobotFabriquesLabel);
        inscriptionFournisseurPanel.add(typeRobotFabriquesField);
        inscriptionFournisseurPanel.add(typeComposantesFabriquesLabel);
        inscriptionFournisseurPanel.add(typeComposantesFabriquesField);
        inscriptionFournisseurPanel.add(capaciteFabricationLabel);
        inscriptionFournisseurPanel.add(capaciteFabricationField);
        inscriptionFournisseurPanel.add(nomCompagnieLabel);
        inscriptionFournisseurPanel.add(nomCompagnieField);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(btnConfirmerInscription);
        inscriptionFournisseurPanel.add(Box.createHorizontalStrut(10));
        inscriptionFournisseurPanel.add(btnRetour);

        btnConfirmerInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlleurFournisseurs = new ControlleurFournisseurs();
                    String inputNom = nomField.getText();
                    String inputEmail = courrielField.getText();
                    String inputTelephone = telephoneField.getText();
                    String mdp = new String(mdpField.getPassword()) ;
                    String confirmationMdp = new String(confirmerMdpField.getPassword());
                    String inputAdresse = adresseField.getText();
                    String inputTypeRobot = typeRobotFabriquesField.getText();
                    String inputTypeComposantes = typeComposantesFabriquesField.getText();
                    String inputCapacite = capaciteFabricationField.getText();
                    String inputCompagnie = nomCompagnieField.getText();

                    boolean nomExiste = controlleurFournisseurs.verifierNom(inputNom);
                    boolean emailValide = Verifications.verifierEmail(inputEmail);
                    boolean telephoneValide = Verifications.verifierTelephone(inputTelephone);

                    List<String> inputs = new ArrayList<>(Arrays.asList(inputNom, inputEmail, inputTelephone, mdp ,
                            confirmationMdp, inputAdresse, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie));

                    List<String> messageErreur = validerInput(nomExiste, emailValide, telephoneValide, inputs);

                    if (!messageErreur.isEmpty()) {
                        String message = "Erreur:\n";
                        for (String s : messageErreur) {
                            message += s + "\n";
                        }
                        String title = "Erreur";
                        int messageType = JOptionPane.ERROR_MESSAGE;

                        JOptionPane.showMessageDialog(null, message, title, messageType);
                    } else {
                        menusFournisseur = new MenusFournisseur(inputNom);
                        inscrireEnvoyerConfirmation(inputNom,mdp,inputAdresse,inputEmail,
                                inputTelephone,inputTypeRobot,inputTypeComposantes,inputCapacite,inputCompagnie);
                        menusFournisseur.afficherMenuFournisseur(jFrame);
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
                jFrame.revalidate();
                jFrame.repaint();
            }
        });
    }

    /**
     * Affiche le panel d'inscription en tant que nouveau fournisseur sur une fenêtre JFrame.
     *
     * @param jFrame La fenêtre JFrame où afficher le panel d'inscription.
     */
    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(inscriptionFournisseurPanel);
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

    /**
     * Valide les informations saisies par le fournisseur lors de l'inscription.
     * Vérifie si les champs sont remplis, si le nom de fournisseur existe déjà,
     * si l'adresse email est valide et si le numéro de téléphone est valide.
     *
     * @param nomExiste       Indique si le nom de fournisseur existe déjà dans la base de données.
     * @param emailValide     Indique si l'adresse email saisie est valide.
     * @param telephoneValide Indique si le numéro de téléphone saisie est valide.
     * @param inputs          Une liste contenant les informations saisies par le fournisseur.
     * @return Une liste de messages d'erreur en cas de validation échouée, sinon une liste vide.
     */
    private List<String> validerInput(boolean nomExiste, boolean emailValide, boolean telephoneValide,
                                      List<String> inputs){
        List<String> messageErreur = new ArrayList<>();

        for (String s: inputs) {
            if (s.isEmpty()){
                messageErreur.add("Au moins un des champs n'a pas été rempli.");
                break;
            }
        }

        if (nomExiste) {
            messageErreur.add("Un fournisseur possède déjà ce nom.");
        }

        if (!emailValide){
            messageErreur.add("L'email n'est pas valide.");
        }

        if (!telephoneValide){
            messageErreur.add("Le numéro de téléphone n'est pas valide.");
        }

        if (!inputs.get(3).equals(inputs.get(4))) {
            messageErreur.add("Le mot de passe n'est pas identique.");
        }

        return messageErreur;
    }

    /**
     * Effectue l'inscription du nouveau fournisseur et envoie un courriel de confirmation.
     *
     * @param inputNom                Le nom du nouveau fournisseur.
     * @param mdp                     Le mot de passe du nouveau fournisseur.
     * @param inputAdresse            L'adresse du nouveau fournisseur.
     * @param inputEmail              L'adresse email du nouveau fournisseur.
     * @param inputTelephone          Le numéro de téléphone du nouveau fournisseur.
     * @param inputTypeRobot          Le type de robots fabriqués par le nouveau fournisseur.
     * @param inputTypeComposantes    Le type de composantes fabriquées par le nouveau fournisseur.
     * @param inputCapacite           La capacité de fabrication du nouveau fournisseur.
     * @param inputCompagnie          Le nom de la compagnie du nouveau fournisseur.
     * @throws IOException    En cas d'erreur lors de la lecture ou de l'écriture de fichiers.
     * @throws ParseException En cas d'erreur lors de l'analyse de dates ou d'heures.
     */
    private void inscrireEnvoyerConfirmation(String inputNom, String mdp, String inputAdresse, String inputEmail,
                                             String inputTelephone, String inputTypeRobot, String inputTypeComposantes,
                                             String inputCapacite, String inputCompagnie) throws IOException, ParseException {
        controlleurFournisseurs  = new ControlleurFournisseurs(inputNom,mdp,inputAdresse,inputEmail,
                inputTelephone,inputTypeRobot,inputTypeComposantes,inputCapacite,inputCompagnie);
        controlleurFournisseurs.inscriptionFournisseur(inputNom, mdp, inputAdresse, inputEmail,
                inputTelephone, inputTypeRobot, inputTypeComposantes, inputCapacite, inputCompagnie);

        String body = "Cher " + inputNom + ",\n\nNous vous remercions de vous être abonné au syteme de vente de robot chez Robotix !" +
                "\nSi vous avez toutefois besoin de gérer des robots, vous pouvez vous inscrire en tant qu'utilisateur. " +
                "\n\nCordialement,\nL'équipe Robotix";

        String message = "Inscription réussie ! Nous vous avons envoyé un couriel de confirmation.\nVeuillez consulter vos couriel svp.";

        JOptionPane.showMessageDialog(null, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        EmailSender emailSender = new EmailSender("robotrobotix4@gmail.com","lkzojmozphkprruj", inputEmail,
                "Confirmation d'inscription", body);

        emailSender.sendInBackground();
    }

}
