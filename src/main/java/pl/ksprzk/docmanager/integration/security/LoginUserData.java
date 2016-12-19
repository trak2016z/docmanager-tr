package pl.ksprzk.docmanager.integration.security;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class LoginUserData {

   protected int id;
   protected String name;
   protected char permission;
   protected Boolean trusted;
   protected String avatar;

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
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

   private LoginUserData(int id, String name, char permission, Boolean trusted, String avatar) {
      this.id = id;
      this.name = name;
      this.permission = permission;
      this.trusted = trusted;
      this.avatar = avatar;
   }

   public static class LoginUserDataBuilder {

      private final int id;
      private final String name;
      private final char permission;
      private final Boolean trusted;
      private String avatar;

      public LoginUserDataBuilder(int id, String name, char permission, Boolean trusted) {
         this.id = id;
         this.name = name;
         this.permission = permission;
         this.trusted = trusted;
      }

      public LoginUserDataBuilder avatar(String avatar) {
         this.avatar = avatar;
         return this;
      }

      public LoginUserData build() {
         return new LoginUserData(id, name, permission, trusted, avatar);
      }
   }
}
