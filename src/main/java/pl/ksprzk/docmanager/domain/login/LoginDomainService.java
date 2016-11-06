package pl.ksprzk.docmanager.domain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
class LoginDomainService {

   private final UserPersistenceService userService;

   @Autowired
   public LoginDomainService(UserPersistenceService userService) {
      this.userService = userService;
   }
   
   public LoginResponse login (LoginRequestBody request) throws NoSuchUserException, PermissionDeniedException{
      LoginUserData data = userService.provideLogonData(request.getEmail(), request.getPassword());
      return new LoginResponse(data.getId(), data.getFirstName());
   }

}
