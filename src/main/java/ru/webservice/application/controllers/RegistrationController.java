package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webservice.application.domain.Role;
import ru.webservice.application.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.webservice.application.repositories.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    public String registrationAsAUser() {
        return "userRegistration";
    }

    @PostMapping("/user")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "userRegistration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/temperatures";
    }

    @GetMapping("/sensor")
    public String registrationAsASensor() {
        return "sensorRegistration";
    }

    @PostMapping("/sensor")
    public String addSensor(User sensor, Map<String, Object> model) {
        User sensorFromDb = userRepo.findByUsername(sensor.getUsername());
        if (sensorFromDb != null) {
            model.put("message", "Sensor exists!");
            return "sensorRegistration";
        }
        sensor.setActive(true);
        sensor.setRoles(Collections.singleton(Role.SENSOR));
        userRepo.save(sensor);
        return "redirect:/temperatures";
    }
}
