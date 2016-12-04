package pl.ksprzk.docmanager.integration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ksprzk.docmanager.domain.login.LoginRequestBody;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.exceptions.SecurityUninitializedException;
import pl.ksprzk.docmanager.integration.security.impl.GuavaCacheProvider;

/**
 *
 * @author Przemysław Kasprzyk
 */
@Component
public class Security {

   private final CacheProvider cacheProvider;
   private final DatabaseProvider databaseProvider;

   @Autowired
   public Security(DatabaseProvider databaseProvider) {
      this.cacheProvider = new GuavaCacheProvider();
      this.databaseProvider = databaseProvider;
   }

   public Boolean isValid(HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      return isValid(request, null);
   }

   public Boolean isValid(HttpServletRequest request, Character permission) throws SecurityUninitializedException, PermissionDeniedException {
      
      HttpSession session = request.getSession();
      String sessionId = session.getId();
      Credentials credentials = cacheProvider.getCredentials(sessionId);
      String auth = request.getHeader("auth_tkt");
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
   
   public Boolean validate (HttpServletRequest request, LoginRequestBody loginData) throws SecurityUninitializedException, NoSuchUserException, PermissionDeniedException{
       HttpSession session = request.getSession();
       String sessionId = session.getId();
       Credentials credentials = databaseProvider.provideCredentials(loginData.getEmail(), loginData.getPassword());
       credentials.setSessionId(sessionId);
       String authTkt = Generators.generateAuthTk();
       credentials.setAuthTk(authTkt);
       session.setAttribute("auth_tkt", authTkt);
       session.setAttribute("user", credentials.getUsername());
       return isValid(request);
   }

}
