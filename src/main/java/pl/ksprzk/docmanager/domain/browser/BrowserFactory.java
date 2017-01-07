package pl.ksprzk.docmanager.domain.browser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pl.ksprzk.docmanager.persistence.browser.SearchBrowser;
import pl.ksprzk.docmanager.persistence.browser.SearchBrowserData;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class BrowserFactory {

   public static BrowserData getDomainData(SearchBrowserData data){
      BrowserData result = new BrowserData();
      result.setPage(data.getPage());
      result.setDataContainer(getDomainContainers(data.getData()));
      return result;
   }
   
   public static BrowserDataContainer getDomainContainer (SearchBrowser browser){
      BrowserDataContainer result = new BrowserDataContainer();
      result.setAvatar(browser.getAvatar());
      result.setFirstName(browser.getFirstName());
      result.setId(browser.getId());
      result.setLastName(browser.getLastName());
      Calendar modified = Calendar.getInstance();
      modified.setTimeInMillis(browser.getUploaded().getTime());
      result.setModified(modified);
      result.setName(browser.getName());
      result.setRate(browser.getAverageRate());
      return result;
   }
   
   public static List<BrowserDataContainer> getDomainContainers (List<SearchBrowser> browser){
      List<BrowserDataContainer> containerList = new ArrayList<>(browser.size());
      for(SearchBrowser b : browser){
         containerList.add(getDomainContainer(b));
      }
      return containerList;
   }
}
