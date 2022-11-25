package com.example.mapp.route.vo;

import com.example.mapp.route.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CoordinateVo {
    private Double x;
    private Double y;
    private String name;

    public CoordinateVo(Location location) {
        this.x = location.getId().getX();
        this.y = location.getId().getY();
        this.name = location.getName();
    }
}
