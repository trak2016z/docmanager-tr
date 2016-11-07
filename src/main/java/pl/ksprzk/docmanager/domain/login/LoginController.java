package pl.ksprzk.docmanager.domain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Przemysław Kasprzyk
 */
@RestController
@RequestMapping(path = "/loginservice")
class LoginController {

   private final LoginDomainService service;

   @Autowired
   public LoginController(LoginDomainService service) {
      this.service = service;
   }

   @RequestMapping(path = "/login", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> login(@RequestBody LoginRequestBody requestBody) {
      return LoginResponseFactory.provideLoginResponse(service, requestBody);
   }

}
