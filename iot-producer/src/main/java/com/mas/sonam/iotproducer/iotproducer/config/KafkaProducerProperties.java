package com.mas.sonam.iotproducer.iotproducer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "kafka.producer")
public class KafkaProducerProperties {

    private String bootstrap;

    private String topic;
}
