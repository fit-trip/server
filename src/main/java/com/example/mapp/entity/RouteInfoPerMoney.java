package com.example.mapp.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "RouteInfoPerMoney")
public class RouteInfoPerMoney extends RouteInfo {

    @Column(name = "money")
    Integer money;
}
