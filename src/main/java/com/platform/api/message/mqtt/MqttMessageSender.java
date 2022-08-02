package com.platform.api.message.mqtt;

import com.platform.api.bean.MqttMessageBody;
import com.platform.api.common.util.SpringContextUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MqttMessageSender {

    private MqttGateway mqttGateway;

    public void send(String topic, String message) {
        mqttGateway.sendToMqtt(topic, message);
    }
    /**
     *
     * @param topic
     * @param messageBody
     */
    public void send(String topic, int qos, MqttMessageBody messageBody){
        mqttGateway.sendToMqtt(topic, qos, messageBody.toString());
    }

    public void send(String topic, int qos, byte[] message){
        mqttGateway.sendToMqtt(topic, qos, message);
    }

}
