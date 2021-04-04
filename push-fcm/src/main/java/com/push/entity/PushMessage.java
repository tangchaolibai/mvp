package com.push.entity;

import com.google.firebase.messaging.Notification;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PushMessage {
    private String platform;
    private Long ttl;
    private Map<String,Object> data;
    private String priority;
    private List<String> tokens;
    private String topic;
    private String icon;
    private String color;
    private String sound;
    private String directBookOk;
    private Notification notification;
}
