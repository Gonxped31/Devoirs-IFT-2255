package domain.logic.Robot;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Action {
    private String nomAction;
    private ArrayList<TypesComposants> composantes;
    private int duree;

    public Action(String nomAction, ArrayList<TypesComposants> composantes, int duree){
        this.nomAction = nomAction;
        this.composantes = composantes;
        this.duree = duree;
    }

    public String getNomAction(){
        return nomAction;
    }

    public ArrayList<TypesComposants> getComposantes(){
        return composantes;
    }

    public int getDuree(){
        return duree;
    }

    public String getInfoActionFormater(){
        return "Nom :" + this.nomAction +
                "\nType de composant nécessaire :" + composantes.stream()
                .map(TypesComposants::name)
                .collect(Collectors.joining("\n")) +
                "Duree : " + this.duree  ;
    }

}
