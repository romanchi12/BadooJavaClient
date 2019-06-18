package org.romanchi.instagram.controller;

import org.romanchi.instagram.model.entities.Girl;
import org.romanchi.instagram.services.GirlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@SessionScope
@RequestMapping("/")
public class ViewController {

    private Integer page = 0;

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
    public ModelAndView liked(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int offset, @RequestParam(defaultValue = "asc") String sort){

        ModelAndView modelAndView = new ModelAndView("liked");
        Sort.Direction direction = (sort == null || "asc".equals(sort.toLowerCase()))
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        List<Girl> girls = girlsService.getAllLikedGirls(
                PageRequest.of(page, offset, Sort.by(direction, "age")));
        modelAndView.addObject("girls",girls);

        return modelAndView;
    }
}
