package pl.ksprzk.docmanager.integration.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Przemysław Kasprzyk
 */
public abstract class BaseRepository {

   private final EntityManagerFactory entityManagerFactory;

   @Autowired
   public BaseRepository(EntityManagerFactory entityManagerFactory) {
      this.entityManagerFactory = entityManagerFactory;
   }

   protected EntityManager getEntityManager() {
      return entityManagerFactory.createEntityManager();
   }

   protected Session getSession() {
      EntityManager em = getEntityManager();
      return em.unwrap(Session.class);
   }

   protected Criterion containsOne(String property, String[] values) {
      Criterion result = null;
      for (String o : values) {
         if (result == null) {
            result = Restrictions.ilike(property, '%' + o + '%');
         } else {
            result = Restrictions.or(result, Restrictions.ilike(property, o));
         }
      }
      return result;
   }

   protected Criterion containsOneOrNone(String property, String[] values) {
      Criterion result = containsOne(property, values);
      if (result == null) {
         return Restrictions.isNull(property);
      } else {
         return Restrictions.or(Restrictions.isNull(property), result);
      }
   }
}
