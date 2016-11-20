package pl.ksprzk.docmanager.integration.security;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class Generators {

   public static String generateAuthTk() {
      return RandomStringUtils.randomAlphanumeric(128);
   }

   public String generateSHA512(String s) {
      HashFunction hash = Hashing.sha512();
      HashCode code = hash.hashString(s, Charsets.UTF_8);
      return code.toString();
   }
}
