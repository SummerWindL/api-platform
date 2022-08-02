package com.platform.api.cmd.mqtt;

/**
 * @author : yanl
 * @version V1.0
 * @date : 2022/7/9
 */
public interface MqttMessageAcceptHandler {


    /**
     * 接收通知
     * @param payload
     * @return
     */
    MqttResponseBody acceptNotifcation(String payload);
}
