package ru.webservice.application.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CoordinateValidationTest {
    private final Map<String, Boolean> toCoordinateValidation = new HashMap<>();

    @Before
    public void setUp() {
        toCoordinateValidation.put("", false);
        toCoordinateValidation.put("1 1", true);
        toCoordinateValidation.put("1.0 -2.1", true);
        toCoordinateValidation.put("1337 91", false);
        toCoordinateValidation.put("asd@", false);
        toCoordinateValidation.put("12", false);
    }

    @After
    public void tearDown() {
        toCoordinateValidation.clear();
    }

    @Test
    public void isValid() {
        for (Map.Entry<String, Boolean> entry : toCoordinateValidation.entrySet()) {
            final Boolean expected = entry.getValue();
            final String testData = entry.getKey();
            CoordinateValidation coordinateValidation = new CoordinateValidation(testData);
            assertEquals(expected, coordinateValidation.isValid());
        }
    }
}