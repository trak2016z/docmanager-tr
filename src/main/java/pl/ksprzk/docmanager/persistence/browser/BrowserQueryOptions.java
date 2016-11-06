package pl.ksprzk.docmanager.persistence.browser;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class BrowserQueryOptions {

   private Boolean publicFlag;
   private Integer pageSize;
   private Integer pageNumber;

   public Boolean getPublicFlag() {
      return publicFlag;
   }

   public void setPublicFlag(Boolean publicFlag) {
      this.publicFlag = publicFlag;
   }

   public Integer getPageSize() {
      return pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public Integer getPageNumber() {
      return pageNumber;
   }

   public void setPageNumber(Integer pageNumber) {
      this.pageNumber = pageNumber;
   }
   
}
