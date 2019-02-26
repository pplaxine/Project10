package com.philippe75.libraryWS.exposure.bootstrap;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringMailServerConfiguration {
	
	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Autowired
	private Environment env;
	
	@Bean(name="mailSender")
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost(env.getProperty("mailservice.host"));
		jms.setPort(Integer.valueOf(env.getProperty("mailservice.port")));
		
		jms.setUsername(env.getProperty("mailservice.user"));
		jms.setPassword(env.getProperty("mailservice.password"));
		
		Properties props = jms.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", env.getProperty("mailservice.smtp.auth"));
		props.put("mail.debug", env.getProperty("mailservice.smtp.starttls.enable"));
		
		return jms;
	}
	
	
	

}
