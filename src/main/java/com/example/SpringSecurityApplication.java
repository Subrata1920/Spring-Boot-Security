package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author subrata
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, //This property enables Spring Security pre/post annotations
		  securedEnabled = true, //This property determines if the @Secured annotation should be enabled
		  jsr250Enabled = true)  //This property allows us to use the @RoleAllowed annotation
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
/*	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.postgresql.datasource")
	public DataSourceProperties primaryDataSource() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.mysql.datasource")
	public DataSourceProperties secondaryDataSource() {
	    return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.postgresql.jpa")
	public JpaProperties primaryDataSourceProperties() {
		return new JpaProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql.jpa")
	public JpaProperties secondaryDataSourceProperties() {
		JpaProperties jpaProperties = new JpaProperties();
		jpaProperties.setDatabasePlatform("MySQL");
		return jpaProperties;
	}	*/
}

