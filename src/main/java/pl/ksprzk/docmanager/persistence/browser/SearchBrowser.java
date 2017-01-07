package pl.ksprzk.docmanager.persistence.browser;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Entity
@Table(name = "search_browser")
public class SearchBrowser implements Serializable {

   private static final long serialVersionUID = -417601052887679396L;

   @Column
   private String avatar;
   @Column
   private String keywords;
   @Column
   private boolean approved;
   @Column
   private String name;
   @Column
   private Timestamp uploaded;
   @Column(name = "fname")
   private String firstName;
   @Column(name = "lname")
   private String lastName;
   @Column(name = "public")
   private boolean publicDocument;
   @Id
   @Column(name = "document_id")
   private int id;
   @Column(name = "average_rate")
   private double averageRate;

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public String getKeywords() {
      return keywords;
   }

   public void setKeywords(String keywords) {
      this.keywords = keywords;
   }

   public boolean isApproved() {
      return approved;
   }

   public void setApproved(boolean approved) {
      this.approved = approved;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Timestamp getUploaded() {
      return uploaded;
   }

   public void setUploaded(Timestamp uploaded) {
      this.uploaded = uploaded;
   }

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

   public boolean isPublicDocument() {
      return publicDocument;
   }

   public void setPublicDocument(boolean publicDocument) {
      this.publicDocument = publicDocument;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public double getAverageRate() {
      return averageRate;
   }

   public void setAverageRate(double averageRate) {
      this.averageRate = averageRate;
   }
   
   
}
