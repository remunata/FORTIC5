package com.pplbo.fortic5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "login";
    }

    @GetMapping("/product")
    public String productDetail() {
        return "product";
    }
}
