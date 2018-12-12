package ru.webservice.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.webservice.application.service.TemperatureService;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

@Configuration
public class ValidationConfig {

    @Bean
    public CoordinateValidation coordinateValidation(){
        return new CoordinateValidation();
    }

    @Bean
    public TemperatureValidation temperatureValidation() {
        return new TemperatureValidation();
    }

    @Bean
    public TemperatureService temperatureService() {
        return new TemperatureService();
    }

}
