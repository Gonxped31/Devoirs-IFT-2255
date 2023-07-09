package service;


import com.fasterxml.jackson.core.type.TypeReference;

import domain.logic.Robot.Composant;
import domain.logic.Robot.Robot;
import java.io.IOException;
import java.util.*;


public class BaseDeDonneeCommun extends BaseDeDonnee {


    private List<Map<String, List<Robot>>> listRobot;
    private List<Map<String, List<Composant>>> listComposant;
    public <T> BaseDeDonneeCommun(String fileName, TypeReference<ArrayList<T>> type) throws IOException {
        super(fileName,type );
        listComposant = new ArrayList<>();
        listRobot = new ArrayList<>();

    }



    @Override
    protected void init() {

    }

    protected void initListeRobotEtComposant() {

    }




    public List<Map<String, List<Composant>>> getListComposant() {
        return listComposant;
    }

    public List<Map<String, List<Robot>>> getListRobot() {
        return listRobot;
    }

    public Robot retournerRobot(String numeroSerie) {
        return listRobot.stream()
                .flatMap(map -> map.values().stream())
                .flatMap(List::stream)
                .filter(robot -> robot.getNumeroSerie().equals(numeroSerie))
                .findFirst()
                .orElse(null);
    }

}


