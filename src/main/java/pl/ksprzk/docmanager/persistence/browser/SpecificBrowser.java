package pl.ksprzk.docmanager.persistence.browser;

import java.sql.Timestamp;
import java.util.Set;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class SpecificBrowser {
   private String name;
   private String authorFirstName;
   private String authorLastName;
   private Timestamp dateSince;
   private Timestamp dateTo;
   private Set<String> keywords;
   private Float minimumRate;
   private BrowserQueryOptions queryOptions;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAuthorFirstName() {
      return authorFirstName;
   }

   public void setAuthorFirstName(String authorFirstName) {
      this.authorFirstName = authorFirstName;
   }

   public String getAuthorLastName() {
      return authorLastName;
   }

   public void setAuthorLastName(String authorLastName) {
      this.authorLastName = authorLastName;
   }

   public Timestamp getDateSince() {
      return dateSince;
   }

   public void setDateSince(Timestamp dateSince) {
      this.dateSince = dateSince;
   }

   public Timestamp getDateTo() {
      return dateTo;
   }

   public void setDateTo(Timestamp dateTo) {
      this.dateTo = dateTo;
   }

   public Set<String> getKeywords() {
      return keywords;
   }

   public void setKeywords(Set<String> keywords) {
      this.keywords = keywords;
   }

   public Float getMinimumRate() {
      return minimumRate;
   }

   public void setMinimumRate(Float minimumRate) {
      this.minimumRate = minimumRate;
   }

   public BrowserQueryOptions getQueryOptions() {
      return queryOptions;
   }

   public void setQueryOptions(BrowserQueryOptions queryOptions) {
      this.queryOptions = queryOptions;
   }

   
}
