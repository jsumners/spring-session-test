package com.jrfom.webapp.config;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@PropertySources({
  @PropertySource("classpath:application-${spring.profiles.active}.properties")
})
public class ApplicationContextBeans {
  @Autowired
  private Environment env;

  private final String DEV_PROFILE_NAME = "dev";

  @Bean
  public ServletContextTemplateResolver templateResolver() {
    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
    resolver.setPrefix("/WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");

    if (this.isDevEnvironment()) {
      resolver.setCacheable(false);
    }

    return resolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(this.templateResolver());
    engine.addDialect(new DataAttributeDialect());

    return engine;
  }

  @Bean
  public ThymeleafViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(this.templateEngine());

    return viewResolver;
  }

  @Bean
  public Boolean isDevEnvironment() {
    Boolean result = false;

    String[] activeProfiles = this.env.getActiveProfiles();
    for (String profile : activeProfiles) {
      if (profile.equals(this.DEV_PROFILE_NAME)) {
        result = true;
        break;
      }
    }

    return result;
  }
}