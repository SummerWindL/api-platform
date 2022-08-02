package com.platform.api.common;

import cn.hutool.json.JSONUtil;
import com.platform.api.bean.APIPlatformLog;
import com.platform.api.cmd.mqtt.MqttResponseBody;
import com.platform.api.common.util.IDGenerator;
import com.platform.api.log.APIPlatformLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author Advance
 * @date 2022年07月13日 23:13
 * @since V1.0.0
 * 日志切面处理
 */
@Slf4j
@Aspect
@Component
public class LogAspectAop {
    @Autowired
    private APIPlatformLogRepository repository;
//    @Pointcut("@annotation(com.platform.api.common.annotation.LogAspect)")
//    @Pointcut("execution(public * com.platform.api.controller.*.*(..))")
    @Pointcut("execution(public * com.platform.api.cmd..*.*(..))")
    public void handleCmd(){

    }

    @Before("handleCmd()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + "=" + args[i];
            }
            log.info("入参:" + params);
//            MqttResponseBody mqttResponseBody = JSONUtil.parseObj(params).toBean(MqttResponseBody.class);
//            APIPlatformLog apiPlatformLog = new APIPlatformLog();
//            apiPlatformLog.setCmdNo(mqttResponseBody.getCmdNo());
//            apiPlatformLog.setSerialId(IDGenerator.generate(32));
//            apiPlatformLog.setRespMsg(mqttResponseBody.getPayload());
//            repository.save(apiPlatformLog);
        }
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("类名:" + declaringTypeName);
        String methodName = joinPoint.getSignature().getName();
        log.info("方法名:" + methodName);
    }

}


