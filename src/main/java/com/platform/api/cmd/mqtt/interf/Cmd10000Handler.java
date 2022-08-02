package com.platform.api.cmd.mqtt.interf;

import com.platform.api.bean.APIPlatformLog;
import com.platform.api.bean.APIResponse;
import com.platform.api.cmd.mqtt.MqttMessageCmdHandler;
import com.platform.api.common.annotation.LogAspect;
import com.platform.api.common.util.IDGenerator;
import com.platform.api.log.APIPlatformLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author : yanl
 * @version V1.0
 * @Description: 接口10000处理类
 * @date : 2022/7/9
 */
@Slf4j
@Service
public class Cmd10000Handler implements MqttMessageCmdHandler {

    @LogAspect(descript = "日志注解")
    @Override
    public APIResponse handleMessage(String cmdNo, String cmdOperatorType, Object payload) {
        log.info("获取到接口10000,{},{},{}",cmdNo,cmdOperatorType,payload);
        return new APIResponse(0);
    }
}
