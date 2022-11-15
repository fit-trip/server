package com.example.mapp.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "RouteInfoPerTime")
public class RouteInfoPerTime extends RouteInfo{
    Integer time;
}
