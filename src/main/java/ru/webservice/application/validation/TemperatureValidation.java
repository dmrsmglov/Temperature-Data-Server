package ru.webservice.application.validation;

public class TemperatureValidation {
    private String message = "not validated";
    private String toValidate = "";

    public String getMessage() {
        return message;
    }

    public boolean isValid(String strToValidate) {
        toValidate = strToValidate;
        message = "validated";
        double temperature;
        try {
            temperature = Double.valueOf(strToValidate);
        } catch (NumberFormatException ex) {
            message = "Your temperature format is invalid! " +
                    "Please, enter one real number.";
            return false;
        }

        if (temperature < -100 || temperature > 100) {
            message = "Your temperature value is invalid! " +
            "Your temperature value must be in range [-100; 100]";
            return false;
        }
        return true;
    }
}
