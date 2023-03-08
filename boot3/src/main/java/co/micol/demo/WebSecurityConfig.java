package co.micol.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean // <bean id="" class=""/>랑 같다.
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		//람다식. 기존 스프링의 security-context.xml에서 <security:~>들이랑 같음
		http.authorizeHttpRequests(
				(requests) -> requests.antMatchers("/", "/home").permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll())
				.csrf().disable();

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("abcd").roles("USER")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("abcd").roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}

}
