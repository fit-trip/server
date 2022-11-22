package com.example.mapp.route.model;

import com.example.mapp.schedule.model.Schedule;
import com.example.mapp.route.model.id.RouteInfoId;
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
@Table(name = "RouteInfoPerTime")
public class RouteInfoPerTime extends RouteInfo {

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

    @Column(name = "time")
    Integer time;

    @Builder
    public RouteInfoPerTime(RouteInfoId id, Schedule scheduleId, Location startLocationId, Location endLocationId,
                            Integer time) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.startLocationId = startLocationId;
        this.endLocationId = endLocationId;
        this.time = time;
    }
}
