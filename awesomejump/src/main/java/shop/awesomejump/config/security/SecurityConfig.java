package shop.awesomejump.config.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;
	
	@Bean public PasswordEncoder 				passwordEncoder()	 			{ return new BCryptPasswordEncoder(); }
	@Bean public UserDetailsService 			customerUserService() 			{ return new CustomUserDetailsService(); }
	@Bean public AuthenticationFailureHandler   customFailureHandler() 			{ return new CustomFailureHandler(); }
	@Bean public AuthenticationSuccessHandler   customSuccessHandler() 			{ return new CustomSuccessHandler(); }
	@Bean public AuthenticationProvider 		customAuthenticationProvider()  { return new CustomAuthenticationProvider(); }
	@Bean public AccessDeniedHandler 			customAccessDeniedHandler() 	{ return new CustomAccessDeniedHandler(); }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(customAuthenticationProvider());
		auth.userDetailsService(customerUserService()).passwordEncoder(passwordEncoder());
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("SELECT mem_email, mem_pw, mem_enabled_yn FROM member WHERE mem_email = ?")
		.authoritiesByUsernameQuery("SELECT mem_email, 'ROLE_USER' FROM member WHERE mem_email = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// FILTER
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
		
        // AUTHORIZE PAGES
//        http.authorizeRequests()
//        		.antMatchers("/admin").access("hasRole('ADMIN')")
//        		.antMatchers("/user").access("hasRole('USER')");
		
        // EXCEPTION HANDLER
		http.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler());
		
		// LOGIN AND LOGOUT
		http.formLogin()
				.loginPage("/login").failureHandler(customFailureHandler())
									.successHandler(customSuccessHandler())
	    .and().logout()	
				.logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
	}
	
}
