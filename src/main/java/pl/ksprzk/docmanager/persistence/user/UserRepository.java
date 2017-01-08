package pl.ksprzk.docmanager.persistence.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Przemek
 */
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
  
   User findUserByEmail (String email);
   
}
