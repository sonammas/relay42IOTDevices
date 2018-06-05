package com.mas.sonam.iotproducer.iotproducer.scheduledTasks;

import com.mas.sonam.iotproducer.iotproducer.model.IotDevice;
import com.mas.sonam.iotproducer.iotproducer.producer.IotProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ScheduledClass {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledClass.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final IotProducerService iotProducerService;

    public ScheduledClass(IotProducerService iotProducerService) {
        this.iotProducerService = iotProducerService;
    }

    private long leftLimit = 1L;
    private long rightLimit = 10L;
    private long generatedLongValue = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

    //Schedular to stimulate IOTDevice1 - thermostat data
    @Scheduled(fixedRate = 1000)
    public void scheduleTaskToSendThermostatData() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        IotDevice thermostat = IotDevice.builder().deviceName("thermostat").value(generatedLongValue).date(new Date()).build();
        iotProducerService.publish(thermostat);
    }

    //Schedular to stimulate IOTDevice2 - heart meter data
    @Scheduled(fixedRate = 1000)
    public void scheduleTaskToSendHeartMeterData() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        IotDevice heartMeter = IotDevice.builder().deviceName("heartMeter").value(generatedLongValue).date(new Date()).build();
        iotProducerService.publish(heartMeter);
    }

    //Schedular to stimulate IOTDevice3 - car fuel data
    @Scheduled(fixedRate = 1000)
    public void scheduleTaskToSendCarFuelData() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        IotDevice carFuel = IotDevice.builder().deviceName("carFuel").value(generatedLongValue).date(new Date()).build();
        iotProducerService.publish(carFuel);
    }
}

