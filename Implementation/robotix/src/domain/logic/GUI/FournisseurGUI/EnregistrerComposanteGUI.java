package domain.logic.GUI.FournisseurGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnregistrerComposanteGUI {
    private JFrame jFrame = new JFrame();
    private JPanel enregistrerComposantePanel = new JPanel(new GridBagLayout());
    private JLabel enregistrerComposanteLabel = new JLabel("Enregistrer une composante", SwingConstants.CENTER);
    private JLabel nomComposanteLabel = new JLabel("Nom de la composante");
    private JLabel prixLabel = new JLabel("Prix");
    private JLabel descriptionLabel = new JLabel("Description");
    private JLabel typeComposanteLabel = new JLabel("Type de la composante");
    private JTextField nomComposanteField = new JTextField();
    private JTextField prixField = new JTextField();
    private JTextField descriptionField = new JTextField();
    private JTextField typeComposanteField = new JTextField();
    private JButton btnEnregistrer = new JButton("Enregistrer");
    private JButton btnRetour = new JButton("Retour");
    private Container panelPrecedent = new Container();
    private GridBagConstraints constraints = new GridBagConstraints(); // Classe qui definit la maniere dont les composants seront places dans un panel

    public EnregistrerComposanteGUI() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        enregistrerComposanteLabel.setFont(new Font("Arial", Font.BOLD, 18));
        enregistrerComposantePanel.setLayout();
        enregistrerComposantePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composantes
        enregistrerComposantePanel.add(enregistrerComposanteLabel);
        enregistrerComposantePanel.add(Box.createHorizontalStrut(10));
        enregistrerComposantePanel.add(nomComposanteLabel);
        enregistrerComposantePanel.add(nomComposanteField);
        enregistrerComposantePanel.add(prixLabel);
        enregistrerComposantePanel.add(prixField);
        enregistrerComposantePanel.add(descriptionLabel);
        enregistrerComposantePanel.add(descriptionField);
        enregistrerComposantePanel.add(typeComposanteLabel);
        enregistrerComposantePanel.add(typeComposanteField);
        enregistrerComposantePanel.add(btnEnregistrer);
        enregistrerComposantePanel.add(btnRetour);

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(panelPrecedent); // Mettre a jour le contentPane avec le panel precedent
                mettreAJourFrame();
            }
        });
    }

    public void afficherMainPanel(JFrame jFrame) {
        panelPrecedent = jFrame.getContentPane(); // Recuperer le contentPane du Menu Fournisseur
        this.jFrame = jFrame;
        this.jFrame.setContentPane(enregistrerComposantePanel);
        mettreAJourFrame();
    }

    public void mettreAJourFrame() {
        this.jFrame.revalidate();
        this.jFrame.repaint();
    }
}
