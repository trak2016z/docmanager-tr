package pl.ksprzk.docmanager.integration.config;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.ksprzk.docmanager.App;

/**
 *
 * @author Przemek
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = App.class)
@Import({ServletConfig.class, DatabaseConfig.class})
@EnableCaching
public class MainConfig {

   @Bean
   CacheManager cacheManager() {
      GuavaCacheManager cacheManager = new GuavaCacheManager("docCache");
      CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100)
              .expireAfterWrite(10, TimeUnit.MINUTES);
      cacheManager.setCacheBuilder(cacheBuilder);
      return cacheManager;
   }

}
