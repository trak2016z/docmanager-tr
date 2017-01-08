package pl.ksprzk.docmanager.domain.browser;

import java.util.Calendar;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class BrowserDataContainer {

   private String name;
   private Double rate;
   private Calendar modified;
   private String avatar;
   private String firstName;
   private String lastName;
   private int id;

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

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Double getRate() {
      return rate;
   }

   public void setRate(Double rate) {
      this.rate = rate;
   }

   public Calendar getModified() {
      return modified;
   }

   public void setModified(Calendar modified) {
      this.modified = modified;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

}
