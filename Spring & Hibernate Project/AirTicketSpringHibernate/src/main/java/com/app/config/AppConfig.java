package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.app")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource getDBConnection(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/airticketbooking");
        dataSource.setUsername("root");
        dataSource.setPassword("Mukesh@82328");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean entityManager=new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.app.model");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        entityManager.setJpaProperties(properties);

        return entityManager;

    }
    @Bean
    public JpaTransactionManager jpaTransactionManager(LocalContainerEntityManagerFactoryBean emf){
        JpaTransactionManager jp=new JpaTransactionManager();
        jp.setEntityManagerFactory(emf.getObject());
        return jp;
    }

}
