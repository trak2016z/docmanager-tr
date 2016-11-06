package pl.ksprzk.docmanager.domain.login;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class LoginUserData {

   protected int id;
   protected String firstName;
   protected char permission;
   protected Boolean trusted;
   protected String avatar;

   public int getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public char getPermission() {
      return permission;
   }

   public Boolean getTrusted() {
      return trusted;
   }

   public String getAvatar() {
      return avatar;
   }

   protected LoginUserData() {

   }

   private LoginUserData(int id, String firstName, char permission, Boolean trusted, String avatar) {
      this.id = id;
      this.firstName = firstName;
      this.permission = permission;
      this.trusted = trusted;
      this.avatar = avatar;
   }

   public static class LoginUserDataBuilder {

      private final int id;
      private final String firstName;
      private final char permission;
      private final Boolean trusted;
      private String avatar;

      public LoginUserDataBuilder(int id, String firstName, char permission, Boolean trusted) {
         this.id = id;
         this.firstName = firstName;
         this.permission = permission;
         this.trusted = trusted;
      }

      public LoginUserDataBuilder avatar(String avatar) {
         this.avatar = avatar;
         return this;
      }

      public LoginUserData build() {
         return new LoginUserData(id, firstName, permission, trusted, avatar);
      }
   }
}
