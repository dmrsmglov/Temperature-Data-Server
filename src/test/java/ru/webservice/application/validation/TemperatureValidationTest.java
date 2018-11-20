package ru.webservice.application.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TemperatureValidationTest {
    private final TemperatureValidation temperatureValidation= new TemperatureValidation();
    private final Map<String, Boolean> toTemperatureValidation = new HashMap<>();

    @Before
    public void setUp() {
        toTemperatureValidation.put("-500", false);
        toTemperatureValidation.put("25", true);
        toTemperatureValidation.put("some temperature", false);
        toTemperatureValidation.put("the temperature is 12 degrees", false);
        toTemperatureValidation.put("", false);
        toTemperatureValidation.put(" ", false);
    }

    @After
    public void tearDown() {
        toTemperatureValidation.clear();
    }

    @Test
    public void isValid() {
        for (Map.Entry<String, Boolean> entry : toTemperatureValidation.entrySet()) {
            final Boolean expected = entry.getValue();
            final String testData = entry.getKey();
            assertEquals(expected, temperatureValidation.isValid(testData));
        }
    }
}