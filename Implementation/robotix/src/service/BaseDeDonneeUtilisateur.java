package service;

import com.google.gson.reflect.TypeToken;
import service.BaseDeDonneeFournisseur;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import domain.logic.Utilisateurs.Utilisateurs;
public class BaseDeDonneeUtilisateur extends BaseDeDonneeFournisseur {
     private static final String FILE_NAME = "utilisateur.json";


     public BaseDeDonneeUtilisateur() throws IOException {
        super(FILE_NAME);
      
     }


    @Override
    protected Type getType() {
        return new TypeToken<ArrayList<Utilisateurs>>(){}.getType();
    }

    @Override
    protected void init() {
        List<domain.logic.Utilisateurs.Utilisateurs> tempList= new ArrayList<>(Arrays.asList(
          //
         //new Utilisateurs (),
        ));

        tempList.stream().forEach(utilisateur-> {
         this.ajouterObjet(utilisateur);
        });
     }
        

       

}