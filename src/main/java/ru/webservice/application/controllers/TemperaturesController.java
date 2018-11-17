package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webservice.application.domain.TemperatureMessage;
import ru.webservice.application.repositories.TemperatureMessageRepo;
import ru.webservice.application.validation.CoordinateValidation;
import ru.webservice.application.validation.TemperatureValidation;

import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/temperatures")
public class TemperaturesController {
    private final TemperatureMessageRepo messageRepo;

    @Autowired
    public TemperaturesController(TemperatureMessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping
    public String showTemperatures(Map<String, Object> model) {
        Iterable<TemperatureMessage> messages = messageRepo.findAll();
        model.put("messages", messages);
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
        CoordinateValidation coordinateValidation = new CoordinateValidation(temperatureMessage.getCoordinates());
        TemperatureValidation temperatureValidation = new TemperatureValidation(temperatureMessage.getTemperature());
        if (!coordinateValidation.isValid() || !temperatureValidation.isValid()) {
            model.put("validation error", "Your temperature data is invalid!");
            return "newTemperatureData";
        }
        temperatureMessage.setTime(
                Calendar.getInstance().get(Calendar.MILLISECOND));

        messageRepo.save(temperatureMessage);
        return "redirect:/temperatures";
    }

}
