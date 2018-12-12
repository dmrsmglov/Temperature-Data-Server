package ru.webservice.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.webservice.application.service.TemperatureService;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

@Configuration
public class ValidationConfig {

    @Bean
    @Scope("prototype")
    public CoordinateValidation coordinateValidation(){
        return new CoordinateValidation();
    }

    @Bean
    @Scope("prototype")
    public TemperatureValidation temperatureValidation() {
        return new TemperatureValidation();
    }

    @Bean
    public TemperatureService temperatureService() {
        return new TemperatureService();
    }

}
