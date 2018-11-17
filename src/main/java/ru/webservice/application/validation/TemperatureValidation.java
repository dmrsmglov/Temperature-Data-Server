package ru.webservice.application.validation;

public class TemperatureValidation {
    private String strToValidate;
    public TemperatureValidation(String strToValidate) {
        this.strToValidate = strToValidate;
    }

    public boolean isValid() {
        try {
            Double a = Double.valueOf(strToValidate);
            return a >= -273;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
