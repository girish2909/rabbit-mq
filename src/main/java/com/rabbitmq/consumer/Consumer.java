package com.rabbitmq.consumer;

import com.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "${rabbitmq.custom.queue-name}")
    public void consumerMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Mesage received from queue :"+orderStatus);
    }
}
