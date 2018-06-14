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

}
