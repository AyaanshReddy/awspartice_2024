package com.order.data.OrderService.dto.response;

import com.order.data.OrderService.dto.enums.OrderStatus;

public class OrderResponse {
    private String orderId;
    private OrderStatus status;

    public OrderResponse(String orderId, OrderStatus status) {
        this.status = status;
        this.orderId = orderId;
    }

    public OrderResponse() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
