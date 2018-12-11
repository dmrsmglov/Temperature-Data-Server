package ru.webservice.application.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.webservice.application.service.TemperatureService;

public class TemperatureValidation {
    private String message = "not validated";
    private String temperature = "";

    @Autowired
    private TemperatureService temperatureService;

    public String getMessage() {
        return message;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isValid() {
        double extremeTemperature = temperatureService.getExtremeTemperature();
        if (temperature.equals("")) {
            message = "Field temperature is empty. Nothing to validate.";
            return false;
        }
        double temp;
        try {
            temp = Double.valueOf(temperature);
        } catch (NumberFormatException ex) {
            message = "Your temperature format is invalid! " +
                    "Please, enter one real number.";
            return false;
        }
        if (temp < -1 * extremeTemperature || temp > extremeTemperature) {
            message = "Your temperature value is invalid! " +
                    "Your temperature value must be in range [-100; 100]";
            return false;
        }
        message = "valid";
        return true;
    }
}
