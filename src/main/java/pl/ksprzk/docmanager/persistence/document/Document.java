package pl.ksprzk.docmanager.persistence.document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Entity
@Table(name = "document")
public class Document implements Serializable {

   private static final long serialVersionUID = 7558572990059163587L;

   @Id
   @Column(name = "document_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "filename")
   @Size(max = 255)
   private String filename;

   @Column(name = "avatar")
   @Size(max = 255)
   private String avatar;

   @Column(name = "version")
   @NotNull
   private int version;

   @Column(name = "extension")
   @Size(max = 5)
   private String extension;

   @Column(name = "note")
   @Size(max = 600)
   private String note;

   @Column(name = "keywords")
   @Size(max = 300)
   private String keywords;

   @Column(name = "approved")
   private boolean approved;

   @Column(name = "public")
   private boolean publicFlag;

   @Column(name = "uploaded")
   private Timestamp updateTime;

   @Column(name = "name")
   @NotNull
   @Size(max = 200)
   private String name;

   @OneToOne
   @JoinColumn(name = "fk_user")
   private User user;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
   private Set<Rate> rate = new HashSet<>();

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public String getAvatar() {
      return avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   public int getVersion() {
      return version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

   public String getExtension() {
      return extension;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
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

   public boolean isPublicFlag() {
      return publicFlag;
   }

   public void setPublicFlag(boolean publicFlag) {
      this.publicFlag = publicFlag;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Set<Rate> getRate() {
      return rate;
   }

   public void setRate(Set<Rate> rate) {
      this.rate = rate;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Timestamp getUpdateTime() {
      return updateTime;
   }

   public void setUpdateTime(Timestamp updateTime) {
      this.updateTime = updateTime;
   }

}
