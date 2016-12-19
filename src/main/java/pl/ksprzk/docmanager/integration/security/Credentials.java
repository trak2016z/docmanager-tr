package pl.ksprzk.docmanager.integration.security;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class Credentials {

   private String sessionId;
   private String authTk;
   private String username;
   private char permission;
   private String password;
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getSessionId() {
      return sessionId;
   }

   public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
   }

   public String getAuthTk() {
      return authTk;
   }

   public void setAuthTk(String authTk) {
      this.authTk = authTk;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public char getPermission() {
      return permission;
   }

   public void setPermission(char permission) {
      this.permission = permission;
   }

}
