package ru.webservice.application.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TemperatureValidationTest {
    private final TemperatureValidation temperatureValidation= new TemperatureValidation();
    private final Map<Double, Boolean> toTemperatureValidation = new HashMap<>();

    @Before
    public void setUp() {
        toTemperatureValidation.put(-500d, false);
        toTemperatureValidation.put(25d, true);
    }

    @After
    public void tearDown() {
        toTemperatureValidation.clear();
    }

    @Test
    public void isValid() {
        for (Map.Entry<Double, Boolean> entry : toTemperatureValidation.entrySet()) {
            final Boolean expected = entry.getValue();
            final Double testData = entry.getKey();
            temperatureValidation.setTemperature(testData);
            assertEquals(expected, temperatureValidation.isValid());
        }
    }
}