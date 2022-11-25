package com.example.mapp.route.model.id;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
@Getter
public class LocationId implements Serializable {
    @Column(name = "x")
    private Double x;
    @Column(name = "y")
    private Double y;

    @Builder
    public LocationId(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
