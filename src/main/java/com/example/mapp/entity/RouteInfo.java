package com.example.mapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public abstract class RouteInfo {

    @Column(name = "orders")
    private Integer order;

    public void updateOrder(int order) {
        this.order = order;
    }
}