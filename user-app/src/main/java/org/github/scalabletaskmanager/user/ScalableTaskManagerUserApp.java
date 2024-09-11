package org.github.scalabletaskmanager.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Excluding these classes to prevent auto security for Spring
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class ScalableTaskManagerUserApp {

    public static void main(String[] args) {
        SpringApplication.run(ScalableTaskManagerUserApp.class, args);
    }
}
