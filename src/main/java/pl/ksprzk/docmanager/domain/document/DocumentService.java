package pl.ksprzk.docmanager.domain.document;

import com.fasterxml.jackson.databind.ObjectMapper;
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
   private final ObjectMapper mapper;

   public DocumentService() {
      File file = new File(PATH);
      if (!file.exists()) {
         file.mkdir();
      }
      mapper = new ObjectMapper();
   }

   void saveFile(MultipartFile file, String strigifiedData) throws IOException {

      String extension = getExtension(file.getOriginalFilename());
      DocumentData data = mapper.readValue(strigifiedData, DocumentData.class);
      String newFileName = generateFilename();
      File savedFile = new File(PATH + "/" + newFileName);
      savedFile.createNewFile();
      file.transferTo(savedFile);

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
