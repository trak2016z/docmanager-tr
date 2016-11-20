package pl.ksprzk.docmanager.integration.security;

/**
 *
 * @author Przemysław Kasprzyk
 */
public interface CacheProvider {
   void insertCredentials (Credentials c);
   Credentials getCredentials(String session);
}
