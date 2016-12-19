package pl.ksprzk.docmanager.persistence.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class DocumentPersistenceService {

   @Autowired
   DocumentRepository repository;

   public void saveDocument(Document document) {
      repository.save(document);
   }

   public Document getDocumentByName(String name) {
      return repository.findDocumentByName(name);
   }

   public Document getDocumentById(int id) {
      return repository.findOne(id);
   }

}
