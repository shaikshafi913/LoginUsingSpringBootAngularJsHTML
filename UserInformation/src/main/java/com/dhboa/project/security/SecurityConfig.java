package com.dhboa.project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity


public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/Login.html","/Login.html#/","HelloForm.html","/**").permitAll()
	.antMatchers("Registerform.html")
	.hasRole("admin").anyRequest()
     .authenticated().and()
	.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
	.csrf().csrfTokenRepository(csrfTokenRepository());
		
//		http.authorizeRequests()
//		.antMatchers("/index.html", "/Login.html","/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/index.html", "/Login.html","/**").access("hasRole('ROLE_ADMIN')")
//		.and().formLogin().and()
//	    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//		.csrf().csrfTokenRepository(csrfTokenRepository());;	
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository token = new HttpSessionCsrfTokenRepository();
		token.setHeaderName("X-XSRF-TOKEN");
		return token;
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery(
						"select username,password,enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select username,role from users where username=?")
				.rolePrefix("");

	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password")
//				.roles("a");
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
