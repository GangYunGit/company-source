package com.jobtang.sourcecompany.api.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("")
    public String test(){
        return "test 통과";
    }
}
