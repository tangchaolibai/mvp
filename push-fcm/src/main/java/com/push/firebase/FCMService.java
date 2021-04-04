package com.push.firebase;

import com.google.firebase.messaging.*;
import com.push.entity.PushMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public List<String> sendMessageByTokens(PushMessage pushMessage){

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(getMulticastMessage(pushMessage));
            if (response.getFailureCount() > 0){
                List<SendResponse> responses = response.getResponses();
                List<String> failedTokens = new ArrayList<>();
                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()){
                        failedTokens.add(pushMessage.getTokens().get(i));
                    }
                }
                return failedTokens;
            }
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendMessageBySingleTokenOrTopic(PushMessage pushMessage){
        Message.Builder builder = getMessageBuilder(pushMessage);
        String messageId = "";
        try {
            Message message = builder.setToken(pushMessage.getTokens().get(0)).build();
            messageId = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return messageId;
    }

    private MulticastMessage getMulticastMessage(PushMessage pushMessage){
        return MulticastMessage.builder()
                .setAndroidConfig(getAndroidConfig(pushMessage))
                .setApnsConfig(getApnsConfig(pushMessage))
                .addAllTokens(pushMessage.getTokens())
                .build();
    }

    private Message.Builder  getMessageBuilder(PushMessage pushMessage){
        Message.Builder builder = Message.builder()
                .setAndroidConfig(getAndroidConfig(pushMessage))
                .setApnsConfig(getApnsConfig(pushMessage));

        String topic = pushMessage.getTopic();
        if (!StringUtils.isEmpty(topic)){
            boolean in = topic.contains("in");
            if (in){
                builder.setCondition(topic);
            }else {
                builder.setTopic(topic);
            }
        }

        return builder;
    }

    //Apple
    private ApnsConfig getApnsConfig(PushMessage pushMessage){
        return ApnsConfig.builder()
                .setAps(getAps(pushMessage))
                .build();
    }

    private Aps getAps(PushMessage pushMessage){
        return Aps.builder()
                .setAlert(getApsAlert(pushMessage))
                .setSound(pushMessage.getSound())
                .putAllCustomData(pushMessage.getData())
                .build();
    }

    private ApsAlert getApsAlert(PushMessage pushMessage){
        return ApsAlert.builder()
                .setTitle(pushMessage.getTitle())
                .setBody(pushMessage.getBody())
                .setLaunchImage(pushMessage.getImage())
                .build();
    }

    //Andriod
    private AndroidConfig getAndroidConfig(PushMessage pushMessage){

        AndroidConfig.Builder builder = AndroidConfig.builder()
                .setNotification(getAndroidNotification(pushMessage))
                .setTtl(pushMessage.getTtl())
                .setDirectBootOk(pushMessage.isDirectBookOk())
                .putAllData(pushMessage.getData());

        String topic = pushMessage.getTopic();
        boolean in = topic.contains("in");
        if (!in){
            builder.setCollapseKey(topic);
        }

        return builder.build();
    }

    private AndroidNotification getAndroidNotification(PushMessage pushMessage){

        AndroidNotification.Builder builder = AndroidNotification.builder()
                .setTitle(pushMessage.getTitle())
                .setBody(pushMessage.getBody())
                .setImage(pushMessage.getImage())
                .setColor(pushMessage.getColor())
                .setIcon(pushMessage.getIcon())
                .setSticky(pushMessage.isSticky());
        String level = pushMessage.getPriority().toUpperCase();

        switch (level){
            case "HIGH":
                builder.setPriority(AndroidNotification.Priority.HIGH);
                break;
            case "LOW":
                builder.setPriority(AndroidNotification.Priority.LOW);
                break;
            case "MAX ":
                builder.setPriority(AndroidNotification.Priority.MAX);
                break;
            case "MIN ":
                builder.setPriority(AndroidNotification.Priority.MIN);
                break;
            default:
                builder.setPriority(AndroidNotification.Priority.DEFAULT);
        }

        if (!StringUtils.isEmpty(pushMessage.getSound())){
            builder.setSound(pushMessage.getSound());
        }else {
            builder.setDefaultSound(true);
        }

        return builder.build();
    }
}
