package pl.ksprzk.docmanager.integration.config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Przemek
 */
@Configuration
public class ServletConfig extends WebMvcConfigurerAdapter {

   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }
   
   @Bean
   MultipartResolver multipartResolver (){
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(20971520);
      return multipartResolver;
   }
   
   @Bean
   CharacterEncodingFilter characterEncodingFilter(){
      return new CharacterEncodingFilter("UTF-8", true);
   }
   
   @Bean
   public StringHttpMessageConverter stringHttpMessageConverter() {
       return new StringHttpMessageConverter(StandardCharsets.UTF_8);
   }
}
