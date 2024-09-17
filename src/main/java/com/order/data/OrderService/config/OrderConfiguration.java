package com.order.data.OrderService.config;

import com.order.data.OrderService.entity.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrderConfiguration {

    Logger logger= LoggerFactory.getLogger(OrderConfiguration.class);

    @Value("${order.event.topicName}")
    private String topicName;

    @Bean
    public NewTopic createTopic(){
        logger.info("======================OrderConfiguration=====topic creating ================"+topicName);
        return new NewTopic(topicName,3,(short)1);
    }


    // Method
    @Bean
    public ProducerFactory<String, OrderEvent> producerFactory()
    {

        // Creating a Map
        Map<String, Object> config = new HashMap<>();

        // Adding Configuration

        // 127.0.0.1:9092 is the default port number for
        // kafka
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                org.springframework.kafka.support.serializer.JsonSerializer.class);

        return new DefaultKafkaProducerFactory<String, OrderEvent>(config);
    }

    // Annotation
    // Method
    @Bean
    public KafkaTemplate<String, OrderEvent> kafkaTemplate()
    {
        return new KafkaTemplate<String, OrderEvent>(producerFactory());
    }

}
