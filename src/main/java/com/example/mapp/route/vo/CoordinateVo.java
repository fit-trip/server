package com.example.mapp.route.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CoordinateVo {
    private String x;
    private String y;
}
