package pl.ksprzk.docmanager.integration.security;

import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;

/**
 *
 * @author Przemysław Kasprzyk
 */
public interface DatabaseProvider {

   Credentials provideCredentials(String username, String password) throws NoSuchUserException, PermissionDeniedException;

}
