package com.example.mapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@MappedSuperclass
public abstract class RouteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column
    private Integer order;

    @Column
    private Integer startLocationId;

    @Column
    private Integer endLocationId;
}
