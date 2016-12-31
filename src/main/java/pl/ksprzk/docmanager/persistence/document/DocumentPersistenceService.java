package pl.ksprzk.docmanager.persistence.document;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.persistence.user.User;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class DocumentPersistenceService {

   @Autowired
   DocumentRepository repository;
   
   @Autowired
   UserPersistenceService userService;

   public void saveDocument(Document document) {
      repository.save(document);
   }

   public Document getDocumentByName(String name) {
      return repository.findDocumentByName(name);
   }

   public Document getDocumentById(int id) {
      return repository.findOne(id);
   }
   
   public List<Document> getDocumentsByEmail(String email){
      User user = userService.getUser(email);
      return repository.findDocumentByUser(user);
   }
   
   public void deleteDocument (int id){
      repository.delete(id);
   }
   
   public void deleteDocument (Document document){
      repository.delete(document);
   }
}
