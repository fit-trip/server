package com.example.mapp.route.model;

import com.example.mapp.schedule.model.Schedule;
import com.example.mapp.route.model.id.RouteInfoId;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "RouteInfoPerFare")
public class RouteInfoPerFare extends RouteInfo {

    @EmbeddedId
    private RouteInfoId id;

    @MapsId("scheduleId")
    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @MapsId("locationId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "x", referencedColumnName = "x"),
            @JoinColumn(name = "y", referencedColumnName = "y")
    })
    private Location location;

    @Column(name = "fareForNextLocation")
    Integer fareForNextLocation;

    @Builder
    public RouteInfoPerFare(RouteInfoId id, Schedule schedule, Location location,
                            Integer fareForNextLocation) {
        this.id = id;
        this.schedule = schedule;
        this.location = location;
        this.fareForNextLocation = fareForNextLocation;
    }
}
