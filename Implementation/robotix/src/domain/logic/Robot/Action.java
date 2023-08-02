package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Représente une action effectuée par un robot.
 * Cette classe est sérialisable en JSON à l'aide de la bibliothèque Jackson.
 */
public class Action implements java.io.Serializable {
    private String nomAction;
    private ArrayList<String> composantes;
    private String duree;

    /**
     * Constructeur de la classe Action.
     *
     * @param nomAction   le nom de l'action
     * @param composantes la liste des composants nécessaires pour l'action
     * @param duree       la durée de l'action
     */
    @JsonCreator
    public Action(@JsonProperty("nomAction") String nomAction,
                  @JsonProperty("composantes") ArrayList<String> composantes,
                  @JsonProperty("duree") String duree) {

        this.nomAction = nomAction;
        this.composantes = composantes;
        this.duree = duree;
    }

    /**
     * Retourne la liste des composants nécessaires pour l'action.
     *
     * @return la liste des composants nécessaires
     */
    public ArrayList<String> getComposantes() {
        return composantes;
    }

    /**
     * Renvoie le nom de l'action.
     *
     * @return le nom de l'action
     */
    public String getNomAction() {
        return nomAction;
    }

    /**
     * Renvoie la durée de l'action.
     *
     * @return la durée de l'action
     */
    public String getDuree() {
        return duree;
    }

    /**
     * Méthode pour obtenir l'information de l'action formatée.
     * Cette méthode est utilisée pour afficher l'information de l'action dans un format spécifique.
     *
     * @return une chaîne de caractères représentant l'information de l'action formatée
     */
    @JsonIgnore
    public String getInfoActionFormater() {
        return "Action :" + this.nomAction +
                "\nType de composant nécessaire :" + composantes.stream()
                .collect(Collectors.joining(", ")) +
                "\nDuree : " + this.duree;
    }
}
