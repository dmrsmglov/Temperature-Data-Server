package ru.webservice.application.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateValidation {
    private String strToValidate;
    private String expression = "[-]??[0-9]+\\Q.\\E??[0-9]*\\s[-]??[0-9]+\\Q.\\E??[0-9]*";

    private Pattern pattern;
    private Matcher matcher;

    public CoordinateValidation(String strToValidate) {
        this.strToValidate = strToValidate;
        pattern = Pattern.compile(expression);
        matcher = pattern.matcher(strToValidate);
    }

    public boolean isValid() {
        if (!matcher.matches()) {
            return false;
        }

        Double a = Double.valueOf(strToValidate.split(" ")[0]);
        Double b = Double.valueOf(strToValidate.split(" ")[1]);
        return Math.abs(a) <= 90 && Math.abs(b) <= 90;
    }
}
