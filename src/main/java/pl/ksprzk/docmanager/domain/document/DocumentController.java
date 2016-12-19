package pl.ksprzk.docmanager.domain.document;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.exceptions.SecurityUninitializedException;
import pl.ksprzk.docmanager.integration.security.Security;

/**
 *
 * @author Przemysław Kasprzyk
 */
@RestController
@RequestMapping(path = "/document")
public class DocumentController {

   @Autowired
   DocumentService service;

   @RequestMapping(path = "/fileUpload")
   public ResponseEntity uploadFile(@RequestParam("data") String data, @RequestParam("upload_file") MultipartFile file, HttpServletRequest request) throws IOException, SecurityUninitializedException, PermissionDeniedException {
      Security.getInstance().isValid(request);
      service.saveFile(file, data, Security.getInstance().getCredentials(request));
      return ResponseEntity.ok().build();
   }
   
   @RequestMapping(path = "/fileDownload/{id}")
   public void downloadFile (@PathVariable("id")Integer id, HttpServletRequest request, HttpServletResponse response) throws SecurityUninitializedException, PermissionDeniedException, FileNotFoundException, IOException{
      Security.getInstance().isValid(request);
      service.downloadFile(response, id);
   }
   
}
