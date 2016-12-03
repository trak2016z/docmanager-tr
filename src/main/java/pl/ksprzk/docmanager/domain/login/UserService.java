package pl.ksprzk.docmanager.domain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.exceptions.AlreadyExistException;
import pl.ksprzk.docmanager.integration.mailing.MailSender;
import pl.ksprzk.docmanager.persistence.user.User;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
class UserService {

   private final UserPersistenceService persistenceService;

   @Autowired
   UserService(UserPersistenceService persistenceService) {
      this.persistenceService = persistenceService;
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
}
