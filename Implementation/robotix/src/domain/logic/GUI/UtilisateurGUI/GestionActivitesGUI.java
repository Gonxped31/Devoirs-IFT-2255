package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Outils.GestionDates;
import domain.logic.Outils.Verifications;
import domain.logic.Robot.Action;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Tache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class GestionActivitesGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    private String pseudo;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel creerActivitePanel = new JPanel(new GridBagLayout());
    private JPanel rejoindreActivitePanel = new JPanel(new GridBagLayout());
    private JLabel gestionActivitesLabel = new JLabel("Gestion de mes activites", SwingConstants.CENTER);
    private JButton btnCreerActivite = new JButton("Creer une activite");
    private JButton btnRejoindreActivite = new JButton("Rejoindre une activite");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private ArrayList<Tache> listeTaches;
    private ArrayList<Activite> listeActivites;
    private JScrollPane scrollPaneListeTaches;
    private JScrollPane scrollPaneListeActivites;
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public GestionActivitesGUI(String pseudo) throws IOException, ParseException {
        this.pseudo = pseudo;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setMainPanel();
        setCreerActivitePanel();
        setRejoindreActivitePanel();

        btnCreerActivite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(creerActivitePanel);
                mettreAJourFrame();
            }
        });
        btnRejoindreActivite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(rejoindreActivitePanel);
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
        gestionActivitesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        mainPanel.add(gestionActivitesLabel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnCreerActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRejoindreActivite);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(btnRetour);
    }

    public void setCreerActivitePanel() {
        // Déclaration des composantes implementees dans le panel
        JLabel nomActiviteLabel = new JLabel("Nom de l'activité");
        JLabel datDebutLabel = new JLabel("Date de debut (en format yyyy-MM-dd)");
        JLabel datFinLabel = new JLabel("Date de fin (en format yyyy-MM-dd)");
        JLabel tacheLabel = new JLabel("Veuillez selectionner les taches a rajouter a l'activite");
        recupererListeTache();
        JLabel interetLabel = new JLabel("Veuillez entrer un les interets a ajouter a cette activte (veuillez les separer par des virgules)");
        JTextField nomActiviteField = new JTextField();
        JTextField dateDebutField = new JTextField();
        JTextField dateFinField = new JTextField();
        JTextField interetField = new JTextField();
        JButton btnCreation = new JButton("Confirmer la creation de l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        // Setup la dimension des JTextField
        nomActiviteField.setPreferredSize(new Dimension(200, 30));
        dateDebutField.setPreferredSize(new Dimension(200, 30));
        dateFinField.setPreferredSize(new Dimension(200, 30));
        interetField.setPreferredSize(new Dimension(200, 30));

        // Ajout des composantes
        constraints.gridy = 0;
        creerActivitePanel.add(nomActiviteLabel, constraints);
        constraints.gridy = 1;
        creerActivitePanel.add(nomActiviteField, constraints);
        constraints.gridy = 2;
        creerActivitePanel.add(datDebutLabel, constraints);
        constraints.gridy = 3;
        creerActivitePanel.add(dateDebutField, constraints);
        constraints.gridy = 4;
        creerActivitePanel.add(datFinLabel, constraints);
        constraints.gridy = 5;
        creerActivitePanel.add(dateFinField, constraints);
        constraints.gridy = 6;
        creerActivitePanel.add(tacheLabel, constraints);
        constraints.gridy = 7;
        creerActivitePanel.add(scrollPaneListeTaches, constraints);
        constraints.gridy = 8;
        creerActivitePanel.add(interetLabel, constraints);
        constraints.gridy = 9;
        creerActivitePanel.add(interetField, constraints);
        constraints.gridy = 10;
        creerActivitePanel.add(btnCreation, constraints);
        constraints.gridy = 11;
        creerActivitePanel.add(btnAnnuler, constraints);

        onBtnActiviteCreeeClicked(btnCreation, nomActiviteField, dateDebutField, dateFinField, interetField);
        onBtnAnnulerClicked(btnAnnuler);
    }

    private void recupererListeTache() {
        listeTaches = controlleurUtilisateurs.recuprerListeTache(pseudo);
        JPanel tachePanel = new JPanel();
        tachePanel.setLayout(new BoxLayout(tachePanel, BoxLayout.Y_AXIS));

        for (Tache tache: listeTaches) {
            JCheckBox checkBox = new JCheckBox(tache.getNom());
            checkBox.setSelected(false);
            tachePanel.add(checkBox);
        }
        scrollPaneListeTaches = new JScrollPane(tachePanel);
        scrollPaneListeTaches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void setRejoindreActivitePanel() {
        JLabel rejoindreActiviteLabel = new JLabel("Veuillez selectionner une activite a rejoindre parmi les suivantes");
        recupererListeActivites();
        JTextField dateActiviteField = new JTextField();
        JButton btnRejoindre = new JButton("Rejoindre l'activite");
        JButton btnAnnuler = new JButton("Annuler");

        dateActiviteField.setPreferredSize(new Dimension(200, 30));
        constraints.gridy = 0;
        rejoindreActivitePanel.add(rejoindreActiviteLabel, constraints);
        constraints.gridy = 7;
        rejoindreActivitePanel.add(dateActiviteField, constraints);
        constraints.gridy = 8;
        rejoindreActivitePanel.add(btnRejoindre, constraints);
        constraints.gridy = 9;
        rejoindreActivitePanel.add(btnAnnuler, constraints);

        //onBtnRejoindreActiviteClicked(btnRejoindre);
        onBtnAnnulerClicked(btnAnnuler);
    }

    private void recupererListeActivites(){

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

    public void onBtnActiviteCreeeClicked(JButton btnCreation, JTextField nomActiviteField, JTextField dateDebutField,
                                          JTextField dateFinField, JTextField interetField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder messageErreurBuilder = new StringBuilder();
                messageErreurBuilder.append("Erreur:\n");
                String dateDebut = dateDebutField.getText();
                String dateFin = dateFinField.getText();
                ArrayList<String> listeInterets = new ArrayList<>(Arrays.asList(interetField.getText().split(",")));
                if (!GestionDates.validerDate(dateDebut) || !GestionDates.validerDate(dateFin)){
                    messageErreurBuilder.append("Reverifier le format des dates entrees.\n");
                    afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                } else {
                    if (!GestionDates.veriferSiDateRealiste(dateDebut)){
                        messageErreurBuilder.append("La date de debut de l'activitee doit commencer au moins au mois aujoud'hui." +
                                "\nNous ne pouvons malheureusement pas encore voyager dans le temps donc veuillez patienter\n" +
                                "jusqu'a ce que cette fonctionnalitee soit disponible un jour :)\n");
                        afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                    } else {
                        if (!GestionDates.verifierCoherenceDate(dateDebut, dateFin)){
                            messageErreurBuilder.append("La date de debut doit commencer avant la date de fin.\n");
                            afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                        } else {
                            if (listeTaches.isEmpty() || listeInterets.get(0).isEmpty()){
                                messageErreurBuilder.append("Vous devez rajouter au moins un interet.\n");
                                afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                            } else {
                                JPanel tachesPanel = (JPanel) scrollPaneListeTaches.getViewport().getView();
                                Component[] taches = tachesPanel.getComponents();
                                ArrayList<String> tachesChoisies = new ArrayList<>();

                                for (Component tache: taches) {
                                    if (tache instanceof JCheckBox cb && cb.isSelected()){
                                        tachesChoisies.add(cb.getText());
                                    }
                                }

                                try {
                                    if (controlleurUtilisateurs.creerActivites(pseudo, nomActiviteField.getText(), dateDebut, dateFin, tachesChoisies, listeInterets)){
                                        confirmerActiviteCreee();
                                    } else {
                                        messageErreurBuilder.append("L'activite que vous tentez de creer existe deja.");
                                        afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                                    }
                                } catch (ParseException ex) {
                                    messageErreurBuilder.setLength(0);
                                    messageErreurBuilder.append("Une erreur s'est produite. Deconnectez-vous puis reconnectez-vous " +
                                            "et assurez vous que les dates sont bien ecrites.\n");
                                    afficherMessageErreurActiviteCreee(messageErreurBuilder.toString());
                                }

                            }
                        }
                    }
                }

            }
        });
    }

    public void onBtnRejoindreActiviteClicked(JButton btnCreation, JRadioButton balladeForet, JRadioButton course, JRadioButton netflix,
                                              JRadioButton gaming, JRadioButton hockey, JTextField dateActiviteField) {
        btnCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateActiviteField.getText().length() == 0)
                    afficherMessageErreurRejoindreActivite();
                else
                    confirmerRejoindreActivite();
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

    public void confirmerActiviteCreee() {
        String message = "L'activitée a été bien créée";
        String title = "Activite creee";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurActiviteCreee(String message) {
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public void confirmerRejoindreActivite() {
        String message = "Vous avez rejoint l'activite ____";
        String title = "Joindre activite reussi";
        int messageType = JOptionPane.INFORMATION_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
        jFrame.setContentPane(mainPanel);
        mettreAJourFrame();
    }

    public void afficherMessageErreurRejoindreActivite() {
        String message = "Veuillez reessayer.";
        String title = "Erreur";
        int messageType = JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
