package pl.ksprzk.docmanager.integration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.exceptions.SecurityUninitializedException;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class Security {

   private CacheProvider cacheProvider;
   private DatabaseProvider databaseProvider;

   private static Security instance;

   protected Security(CacheProvider cacheProvider, DatabaseProvider databaseProvider) {
      this.cacheProvider = cacheProvider;
      this.databaseProvider = databaseProvider;
   }

   public static Security getInstance(CacheProvider cacheProvider, DatabaseProvider databaseProvider) {
      if (instance == null) {
         instance = new Security(cacheProvider, databaseProvider);
      }
      return instance;
   }

   public static Security getInstance() throws SecurityUninitializedException {
      if (instance == null) {
         throw new SecurityUninitializedException();
      }
      return instance;
   }

   public static Boolean isValid(HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      return isValid(request, null);
   }

   public static Boolean isValid(HttpServletRequest request, Character permission) throws SecurityUninitializedException, PermissionDeniedException {
      Security securityImplementation = Security.getInstance();
      HttpSession session = request.getSession();
      String sessionId = session.getId();
      Credentials credentials = securityImplementation.cacheProvider.getCredentials(sessionId);
      String auth = request.getHeader("auth_tk");
      boolean result = false;
      if (credentials != null && StringUtils.isNotEmpty(auth)) {
         boolean authResult = auth.equals(credentials.getAuthTk());
         if (permission == null) {
            result = authResult;
         } else {
            result = authResult && permission.equals(credentials.getPermission());
         }
      } else {
         result = false;
      }
      if (!result){
         throw new PermissionDeniedException();
      }
      return result;
   }

}
