package pl.ksprzk.docmanager.domain.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;

/**
 *
 * @author Przemysław Kasprzyk
 */
class LoginResponseFactory {

   static ResponseEntity<?> provideLoginResponse(LoginDomainService service, LoginRequestBody request) {
      try {
         return ResponseEntity.ok(service.login(request));
      } catch (NoSuchUserException ex) {
         return ResponseEntity.notFound().build();
      } catch (PermissionDeniedException ex) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
   }
}
