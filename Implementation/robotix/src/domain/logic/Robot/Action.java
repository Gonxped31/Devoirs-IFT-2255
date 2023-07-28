package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Action implements java.io.Serializable {
    private String nomAction;
    private ArrayList<String> composantes;
    private String duree;

    @JsonCreator
    public Action(@JsonProperty("nomAction") String nomAction,
                  @JsonProperty("composantes") ArrayList<String> composantes,
                  @JsonProperty("duree") String duree) {

        this.nomAction = nomAction;
        this.composantes = composantes;
        this.duree = duree;
    }

    public ArrayList<String> getComposantes() {
        return composantes;
    }

    public String getNomAction() {
        return nomAction;
    }

    // Ne pas retirer le getter même si on ne l'utilise pas. Il est nécessaire pour la désérialisation.
    public String getDuree() {
        return duree;
    }

    @JsonIgnore
    public String getInfoActionFormater() {
        return "Action :" + this.nomAction +
                "\nType de composant nécessaire :" + composantes.stream()
                .collect(Collectors.joining(", ")) +
                "\nDuree : " + this.duree;
    }
}
