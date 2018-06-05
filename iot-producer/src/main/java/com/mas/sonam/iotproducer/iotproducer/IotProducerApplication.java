package com.mas.sonam.iotproducer.iotproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotProducerApplication.class, args);
    }
}
