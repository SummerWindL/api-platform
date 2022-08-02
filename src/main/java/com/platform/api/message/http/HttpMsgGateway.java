package com.platform.api.message.http;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : yanl
 * @version V1.0
 * @date : 2022/7/10
 */
@MessagingGateway
@Component
public interface HttpMsgGateway {
    /**
     * http请求接口
     * @param out 需要发送的内容
     * @return
     */
    @Gateway(requestChannel = "http_out")
    String Post(Message<Map<String,String>> out);
}
