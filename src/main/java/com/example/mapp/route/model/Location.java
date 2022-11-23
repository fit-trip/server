package com.example.mapp.route.model;

import com.example.mapp.route.model.id.LocationId;
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

    @EmbeddedId
    private LocationId id;

    @Column(name = "name")
    private String name;

    @Builder
    public Location(LocationId id, String name, Double x, Double y) {
        this.id = id;
        this.name = name;
    }
}