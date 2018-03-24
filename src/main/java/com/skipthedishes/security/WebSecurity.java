package com.skipthedishes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import com.skipthedishes.services.impl.CustomerServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private CustomerServiceImpl customerServiceImpl;

	public WebSecurity(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/api/v1/Customer/auth");
		
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.GET, "api/v1/Product/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(filter)
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customerServiceImpl));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("skip").roles("USER")
			.and()
			.withUser("admin").password("skip").roles("USER", "ADMIN");
	}

	// @Bean
	// CorsConfigurationSource corsConfigurationSource() {
	// final UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// source.registerCorsConfiguration("/**", new
	// CorsConfiguration().applyPermitDefaultValues());
	// return source;
	// }
}
