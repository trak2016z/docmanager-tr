package pl.ksprzk.docmanager.persistence.browser;

import java.util.Set;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class GeneralBrowser {
   private BrowserQueryOptions queryOptions;
   private Set<String> querriedElems;

   public BrowserQueryOptions getQueryOptions() {
      return queryOptions;
   }

   public void setQueryOptions(BrowserQueryOptions queryOptions) {
      this.queryOptions = queryOptions;
   }

   public Set<String> getQuerriedElems() {
      return querriedElems;
   }

   public void setQuerriedElems(Set<String> querriedElems) {
      this.querriedElems = querriedElems;
   }
   
}
