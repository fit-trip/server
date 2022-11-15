package com.example.mapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "Id")
    User user;

    @Column(name = "totalTime")
    Integer totalTime;

    @Column(name = "totalMoney")
    Integer totalMoney;

    @ManyToOne
    @JoinColumn(name = "routeInfoId")
    RouteInfo routeInfoPerMoney;

    @ManyToOne
    @JoinColumn(name = "routeInfoId")
    RouteInfo routeInfoPerTime;
}
