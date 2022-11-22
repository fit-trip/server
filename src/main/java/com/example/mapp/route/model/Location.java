package com.example.mapp.route.model;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Location")
public class Location {

    @Id @GeneratedValue
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "x")
    Double x;

    @Column(name = "y")
    Double y;

    @Builder
    public Location(String id, String name, Double x, Double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}