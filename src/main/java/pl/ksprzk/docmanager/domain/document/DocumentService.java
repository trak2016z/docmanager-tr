package pl.ksprzk.docmanager.domain.document;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class DocumentService {

   private final String PATH = "storage";

   public DocumentService() {
      File file = new File(PATH);
      if (!file.exists()){
         file.mkdir();
      }
   }

   
   
   void saveFile(MultipartFile file) throws IOException {

//      String extension = getExtension(file.getOriginalFilename());
      String newFileName = generateFilename();
      File savedFile = new File(PATH +"/"+ newFileName);
      savedFile.createNewFile();
      file.transferTo(savedFile);

   }

   private String generateFilename() {
      return Calendar.getInstance().getTimeInMillis() + '-' + RandomStringUtils.randomAlphanumeric(8);
   }

   private String getExtension(String filename) {
      String[] splittedName = filename.split(".");
      return splittedName[splittedName.length];
   }

}
