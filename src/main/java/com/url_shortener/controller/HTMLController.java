package com.url_shortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {

    @GetMapping(path = "/")
    public String getAllUrls() {
        return "index";
    }
}
