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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactoryPrimary",
    transactionManagerRef = "transactionManagerPrimary",
    basePackages = {"com.kunkun.jpa.primary"}    //需要修改的地方
)
public class PrimaryConfig {
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Autowired
  @Qualifier("primaryDataSource")
  private DataSource primaryDataSource;

  @Primary
  @Bean(name = "entityManagerPrimary")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
  }

  @Primary
  @Bean(name = "entityManagerFactoryPrimary")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(primaryDataSource)
        .properties(getVendorProperties())
        .packages("com.kunkun.jpa.model.primary")         //设置实体类所在位置与副数据源区分
        .persistenceUnit("primaryPersistenceUnit")
        .build();
  }

  private Map getVendorProperties() {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.dialect",
        env.getProperty("hibernate.dialect"));
    properties.put("hibernate.ddl-auto",
        "create");
    properties.put("hibernate.physical_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    properties.put("hibernate.implicit_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
    return properties;
  }

  @Autowired
  private Environment env;

  @Primary
  @Bean(name = "transactionManagerPrimary")
  public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
  }

}
