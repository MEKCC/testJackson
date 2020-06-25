package com.practice.controller;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

    @GetMapping("/abc")
    private RedirectView  helloFromMain(RedirectAttributes attributes) {
        attributes.addFlashAttribute("link", "hello max");
        return new RedirectView("/qwe");
    }

    @GetMapping("/qwe")
    public ModelAndView redirection(ModelMap model, @ModelAttribute("link") Object flashAttribute) {
        model.addAttribute("link", flashAttribute);
        return new ModelAndView("notMain", model);
    }
}
