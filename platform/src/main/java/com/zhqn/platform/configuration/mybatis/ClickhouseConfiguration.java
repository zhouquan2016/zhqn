package com.zhqn.platform.configuration.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.clickhouse.client.config.ClickHouseDefaults;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.clickhouse.jdbc.JdbcConfig;
import com.zhqn.platform.properties.PlatformProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * FileName: ClickhouseConfiguration
 * Date:     2022/9/29 16:21
 * Description:
 * @author zhouquan3
 */
@Slf4j
@Configuration
@MapperScan("com.zhqn.platform.mapper.clickhouse")
public class ClickhouseConfiguration extends BaseMybatisConfig{

    @Resource
    PlatformProperties platformProperties;

    @Bean
    DataSource clickhouseDataSource() throws SQLException {
        PlatformProperties.ClickhouseProperties clickhouseProperties = platformProperties.getClickhouse();
        log.info("clickhouse properties:{}", JSON.toJSONString(clickhouseProperties));
        Properties properties = parseProperties(clickhouseProperties);
        String jdbcUrl = "jdbc:ch://" + clickhouseProperties.getHost() + ":" + clickhouseProperties.getPort() + "/" + clickhouseProperties.getDatabase();
        log.info("clickhouse jdbcUrl:{}", jdbcUrl);
        return new ClickHouseDataSource(jdbcUrl, properties);
    }

    private Properties parseProperties(PlatformProperties.ClickhouseProperties clickhouseProperties) {
        Properties properties = new Properties();
        properties.setProperty(ClickHouseDefaults.HOST.getKey(), object2String(clickhouseProperties.getHost()));
        properties.setProperty(ClickHouseDefaults.PORT.getKey(), object2String(clickhouseProperties.getPort()));
        properties.setProperty(ClickHouseDefaults.DATABASE.getKey(), object2String(clickhouseProperties.getDatabase()));
        properties.setProperty(ClickHouseDefaults.USER.getKey(), object2String(clickhouseProperties.getUser()));
        properties.setProperty(ClickHouseDefaults.PASSWORD.getKey(), object2String(clickhouseProperties.getPassword()));
        properties.setProperty(ClickHouseDefaults.SERVER_TIME_ZONE.getKey(), object2String(clickhouseProperties.getTimeZone()));
        /**
         * see com.clickhouse.jdbc.JdbcConfig#JdbcConfig(java.util.Properties)
         */
        properties.setProperty(JdbcConfig.PROP_CREATE_DATABASE, "false");
        if (CollectionUtil.isNotEmpty(clickhouseProperties.getOther())) {
            properties.putAll(clickhouseProperties.getOther());
        }
        return properties;
    }

    private String object2String(Object prop) {
        if (Objects.isNull(prop)) {
            return "";
        }
        return prop.toString();
    }

    @Bean
    SqlSessionFactory clickhouseSqlSessionFactory(DataSource clickhouseDataSource) throws Exception {

        return sqlSessionFactory(clickhouseDataSource, new String[]{"classpath:mapper/clickhouse/*.xml"});
    }

    @Bean
    SqlSessionTemplate clickhouseSqlSessionTemplate(SqlSessionFactory clickhouseSqlSessionFactory) {

        return sqlSessionTemplate(clickhouseSqlSessionFactory);
    }
}
