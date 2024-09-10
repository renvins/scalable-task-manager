package org.github.scalabletaskmanager.user;

import org.github.scalabletaskmanager.user.security.UserAuthFilter;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAppConfig {

    /* To register it only for some URL patterns */
    @Bean
    FilterRegistrationBean<UserAuthFilter> filterRegistrationBean(@Autowired UserService userService) {
        FilterRegistrationBean<UserAuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UserAuthFilter(userService));
        registrationBean.addUrlPatterns("/v1/tasks/*");

        return registrationBean;
    }
}
