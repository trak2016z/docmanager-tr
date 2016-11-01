package pl.ksprzk.docmanager.persistence.user;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

   @Getter
   @Setter
   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   @Getter
   @Setter
   @NotNull
   private Timestamp dob;

   @Column(name = "fname")
   @Getter
   @Setter
   @NotNull
   @Size(max = 50)
   private String firstName;

   @Column(name = "mname")
   @Getter
   @Setter
   @Size(max = 50)
   private String middleName;

   @Column(name = "lname")
   @Getter
   @Setter
   @NotNull
   @Size(max = 50)
   private String lastName;

   @Column
   @Getter
   @Setter
   @NotNull
   @Size(max = 300)

   private String email;

   @Column
   @Getter
   @Setter
   @NotNull
   private char permission;

   @Column
   @Getter
   @Setter
   private String description;

   @Column
   @Getter
   @Setter
   @Size(max = 50)
   private String avatar;

   @Column
   @Getter
   @Setter
   private Boolean trusted;

   @Column
   @Getter
   @Setter
   @Size(max = 20)
   private String phone;

}
