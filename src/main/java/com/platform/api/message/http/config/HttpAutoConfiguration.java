package com.platform.api.message.http.config;

import com.platform.api.message.http.client.HttpClientService;
import com.platform.api.message.http.client.impl.ApacheHttpClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: fuyl
 * @create: 2020-07-03 11:03
 **/
@Configuration
@ConditionalOnClass({ApacheHttpClientServiceImpl.class})
public class HttpAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HttpClientService.class)
    ApacheHttpClientServiceImpl apacheHttpClient() {
        return new ApacheHttpClientServiceImpl();
    }

}
