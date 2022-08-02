package com.platform.api.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Advance
 * @date 2022年07月13日 17:40
 * @since V1.0.0
 */
@Entity
@Data
@Table(name = "INTERF_API_PLATFORM_LOG")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@IdClass(APIPlatformLogPK.class)
public class APIPlatformLog {
    /**
     * serial_id  流水号
     * @GeneratedValue(strategy = GenerationType.IDENTITY)  //指定具体策略
     * @GenericGenerator
     */
    @Id
    @Column(nullable = false,name = "serial_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.platform.api.common.util.IDGenerator")
    private String serialId;
    /**
     * 命令号
     */
    @Column(name = "cmd_no")
    private  String cmdNo;
    /**
     * 操作类型
     * 插入：insert
     * 更新：update
     * 删除：delete
     */
    @Column(name = "operator_type")
    private  String operatorType;
    /**
     * 命令号版本
     */
    @Column(name = "cmd_version")
    private  String cmdVersion;
    /**
     * 接口调用方式：mqtt、http
     */
    @Column(name = "call_type")
    private  String callType;
    /**
     * 报文信息
     */
    @Column(name = "req_msg")
    private  String reqMsg;
    /**
     * 调用方
     */
    @Column(name = "req_sys")
    private  String reqSys;
    /**
     * 请求时间
     */
    @Column(name = "req_time")
    private Date reqTime;
    /**
     * 接收方
     */
    @Column(name = "resp_sys")
    private  String respSys;
    /**
     * 接收方处理类
     */
    @Column(name = "handler")
    private  String handler;
    /**
     * 响应信息
     */
    @Column(name = "resp_msg")
    private  String respMsg;
    /**
     * 处理状态：0：处理完成；1：处理失败
     */
    @Column(name = "handle_status")
    private  String handleStatus;
    /**
     * topic
     */
    @Column(name = "topic")
    private  String topic;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private  Date createTime;

}
