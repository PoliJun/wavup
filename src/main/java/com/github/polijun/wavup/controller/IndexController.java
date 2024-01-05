package com.github.polijun.wavup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    public String getHomePage() {
        return "index.html"; 
    }
}
