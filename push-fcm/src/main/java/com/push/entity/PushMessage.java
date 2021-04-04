package com.push.entity;

import com.google.api.client.util.Key;
import com.google.firebase.messaging.Notification;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PushMessage {
    private String platform;
    private Long ttl;
    private Map data;
    private String priority;
    private List<String> tokens;
    private String topic;
    private String icon;
    private String color;
    private String sound;
    private boolean directBookOk;
    private String title;
    private String body;
    private String image;
    private boolean sticky;
}
