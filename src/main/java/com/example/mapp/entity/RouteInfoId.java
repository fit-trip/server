package com.example.mapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RouteInfoId implements Serializable {

    @Column(name = "scheduleId")
    private Integer scheduleId;

    @Column(name = "startLocationId")
    private Integer startLocationId;

    @Column(name = "endLocationId")
    private Integer endLocationId;

}
