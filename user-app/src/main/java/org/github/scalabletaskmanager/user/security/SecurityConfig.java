package org.github.scalabletaskmanager.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter authFilter;

    @Autowired
    public SecurityConfig(JwtAuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception { // Method injection
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> manager.requestMatchers("/v1/auth/register", "v1/auth/login") // All requests inside are permitted
                        .permitAll()
                        .anyRequest() // All other requests
                        .authenticated());

        // Stateless because every request needs authentication
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationManager(authenticationManager).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
