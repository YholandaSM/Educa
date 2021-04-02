package com.ysoberon.homework.security;

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

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	
	

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email as 'username', password, estatus from Usuario where email=?")
				.authoritiesByUsernameQuery("select email 'username', password, ' ' as perfil from Usuario where email=?");
		
		
		 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Los recursos estáticos no requieren autenticación
				.antMatchers("/css/**", "/images/**", "/js/**", "/logos/**").permitAll()
				// Las vistas públicas no requieren autenticación

				.antMatchers("/registro","/login","/bcrypt/**"

				).permitAll()

				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()
				// El formulario de Login no requiere autenticacion
				//.and().formLogin().permitAll();
		.and().formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/home")
		.usernameParameter("username")
		.passwordParameter("password")
		 
		 .and().logout().permitAll();
	}
	
	
 	@Bean
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	} 
	
	 
}
