package com.mas.sonam.iotconsumer.iotconsumer.controller;

import com.mas.sonam.iotconsumer.iotconsumer.service.IotQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author sonam.masuriha
 *
 * This Query Controller is to query the readings (example: min, max, median, average etc.) of specific sensor device
 * or group of sensors device for a specific timeframe
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "iotdevice")
public class IotQueryController {

    private final IotQueryService iotService;

    public IotQueryController(IotQueryService iotService) {
        this.iotService = iotService;
    }

    /**
     * This rest end point to used to query the readings to get average, min, max, median etc of ONE specific sensor for a
     * particular timeframe
     *
     * @param deviceName
     * @param operation
     * @param fromDate
     * @param toDate
     * @return Double value - can be minimum, maximum, average, median of the readings
     */
    @GetMapping(value = "{deviceName}/operation/{operation}")
    public Double getReadingByDeviceNameAndWithinDates(@PathVariable final String deviceName, @PathVariable final String operation,
                                                    @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") final Date fromDate,
                                                    @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") final Date toDate) {
        return iotService.getReadingsValueByDeviceNameAndTimestamp(deviceName, fromDate, toDate, operation);
    }

/**
     * This rest end point to used to query the readings to get average, min, max, median etc for group of sensors for a
     * particular timeframe
     *
     * @param deviceNames
     * @param operation
     * @param fromDate
     * @param toDate
     * @return Double value - can be minimum, maximum, average, median of the readings
     */

    @GetMapping(value = "/operation/{operation}")
    public Double getReadingsForGroupOfDeviceByDeviceNameAndWithinDates(@RequestParam("deviceName") final List<String> deviceNames, @PathVariable final String operation,
                                                                        @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") final Date fromDate,
                                                                        @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") final Date toDate) {
        return iotService.getReadingsForGroupOfDeviceByDeviceNameAndWithinDates(deviceNames, fromDate, toDate, operation);
    }
}
