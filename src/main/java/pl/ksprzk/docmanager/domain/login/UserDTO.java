package pl.ksprzk.docmanager.domain.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class UserDTO {

   @JsonProperty(value = "fname")
   private String firstName;
   @JsonProperty(value = "lname")
   private String lastName;
   @JsonProperty(value = "pw")
   private String password;
   @JsonProperty(value = "dob")
   private Calendar dateOfBirth;

   private String phone;

   @JsonProperty(value = "mname")
   private String middleName;
   private String email;

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Calendar getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(Calendar dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

}
