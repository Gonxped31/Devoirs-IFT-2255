package domain.logic.Outils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import javax.swing.SwingWorker;

public class EmailSender extends SwingWorker<Void, Void> {
    private String fromEmail;
    private String password;
    private String toEmail;
    private String subject;
    private String body;

    public EmailSender(String fromEmail, String password, String toEmail, String subject, String body) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    @Override
    protected Void doInBackground() throws Exception {
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
        return null;
    }

    // Méthode pour envoyer l'email en background
    public void sendInBackground() {
        execute();
    }
}

