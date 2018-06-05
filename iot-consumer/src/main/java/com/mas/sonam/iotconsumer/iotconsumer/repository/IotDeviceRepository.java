package com.mas.sonam.iotconsumer.iotconsumer.repository;

import com.mas.sonam.iotconsumer.iotconsumer.model.IotDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IotDeviceRepository extends MongoRepository<IotDevice, String> {

    @Query( "{'deviceName': ?0, 'date': {$gte: ?1, $lte:?2 }}")
    List<IotDevice>  findByDeviceNameAndDateBetween(String deviceName, Date startDate, Date toDate);
}