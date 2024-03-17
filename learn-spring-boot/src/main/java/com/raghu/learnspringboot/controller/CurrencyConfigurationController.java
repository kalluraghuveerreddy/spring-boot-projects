package com.raghu.learnspringboot.controller;

import com.raghu.learnspringboot.dto.Course;
import com.raghu.learnspringboot.dto.CurrencyServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    CurrencyServiceConfiguration configuration;
    @GetMapping("/currency-configuration")
    public CurrencyServiceConfiguration getCourseDetails() {
        return configuration;
    }

}
