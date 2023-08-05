import domain.logic.Membre.Fournisseur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe pour les tests unitaires des méthodes de la classe fournisseur.
 */
public class FournisseurTest {
    /**
     * Vérifie que la méthode {@code getNomCompagnie()} retourne correctement
     * le nom de la compagnie du fournisseur.
     *
     * @author Kamen Damov
     */
    @Test
    void testNomCompagnie() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        assertEquals("compagnie", fournisseur.getNomCompagnie());
    }

    /**
     * Vérifie que la méthode {@code ajouterRobot()} ajoute correctement un robot
     * à l'inventaire du fournisseur.
     *
     * @author Kamen Damov
     */
    @Test
    void testAjouterRobot() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterRobot(new ArrayList<>(), "type");
        assertEquals(1, fournisseur.getInventaireDeRobot().size());
    }

    /**
     * Vérifie que la méthode {@code retirerRobot()} supprime correctement un robot
     * de l'inventaire du fournisseur en utilisant son identifiant unique (UUID).
     *
     * @author Kamen Damov
     */
    @Test
    void testRetirerRobot() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        UUID idRobot = fournisseur.ajouterRobot(new ArrayList<>(), "type");
        assertTrue(fournisseur.retirerRobot(idRobot.toString()));
    }

    /**
     * Vérifie que la méthode {@code ajouterComposante()} ajoute correctement une
     * composante à l'inventaire de composants du fournisseur.
     *
     * @author Boubacar Hama Bague
     */
    @Test
    void testAjouterComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertEquals(1, fournisseur.getInventaireComposant().size());
    }

    /**
     * Vérifie que la méthode {@code retirerComposante()} supprime correctement
     * une composante de l'inventaire de composants du fournisseur en utilisant son nom.
     *
     * @author Boubacar Hama Bague
     */
    @Test
    void testRetirerComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertTrue(fournisseur. retirerComopsante("nom"));
    }

    /**
     * Vérifie que la méthode {@code modifierPrixComposante()} met à jour correctement
     * le prix d'une composante dans l'inventaire de composants du fournisseur en utilisant son nom.
     *
     * @author Boubacar Hama Bague
     */
    @Test
    void testModifierPrixComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "mdp", "adresse", "email", "0123456789", "typeDeRobot", "typeComposant", "capacite", "compagnie");
        fournisseur.ajouterComposante("nom", "prix", "description", "type");
        assertTrue(fournisseur.modifierPrixComposante("nom", "nouveauPrix"));
    }

}