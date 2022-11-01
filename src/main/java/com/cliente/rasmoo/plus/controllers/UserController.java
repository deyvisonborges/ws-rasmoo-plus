package com.cliente.rasmoo.plus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class UserController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
