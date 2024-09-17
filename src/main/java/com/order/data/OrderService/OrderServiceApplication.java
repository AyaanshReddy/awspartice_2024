package com.order.data.OrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*@OpenAPIDefinition(info=@Info(
		title = "order sevice",
		version = "1.0.0",
		description ="order applicaation",
		termsOfService="runcode",
		contact = @Contact(
				name="rajulu",
				email="rajulu@gmail.com"
		)
))*/
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
