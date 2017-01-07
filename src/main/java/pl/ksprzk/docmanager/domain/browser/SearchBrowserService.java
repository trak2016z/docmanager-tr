package pl.ksprzk.docmanager.domain.browser;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.security.Security;
import pl.ksprzk.docmanager.persistence.browser.SearchBrowserData;
import pl.ksprzk.docmanager.persistence.browser.SearchBrowserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class SearchBrowserService {

   @Autowired
   SearchBrowserPersistenceService service;

   BrowserData searchData (SimpleSearchRequest criteria, HttpServletRequest request){
      String[] splittedCriteria = criteria.getCriteria().split(" ");
      boolean logged = Security.getInstance().isLogged(request);
      SearchBrowserData data = service.getSearchPage(!logged, splittedCriteria, criteria.getPage().getNumber(), criteria.getPage().getSize());
      return BrowserFactory.getDomainData(data);
   }
}
