package ru.webservice.application.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.webservice.application.service.TemperatureService;

public class TemperatureValidation {
    private String message = "not validated";
    private double temperature;

    @Autowired
    private TemperatureService temperatureService;

    public String getMessage() {
        return message;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isValid() {
        double extremeTemperature = temperatureService.getExtremeTemperature();

        if ( Math.abs(temperature) > extremeTemperature) {
            message = "Your temperature value is invalid! " +
                    "Your temperature value must be in range [-100; 100]";
            return false;
        }
        message = "valid";
        return true;
    }
}
