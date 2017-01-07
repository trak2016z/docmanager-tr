package pl.ksprzk.docmanager.domain.browser;

import java.util.List;
import pl.ksprzk.docmanager.integration.persistence.Page;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class BrowserData {

   private Page page;
   private List<BrowserDataContainer> dataContainer;

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public List<BrowserDataContainer> getDataContainer() {
      return dataContainer;
   }

   public void setDataContainer(List<BrowserDataContainer> dataContainer) {
      this.dataContainer = dataContainer;
   }
   
}
