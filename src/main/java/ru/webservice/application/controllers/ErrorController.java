package ru.webservice.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping
    public String errorMessage() {
        return "error";
    }
}
