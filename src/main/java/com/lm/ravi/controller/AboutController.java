package com.lm.ravi.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";  // This will render the about.html file from the templates folder
    }
}