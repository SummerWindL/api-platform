package com.platform.api.config;

import com.platform.api.common.util.MqttUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
@Slf4j
@AllArgsConstructor
@Configuration
@IntegrationComponentScan
public class MqttOutboundConfiguration {
    private MqttConfiguration mqttConfiguration;
    private MqttPahoClientFactory factory;

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
                e -> e.poller(Pollers.fixedDelay(2000)))
                .transform(p -> p + "")
                .handle(mqttOutbound())
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttConfiguration.getProducer().getClientId() + System.currentTimeMillis(),
                factory);
        //开启异步
        messageHandler.setDefaultQos(0);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttConfiguration.getDefalutTopic());
        MqttUtils.put("mqttOutboundChannel",messageHandler); //添加通道
        return messageHandler;
    }

    @Bean//(name = MqttConfiguration.CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        DirectChannel dc = new DirectChannel();
        dc.subscribe(mqttOutbound());
        return dc;
    }
}
