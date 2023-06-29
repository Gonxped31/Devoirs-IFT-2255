package domain.logic.Membre;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {
    private String titre;
    private String messsage;
    private LocalDateTime date;// = LocalDateTime.now();
    private TypeNotification typeNotification;


    public Notification() {
        //DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        //date = LocalDateTime.parse(date.format(formatDate));
        date = LocalDateTime.now();
    }

    /*public String formatDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }*/

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
