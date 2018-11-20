package ru.webservice.application.validation;

public class TemperatureValidation {

    public boolean isValid(String strToValidate) {
        try {
            Double a = Double.valueOf(strToValidate);
            return a >= -80 && a <= 100;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
