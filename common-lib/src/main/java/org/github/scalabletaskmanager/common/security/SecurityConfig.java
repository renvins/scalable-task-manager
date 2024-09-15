package org.github.scalabletaskmanager.common.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig {

    private final JwtAuthFilter authFilter;

    public SecurityConfig(JwtAuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Method injection
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(manager -> {
                    manager.requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**","GET"),
                            new AntPathRequestMatcher("/swagger-ui/**","GET"),
                            new AntPathRequestMatcher("/swagger-ui.html","GET"),
                            new AntPathRequestMatcher("/api/swagger.yml", "GET"))
                            .permitAll();

                    manager.anyRequest().authenticated();
                });

        // Stateless because every request needs authentication
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
