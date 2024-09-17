package com.order.data.OrderService.service;

import com.order.data.OrderService.dto.enums.OrderStatus;
import com.order.data.OrderService.dto.request.OrderRequest;
import com.order.data.OrderService.dto.response.OrderResponse;
import com.order.data.OrderService.entity.OrderEvent;
import com.order.data.OrderService.publisher.OrderPublisher;
import com.order.data.OrderService.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderPublisher publishOrder;

    // Handle order creation
    public OrderResponse placeAnOrder(OrderRequest orderRequest) {
        logger.info("************** placeAnOrder -- Service **************");
        String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);

        OrderEvent orderEvent = new OrderEvent(orderId,OrderStatus.CREATED,"Order created Successfully..", LocalDateTime.now());
        //save and send event to kafka.
        saveAndPublishEvent(orderEvent);
        logger.info("*############ placeAnOrder -- Service **######################*");
        return new OrderResponse(orderId, OrderStatus.CREATED);

    }
    // Handle order confirmation
    public OrderResponse confirmOrder(String orderId) {
        logger.info("*@@@@@@@@@@@@@ confirmOrder -- Service @@@@@@@@@@@");
        OrderEvent orderEvent = new OrderEvent(orderId,OrderStatus.CONFIRMED,
                "Order Confirmed Successfully..", LocalDateTime.now());
        //save and send event to kafka.
        saveAndPublishEvent(orderEvent);
        logger.info("*&&&&&&&&&&&&&& confirmOrder -- Service &&&&&&&&&&&&"+orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }

    private void saveAndPublishEvent(OrderEvent orderEvent){
        repository.save(orderEvent);
        publishOrder.sendOrderEvent(orderEvent);
    }
}
