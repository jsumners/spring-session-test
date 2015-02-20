package com.jrfom.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  private static final Logger log = LoggerFactory.getLogger(ApplicationInitializer.class);

  private final String SERVLET_NAME = "session-test";
  private final String PROD_PROFILE_NAME = "prod";

  @Override
  protected Class<?>[] getRootConfigClasses() {
    log.info("Getting root config classes");
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    log.info("Getting servlet config classes");
    return new Class<?>[] {
        ApplicationContextConfig.class
    };
  }

  @Override
  protected Filter[] getServletFilters() {
    log.info("Getting servlet filters");
    return null;
  }

  @Override
  protected String[] getServletMappings() {
    log.info("Getting servlet mappings");
    return new String[] { "/" };
  }

  @Override
  protected String getServletName() {
    return this.SERVLET_NAME;
  }

  @Override
  public void onStartup(ServletContext context) throws ServletException {
    super.onStartup(context);

    String activeProfile = System.getProperty("application.profile");
    if (activeProfile == null) {
      activeProfile = this.PROD_PROFILE_NAME;
    }

    context.setInitParameter("spring.profiles.active", activeProfile);
  }
}