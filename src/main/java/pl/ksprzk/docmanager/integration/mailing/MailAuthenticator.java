package pl.ksprzk.docmanager.integration.mailing;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class MailAuthenticator extends Authenticator {

   private final String adress;
   private final String password;

   public MailAuthenticator(String adress, String password) {
      this.adress = adress;
      this.password = password;
   }

   @Override
   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(adress, password);
   }
   

}
