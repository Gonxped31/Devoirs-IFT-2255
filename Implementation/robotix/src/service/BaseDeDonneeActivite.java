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

public class BaseDeDonneeActivite extends BaseDeDonnee {
private static final String FILE_NAME= "activite.json";

public BaseDeDonneeActivite() throws IOException, ParseException {
    super(FILE_NAME,new TypeReference<ArrayList<Activite>>() {});
}



    @Override
    protected void init() throws ParseException {

    }

    public String recupererLalisteDesActivite()
    {
        return (String) this.getListObjet().stream()
                .map(activite->((Activite) activite).getInfoActiviteFormater())
                .collect(Collectors.joining("\n"));
    }

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