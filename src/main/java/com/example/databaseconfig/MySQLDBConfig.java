package com.example.databaseconfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * MySQL Database configuration
 * 
 * @author subrata
 *
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.repository", 
    entityManagerFactoryRef = "mysqlEntityManagerFactory", 
    transactionManagerRef = "mysqlTransactionManager"
)

public class MySQLDBConfig {
	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysqlDataSource());
		em.setPackagesToScan(new String[] { "com.example.domain" });

		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.mysql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect",	env.getProperty("spring.mysql.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource mysqlDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.mysql.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.mysql.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.mysql.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.mysql.datasource.password"));

		return dataSource;
	}

	@Bean
	public JpaTransactionManager mysqlTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mysqlEntityManagerFactory().getObject());
		return transactionManager;
	}
	
}
