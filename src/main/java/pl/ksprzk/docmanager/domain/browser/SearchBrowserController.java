package pl.ksprzk.docmanager.domain.browser;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Przemysław Kasprzyk
 */
@RestController
@RequestMapping(path = "/search")
public class SearchBrowserController {

   @Autowired
   SearchBrowserService service;

   @PostMapping(path = "/simple")
   public ResponseEntity searchDocuments(@RequestBody SimpleSearchRequest searchRequest, HttpServletRequest request) {
      return ResponseEntity.ok(service.searchData(searchRequest, request));
   }

}
