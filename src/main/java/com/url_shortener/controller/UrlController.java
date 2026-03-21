package com.url_shortener.controller;

import com.url_shortener.models.entity.UrlMapping;
import com.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping(path = "/shorten")
    public ResponseEntity<?> shorten(@RequestBody Map<String, String> request) {
        String shortCode = urlService.shortenUrl(request.get("url"), request.get("shortCode"));
        return ResponseEntity.ok().body(Map.of("shortCode",shortCode));
    }

    @GetMapping(path = "/{shortCode}")
    public ResponseEntity<?> getUrl(@PathVariable("shortCode") String shortCode) {
       String originalUrl = urlService.findByShortCode(shortCode);

       if(Objects.equals(originalUrl, "NOT_FOUND")) {
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity
               .status(HttpStatus.FOUND)
               .location(URI.create(originalUrl))
               .build();
    }

}
