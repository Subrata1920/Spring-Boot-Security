package com.example.databaseconfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

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
    transactionManagerRef = "transactionManager"
)
@DependsOn("transactionManager")
public class PostgresDBConfig {
	
	@Autowired
	private JpaVendorAdapter jpaVendorAdapterPostgres;
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "postgresDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource postgresDataSource() {
		PGXADataSource pgxaDataSource = new PGXADataSource();
		pgxaDataSource.setUrl(env.getProperty("spring.postgresql.datasource.url"));
		pgxaDataSource.setPassword(env.getProperty("spring.postgresql.datasource.password"));
		pgxaDataSource.setUser(env.getProperty("spring.postgresql.datasource.username"));

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(pgxaDataSource);
		xaDataSource.setUniqueResourceName("xads2");
		xaDataSource.setPoolSize(30);
		return xaDataSource;
	}
	
	@Bean(name = "postgresEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean postgresEntityManager() {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.postgresql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect",	env.getProperty("spring.postgresql.jpa.properties.hibernate.dialect"));

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(postgresDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapterPostgres);
		entityManager.setPackagesToScan("com.example.domain");
		entityManager.setPersistenceUnitName("postgresPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
	
}
