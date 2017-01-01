package pl.ksprzk.docmanager.domain.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class DocumentData {

   @JsonProperty(value ="isPublic")
   private boolean publicVisible;
   private String keywords;
   private String name;
   private String note;
   private Calendar lastUpdate;
   private Integer id;
   private FileDescription fileDescription;
   private String avatarFile;

   public String getAvatarFile() {
      return avatarFile;
   }

   public void setAvatarFile(String avatarFile) {
      this.avatarFile = avatarFile;
   }
   
   public FileDescription getFileDescription() {
      return fileDescription;
   }

   public void setFileDescription(FileDescription fileDescription) {
      this.fileDescription = fileDescription;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Calendar getLastUpdate() {
      return lastUpdate;
   }

   public void setLastUpdate(Calendar lastUpdate) {
      this.lastUpdate = lastUpdate;
   }
   
   public boolean isPublicVisible() {
      return publicVisible;
   }

   public void setPublicVisible(boolean publicVisible) {
      this.publicVisible = publicVisible;
   }

   public String getKeywords() {
      return keywords;
   }

   public void setKeywords(String keywords) {
      this.keywords = keywords;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

}
