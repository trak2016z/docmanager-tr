package pl.ksprzk.docmanager.persistence.comment;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.ksprzk.docmanager.persistence.document.Document;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

   private static final long serialVersionUID = 1188927012471516381L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "comment_id")
   private int id;

   @Column(name = "comm_date")
   @NotNull
   private Timestamp date;

   @Column(name = "content")
   @NotNull
   @Size(max = 600)
   private String content;

   @Column(name = "visible")
   private boolean visible;

   @OneToOne
   @JoinColumn(name = "user_fk")
   private User user;

   @OneToOne
   @JoinColumn(name = "document_fk")
   private Document document;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Timestamp getDate() {
      return date;
   }

   public void setDate(Timestamp date) {
      this.date = date;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public boolean isVisible() {
      return visible;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Document getDocument() {
      return document;
   }

   public void setDocument(Document document) {
      this.document = document;
   }

}
