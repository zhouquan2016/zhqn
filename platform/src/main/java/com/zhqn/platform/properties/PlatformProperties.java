package com.zhqn.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * FileName: PlatformProperties
 * Date:     2022/9/29 14:56
 * Description:
 * @author zhouquan3
 */
@Component
@ConfigurationProperties(prefix = "platform")
@Data
public class PlatformProperties {

    private ElasticProperties elastic;

    private ClickhouseProperties clickhouse;

    @Data
    public static class ElasticProperties {
        /**
         * http[s]://localhost:9200
         */
        private String hostname;
        /**
         * 用户名
         */
        private String username;
        /**
         * 密码
         */
        private String password;
    }

    @Data
    public static class ClickhouseProperties  {

        /**
         * ip
         */
        private String host;

        /**
         * 端口
         */
        private Integer port;

        /**
         * 数据库
         */
        private String database;

        /**
         * 用户名
         */
        private String user;

        /**
         * 密码
         */
        private String password;

        /**
         * 市区
         */
        private String timeZone;

        /**
         * 其他ck参数
         */
        private Map<String, String> other;
    }
}
