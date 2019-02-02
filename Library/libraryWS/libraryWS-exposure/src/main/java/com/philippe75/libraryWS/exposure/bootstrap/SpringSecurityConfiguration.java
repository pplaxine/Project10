package com.philippe75.libraryWS.exposure.bootstrap;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;

@Configuration
@ComponentScan("com.philippe75.libraryWS")
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	@Bean
	public PasswordEncoder bcPasswordEncodeur () {
		return new BCryptPasswordEncoder();
	}
	

}
