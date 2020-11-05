package com.kunkun.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan(basePackages = "com.kunkun.springboot.filter")
public class Ch03SpringbootFilterApplication {

  public static void main(String[] args) {
    SpringApplication.run(Ch03SpringbootFilterApplication.class, args);
  }

}
