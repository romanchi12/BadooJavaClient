package org.romanchi.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ViewController {
    @GetMapping
    public ModelAndView page(){
        ModelAndView modelAndView = new ModelAndView("view.html");
        return modelAndView;

    }
}
