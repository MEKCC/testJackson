package com.practice.controller;

import com.practice.service.GraphicCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowViewControllerImpl implements ShowViewController {

    @Autowired
    private GraphicCreator graphicCreator;

    @Override
    @GetMapping("/show")
    public String showMainPage(Model model) {
        model.addAttribute("graphic", graphicCreator.createGraphic());
        return "main";
    }
}
