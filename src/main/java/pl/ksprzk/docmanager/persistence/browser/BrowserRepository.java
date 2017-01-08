package pl.ksprzk.docmanager.persistence.browser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.ksprzk.docmanager.integration.persistence.BaseRepository;
import pl.ksprzk.docmanager.integration.persistence.Page;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Repository
class BrowserRepository extends BaseRepository {

   @Autowired
   BrowserRepository(EntityManagerFactory entityManagerFactory) {
      super(entityManagerFactory);
   }

   private Criterion createCriterion(boolean publicNeeded, String[] criteriaStrings) {
      Set<Criterion> criterions = new HashSet<>();
      criterions.add(containsOneOrNone("firstName", criteriaStrings));
      criterions.add(containsOneOrNone("lastName", criteriaStrings));
      criterions.add(containsOneOrNone("name", criteriaStrings));
      criterions.add(containsOneOrNone("keywords", criteriaStrings));

      Criterion result = Restrictions.or(criterions.toArray(new Criterion[criteriaStrings.length]));
      if (publicNeeded) {
         result = Restrictions.and(Restrictions.eq("publicDocument", publicNeeded), result);
      }
      return result;
   }

   SearchBrowserData getRecords(boolean publicNeeded, String[] criteriaStrings, int pageNumber, int pageSize) {
      Session session = getSession();
      Criteria criteria = session.createCriteria(SearchBrowser.class);
      criteria.add(createCriterion(publicNeeded, criteriaStrings));
      Projection projection = Projections.rowCount();
      criteria.setProjection(projection);
      long records = (long) criteria.uniqueResult();
      criteria.setProjection(null);
      criteria.addOrder(Order.desc("uploaded"));
      criteria.setFirstResult((pageNumber - 1) * pageSize + 1);
      criteria.setMaxResults(pageNumber * pageSize);
      List<SearchBrowser> searchList = criteria.list();
      Page page= new Page();
      page.setNumber(pageNumber);
      page.setSize(pageSize);
      page.setTotal(records);
      SearchBrowserData data = new SearchBrowserData();
      data.setData(searchList);
      data.setPage(page);
      return data;
   }
}
