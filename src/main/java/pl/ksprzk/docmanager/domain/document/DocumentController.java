package pl.ksprzk.docmanager.domain.document;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
   public ResponseEntity uploadFile (@RequestParam("upload_file")MultipartFile file) throws IOException{
      service.saveFile(file);
      return ResponseEntity.ok().build();
   }
}
