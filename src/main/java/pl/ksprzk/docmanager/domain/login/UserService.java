package pl.ksprzk.docmanager.domain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.exceptions.AlreadyExistException;
import pl.ksprzk.docmanager.integration.services.MailingService;
import pl.ksprzk.docmanager.persistence.user.User;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
class UserService {

   private final UserPersistenceService persistenceService;
   private MailingService mailingService;

   @Autowired
   UserService(UserPersistenceService persistenceService) {
      this.persistenceService = persistenceService;
   }

   public void setMailingService(MailingService mailingService) {
      this.mailingService = mailingService;
   }
   
   void registerUser(UserDTO user) throws AlreadyExistException {
      User entity = UserFactory.createEntity(user);
      persistenceService.registerUser(entity);
      mailingService.sendMessage("registeredMsg", user.getEmail());
   }
}
