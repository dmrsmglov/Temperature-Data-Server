package ru.webservice.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.webservice.application.service.TemperatureService;

@Configuration
public class ValidationConfig {

    @Bean
    public TemperatureService temperatureService() {
        return new TemperatureService();
    }

}
