package com.kunkun.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MyInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    //业务的处理就省略了，我们主要是配置拦截器

    System.out.println("进入了拦截器。。。。。");
    //false表示不进controller，true表示拦截器拦截完成还是会进入controller
    return false;
  }
}
