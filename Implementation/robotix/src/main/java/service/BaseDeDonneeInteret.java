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

/**
 * Cette classe gère la base de données des intérêts des membres.
 * Elle étend la classe BaseDeDonnee et est utilisée pour charger les données des intérêts à partir d'un fichier JSON et gérer les opérations liées aux intérêts.
 * Le nom du fichier JSON est défini comme constante FILE_NAME.
 */
public class BaseDeDonneeInteret extends BaseDeDonnee{
    private static final String FILE_NAME= "interet.json";

    /**
     * Constructeur de la classe BaseDeDonneeInteret.
     * Ce constructeur utilise le constructeur de la classe parente BaseDeDonnee pour charger les données des intérêts à partir du fichier JSON spécifié.
     *
     * @throws IOException    Une exception d'entrée/sortie peut se produire lors de la lecture du fichier JSON.
     * @throws ParseException Une exception de parsing peut se produire lors de la désérialisation des données depuis le fichier JSON.
     */
    public BaseDeDonneeInteret() throws IOException, ParseException {
        super(FILE_NAME, new TypeReference<ArrayList<Interet>>() {});
    }

    /**
     * Méthode pour initialiser la base de données des intérêts.
     * Cette méthode est appelée lors de la création d'un objet BaseDeDonneeInteret pour initialiser la liste des intérêts avec des données par défaut.
     * Elle ajoute une liste d'objets Interet pré-définis à la liste d'objets gérée par la classe parente BaseDeDonnee.
     */
    @Override
    protected void init() {
        //TODO

        List<Interet> tempList= new ArrayList<>(Arrays.asList(

                new Interet("Combat"),
                new Interet("Foot"),
                new Interet("Soccer"),
                new Interet("Danse"),
                new Interet("Break"),
                new Interet("Programmation"),
                new Interet("Apprentissage Machine"),
                new Interet("Cinematographie"),
                new Interet("Bagarre"),
                new Interet("Course")


        ));
        tempList.stream().forEach(interet -> {
            this.ajouterObjet(interet);
        });
    }

    /**
     * Récupère la liste des intérêts disponibles dans la base de données.
     *
     * @return Une ArrayList contenant tous les objets Interet présents dans la base de données.
     */
    public HashSet<Interet> recupererListeInteret() {
        HashSet<Interet> interets = new HashSet<>();

        for (Object i : getListObjet()) {
            if (i instanceof Interet) {
                interets.add((Interet) i);
            }
        }

        return interets;
    }

    /**
     * Recherche et retourne l'objet Interet correspondant au nom d'intérêt spécifié.
     * La méthode parcourt la liste des objets Interet récupérés à partir du fichier JSON et renvoie le premier objet Interet dont le nom correspond exactement au nomInteret spécifié.
     *
     * @param nomInteret Le nom de l'intérêt à rechercher.
     * @return L'objet Interet correspondant au nom d'intérêt spécifié, ou null si aucun intérêt correspondant n'est trouvé.
     */
    public Interet retournerInteret(String nomInteret){
        return (Interet) this.getListObjet().stream()
                .filter(i-> ((Interet) i).getNom().trim().equals(nomInteret.trim()))
                .distinct()
                .findFirst()
                .orElse(null);
    }

    /**
     * Modifie un intérêt dans la base de données.
     * Cette méthode permet de modifier un intérêt existant en remplaçant son nom par un nouvel intérêt spécifié.
     *
     * @param interetCourant L'intérêt actuel à modifier.
     * @param nouvelInteret  Le nouvel intérêt à définir.
     */
    public void modifierInteret(String interetCourant, String nouvelInteret) {

    }
}
