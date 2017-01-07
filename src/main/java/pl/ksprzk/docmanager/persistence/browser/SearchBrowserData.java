package pl.ksprzk.docmanager.persistence.browser;

import java.util.List;
import pl.ksprzk.docmanager.integration.persistence.Page;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class SearchBrowserData {

   private Page page;
   private List<SearchBrowser> data;

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public void setData(List<SearchBrowser> data) {
      this.data = data;
   }

   public List<SearchBrowser> getData() {
      return data;
   }

}
