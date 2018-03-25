package com.skipthedishes.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import com.skipthedishes.services.impl.CustomerServiceImpl;

//@Configuration
//@EnableWebSecurity
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private CustomerServiceImpl customerServiceImpl;

	public WebSecurity(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
		
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/Product/").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
				.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(filter)
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), customerServiceImpl));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("rivanluiz@gmail.com").password("123456").roles("USER")
			.and()
			.withUser("admin@skipthedishes.com").password("skip@123").roles("USER", "ADMIN");
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
