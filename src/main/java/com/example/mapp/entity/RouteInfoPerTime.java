package com.example.mapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "RouteInfoPerTime")
public class RouteInfoPerTime extends RouteInfo{

    @Column(name = "time")
    Integer time;
}
