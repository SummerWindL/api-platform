package com.platform.api.message.mqtt;

import com.platform.api.config.MqttConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
//@Configuration(value = "com.platform.api.message.mqtt")
//@IntegrationComponentScan(value = "com.platform.api.message.mqtt")
@MessagingGateway(defaultRequestChannel = MqttConfiguration.CHANNEL_NAME_OUT)
public interface MqttGateway {
    /**
     *
     * @param topic
     * @param payload
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     *
     * @param topic
     * @param qos 对消息处理的几种机制。
     *      * 0 表示的是订阅者没收到消息不会再次发送，消息会丢失。<br>
     *      * 1 表示的是会尝试重试，一直到接收到消息，但这种情况可能导致订阅者收到多次重复消息。<br>
     *      * 2 多了一次去重的动作，确保订阅者收到的消息有一次。
     * @param payload json串
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, byte[] payload);
}
