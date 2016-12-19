package pl.ksprzk.docmanager.domain.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
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

   void saveFile(MultipartFile file, String strigifiedData, Credentials credentials) throws IOException {
      String extension = getExtension(file.getOriginalFilename());
      DocumentData data = mapper.readValue(strigifiedData, DocumentData.class);
      String newFileName = generateFilename();
      File savedFile = new File(PATH + "/" + newFileName);
      savedFile.createNewFile();
      file.transferTo(savedFile);
      User user = userService.getUser(credentials.getUsername());
      Document document = DocumentFactory.provideJpaDocument(data, newFileName, extension, user);
      service.saveDocument(document);
   }
   
   void downloadFile(HttpServletResponse response, Integer id) throws FileNotFoundException, IOException{
      Document document = service.getDocumentById(id);
      File file = new File(document.getFilename()+"."+document.getExtension());
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
      }
      else {
         return "";
      }
   }

}
