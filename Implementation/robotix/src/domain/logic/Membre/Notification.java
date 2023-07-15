package domain.logic.Membre;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/*
Use case:
- quand on a été suivi
- problème avec système
- date limite des activité
*/

public class Notification implements java.io.Serializable{
    private String titre;
    private String message;
    private String date;// = LocalDateTime.now();
    private TypeNotification typeNotification;


    public Notification() {
        //typeNotification = TypeNotification.NOUVEAU_ABONNE;
        //message = "TEST";
        //date =  "01/02/1223";

        //DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        //date = LocalDateTime.parse(date.format(formatDate));
    }

    public Notification(String titre, String message, String date, TypeNotification typeNotification){
        this.titre = titre;
        this.message = message;
        this.date = date;
        this.typeNotification = typeNotification;
    }

    /*public String formatDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }*/

    public String getTitre() {
        return titre;
    }

    public String getMesssage() {
        return message;
    }

    public String getDate() { return date; }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setMesssage(String messsage) {
        this.message = messsage;
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
