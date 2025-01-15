package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

  // add support for JDBC ... no more hardcoded users :-)

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
    
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(configurer ->
        configurer
          .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
          .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
          .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
          .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
          .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
          
    );

    // use HTTP Basic Authentication
    http.httpBasic(Customizer.withDefaults());

    // disable CSRF - Cross Site Request Forgery
    // in general not required for stateless APIs using POST, PUT. DELETE and/or PATCH
    http.csrf(csrf -> csrf.disable());

    return http.build();
  }
    
}

 /*
  @Bean
  public InMemoryUserDetailsManager userDetailsManagerConfigurer() {

    UserDetails john = User.builder()
      .username("john")
      .password("{noop}test123")
      .roles("EMPLOYEE")
      .build();

    UserDetails mary = User.builder()
      .username("mary")
      .password("{noop}test123")
      .roles("EMPLOYEE", "MANAGER")
      .build();

      UserDetails susan = User.builder()
      .username("susan")
      .password("{noop}test123")
      .roles("EMPLOYEE", "MANAGER", "ADMIN")
      .build();  
      
    return new InMemoryUserDetailsManager(john, mary, susan);
  }
*/