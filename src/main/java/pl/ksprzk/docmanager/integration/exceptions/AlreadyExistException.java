package pl.ksprzk.docmanager.integration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Przemysław Kasprzyk
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Record already exsist in database")
public class AlreadyExistException extends Exception {

   private static final long serialVersionUID = -191195169495744891L;

   public AlreadyExistException() {
   }

   public AlreadyExistException(String message) {
      super(message);
   }

   public AlreadyExistException(String message, Throwable cause) {
      super(message, cause);
   }

   public AlreadyExistException(Throwable cause) {
      super(cause);
   }

   public AlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
