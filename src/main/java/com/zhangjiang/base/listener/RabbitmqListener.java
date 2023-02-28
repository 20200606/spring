package com.zhangjiang.base.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className RabbitmqListener
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/13 10:46:59
 */
@Component
public class RabbitmqListener {

    @RabbitListener(queues = "topic_queue")
    public void listenerQueue(Message message){
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("message = " + new String(message.getBody()));
        }
    }
}
