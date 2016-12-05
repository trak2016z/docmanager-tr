package pl.ksprzk.docmanager.integration.config;

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
public class MainConfig {


}
