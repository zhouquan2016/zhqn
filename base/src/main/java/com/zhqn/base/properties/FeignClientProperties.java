package com.zhqn.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
public class FeignClientProperties {

    String platformUrl;

}
