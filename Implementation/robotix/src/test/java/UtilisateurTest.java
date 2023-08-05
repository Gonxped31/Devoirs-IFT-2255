import domain.logic.Membre.Interet;
import domain.logic.Membre.Notification;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

public class UtilisateurTest {

    HashSet<Interet> interets = new HashSet<>();
    ArrayList<Notification> notifications = new ArrayList<>();
    Utilisateur utilisateur = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);
    private HashSet<Interet> listeInteret = new HashSet<>();
    private ArrayList<Notification> listeNotifications = new ArrayList<>();
    private Utilisateur utilisateur2 = new Utilisateur("Louis", "William", "2023 avenue du Genie-Logiciel", "will03", "wlouis123",
            "willlouis@gmail.com", "4382215728", "Les Genies du Web", listeInteret, listeNotifications);


    /**
     * Vérifie que la méthode {@code suivreUtilisateur()} ajoute correctement
     * l'utilisateur spécifié à la liste des utilisateurs suivis par l'utilisateur.
     *
     * @author Gbian Bio Samir
     */
    @Test
    void testSuivreUtilisateur() {
        Utilisateur userToFollow = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);
        utilisateur.suivreUtilisateur(userToFollow);
        assertTrue(utilisateur.getListeUtilisateursSuivi().contains(userToFollow.getPseudo()));
    }

    /**
     * Vérifie que la méthode {@code supprimerUtilisateurDeMaListe()} supprime correctement
     * un utilisateur de la liste des utilisateurs suivis par l'utilisateur, en utilisant son pseudo.
     *
     * @author Gbian Bio Samir
     */
    @Test
    void testAreterDeSuivreUtilisateur() {
        utilisateur.getListeUtilisateursSuivi().add("Testeur");
        utilisateur.supprimerUtilisateurDeMaListe("Testeur");
        assertFalse(utilisateur.getListeUtilisateursSuivi().contains("Testeur"));
    }

    /**
     * Vérifie que la méthode {@code etreSuivi()} ajoute correctement
     * l'utilisateur spécifié à la liste des utilisateurs qui suivent cet utilisateur.
     *
     * @author Gbian Bio Samir
     */
    @Test
    void testEtreSuivi() {
        Utilisateur follower = new Utilisateur("nom", "prenom", "adresse", "pseudo", "mdp", "test@example.com", "numeroTelephone", "nomCompagnie", interets, notifications);
        utilisateur.etreSuivi(follower);
        assertTrue(utilisateur.getListSuiveur().contains(follower));
    }

    /**
     * Vérifie que la méthode {@code enregistrerRobot()} retourne {@code false}
     * lorsque le robot est déjà enregistré par l'utilisateur et {@code true} lorsqu'il est enregistré pour la première fois.
     *
     * @author Brice ZAMBOU NGUEMO
     */
    @Test
    void testEnregistrerRobot() {
        Robot robot = new Robot();
        assertFalse(utilisateur.enregistrerRobot(robot));
        assertTrue(utilisateur.enregistrerRobot(robot));
    }

    /**
     * Vérifie que la méthode {@code modifierProfil()} met à jour correctement les informations du profil de l'utilisateur.
     *
     * @author Brice ZAMBOU NGUEMO
     */
    @Test
    public void testModifierProfil() {
        Utilisateur utilisateur = new Utilisateur(
                "Dupont",
                "Jean",
                "12 rue de la République",
                "jdupont",
                "1234",
                "jdupont@mail.com",
                "0102030405",
                "Compagnie Dupont",
                new HashSet<Interet>(),
                new ArrayList<Notification>()
        );

        // Nouvelles informations
        String nouveauNom = "Doe";
        String nouveauPrenom = "John";
        String nouvelleAdresse = "34 rue des Fleurs";
        String nouveauEmail = "jdoe@mail.com";
        String nouveauNumeroTelephone = "0607080910";

        utilisateur.modifierProfile("nom", nouveauNom);
        utilisateur.modifierProfile("prenom", nouveauPrenom);
        utilisateur.modifierProfile("adresse", nouvelleAdresse);
        utilisateur.modifierProfile("email", nouveauEmail);
        utilisateur.modifierProfile("numerotelephone", nouveauNumeroTelephone);

        assertEquals(nouveauNom, utilisateur.getNom(), "Le nom de l'utilisateur doit être mis à jour.");
        assertEquals(nouveauPrenom, utilisateur.getPrenom(), "Le prénom de l'utilisateur doit être mis à jour.");
        assertEquals(nouvelleAdresse, utilisateur.getAdresse(), "L'adresse de l'utilisateur doit être mise à jour.");
        assertEquals(nouveauEmail, utilisateur.getEmail(), "L'email de l'utilisateur doit être mis à jour.");
        assertEquals(nouveauNumeroTelephone, utilisateur.getTelephone(), "Le numéro de téléphone de l'utilisateur doit être mis à jour.");
    }

    /**
     * Vérifie que la méthode {@code ajouterComposante()} ajoute correctement
     * une composante à la liste des composantes achetées par l'utilisateur.
     *
     * @author Brice ZAMBOU NGUEMO
     */
    @Test
    void testAjouterComposante() {
        Composant composant = new Composant();
        utilisateur.ajouterComposante(composant);
        assertTrue(utilisateur.getComposantesAchetes().contains(composant));
    }

    /**
     * Vérifie que la méthode {@code rejoindreActivite()} retourne {@code true}
     * si l'utilisateur rejoint une activité déjà présente dans sa liste d'activités rejointes.
     *
     * @author Dorensky Herard
     */
    @Test
    void testSiActiviteEstDejaDansLaListeActiviteRejoint() {
        Activite cascade = new Activite();
        cascade.setNom("Cascade aerienne");
        utilisateur2.rejoindreActivite(cascade);

        Activite cascade2 = new Activite();
        cascade2.setNom("Cascade aerienne");
        assertTrue(utilisateur2.rejoindreActivite(cascade2));
    }

    /**
     * Vérifie que la méthode {@code supprimerUtilisateurDeMaListe()} supprime correctement
     * un utilisateur de la liste des utilisateurs suivis par l'utilisateur.
     *
     * @author Dorensky Herard
     */
    @Test
    void testSiUtilisateurEstSupprimeDansLaListeUtilisateursSuivi() {
        Utilisateur utilisateurSuivi = new Utilisateur("Robert", "Jacques", "2022 rue de l'Informatique", "rob2022", "jrob456",
                "jacquesrobert@gmail.com", "5145557777", "Big Rob", listeInteret, listeNotifications);
        utilisateur2.suivreUtilisateur(utilisateurSuivi);

        assertTrue(utilisateur2.supprimerUtilisateurDeMaListe("rob2022"));
    }

    /**
     * Vérifie que la méthode {@code retrouverRobotNom()} retourne {@code null}
     * si le nom du robot spécifié n'est pas présent dans la liste des robots de l'utilisateur.
     *
     * @author Dorensky Herard
     */
    @Test
    void testSiLeNomDuRobotEstRetrouve() {
        Robot robot = new Robot();
        robot.setNom("Alexandre");
        utilisateur2.ajouterRobot(robot);

        assertNull(utilisateur2.retrouverRobotNom("Paul"));
    }
}