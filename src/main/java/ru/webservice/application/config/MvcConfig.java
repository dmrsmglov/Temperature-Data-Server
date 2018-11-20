package ru.webservice.application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public CoordinateValidation coordinateValidation(){
        return new CoordinateValidation();
    }

    @Bean
    public TemperatureValidation temperatureValidation() {
        return new TemperatureValidation();
    }
}