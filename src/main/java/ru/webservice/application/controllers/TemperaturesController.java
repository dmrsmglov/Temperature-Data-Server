package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webservice.application.domain.TemperatureMessage;
import ru.webservice.application.repositories.TemperatureMessageRepo;

import java.util.Map;

@Controller
public class TemperaturesController {

    private final TemperatureMessageRepo messageRepo;

    @Autowired
    public TemperaturesController(TemperatureMessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping("/temperatures")
    public String main(Map<String, Object> model) {
        Iterable<TemperatureMessage> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "listOfTemperatures";
    }
}
