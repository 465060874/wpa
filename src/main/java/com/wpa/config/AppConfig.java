package com.wpa.config;

import com.wpa.service.BusinessService;
import com.wpa.service.BusinessServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BusinessService businessService() {
        return new BusinessServiceImpl();
    }
}
