package service;

import com.fasterxml.jackson.core.type.TypeReference;
import domain.logic.Robot.Activite;
import domain.logic.Robot.Robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BaseDeDonneeRobotVendus extends BaseDeDonnee{


        private static final String FILE_NAME= "robotsVendus.json";

        public BaseDeDonneeRobotVendus() throws IOException {
            super(FILE_NAME,new TypeReference<ArrayList<Robot>>() {});
        }


    @Override
    protected void init() {

    }
    public Robot getCurrentSolfRobot(String numeroSeri){
            return (Robot) this.getListObjet().stream()
                    .filter(r->((Robot)r).getNumeroSerie().toString().trim().equals(numeroSeri.trim()))
                    .findFirst()
                    .orElse(null);
    }
}
