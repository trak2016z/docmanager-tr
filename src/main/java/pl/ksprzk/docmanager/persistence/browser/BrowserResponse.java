package pl.ksprzk.docmanager.persistence.browser;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Entity
public class BrowserResponse implements Serializable {

   private static final long serialVersionUID = -2275417567791800739L;

   @Id
   @Column(name = "document_id")
   private int id;
   private int number;
   @Column
   private String name;
   @Column(name = "fname")
   private String authorFirstName;
   @Column(name = "lname")
   private String authorLastName;
   @Column
   private Timestamp uploaded;
   @Column(name = "avatar")
   private String miniature;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAuthorFirstName() {
      return authorFirstName;
   }

   public void setAuthorFirstName(String authorFirstName) {
      this.authorFirstName = authorFirstName;
   }

   public String getAuthorLastName() {
      return authorLastName;
   }

   public void setAuthorLastName(String authorLastName) {
      this.authorLastName = authorLastName;
   }

   public Timestamp getUploaded() {
      return uploaded;
   }

   public void setUploaded(Timestamp uploaded) {
      this.uploaded = uploaded;
   }

   public String getMiniature() {
      return miniature;
   }

   public void setMiniature(String miniature) {
      this.miniature = miniature;
   }
   
   

}
