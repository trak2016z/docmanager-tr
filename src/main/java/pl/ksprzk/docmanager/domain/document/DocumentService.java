package pl.ksprzk.docmanager.domain.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.ksprzk.docmanager.integration.security.Credentials;
import pl.ksprzk.docmanager.persistence.document.Document;
import pl.ksprzk.docmanager.persistence.document.DocumentPersistenceService;
import pl.ksprzk.docmanager.persistence.user.User;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class DocumentService {

   @Autowired
   UserPersistenceService userService;

   @Autowired
   DocumentPersistenceService service;

   private final String PATH = "storage";
   private final ObjectMapper mapper;

   public DocumentService() {
      File file = new File(PATH);
      if (!file.exists()) {
         file.mkdir();
      }
      mapper = new ObjectMapper();
   }

   List<DocumentData> getUserPublications(DocumentOwner owner) {
      List<Document> documents = service.getDocumentsByEmail(owner.getUser());
      List<DocumentData> responseList = new ArrayList<>();
      for (Document d : documents) {
         responseList.add(DocumentFactory.provideJsonDocument(d));
      }
      return responseList;
   }

   void deleteDocument(int documentId) {
      Document document = service.getDocumentById(documentId);
      String filename = document.getFilename();
      String avatarFilename = document.getAvatar();
      service.deleteDocument(document);
      if (StringUtils.isNoneEmpty(filename)) {
         File file = new File(PATH + "/" + filename);
         file.delete();
      }
      if (StringUtils.isNoneEmpty(avatarFilename)) {
         File file = new File(PATH + "/" + avatarFilename);
         file.delete();
      } 
   }
   
   void saveFileDataToDatabase (DocumentData data, Credentials credentials){
      User user = userService.getUser(credentials.getUsername());
      Document document = DocumentFactory.provideJpaDocument(data, user);
      service.saveDocument(document);
   }

   FileDescription saveFile(MultipartFile file) throws IOException {
      String extension = getExtension(file.getOriginalFilename());
      String newFileName = generateFilename();
      File savedFile = new File(PATH + "/" + newFileName);
      savedFile.createNewFile();
      file.transferTo(savedFile);
      return new FileDescription(extension, newFileName);
   }

   void downloadFile(HttpServletResponse response, Integer id) throws FileNotFoundException, IOException {
      Document document = service.getDocumentById(id);
      File file = new File(document.getFilename() + "." + document.getExtension());
      InputStream inputStream = new FileInputStream(file);
      IOUtils.copy(inputStream, response.getOutputStream());
      response.flushBuffer();
   }

   private String generateFilename() {
      return Calendar.getInstance().getTimeInMillis() + '-' + RandomStringUtils.randomAlphanumeric(8);
   }

   private String getExtension(String filename) {
      String[] splittedName = filename.split("\\.");
      if (splittedName.length > 1) {
         return splittedName[splittedName.length - 1];
      } else {
         return "";
      }
   }

}
