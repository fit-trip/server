package com.example.mapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class RouteInfo {

    @EmbeddedId
    RouteInfoId routeInfoId;

    @Column
    private Integer order;
}