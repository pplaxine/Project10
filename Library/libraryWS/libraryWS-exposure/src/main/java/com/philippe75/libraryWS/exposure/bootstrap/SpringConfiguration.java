package com.philippe75.libraryWS.exposure.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <b>Spring configuration Class.</b>
 * 
 * <p>
 * 	Spring component scan configuration.  
 * </p>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@ComponentScan("com.philippe75.libraryWS")
public class SpringConfiguration {



}
