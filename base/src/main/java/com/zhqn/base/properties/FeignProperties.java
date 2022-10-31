package com.zhqn.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "feign-client")
@Component
@Data
public class FeignProperties {

    Map<String, String> clients;
}
