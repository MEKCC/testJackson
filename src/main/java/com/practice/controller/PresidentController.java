package com.practice.controller;

import com.practice.service.PresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PresidentController {

    @Autowired
    private PresidentService presidentService;

    @GetMapping("/add")
    public void addPresidents() throws IOException {
        presidentService.addPresidents();
    }
}