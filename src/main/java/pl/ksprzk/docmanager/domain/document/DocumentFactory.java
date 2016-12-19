package pl.ksprzk.docmanager.domain.document;

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
      return document;
   }
}
