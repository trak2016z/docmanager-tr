package pl.ksprzk.docmanager.integration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Przemysław Kasprzyk
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoSuchUserException extends Exception {

   private static final long serialVersionUID = 7730348604941450793L;

   public NoSuchUserException() {
      super("Cannot find such user in database");
   }

   public NoSuchUserException(Throwable cause) {
      super("Cannot find such user in database", cause);
   }

}
