package org.github.scalabletaskmanager.task;

import org.github.scalabletaskmanager.common.JwtService;
import org.github.scalabletaskmanager.common.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TaskAppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JwtService jwtService() {
        return new JwtServiceImpl();
    }
}
