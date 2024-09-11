package org.github.scalabletaskmanager.user;

import org.github.scalabletaskmanager.user.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserAppConfig {

    private final UserRepository userRepository;

    @Autowired
    public UserAppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return userRepository::findByUsername; // Automatically calls its method inside
    }
}
