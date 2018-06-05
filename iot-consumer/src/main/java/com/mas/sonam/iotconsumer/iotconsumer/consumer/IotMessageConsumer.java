package com.mas.sonam.iotconsumer.iotconsumer.consumer;

import com.mas.sonam.iotconsumer.iotconsumer.model.IotDevice;
import com.mas.sonam.iotconsumer.iotconsumer.service.IotSaveKafkaMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * This service consumes the message from Kafka bus and then send request to save the object to MongoDB
 */
@Service
public class IotMessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(IotMessageConsumer.class);

    private final IotSaveKafkaMessageService iotSaveKafkaMessageService;

    public IotMessageConsumer(IotSaveKafkaMessageService iotSaveKafkaMessageService) {
        this.iotSaveKafkaMessageService = iotSaveKafkaMessageService;
    }

    @KafkaListener(topics = "iotdevice")
    public void onReceiving(IotDevice iotDevice, @Header(KafkaHeaders.OFFSET) Integer offset,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Processing topic = {}, partition = {}, offset = {}, iotDevice = {}",
                topic, partition, offset, iotDevice);

        //saving into mongoDB
        iotSaveKafkaMessageService.saveKafkaMessageToDatabase(iotDevice);
    }

}
