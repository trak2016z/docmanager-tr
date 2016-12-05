package pl.ksprzk.docmanager.integration.security;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import pl.ksprzk.docmanager.integration.exceptions.NoSuchUserException;
import pl.ksprzk.docmanager.integration.exceptions.PermissionDeniedException;
import pl.ksprzk.docmanager.integration.exceptions.SecurityUninitializedException;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class Security {

   private static Security instance;
   private Map<String, Credentials> authMap;

   private Security() {
      this.authMap = new HashMap<>();
   }
   public static Security getInstance (){
      if (instance == null){
         instance = new Security();
      }
      return instance;
   }  
   
   public Boolean isValid(HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      return isValid(request, null);
   }

   public Boolean isValid(HttpServletRequest request, Character permission) throws SecurityUninitializedException, PermissionDeniedException {
      
      HttpSession session = request.getSession();
      String sessionId = session.getId();
      Credentials credentials = authMap.get(sessionId);
      String auth = (String)session.getAttribute("auth_tkt");
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
   
   public Boolean validate (HttpServletRequest request, Credentials credentials) throws SecurityUninitializedException, NoSuchUserException, PermissionDeniedException{
       HttpSession session = request.getSession();
       String sessionId = session.getId();
       credentials.setSessionId(sessionId);
       String authTkt = Generators.generateAuthTk();
       credentials.setAuthTk(authTkt);
       session.setAttribute("auth_tkt", authTkt);
       session.setAttribute("user", credentials.getUsername());
       this.authMap.put(sessionId, credentials);
       return isValid(request);
   }

}
