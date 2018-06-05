package com.mas.sonam.iotproducer.iotproducer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class IotDevice {

    private String id;

    private String deviceName;

    private Long value;

    private Date date;

    @JsonCreator
    public IotDevice(@JsonProperty("id") String id,
                     @JsonProperty("deviceName") String deviceName,
                     @JsonProperty("value") Long value,
                     @JsonProperty("date") Date date) {
        this.id = id;
        this.deviceName = deviceName;
        this.date = date;
        this.value = value;
    }
}
