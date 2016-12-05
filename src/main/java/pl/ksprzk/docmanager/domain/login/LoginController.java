package pl.ksprzk.docmanager.domain.login;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.exceptions.SecurityUninitializedException;
import pl.ksprzk.docmanager.integration.security.Credentials;
import pl.ksprzk.docmanager.integration.security.DatabaseProvider;
import pl.ksprzk.docmanager.integration.security.Security;

/**
 *
 * @author Przemysław Kasprzyk
 */
@RestController
@RequestMapping(path = "/loginservice")
class LoginController {

   @Autowired
   DatabaseProvider securityService;
   
   @Autowired
   UserService userService;
   
   @RequestMapping(path = "/login", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> login(@RequestBody LoginRequestBody requestBody, HttpServletRequest request) throws SecurityUninitializedException, NoSuchUserException, PermissionDeniedException {
      Credentials credentials = securityService.provideCredentials(requestBody.getEmail(), requestBody.getPassword());
      return ResponseEntity.ok(Security.getInstance().validate(request, credentials));
   }
   
   @RequestMapping(path = "/reset", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> reset(@RequestParam String email) throws NoSuchUserException{
      userService.resetPassword(email);
      return ResponseEntity.ok().build();
   }
   
   

}
