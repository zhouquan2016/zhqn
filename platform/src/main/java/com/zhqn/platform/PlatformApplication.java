package com.zhqn.platform;

import com.zhqn.platform.properties.PlatformProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouquan3
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zhqn.base", "com.zhqn.platform"})
@EnableConfigurationProperties(PlatformProperties.class)
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

}
