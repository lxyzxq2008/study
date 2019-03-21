package com.demo.https.demohttps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoHttpsController {

    @GetMapping("/index")
    public String onTestHttps() {
        return "/index";
    }

    @PostMapping("/users")
    @ResponseBody
    public Object onUserList() {
        Map user = new HashMap<>();
        user.put("name", "sunroom");
        user.put("nickname", "sunroom");
        return user;
    }
}
