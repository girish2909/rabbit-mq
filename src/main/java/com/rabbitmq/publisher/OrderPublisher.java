package com.rabbitmq.publisher;

import com.rabbitmq.dto.Order;
import com.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Value("${rabbitmq.custom.topic-exchange}")
    private String exchangeName;

    @Value("${rabbitmq.custom.queue-name}")
    private String queueName;

    @Value("${rabbitmq.custom.routing-key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus= new OrderStatus(order, "PROCESS", "order placed successfully in "+restaurantName);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderStatus);
        return "Success !!";
    }
}
