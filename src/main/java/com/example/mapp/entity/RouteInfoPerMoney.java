package com.example.mapp.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RouteInfoPerMoney")
public class RouteInfoPerMoney extends RouteInfo {

    @EmbeddedId
    private RouteInfoId id;

    @MapsId("scheduleId")
    @ManyToOne
    @JoinColumn(name = "schedule_Id")
    private Schedule scheduleId;

    @MapsId("startLocationId")
    @ManyToOne
    @JoinColumn(name = "start_location_id")
    private Location startLocationId;

    @MapsId("endLocationId")
    @ManyToOne
    @JoinColumn(name = "end_location_id")
    private Location endLocationId;

    @Column(name = "money")
    Integer money;

    @Builder
    public RouteInfoPerMoney(RouteInfoId id, Schedule scheduleId, Location startLocationId, Location endLocationId,
                             Integer money) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
        this.money = money;
    }
}
