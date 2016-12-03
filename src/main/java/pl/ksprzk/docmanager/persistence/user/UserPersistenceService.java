package pl.ksprzk.docmanager.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.domain.login.LoginUserData;
import pl.ksprzk.docmanager.integration.exceptions.AlreadyExistException;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.permissions.AuthRules;

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

   public Iterable<User> listAllUsers() {
      return repository.findAll();
   }

   @Cacheable
   public LoginUserData provideLogonData(String email, String password) throws NoSuchUserException, PermissionDeniedException {
      User user = repository.findUserByEmailAndPassword(email, password);
      if (user == null) {
         throw new NoSuchUserException();
      } else if (AuthRules.isEligibleToLogin(user)) {
         throw new PermissionDeniedException();
      } else {
         LoginUserData data = new LoginUserData.LoginUserDataBuilder(user.getId(), user.getFirstName(), user.getPermission(),
                 user.getTrusted()).avatar(user.getAvatar()).build();
         return data;
      }
   }
   
   public Boolean userExist(String email){
      User u = repository.findUserByEmail(email);
      return u!=null;
   }
   
   public void registerUser (User u) throws AlreadyExistException{
      User dbUser = this.repository.findUserByEmail(u.getEmail());
      if (dbUser != null){
         throw new AlreadyExistException();
      }
      else {
         this.repository.save(u);
      }
   }

}
