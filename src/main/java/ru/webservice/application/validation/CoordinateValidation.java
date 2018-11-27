package ru.webservice.application.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CoordinateValidation {
    private String expression = "[-]??[0-9]+\\Q.\\E??[0-9]*\\s[-]??[0-9]+\\Q.\\E??[0-9]*";
    private Pattern pattern = Pattern.compile(expression);
    private String message = "not validated";
    private String toValidate = "";

    public String getExpression() {
        return expression;
    }

    public CoordinateValidation(String expression) {
        this.expression = expression;
        this.pattern = Pattern.compile(expression);
    }

    public void setExpression(String expression) {

        this.expression = expression;
        pattern = Pattern.compile(expression);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public CoordinateValidation() {

    }

    public boolean isValid(String strToValidate) {
        Matcher matcher = pattern.matcher(strToValidate);
        if (!matcher.matches()) {
            return false;
        }
        Double a = Double.valueOf(strToValidate.split(" ")[0]);
        Double b = Double.valueOf(strToValidate.split(" ")[1]);
        return Math.abs(a) <= 90 && Math.abs(b) <= 90;
    }
}
