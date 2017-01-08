package pl.ksprzk.docmanager.integration.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.ksprzk.docmanager.persistence.Persistence;

/**
 *
 * @author Przemek
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = Persistence.class)
@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement
public class DatabaseConfig {

   @Autowired
   Environment environment;

   @Bean
   DataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setDriverClassName("com.mysql.jdbc.Driver");
      config.setJdbcUrl("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7142486");
      config.setMaximumPoolSize(1);
      config.setUsername("sql7142486");
      config.setPassword("NmTDzEDFL1");
      config.setAutoCommit(true);
      HikariDataSource dataSource = new HikariDataSource(config);
      return dataSource;
   }

   @Bean
   LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
      LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
      entityManager.setDataSource(dataSource);
      entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      entityManager.setPackagesToScan("pl.ksprzk.docmanager.persistence");
      entityManager.setJpaProperties(getHibernateProperties());
      return entityManager;

   }

   private Properties getHibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      properties.put("hibernate.show_sql", true);
      properties.put("hibernate.format_sql", true);
      properties.put("hibernate.enable_lazy_load_no_trans", true);
      return properties;
   }

   @Bean
   PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(
              entityManagerFactoryBean.getObject());
      return transactionManager;
   }
}
