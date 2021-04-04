package com.push.controller;

import com.push.entity.PushMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PushController {

    @GetMapping("test")
    public String test(){
        int a = 1/0;

        return "success";
    }

    @PostMapping("PushMessage")
    public void PushMessage(List<PushMessage> pushMessages){

    }

}
