package com.platform.api;

import com.platform.api.bean.MqttMessageBody;
import com.platform.api.common.util.SpringContextUtil;
import com.platform.api.message.http.HttpMsgGateway;
import com.platform.api.message.http.client.HttpClientService;
import com.platform.api.message.http.client.HttpResponseWrapper;
import com.platform.api.message.mqtt.MqttGateway;
import com.platform.api.message.mqtt.MqttMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
@SpringBootTest(classes = ApiApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@IntegrationComponentScan(value = "com.platform.api")
//@SpringBootTest(classes = ApiApplication.class,webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiApplicationTest {
    @Autowired
    private MqttMessageSender sender;
    @Autowired
    private HttpMsgGateway msgGateway;
    @Autowired
    private MqttGateway mqttGateway;
    @Autowired
    HttpClientService httpClientService;

    @Test
    public void testSendMqtt(){
        System.out.println(msgGateway);
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
//        sender = context.getBean(MqttMessageSender.class);
        //CountDownLatch countDownLatch = new CountDownLatch(1000);

//        sender.send("twotopic","{'cmdNo':'cmd_10010','cmdOperatorType':'insert','payload':{'username':'advance'}}");
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    sender.send("twotopic","{'cmdNo':'cmd_10000','cmdOperatorType':'insert','payload':'xxxxx'}");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();*/

        /*Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();*/

        /*new Thread() {
            int i = 0;
            public void run(){
                boolean flg = false;
                while(!flg){
                    try {
                        i++;
                        System.out.println("第"+i+"次消息发送！");
                        sender.send("twotopic","{'cmdNo':'cmd_10010','cmdOperatorType':'insert','payload':{'username':'advance'}}");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/
        int i = 0;
        for(;;){
            i++;
            try {
                Thread.sleep(5000);//10秒发一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mqttGateway.sendToMqtt("twotopic","{'cmdNo':'cmd_10000','cmdOperatorType':'insert','payload':{'username':'advance','count':"+i+"}}");
        }

//        new Thread(()->{
//
//        },"t2").start();
//
//        new Thread(()->{
//            sender.send("twotopic","{'cmdNo':'cmd_10000','cmdOperatorType':'insert','payload':'xxxxx'}");
//        },"t3").start();


        //sender.send("twotopic",1,new MqttMessageBody("twotopic","{'password:'112','username':'advance'}",2));
    }

    @Test
    public void httpTest(){
        //-----需要传到服务器上的参数----------------------------------------
        Map<String,String> pars = new HashMap<>();
        pars.put("par1","xiefangjian");//参数1
        Message<Map<String,String>> ms = MessageBuilder.withPayload(pars)
                .setHeader(HttpHeaders.ACCEPT_LANGUAGE,"zh-CN")//Accept-Language 设置为zh-CN
                .setHeader("aaaa","xiefangjian")//自定义协议头
                .build();
        String body = msgGateway.Post(ms);//这里body为服务器返回的内容
        log.info(body);

        /*try {
            Map map = new HashMap();
            map.put("a","1");
            HttpResponseWrapper responseWrapper = httpClientService.post("http://localhost:8081/api/postHello/", map);
            log.info(responseWrapper.getResponseBody());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

    }
}
