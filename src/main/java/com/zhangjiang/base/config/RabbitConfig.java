package com.zhangjiang.base.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className MQProducerAckConfig
 * @Author zhangjiang
 * @Description: Rabbitmq配置类
 * @Date 2023/2/13 10:22:27
 */
@Configuration
public class RabbitConfig{

    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String QUEUE_NAME = "topic_queue";

    /**
     * 定义Topic交换机
     * @return
     */
    @Bean("topicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 定义Topic队列
     * @return
     */
    @Bean("topicQueue")
    public Queue topicQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    /**
     * 绑定Topic交换机和Topic队列
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding bindQueueExchange(@Qualifier("topicExchange") Exchange exchange,
                                     @Qualifier("topicQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("topic.*").noargs();

    }
}
