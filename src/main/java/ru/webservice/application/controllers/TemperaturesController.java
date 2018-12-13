package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.webservice.application.domain.TemperatureMessage;
import ru.webservice.application.repositories.TemperatureMessageRepo;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/temperatures")
public abstract class TemperaturesController {
    private final TemperatureMessageRepo messageRepo;

    @Autowired
    public TemperaturesController(TemperatureMessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping
    public String displayTemperaturesData(Map<String, Object> model) {
        List<TemperatureMessage> messages = messageRepo.findFirst10ByOrderByTimeDesc();
        model.put("messages", messages);
        return "listOfTemperatures";
    }

    @RequestMapping("/filter")
    public String filterTemperaturesData(
            @RequestParam(name = "latitude", required = false, defaultValue = "180") double latitude,
            @RequestParam(name = "longitude", required = false, defaultValue = "180") double longitude,
            Map<String, Object> model) {
        List<TemperatureMessage> messages;
        CoordinateValidation coordinateValidation = getCoordinateValidation();
        coordinateValidation.setCoordinates(latitude, longitude);
        if (!coordinateValidation.isValid()) {
            String coordinateValidationMessage = coordinateValidation.getMessage();
            messages = messageRepo.findFirst10ByOrderByTimeDesc();
            model.put("validation error", coordinateValidationMessage);
        } else {
            messages = messageRepo.findFirst10ByLatitudeEqualsAndLongitudeEqualsOrderByTimeDesc(latitude, longitude);
        }
        model.put("messages", messages);
        return "listOfTemperatures";
    }

    @Lookup
    abstract CoordinateValidation getCoordinateValidation();

    @Lookup
    abstract TemperatureValidation getTemperatureValidation();

    @RequestMapping("/newTemperatureData")
    @PreAuthorize("hasAuthority('SENSOR')")
    public String addTemperature(Map<String, Object> model) {
        return "newTemperatureData";
    }

    @PostMapping("/newTemperatureData")
    @PreAuthorize("hasAuthority('SENSOR')")
    public String temperatureSave(TemperatureMessage temperatureMessage,
                                  Map<String, Object> model) {
        CoordinateValidation coordinateValidation = getCoordinateValidation();
        TemperatureValidation temperatureValidation = getTemperatureValidation();

        coordinateValidation.setCoordinates(temperatureMessage.getLatitude(), temperatureMessage.getLongitude());
        temperatureValidation.setTemperature(temperatureMessage.getTemperature());

        boolean isValid = true;
        if (!coordinateValidation.isValid()) {
            isValid = false;
            String coordinateValidationMessage = "";
            if (!coordinateValidation.getMessage().equals("valid")) {
                coordinateValidationMessage = coordinateValidation.getMessage();
            }
            model.put("validation error", coordinateValidationMessage);
        } else if (!temperatureValidation.isValid()) {
            isValid = false;
            String temperatureValidationMessage = "";
            if (!temperatureValidation.getMessage().equals("valid")) {
                temperatureValidationMessage = temperatureValidation.getMessage();
            }
            model.put("validation error", temperatureValidationMessage);
        }
        if (!isValid) {
            return "newTemperatureData";
        }

        messageRepo.save(temperatureMessage);
        return "redirect:/temperatures";
    }
}