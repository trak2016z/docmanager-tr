package pl.ksprzk.docmanager.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemek
 */
@Service
public class UserPersistenceService {

   private final UserRepository repository;

   @Autowired
   public UserPersistenceService(UserRepository repository) {
      this.repository = repository;
   }

   public Iterable<User> listAllUsers (){
      return repository.findAll();
   }
}
