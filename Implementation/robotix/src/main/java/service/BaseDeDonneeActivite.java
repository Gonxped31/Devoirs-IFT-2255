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

public class BaseDeDonneeActivite extends BaseDeDonnee {
    private static final String FILE_NAME= "activite.json";

    public BaseDeDonneeActivite() throws IOException {
        super(FILE_NAME,new TypeReference<ArrayList<Activite>>() {});
    }



    @Override
    protected void init() {
        //Todo
    }

    public String recupererLalisteDesActivite()
    {
        return (String) this.getListObjet().stream()
                .map(activite->((Activite) activite).getInfoActiviteFormater())
                .collect(Collectors.joining("\n"));
    }

}
