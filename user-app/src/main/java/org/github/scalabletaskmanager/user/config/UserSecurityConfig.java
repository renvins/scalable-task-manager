package org.github.scalabletaskmanager.user.config;

import org.github.scalabletaskmanager.common.security.JwtAuthFilter;
import org.github.scalabletaskmanager.common.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends SecurityConfig {

    @Autowired
    public UserSecurityConfig(JwtAuthFilter authFilter) {
        super(authFilter);
    }

    @Override
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(manager -> manager.requestMatchers("/v1/auth/**", "/v1/users/**").permitAll());
        return super.filterChain(http);
    }
}
