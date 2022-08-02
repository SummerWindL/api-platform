package com.platform.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yanl
 * @version V1.0
 * @Description: MQTT消息体
 * @date : 2022/7/9
 */
@Data
public class MqttMessageBody implements Serializable {

    private static final long serialVersionUID = -2438211882814972524L;
    private String topic;
    private String content;
    private int qos;

    public MqttMessageBody(String topic, String content, int qos) {
        this.topic = topic;
        this.content = content;
        this.qos = qos;
    }
}
