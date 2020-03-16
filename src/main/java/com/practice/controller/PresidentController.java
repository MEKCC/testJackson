package com.practice.controller;

import com.practice.service.PresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PresidentController {

    @Autowired
    private PresidentService presidentService;

    @GetMapping("/add")
    public void addPresidents() {
        presidentService.addPresidents();
    }

    @GetMapping("/delete/{id}")
    public void deletePresidents(@PathVariable Long id) {
        presidentService.deletePresidents(id);
    }

    @PutMapping("/update/{id}")
    public void updatePresidents(@PathVariable Long id) {
        presidentService.updatePresidents(id);
    }
}