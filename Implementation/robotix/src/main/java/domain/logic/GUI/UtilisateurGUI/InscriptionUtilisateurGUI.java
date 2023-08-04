package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurFournisseurs;
import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Menu.MenuUtilisateur;
import domain.logic.Menu.MenusFournisseur;
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

public class InscriptionUtilisateurGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private JFrame jFrame = new JFrame();
    private JPanel inscriptionUtilisateurPanel = new JPanel();
    private JLabel inscriptionUtilisateurLabel = new JLabel("Inscription en tant que nouvel utilisateur", SwingConstants.CENTER);
    private JLabel prenomLabel = new JLabel("Prenom");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel pseudoLabel = new JLabel("Pseudo");
    private JLabel courrielLabel = new JLabel("Adresse courriel");
    private JLabel mdpLabel = new JLabel("Mot de passe");
    private JLabel confirmationMdpLabel = new JLabel("Confirmation mot de passe");
    private JLabel telephoneLabel = new JLabel("Numero de telephone");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel nomCompagnieLabel = new JLabel("Nom de compagnie");
    private JLabel interetLabel = new JLabel("Intérêts : Veuillez séparer les intérêts par des virgules (\",\"). " +
            "Vous devez également entrer au plus 10 interêts. Les interets en trop ne seront pas considérés.");
    private JTextField prenomField = new JTextField();
    private JTextField nomField = new JTextField();
    private JTextField pseudoField = new JTextField();
    private JTextField courrielField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();
    private JPasswordField confirmationMdpField = new JPasswordField();
    private JTextField telephoneField = new JTextField();
    private JTextField adresseField = new JTextField();
    private JTextField nomCompagnieField = new JTextField();
    private JTextField interetField = new JTextField();
    private JButton btnConfirmerInscription = new JButton("Confirmer");
    private JButton btnRetour = new JButton("Retour");
    private MenuUtilisateur menuUtilisateur;
    ConnexionUtilisateurGUI connexionUtilisateurGUI;
    private Container panelPrecedent = new Container();

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

    public void afficherPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Principal
        this.jFrame = jFrame;
        this.jFrame.setContentPane(inscriptionUtilisateurPanel);
        this.jFrame.revalidate();
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }

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
