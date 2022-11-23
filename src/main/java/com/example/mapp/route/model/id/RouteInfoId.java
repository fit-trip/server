package com.example.mapp.route.model.id;

import lombok.AccessLevel;
import lombok.Builder;

import javax.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
public class RouteInfoId implements Serializable {

    private Integer scheduleId;
    private LocationId locationId;

    @Builder
    public RouteInfoId(Integer scheduleId, LocationId locationId) {
        this.scheduleId = scheduleId;
        this.locationId = locationId;
    }
}
