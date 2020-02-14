package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.config;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp.BookStoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	private UserDetailsService userDetailsService;

	public SecurityConfig(BookStoreUserDetailsService bookStoreUserDetailsService) {
		this.userDetailsService = bookStoreUserDetailsService;
	}

	//@Bean
	//public PasswordEncoder passwordEncoder() {
	//	return new BCryptPasswordEncoder();
	//}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.headers()
				.frameOptions().sameOrigin()
				.and()
				.authorizeRequests()
				.antMatchers("/resources/static/**", "/js/**", "/image/**", "/fonts/**", "/css/**", "/onlinebookstore/public/**").permitAll()
				.antMatchers("/", "/onlinebookstore").permitAll()
				.antMatchers("/onlinebookstore/secured/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/onlinebookstore/public/login")
//				.defaultSuccessUrl("/onlinebookstore/secured/myprofile")
				.defaultSuccessUrl("/onlinebookstore/public/home")
				.failureUrl("/onlinebookstore/public/login?error")
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/onlinebookstore/public/logout"))
				.logoutSuccessUrl("/onlinebookstore/public/login?logout")
				.permitAll()
				.and()
				.exceptionHandling();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
				//.passwordEncoder(passwordEncoder());
	}

}
