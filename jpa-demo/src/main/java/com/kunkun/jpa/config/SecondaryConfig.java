package com.kunkun.jpa.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactorySecondary",
    transactionManagerRef = "transactionManagerSecondary",
    basePackages = {"com.kunkun.jpa.secondary"}) //设置DAO接口层所在包位置与主数据源区分
public class SecondaryConfig {

  @Autowired
  @Qualifier("secondaryDataSource")
  private DataSource secondaryDataSource;

  @Bean(name = "entityManagerSecondary")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactorySecondary(builder).getObject().createEntityManager();
  }

  @Bean(name = "entityManagerFactorySecondary")
  public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(secondaryDataSource)
        .properties(getVendorProperties())
        .packages("com.kunkun.jpa.model.secondary")      //设置实体类所在包的位置与主数据源区分
        .persistenceUnit("primaryPersistenceUnit")
        .build();
  }

  private Map getVendorProperties() {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto",
        env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.ddl-auto",
        env.getProperty("update"));
    properties.put("hibernate.dialect",
        env.getProperty("hibernate.dialect"));
    properties.put("hibernate.physical_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    properties.put("hibernate.implicit_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
    return properties;
  }

  @Autowired
  private Environment env;

  @Bean(name = "transactionManagerSecondary")
  PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
  }
}