package domain.logic.Membre;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {
    private String titre;
    private String messsage;
    private LocalDateTime date  ;
    private TypeNotification typeNotification;

    public Notification() {

        date = date = LocalDateTime.now();
    }

    public String getTitre() {
        return titre;
    }

    public String getMesssage() {
        return messsage;
    }

    public LocalDateTime getDate() { return date; }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    @Override
    public String toString() {
        return  getTitre().toUpperCase() + '\'' +
                getMesssage() + '\'' +
                "Date:" + getDate();
    }
}
