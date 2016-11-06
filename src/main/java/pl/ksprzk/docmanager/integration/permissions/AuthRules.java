package pl.ksprzk.docmanager.integration.permissions;

import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class AuthRules {

   public static boolean isEligibleToLogin(char permission) {
      switch (permission) {
         case Priviledge.ACTIVE:
         case Priviledge.ADMIN:
         case Priviledge.MODERATOR:
            return true;
         default:
            return false;
      }
   }
   
   public static boolean isEligibleToLogin(User user) {
      return isEligibleToLogin(user.getPermission());
   }
}
