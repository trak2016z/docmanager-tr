package pl.ksprzk.docmanager.persistence.user;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column
   @NotNull
   private Timestamp dob;

   @Column
   @NotNull
   @Size(max = 50, min = 8)
   private String password;
   
   @Column(name = "fname")
   @NotNull
   @Size(max = 50)
   private String firstName;

   @Column(name = "mname")
   @Size(max = 50)
   private String middleName;

   @Column(name = "lname")
   @NotNull
   @Size(max = 50)
   private String lastName;

   @Column
   @NotNull
   @Size(max = 300)
   private String email;

   @Column
   @NotNull
   private char permission;

   @Column
   private String description;

   @Column
   @Size(max = 50)
   private String avatar;

   @Column
   private Boolean trusted;

   @Column
   @Size(max = 20)
   private String phone;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Timestamp getDob() {
      return dob;
   }

   public void setDob(Timestamp dob) {
      this.dob = dob;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public char getPermission() {
      return permission;
   }

   public void setPermission(char permission) {
      this.permission = permission;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public Boolean getTrusted() {
      return trusted;
   }

   public void setTrusted(Boolean trusted) {
      this.trusted = trusted;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   
}
