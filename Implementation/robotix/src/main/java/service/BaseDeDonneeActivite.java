package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import domain.logic.Membre.Fournisseur;
import domain.logic.Membre.Interet;
import domain.logic.Membre.Utilisateur;
import domain.logic.Robot.*;
import service.BaseDeDonnee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * La classe BaseDeDonneeActivite étend la classe BaseDeDonnee
 *et permet d'effectuer des opérations de base de données spécifiques à l'objet Activite.
 *
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeActivite extends BaseDeDonnee {
private static final String FILE_NAME= "activite.json";

    /**
     * Constructeur de la classe BaseDeDonneeActivite.
     *
     * @author Boubacar Hama Bague
     */
    public BaseDeDonneeActivite() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Activite>>() {});
    }

    /**
     * Méthode permettant d'initialiser la base de données d'activités.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() throws ParseException {
        ArrayList<Activite> acts = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //Activite 1
        Composant c1 = new Composant("cpu", "20", "Unité Centrale de Traitement", TypesComposants.CPU.name());
        Composant c2 = new Composant("helice", "10", "Helice pour voler", TypesComposants.HELICE.name());

        LinkedList<Composant> listeC1 = new LinkedList<>();
        listeC1.add(c1);
        listeC1.add(c2);

        ArrayList<String> composantesNom = new ArrayList<>();
        for (Composant c : listeC1){
            composantesNom.add(c.getNom());
        }
        Action a = new Action("Voler", composantesNom, "30");
        ArrayList<Action> tache = new ArrayList<>();
        tache.add(a);
        Tache t = new Tache("Voler a 5 metres de hauteur", tache);
        ArrayList<Tache> taches = new ArrayList<>();
        taches.add(t);
        HashSet<Interet> interet = new HashSet<>();
        interet.add(new Interet("Course"));
        Activite act = null;
        try {
            act = new Activite("KellyB", "Course de vol entre robots", dateFormat.parse("2023-08-04"), dateFormat.parse("2023-08-10"), taches, interet);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        acts.add(act);

        //Activite 2

        Action a1 = new Action("Survoler", composantesNom, "30");
        ArrayList<Action> tache1 = new ArrayList<>();
        tache.add(a1);
        Tache t1 = new Tache("Survoler un objet", tache);
        ArrayList<Tache> taches1 = new ArrayList<>();
        taches1.add(t1);
        HashSet<Interet> interet1 = new HashSet<>();
        interet.add(new Interet("Aviation"));
        Activite act1 = null;
        try {
            act1 = new Activite("SD", "Survoler un autre robot", dateFormat.parse("2023-08-01"), dateFormat.parse("2023-08-3"), taches1, interet1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        acts.add(act1);

        //Activite 3

        Composant c3 = new Composant("cpu", "20", "Unité Centrale de Traitement", TypesComposants.CPU.name());
        Composant c4 = new Composant("roue", "30", "Roues pour se deplacer", TypesComposants.ROUE.name());
        Composant c5 = new Composant("bras", "10", "Bras pour prendre", TypesComposants.BRAS.name());
        LinkedList<Composant> listeC2 = new LinkedList<>();
        listeC2.add(c3);
        listeC2.add(c4);
        listeC2.add(c5);

        ArrayList<String> composantesNom1 = new ArrayList<>();
        for (Composant c : listeC2){
            composantesNom1.add(c.getNom());
        }
        ArrayList<Tache> taches3 = new ArrayList<>();
        taches3.add(t);
        HashSet<Interet> interet3 = new HashSet<>();
        interet3.add(new Interet("Bagarre"));
        Activite act3 = null;
        try {
            act3 = new Activite("MG", "Match de boxe contre un autre robot", dateFormat.parse("2023-08-05"), dateFormat.parse("2023-08-10"), taches3, interet3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        acts.add(act3);

        //Activite 4
        Composant c6 = new Composant("cpu", "20", "Unité Centrale de Traitement", TypesComposants.CPU.name());
        Composant c7 = new Composant("ecran", "10", "Ecran pour montrer des images ou videos", TypesComposants.ECRAN.name());
        LinkedList<Composant> listeC3 = new LinkedList<>();
        listeC1.add(c6);
        listeC1.add(c7);

        ArrayList<String> composantesNom3 = new ArrayList<>();
        for (Composant c : listeC3){
            composantesNom3.add(c.getNom());
        }

        Action a4 = new Action("Diffuser images", composantesNom, "30");
        ArrayList<Action> tache4 = new ArrayList<>();
        tache4.add(a4);
        Tache t4 = new Tache("Montrer rafales d'images", tache);
        ArrayList<Tache> taches4 = new ArrayList<>();
        taches.add(t4);
        HashSet<Interet> interet4 = new HashSet<>();
        interet.add(new Interet("Cinematographie"));
        Activite act4 = null;
        try {
            act4 = new Activite("SD", "Montrer images des autres robots", dateFormat.parse("2023-08-05"), dateFormat.parse("2023-08-10"), taches4, interet4);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        acts.add(act4);

        //Activite 5
        Action a5 = new Action("Diffuser videos", composantesNom, "30");
        ArrayList<Action> tache5 = new ArrayList<>();
        tache5.add(a5);
        Tache t5 = new Tache("Montrer une video de 5 minutes", tache);
        ArrayList<Tache> taches5 = new ArrayList<>();
        taches5.add(t5);
        HashSet<Interet> interet5 = new HashSet<>();
        interet.add(new Interet("Cinematographie"));
        Activite act5 = null;
        try {
            act = new Activite("JG", "Montrer des videos des autres competitions de robots", dateFormat.parse("2023-08-05"), dateFormat.parse("2023-08-10"), taches5, interet5);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        acts.add(act);


        ArrayList<String> p = new ArrayList<>();
        p.add("KD");
        p.add("SB");
        p.add("MG");
        p.add("KellyB");
        p.add("SD");
        p.add("FT");
        p.add("Brice");
        p.add("MP");
        p.add("JG");
        p.add("SDi");

        for (Activite activite: acts){
            for (String pse: p){
                activite.getListeUtilisateurInsccrit().add(pse);
            }

        }

        acts.stream().forEach(activite -> {
            this.ajouterObjet(activite);
        });

    }

    /**
     * Méthode pour récupérer la liste des activités formatées dans une chaîne.
     *
     * @author Boubacar Hama Bague
     * @return String représentant la liste des activités
     */
    public ArrayList<Activite> recupererLalisteDesActivite() {
        return this.getListObjet();
    }

    /**
     * Recherche et retourne une instance de l'objet Activite en fonction du nom de l'activité donné.
     * La méthode parcourt la liste des objets disponibles et renvoie la première instance de l'objet Activite
     * dont le nom correspond exactement (après suppression des espaces de début et de fin) au nomActivite spécifié.
     *
     *@author Boubacar Hama Bague
     * @param nomActivite Le nom de l'activité à rechercher.
     * @return L'objet Activite correspondant au nom donné, ou null si aucune activité correspondante n'est trouvée.
     */
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