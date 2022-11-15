package com.example.mapp.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class RouteInfoId implements Serializable {


//    @Column(name = "scheduleId")
    @ManyToOne
    @JoinColumn(name = "id")
    private Schedule scheduleId;

//    @Column(name = "startLocationId")
    @ManyToOne
    @JoinColumn(name = "id")
    private Location startLocationId;

//    @Column(name = "endLocationId")
    @ManyToOne
    @JoinColumn(name = "id")
    private Location endLocationId;

}
