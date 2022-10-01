package com.practice.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    public String index(){
        return "index";
    }

    @GetMapping(value = "/uslugi")
    public String uslugi(){
        return "uslugi";
    }

    @GetMapping(value = "/about-us")
    public String about(){
        return "about";
    }
}
