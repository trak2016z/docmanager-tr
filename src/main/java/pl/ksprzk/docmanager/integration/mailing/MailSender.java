package pl.ksprzk.docmanager.integration.mailing;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class MailSender {


   private static Message getInitializedMessage() throws IOException, AddressException, MessagingException {
      Properties prop = new Properties();
      prop.load(MailSender.class.getResourceAsStream("/mail.properties"));
      String adress = prop.getProperty("adress");
      String password = prop.getProperty("password");
      Session session = Session.getDefaultInstance(prop, new MailAuthenticator(adress, password));
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(adress));
      return message;
   }

   public static void sendMessage(String text, String subject, String target) {
      try {
         Message message = getInitializedMessage();
         message.setSubject(subject);
         message.setText(text);
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(target));
         Transport.send(message);
      } catch (Exception ex) {

      }

   }

   public static void sendMessage(String predefienedMessage, String target) {
      try {
         Properties messageDetails = new Properties();
         messageDetails.load(MailSender.class.getResourceAsStream("/" + predefienedMessage + ".properties"));
         Message message = getInitializedMessage();
         message.setSubject(messageDetails.getProperty("subject"));
         message.setText(messageDetails.getProperty("content"));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(target));
         Transport.send(message);
      }catch(Exception ex){
         ex.printStackTrace();
      }

   }

}
