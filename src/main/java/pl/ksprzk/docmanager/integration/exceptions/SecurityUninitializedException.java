package pl.ksprzk.docmanager.integration.exceptions;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class SecurityUninitializedException extends Exception {

   private final static String MESSAGE = "Could not initialize security module";
   private static final long serialVersionUID = -6530786621695052929L;
   
   public SecurityUninitializedException() {
      super(MESSAGE);
   }

   public SecurityUninitializedException(Throwable cause) {
      super(MESSAGE, cause);
   }

   public SecurityUninitializedException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(MESSAGE, cause, enableSuppression, writableStackTrace);
   }
   
   

}
