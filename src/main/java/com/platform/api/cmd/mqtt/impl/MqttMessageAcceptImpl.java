package com.platform.api.cmd.mqtt.impl;

import cn.hutool.json.JSONUtil;
import com.platform.api.cmd.mqtt.MqttMessageAcceptHandler;
import com.platform.api.cmd.mqtt.MqttResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : yanl
 * @version V1.0
 * @Description: default 消息实体转换器
 * @date : 2022/7/9
 */
public class MqttMessageAcceptImpl implements MqttMessageAcceptHandler {
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 转换
     * @param payload
     * @return
     */
    @Override
    public MqttResponseBody acceptNotifcation(String payload) {
        MqttResponseBody notice = null;
        try{
            notice = JSONUtil.parseObj(payload).toBean(MqttResponseBody.class);
        }catch (Exception e){
            log.error("消息转换失败：{}",e.getMessage());
            e.printStackTrace();
        }

        return notice;
    }
}
