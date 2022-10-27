package com.zhqn.base.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * FileName: FeignClientProperties
 * Author:   zhouquan3
 * Date:     2022/10/21 18:41
 * Description:
 * @author zhouquan3
 */
@ConfigurationProperties(prefix = "feign-client")
@Data
@Component
@Slf4j
public class FeignClientProperties {

    /**
     *
     */
    static boolean runByContainer = resolveRunByContainer();

    static boolean resolveRunByContainer () {
        String val = System.getProperty("runByContainer");
        if (!StringUtils.hasLength(val)) {
            throw new RuntimeException("runByContainer环境变量未指定!");
        }
        return Boolean.parseBoolean(val);
    }

    static String resolveFeignUrl(String serviceName, int nodePort) {
        return runByContainer ? "http://" + serviceName : "http://192.168.48.141:" + nodePort;
    }

    String platformUrl = resolveFeignUrl("platform", 32686);

    String shopUrl = resolveFeignUrl("shop", 30856);

}
