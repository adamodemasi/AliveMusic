package email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class SenderEmail {

public void sendEmail(MimeMessage m, Session s) {
		
		try {
			
			SMTPTransport t = (SMTPTransport)s.getTransport("smtps");
			t.connect("smtp.gmail.com", "musicexpinfo@gmail.com", "dongogay");
			t.sendMessage(m, m.getAllRecipients());      
			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	
}
