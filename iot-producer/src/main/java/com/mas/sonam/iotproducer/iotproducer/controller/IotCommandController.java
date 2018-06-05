package com.mas.sonam.iotproducer.iotproducer.controller;

import com.mas.sonam.iotproducer.iotproducer.model.IotDevice;
import com.mas.sonam.iotproducer.iotproducer.producer.IotProducerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This rest controller is used to publish any IotDevice data to kafka
 */
@RestController
@RequestMapping(value = "iotdevice")
public class IotCommandController {

    private final IotProducerService iotProducerService;

    public IotCommandController(IotProducerService iotProducerService) {
        this.iotProducerService = iotProducerService;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean publishIotDeviceData(@RequestBody IotDevice iotDevice) {
        return iotProducerService.publish(iotDevice);
    }
}
