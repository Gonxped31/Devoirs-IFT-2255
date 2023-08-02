package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BaseDeDonneeInteret extends BaseDeDonnee{
    private static final String FILE_NAME= "interet.json";
    public BaseDeDonneeInteret() throws IOException, ParseException {
        super(FILE_NAME, new TypeReference<ArrayList<Interet>>() {});
    }

    @Override
    protected void init() {
        //TODO

        List<Interet> tempList= new ArrayList<>(Arrays.asList(

                new Interet("Combat"),
                new Interet("Foot"),
                new Interet("Soccer"),
                new Interet("Danse"),
                new Interet("Break")


        ));
        tempList.stream().forEach(interet -> {
            this.ajouterObjet(interet);
        });
    }

    public HashSet<Interet> recupererListeInteret() {
        HashSet<Interet> interets = new HashSet<>();

        for (Object i : getListObjet()) {
            if (i instanceof Interet) {
                interets.add((Interet) i);
            }
        }

        return interets;
    }
    public Interet retournerInteret(String nomInteret){
        return (Interet) this.getListObjet().stream()
                .filter(i-> ((Interet) i).getNom().trim().equals(nomInteret.trim()))
                .distinct()
                .findFirst()
                .orElse(null);
    }

    public void modifierInteret(String interetCourant, String nouvelInteret) {

    }
}
