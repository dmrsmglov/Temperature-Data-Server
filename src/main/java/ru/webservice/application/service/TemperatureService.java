package ru.webservice.application.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class TemperatureService {

    @Value("${extremeTemperatureValue}")
    private double extremeTemperature;

    public double getExtremeTemperature() {
        return extremeTemperature;
    }
}
