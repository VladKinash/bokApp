package org.libapp.libapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF (temporarily, for development)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll() // Allow home page without authentication
                        .anyRequest().authenticated()    // Require authentication for all other pages
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specify custom login page
                        .permitAll()         // Allow everyone to access the login page
                );

        return http.build();
    }
}
