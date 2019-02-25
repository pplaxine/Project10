package com.philippe75.libraryBatch.bootstrap;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingServiceImplService;

@Configuration
@ComponentScan("com.philippe75.libraryBatch")
@EnableScheduling
@PropertySource("classpath:/Config.properties")
public class SpringConfiguration {
	
	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Autowired
	Environment env;
	
    @Value("org/springframework/batch/core/schema-drop-postgresql.sql")
    private Resource dropReopsitoryTables;
 
    @Value("org/springframework/batch/core/schema-postgresql.sql")
    private Resource dataReopsitorySchema;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.user"));
        dataSource.setPassword(env.getProperty("database.code"));

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) throws MalformedURLException {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        
        databasePopulator.addScript(dropReopsitoryTables);

        //Flag to indicate that all failures in SQL should be logged but not cause a failure.
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(dataReopsitorySchema);
        databasePopulator.setIgnoreFailedDrops(true);
 
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator);
 
        return initializer;
    }
    
    
    private JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTransactionManager(getTransactionManager());
     //   factory.setSerializer(new Jackson2ExecutionContextStringSerializer());
        factory.afterPropertiesSet();
        
        return (JobRepository) factory.getObject();
    }
    
    private PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }
    
    @Bean(name="jobLauncher")
    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        
        return jobLauncher;
    }
    @Bean(name="borowingService")
    public BorrowingService getBorrowingService() {
    	return new BorrowingServiceImplService().getBorrowingServiceImplPort();
    }

}
