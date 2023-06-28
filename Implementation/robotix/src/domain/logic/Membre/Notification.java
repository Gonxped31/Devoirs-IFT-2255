package domain.logic.Membre;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Notification {
    private String titre;
    private String messsage;
    private LocalDateTime date = LocalDateTime.now();
    private TypeNotification typeNotification;
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public Notification (String titre, String messsage, LocalDateTime date, DateTimeFormatter formatDate, TypeNotification typeNotification) {
        this.titre = titre;
        this.messsage = messsage;
        this.date = LocalDateTime.parse(date.format(formatDate));
        this.typeNotification = typeNotification;
    }
    public String getTitre() {
        return titre;
    }

    public String getMesssage() {
        return messsage;
    }

    public LocalDateTime getDate() {
        return date;

    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void menuPrincipale() {
        System.out.println(date);
    }
}
