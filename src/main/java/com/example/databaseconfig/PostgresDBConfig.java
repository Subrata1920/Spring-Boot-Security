package com.example.databaseconfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Postgres Database configuration
 * 
 * @author subrata
 *
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.repository", 
    entityManagerFactoryRef = "postgresEntityManagerFactory",  
    transactionManagerRef = "postgresTransactionManager"
)

public class PostgresDBConfig {
	@Autowired
	private Environment env;

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(postgresDataSource());
		em.setPackagesToScan(new String[] { "com.example.domain" });

		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.postgresql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect",	env.getProperty("spring.postgresql.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource postgresDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.postgresql.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.postgresql.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.postgresql.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.postgresql.datasource.password"));

		return dataSource;
	}

	@Primary
	@Bean
	public JpaTransactionManager postgresTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(postgresEntityManagerFactory().getObject());
		return transactionManager;
	}
	
}
