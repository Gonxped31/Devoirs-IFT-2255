package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import service.BaseDeDonnee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * La classe BaseDeDonneeActivite étend la classe BaseDeDonnee
 *et permet d'effectuer des opérations de base de données spécifiques à l'objet Activite.
 *
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeActivite extends BaseDeDonnee {
    private static final String FILE_NAME= "activite.json";

    /**
     * Constructeur de la classe BaseDeDonneeActivite.
     *
     * @author Boubacar Hama Bague
     */
    public BaseDeDonneeActivite() {
        super(FILE_NAME,new TypeReference<ArrayList<Activite>>() {});
    }


    /**
     * Méthode permettant d'initialiser la base de données d'activités.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() {
        //Todo
    }


    /**
     * Méthode pour récupérer la liste des activités formatées dans une chaîne.
     *
     * @author Boubacar Hama Bague
     * @return String représentant la liste des activités
     */
    public String recupererLalisteDesActivite()
    {
        return (String) this.getListObjet().stream()
                .map(activite->((Activite) activite).getInfoActiviteFormater())
                .collect(Collectors.joining("\n"));
    }

}
