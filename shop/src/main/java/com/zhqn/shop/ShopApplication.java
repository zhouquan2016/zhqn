package com.zhqn.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouquan3
 */
@SpringBootApplication
//@EnableEurekaClient()
@ComponentScan(basePackages = {"com.zhqn"})
@Slf4j
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        log.info("-----------------启动完成----------------------------------");
    }

}
