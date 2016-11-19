package pl.ksprzk.docmanager.domain.login;

import java.sql.Timestamp;
import pl.ksprzk.docmanager.persistence.user.User;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class UserFactory {

   public static User createEntity(UserDTO dto) {
      User entity = new User();
      entity.setEmail(dto.getEmail());
      entity.setFirstName(dto.getFirstName());
      entity.setLastName(dto.getLastName());
      entity.setMiddleName(dto.getMiddleName());
      entity.setDob(new Timestamp(dto.getDateOfBirth().getTimeInMillis()));
      entity.setPhone(dto.getPhone());
      entity.setPassword(dto.getPassword());
      return entity;
   }
}
