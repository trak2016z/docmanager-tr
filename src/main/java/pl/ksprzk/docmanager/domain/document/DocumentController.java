package pl.ksprzk.docmanager.domain.document;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
   public ResponseEntity uploadFile(@RequestParam("upload_file") MultipartFile file, HttpServletRequest request) throws IOException, SecurityUninitializedException, PermissionDeniedException {
      Security.getInstance().isValid(request);
      return ResponseEntity.ok(service.saveFile(file));
   }

   @PostMapping(path = "/updateFileData")
   public ResponseEntity updateFileData(@RequestBody DocumentData data, HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      Security.getInstance().isValid(request);
      service.saveFileDataToDatabase(data, Security.getInstance().getCredentials(request));
      return ResponseEntity.ok().build();
   }

   //TODO: Poprawić
   @RequestMapping(path = "/fileDownload/{id}")
   public void downloadFile(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) throws SecurityUninitializedException, PermissionDeniedException, FileNotFoundException, IOException {
      Security.getInstance().isValid(request);
      service.downloadFile(response, id);
   }

   //TODO: ZABEZPIECZYĆ
   @RequestMapping(path = "/avatar/{id}")
   public void downloadAvatar(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) throws SecurityUninitializedException, PermissionDeniedException, FileNotFoundException, IOException {

      service.downloadAvatar(response, id);
   }

   @PostMapping(path = "/myDocuments")
   public ResponseEntity getAllUserPublications(@RequestBody DocumentOwner user, HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      Security security = Security.getInstance();
      security.isValid(request);
      security.isAuthorizedOwner(request, user.getUser());
      return ResponseEntity.ok(service.getUserPublications(user));
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity deleteDocument(@PathVariable(name = "id") int documentId, HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      Security security = Security.getInstance();
      security.isValid(request);
      security.isAuthorizedOwner(request);
      service.deleteDocument(documentId);
      return ResponseEntity.ok().build();
   }

   //TODO: Zabezpieczyć
   @GetMapping(path = "/{id}")
   public ResponseEntity getDocument(@PathVariable(name = "id") int documentId, HttpServletRequest request) {
      return ResponseEntity.ok(service.getDocumentById(documentId));
   }

}
