package com.example.databaseconfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * MySQL Database configuration
 * 
 * @author subrata
 *
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.mysqlrepository", 
    entityManagerFactoryRef = "mysqlEntityManagerFactory", 
    transactionManagerRef = "transactionManager"
)
@DependsOn("transactionManager")
public class MySQLDBConfig {
	
	@Autowired
	private JpaVendorAdapter jpaVendorAdapterMySQL;
	
	@Autowired
	private Environment env;
	
	@Bean(name = "mysqlDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource mysqlDataSource() {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setURL(env.getProperty("spring.mysql.datasource.url"));
		mysqlXADataSource.setPassword(env.getProperty("spring.mysql.datasource.password"));
		mysqlXADataSource.setUser(env.getProperty("spring.mysql.datasource.username"));
		
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXADataSource);
		xaDataSource.setUniqueResourceName("xads1");
		return xaDataSource;
	}

	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.mysql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect",	env.getProperty("spring.mysql.jpa.properties.hibernate.dialect"));

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(mysqlDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapterMySQL);
		entityManager.setPackagesToScan("com.example.mysqldomain");
		entityManager.setPersistenceUnitName("mysqlPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
