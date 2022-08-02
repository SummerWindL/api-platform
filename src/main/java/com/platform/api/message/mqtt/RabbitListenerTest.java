package com.platform.api.message.mqtt;

import com.alibaba.fastjson.JSONObject;
//import com.platform.rabbitmq.entity.SimpleRabbitMessage;
//import com.platform.rabbitmq.util.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Advance
 * @date 2022年07月18日 21:13
 * @since V1.0.0
 */
@Slf4j
@Lazy(false)
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RabbitListenerTest {

    /*@RabbitListener(queues = "queue.interf.execute")
    public void process(SimpleRabbitMessage context, Channel channel, Message message) throws IOException {
        System.err.println("dlx queue");
        //RabbitUtils.ack(channel, message);
        System.out.println(1 / 0);
        log.info(JSONObject.toJSONString(context.getMessage()));
    }

    @RabbitListener(queues = "queue.test.execute")
    public void process2(SimpleRabbitMessage context, Channel channel, Message message) throws IOException {
        System.err.println("dlx queue");
        //RabbitUtils.ack(channel, message);
        log.info(JSONObject.toJSONString(context.getMessage()));
    }
    @RabbitListener(queues = "queue.test2.execute")
    @SendTo(value = "queue.test2.execute")
    public Map<String, Object> process3(SimpleRabbitMessage context, Channel channel, Message message) throws IOException {
        System.err.println("dlx queue");
        //RabbitUtils.ack(channel, message);
        log.info(JSONObject.toJSONString(context.getMessage()));
        Map<String,Object> resultProcess = new HashMap<>();
        resultProcess.put("status","sucess");
        return resultProcess;
    }*/
}
