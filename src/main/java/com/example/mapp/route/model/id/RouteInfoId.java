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

    private String scheduleId;
    private String startLocationId;
    private String endLocationId;

    @Builder
    public RouteInfoId(String scheduleId, String startLocationId, String endLocationId) {
        this.scheduleId = scheduleId;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
    }
}
