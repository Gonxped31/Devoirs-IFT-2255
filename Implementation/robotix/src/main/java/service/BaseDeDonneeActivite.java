package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Tache;
import service.BaseDeDonnee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public BaseDeDonneeActivite() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Activite>>() {});
    }

    /**
     * Méthode permettant d'initialiser la base de données d'activités.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() throws ParseException {

    }

    /**
     * Méthode pour récupérer la liste des activités formatées dans une chaîne.
     *
     * @author Boubacar Hama Bague
     * @return String représentant la liste des activités
     */
    public ArrayList<Activite> recupererLalisteDesActivite() {
        return this.getListObjet();
    }

    /**
     * Recherche et retourne une instance de l'objet Activite en fonction du nom de l'activité donné.
     * La méthode parcourt la liste des objets disponibles et renvoie la première instance de l'objet Activite
     * dont le nom correspond exactement (après suppression des espaces de début et de fin) au nomActivite spécifié.
     *
     *@author Boubacar Hama Bague
     * @param nomActivite Le nom de l'activité à rechercher.
     * @return L'objet Activite correspondant au nom donné, ou null si aucune activité correspondante n'est trouvée.
     */
    public Activite retournerActivite(String nomActivite) {
        List<Object> listObjet = this.getListObjet();
        for (Object obj : listObjet) {
            if (obj instanceof Activite) {
                Activite activite = (Activite) obj;
                if (activite.getNom().equals(nomActivite.trim())) {
                    return activite;
                }
            }
        }
        return null;
    }

}