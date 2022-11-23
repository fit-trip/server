package com.example.mapp.route.vo;

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
}
