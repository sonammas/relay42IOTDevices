package com.mas.sonam.iotconsumer.iotconsumer.service;

import com.mas.sonam.iotconsumer.iotconsumer.model.IotDevice;
import com.mas.sonam.iotconsumer.iotconsumer.repository.IotDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * This service will save the iotDevice data to the mongoDB as a document
 */
@Service
public class IotSaveKafkaMessageService {

    private static final Logger log = LoggerFactory.getLogger(IotSaveKafkaMessageService.class);

    @Autowired
    private final IotDeviceRepository iotDeviceRepository;

    public IotSaveKafkaMessageService(IotDeviceRepository iotDeviceRepository) {
        this.iotDeviceRepository = iotDeviceRepository;
    }

    public void saveKafkaMessageToDatabase(final IotDevice iotDevice) {
        iotDevice.setId(UUID.randomUUID().toString());
        iotDeviceRepository.save(iotDevice);
        log.info("IotDeviceData is saved into the database = {}", iotDevice.getId());
    }
}
