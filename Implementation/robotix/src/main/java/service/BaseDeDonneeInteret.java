package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe BaseDeDonneeInteret étend la classe BaseDeDonnee
 * et permet d'effectuer des opérations de base de données spécifiques à l'objet Interet.
 *
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeInteret extends BaseDeDonnee{
    private static final String FILE_NAME= "interet.json";

    /**
     * Constructeur de la classe BaseDeDonneeInteret.
     *
     * @author Boubacar Hama Bague
     */
    public BaseDeDonneeInteret()  {
        super(FILE_NAME, new TypeReference<ArrayList<Interet>>() {});
    }

    /**
     * Méthode à implémenter pour initialiser la base de données d'intérêts.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() {
        //TODO

        List<Interet> tempList= new ArrayList<>(Arrays.asList(


               new Interet("Combat"),

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

    /**
     * Méthode pour récupérer la liste des intérêts sous forme de chaîne.
     *
     * @author Boubacar Hama Bague
     * @return String représentant la liste des intérêts
     */
    public String recupererListeInteret(){
        return (String) this.getListObjet().stream()
                .map( i-> ((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Méthode pour retourner un objet Interet à partir de son nom.
     *
     * @author Boubacar Hama Bague
     * @param nomInteret Le nom de l'intérêt à récupérer
     * @return L'objet Interet correspondant au nom donné, ou null si aucun intérêt n'est trouvé
     */
    public Interet retournerInteret(String nomInteret){
        return (Interet) this.getListObjet().stream()
                .filter(i-> ((Interet) i).getNom().toLowerCase().trim().equals(nomInteret.toLowerCase().trim()))
                .distinct()
                .findFirst()
                .orElse(null);
    }

    /**
     * Cette méthode filtre la liste des intérêts par les trois premières lettres.
     * @param troislettre Les trois premières lettres pour le filtrage.
     * @return La liste des intérêts filtrée.
     */
    public String recupererListeInteretParFiltrageSurTroisPremierSousChaine( String troislettre)
    {
        return (String) this.getListObjet().stream()
                .filter(interet-> ((Interet) interet).getNom().toUpperCase().substring(0,3).trim().equals(troislettre.toUpperCase().trim()))
                .map(i->((Interet) i).getNom())
                .distinct()
                .collect(Collectors.joining(", "));
    }

}
