package com.platform.api.config;

import com.platform.api.message.mqtt.MqttMessageReceiver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
public class MqttInboundConfiguration {
    private MqttConfiguration mqttConfig;
    private MqttPahoClientFactory factory;
    private MqttMessageReceiver mqttMessageReceiver;
    //private MqttPahoMessageDrivenChannelAdapter adapter;
    /**
     * 此处可以使用其他消息通道
     * Spring Integration默认的消息通道，它允许将消息发送给一个订阅者，然后阻碍发送直到消息被接收。
     *
     * @return
     */
    @Bean
    public MessageChannel mqttInBoundChannel() {
        return new DirectChannel();
    }

    /**
     * 适配器, 两个topic共用一个adapter
     * 客户端作为消费者，订阅主题，消费消息
     * @param
     * @param
     * @return
     */
    @Bean//name = MqttConfiguration.ADAPTER_NAME_IN)
    public MessageProducerSupport mqttInbound() {
        //应该判断当前mqtt服务是否启动
        MqttPahoMessageDrivenChannelAdapter
                adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + System.currentTimeMillis(),
                factory,
                StringUtils.split(mqttConfig.getConsumer().getTopic(), ","));
        adapter.setCompletionTimeout(60000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setRecoveryInterval(10000);
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInBoundChannel());
        return adapter;
    }

    /**
     * 添加主题
     * @param topic
     */
    /*public void addTopic(String topic){
        if (adapter == null){
            adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + System.currentTimeMillis(),
                    factory, "");
        }
        adapter.addTopic(topic,0);
        log.info("添加 :"+ topic);
    }*/

    /**
     * 移除主题
     * @param topic
     */
    /*public void removeTopic(String topic){
        if (adapter == null){
            adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + System.currentTimeMillis(),
                    factory, "");
        }
        log.info("移除 :"+topic);
        adapter.removeTopic(topic);
    }*/


    /*@Bean(name = MqttConfiguration.CHANNEL_NAME_IN)
    public MqttPahoMessageDrivenChannelAdapter adapter(){
        return new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + System.currentTimeMillis(),
                factory,
                StringUtils.split(mqttConfig.getConsumer().getTopic(), ","));
    }*/

    /**
     * mqtt入站消息处理工具，对于指定消息入站通道接收到生产者生产的消息后处理消息的工具。
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInBoundChannel")
    public MessageHandler mqttMessageHandler() {
        return this.mqttMessageReceiver;
    }
}
