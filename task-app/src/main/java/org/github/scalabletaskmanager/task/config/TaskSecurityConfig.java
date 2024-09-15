package org.github.scalabletaskmanager.task.config;

import org.github.scalabletaskmanager.common.security.JwtAuthFilter;
import org.github.scalabletaskmanager.common.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class TaskSecurityConfig extends SecurityConfig {

    public TaskSecurityConfig(JwtAuthFilter authFilter) {
        super(authFilter);
    }

    @Override
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return super.filterChain(http);
    }
}
