package domain.logic.Robot;

import java.util.ArrayList;

public class Action {
    private String nomAction;
    private ArrayList<TypesComposants> composantes;
    private String duree;

    public Action(String nomAction, ArrayList<TypesComposants> composantes, String duree){
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

    public String getDuree(){
        return duree;
    }
}
