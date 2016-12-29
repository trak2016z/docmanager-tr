package pl.ksprzk.docmanager.domain.document;

import java.sql.Timestamp;
import java.util.Calendar;
import pl.ksprzk.docmanager.persistence.document.Document;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class DocumentFactory {
   public static Document provideJpaDocument (DocumentData data, String filename, String extension, User user){
      Document document = new Document();
      document.setPublicFlag(data.isPublicVisible());
      document.setName(data.getName());
      document.setFilename(filename);
      document.setExtension(extension);
      document.setUser(user);
      document.setKeywords(data.getKeywords());
      document.setVersion(1);
      document.setNote(data.getNote());
      document.setUpdateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
      document.setId(data.getId());
      return document;
   }
   
   public static DocumentData provideJsonDocument (Document document){
      DocumentData data = new DocumentData();
      data.setName(document.getName());
      data.setKeywords(document.getKeywords());
      Calendar date = Calendar.getInstance();
      date.setTimeInMillis(document.getUpdateTime().getTime());
      data.setLastUpdate(date);
      data.setNote(document.getNote());
      data.setPublicVisible(document.isPublicFlag());
      data.setId(document.getId());
      return data;
   }
}
