package com.philippe75.libraryWS.exposure.bootstrap;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b>Hibernate configuration Class.</b>
 * 
 * <p>
 * 	Configuration beans are injected via Spring.  
 * </p>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/properties/DataBaseConf.properties")
public class HibernateConfiguration {
	
	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Inject
	Environment env;
	
	/**
	 * Spring bean of a sessionFactory creation and configuration.
	 * 
	 * @see #dataSource()
	 * @see #hibernateProperties()
	 * 
	 * @return configured SessionFactory object 
	 */
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.philippe75.libraryWS");
        sessionFactory.setHibernateProperties(hibernateProperties());
 
        return sessionFactory;
    }
	 
	/**
	 * Spring bean of a dataSource creation and configuration.
	 * 
	 * @see #env
	 * 
	 * @return configured DataSource object
	 */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.code"));
 
        return dataSource;
    }
 
	/**
	 * Spring bean of a TransactionManager creation and configuration.
	 * 
	 * @see #sessionFactory()
	 * 
	 * @return PlatformTransactionManager object
	 */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
 
    /**
     * Hibernate properties configuration.
     * 
     * @return Properties
     */
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        
        return hibernateProperties;
    }
	
}
