package com.mas.sonam.iotconsumer.iotconsumer.service;

import com.mas.sonam.iotconsumer.iotconsumer.model.IotDevice;
import com.mas.sonam.iotconsumer.iotconsumer.repository.IotDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
@AllArgsConstructor
public class IotQueryService {

    @Autowired
    private final IotDeviceRepository iotDeviceRepository;

    public Double getReadingsValueByDeviceNameAndTimestamp(final String deviceName, final Date fromDate,
                                                           final Date toDate, final String operation) {
        List<IotDevice> listOfDeviceByDeviceName = iotDeviceRepository.findByDeviceNameAndDateBetween(deviceName, fromDate, toDate);
        return calculateReadings(operation, listOfDeviceByDeviceName);
    }

    public Double getReadingsForGroupOfDeviceByDeviceNameAndWithinDates(final List<String> deviceNames, final Date fromDate,
                                                                        final Date toDate, final String operation) {
        List<IotDevice> listOfDeviceByDeviceName = deviceNames.stream()
                .flatMap(deviceName -> iotDeviceRepository.findByDeviceNameAndDateBetween(deviceName, fromDate, toDate).stream())
                .collect(Collectors.toList());
        return calculateReadings(operation, listOfDeviceByDeviceName);
    }

    private Double calculateReadings(String operation, List<IotDevice> listOfDeviceByDeviceName) {
        final LongSummaryStatistics longSummaryStatistics = listOfDeviceByDeviceName.stream()
                .map(IotDevice::getValue)
                .mapToLong((x) -> x)
                .summaryStatistics();
        switch (operation) {
            case "max":
                return (double) longSummaryStatistics.getMax();
            case "min":
                return (double) longSummaryStatistics.getMin();
            case "average":
                return longSummaryStatistics.getAverage();
            case "median": {
                DoubleStream sortedAges = listOfDeviceByDeviceName.stream().mapToDouble(IotDevice::getValue).sorted();
                return listOfDeviceByDeviceName.size() % 2 == 0 ?
                        sortedAges.skip(listOfDeviceByDeviceName.size() / 2 - 1).limit(2).average().getAsDouble() :
                        sortedAges.skip(listOfDeviceByDeviceName.size() / 2).findFirst().getAsDouble();
            }
        }
        return null;
    }
}
