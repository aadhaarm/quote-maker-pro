package com.lounge3.quotemakerpro.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lounge3.quotemakerpro.shared.TO.FormTO;

public class MailManager {
	
	public static boolean sendFormSaveEmail(String userEmailAddress, long formSaveId, String formName) {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "<a href=\"http://quotemakerpro.appspot.com?formSaveId="+ formSaveId + "&formName="+ formName +"#openSaveform\">Link to form</a>";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("aadhaar.m@gmail.com", "Quote Maker Pro Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(userEmailAddress, "Mr. User"));
            msg.setSubject("Please find the link to the form you just submitted");
            msg.setText(msgBody);
            Transport.send(msg);
    
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
