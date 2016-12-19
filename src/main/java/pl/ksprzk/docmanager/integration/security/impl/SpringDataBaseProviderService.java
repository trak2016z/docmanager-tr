package pl.ksprzk.docmanager.integration.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ksprzk.docmanager.integration.security.LoginUserData;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.security.Credentials;
import pl.ksprzk.docmanager.integration.security.DatabaseProvider;
import pl.ksprzk.docmanager.integration.security.Generators;
import pl.ksprzk.docmanager.persistence.user.UserPersistenceService;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Service
public class SpringDataBaseProviderService implements DatabaseProvider {

   @Autowired
   UserPersistenceService service;

   @Override
   public Credentials provideCredentials(String username, String password) throws NoSuchUserException, PermissionDeniedException {
      LoginUserData data = service.provideLogonData(username, password);
      Credentials credentials = new Credentials();
      credentials.setPassword(password);
      credentials.setUsername(username);
      credentials.setPermission(data.getPermission());
      credentials.setAuthTk(Generators.generateAuthTk());
      credentials.setName(data.getName());
      return credentials;
   }

}
