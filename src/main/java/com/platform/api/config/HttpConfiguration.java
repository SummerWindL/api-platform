package com.platform.api.config;

import com.platform.api.message.http.client.HttpClientService;
import com.platform.api.message.http.client.impl.ApacheHttpClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.outbound.AbstractHttpRequestExecutingMessageHandler;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/10
 */
@EnableIntegration
@Configuration
public class HttpConfiguration {

    private String url="http://localhost:8081/api/postHello/";//http服务器地址
    @Bean("httpRequest")
    public MessageChannel httpClientService(){
        return new DirectChannel();
    }
    /**
     * http请求配置
     * 这量数据管道名称为 http_out
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "http_out")
    public MessageHandler httpHandler(){
        AbstractHttpRequestExecutingMessageHandler http = new HttpRequestExecutingMessageHandler(url);
        DefaultHttpHeaderMapper httpHeaderMapper= new DefaultHttpHeaderMapper();
        httpHeaderMapper.setOutboundHeaderNames("*");//允许自定义协议的头部信息
        http.setHttpMethod(HttpMethod.POST);//post方式
        http.setCharset("UTF-8");//编码方式
        http.setHeaderMapper(httpHeaderMapper);
        //http.setExtractPayload(true);
        http.setExpectedResponseType(String.class);//请求以纯字符串的方式返回
        return http;
    }
    @Bean
    public HttpRequestHandlingMessagingGateway inbound() {
        HttpRequestHandlingMessagingGateway gateway =
                new HttpRequestHandlingMessagingGateway(true);
        gateway.setRequestMapping(mapping());
        gateway.setRequestPayloadType(ResolvableType.forClass(String.class));
        gateway.setRequestChannelName("httpRequest");
        return gateway;
    }

    @Bean
    public RequestMapping mapping() {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns("/foo");
        requestMapping.setMethods(HttpMethod.POST);
        return requestMapping;
    }

}
