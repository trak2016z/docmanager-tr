package pl.ksprzk.docmanager.persistence.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class SearchBrowserPersistenceService {
   
   private BrowserRepository repository;

   @Autowired
   public SearchBrowserPersistenceService(BrowserRepository repository) {
      this.repository = repository;
   }

   public SearchBrowserData getSearchPage (boolean publicNeeded, String[] criteria, int page, int pageSize){
      return repository.getRecords(publicNeeded, criteria, page, pageSize);
   }
}
