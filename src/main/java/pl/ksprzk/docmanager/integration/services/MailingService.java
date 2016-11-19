package pl.ksprzk.docmanager.integration.services;

import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class MailingService {

   private Properties prop;
   private String adress;
   private Session session;
   private String password;

   public MailingService() {
      try {
         FileInputStream fis = new FileInputStream("mail.properties");
         prop = new Properties();
         prop.load(fis);
         adress = prop.getProperty("testy1234@vip.interia.pl");
         password = prop.getProperty("testytesty1234");
         session = Session.getDefaultInstance(prop,
                 new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(adress, password);
            }
         });
      } catch (Exception ex) {

      }
   }

   public void sendMessage(String text, String subject, String target) {
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(adress));
         message.setSubject(subject);
         message.setText(text);
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(target));
         Transport.send(message);
      } catch (Exception ex) {

      }

   }

   public void sendMessage(String predefienedMessage, String target) {
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(adress));
         FileInputStream fis = new FileInputStream(predefienedMessage + ".properties");
         Properties messageDetails = new Properties();
         message.setSubject(messageDetails.getProperty("subject"));
         message.setText(messageDetails.getProperty("content"));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(target));
         Transport.send(message);
      } catch (Exception ex) {

      }

   }

}
