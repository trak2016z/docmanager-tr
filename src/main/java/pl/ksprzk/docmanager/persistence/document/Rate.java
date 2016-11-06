package pl.ksprzk.docmanager.persistence.document;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Entity
@Table(name = "rate")
public class Rate implements Serializable {

   private static final long serialVersionUID = -1109952026056240623L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "rate_id")
   private Integer id;

   @Column(name = "rate_value")
   private float rateValue;

   @ManyToOne
   @JoinColumn(name = "document_fk")
   private Document document;

   @OneToOne
   @JoinColumn(name = "user_fk")
   private User user;
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public float getRateValue() {
      return rateValue;
   }

   public void setRateValue(float rateValue) {
      this.rateValue = rateValue;
   }

   public Document getDocument() {
      return document;
   }

   public void setDocument(Document document) {
      this.document = document;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
   
}
