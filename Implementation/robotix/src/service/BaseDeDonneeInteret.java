package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Robot.Activite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseDeDonneeInteret extends BaseDeDonnee{
    private static final String FILE_NAME= "interet.json";
    public BaseDeDonneeInteret() throws IOException {
        super(FILE_NAME, new TypeReference<ArrayList<Activite>>() {});
    }

    @Override
    protected void init() {
      //TODO

        List<Interet> tempList= new ArrayList<>(Arrays.asList(

                //Instancier dans les interet ici

        ));
        tempList.stream().forEach(interet -> {
            this.ajouterObjet(interet);
        });
    }

    public String recupererListeInteret(){
        return (String) this.getListObjet().stream()
                .map( i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

}
