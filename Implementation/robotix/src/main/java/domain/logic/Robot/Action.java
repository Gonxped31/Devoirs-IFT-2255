package domain.logic.Robot;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Action implements java.io.Serializable{
    private String nomAction;
    private ArrayList<String> composantes;
    private String duree;

    public Action(String nomAction, ArrayList<String> composantes, String duree){
        this.nomAction = nomAction;
        this.composantes = composantes;
        this.duree = duree;
    }

    public String getNomAction(){
        return nomAction;
    }

    public ArrayList<String> getComposantes(){
        return composantes;
    }

    public String getDuree(){
        return duree;
    }
    @JsonIgnore
    public String getInfoActionFormater(){
        return "Nom :" + this.nomAction +
                "\nType de composant nécessaire :" + composantes.stream()
                .collect(Collectors.joining("\n")) +
                "Duree : " + this.duree  ;
    }

}