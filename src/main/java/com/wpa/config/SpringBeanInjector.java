package com.wpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanInjector {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    public void inject(Object target) {
        autowireCapableBeanFactory.autowireBean(target);
    }
}
