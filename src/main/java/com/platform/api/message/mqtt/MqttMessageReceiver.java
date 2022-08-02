package com.platform.api.message.mqtt;

import com.platform.api.cmd.mqtt.MqttMessageCmdHandler;
import com.platform.api.cmd.mqtt.MqttResponseBody;
import com.platform.api.cmd.mqtt.impl.MqttMessageAcceptImpl;
import com.platform.api.config.MqttConfiguration;
import com.platform.api.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */

@Slf4j
@Component
public class MqttMessageReceiver implements MessageHandler {

    public Map<String,Object> handleMap = new ConcurrentHashMap<>();
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        try {
            MqttMessageAcceptImpl mqttMessageAccept = new MqttMessageAcceptImpl();
            MessageHeaders headers = message.getHeaders();
            //获取消息Topic
            String receivedTopic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);
            //log.info("[获取到的消息的topic :]{} ", receivedTopic);
            //获取消息体
            String payload = (String) message.getPayload();
            //log.info("[获取到的消息的payload :]{} ", payload);
            //分发各接口实现核心代码
            MqttResponseBody mqttResponseBody = mqttMessageAccept.acceptNotifcation(payload);
            handleMessage(mqttResponseBody);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void handleMessage(MqttResponseBody mqttResponseBody) {
        if (handleMap.containsKey(mqttResponseBody.getCmdNo())) {
            MqttMessageCmdHandler messageCmdHandler = (MqttMessageCmdHandler) handleMap.get(mqttResponseBody.getCmdNo());
            messageCmdHandler.handleMessage(mqttResponseBody.getCmdNo(), mqttResponseBody.getCmdOperatorType(),mqttResponseBody.getPayload());
        }
    }
}