package pl.ksprzk.docmanager.persistence.browser;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.ksprzk.docmanager.integration.persistence.BaseRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Repository
class BrowserRepostory extends BaseRepository {

   @Autowired
   public BrowserRepostory(EntityManagerFactory entityManagerFactory) {
      super(entityManagerFactory);
   }

   List<BrowserResponse> findByQuery(GeneralBrowser queryOptions) {
      EntityManager em = getEntityManager();
      Query query = em.createNativeQuery("", BrowserResponse.class);
//      query.

      return query.getResultList();
   }
   
   private String createQueryString(GeneralBrowser queryOptions) {
      
      return "";
   }

   private final String SELECT = "select d.name, d.uploaded, d.avatar, d.document_id, u.fname, u.lname from document d, count(*) as rownum "
           + "left join user u on u.user_id = d.fk_user "
           + "left join (select avg(r.rate_value) as avgrate, r.document_fk as docid from rate r group by r.document_fk) cr on cr.docid = d.document_id";
   
   private String generateLimit (BrowserQueryOptions options){
      return "";
   }

}
