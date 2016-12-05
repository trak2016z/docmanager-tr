package pl.ksprzk.docmanager.domain.login;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.exceptions.AlreadyExistException;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.mailing.MailSender;
import pl.ksprzk.docmanager.integration.security.Generators;
import pl.ksprzk.docmanager.persistence.user.User;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
class UserService {

   private final UserPersistenceService persistenceService;
   private final Generators generator;

   @Autowired
   UserService(UserPersistenceService persistenceService) {
      this.persistenceService = persistenceService;
      generator = new Generators();
   }

   void registerUser(UserDTO user) throws AlreadyExistException {
      User entity = UserFactory.createEntity(user);
      persistenceService.registerUser(entity);
      new Runnable() {
         @Override
         public void run() {
            MailSender.sendMessage("registerMsg", user.getEmail());
         }
      }.run();
     
   }
   
   Boolean emailAvailable (String email){
      return persistenceService.userExist(email);
   }
   
   void resetPassword (String email) throws NoSuchUserException{
      String newPassword = RandomStringUtils.randomAlphanumeric(8, 16);
      String hashedPassword = generator.generateSHA512(newPassword);
      persistenceService.resetPassword(email, hashedPassword);
      new Runnable() {
         @Override
         public void run() {
            MailSender.sendPasswordMessage(newPassword, email);
         }
      }.run();
   }
}
