package com.push.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class PushResponse {

    private List<String> messageIds;
    private Integer failureCount;
    private Integer successCount;
    private List<String> tokens;

}
