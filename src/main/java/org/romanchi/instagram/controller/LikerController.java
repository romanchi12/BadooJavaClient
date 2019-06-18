package org.romanchi.instagram.controller;

import org.romanchi.instagram.model.dto.GirlDTO;
import org.romanchi.instagram.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/liker")
@RestController
public class LikerController {

    private final ApiService apiService;

    @Autowired
    public LikerController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(value = "/next", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GirlDTO next(){
        return apiService.next();
    }

    @GetMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GirlDTO like(){
        return apiService.like();
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GirlDTO current(){
        return apiService.current();
    }

}
