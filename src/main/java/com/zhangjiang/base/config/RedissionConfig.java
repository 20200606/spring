package com.zhangjiang.base.config;

import jodd.util.StringUtil;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className RedissionConfig
 * @Author zhangjiang
 * @Description: redission配置信息
 * @Date 2023/2/10 16:56:42
 */
@Data
@Configuration
@ConfigurationProperties("spring.redis")
public class RedissionConfig {

    private String host;
    private String port;
    private String password;
    private String address;

    private int timeout = 3000;
    private int connectionPoolSize = 64;
    private int connectionMinimumIdleSize=10;
    private int pingConnectionInterval = 60000;
    private static final String PREFIX="redis://";

    /**
     * 自动装配
     * @return
     */
    @Bean
    RedissonClient singleredissonClient() {
        Config config = new Config();
        if (StringUtil.isEmpty(host)) {
            throw new RuntimeException("host is empty");
        }
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(PREFIX + this.host + ":" + this.port)
                .setTimeout(this.timeout)
                .setPingConnectionInterval(pingConnectionInterval)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize);
        if (!StringUtil.isEmpty(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create();
    }
}
