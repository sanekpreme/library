package com.demo.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome, guest, please log in or create a new account");
        return "about";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }


}
