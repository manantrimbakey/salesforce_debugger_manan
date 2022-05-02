package com.manan.org.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = "/")
    public String getTemplate() {
        return "greetings";
    }

   
}