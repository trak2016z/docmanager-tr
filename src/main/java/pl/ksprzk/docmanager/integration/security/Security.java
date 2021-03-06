package pl.ksprzk.docmanager.integration.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
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
   private final Map<String, Credentials> authMap;

   private Security() {
      this.authMap = new HashMap<>();
   }

   public static Security getInstance() {
      if (instance == null) {
         instance = new Security();
      }
      return instance;
   }

   public void isAuthorizedOwner(HttpServletRequest request) throws PermissionDeniedException {
      String user = Arrays.stream(request.getCookies()).filter((Cookie c) -> {
         return c.getName().equalsIgnoreCase("user");
      }).findFirst().get().getValue();
      isAuthorizedOwner(request, user);
   }

   public void isAuthorizedOwner(HttpServletRequest request, String user) throws PermissionDeniedException {
      HttpSession session = request.getSession();
      Credentials credentials = authMap.get(session.getId());
      if (!credentials.getUsername().equals(user)) {
         throw new PermissionDeniedException();
      }
   }

   public Boolean isValid(HttpServletRequest request) throws SecurityUninitializedException, PermissionDeniedException {
      return isValid(request, null);
   }

   public Credentials getCredentials(HttpServletRequest request) {
      String sessionId = request.getSession().getId();
      return authMap.get(sessionId);
   }

   public boolean isLogged(HttpServletRequest request) {
      HttpSession session = request.getSession();
      String sessionId = session.getId();
      Credentials credentials = authMap.get(sessionId);
      String auth = (String) session.getAttribute("auth_tkt");
      boolean result = false;
      if (credentials != null && StringUtils.isNotEmpty(auth)) {
         result = auth.equals(credentials.getAuthTk());
      } else {
         result = false;
      }
      return result;
   }

   public Boolean isValid(HttpServletRequest request, Character permission) throws SecurityUninitializedException, PermissionDeniedException {

      HttpSession session = request.getSession();
      String sessionId = session.getId();
      Credentials credentials = authMap.get(sessionId);
      String auth = (String) session.getAttribute("auth_tkt");
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
      if (!result) {
         throw new PermissionDeniedException();
      }
      return result;
   }

   public LoginResponse validate(HttpServletRequest request, Credentials credentials) throws SecurityUninitializedException, NoSuchUserException, PermissionDeniedException {
      HttpSession session = request.getSession();
      String sessionId = session.getId();
      credentials.setSessionId(sessionId);
      String authTkt = Generators.generateAuthTk();
      credentials.setAuthTk(authTkt);
      session.setAttribute("auth_tkt", authTkt);
      session.setAttribute("user", credentials.getUsername());
      session.setAttribute("permission", credentials.getPermission());
      this.authMap.put(sessionId, credentials);
      isValid(request);
      return new LoginResponse(credentials);
   }
   
   public void logoff(HttpServletRequest request){
      HttpSession session = request.getSession();
      this.authMap.remove(session.getId());
   }

}
