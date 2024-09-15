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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .authorizeHttpRequests(manager -> {
                    manager.requestMatchers("/v1/auth/**", "/v1/users/**").permitAll();
                    manager.requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**","GET"),
                            new AntPathRequestMatcher("/swagger-ui/**","GET"),
                            new AntPathRequestMatcher("/swagger-ui.html","GET"),
                            new AntPathRequestMatcher("/api/swagger.yml", "GET"))
                            .permitAll();

                    manager.anyRequest().authenticated();
                });

        // Stateless because every request needs authentication
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationManager(authenticationManager).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
