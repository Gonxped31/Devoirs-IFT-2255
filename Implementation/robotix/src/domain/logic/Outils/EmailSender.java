package domain.logic.Outils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import javax.swing.SwingWorker;

/**
 * Une classe utilisée pour envoyer un email de manière asynchrone en arrière-plan.
 * Cette classe hérite de SwingWorker pour faciliter l'exécution de l'envoi d'email sur un thread séparé.
 */
public class EmailSender extends SwingWorker<Void, Void> {
    private String fromEmail;
    private String password;
    private String toEmail;
    private String subject;
    private String body;

    /**
     * Constructeur de la classe.
     *
     * @param fromEmail l'adresse email de l'expéditeur
     * @param password  le mot de passe de l'expéditeur (pour l'authentification SMTP)
     * @param toEmail   l'adresse email du destinataire
     * @param subject   le sujet de l'email
     * @param body      le corps du message de l'email
     */
    public EmailSender(String fromEmail, String password, String toEmail, String subject, String body) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Méthode exécutée en arrière-plan pour envoyer l'email.
     *
     * @return nul car la méthode ne renvoie rien
     * @throws Exception en cas d'erreur lors de l'envoi de l'email
     */
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

    /**
     * Méthode pour envoyer l'email en arrière-plan en lançant le travail du SwingWorker.
     */    public void sendInBackground() {
        execute();
    }
}

