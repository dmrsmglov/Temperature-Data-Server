package ru.webservice.application.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class TemperatureService {

    @NotNull
    @Value("${extremeTemperatureValue}")
    private double extremeTemperature;

    public double getExtremeTemperature() {
        return extremeTemperature;
    }
}
