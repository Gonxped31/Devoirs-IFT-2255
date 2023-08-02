package domain.logic.GUI.UtilisateurGUI;

import domain.logic.Controller.ControlleurUtilisateurs;
import domain.logic.Robot.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GestionReseauGUI {
    private ControlleurUtilisateurs controlleurUtilisateurs = new ControlleurUtilisateurs();
    String pseudo;
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private JPanel suivreUtilisateurPanel = new JPanel(new GridBagLayout());
    private JPanel gererSuiveursPanel = new JPanel(new GridLayout(0, 1));
    private JPanel voirListeAbonnesPanel = new JPanel(new GridBagLayout());
    private JPanel supprimerAbonnePanel = new JPanel(new GridBagLayout());
    private JPanel gererInteretsPanel = new JPanel(new GridBagLayout());
    private JLabel gestionReseauLabel = new JLabel("Gestion reseau", SwingConstants.CENTER);
    private JButton btnSuivreUtilisateur = new JButton("Suivre un utilisateur");
    private JButton btnGererSuiveurs = new JButton("Gerer mes suiveurs");
    private JButton btnGererInterets = new JButton("Gerer mes interets");
    private JButton btnRetour = new JButton("Retour au menu utilisateur");
    private Container panelPrecedent = new Container();
    private JScrollPane scrollPaneVoirMesAbonnes;
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

    public void setSupprimerAbonnePanel() {
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

    public void setGererInteretsPanel() {
        JButton btnRetour = new JButton("Retour");

        constraints.gridy = 0;
        gererInteretsPanel.add(btnRetour, constraints);

        onBtnAnnulerClicked(btnRetour);
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
}
