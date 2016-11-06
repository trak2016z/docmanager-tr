package pl.ksprzk.docmanager.integration.exceptions;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class NoSuchUserException extends Exception {

   private static final long serialVersionUID = 7730348604941450793L;

   public NoSuchUserException() {
      super("Cannot find such user in database");
   }

   public NoSuchUserException(Throwable cause) {
      super("Cannot find such user in database", cause);
   }

}
