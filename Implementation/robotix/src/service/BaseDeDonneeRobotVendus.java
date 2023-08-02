package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * La classe BaseDeDonneeRobotVendus étend la classe BaseDeDonnee et
 * permet d'effectuer des opérations de base de données spécifiques à l'objet Robot.
 *
 * @author Boubacar Hama Bague
 */
public class BaseDeDonneeRobotVendus extends BaseDeDonnee{


    private static final String FILE_NAME= "robotsVendus.json";

    /**
     * Constructeur de la classe BaseDeDonneeRobotVendus.
     *
     * @author Boubacar Hama Bague
     * @throws IOException si une erreur se produit lors de l'accès au fichier de la base de données
     */
    public BaseDeDonneeRobotVendus() throws IOException, ParseException {
        super(FILE_NAME,new TypeReference<ArrayList<Robot>>() {});
    }

    /**
     * Méthode à implémenter pour initialiser la base de données des robots vendus.
     * Cette méthode est définie dans la classe de base abstraite BaseDeDonnee.
     *
     * @author Boubacar Hama Bague
     */
    @Override
    protected void init() {
        LinkedList<Composant> listComposant = new LinkedList<>();
        listComposant.add(new Composant("cpu", "150000", "Description cpu", "CPU"));
        listComposant.add(new Composant("bras", "800000", "Description bras", "BRAS"));
        listComposant.add(new Composant("roue", "570000", "Description roue", "ROUE"));
        listComposant.add(new Composant("helice", "457000", "Description helice", "HELICE"));
        listComposant.add(new Composant("micro", "365000000", "Description micro", "MICRO"));
        listComposant.add(new Composant("hautparleur", "7540000", "Description hautparleur", "HAUTPARLEUR"));
        listComposant.add(new Composant("ecran", "841000000", "Description ecran", "ECRAN"));

        LinkedList<LinkedList<Robot>> listeRobots = new LinkedList<>();
        LinkedList<Robot> listeRobotRoy = new LinkedList<>();
        LinkedList<Robot> listeRobotKare = new LinkedList<>();
        LinkedList<Robot> listeRobotBouchard = new LinkedList<>();
        LinkedList<Robot> listeRobotAdams = new LinkedList<>();
        LinkedList<Robot> listeRobotWilson = new LinkedList<>();
        LinkedList<Robot> listeRobotThompson = new LinkedList<>();
        listeRobotRoy.add(new Robot("", 0, 0, 0, 100, 120, 32, listComposant, "Coureur", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotKare.add(new Robot("", 0, 0, 0, 32, 100, 64, listComposant, "Sauteur", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotBouchard.add(new Robot("", 0, 0, 0, 45, 75, 32, listComposant, "Musicienne", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotAdams.add(new Robot("", 0, 0, 0, 76, 85, 32, listComposant, "SurPoids", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotWilson.add(new Robot("", 0, 0, 0, 24, 105, 64, listComposant, "Calme", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));
        listeRobotThompson.add(new Robot("", 0, 0, 0, 98, 46, 84, listComposant, "Abeille", new LinkedList<>(), new LinkedList<>(), new LinkedList<>()));

        listeRobots.add(listeRobotRoy);
        listeRobots.add(listeRobotKare);
        listeRobots.add(listeRobotBouchard);
        listeRobots.add(listeRobotAdams);
        listeRobots.add(listeRobotWilson);
        listeRobots.add(listeRobotThompson);

        listeRobots.forEach(this::ajouterObjet);

    }

    /**
     * Méthode pour retourner un objet Robot actuellement vendu à partir de son numéro de série.
     *
     * @author Boubacar Hama Bague
     * @param numeroSeri Le numéro de série du robot
     * @return L'objet Robot correspondant au numéro de série donné, ou null si aucun robot n'est trouvé
     */
    public Robot getCurrentSoldRobot(String numeroSeri){
        return (Robot) this.getListObjet().stream()
                .filter(r->((Robot)r).getNumeroSerie().toString().trim().equals(numeroSeri.trim()))
                .findFirst()
                .orElse(null);
    }
}
