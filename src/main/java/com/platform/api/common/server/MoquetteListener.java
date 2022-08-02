//package com.platform.api.common.server;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * @author Advance
// * @date 2022年07月16日 11:10
// * @since V1.0.0
// */
//@Component
//@Slf4j
//public class MoquetteListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private MoquetteServer moquetteServer;
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        //启动Moquette 服务
//        try {
//            moquetteServer.startServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                moquetteServer.stop();
//                log.info("Moquette Server stopped");
//            }
//        });
//
//    }
//}
