package pl.ksprzk.docmanager.integration.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
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
   
   protected EntityManager getEntityManager (){
      return entityManagerFactory.createEntityManager();
   }
   
   protected Session getSession (){
      EntityManager em = getEntityManager();
      return em.unwrap(Session.class);
   }
}
