package com.platform.api.cmd.mqtt.interf;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.platform.api.bean.APIResponse;
import com.platform.api.cmd.mqtt.MqttMessageCmdHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : yanl
 * @version V1.0
 * @Description: 接口Cmd10010
 * @date : 2022/7/10
 */
@Slf4j
public class Cmd10010Handler implements MqttMessageCmdHandler {

    @Override
    public APIResponse handleMessage(String cmdNo, String cmdOperatorType, Object payload) {
        log.info("获取到接口10010,{},{},{}",cmdNo,cmdOperatorType,JSONUtil.toJsonStr(payload));
        return new APIResponse(0);
    }
}
