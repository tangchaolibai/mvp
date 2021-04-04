package com.push.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {

    @GetMapping("test")
    public String test(){
        int a = 1/0;

        return "success";
    }

}
