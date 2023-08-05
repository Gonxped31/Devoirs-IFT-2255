package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.Activite;

/**
 * Classe abstraite BaseDeDonnee qui fournit les méthodes de base pour une opération de base de données comme la lecture de fichiers,
 * la sauvegarde de données, l'ajout et la suppression d'objets.
 *
 * @author Boubacar Hama Bague
 * @param <T> Le type générique T qui représente le type d'objet que cette base de données va stocker.
 */
public abstract class BaseDeDonnee<T>{

private ArrayList<T> listObjet;
private File database;

    /**
     * Constructeur de la classe BaseDeDonnee
     *
     * @author Boubacar Hama Bague
     * @param fileName Le nom du fichier de la base de données
     * @param type Le type des objets stockés dans la base de données
     */
    public BaseDeDonnee(String fileName, TypeReference<ArrayList<T>> type) throws IOException, ParseException {
    this.database = new File(fileName);
    this.listObjet = lireFichier(type);
}

    /**
     * Cette méthode est utilisée pour lire le fichier de la base de données.
     *
     * @author Boubacar Hama Bague
     * @param type Le type des objets stockés dans la base de données
     * @return ArrayList<T> Une liste d'objets T lus à partir du fichier de la base de données
     */
    public ArrayList<T> lireFichier(TypeReference<ArrayList<T>> type) throws ParseException {
        ArrayList<T> objets = new ArrayList<>();

        if(!database.exists()) {
            try {
                database.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            init();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        // Vérifier si le fichier est vide
        if(database.length() != 0) {
            try {
                objets = objectMapper.readValue(database, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objets;
    }

    /**
     * Cette méthode est utilisée pour sauvegarder les modifications apportées à la liste d'objets dans le fichier de la base de données.
     *
     * @author Boubacar Hama Bague
     */
    protected void sauvegarder() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(this.database.toURI()), listObjet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode est utilisée pour ajouter un nouvel objet à la liste d'objets et sauvegarder la liste mise à jour dans le fichier de la base de données.
     *
     * @author Boubacar Hama Bague
     * @param objet L'objet à ajouter à la liste d'objets
     */
    public void ajouterObjet(T objet) {
        if (this.listObjet==null){
        this.listObjet=new ArrayList<>();
        }
        this.listObjet.add(objet);
        this.sauvegarder();
    }

    /**
     * Cette méthode est utilisée pour supprimer un objet de la liste d'objets
     * et sauvegarder la liste mise à jour dans le fichier de la base de données.
     *
     * @author Boubacar Hama Bague
     * @param objet L'objet à supprimer de la liste d'objets
     */
    public void supprimerObjet(T objet) {
        Iterator<Object> iterator = (Iterator<Object>) listObjet.iterator();
        while(iterator.hasNext()) {
            Object o = iterator.next();

            if( objet instanceof Utilisateur ) {
                if(((Utilisateur) objet).getPseudo().equals(((Utilisateur) o).getPseudo())){
                    iterator.remove();
                }
            }
            else if ( objet instanceof Fournisseur) {
                if(((Fournisseur) o).getNom().equals(((Fournisseur) objet).getNom())){
                    iterator.remove();
                }
            }
            else if (objet instanceof Interet){
                if(((Interet) o).getNom().equals(((Interet) objet).getNom())){
                    iterator.remove();
                }
            }
            else if (objet instanceof Activite) {
                if(((Activite) o).getNom().equals(((Activite) objet).getNom())){
                    iterator.remove();
                }
            }
        }
        sauvegarder();
    }

    /**
     * Cette méthode est une méthode abstraite qui doit être implémentée
     * par les classes qui héritent de cette classe BaseDeDonnee.
     * Elle permet d'initialiser les bases de donnee
     *
     * @author Boubacar Hama Bague
     */
    protected abstract void init() throws ParseException;

    /**
     * Cette méthode renvoie la liste d'objets stockés dans la base de données.
     *
     * @author Boubacar Hama Bague
     * @return ArrayList<T> La liste d'objets stockés dans la base de données.
     */
    public ArrayList<T> getListObjet()
    {
        return this.listObjet==null ? new ArrayList<>() : listObjet;

    }
 
}