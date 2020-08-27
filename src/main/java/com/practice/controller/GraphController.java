package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GraphController {

    @GetMapping("/show")
    public ModelAndView showMainPage() {
        ModelAndView modelAndView = new ModelAndView("graph");
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            integerList.add(i);
        }
        modelAndView.addObject("listOfNumbers", integerList);
        return modelAndView;
    }
}
