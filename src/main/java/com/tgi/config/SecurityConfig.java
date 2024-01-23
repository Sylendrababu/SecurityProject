//  Authentication fetching from db

package com.tgi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tgi.filter.JwtAuthFilter;
import com.tgi.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	

	@Autowired
	JwtAuthFilter authFilter;
	
	//Authentication
	@Bean
	UserDetailsService userDetailsService() {
		
		return new UserInfoUserDetailsService();
	}
	
	
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/student/getAll", "/user/save","/jwt/authenticate")
				.permitAll().and().authorizeHttpRequests().requestMatchers("/student/**").authenticated().and()
				.formLogin().and().build();
	*/
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> request
	               .requestMatchers("/UserInfo/save", "/UserInfo/authenticate").permitAll()
	             //  .requestMatchers("/User/new/admin").hasAnyAuthority(Role.ADMIN.name)
	             //  .requestMatchers("/User/new/user").hasAnyAuthority(Role.USER.name)
	               .anyRequest().authenticated())
	               .sessionManagement(session -> session
	               .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	               .authenticationProvider(authenticationProvider())
	               .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	              return http .build();
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	   AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
}
