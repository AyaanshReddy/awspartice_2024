package com.order.data.OrderService.controller;

import com.order.data.OrderService.dto.request.OrderRequest;
import com.order.data.OrderService.dto.response.OrderResponse;

import com.order.data.OrderService.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public String getTestMessage(){
        logger.info("##^^^^^^^^^^^^^^^^^^^^^^^ getTestMessage #^^^^^^^^^^^^^^^^^^^");
        return "service is up.... running.....";
    }

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        logger.info("################# placeOrder ########################");
        try {
            logger.info("###@@@@@@@@@@@@@@@@ placeOrder @@@@@@@@@@@@@@@@@@@@@@@@@@@#");
            OrderResponse orderResponse = orderService.placeAnOrder(orderRequest);
            logger.info("#$$$$$$$$$$$$$$$$ place the first order #$$$$$$$$$$$$$$$$$$$$$$$$$");
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
            //return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("%%%%%%%%%%%%%%%%%%%%"+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable String orderId) {
        try {
            logger.info("^^^^^^^^^^^^^^^^^^^^^^confirmOrder^^^^^^^^^^^^^^^^^^^^");
            OrderResponse orderResponse = orderService.confirmOrder(orderId);
            logger.info("************** Confirm the order **************");
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
           // return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("************** Confirm the Exception **************");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
