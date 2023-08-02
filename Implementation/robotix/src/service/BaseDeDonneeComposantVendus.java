package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * La classe BaseDeDonneeComposantVendus étend la classe BaseDeDonnee
 * et permet d'effectuer des opérations de base de données spécifiques à l'objet Composant.
 *
 * @author Boubacar Hama Bague
 */

public class BaseDeDonneeComposantVendus extends BaseDeDonnee{


    private static final String FILE_NAME= "composantVendus.json";

    /**
     * Constructeur de la classe BaseDeDonneeComposantVendus.
     *
     * @author Boubacar Hama Bague
     */
    public BaseDeDonneeComposantVendus() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Composant>>() {});
    }


    /**
     * Méthode à implémenter pour initialiser la base de données des composants vendus.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() {

    }
    /**
     * Méthode pour retourner un objet Composant actuellement vendu à partir de son nom et de son numéro.
     *
     * @author Boubacar Hama Bague
     * @param nom Le nom du composant
     * @param numero Le numéro du composant
     * @return L'objet Composant correspondant au nom et numéro donnés, ou null si aucun composant n'est trouvé
     */
    public Composant getCurrentSoldComposant(String nom, int numero){
        return (Composant) this.getListObjet().stream()
                .filter(c->((Composant)c).getNumero()==numero && ((Composant) c).getNom().trim().equals(nom.trim()) )
                .findFirst()
                .orElse(null);
    }

}