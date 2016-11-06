package pl.ksprzk.docmanager.persistence.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Przemek
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
   
   @Query("select u.id, u.firstName, u.permission, u.trusted, u.avatar from User u where u.email=?1 and u.password=?2")
   User findUserByEmailAndPassword (String email, String password);
}
