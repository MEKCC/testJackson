package com.practice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface ShowViewController {

    @GetMapping("/show")
    String showMainPage(Model model);

}
