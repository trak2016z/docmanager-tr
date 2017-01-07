package pl.ksprzk.docmanager.domain.browser;

import pl.ksprzk.docmanager.integration.persistence.Page;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class SimpleSearchRequest {
   private Page page;
   private String criteria;

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public String getCriteria() {
      return criteria;
   }

   public void setCriteria(String criteria) {
      this.criteria = criteria;
   }
   
}
