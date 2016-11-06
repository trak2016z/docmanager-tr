package pl.ksprzk.docmanager.integration.exceptions;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class PermissionDeniedException extends Exception {

   private static final long serialVersionUID = 3247251172230753862L;

   public PermissionDeniedException() {
      super("Permission denied for this user");
   }

   public PermissionDeniedException(Throwable cause) {
      super("Permission denied for this user", cause);
   }

}
