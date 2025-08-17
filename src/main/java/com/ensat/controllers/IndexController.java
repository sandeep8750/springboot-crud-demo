package com.ensat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        System.out.println("Index Controller called");
        return "index";  // maps to src/main/resources/templates/index.html
    }
}
