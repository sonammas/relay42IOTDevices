package com.mas.sonam.iotconsumer.iotconsumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
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
        this.value = value;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("deviceName", deviceName)
                .add("value", value)
                .add("date", date)
                .toString();
    }
}
