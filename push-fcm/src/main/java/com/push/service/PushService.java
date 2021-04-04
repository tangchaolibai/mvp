package com.push.service;

import com.push.entity.PushMessage;
import com.push.firebase.FCMService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PushService {

    @Resource
    private FCMService fcmService;

    public void sendMessage(List<PushMessage> pushMessages){
        List<String> failTokens = new ArrayList<>();
        if (pushMessages.size() == 1){
            PushMessage pushMessage = pushMessages.get(0);
            List<String> tokens = pushMessage.getTokens();
            if (tokens.size() == 1){
                String messageId = fcmService.sendMessageBySingleTokenOrTopic(pushMessage);
            }else if (tokens.size() >1){
                List<String> failToken = fcmService.sendMessageByTokens(pushMessage);
            }
        }else if (pushMessages.size() > 1){

        }
    }

}
