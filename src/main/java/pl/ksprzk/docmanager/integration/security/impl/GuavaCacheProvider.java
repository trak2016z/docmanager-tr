package pl.ksprzk.docmanager.integration.security.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import pl.ksprzk.docmanager.integration.security.CacheProvider;
import pl.ksprzk.docmanager.integration.security.Credentials;

/**
 *
 * @author Przemysław Kasprzyk
 */
public class GuavaCacheProvider implements CacheProvider {

   private final Cache<String, Credentials> cache;

   public GuavaCacheProvider() {
      cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(30, TimeUnit.MINUTES).build();
   }
   
   @Override
   public void insertCredentials(Credentials c) {
      this.cache.put(c.getSessionId(), c);
   }

   @Override
   public Credentials getCredentials(String session) {
      return cache.getIfPresent(session);
   }

}
