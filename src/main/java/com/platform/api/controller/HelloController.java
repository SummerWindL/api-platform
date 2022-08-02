package com.platform.api.controller;

import cn.hutool.json.JSONUtil;
import com.platform.api.bean.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/10
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class HelloController {
    @GetMapping(value = "/hello")
    @ResponseBody
    public APIResponse hello(){
        log.info(" say hello");
        int i = 10 / 0;
        return new APIResponse(0);
    }

    @PostMapping(value = "/postHello",produces = "application/json")
    @ResponseBody
    public APIResponse postHello(@RequestParam Map<String, Object> map){
        log.info("请求参数：{}",JSONUtil.toJsonStr(map));
        return new APIResponse("hello world!!!");
    }
}
