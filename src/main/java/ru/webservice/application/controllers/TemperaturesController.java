package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.webservice.application.domain.TemperatureMessage;
import ru.webservice.application.repositories.TemperatureMessageRepo;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/temperatures")
public class TemperaturesController {
    private final TemperatureMessageRepo messageRepo;
    private final CoordinateValidation coordinateValidation;
    private final TemperatureValidation temperatureValidation;

    @Autowired
    public TemperaturesController(TemperatureMessageRepo messageRepo,
                                  CoordinateValidation coordinateValidation,
                                  TemperatureValidation temperatureValidation) {
        this.messageRepo = messageRepo;
        this.coordinateValidation = coordinateValidation;
        this.temperatureValidation = temperatureValidation;
    }

    @RequestMapping
    public String displayTemperaturesData(Map<String, Object> model) {
        Iterable<TemperatureMessage> messages = messageRepo.findByOrderByTime();
        model.put("messages",
                StreamSupport.stream(messages.spliterator(), false).limit(10).collect(Collectors.toList()));
        return "listOfTemperatures";
    }

    @PostMapping("/filter")
    public String filterTemperaturesData(
            @RequestParam(name = "coordinates", required = false, defaultValue = "none") String coordinates,
            Map<String, Object> model) {
        Iterable<TemperatureMessage> messages;
        if (!coordinates.equals("none")) {
            if (!coordinateValidation.isValid(coordinates)) {
                String coordinateValidationMessage;
                coordinateValidationMessage = coordinateValidation.getMessage();
                messages = messageRepo.findByOrderByTime();
                model.put("validation error", coordinateValidationMessage);
            } else {
                messages = messageRepo.findByCoordinatesEqualsOrderByTime(coordinates);
            }
        } else {
            messages = messageRepo.findByOrderByTime();

        }
        model.put("messages",
                StreamSupport.stream(messages.spliterator(), false).limit(10).collect(Collectors.toList()));
        return "listOfTemperatures";
    }

    @RequestMapping("/newTemperatureData")
    @PreAuthorize("hasAuthority('SENSOR')")
    public String addTemperature(Map<String, Object> model) {
        return "newTemperatureData";
    }

    @PostMapping("/newTemperatureData")
    @PreAuthorize("hasAuthority('SENSOR')")
    public String temperatureSave(TemperatureMessage temperatureMessage,
                                  Map<String, Object> model) {
        if (!coordinateValidation.isValid(temperatureMessage.getCoordinates())
                || !temperatureValidation.isValid(temperatureMessage.getTemperature())) {
            String coordinateValidationMessage = "";
            if (!coordinateValidation.getMessage().equals("not validated")) {
                coordinateValidationMessage = coordinateValidation.getMessage();
            }
            String temperatureValidationMessage = "";
            if (!temperatureValidation.getMessage().equals("not validated")) {
                temperatureValidationMessage = temperatureValidation.getMessage();
            }
            model.put("validation error", coordinateValidationMessage + "\n" + temperatureValidationMessage);
            return "newTemperatureData";
        }
        temperatureMessage.setTime(
                Calendar.getInstance().get(Calendar.MILLISECOND));

        messageRepo.save(temperatureMessage);
        return "redirect:/temperatures";
    }
}