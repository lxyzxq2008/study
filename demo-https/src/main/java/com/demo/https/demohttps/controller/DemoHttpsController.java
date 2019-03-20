package com.demo.https.demohttps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoHttpsController {

    @GetMapping("/index")
    public String onTestHttps() {
        return "/index";
    }
}
