package email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class ReadMessage {

	public void setText(MimeMessage m, String jTx) {
		try {
			m.setText(jTx);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
