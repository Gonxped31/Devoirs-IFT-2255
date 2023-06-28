package service;

import service.BaseDeDonnee;

import java.io.IOException;
import java.lang.reflect.Type;

public class BaseDeDonneeActivite extends BaseDeDonnee {
private static final String FILE_NAME= "activite.json";

public BaseDeDonneeActivite() throws IOException {
    super(this.FILE_NAME);
}


    @Override
    protected Type getType() {
        return null;
    }

    @Override
    protected void init() {

    }
}