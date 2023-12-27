package com.omarkhaled.simple.webbased.email.program.configurations;

import com.omarkhaled.simple.webbased.email.program.components.SaveUsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final SaveUsersController saveDataInterceptor;

    @Autowired
    public WebConfiguration(SaveUsersController saveDataInterceptor) {
        this.saveDataInterceptor = saveDataInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(saveDataInterceptor);
    }
}
