package org.github.scalabletaskmanager.user;

import org.github.scalabletaskmanager.common.JwtService;
import org.github.scalabletaskmanager.common.JwtServiceImpl;
import org.github.scalabletaskmanager.user.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserAppConfig {

    private final UserRepository userRepository;

    @Autowired
    public UserAppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userRepository::findByUsername; // Automatically calls its method inside
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // One implementation of PasswordEncoder
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtServiceImpl();
    }
}
