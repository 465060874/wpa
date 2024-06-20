package com.wpa.config;

import com.wpa.service.BusinessService;
import com.wpa.service.BusinessServiceImpl;
import com.wpa.test.SharedDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.wpa")
public class AppConfig {
    @Bean
    public BusinessService businessService() {
        return new BusinessServiceImpl();
    }

    @Bean
    public SharedDataService sharedDataService() {
        return new SharedDataService();
    }
}
