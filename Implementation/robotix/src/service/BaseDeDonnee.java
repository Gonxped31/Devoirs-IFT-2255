package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Utilisateur;

public abstract class BaseDeDonnee<T>{

private ArrayList<T> listObjet;
private File database;


public BaseDeDonnee(String fileName, TypeReference<ArrayList<T>> type) throws IOException {
    this.database = new File(fileName);
    this.listObjet = lireFichier(type);
}


    public ArrayList<T> lireFichier(TypeReference<ArrayList<T>> type) {
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

    protected void sauvegarder() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(this.database.toURI()), listObjet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterObjet(T objet) {
        if (this.listObjet==null){
        this.listObjet=new ArrayList<>();
        }
        this.listObjet.add(objet);
        this.sauvegarder();
    }

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
        }
        sauvegarder();
    }

 
protected abstract void init();

    public ArrayList<T> getListObjet()
    {
        return this.listObjet==null ? new ArrayList<>() : listObjet;

    }
 
}