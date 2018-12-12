package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.webservice.application.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.webservice.application.repositories.RoleRepo;
import ru.webservice.application.repositories.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public String registrationAsAUser() {
        return "userRegistration";
    }

    @PostMapping("/user")
    public String addUser(User user, Map<String, Object> model) {
        if (userRepo.existsByUsername(user.getUsername())) {
            model.put("message", "User exists!");
            return "userRegistration";
        }
        add(user, "USER");
        return "redirect:/temperatures";
    }

    @GetMapping("/sensor")
    public String registrationAsASensor() {
        return "sensorRegistration";
    }

    @PostMapping("/sensor")
    public String addSensor(User sensor, Map<String, Object> model) {
        if (userRepo.existsByUsername(sensor.getUsername())) {
            model.put("message", "Sensor exists!");
            return "sensorRegistration";
        }
        add(sensor, "SENSOR");
        return "redirect:/temperatures";
    }

    private void add(User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepo.findByName(role)));
        userRepo.save(user);
    }
}
