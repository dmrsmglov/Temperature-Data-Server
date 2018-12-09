package ru.webservice.application.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CoordinateValidationTest {
    private final Map<String, Boolean> toCoordinateValidation = new HashMap<>();
    private final CoordinateValidation coordinateValidation = new CoordinateValidation();

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
            coordinateValidation.setCoordinates(entry.getKey());
            assertEquals(expected, coordinateValidation.isValid());
        }
    }
}