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
        message = "not validated";
        this.expression = expression;
        pattern = Pattern.compile(expression);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getMessage() {
        return message;
    }

    public CoordinateValidation() {

    }

    public boolean isValid(String strToValidate) {
        message = "validated";
        toValidate = strToValidate;
        Matcher matcher = pattern.matcher(toValidate);
        if (!matcher.matches()) {
            message = "Your coordinate format is invalid! " +
                    "Please, enter two real numbers with one space between them.";
            return false;
        }
        Double a = Double.valueOf(toValidate.split(" ")[0]);
        Double b = Double.valueOf(toValidate.split(" ")[1]);
        if (Math.abs(a) > 180 || Math.abs(b) > 180) {
            message = "Your coordinate values are invalid! " +
                    "Your absolute values of coordinates must be no more than 90.";
            return false;
        }
        return true;
    }
}
