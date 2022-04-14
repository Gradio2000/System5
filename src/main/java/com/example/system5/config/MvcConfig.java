package com.example.system5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/home.html").setViewName("home");
//        registry.addViewController("/loginnn.html").setViewName("login");
//        registry.addViewController("/admin").setViewName("admin");
//        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/registration").setViewName("registration");
    }
}

