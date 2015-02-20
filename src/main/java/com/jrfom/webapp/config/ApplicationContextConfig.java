package com.jrfom.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan (
  basePackages = (
    "com.jrfom"
  )
)
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {
  private static final Logger log = LoggerFactory.getLogger(ApplicationContextConfig.class);

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Disable caching for a specific application path.
    /*WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
    webContentInterceptor.setUseCacheControlHeader(true);
    webContentInterceptor.setCacheSeconds(0);
    webContentInterceptor.setUseCacheControlNoStore(true);
    webContentInterceptor.setUseCacheControlHeader(true);
    registry.addInterceptor(webContentInterceptor)
        .addPathPatterns("/secured/**");*/

    super.addInterceptors(registry);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("local/**")
        .addResourceLocations("/WEB-INF/assets/local/");

    // A jQuery example
//    registry
//        .addResourceHandler("jquery/**")
//        .addResourceLocations("/WEB-INF/assets/jquery-2.0.3/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    super.addViewControllers(registry);
  }
}