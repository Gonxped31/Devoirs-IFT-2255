package domain.logic.Menu;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
    public static void sendEmail(String fromEmail, String password, String toEmail, String subject, String body) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587); // Replace with your SMTP server port
            email.setAuthenticator(new DefaultAuthenticator(fromEmail, password));
            email.setStartTLSEnabled(true);
            email.setFrom(fromEmail);
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setMsg(body);

            email.send();

            System.out.println("Email envoye");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
