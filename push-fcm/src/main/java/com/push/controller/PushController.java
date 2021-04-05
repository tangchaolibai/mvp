package com.push.controller;

import com.push.common.PushResponse;
import com.push.common.ResponseResult;
import com.push.entity.PushMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PushController {

    @GetMapping("test")
    public PushResponse test(){

        PushResponse pushResponse = new PushResponse();
        pushResponse.setFailureCount(100);
        pushResponse.setSuccessCount(200);
        pushResponse.setTokens(Arrays.asList("1111","22222","3333333"));
        pushResponse.setMessageIds(Arrays.asList("aaaa","vvvv","ddddd"));
        return pushResponse;
    }

    @PostMapping("PushMessage")
    public void PushMessage(List<PushMessage> pushMessages){

    }

}
