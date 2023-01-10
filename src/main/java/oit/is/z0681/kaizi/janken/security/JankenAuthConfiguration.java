package oit.is.z0681.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JankenAuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();
    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$/Xl2DAr6sxThA.ijmYH5PuSZQtxSW8YvWetfb3udCFSVnPX3hg1c2")
        .roles("USER")
        .build();
    // p@ss
    UserDetails user2 = users
        .username("user2")
        .password("$2y$10$Gjs/cEitqs6vuRVUFRK8mOqnFFsMcS/j8uH58eW9X8FvbKup44dhC")
        .roles("USER")
        .build();
    // hoge
    UserDetails user3 = users
        .username("ほんだ")
        .password("$2y$10$x4IAfJfysuzL0S4DeLK5SOCDh7E8lNmBdhPxk4cZyKXKOrPq6nsmG")
        .roles("USER")
        .build();
    // kiyosi
    UserDetails user4 = users
        .username("いがき")
        .password("$2y$10$8kf7l40bFPC5LQaedIN8uOlaALc/tJOUXR7RiABHb7bupHo3DE5Zi")
        .roles("USER")
        .build();
    // hirosi
    return new InMemoryUserDetailsManager(user1, user2, user3, user4);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeHttpRequests().mvcMatchers("/janken/**").authenticated();
    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
