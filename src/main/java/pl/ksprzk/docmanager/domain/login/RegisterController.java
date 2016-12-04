package pl.ksprzk.docmanager.domain.login;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ksprzk.docmanager.integration.exceptions.AlreadyExistException;

/**
 *
 * @author Przemysław Kasprzyk
 */
@RestController
public class RegisterController {
   
   private UserService service;

   @Autowired
   public RegisterController(UserService service) {
      this.service = service;
   }
   
   @RequestMapping(method = RequestMethod.POST, path = "/register/registerUser")
   public ResponseEntity registerUser (@RequestBody UserDTO user, HttpSession session) throws AlreadyExistException{
      service.registerUser(user);
      return ResponseEntity.ok().build();
   }
   
   @RequestMapping(method = RequestMethod.GET, path = "/register/userAvailable")
   public ResponseEntity isUserAvailable (@RequestParam(name = "email") String email){
      return ResponseEntity.ok(service.emailAvailable(email));
   }

}
