package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseDeDonnee<T>{

private List<T> listObjet;
private File database;

private Gson gson= new Gson();
public BaseDeDonnee(String fileName) throws IOException {
    this.database = new File(fileName);
    this.listObjet = lireFichier();
}

protected abstract Type getType();
public List<T> lireFichier() throws IOException {
   
         if(!database.exists())
         {
            database.createNewFile();

         }
         init();

        try {
            String contenu = new String(Files.readAllBytes(Paths.get(database.getPath())));
            List<T> objets = gson.fromJson(contenu, getType());
            return objets;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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