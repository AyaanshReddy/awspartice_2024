package com.order.data.OrderService.publisher;

import com.order.data.OrderService.entity.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {
    Logger logger = LoggerFactory.getLogger(OrderPublisher.class);

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${order.event.topicName}")
    private String topicName;

    public String sendOrderEvent(OrderEvent event){
        logger.info("-++++++++++++++++++++++++++++++ sendOrderEvent +++++++++++++++++++++-"+topicName);
        kafkaTemplate.send(topicName, event);
        logger.info("!!!!!!!!!!!!!!!success...!!!!!!!!!!!!!!");
        return "Order sent to kafka message";
    }
}
