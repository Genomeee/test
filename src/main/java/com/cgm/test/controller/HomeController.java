package com.cgm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final String REDIRECT = "redirect:/swagger-ui.html";
    @RequestMapping("/")
    public String home() {
        return REDIRECT;
    }
}
