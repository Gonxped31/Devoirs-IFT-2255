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
