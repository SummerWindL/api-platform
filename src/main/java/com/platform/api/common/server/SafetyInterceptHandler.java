//package com.platform.api.common.server;
//
//import io.moquette.interception.AbstractInterceptHandler;
//import io.moquette.interception.messages.InterceptAcknowledgedMessage;
//import io.moquette.interception.messages.InterceptConnectMessage;
//import io.moquette.interception.messages.InterceptConnectionLostMessage;
//import io.moquette.interception.messages.InterceptPublishMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * @author Advance
// * @date 2022年07月16日 10:59
// * @since V1.0.0
// * 处理业务逻辑 拦截器
// */
//@Slf4j
//@Component
//public class SafetyInterceptHandler extends AbstractInterceptHandler {
//    @Override
//    public String getID() {
//        return SafetyInterceptHandler.class.getName();
//    }
//    @Override
//    public void onConnect(InterceptConnectMessage msg) {
//
//    }
//    @Override
//    public void onConnectionLost(InterceptConnectionLostMessage msg) {
//
//    }
//    @Override
//    public void onPublish(InterceptPublishMessage msg) {
//
//    }
//    @Override
//    public void onMessageAcknowledged(InterceptAcknowledgedMessage msg) {
//
//    }
//}
