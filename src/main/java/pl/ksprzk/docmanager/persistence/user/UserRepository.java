package pl.ksprzk.docmanager.persistence.user;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Przemek
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
  
   User findUserByEmail (String email);
}
