package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseDeDonnee<T>{

private ArrayList<T> listObjet;
private File database;

private Gson gson= new Gson();
public BaseDeDonnee(String fileName) throws IOException {
    this.database = new File(fileName);
    this.listObjet = lireFichier();
}




protected abstract Type getType();
    public ArrayList<T> lireFichier() {
        ArrayList<T> objets = new ArrayList<>();

        if(!database.exists()) {
            try {
                database.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();

        // Vérifier si le fichier est vide
        if(database.length() != 0) {
            try {
                objets = objectMapper.readValue(database, new TypeReference<ArrayList<T>>() {});
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
public void ajouterObjet(T objet)

{   if (this.listObjet==null){
    this.listObjet=new ArrayList<>();
}



    this.listObjet.add(objet);
    this.sauvegarder();
}
public void supprimerObjet(T objet){
    listObjet.remove(objet);
    sauvegarder();
}
 
protected abstract void init();

    public List<T> getListObjet()
    {
        return this.listObjet==null ? new ArrayList<>() : listObjet;

    }
 
}