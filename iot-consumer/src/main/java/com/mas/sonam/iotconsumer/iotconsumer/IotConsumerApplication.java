package com.mas.sonam.iotconsumer.iotconsumer;

import com.mas.sonam.iotconsumer.iotconsumer.config.KafkaConsumerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories
@EnableConfigurationProperties(KafkaConsumerProperties.class)
public class IotConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotConsumerApplication.class, args);
    }
}
