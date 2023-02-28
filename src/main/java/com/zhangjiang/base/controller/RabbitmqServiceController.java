package com.zhangjiang.base.controller;

import com.zhangjiang.base.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className RabbitmqServiceController
 * @Author zhangjiang
 * @Description: RabbitMQ消息中间件测试
 * @Date 2023/2/13 10:42:57
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqServiceController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/sendMessage")
    public void sendMessage(){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "topic.hello", "rabbitmq hello");
    }
}
