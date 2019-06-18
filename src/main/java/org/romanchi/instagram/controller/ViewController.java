package org.romanchi.instagram.controller;

import org.romanchi.instagram.services.GirlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller
@RequestMapping("/")
public class ViewController {

    private final GirlsService girlsService;

    @Autowired
    public ViewController(GirlsService girlsService) {
        this.girlsService = girlsService;
    }

    @GetMapping
    public ModelAndView page(){
        return new ModelAndView("view");
    }
    @GetMapping("/liked")
    public ModelAndView liked(){

        ModelAndView modelAndView = new ModelAndView("liked");
        modelAndView.addObject("girls", girlsService.getAllLikedGirls());

        return modelAndView;
    }
}
