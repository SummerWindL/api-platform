package com.platform.api.config;

import com.platform.api.bean.Consumer;
import com.platform.api.bean.Producer;
import com.platform.api.cmd.mqtt.interf.Cmd10000Handler;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.util.StringUtils;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mqtt")
@IntegrationComponentScan
public class MqttConfiguration {
    @Bean
    public Cmd10000Handler cmd10000Handler(){
        return new Cmd10000Handler();
    }
    /**
     * 出站-生产者
     */
    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";

    /**
     * 入站-消费者
     */
    public static final String CHANNEL_NAME_IN = "mqttInputChannel";

    private String username;
    private String password;
    private String url;
    private String clientId;
    private String topic = "TOPIC_DEFAULT";
    private Integer completionTimeout = 2000;
    private Consumer consumer;
    private Producer producer;
    private String defalutTopic = "TOPIC_DEFAULT";

    /**
     * 注册MQTT客户端工厂
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() throws MqttException {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(0);
        options.setKeepAliveInterval(90);
        options.setAutomaticReconnect(true);
        options.setUserName(this.getUsername());
        options.setPassword(this.getPassword().toCharArray());
        options.setServerURIs(new String[]{this.getUrl()});

        factory.setConnectionOptions(options);
        return factory;
    }
}
