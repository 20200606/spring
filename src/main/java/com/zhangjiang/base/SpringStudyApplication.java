package com.zhangjiang.base;

import com.zhangjiang.base.config.FastDfsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = "com.zhangjiang.base.mapper")
@Import(FastDfsConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)

public class SpringStudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringStudyApplication.class, args);
        RabbitMQStart rabbitMQStart = context.getBean(RabbitMQStart.class);
        rabbitMQStart.start();
    }

    @Bean
    public RabbitMQStart rabbitMQRun() {
        return new RabbitMQStart();
    }

    private static class RabbitMQStart {
        //为了在main中的static方法中使用@value注解只能用这种办法
        @Value("${rabbitmq.start}")
        private Boolean rabbitmqStart;

        @Resource
        RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

        public void start() {
            if (rabbitmqStart)
                rabbitListenerEndpointRegistry.start();
            else
                rabbitListenerEndpointRegistry.stop();
        }
    }

}
