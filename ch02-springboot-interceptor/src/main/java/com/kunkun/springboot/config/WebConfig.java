package com.kunkun.springboot.config;

import com.kunkun.springboot.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] path = {
        "/**"
    };

    //在拦截器的注册对象中奖拦截器注册进来,然后加入拦截器需要拦截的路径，也可以加入不拦截的路径
    registry.addInterceptor(new MyInterceptor()).addPathPatterns(path).excludePathPatterns("/jsp");
  }
}
