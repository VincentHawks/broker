package ru.hse.broker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {



    @GetMapping("/")
    public void init() {

    }
}
