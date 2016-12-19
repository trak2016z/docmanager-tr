package pl.ksprzk.docmanager.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.security.LoginUserData;
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

   public User getUser(String email) {
      return repository.findUserByEmail(email);
   }

   public Iterable<User> listAllUsers() {
      return repository.findAll();
   }

   public LoginUserData provideLogonData(String email, String password) throws NoSuchUserException, PermissionDeniedException {
      User user = repository.findUserByEmail(email);
      if (user == null || !user.getPassword().equals(password)) {
         throw new NoSuchUserException();
      } else if (!AuthRules.isEligibleToLogin(user)) {
         throw new PermissionDeniedException();
      } else {
         LoginUserData data = new LoginUserData.LoginUserDataBuilder(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getPermission(),
                 user.getTrusted()).avatar(user.getAvatar()).build();
         return data;
      }
   }

   public Boolean userExist(String email) {
      User u = repository.findUserByEmail(email);
      return u != null;
   }

   public void registerUser(User u) throws AlreadyExistException {
      User dbUser = this.repository.findUserByEmail(u.getEmail());
      if (dbUser != null) {
         throw new AlreadyExistException();
      } else {
         this.repository.save(u);
      }
   }

   public void resetPassword(String email, String newPassword) throws NoSuchUserException {
      User dbUser = this.repository.findUserByEmail(email);
      if (dbUser == null) {
         throw new NoSuchUserException();
      } else {
         dbUser.setPassword(newPassword);
         this.repository.save(dbUser);
      }
   }

}
