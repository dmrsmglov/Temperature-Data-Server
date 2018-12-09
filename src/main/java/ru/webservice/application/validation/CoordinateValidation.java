package ru.webservice.application.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CoordinateValidation {
    private String expression = "[-]??[0-9]+\\Q.\\E??[0-9]*\\s[-]??[0-9]+\\Q.\\E??[0-9]*";
    private Pattern pattern = Pattern.compile(expression);
    private String message = "not validated";
    private String coordinates = "";

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        if (coordinates.equals("")) {
            message = "Field coordinates is empty. Nothing to validate.";
            return false;
        }
        Matcher matcher = pattern.matcher(coordinates);
        if (!matcher.matches()) {
            message = "Your coordinate format is invalid! " +
                    "Please, enter two real numbers with one space between them.";
            return false;
        }
        Double a = Double.valueOf(coordinates.split(" ")[0]);
        Double b = Double.valueOf(coordinates.split(" ")[1]);
        if (Math.abs(a) >= 180 || Math.abs(b) >= 180) {
            message = "Your coordinate values are invalid! " +
                    "Your absolute values of coordinates must be less than 180.";
            return false;
        }
        message = "valid";
        return true;
    }
}
