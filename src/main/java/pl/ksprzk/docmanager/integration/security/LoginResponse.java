package pl.ksprzk.docmanager.integration.security;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class LoginResponse {

   private final String name;
   private final String auth_tkt;
   private final char permission;

   public LoginResponse(Credentials credentials) {
      name = credentials.getUsername();
      auth_tkt = credentials.getAuthTk();
      permission = credentials.getPermission();
   }

   public char getPermission() {
      return permission;
   }

   public String getName() {
      return name;
   }

   public String getAuth_tkt() {
      return auth_tkt;
   }

}
