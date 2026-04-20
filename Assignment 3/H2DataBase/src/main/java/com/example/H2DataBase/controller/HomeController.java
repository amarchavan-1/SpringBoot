package com.example.H2DataBase.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping({"/", "/index"})
    public String home() {
        return "index"; // looks for templates/index.html
    }
}