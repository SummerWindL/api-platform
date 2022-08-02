package com.platform.api.cmd.mqtt;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yanl
 * @version V1.0
 * @Description: 响应体
 * @date : 2022/7/9
 */
@Data
public class MqttResponseBody implements Serializable {
    private static final long serialVersionUID = 7452407960049276342L;
    private String cmdNo;
    private String cmdOperatorType; //操作类型 插入/回滚
    private String payload; //响应内容
}
