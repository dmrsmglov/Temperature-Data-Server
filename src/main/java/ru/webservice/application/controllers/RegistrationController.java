package ru.webservice.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.webservice.application.domain.Role;
import ru.webservice.application.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.webservice.application.repositories.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/temperatures";

    }
}
