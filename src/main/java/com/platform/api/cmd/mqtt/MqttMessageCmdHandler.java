package com.platform.api.cmd.mqtt;

import com.platform.api.bean.APIResponse;

/**
 * 为了区分topic的handler ，新增接口级别的Handler处理，各接口都需要实现此方法以实现数据接收
 * @author : yanl
 * @version V1.0
 * @date : 2022/7/9
 */
public interface MqttMessageCmdHandler {

    /**
     * 消息处理
     * @param cmdNo
     * @param cmdOperatorType
     * @param payload
     * @return APIResponse
     */
    APIResponse handleMessage(String cmdNo, String cmdOperatorType, Object payload);
}
