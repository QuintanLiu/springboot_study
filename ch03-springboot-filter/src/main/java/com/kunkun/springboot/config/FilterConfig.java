package com.kunkun.springboot.config;

import com.kunkun.springboot.filter.HeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean registrationBean(){
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HeFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    return filterRegistrationBean;
  }
}
