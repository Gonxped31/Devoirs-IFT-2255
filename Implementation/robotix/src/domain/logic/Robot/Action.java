package domain.logic.Robot;

import java.util.ArrayList;

public class Action {
    private String nomAction;
    private ArrayList<Composant> composantes;

    public Action(String nomAction, ArrayList<Composant> composantes){
        this.nomAction = nomAction;
        this.composantes = composantes;
    }

    public String getNomAction(){
        return nomAction;
    }

    public ArrayList<Composant> getComposantes(){
        return composantes;
    }
}
