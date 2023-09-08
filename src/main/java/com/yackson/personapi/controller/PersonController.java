package com.yackson.personapi.controller;

import com.yackson.personapi.service.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private WorldService worldService;

    @GetMapping("/getRandom/{amount}")
    public void getRandomPerson(@PathVariable Integer amount) {

    }
}
