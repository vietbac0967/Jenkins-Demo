package com.example.jenkinsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping()
public class HelloController {
    @GetMapping
    public String getHello(){
        return "Hello world " + LocalDate.now();
    }

    @GetMapping("/bye")
    public String sayGoodBye(){
        return "Good bye see you later " + LocalDate.now();
    }
}
