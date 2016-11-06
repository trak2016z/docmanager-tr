package pl.ksprzk.docmanager.domain.login;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class LoginResponse {

   protected int id;
   protected String firstName;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public LoginResponse() {
   }

   public LoginResponse(int id, String firstName) {
      this.id = id;
      this.firstName = firstName;
   }
   
}
