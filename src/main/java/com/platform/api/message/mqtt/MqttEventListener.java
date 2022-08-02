package com.platform.api.message.mqtt;

import com.platform.api.cmd.mqtt.MqttMessageCmdHandler;
import com.platform.api.cmd.mqtt.interf.Cmd10000Handler;
import com.platform.api.cmd.mqtt.interf.Cmd10010Handler;
import com.platform.api.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author : yanl
 * @version V1.0
 * @Description: 容器启动就注册接口信息
 * @date : 2022/7/9
 */
@Component
@Slf4j
public class MqttEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MqttMessageReceiver mqttMessageReceiver;
    @Autowired
    private Cmd10000Handler cmd10000Handler;
    /**
     * 暴露的添加处理业务方法
     * @param cmdNo
     * @param messageCmdHandler
     */
    public synchronized void addHandler(String cmdNo, MqttMessageCmdHandler messageCmdHandler) {
        if (StringUtils.hasText(cmdNo) && mqttMessageReceiver.handleMap.containsKey(cmdNo)) {
            throw new ApiException("接收到的接口号为空，或者已经存在：【"+cmdNo+"】 请检查发送的报文信息！！！");
        }
        mqttMessageReceiver.handleMap.put(cmdNo, messageCmdHandler);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initInterfApplication();
    }

    /**
     * 定义所有接口
     */
    private void initInterfApplication() {
        log.info("注册：cmd_10000，cmd_10010");
        addHandler("cmd_10000",cmd10000Handler); //注册接口服务
        addHandler("cmd_10010",new Cmd10010Handler()); //注册接口服务
    }
}