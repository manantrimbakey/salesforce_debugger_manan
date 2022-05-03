package com.manan.org.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = "/")
    public String getTemplate() {
        return "greetings";
    }

   
}