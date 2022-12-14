package com.zhqn.base.configuration;

import com.zhqn.base.feign.platform.AppLogClient;
import com.zhqn.base.properties.FeignProperties;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * FileName: FeignClientConfiguration
 * Author:   zhouquan3
 * Date:     2022/10/21 18:34
 * Description:
 * @author zhouquan3
 */
@Configuration
@EnableConfigurationProperties(FeignProperties.class)
public class FeignClientConfiguration {

    @Resource
    FeignProperties feignProperties;


    @Bean
    public Feign.Builder baseBuilder() {
        return Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder());
    }

    @Bean
    public AppLogClient appLogClient (Feign.Builder baseBuilder) {
        return baseBuilder.target(AppLogClient.class, getFeignUrl("platform"));
    }

    private String getFeignUrl(String name) {
        String url = feignProperties.getClients() != null ? feignProperties.getClients().get("platform") : "http://platform";
        System.out.println(name + " url:" + url);
        return url;
    }
}
