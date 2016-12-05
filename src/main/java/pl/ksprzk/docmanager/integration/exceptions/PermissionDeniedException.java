package pl.ksprzk.docmanager.integration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Przemysław Kasprzyk
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends Exception {

   private static final long serialVersionUID = 3247251172230753862L;

   public PermissionDeniedException() {
      super("Permission denied for this user");
   }

   public PermissionDeniedException(Throwable cause) {
      super("Permission denied for this user", cause);
   }

}
