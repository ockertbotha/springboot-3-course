package com.luv2code.springboot.demosecurity.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

/* 
  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {

    UserDetails john = User.builder()
        .username("john")
        .password("{noop}test123") // {noop} means no password encoder
        .roles("EMPLOYEE")
        .build();

    UserDetails mary = User.builder()
        .username("mary")
        .password("{noop}test123") // {noop} means no password encoder
        .roles("EMPLOYEE", "MANAGER")
        .build();

    UserDetails susan = User.builder()
        .username("susan")
        .password("{noop}test123") // {noop} means no password encoder
        .roles("EMPLOYEE", "MANAGER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(john, mary, susan);
    
  }
*/

  // add support for JDBC ... no more hard-coded users

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {

    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer -> 
      configurer
        .requestMatchers("/").hasRole("EMPLOYEE")
        .requestMatchers("/leaders/**").hasRole("MANAGER")
        .requestMatchers("/systems/**").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .formLogin(form ->
        form
          .loginPage("/showMyLoginPage")
          .loginProcessingUrl("/authenticateTheUser")
          .permitAll()
      )
      .logout(logout -> 
        logout
          .permitAll()
      )
      .exceptionHandling(configurer -> 
        configurer
          .accessDeniedPage("/access-denied")
      );

    return http.build();
  }
}
